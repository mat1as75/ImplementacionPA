package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import webservices.DataTypes.DtDatosArtista;
import webservices.DataTypes.DtDatosCliente;
import webservices.DataTypes.DtDatosUsuario;
import webservices.UsuarioService;
import webservices.UsuarioServiceService;

/**
 *
 * @author tecnologo
 */
@WebServlet(name = "SVConsultaPerfilUsuario", urlPatterns = {"/SVConsultaPerfilUsuario"})
public class SVConsultaPerfilUsuario extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        /* Instanciar UsuarioService */
        UsuarioServiceService serviceU = new UsuarioServiceService();
        UsuarioService serviceUsuario = serviceU.getUsuarioServicePort();
        
        /* DATOS DE LA SESION */
        HttpSession sesion = request.getSession(false);
        String nicknameUsuarioSesion = null;
        nicknameUsuarioSesion = (String) sesion.getAttribute("nickname");
        
        DtDatosUsuario datosUsuario = null;

        // Recupero el parametro enviado desde el formulario en resultados.jsp
        String usuarioConsultado = request.getParameter("usuario-Consultar");
        System.out.println("ACA3: " + usuarioConsultado);
        
        String rolSesion = (String) sesion.getAttribute("rol");
        if (rolSesion != null && rolSesion.equals("Artista")) {
            if (usuarioConsultado != null && !usuarioConsultado.equals(nicknameUsuarioSesion)) {
                SVError.redirectForbidden(request, response);
                return;
            }
        }
        
        if (usuarioConsultado == null) {
            System.out.println("ACA4");
            // ConsultaPerfilUsuario propio de la Sesion
            datosUsuario = serviceUsuario.getDatosUsuario(nicknameUsuarioSesion).getDtDatosUsuario();
        } else {
            System.out.println("ACA5");
            // ConsultaPerfilUsuario desde SearchBar
            datosUsuario = serviceUsuario.getDatosUsuario(usuarioConsultado).getDtDatosUsuario();
        }
        
        if (datosUsuario instanceof webservices.DataTypes.DtDatosCliente) {
            System.out.println("SVCONSULTA " + datosUsuario.getClass());
            // Envio un DataType con los datos del Usuario consultado
            sesion.setAttribute("DTusuarioConsultado", (DtDatosCliente) datosUsuario);
        } else {
            // Envio un DataType con los datos del Usuario consultado
            sesion.setAttribute("DTusuarioConsultado", (DtDatosArtista) datosUsuario);
        }

        response.sendRedirect("ConsultaPerfilUsuario.jsp");

    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Obtengo la sesion actual
        HttpSession sesion = request.getSession(false);
        
        // Lee el valor del nicknameUsuarioConsultado enviado desde el cliente
        String nicknameUsuarioConsultado = (String) request.getAttribute("nicknameUsuarioConsultado");
        
        // Asigno el valor a la sesion
        sesion.setAttribute("perfilConsultado", "nicknameUsuarioConsultado");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
}
