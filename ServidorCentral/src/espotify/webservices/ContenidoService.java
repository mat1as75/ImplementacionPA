package espotify.webservices;

import espotify.DataTypes.DTAlbum;
import espotify.DataTypes.DTAlbum_SinDTArtista;
import espotify.DataTypes.DTGenero_Simple;
import espotify.DataTypes.DTTemaGenericoConRutaOUrl;
import espotify.DataTypes.DTTemaSimple;
import espotify.logica.Fabrica;
import espotify.logica.IControlador;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.xml.ws.Endpoint;
import java.util.ArrayList;
import java.util.HashMap;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class ContenidoService {
    private final Fabrica fb = Fabrica.getInstance();
    private final IControlador ictrl = fb.getControlador(); 
    private Endpoint endpoint = null;
    
    public ContenidoService() {};
    
    @WebMethod(exclude = true)
    public void publishEndpoint() {
        endpoint = Endpoint.publish("http://localhost:8089/ContenidoService", this);
    }
    
    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
        return endpoint;
    }
    
    @WebMethod
    public void AltaAlbum(DTAlbum_SinDTArtista dataAlbum) throws Exception {
        try {
            this.ictrl.AltaAlbum(dataAlbum);
            System.out.println("ok");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    @WebMethod
    public MapContainer getAlbumesDisponibles() {
        HashMap<Long, String> mapAlbumes = new HashMap(this.ictrl.getAlbumesDisponibles());
        MapContainer contenedor = new MapContainer();
        contenedor.setMapLongString(mapAlbumes);
        
        return contenedor;
    }

    @WebMethod
    public Long buscarAlbumPorNombreYArtista(String nombreArt, String nombreAlb) {
        //retorna 0L si no se encuentra
        return this.ictrl.buscarAlbumPorNombreYArtista(nombreArt, nombreAlb);
    }
    
    @WebMethod
    public NullableContainer ConsultaAlbum(Long idAlbum) {
        NullableContainer contenedor = new NullableContainer();
        DTAlbum dtAlbum = this.ictrl.ConsultaAlbum(idAlbum);
        contenedor.setDtAlbum(dtAlbum);
        
        return contenedor;
    }
    
    @WebMethod
    public void agregarTemaALista(Long idTema, String nombreLista) throws Exception {
        try {
            this.ictrl.agregarTemaALista(idTema, nombreLista);
        } catch (Exception e) {
            throw e;
        }
    }
    
    @WebMethod
    public void quitarTemaDeLista(Long idTema, String nombreLista) throws Exception {
        try {
            this.ictrl.quitarTemaDeLista(idTema, nombreLista);
        } catch (Exception e) {
            throw e;
        }
    }
    
    @WebMethod
    public MapContainer getTemasDisponibles() {
        HashMap<Long, String> mapTemas = new HashMap(this.ictrl.getTemasDisponibles());
        MapContainer contenedor = new MapContainer();
        contenedor.setMapLongString(mapTemas);
        
        return contenedor;
    }
    
    @WebMethod
    public MapContainer getDTTemasDisponiblesConAlbum() {
        HashMap<Long, DTTemaSimple> hashMapTemas = new HashMap(this.ictrl.getDTTemasDisponiblesConAlbum());
        MapContainer contenedor = new MapContainer();
        contenedor.setMapLongDatatype(hashMapTemas);
       
        return contenedor;
    }

    @WebMethod
    public DTTemaGenericoConRutaOUrl getDTTemaGenericoConRutaOUrl(Long idTema) {
        return this.ictrl.getDTTemaGenericoConRutaOUrl(idTema);
    }

    @WebMethod
    public ArrayListContainer getListaDTGeneroSimple() {
        ArrayList<DTGenero_Simple> listaDtGenero = this.ictrl.getListaDTGeneroSimple();
        ArrayListContainer contenedor = new ArrayListContainer(listaDtGenero);
        
        return contenedor;
    }
    
    /**
     * Esta operacion no es necesaria para consumir desde el Web Service Client, 
     * pero aparentemente si el WebService no tiene visibilidad sobre el datatype 
     * en tiempo de ejecucion el proxy no puede procesar la clase, haciendo que
     * la operacion de getListaDTGeneroSimple no devuelva ninguna respuesta al Web Service Client, 
     * por lo que ocurre un error al invocarla. 
     * O al menos eso entendi de la documentacion ¯\_(ツ)_/¯
     * Si encuentran otra forma de resolverlo es todo suyo
     * 
     * https://jakarta.ee/specifications/xml-web-services/3.0/jakarta-xml-ws-spec-3.0#protocolspecificfaults
     * https://javadoc.io/static/com.sun.xml.ws/jaxws-rt/2.2.8/com/sun/xml/ws/client/sei/SEIStub.html
     * https://docs.oracle.com/javase/8/docs/technotes/guides/reflection/proxy.html
     * 
     * Como no es necesaria para el WS Client no esta agregada a las clases del lado del cliente.
     * El mismo problema ocurre con ListaReproduccionService 
     * y por ese motivo cree la operacion getDTDatosListaReproduccion, 
     * aunque esa si tiene algo mas de utilidad.
    */
    @WebMethod
    public DTGenero_Simple getDTGeneroSimple(String nombreGenero) {
        ArrayList<DTGenero_Simple> listaDtGenero = this.ictrl.getListaDTGeneroSimple();
        for (DTGenero_Simple dg : listaDtGenero) {
            if (dg.getNombreGenero().equals(nombreGenero)) {
                return dg;
            }
        }
        return new DTGenero_Simple();
    }
    
    @WebMethod
    public ArrayListContainer getNombresGenerosPadre() {
        ArrayList<String> listaGenPadre = this.ictrl.getNombresGenerosPadre();
        ArrayListContainer contenedor = new ArrayListContainer(listaGenPadre);
        
        return contenedor;
    }
}
