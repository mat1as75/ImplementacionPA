package espotify.webservices;

import espotify.DataTypes.DTSuscripcion;
import espotify.config.ConfigReader;
import espotify.logica.Fabrica;
import espotify.logica.IControlador;
import espotify.logica.Suscripcion;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.xml.ws.Endpoint;
import java.util.Date;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class SuscripcionesService {
    private final Fabrica fb = Fabrica.getInstance();
    private final IControlador ictrl = fb.getControlador(); 
    private Endpoint endpoint = null;
    
    public SuscripcionesService() {};
    
    @WebMethod(exclude = true)
    public void publishEndpoint() {
        endpoint = Endpoint.publish(ConfigReader.getWebServiceBaseURL() + "SuscripcionesService", this);
    }
    
    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
        return endpoint;
    }
    
    @WebMethod
    public NullableContainer getDTSuscripcionDeCliente(String nickname) throws Exception {
        NullableContainer contenedor = new NullableContainer();
        
        try {
            DTSuscripcion dtSuscripcion = ictrl.getDTSuscripcionDeCliente(nickname);
            contenedor.setDtSuscripcion(dtSuscripcion);
        } catch (Exception e) {
            throw e;
        }
        return contenedor;
    }
    
    @WebMethod
    public void ingresarNuevaSuscripcion(String nickname, Suscripcion.TipoSuscripcion tipoSuscripcion) throws Exception {
        try {
            ictrl.ingresarNuevaSuscripcion(nickname, tipoSuscripcion);
        } catch (Exception e) {
            throw e;
        }
    }
    
    @WebMethod
    public Boolean actualizarSuscripcionVencida(Long idSuscripcion) {
        return ictrl.actualizarSuscripcionVencida(idSuscripcion);
    }

    @WebMethod
    public void ActualizarEstadoSuscripcion(Long idSuscripcion, Suscripcion.EstadoSuscripcion estadoSuscripcion, Date fechaSuscripcion) {
        ictrl.ActualizarEstadoSuscripcion(idSuscripcion, estadoSuscripcion, fechaSuscripcion);
    }

    @WebMethod
    public NullableContainer getDTSuscripcon(Long idSuscripcion) {
        NullableContainer contenedor = new NullableContainer();
        DTSuscripcion dtSuscripcion = ictrl.getDTSuscripcion(idSuscripcion);
        contenedor.setDtSuscripcion(dtSuscripcion);
        
        return contenedor;
    }
    
}
