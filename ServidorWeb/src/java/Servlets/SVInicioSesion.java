package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import webservices.DataTypes.DtArtista;
import webservices.DataTypes.DtCliente;
import webservices.DataTypes.DtUsuario;
import webservices.NullableContainer;
import webservices.UsuarioService;
import webservices.UsuarioServiceService;

@WebServlet(urlPatterns = {"/SVInicioSesion"})
public class SVInicioSesion extends HttpServlet {

    public enum Rol {
        Visitante,
        Cliente,
        Artista
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        /* Obtengo los datos de los inputs */
        String indentificadorUsuario = (String) request.getParameter("usuario");
        String contrasenaUsuario = (String) request.getParameter("contrasena");

        /* Instanciar UsuarioService */
        UsuarioServiceService serviceU = new UsuarioServiceService();
        UsuarioService serviceUsuario = serviceU.getUsuarioServicePort();

        /* Buscar UsuarioAutentificado */
        NullableContainer dtUserContainer = serviceUsuario.getUsuarioAutentificado(indentificadorUsuario, contrasenaUsuario);
        DtUsuario dtUser = dtUserContainer.getDtUsuario();
        
        /* Usuario existe en el Sistema */
        if (dtUser != null) {
            /* Es Artista & No es ACTIVO */
            if ((dtUser instanceof DtArtista dtArtista && dtArtista.isActivo()) || dtUser instanceof DtCliente) {

                /* Si el Usuario Existe en el Sistema simplemente 
                    dejo su Nickname como atributo de la Sesion */

                /* Crear sesion para el usuario */
                HttpSession miSesion = request.getSession();
                miSesion.setAttribute("nickname", (String) dtUser.getNickname());
                miSesion.setAttribute("DTusuarioConsultado", null);

                if (dtUser instanceof DtCliente){
                    miSesion.setAttribute("rol", "Cliente");
                }else{
                    miSesion.setAttribute("rol", "Artista");
                }

                /* Creo una cookie que persiste */
                Cookie cookie = new Cookie("sessionId", miSesion.getId());
                cookie.setMaxAge(60 * 60 * 24); // 1 dia
                response.addCookie(cookie);

                /* Establezco el Nickname del Usuario como atributo de la Cookie */
                Cookie cookieMiSesion = new Cookie("nickname", (String) miSesion.getAttribute("nickname"));
                response.addCookie(cookieMiSesion);

                /* Redirecciono al Usuario */
                response.sendRedirect("index.jsp?mensaje=abierta");
            } else {
                /* Artista inactivo */
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                request.setAttribute("mensaje", "Usuario inactivo.");
                /* Volver a la pagina de InicioSesion con un mensaje de error */
                request.getRequestDispatcher("InicioSesion.jsp").forward(request, response);
            }
        } else {
            /* Usuario no existe en el Sistema */
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            request.setAttribute("mensaje", "Usuario o contrasena incorrectos.");
            /* Usuario no encontrado, volver a la pagina de InicioSesion con un mensaje de error */
            request.getRequestDispatcher("InicioSesion.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
