
package webservices;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.ws.Action;
import jakarta.xml.ws.FaultAction;
import webservices.DataTypes.DtArtista;
import webservices.DataTypes.DtCliente;
import webservices.DataTypes.DtDatosUsuario;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.3.0.2
 * Generated source version: 2.2
 * 
 */
@WebService(name = "UsuarioService", targetNamespace = "http://webservices.espotify/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface UsuarioService {


    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns espotify.webservices.NullableContainer
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webservices.espotify/UsuarioService/getUsuarioAutentificadoRequest", output = "http://webservices.espotify/UsuarioService/getUsuarioAutentificadoResponse")
    public NullableContainer getUsuarioAutentificado(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1);

    /**
     * 
     * @param arg0
     * @return
     *     returns espotify.webservices.NullableContainer
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webservices.espotify/UsuarioService/getDatosUsuarioRequest", output = "http://webservices.espotify/UsuarioService/getDatosUsuarioResponse")
    public NullableContainer getDatosUsuario(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg0
     * @throws Exception_Exception
     */
    @WebMethod
    @Action(input = "http://webservices.espotify/UsuarioService/darDeBajaArtistaRequest", output = "http://webservices.espotify/UsuarioService/darDeBajaArtistaResponse", fault = {
        @FaultAction(className = Exception_Exception.class, value = "http://webservices.espotify/UsuarioService/darDeBajaArtista/Fault/Exception")
    })
    public void darDeBajaArtista(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0)
        throws Exception_Exception
    ;

    /**
     * 
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webservices.espotify/UsuarioService/getTipoUsuarioRequest", output = "http://webservices.espotify/UsuarioService/getTipoUsuarioResponse")
    public String getTipoUsuario(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod(operationName = "ExisteArtista")
    @WebResult(partName = "return")
    @Action(input = "http://webservices.espotify/UsuarioService/ExisteArtistaRequest", output = "http://webservices.espotify/UsuarioService/ExisteArtistaResponse")
    public boolean existeArtista(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns espotify.webservices.DtDatosUsuario
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webservices.espotify/UsuarioService/getDataDatosUsuariosRequest", output = "http://webservices.espotify/UsuarioService/getDataDatosUsuariosResponse")
    public DtDatosUsuario getDataDatosUsuarios(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod(operationName = "ExisteNickname")
    @WebResult(partName = "return")
    @Action(input = "http://webservices.espotify/UsuarioService/ExisteNicknameRequest", output = "http://webservices.espotify/UsuarioService/ExisteNicknameResponse")
    public boolean existeNickname(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod(operationName = "ExisteEmail")
    @WebResult(partName = "return")
    @Action(input = "http://webservices.espotify/UsuarioService/ExisteEmailRequest", output = "http://webservices.espotify/UsuarioService/ExisteEmailResponse")
    public boolean existeEmail(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg0
     */
    @WebMethod(operationName = "AltaArtista")
    @Action(input = "http://webservices.espotify/UsuarioService/AltaArtistaRequest", output = "http://webservices.espotify/UsuarioService/AltaArtistaResponse")
    public void altaArtista(
        @WebParam(name = "arg0", partName = "arg0")
        DtArtista arg0);

    /**
     * 
     * @param arg0
     */
    @WebMethod(operationName = "AltaCliente")
    @Action(input = "http://webservices.espotify/UsuarioService/AltaClienteRequest", output = "http://webservices.espotify/UsuarioService/AltaClienteResponse")
    public void altaCliente(
        @WebParam(name = "arg0", partName = "arg0")
        DtCliente arg0);

    /**
     * 
     * @return
     *     returns espotify.webservices.ArrayListContainer
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webservices.espotify/UsuarioService/getNicknamesClientesRequest", output = "http://webservices.espotify/UsuarioService/getNicknamesClientesResponse")
    public ArrayListContainer getNicknamesClientes();

    /**
     * 
     * @return
     *     returns espotify.webservices.ArrayListContainer
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webservices.espotify/UsuarioService/getNicknamesArtistasRequest", output = "http://webservices.espotify/UsuarioService/getNicknamesArtistasResponse")
    public ArrayListContainer getNicknamesArtistas();

    /**
     * 
     * @param arg0
     * @return
     *     returns espotify.webservices.NullableContainer
     */
    @WebMethod(operationName = "ConsultarPerfilArtista")
    @WebResult(partName = "return")
    @Action(input = "http://webservices.espotify/UsuarioService/ConsultarPerfilArtistaRequest", output = "http://webservices.espotify/UsuarioService/ConsultarPerfilArtistaResponse")
    public NullableContainer consultarPerfilArtista(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns espotify.webservices.NullableContainer
     */
    @WebMethod(operationName = "ConsultarPerfilCliente")
    @WebResult(partName = "return")
    @Action(input = "http://webservices.espotify/UsuarioService/ConsultarPerfilClienteRequest", output = "http://webservices.espotify/UsuarioService/ConsultarPerfilClienteResponse")
    public NullableContainer consultarPerfilCliente(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod(operationName = "ExisteCliente")
    @WebResult(partName = "return")
    @Action(input = "http://webservices.espotify/UsuarioService/ExisteClienteRequest", output = "http://webservices.espotify/UsuarioService/ExisteClienteResponse")
    public boolean existeCliente(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

}
