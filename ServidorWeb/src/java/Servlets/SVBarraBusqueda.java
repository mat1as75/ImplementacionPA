package Servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
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
import webservices.ContenidoService;
import webservices.ContenidoServiceService;
import webservices.DataTypes.DtTemaSimple;
import webservices.DataTypes.DtUsuarioGenerico;
import webservices.MapContainer;
import webservices.UsuarioService;
import webservices.UsuarioServiceService;

/**
 *
 * @author tecnologo
 */
@WebServlet(name = "SVBarraBusqueda", urlPatterns = {"/SVBarraBusqueda"})
public class SVBarraBusqueda extends HttpServlet {

    private EntityManagerFactory emf;
    private EntityManager em;
    
    private UsuarioServiceService serviceU = new UsuarioServiceService();
    private UsuarioService serviceUsuario = serviceU.getUsuarioServicePort();
    
    private ContenidoServiceService serviceC = new ContenidoServiceService();
    private ContenidoService serviceContenido = serviceC.getContenidoServicePort();
    
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
        

        // Opcion del ComboBox Filtro
        String opcionFiltro = request.getParameter("combo");
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
        
        if (opcionFiltro == null) {
            response.sendRedirect("BarraBusqueda.jsp");
        } else {

            switch (opcionFiltro) {
                case "tema" -> resultados.putAll(buscarTemas(consulta));
                case "lista" -> resultados.putAll(buscarListasReproduccion(consulta));
                case "album" -> resultados.putAll(buscarAlbumes(consulta));
                case "usuario" -> resultados.putAll(buscarUsuarios(consulta));
            }

            // Guardo resultados en el request
            request.setAttribute("resultados", resultados);
            request.setAttribute("n_Resultados", resultados.size());

            // Redirigir a la pagina de resultados
            RequestDispatcher dispatcher = request.getRequestDispatcher("resultadosBusqueda.jsp");
            dispatcher.forward(request, response); 
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    // Buscar Usuarios
    private Map<String, String> buscarUsuarios(String query) {
        
        em = emf.createEntityManager();
        Map<String, String> results = new HashMap<>();
        
        try {
            String jpqlUsuarios = "SELECT u.nickname FROM Usuario u WHERE u.nickname LIKE :query";
            TypedQuery<String> consultaUsuarios = em.createQuery(jpqlUsuarios, String.class);
            consultaUsuarios.setParameter("query", "%" + query + "%");
            ArrayList<String> nicknamesClientes = (ArrayList<String>) serviceUsuario.getNicknamesClientes().getColeccion().stream()
                    .map(String::valueOf)
                    .collect(Collectors.toList());
            
            for (String nickname : consultaUsuarios.getResultList()) {
                DtUsuarioGenerico datosU = serviceUsuario.getDatosUsuario(nickname).getDtUsuarioGenerico();
                String tipoUsuario = serviceUsuario.getTipoUsuario(nickname);
                
                /* Si es Cliente || es Artista y esActivo */
                if (tipoUsuario.equals("Cliente") || 
                        tipoUsuario.equals("Artista") && datosU.isActivo()) {
                    if (nicknamesClientes.contains(nickname))
                        results.put(nickname, "Cliente");
                    else
                        results.put(nickname, "Artista");
                }
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
        
        em = emf.createEntityManager();
        Map<String, String> results = new HashMap<>();
        try { 
            // Buscar Temas
            String jpqlTemas = "SELECT t.idTema FROM Tema t WHERE t.nombreTema LIKE :query";
            TypedQuery<Long> consultaTemas = em.createQuery(jpqlTemas, Long.class);
            consultaTemas.setParameter("query", "%" + query + "%");
            
            for (Long result : consultaTemas.getResultList()) {
                // Obtengo el MapContainer y lo convierto a Map<Long, DtTemaSimple> con una operacion
                Map<Long, DtTemaSimple> mapDtTemas = convertirAMap(serviceContenido.getDTTemasDisponiblesConAlbum());
                for (Map.Entry<Long, DtTemaSimple> entrada : mapDtTemas.entrySet()) {
                    // idTema del Map (mapDtTemas) == idTema del Objeto (results)
                    if ((entrada.getValue().getIdTema()).equals(result)) {
                        String value = String.valueOf(entrada.getValue().getIdAlbum()) + ", " + entrada.getValue().getNombreTema();
                        results.put(value, "Tema");
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
        em = emf.createEntityManager();
        Map<String, String> results = new HashMap<>();
        
        try {
            String jpqlAlbumes = "SELECT a.idAlbum, a.nombreAlbum FROM Album a WHERE a.nombreAlbum LIKE :query";
            TypedQuery<Object[]> consultaAlbumes = em.createQuery(jpqlAlbumes, Object[].class);
            consultaAlbumes.setParameter("query", "%" + query + "%");
            
            for (Object[] result : consultaAlbumes.getResultList()) {
                Long idAlbum = (Long) result[0];
                String nombreAlbum = (String) result[1];
                
                // Concatenar idAlbum y nombreAlbum
                String value = idAlbum + ", " + nombreAlbum;
                results.put(value, "Album");
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
            String jpqlListasR = "SELECT l.nombreLista FROM ListaReproduccion l WHERE l.nombreLista LIKE :query";
            TypedQuery<String> consultaListasR = em.createQuery(jpqlListasR, String.class);
            consultaListasR.setParameter("query", "%" + query + "%");
            for (String nombreListaR : consultaListasR.getResultList()) {
                results.put(nombreListaR, "Lista");
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
    
    // Convertir MapContainer en Map<Long, DtTemaSimple>
    private Map<Long, DtTemaSimple> convertirAMap(MapContainer mapContainer) {
        Map<Long, DtTemaSimple> resultMap = new HashMap<>();
        
        // Obtener la lista de entradas del mapLongDatatype
        if (mapContainer.getMapLongDatatype() != null) {
            for (MapContainer.MapLongDatatype.Entry entry : mapContainer.getMapLongDatatype().getEntry()) {
                resultMap.put(entry.getKey(), entry.getValue());
            }
        }
        
        return resultMap;
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
