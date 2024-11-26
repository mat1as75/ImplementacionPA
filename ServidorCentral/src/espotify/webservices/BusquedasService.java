package espotify.webservices;

import espotify.config.ConfigReader;
import espotify.logica.Fabrica;
import espotify.logica.IControlador;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.xml.ws.Endpoint;
import java.util.HashMap;
import java.util.Map.Entry;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public class BusquedasService {
    
    private final Fabrica fb = Fabrica.getInstance();
    private final IControlador ictrl = fb.getControlador(); 
    private Endpoint endpoint = null;
    
    public BusquedasService() {};
    
    @WebMethod(exclude = true)
    public void publishEndpoint() {
        endpoint = Endpoint.publish(ConfigReader.getWebServiceBaseURL() + "BusquedasService", this);
    }
    
    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
        return endpoint;
    }
    
    @WebMethod
    public MapContainer buscarUsuariosPorQuery(String query) {
        MapContainer contenedor = new MapContainer();
        HashMap<String, String> usuarios = new HashMap(ictrl.buscarUsuariosPorQuery(query));
        
        contenedor.setMapStringString(usuarios);
        
        for (Entry e : contenedor.getMapStringString().entrySet()) {
            System.out.println(e.getKey() + " - " + e.getValue());
        }
        
        return contenedor;
    }
    
    @WebMethod
    public MapContainer buscarTemasPorQuery(String query) {
        MapContainer contenedor = new MapContainer();
        HashMap<String, String> temas = new HashMap(ictrl.buscarTemasPorQuery(query));
       
        contenedor.setMapStringString(temas);
        
            for (Entry e : contenedor.getMapStringString().entrySet()) {
            System.out.println(e.getKey() + " - " + e.getValue());
        }
        
        return contenedor;
    }
    
    @WebMethod
    public MapContainer buscarListasReproduccionPorQuery(String query) {
        MapContainer contenedor = new MapContainer();
        HashMap<String, String> listasRep = new HashMap(ictrl.buscarListasReproduccionPorQuery(query));
       
        contenedor.setMapStringString(listasRep);
            for (Entry e : contenedor.getMapStringString().entrySet()) {
            System.out.println(e.getKey() + " - " + e.getValue());
        }
        
        return contenedor;
    }
    
    @WebMethod
    public MapContainer buscarAlbumsPorQuery(String query) {
        MapContainer contenedor = new MapContainer();
        HashMap<String, String> albums = new HashMap(ictrl.buscarAlbumsPorQuery(query));
       
        contenedor.setMapStringString(albums);
            for (Entry e : contenedor.getMapStringString().entrySet()) {
            System.out.println(e.getKey() + " - " + e.getValue());
        }
        
        return contenedor;
    }
}
