package espotify.webservices;

import espotify.DataTypes.DTArtista;
import espotify.DataTypes.DTCliente;
import espotify.DataTypes.DTDatosArtista;
import espotify.DataTypes.DTDatosCliente;
import espotify.DataTypes.DTDatosUsuario;
import espotify.DataTypes.DTUsuario;
import espotify.DataTypes.DTUsuarioGenerico;
import espotify.config.ConfigReader;
import espotify.logica.Fabrica;
import espotify.logica.IControlador;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.jws.soap.SOAPBinding.ParameterStyle;
import jakarta.jws.soap.SOAPBinding.Style;
import jakarta.xml.ws.Endpoint;
import java.util.ArrayList;


@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class UsuarioService {
    
    private final Fabrica fb = Fabrica.getInstance();
    private final IControlador control = fb.getControlador();
    private Endpoint endpoint = null;
    
    public UsuarioService() {};
    
    @WebMethod(exclude = true)
    public void publishEndpoint() {
        endpoint = Endpoint.publish(ConfigReader.getWebServiceBaseURL() + "UsuarioService", this);
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
        DTDatosUsuario datosU = this.control.getDatosUsuario(identificadorUsuario);
        DTUsuarioGenerico dtDatosUsuario = null;
        String tipoUsuario = this.control.getTipoUsuario(datosU.getNicknameUsuario());
 
        if (tipoUsuario.equals("Cliente")) {
            dtDatosUsuario = new DTUsuarioGenerico(
            datosU.getNicknameUsuario(),
            datosU.getNombreUsuario(),
            datosU.getApellidoUsuario(),
            datosU.getContrasenaUsuario(),
            datosU.getEmail(),
            datosU.getFecNac(),
            datosU.getFotoPerfil(),
            datosU.getNicknamesSeguidores(),
            ((DTDatosCliente) datosU).getNicknamesSeguidos(),
            ((DTDatosCliente) datosU).getNombresListasRCreadas(),
            ((DTDatosCliente) datosU).getNombresListasRCreadasPublicas(),
            ((DTDatosCliente) datosU).getNombresListasRFavoritas(),
            ((DTDatosCliente) datosU).getNombresAlbumesFavoritos(),
            ((DTDatosCliente) datosU).getNombresTemasFavoritos(),
            ((DTDatosCliente) datosU).getSuscripcion()
            );
        } else {
            dtDatosUsuario = new DTUsuarioGenerico(
            datosU.getNicknameUsuario(),
            datosU.getNombreUsuario(),
            datosU.getApellidoUsuario(),
            datosU.getContrasenaUsuario(),
            datosU.getEmail(),
            datosU.getFecNac(),
            datosU.getFotoPerfil(),
            datosU.getNicknamesSeguidores(),
            ((DTDatosArtista) datosU).getBiografia(),
            ((DTDatosArtista) datosU).getDirSitioWeb(),
            ((DTDatosArtista) datosU).getActivo(),
            ((DTDatosArtista) datosU).getFechaBaja(),
            ((DTDatosArtista) datosU).getCantidadSeguidores(),
            ((DTDatosArtista) datosU).getNombresAlbumesPublicados()
            );
        }
        
        contenedor.setDtUsuarioGenerico(dtDatosUsuario);
        return contenedor;
    }
    
    @WebMethod
    public DTDatosUsuario getDataDatosUsuarios(String identificadorUsuario) {
        DTDatosUsuario dtDatosUsuario = this.control.getDatosUsuario(identificadorUsuario);
        return dtDatosUsuario;
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
    public void darDeBajaArtista(String nicknameArtista) throws Exception {
        try {
            this.control.darDeBajaArtista(nicknameArtista);
        } catch (Exception ex) {
            throw ex;
        }
    }
    
    @WebMethod
    public String getTipoUsuario(String identificador) {
        return this.control.getTipoUsuario(identificador);
    }
    
}
