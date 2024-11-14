package webservices;

import espotify.logica.Fabrica;
import espotify.logica.IControlador;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.xml.ws.Endpoint;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class UsuarioService {
    
    private final Fabrica fb = Fabrica.getInstance();
    private final IControlador ictrl = fb.getControlador(); 
    private Endpoint endpoint = null;
    public UsuarioService() {};
    
    @WebMethod(exclude = true)
    public void publishEndpoint() {
        endpoint = Endpoint.publish("http://localhost:8080/webservice", this);
    }
    
    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
        return endpoint;
    }
    
    @WebMethod
    public Boolean existeCliente(String nickname) {
        
        return ictrl.ExisteCliente(nickname);
    }
    
    @WebMethod
    public String hello() {
        return "Hello";
    }
    
    @WebMethod
    public String getPepe() {
        String pepe = ictrl.getDatosUsuario("ppArgento").getNicknameUsuario();
        return pepe;
    }
}
