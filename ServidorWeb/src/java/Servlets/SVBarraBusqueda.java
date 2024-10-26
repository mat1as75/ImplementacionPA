/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import espotify.logica.Fabrica;
import espotify.logica.IControlador;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author tecnologo
 */
@WebServlet(name = "SVBarraBusqueda", urlPatterns = {"/SVBarraBusqueda"})
public class SVBarraBusqueda extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Fabrica fb = Fabrica.getInstance();
        IControlador control = fb.getControlador();
        String consulta = request.getParameter("consulta");
        
        /* Podria guardar el ID (si encuentra) para poder obtener 
            el Entity desde TemaJpaController */
        Map<Long, String> mapTemas = control.getTemasDisponibles();
        boolean encontroTema = false;
        for (Map.Entry<Long, String> entrada : mapTemas.entrySet()) {
            if (entrada.getValue().equals(consulta))
                encontroTema = true;
        }
        
        /* Podria guardar el ID (si encuentra) para poder obtener 
            el Entity desde AlbumJpaController */
        Map<Long, String> mapAlbumes = control.getAlbumesDisponibles();
        boolean encontroAlbum = false;
        for (Map.Entry<Long, String> entrada : mapAlbumes.entrySet()) {
            if (entrada.getValue().equals(consulta))
                encontroAlbum = true;
        }
        
        /* Podria guardar el ID (si encuentra) para poder obtener 
            el Entity desde ListaReproduccionJpaController */
        ArrayList<String> listasR = control.getListasReproduccionDisponibles();
        boolean encontroListaR = false;
        for (String nombreListaR : listasR) {
            if (nombreListaR.equals(consulta))
                encontroListaR = true;
        }
        
        /* Podria guardar el ID (si encuentra) para poder obtener 
            el Entity desde UsuarioJpaController */
        List<String> usuarios = control.getNicknamesClientes();
        List<String> artistas = control.getNicknamesArtistas();
        usuarios.addAll(artistas);
        boolean encontroUsuario = false;
        for (String nickname : usuarios) {
            if (nickname.equals(consulta))
                encontroUsuario = true;
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
