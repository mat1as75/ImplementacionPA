package Servlets;

import espotify.DataTypes.DTDatosCliente;
import espotify.logica.Fabrica;
import espotify.logica.IControlador;
import java.io.IOException;
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
@WebServlet(name = "SVGuardarTemaFavorito", urlPatterns = {"/SVGuardarTemaFavorito"})
public class SVGuardarTemaFavorito extends HttpServlet {

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
        String tipo = request.getParameter("tipo");
        String identificador = request.getParameter("identificador");
        
        
        String temaIdStr = request.getParameter("idTema");
        Long temaId = null;
        if (temaIdStr != null) {
            temaId = Long.parseLong(temaIdStr);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de tema inválido");
        }
         DTDatosCliente user = control.ConsultarPerfilCliente(request.getParameter("nickname"));
         // Obtener la sesión del usuario//
        HttpSession miSesion = request.getSession(false);
        miSesion.setAttribute("user", user);
        //Boolean suscripcionValida = (Boolean) miSesion.getAttribute("suscripcionValida");
        //String userRole = (String) miSesion.getAttribute("userRole");
        
        // Verificar que el usuario tenga rol de cliente y una suscripción válida
        //if (suscripcionValida == null || !suscripcionValida || !"cliente".equals(userRole)) {
          //  response.sendRedirect("error.jsp?message=Acceso denegado");
            //return;
        //}
        
         // Validar que el usuario esté autenticado y que se haya proporcionado el temaId
        //if (user == null || temaId == null ) {
          //  response.sendRedirect("error.jsp?message=Información incompleta");
            //return;
        //}
        
        try {
            control.GuardarTemaFavorito(user.getNicknameUsuario(), temaId);
        } catch (Exception ex) {
            Logger.getLogger(SVGuardarTemaFavorito.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(tipo.equals("Lista")){
            response.sendRedirect("DatosListaReproduccion.jsp?nombreLista="+ identificador);
        }else if(tipo.equals("Album")){
            Long id = Long.parseLong(identificador);
            response.sendRedirect("ConsultaAlbum.jsp?albumId="+ id);
            
        }
        
       
    }

}