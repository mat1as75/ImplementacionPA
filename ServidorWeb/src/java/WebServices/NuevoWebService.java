package WebServices;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.xml.ws.Action;


@WebService(name = "NuevoWebService", targetNamespace = "http://webservices/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface NuevoWebService {

    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webservices/NuevoWebService/existeClienteRequest", output = "http://webservices/NuevoWebService/existeClienteResponse")
    public boolean existeCliente(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webservices/NuevoWebService/helloRequest", output = "http://webservices/NuevoWebService/helloResponse")
    public String hello();

    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webservices/NuevoWebService/getPepeRequest", output = "http://webservices/NuevoWebService/getPepeResponse")
    public String getPepe();

}