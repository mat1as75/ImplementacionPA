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
import webservices.DataTypes.DtSuscripcion;

@WebService(name = "SuscripcionesService", targetNamespace = "http://webservices.espotify/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface SuscripcionesService {


    /**
     * 
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webservices.espotify/SuscripcionesService/actualizarSuscripcionVencidaRequest", output = "http://webservices.espotify/SuscripcionesService/actualizarSuscripcionVencidaResponse")
    public boolean actualizarSuscripcionVencida(
        @WebParam(name = "arg0", partName = "arg0")
        long arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns espotify.webservices.NullableContainer
     * @throws Exception_Exception
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webservices.espotify/SuscripcionesService/getDTSuscripcionDeClienteRequest", output = "http://webservices.espotify/SuscripcionesService/getDTSuscripcionDeClienteResponse", fault = {
        @FaultAction(className = Exception_Exception.class, value = "http://webservices.espotify/SuscripcionesService/getDTSuscripcionDeCliente/Fault/Exception")
    })
    public NullableContainer getDTSuscripcionDeCliente(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0)
        throws Exception_Exception
    ;

    /**
     * 
     * @param arg1
     * @param arg0
     * @throws Exception_Exception
     */
    @WebMethod
    @Action(input = "http://webservices.espotify/SuscripcionesService/ingresarNuevaSuscripcionRequest", output = "http://webservices.espotify/SuscripcionesService/ingresarNuevaSuscripcionResponse", fault = {
        @FaultAction(className = Exception_Exception.class, value = "http://webservices.espotify/SuscripcionesService/ingresarNuevaSuscripcion/Fault/Exception")
    })
    public void ingresarNuevaSuscripcion(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        TipoSuscripcion arg1)
        throws Exception_Exception
    ;

    /**
     * 
     * @param arg0
     * @return
     *     returns espotify.webservices.NullableContainer
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webservices.espotify/SuscripcionesService/getDTSuscripconRequest", output = "http://webservices.espotify/SuscripcionesService/getDTSuscripconResponse")
    public NullableContainer getDTSuscripcon(
        @WebParam(name = "arg0", partName = "arg0")
        long arg0);

    /**
     * 
     * @param arg2
     * @param arg1
     * @param arg0
     */
    @WebMethod(operationName = "ActualizarEstadoSuscripcion")
    @Action(input = "http://webservices.espotify/SuscripcionesService/ActualizarEstadoSuscripcionRequest", output = "http://webservices.espotify/SuscripcionesService/ActualizarEstadoSuscripcionResponse")
    public void actualizarEstadoSuscripcion(
        @WebParam(name = "arg0", partName = "arg0")
        long arg0,
        @WebParam(name = "arg1", partName = "arg1")
        EstadoSuscripcion arg1,
        @WebParam(name = "arg2", partName = "arg2")
        XMLGregorianCalendar arg2);

}
