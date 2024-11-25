/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import webservices.DataAlbumsService;
import webservices.DataAlbumsServiceService;
import webservices.DataTypes.DtAlbumSimple;

/**
 *
 * @author brisa
 */
@WebServlet(name = "SVCargarAlbumes", urlPatterns = {"/SVCargarAlbumes"})
public class SVCargarAlbumes extends HttpServlet {
    
    DataAlbumsServiceService serviceDA = new DataAlbumsServiceService();
    DataAlbumsService serviceDataAlbum = serviceDA.getDataAlbumsServicePort();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String tipo = request.getParameter("tipo");
        String nombre = request.getParameter("nombre");

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        ArrayList<Map<String, String>> albumesJson = new ArrayList<>();

        if ("Genero".equals(tipo) && nombre != null && !nombre.isEmpty()) {
            ArrayList<DtAlbumSimple> albumesGenero = serviceDataAlbum.getDTAlbumesSimplePorGenero(nombre).getColeccion()
                    .stream()
                    .filter(DtAlbumSimple.class::isInstance)
                    .map(DtAlbumSimple.class::cast)
                    .collect(Collectors.toCollection(ArrayList::new));
            for (DtAlbumSimple album : albumesGenero) {
                Map<String, String> albumInfo = new HashMap<>();
                albumInfo.put("id", album.getIdAlbum().toString());  
                albumInfo.put("nombre", album.getNombreAlbum());
                albumesJson.add(albumInfo);
            }
        } else if ("Artista".equals(tipo) && nombre != null && !nombre.isEmpty()) {
            ArrayList<DtAlbumSimple> albumesArtista = serviceDataAlbum.getDTAlbumesSimplePorArtista(nombre).getColeccion()
                    .stream()
                    .filter(DtAlbumSimple.class::isInstance)
                    .map(DtAlbumSimple.class::cast)
                    .collect(Collectors.toCollection(ArrayList::new));
            for (DtAlbumSimple album : albumesArtista) {
                Map<String, String> albumInfo = new HashMap<>();
                albumInfo.put("id", album.getIdAlbum().toString());  
                albumInfo.put("nombre", album.getNombreAlbum());
                albumesJson.add(albumInfo);
            }
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\": \"Tipo inválido o parámetros faltantes\"}");
            return;
        }

        
        String json = new Gson().toJson(albumesJson);
        try (PrintWriter out = response.getWriter()) {
            out.write(json);
        }
    }
    @Override
    public String getServletInfo() {
        return "Servlet carga albumes";
    }// </editor-fold>

}
