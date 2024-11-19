package espotify.webservices;

import espotify.DataTypes.DTArtista;
import espotify.DataTypes.DTCliente;
import espotify.DataTypes.DTDatosArtista;
import espotify.DataTypes.DTDatosCliente;
import espotify.DataTypes.DTDatosUsuario;
import espotify.DataTypes.DTSuscripcion;
import espotify.DataTypes.DTUsuario;
import espotify.logica.Fabrica;
import espotify.logica.IControlador;
import espotify.logica.Suscripcion.EstadoSuscripcion;
import espotify.logica.Suscripcion.TipoSuscripcion;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.xml.ws.Endpoint;
import java.util.Date;
import java.util.List;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class UsuarioService {
    
    private final Fabrica fb = Fabrica.getInstance();
    private final IControlador control = fb.getControlador();
    private Endpoint endpoint = null;
    public UsuarioService() {};
    
    @WebMethod(exclude = true)
    public void publishEndpoint() {
        endpoint = Endpoint.publish("http://localhost:8089/UsuarioService", this);
    }
    
    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
        return endpoint;
    }
    
    @WebMethod
    public void AltaArtista(DTArtista dtArtista) {
        this.control.AltaArtista(dtArtista);
    }
    
    @WebMethod
    public void AltaCliente(DTCliente dtCliente) {
        this.control.AltaCliente(dtCliente);
    }
    
    @WebMethod
    public List<String> getNicknamesArtistas() {
        return this.control.getNicknamesArtistas();
    }
    
    @WebMethod
    public List<String> getNicknamesClientes() {
        return this.control.getNicknamesClientes();
    }
    
    @WebMethod
    public DTDatosUsuario getDatosUsuario(String identificadorUsuario) {
        return this.control.getDatosUsuario(identificadorUsuario);
    }
    
    @WebMethod
    public DTUsuario getUsuarioAutentificado(String identificador, String contrasenaUsuario) {
        return this.control.getUsuarioAutentificado(identificador, contrasenaUsuario);
    }
    
    @WebMethod
    public DTDatosArtista ConsultarPerfilArtista(String nicknameArtista) {
        return this.control.ConsultarPerfilArtista(nicknameArtista);
    }
    
    @WebMethod
    public DTDatosCliente ConsultarPerfilCliente(String nicknameCliente) {
        return this.control.ConsultarPerfilCliente(nicknameCliente);
    }
    
    @WebMethod
    public boolean ExisteCliente(String nicknameCliente) {
        return this.control.ExisteCliente(nicknameCliente);
    }
    
    @WebMethod
    public boolean ExisteArtista(String nicknameArtista) {
        return this.control.ExisteArtista(nicknameArtista);
    }
    
    @WebMethod
    public boolean ExisteNickname(String nickname) {
        return this.control.ExisteNickName(nickname);
    }
    
    @WebMethod
    public boolean ExisteEmail(String email) {
        return this.control.ExisteEmail(email);
    }
    
    @WebMethod
    public DTSuscripcion getDTSuscripcion(Long id) {
        return this.control.getDTSuscripcion(id);
    }
    
    @WebMethod
    public DTSuscripcion getDTSuscripcionDeCliente(String nickname) throws Exception{
        try {
            return this.control.getDTSuscripcionDeCliente(nickname);
        } catch (Exception e) {
            throw e;
        }
    }
    
    @WebMethod
    public void ingresarNuevaSuscirpcion(String nickname, TipoSuscripcion tipoSuscripcion) throws Exception {
        try {
            this.control.ingresarNuevaSuscripcion(nickname, tipoSuscripcion);
        } catch (Exception e) {
            throw e;
        }
    }

    @WebMethod
    public boolean actualizarSuscirpcionVencida(Long idSuscripcion) {
        return this.control.actualizarSuscripcionVencida(idSuscripcion);
    }
    
    @WebMethod
    public void ActualizarEstadoSuscripcion(Long idSuscripcion, EstadoSuscripcion estadoSuscripcion, Date fechaSuscripcion) {
        this.control.ActualizarEstadoSuscripcion(idSuscripcion, estadoSuscripcion, fechaSuscripcion);
    }
    
}
