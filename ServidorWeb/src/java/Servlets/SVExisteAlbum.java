/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import espotify.DataTypes.DTDatosUsuario;
import espotify.logica.Fabrica;
import espotify.logica.IControlador;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


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
        
        Fabrica fb = Fabrica.getInstance();
        IControlador ictrl = fb.getControlador();
        
        HttpSession sesion = request.getSession(false);
        if (sesion == null) {
            response.setStatus(response.SC_UNAUTHORIZED);
            response.getWriter().write("Error al obtener la sesión.");
            return;
        }
        
        DTDatosUsuario datosUsuario = (DTDatosUsuario) sesion.getAttribute("usuario");
        if (datosUsuario == null) {
            response.setStatus(response.SC_UNAUTHORIZED);
            response.getWriter().write("Error al obtener la sesión de usuario.");
            return;
        }

        String nickname = datosUsuario.getNicknameUsuario();
        String album;
        
        BufferedReader reader = request.getReader();
        StringBuilder body = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            body.append(line);  
        }
        reader.close();

        album = body.toString();
        
        Long idAlbum = ictrl.buscarAlbumPorNombreYArtista(nickname, album);
        
        response.setStatus(response.SC_OK);
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        if (idAlbum == null) {
            response.getWriter().write("No existe");
        } else {
            response.getWriter().write("Existe");
        }
    }

}
