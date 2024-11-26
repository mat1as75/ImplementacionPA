package Servlets;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import webservices.DataTypes.DtUsuarioGenerico;
import webservices.Exception_Exception;
import webservices.PreferenciasService;
import webservices.PreferenciasServiceService;
import webservices.UsuarioService;
import webservices.UsuarioServiceService;

/**
 *
 * @author brisa
 */
@WebServlet(name = "SVGuardarAlbumFavorito", urlPatterns = {"/SVGuardarAlbumFavorito"})
public class SVGuardarAlbumFavorito extends HttpServlet {
    
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
        
        String nicknameSesion = request.getParameter("nickname");
        String albumIdStr = request.getParameter("albumId");
        Long albumId = null;
        if (albumIdStr != null) {
            albumId = Long.valueOf(albumIdStr);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de álbum inválido");
        }
        
        try {
            servicePreferencias.guardarAlbumFavorito(nicknameSesion, albumId);
        } catch (Exception_Exception ex) {
            Logger.getLogger(SVGuardarAlbumFavorito.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        response.sendRedirect("ConsultaAlbum.jsp?albumId="+ albumId);
    }

}