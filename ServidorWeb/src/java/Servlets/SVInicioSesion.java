package Servlets;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
import espotify.DataTypes.DTCliente;
import espotify.DataTypes.DTDatosArtista;
import espotify.DataTypes.DTDatosCliente;
import espotify.DataTypes.DTDatosUsuario;
import espotify.DataTypes.DTUsuario;
import espotify.logica.Fabrica;
import espotify.logica.IControlador;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/SVInicioSesion"})
public class SVInicioSesion extends HttpServlet {

    private IControlador control = null;

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

        /* Instanciar un Controlador */
        Fabrica fb = Fabrica.getInstance();
        control = fb.getControlador();

        /* Buscar UsuarioAutentificado */
        DTUsuario dtUser = control.getUsuarioAutentificado(indentificadorUsuario, contrasenaUsuario);

        /* Usuario existe en el Sistema */
        if (dtUser != null) {
            /* Si el Usuario Existe en el Sistema simplemente 
                dejo su Nickname como atributo de la Sesion */
            
            /* Crear sesion para el usuario */
            HttpSession miSesion = request.getSession();
            miSesion.setAttribute("nickname", dtUser.getNickname());
            miSesion.setAttribute("perfilConsultado", null);

            if (dtUser instanceof DTCliente){
                miSesion.setAttribute("rol", "Cliente");
                System.out.println("Cliente");
            }else{
                miSesion.setAttribute("rol", "Artista");
                System.out.println("Artista");
            }
            
//            if (datosUsuario.getClass().equals(DTDatosCliente.class))
//                miSesion.setAttribute("rol", "Cliente");
//            else
//                miSesion.setAttribute("rol", "Artista");
            
            /* Establezco el Nickname del Usuario como atributo de la Cookie */
            Cookie cookieMiSesion = new Cookie("nickname", (String) miSesion.getAttribute("nickname"));
            response.addCookie(cookieMiSesion);

            /* Redirecciono al Usuario */
            response.sendRedirect("index.jsp?mensaje=abierta");
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
