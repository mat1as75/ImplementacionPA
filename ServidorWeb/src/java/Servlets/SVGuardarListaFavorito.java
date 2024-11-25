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

@WebServlet(name = "SVGuardarListaFavorito", urlPatterns = {"/SVGuardarListaFavorito"})
public class SVGuardarListaFavorito extends HttpServlet {

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
        
        String nicknameSesion = request.getParameter("nickname");
        String nombreLista = request.getParameter("nombreLista");

        try {
            servicePreferencias.guardarListaFavorito(nicknameSesion, nombreLista);
        } catch (Exception_Exception ex) {
            Logger.getLogger(SVGuardarListaFavorito.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.sendRedirect("DatosListaReproduccion.jsp?nombreLista="+ nombreLista);
    }
}