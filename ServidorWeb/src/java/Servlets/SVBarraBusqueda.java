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
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
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

    private EntityManagerFactory emf;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    public void init() {
        emf = Persistence.createEntityManagerFactory("EspotifyPU");
    }
    
    @Override
    public void destroy() {
        // Cierra el EntityManagerFactory
        if (emf != null) {
            emf.close();
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Fabrica fb = Fabrica.getInstance();
        IControlador control = fb.getControlador();
        EntityManager em = emf.createEntityManager();
        
        // Consulta
        String consulta = request.getParameter("consulta");
        
        // Mapa para almacenar los resultados
        Map<String, String> resultados = new HashMap<>();
        
        // Nicknames de Clientes en el Sistema
        ArrayList<String> nicknamesClientes = (ArrayList<String>) control.getNicknamesClientes();
        
        try {
            
            // Buscar Usuarios
            String jpqlUsuarios = "SELECT u.nickname FROM Usuario u WHERE u.nickname LIKE :query AND LENGTH(u.nickname) BETWEEN LENGTH(:query) - 4 AND LENGTH(:query) + 4";
            TypedQuery<String> consultaUsuarios = em.createQuery(jpqlUsuarios, String.class);
            consultaUsuarios.setParameter("query", "%" + consulta + "%");
            for (String nickname : consultaUsuarios.getResultList()) {
                if (nicknamesClientes.contains(nickname))
                    resultados.put("Cliente", nickname);
                else
                    resultados.put("Artista", nickname);
            }
            
            // Buscar Temas
            String jpqlTemas = "SELECT t.idTema, t.nombreTema FROM Tema t WHERE t.nombreTema LIKE :query AND LENGTH(t.nombreTema) BETWEEN LENGTH(:query) - 4 AND LENGTH(:query) + 4";
            TypedQuery<Object[]> consultaTemas = em.createQuery(jpqlTemas, Object[].class);
            consultaTemas.setParameter("query", "%" + consulta + "%");
            
            for (Object[] result : consultaTemas.getResultList()) {
                Long idTema = (Long) result[0];
                String nombreTema = (String) result[1];
                
                // Concatenar idTema y nombreTema
                String value = idTema + ", " + nombreTema;
                resultados.put("Tema", value);
            }
            
            // Buscar Albumes
            String jpqlAlbumes = "SELECT a.idAlbum, a.nombreAlbum FROM Album a WHERE a.nombreAlbum LIKE :query AND LENGTH(a.nombreAlbum) BETWEEN LENGTH(:query) - 4 AND LENGTH(:query) + 4";
            TypedQuery<Object[]> consultaAlbumes = em.createQuery(jpqlAlbumes, Object[].class);
            consultaAlbumes.setParameter("query", "%" + consulta + "%");
            
            for (Object[] result : consultaAlbumes.getResultList()) {
                Long idAlbum = (Long) result[0];
                String nombreAlbum = (String) result[1];
                
                // Concatenar idAlbum y nombreAlbum
                String value = idAlbum + ", " + nombreAlbum;
                resultados.put("Album", value);
            }
            
            // Buscar ListaReproduccion
            String jpqlListasR = "SELECT l.nombreLista FROM ListaReproduccion l WHERE l.nombreLista LIKE :query AND LENGTH(l.nombreLista) BETWEEN LENGTH(:query) - 4 AND LENGTH(:query) + 4";
            TypedQuery<String> consultaListasR = em.createQuery(jpqlListasR, String.class);
            consultaListasR.setParameter("query", "%" + consulta + "%");
            for (String nombreListaR : consultaListasR.getResultList()) {
                resultados.put("Lista", nombreListaR);
            }
            
            // Buscar por Genero
            // - Tema
            String jpqlTemaGenero = "SELECT t.idTema, t.nombreTema FROM "
                    + "(((ALBUM a, TEMA t), ALBUM_GENERO ag), GENERO g) WHERE "
                    + "a.idAlbum = t.miAlbum_IDALBUM AND "
                    + "a.idAlbum = ag.misAlbumes_IDALBUM AND ag.misGeneros_NOMBREGENERO = g.nombreGenero AND "
                    + "g.nombreGenero LIKE :query";
            TypedQuery<Object[]> consultaTemaGenero = em.createQuery(jpqlTemaGenero, Object[].class);
            consultaTemaGenero.setParameter("query", "%" + consulta + "%");
            for (Object[] result : consultaTemaGenero.getResultList()) {
                Long idTema = (Long) result[0];
                String nombreTema = (String) result[1];
                
                // Concatenar idTema y nombreTema
                String value = idTema + " " + nombreTema;
                resultados.put("Tema", value);
            }
            
            // - Album
            String jpqlAlbumGenero = "SELECT t.idTema, t.nombreTema FROM "
                    + "ALBUM a JOIN TEMA t ON a.idAlbum = t.miAlbum_IDALBUM JOIN "
                    + "ALBUM_GENERO ag ON a.idAlbum = ag.misAlbumes_IDALBUM JOIN "
                    + "GENERO g ON ag.misGeneros_NOMBREGENERO = g.nombreGenero WHERE g.nombreGenero LIKE :query";
            TypedQuery<Object[]> consultaAlbumGenero = em.createQuery(jpqlAlbumGenero, Object[].class);
            consultaAlbumGenero.setParameter("query", "%" + consulta + "%");
            for (Object[] result : consultaAlbumGenero.getResultList()) {
                Long idAlbum = (Long) result[0];
                String nombreAlbum = (String) result[1];
                
                // Concatenar idAlbum y nombreAlbum
                String value = idAlbum + " " + nombreAlbum;
                resultados.put("Album", value);
            }
            
            // - ListaReproduccion
            String jpqlListaRGenero = "SELECT l.NOMBRELISTA FROM "
                    + "LISTAPORDEFECTO l, GENERO g WHERE l.MIGENERO_NOMBREGENERO = g.NOMBREGENERO AND "
                    + "g.NOMBREGENERO LIKE :query";
            TypedQuery<String> consultaListasRGenero = em.createQuery(jpqlListaRGenero, String.class);
            consultaListasRGenero.setParameter("query", "%" + consulta + "%");
            for (String nombreListaR : consultaListasRGenero.getResultList()) {
                resultados.put("Lista", nombreListaR);
            }
            
        
        } catch (Exception e) {
            
            throw e;
        } finally {
            
            if (em != null) {
                em.close();
            }
        }
        
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
//        Map<String, String> resultados = buscarResultados(consulta);
        
        // Guardo resultados en el request
        request.setAttribute("resultados", resultados);
        request.setAttribute("n_Resultados", resultados.size());
        
        // Redirigir a la pagina de resultados
        RequestDispatcher dispatcher = request.getRequestDispatcher("resultadosBusqueda.jsp");
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
