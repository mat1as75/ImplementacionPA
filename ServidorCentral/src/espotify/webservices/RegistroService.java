package espotify.webservices;

import espotify.config.ConfigReader;
import espotify.logica.Fabrica;
import espotify.logica.IControlador;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.xml.ws.Endpoint;
import java.util.Date;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class RegistroService {
    private final Fabrica fb = Fabrica.getInstance();
    private final IControlador icontrolador = fb.getControlador(); 
    private Endpoint endpoint = null;
    
    public RegistroService() {};
    
    @WebMethod(exclude = true)
    public void publishEndpoint() {
        endpoint = Endpoint.publish(ConfigReader.getWebServiceBaseURL() + "RegistroService", this);
    }
    
    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
        return endpoint;
    }
    
    @WebMethod
    public void guardarRegistroAcceso(String direccionIP, String url, String navegador, String sistemaOperativo, Date fechaAcceso) {
        icontrolador.guardarRegistroAcceso(direccionIP, url, navegador, sistemaOperativo, fechaAcceso);
    }
    
}
