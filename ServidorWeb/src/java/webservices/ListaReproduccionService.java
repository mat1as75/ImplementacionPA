package webservices;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import javax.xml.datatype.XMLGregorianCalendar;
import jakarta.xml.ws.Action;
import jakarta.xml.ws.FaultAction;
import webservices.DataTypes.DtDatosListaReproduccion;

@WebService(name = "ListaReproduccionService", targetNamespace = "http://webservices.espotify/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface ListaReproduccionService {


    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns espotify.webservices.DtDatosListaReproduccion
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webservices.espotify/ListaReproduccionService/getDTDatosListaReproduccionRequest", output = "http://webservices.espotify/ListaReproduccionService/getDTDatosListaReproduccionResponse")
    public DtDatosListaReproduccion getDTDatosListaReproduccion(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1);

    /**
     * 
     * @return
     *     returns espotify.webservices.ArrayListContainer
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webservices.espotify/ListaReproduccionService/getNombresListasParticularesRequest", output = "http://webservices.espotify/ListaReproduccionService/getNombresListasParticularesResponse")
    public ArrayListContainer getNombresListasParticulares();

    /**
     * 
     * @param arg0
     * @return
     *     returns espotify.webservices.ArrayListContainer
     * @throws Exception_Exception
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webservices.espotify/ListaReproduccionService/getListaDTDatosListaReproduccionDeClienteRequest", output = "http://webservices.espotify/ListaReproduccionService/getListaDTDatosListaReproduccionDeClienteResponse", fault = {
        @FaultAction(className = Exception_Exception.class, value = "http://webservices.espotify/ListaReproduccionService/getListaDTDatosListaReproduccionDeCliente/Fault/Exception")
    })
    public ArrayListContainer getListaDTDatosListaReproduccionDeCliente(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0)
        throws Exception_Exception
    ;

    /**
     * 
     * @param arg0
     * @return
     *     returns espotify.webservices.ArrayListContainer
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webservices.espotify/ListaReproduccionService/getNombresDeListasPrivadasDeClienteRequest", output = "http://webservices.espotify/ListaReproduccionService/getNombresDeListasPrivadasDeClienteResponse")
    public ArrayListContainer getNombresDeListasPrivadasDeCliente(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg3
     * @param arg2
     * @param arg4
     * @param arg1
     * @param arg0
     */
    @WebMethod(operationName = "CrearListaParticular")
    @Action(input = "http://webservices.espotify/ListaReproduccionService/CrearListaParticularRequest", output = "http://webservices.espotify/ListaReproduccionService/CrearListaParticularResponse")
    public void crearListaParticular(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1,
        @WebParam(name = "arg2", partName = "arg2")
        String arg2,
        @WebParam(name = "arg3", partName = "arg3")
        XMLGregorianCalendar arg3,
        @WebParam(name = "arg4", partName = "arg4")
        boolean arg4);

    /**
     * 
     * @return
     *     returns espotify.webservices.ArrayListContainer
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webservices.espotify/ListaReproduccionService/getNombresListasPorDefectoRequest", output = "http://webservices.espotify/ListaReproduccionService/getNombresListasPorDefectoResponse")
    public ArrayListContainer getNombresListasPorDefecto();

    /**
     * 
     * @return
     *     returns espotify.webservices.ArrayListContainer
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webservices.espotify/ListaReproduccionService/getNombresListasParticularesPublicasRequest", output = "http://webservices.espotify/ListaReproduccionService/getNombresListasParticularesPublicasResponse")
    public ArrayListContainer getNombresListasParticularesPublicas();

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns espotify.webservices.NullableContainer
     */
    @WebMethod(operationName = "ConsultarListaReproduccion")
    @WebResult(partName = "return")
    @Action(input = "http://webservices.espotify/ListaReproduccionService/ConsultarListaReproduccionRequest", output = "http://webservices.espotify/ListaReproduccionService/ConsultarListaReproduccionResponse")
    public NullableContainer consultarListaReproduccion(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1);

    /**
     * 
     * @return
     *     returns espotify.webservices.ArrayListContainer
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webservices.espotify/ListaReproduccionService/getListasReproduccionDisponiblesRequest", output = "http://webservices.espotify/ListaReproduccionService/getListasReproduccionDisponiblesResponse")
    public ArrayListContainer getListasReproduccionDisponibles();

}
