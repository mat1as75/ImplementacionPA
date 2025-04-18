
package Servlets;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import webservices.ContenidoService;
import webservices.ContenidoServiceService;


@WebServlet(name = "SVExisteAlbum", urlPatterns = {"/ExisteAlbum"})
public class SVExisteAlbum extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        ContenidoServiceService contenidoWS = new ContenidoServiceService();
        ContenidoService contenidoPort = contenidoWS.getContenidoServicePort();
        
        HttpSession sesion = request.getSession(false);
        if (sesion == null) {
            response.setStatus(response.SC_UNAUTHORIZED);
            response.getWriter().write("Error al obtener la sesión.");
            return;
        }
        
        String nickname = (String) sesion.getAttribute("nickname");
        if (nickname == null) {
            response.setStatus(response.SC_UNAUTHORIZED);
            response.getWriter().write("Error al obtener la sesión de usuario.");
            return;
        }

        String album;
        
        BufferedReader reader = request.getReader();
        StringBuilder body = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            body.append(line);  
        }
        reader.close();

        album = body.toString();
        
        Long idAlbum = contenidoPort.buscarAlbumPorNombreYArtista(nickname, album);
        
        response.setStatus(response.SC_OK);
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        if (idAlbum == 0L) {
            response.getWriter().write("No existe");
        } else {
            response.getWriter().write("Existe");
        }
    }

}
