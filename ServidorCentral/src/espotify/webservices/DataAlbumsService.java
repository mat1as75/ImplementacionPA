package espotify.webservices;

import espotify.DataTypes.DTAlbum_Simple;
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
import java.util.List;


@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class DataAlbumsService {
    private final Fabrica fb = Fabrica.getInstance();
    private final IControlador ictrl = fb.getControlador();
    private Endpoint endpoint = null;
    
    public DataAlbumsService() {};
    
    @WebMethod(exclude = true)
    public void publishEndpoint() {
        endpoint = Endpoint.publish(ConfigReader.getWebServiceBaseURL() + "DataAlbumsService", this);
    }
    
    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
        return endpoint;
    }
    
    @WebMethod
    public DTAlbum_Simple getDTAlbumSimplePorId(Long idAlbum) {
        List<DTAlbum_Simple> listAlbums = ictrl.getDTAlbumesSimple();
        for (DTAlbum_Simple dataAlb : listAlbums) {
            if (dataAlb.getIdAlbum().equals(idAlbum)) {
                return dataAlb;
            }
        }
        return new DTAlbum_Simple();
    }
    
    @WebMethod
    public ArrayListContainer getDTAlbumesSimple() {
        List<DTAlbum_Simple> listAlbums = ictrl.getDTAlbumesSimple();
        ArrayList<DTAlbum_Simple> arrayListAlbums = new ArrayList();
        arrayListAlbums.addAll(listAlbums);
        ArrayListContainer contenedor = new ArrayListContainer(arrayListAlbums);
        
        return contenedor;
    }
    
    @WebMethod
    public ArrayListContainer getDTAlbumesSimplePorArtista(String artista) {
        List<DTAlbum_Simple> listAlbums = ictrl.getDTAlbumesSimplePorArtista(artista);
        ArrayList<DTAlbum_Simple> arrayListAlbums = new ArrayList();
        arrayListAlbums.addAll(listAlbums);
        ArrayListContainer contenedor = new ArrayListContainer(arrayListAlbums);
     
        return contenedor;
    }
    
    @WebMethod
    public ArrayListContainer getDTAlbumesSimplePorGenero(String genero) {
        List<DTAlbum_Simple> listAlbums = ictrl.getDTAlbumesSimplePorGenero(genero);
        ArrayList<DTAlbum_Simple> arrayListAlbums = new ArrayList();
        arrayListAlbums.addAll(listAlbums);
        ArrayListContainer contenedor = new ArrayListContainer(arrayListAlbums);
        
        return contenedor;
    }
}
