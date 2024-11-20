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
import java.util.ArrayList;
import java.util.Date;

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
    public ArrayListContainer getNicknamesArtistas() {
        ArrayList<String> nicknamesArtistas = (ArrayList<String>) this.control.getNicknamesArtistas();
        ArrayListContainer contenedor = new ArrayListContainer(nicknamesArtistas);
        
        return contenedor;
    }
    
    @WebMethod
    public ArrayListContainer getNicknamesClientes() {
        ArrayList<String> nicknamesClientes = (ArrayList<String>) this.control.getNicknamesClientes();
        ArrayListContainer contenedor = new ArrayListContainer(nicknamesClientes);
        
        return contenedor;
    }
    
    @WebMethod
    public NullableContainer getDatosUsuario(String identificadorUsuario) {
        NullableContainer contenedor = new NullableContainer();
        DTDatosUsuario dtDatosUsuario = this.control.getDatosUsuario(identificadorUsuario);
        contenedor.setDtDatosUsuario(dtDatosUsuario);
        
        return contenedor;
    }
    
    @WebMethod
    public NullableContainer getUsuarioAutentificado(String identificador, String contrasenaUsuario) {
        NullableContainer contenedor = new NullableContainer();
        DTUsuario dtUsuario = this.control.getUsuarioAutentificado(identificador, contrasenaUsuario);
        contenedor.setDtUsuario(dtUsuario);
        
        return contenedor;
    }
    
    @WebMethod
    public NullableContainer ConsultarPerfilArtista(String nicknameArtista) {
        NullableContainer contenedor = new NullableContainer();
        DTDatosArtista dtDatosArtista = this.control.ConsultarPerfilArtista(nicknameArtista);
        contenedor.setDtDatosArtista(dtDatosArtista);
        
        return contenedor;
    }
    
    @WebMethod
    public NullableContainer ConsultarPerfilCliente(String nicknameCliente) {
        NullableContainer contenedor = new NullableContainer();
        DTDatosCliente dtDatosCliente = this.control.ConsultarPerfilCliente(nicknameCliente);
        contenedor.setDtDatosCliente(dtDatosCliente);
        
        return contenedor;
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
    public NullableContainer getDTSuscripcion(Long id) {
        NullableContainer contenedor = new NullableContainer();
        DTSuscripcion dtSuscripcion = this.control.getDTSuscripcion(id);
        contenedor.setDtSuscripcion(dtSuscripcion);
        
        return contenedor;
    }
    
    @WebMethod
    public NullableContainer getDTSuscripcionDeCliente(String nickname) throws Exception{
        NullableContainer contenedor = new NullableContainer();
        DTSuscripcion dtSuscripcion = this.control.getDTSuscripcionDeCliente(nickname);
        contenedor.setDtSuscripcion(dtSuscripcion);
        
        try {
            return contenedor;
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
