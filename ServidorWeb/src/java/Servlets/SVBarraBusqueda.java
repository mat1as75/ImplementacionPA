/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import espotify.DataTypes.DTDatosCliente;
import espotify.DataTypes.DTDatosUsuario;
import espotify.logica.Fabrica;
import espotify.logica.IControlador;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
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
        
//        /* Podria guardar el ID (si encuentra) para poder obtener 
//            el Entity desde TemaJpaController */
//        Map<Long, String> mapTemas = control.getTemasDisponibles();
//        boolean encontroTema = false;
//        for (Map.Entry<Long, String> entrada : mapTemas.entrySet()) {
//            if (entrada.getValue().equals(consulta))
//                encontroTema = true;
//        }
//        
//        /* Podria guardar el ID (si encuentra) para poder obtener 
//            el Entity desde AlbumJpaController */
//        Map<Long, String> mapAlbumes = control.getAlbumesDisponibles();
//        boolean encontroAlbum = false;
//        for (Map.Entry<Long, String> entrada : mapAlbumes.entrySet()) {
//            if (entrada.getValue().equals(consulta))
//                encontroAlbum = true;
//        }
//        
//        /* Podria guardar el ID (si encuentra) para poder obtener 
//            el Entity desde ListaReproduccionJpaController */
//        ArrayList<String> listasR = control.getListasReproduccionDisponibles();
//        boolean encontroListaR = false;
//        for (String nombreListaR : listasR) {
//            if (nombreListaR.equals(consulta))
//                encontroListaR = true;
//        }
//        
//        /* Podria guardar el ID (si encuentra) para poder obtener 
//            el Entity desde UsuarioJpaController */
//        List<String> usuarios = control.getNicknamesClientes();
//        List<String> artistas = control.getNicknamesArtistas();
//        usuarios.addAll(artistas);
//        boolean consultaUsuario = false;
//        for (String nickname : usuarios) {
//            if (nickname.equals(consulta))
//                consultaUsuario = true;
//        }
        
        // Obtengo resultados de la busqueda
        Map<String, String> resultados = buscarResultados(consulta);
        
        // Guardo resultados en el request
        request.setAttribute("resultados", resultados);
        request.setAttribute("n_Resultados", resultados.size());
        
        // Redirigir a la pagina de resultados
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response); 
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    private String normalizar(String input) {
        // Normalizo la cadena y elimino diacriticos
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        // Elimino caracteres diacriticos
        normalized = normalized.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        // Convierto a minusculas
        return normalized.toLowerCase();
    }
    
    private boolean comparar(String a, String b) {
        // Normalizo y elimino tildes
        String normalizedA = normalizar(a);
        String normalizedB = normalizar(b);
        
        return normalizedA.equals(normalizedB);
    }
    
    private Map<String, String> buscarResultados(String consulta) {
        Fabrica fb = Fabrica.getInstance();
        IControlador control = fb.getControlador();
        Map<String, String> resultados = new HashMap<>();
        
        // Busco en Temas
        Map<Long, String> mapTemas = control.getTemasDisponibles();
        for (Map.Entry<Long, String> entrada : mapTemas.entrySet()) {
            if (comparar(entrada.getValue(), consulta))
                resultados.put("Tema", entrada.getValue());
        }
        
        // Busco en Albumes
        Map<Long, String> mapAlbumes = control.getAlbumesDisponibles();
        for (Map.Entry<Long, String> entrada : mapAlbumes.entrySet()) {
            if (comparar(entrada.getValue(), consulta))
                resultados.put("Album", entrada.getValue());
        }
        
        // Busco en ListasReproduccion
        ArrayList<String> listasR = control.getListasReproduccionDisponibles();
        for (String nombreListaR : listasR) {
            if (comparar(nombreListaR, consulta))
                resultados.put("Lista", nombreListaR);
        }
        
        // Busco en Usuarios
        List<String> usuarios = control.getNicknamesClientes();
        List<String> artistas = control.getNicknamesArtistas();
        usuarios.addAll(artistas);
        for (String nickname : usuarios) {
            if (comparar(nickname, consulta))
                resultados.put("Usuario", nickname);
        }
        
        return resultados;
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
