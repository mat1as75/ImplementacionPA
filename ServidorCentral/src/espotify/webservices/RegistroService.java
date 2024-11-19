package espotify.webservices;

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
public class RegistroService {
    private final Fabrica fb = Fabrica.getInstance();
    private final IControlador icontrolador = fb.getControlador(); 
    private Endpoint endpoint = null;
    
    public RegistroService() {};
    
    @WebMethod(exclude = true)
    public void publishEndpoint() {
        endpoint = Endpoint.publish("http://localhost:8089/RegistroService", this);
    }
    
    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
        return endpoint;
    }
    
    /*@WebMethod
    public  guardarRegistroAcceso() {
        return icontrolador.();
    }/*
    
}
