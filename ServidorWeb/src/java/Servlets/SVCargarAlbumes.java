/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import com.google.gson.Gson;
import espotify.DataTypes.DTAlbum_Simple;
import espotify.logica.Fabrica;
import espotify.logica.IControlador;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author brisa
 */
@WebServlet(name = "SVCargarAlbumes", urlPatterns = {"/SVCargarAlbumes"})
public class SVCargarAlbumes extends HttpServlet {

     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String tipo = request.getParameter("tipo");
        String nombre = request.getParameter("nombre");

        Fabrica f = Fabrica.getInstance();
        IControlador iControlador = f.getControlador();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        if ("Genero".equals(tipo) && nombre != null && !nombre.isEmpty()){
            ArrayList<DTAlbum_Simple> albumesGenero = iControlador.getDTAlbumesSimplePorGenero(nombre);
            // Crear una lista para almacenar objetos con ID y nombre
            ArrayList<Map<String, String>> albumesJson = new ArrayList<>();
            for (DTAlbum_Simple album : albumesGenero) {
                Map<String, String> albumInfo = new HashMap<>();
                albumInfo.put("id", album.getIdAlbum().toString());  
                albumInfo.put("nombre", album.getNombreAlbum());
                albumesJson.add(albumInfo);
            }
    
            String json = new Gson().toJson(albumesJson);
            response.getWriter().write(json);
        } else if ("Artista".equals(tipo)&& nombre != null && !nombre.isEmpty()) {
            ArrayList<DTAlbum_Simple> albumesArtista = iControlador.getDTAlbumesSimplePorArtista(nombre);
            // Crear una lista para almacenar objetos con ID y nombre
            ArrayList<Map<String, String>> albumesJson = new ArrayList<>();
            for (DTAlbum_Simple album : albumesArtista) {
                Map<String, String> albumInfo = new HashMap<>();
                albumInfo.put("id", album.getIdAlbum().toString());  
                albumInfo.put("nombre", album.getNombreAlbum());
                albumesJson.add(albumInfo);
            }
            
            String json = new Gson().toJson(albumesArtista);
            response.getWriter().write(json);
            
        }
        
        
    }
    @Override
    public String getServletInfo() {
        return "Servlet carga albumes";
    }// </editor-fold>

}
