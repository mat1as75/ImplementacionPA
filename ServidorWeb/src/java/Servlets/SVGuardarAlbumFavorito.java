package Servlets;

import espotify.DataTypes.DTDatosCliente;
import espotify.logica.Fabrica;
import espotify.logica.IControlador;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author brisa
 */
@WebServlet(name = "SVGuardarAlbumFavorito", urlPatterns = {"/SVGuardarAlbumFavorito"})
public class SVGuardarAlbumFavorito extends HttpServlet {

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
         // Obtener el controlador
        Fabrica fb = Fabrica.getInstance();
        IControlador control = fb.getControlador();
        
        
        String albumIdStr = request.getParameter("albumId");
        Long albumId = null;
        if (albumIdStr != null) {
            albumId = Long.parseLong(albumIdStr);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de álbum inválido");
        }
        System.out.println(request.getParameter("nickname"));
        DTDatosCliente user = control.ConsultarPerfilCliente(request.getParameter("nickname"));
         // Obtener la sesión del usuario//
        HttpSession miSesion = request.getSession(false);
        miSesion.setAttribute("user", user);
        //Boolean suscripcionValida = (Boolean) miSesion.getAttribute("suscripcionValida");
        //String userRole = (String) miSesion.getAttribute("userRole");
        // Verificar que el usuario tenga rol de cliente y una suscripción válida
        //if (suscripcionValida == null || !suscripcionValida || !"cliente".equals(userRole)) {
            //response.sendRedirect("error.jsp?message=Acceso denegado");
            //return;
        //}
        
         // Validar que el usuario esté autenticado y que se haya proporcionado el albumId 
        //if (user == null || albumId == null ) {
            //response.sendRedirect("error.jsp?message=Información incompleta");
           // return;
        //}
        
        try {
            control.GuardarAlbumFavorito(user.getNicknameUsuario(), albumId);
        } catch (Exception ex) {
            Logger.getLogger(SVGuardarAlbumFavorito.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.sendRedirect("ConsultaAlbum.jsp?albumId="+ albumId);
        
        
    }

}