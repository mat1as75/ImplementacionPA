package espotify.webservices;

import espotify.DataTypes.DTSuscripcion;
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
        endpoint = Endpoint.publish("http://localhost:8089/SuscripcionesService", this);
    }
    
    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
        return endpoint;
    }
    
    @WebMethod
    public DTSuscripcion getDTSuscripcionDeCliente(String nickname) throws Exception {
        try {
            return ictrl.getDTSuscripcionDeCliente(nickname);
        } catch (Exception e) {
            throw e;
        }
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
    public DTSuscripcion getDTSuscripcon(Long idSuscripcion) {
        return ictrl.getDTSuscripcion(idSuscripcion);
    }
    
}
