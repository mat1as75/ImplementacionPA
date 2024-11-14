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
public class PreferenciasService {
    private final Fabrica fb = Fabrica.getInstance();
    private final IControlador ictrl = fb.getControlador(); 
    private Endpoint endpoint = null;
    
    public PreferenciasService() {};
    
    @WebMethod(exclude = true)
    public void publishEndpoint() {
        endpoint = Endpoint.publish("http://localhost:8089/PreferenciasService", this);
    }
    
    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
        return endpoint;
    }
    
    @WebMethod
    public void dejarDeSeguir(String nickCliente, String nickUsuario) {
        ictrl.dejarDeSeguir(nickUsuario, nickUsuario);
    }
    
    @WebMethod
    public void guardarAlbumFavorito(String nicknameCliente, Long idAlbum) throws Exception {
        try {
            ictrl.GuardarAlbumFavorito(nicknameCliente, idAlbum);
        } catch (Exception e) {
            throw e;
        }
    }
    
    @WebMethod
    public void guardarTemaFavorito(String nicknameCliente, Long idTema) throws Exception {
        try {
            ictrl.GuardarTemaFavorito(nicknameCliente, idTema);
        } catch (Exception e) {
            throw e;
        }
    }
    
    @WebMethod
    public void guardarListaFavorito(String nicknameCliente, String nombreLista) throws Exception {
        try {
            ictrl.GuardarListaFavorito(nicknameCliente, nombreLista);
        } catch (Exception e) {
            throw e;
        }
    }
    
    @WebMethod
    public void publicarLista(String nicknameCliente, String nombreLista) {
        ictrl.setPrivadafalse(nicknameCliente, nombreLista);
    }
    
    @WebMethod
    public void seguirUsuario(String nicknameSeguidor, String nicknameSeguido) {
        ictrl.setSeguidorSeguido(nicknameSeguidor, nicknameSeguido);
    }
    
    @WebMethod
    public void incrementarReproduccionesDeTema(Long idTema) throws Exception {
        try {
            ictrl.incrementarReproduccionesDeTema(idTema);
        } catch (Exception e) {
            throw e;
        }
    }
    
    @WebMethod
    public void incrementarDescargasOVisitasDeTema(Long idTema) throws Exception {
        try {
            ictrl.incrementarDescargasOVisitasDeTema(idTema);
        } catch (Exception e) {
            throw e;
        }
    }
}
