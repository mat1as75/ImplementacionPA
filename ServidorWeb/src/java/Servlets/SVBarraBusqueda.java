package Servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import webservices.BusquedasService;
import webservices.BusquedasServiceService;
import webservices.ContenidoService;
import webservices.ContenidoServiceService;
import webservices.MapContainer;
import webservices.UsuarioService;
import webservices.UsuarioServiceService;

/**
 *
 * @author tecnologo
 */
@WebServlet(name = "SVBarraBusqueda", urlPatterns = {"/SVBarraBusqueda"})
public class SVBarraBusqueda extends HttpServlet {

    private UsuarioServiceService serviceU = new UsuarioServiceService();
    private UsuarioService serviceUsuario = serviceU.getUsuarioServicePort();
    private BusquedasServiceService busquedasWS = new BusquedasServiceService();
    private BusquedasService serviceBusquedas = busquedasWS.getBusquedasServicePort();
    
    private ContenidoServiceService serviceC = new ContenidoServiceService();
    private ContenidoService serviceContenido = serviceC.getContenidoServicePort();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
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
        
        MapContainer.MapStringString resultado = serviceBusquedas.buscarUsuariosPorQuery(query).getMapStringString();
        Map<String, String> mapUsuarios = new HashMap();
        
        for (MapContainer.MapStringString.Entry entry : resultado.getEntry()) {
            mapUsuarios.put(entry.getKey(), entry.getValue());
        }
        
        return mapUsuarios;
    }
    
    // Buscar Temas
    private Map<String, String> buscarTemas(String query) {
       
        MapContainer.MapStringString resultado = serviceBusquedas.buscarTemasPorQuery(query).getMapStringString();
        Map<String, String> mapTemas = new HashMap();
        
        for (MapContainer.MapStringString.Entry entry : resultado.getEntry()) {
            mapTemas.put(entry.getKey(), entry.getValue());
        }
        
        return mapTemas;
    }
    
    // Buscar Albumes
    private Map<String, String> buscarAlbumes(String query) {
        
        MapContainer.MapStringString resultado = serviceBusquedas.buscarAlbumsPorQuery(query).getMapStringString();
        Map<String, String> mapAlbums = new HashMap();
        
        for (MapContainer.MapStringString.Entry entry : resultado.getEntry()) {
            mapAlbums.put(entry.getKey(), entry.getValue());
        }
        
        return mapAlbums;
    }
    
    // Buscar ListasReproduccion
    private Map<String, String> buscarListasReproduccion(String query) {
         
        MapContainer.MapStringString resultado = serviceBusquedas.buscarListasReproduccionPorQuery(query).getMapStringString();
        Map<String, String> mapListasRep = new HashMap();
        
        for (MapContainer.MapStringString.Entry entry : resultado.getEntry()) {
            mapListasRep.put(entry.getKey(), entry.getValue());
        }
        
        return mapListasRep;
    }

}
