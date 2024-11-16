package webservices;

 
import espotify.DataTypes.DTDatosListaReproduccion;
import espotify.DataTypes.DTTemaSimple;
import espotify.logica.Fabrica;
import espotify.logica.IControlador;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.xml.ws.Endpoint;
import jakarta.xml.ws.WebServiceException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import webservices.DtDatosListaReproduccion;
import webservices.DtTemaSimple;
@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class ListasReproduccionService {
private final Fabrica fb = Fabrica.getInstance();
    private final IControlador ictrl = fb.getControlador(); 
    private Endpoint endpoint = null;
     public ListasReproduccionService() {};
    
    @WebMethod(exclude = true)
    public void publishEndpoint() {
        endpoint = Endpoint.publish("http://localhost:8089/webserviceListasReproduccion", this);
    }
    
    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
        return endpoint;
    }
    
    @WebMethod
    public void CrearListaParticular(String nombreLista, String fotoLista, String nicknameCliente, boolean esPrivada) {
        ictrl.CrearListaParticular(nombreLista, fotoLista, nicknameCliente, esPrivada);
    }
  
    @WebMethod
    public void CrearListaParticularConFecha(String nombreLista, String fotoLista, String nicknameCliente, String fechaCreacion, boolean esPrivada) {
        Date FechaCreacion=new Date(fechaCreacion);
        ictrl.CrearListaParticular(nombreLista, fotoLista, nicknameCliente,FechaCreacion, esPrivada);
    }
    @WebMethod
    public MyArrayList getListasReproduccionDisponibles() {
        MyArrayList a = new MyArrayList();
        List<String> internalList = ictrl.getListasReproduccionDisponibles();
        // Convertir List<String> a List<Object>
        List<Object> objectList = new ArrayList<>(internalList);
        a.setInternalList(objectList);
        return a;
    }



    @WebMethod
    public MyArrayList getNombresListasParticularesPublicas() {
        MyArrayList a = new MyArrayList();
        List<String> internalList = ictrl.getNombresListasParticularesPublicas();
        // Convertir List<String> a List<Object>
        List<Object> objectList = new ArrayList<>(internalList);
        a.setInternalList(objectList);
        return a;
    }
    @WebMethod
    public MyArrayList getNombresListasPorDefecto() {
        MyArrayList a = new MyArrayList();
        List<String> internalList = ictrl.getNombresListasPorDefecto();
        // Convertir List<String> a List<Object>
        List<Object> objectList = new ArrayList<>(internalList);
        a.setInternalList(objectList);
        return a;
    }
    @WebMethod
     public MyArrayList getNombresListasParticulares() {
        MyArrayList a = new MyArrayList();
        List<String> internalList = ictrl.getNombresListasParticulares();
        // Convertir List<String> a List<Object>
        List<Object> objectList = new ArrayList<>(internalList);
        a.setInternalList(objectList);
        return a;
    
    }
   @WebMethod
    public MyArrayList getListaDTDatosListaReproduccionDeCliente(String nicknameCliente) {
       /* try {
            MyArrayList a = new MyArrayList();
            List<DTDatosListaReproduccion> internalList = this.ictrl.getListaDTDatosListaReproduccionDeCliente(nicknameCliente);
            // Convertir List<String> a List<Object>
            List<Object> objectList = new ArrayList<>(internalList);
            a.setInternalList(objectList);
            return a;

        } catch (Exception e) {
            e.printStackTrace();
            throw new WebServiceException("Error al obtener la lista de reproducción del cliente: " + e.getMessage(), e);
        }*/
       return null;
    }

    @WebMethod
    public DtDatosListaReproduccion ConsultarListaReproduccion(String tipoDeLista, String nombreLista) {
         DtDatosListaReproduccion retorno=new DtDatosListaReproduccion();
         DTDatosListaReproduccion  dt= ictrl.ConsultarListaReproduccion(tipoDeLista, nombreLista);
         retorno.setCliente(dt.getCliente());
         retorno.setFotoLista(dt.getFotoLista());
         retorno.setGenero(dt.getGenero());
         retorno.setNombreLista(dt.getNombreLista());
         retorno.setPrivacidad(dt.getPrivacidad());
         retorno.setTipoDeLista(dt.getTipoDeLista());
         List<DTTemaSimple> temas=dt.getTemas();
         List<DtTemaSimple> lostemas=new ArrayList<DtTemaSimple>();
         for(DTTemaSimple dts:temas){
             DtTemaSimple ts=new DtTemaSimple();
             ts.setDuracionSegundos(dts.getDuracionSegundos());
             ts.setIdAlbum(dts.getIdAlbum());
             ts.setIdTema(dts.getIdTema());
             ts.setNombreAlbum(dts.getNombreAlbum());
             ts.setNombreCompletoArtista(dts.getNombreCompletoArtista());
             ts.setNombreTema(dts.getNombreTema());
             ts.setPosicionEnAlbum(dts.getPosicionEnAlbum());
             lostemas.add(ts);
         }
         retorno.setTemas(lostemas);
         return retorno;
    }
    @WebMethod
    public MyArrayList listasCreadasEstadoPrivadoTrue(String cliente){
         MyArrayList a = new MyArrayList();
        List<String> internalList = ictrl.listasCreadasEstadoPrivadoTrue(cliente);
        // Convertir List<String> a List<Object>
        List<Object> objectList = new ArrayList<>(internalList);
        a.setInternalList(objectList);
        return a;
    }
}