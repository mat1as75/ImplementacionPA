package espotify.webservices;

import espotify.DataTypes.DTDatosListaReproduccion;
import espotify.logica.Fabrica;
import espotify.logica.IControlador;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.xml.ws.Endpoint;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public class ListaReproduccionService {
    
    private final Fabrica fb = Fabrica.getInstance();
    private final IControlador ictrl = fb.getControlador(); 
    private Endpoint endpoint = null;
    
    public ListaReproduccionService() {};
    
    @WebMethod(exclude = true)
    public void publishEndpoint() {
        endpoint = Endpoint.publish("http://localhost:8089/ListaReproduccionService", this);
    }
    
    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
        return endpoint;
    }
    
    @WebMethod
    public void CrearListaParticular(String nombreLista, String fotoLista, String nicknameCliente, Date fechaCreacion, boolean esPrivada) {
        this.ictrl.CrearListaParticular(nombreLista, fotoLista, nicknameCliente, fechaCreacion, esPrivada);
    }
    
    @WebMethod
    public NullableContainer ConsultarListaReproduccion(String tipoDeLista, String nombreLista) {
        NullableContainer contenedor = new NullableContainer(); 
        //contenedor.setObject(this.ictrl.ConsultarListaReproduccion(tipoDeLista, nombreLista));
        
        return contenedor;
    }
    
    @WebMethod
    public ArrayListContainer getListasReproduccionDisponibles() {
        ArrayList<String> listasRepDisponibles = this.ictrl.getListasReproduccionDisponibles();
        ArrayListContainer contenedor = new ArrayListContainer(listasRepDisponibles);

        return contenedor;
    }
    
    @WebMethod
    public ArrayListContainer getNombresListasPorDefecto() {
        ArrayList<String> listasPorDef = this.ictrl.getNombresListasPorDefecto();
        ArrayListContainer contenedor = new ArrayListContainer(listasPorDef);
        
        return contenedor;
    }
    
    @WebMethod
    public ArrayListContainer getNombresListasParticulares() {
        ArrayList<String> listasPart = this.ictrl.getNombresListasParticulares();
        ArrayListContainer contenedor = new ArrayListContainer(listasPart);
        
        return contenedor;
    }
    
    @WebMethod
    public ArrayListContainer getNombresListasParticularesPublicas() {
        ArrayList<String> listasPartPublicas = this.ictrl.getNombresListasParticularesPublicas();
        ArrayListContainer contenedor = new ArrayListContainer(listasPartPublicas);
        
        return contenedor;
    }
    
    @WebMethod
    public ArrayListContainer getListaDTDatosListaReproduccionDeCliente(String nicknameCliente) throws Exception {
        List<DTDatosListaReproduccion> listDT = new ArrayList();
        try {
            listDT = this.ictrl.getListaDTDatosListaReproduccionDeCliente(nicknameCliente);
        } catch (Exception e) {
            throw e;
        }
        ArrayList<DTDatosListaReproduccion> arrayListDT = new ArrayList(listDT);
        ArrayListContainer contenedor = new ArrayListContainer(arrayListDT);
        
        return contenedor;
    }

    @WebMethod
    public ArrayListContainer getNombresDeListasPrivadasDeCliente(String cliente) {
        ArrayList<String> listasPrivadasDeCliente = this.ictrl.listasCreadasEstadoPrivadoTrue(cliente);
        ArrayListContainer contenedor = new ArrayListContainer(listasPrivadasDeCliente);
        
        return contenedor;
    }
}
