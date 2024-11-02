package Servlets;

import espotify.DataTypes.DTTemaSimple;
import espotify.logica.Fabrica;
import espotify.logica.IControlador;
import java.io.IOException;
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
import javax.servlet.http.HttpSession;

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
    
    /*
            ~ COSAS QUE MODIFICAR ~
        
        - Agregar boton a la barra de busqueda para filtrar por Tema, Album, Lista o Usuario. 
        - Dividir en 4 operaciones los Buscar y llamarlos en el doGet dependiendo del parametro
            que me llegue desde el filtro creado en el punto anterior. (HECHO)
        - Trabajar los Arrays con JSON unicos dependiendo del filtro.
        - Implementar funciones JavaScript de ordenamiento por alfabeto y por anio.
    */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession sesion = request.getSession(false);
        
        if (sesion != null) {
          String rolSesion = (String) sesion.getAttribute("rol");
            if (rolSesion != null && rolSesion.equals("Artista")) {
                SVError.redirectUnauthorized(request, response);
                return;
            }
        }
      
        // Consulta
        String consulta = request.getParameter("consulta");
        
        // Mapa para almacenar los resultados
        Map<String, String> resultados = new HashMap<>();
        
        // Buscar Usuarios
        resultados.putAll(buscarUsuarios(consulta));
        
        // Buscar Temas
        resultados.putAll(buscarTemas(consulta));
        
        // Buscar Albumes
        resultados.putAll(buscarAlbumes(consulta));
        
        // Buscar Listas
        resultados.putAll(buscarListasReproduccion(consulta));
        
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
    
    // Funcion para buscar los Usuarios
    private Map<String, String> buscarUsuarios(String query) {
        Fabrica fb = Fabrica.getInstance();
        IControlador control = fb.getControlador();
        EntityManager em = emf.createEntityManager();
        Map<String, String> results = new HashMap<>();
        
        try {
            String jpqlUsuarios = "SELECT u.nickname FROM Usuario u WHERE u.nickname LIKE :query AND LENGTH(u.nickname) BETWEEN LENGTH(:query) - 4 AND LENGTH(:query) + 4";
            TypedQuery<String> consultaUsuarios = em.createQuery(jpqlUsuarios, String.class);
            consultaUsuarios.setParameter("query", "%" + query + "%");
            ArrayList<String> nicknamesClientes = (ArrayList<String>) control.getNicknamesClientes();
            for (String nickname : consultaUsuarios.getResultList()) {
                if (nicknamesClientes.contains(nickname))
                    results.put("Cliente", nickname);
                else
                    results.put("Artista", nickname);
            }
        } catch(Exception ex) {
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
        
        return results;
    }
    
    // Buscar Temas
    private Map<String, String> buscarTemas(String query) {
        Fabrica fb = Fabrica.getInstance();
        IControlador control = fb.getControlador();
        EntityManager em = emf.createEntityManager();
        Map<String, String> results = new HashMap<>();
        try { 
            // Buscar Temas
            String jpqlTemas = "SELECT t.idTema FROM Tema t WHERE t.nombreTema LIKE :query AND LENGTH(t.nombreTema) BETWEEN LENGTH(:query) - 4 AND LENGTH(:query) + 4";
            TypedQuery<Long> consultaTemas = em.createQuery(jpqlTemas, Long.class);
            consultaTemas.setParameter("query", "%" + query + "%");
            
            for (Long result : consultaTemas.getResultList()) {
                Map<Long, DTTemaSimple> mapDtTemas = control.getDTTemasDisponiblesConAlbum();
                
                for (Map.Entry<Long, DTTemaSimple> entrada : mapDtTemas.entrySet()) {
                    // idTema del Map (mapDtTemas) == idTema del Objeto (results)
                    if ((entrada.getValue().getIdTema()).equals(result)) {
                        String value = String.valueOf(entrada.getValue().getIdAlbum()) + ", " + entrada.getValue().getNombreTema();
                        results.put("Tema", value);
                    }
                }
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
        
        return results;
    }
    
    // Buscar Albumes
    private Map<String, String> buscarAlbumes(String query) {
        EntityManager em = emf.createEntityManager();
        Map<String, String> results = new HashMap<>();
        
        try {
            String jpqlAlbumes = "SELECT a.idAlbum, a.nombreAlbum FROM Album a WHERE a.nombreAlbum LIKE :query AND LENGTH(a.nombreAlbum) BETWEEN LENGTH(:query) - 4 AND LENGTH(:query) + 4";
            TypedQuery<Object[]> consultaAlbumes = em.createQuery(jpqlAlbumes, Object[].class);
            consultaAlbumes.setParameter("query", "%" + query + "%");
            
            for (Object[] result : consultaAlbumes.getResultList()) {
                Long idAlbum = (Long) result[0];
                String nombreAlbum = (String) result[1];
                
                // Concatenar idAlbum y nombreAlbum
                String value = idAlbum + ", " + nombreAlbum;
                results.put("Album", value);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
        
        return results;
    }
    
    // Buscar ListasReproduccion
    private Map<String, String> buscarListasReproduccion(String query) {
        EntityManager em = emf.createEntityManager();
        Map<String, String> results = new HashMap<>();
        
        try {
            String jpqlListasR = "SELECT l.nombreLista FROM ListaReproduccion l WHERE l.nombreLista LIKE :query AND LENGTH(l.nombreLista) BETWEEN LENGTH(:query) - 4 AND LENGTH(:query) + 4";
            TypedQuery<String> consultaListasR = em.createQuery(jpqlListasR, String.class);
            consultaListasR.setParameter("query", "%" + query + "%");
            for (String nombreListaR : consultaListasR.getResultList()) {
                results.put("Lista", nombreListaR);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
        
        return results;
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
