package Servlets;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import webservices.Exception_Exception;
import webservices.PreferenciasService;
import webservices.PreferenciasServiceService;

/**
 *
 * @author brisa
 */
@WebServlet(name = "SVGuardarTemaFavorito", urlPatterns = {"/SVGuardarTemaFavorito"})
public class SVGuardarTemaFavorito extends HttpServlet {
    
    PreferenciasServiceService serviceP = new PreferenciasServiceService();
    PreferenciasService servicePreferencias = serviceP.getPreferenciasServicePort();

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
        
        String tipo = request.getParameter("tipo");
        String identificador = request.getParameter("identificador");
        
        String nicknameSesion = request.getParameter("nickname");
        String temaIdStr = request.getParameter("idTema");
        
        Long temaId = null;
        if (temaIdStr != null) {
            temaId = Long.valueOf(temaIdStr);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de tema inválido");
        }
        
        try {
            servicePreferencias.guardarTemaFavorito(nicknameSesion, temaId);
        } catch (Exception_Exception ex) {
            Logger.getLogger(SVGuardarTemaFavorito.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(tipo.equals("Lista")){
            response.sendRedirect("DatosListaReproduccion.jsp?nombreLista="+ identificador);
        }else if(tipo.equals("Album")){
            Long id = Long.valueOf(identificador);
            response.sendRedirect("ConsultaAlbum.jsp?albumId="+ id);   
        }
    }

}