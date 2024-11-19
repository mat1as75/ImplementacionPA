
package espotify.webservices;

import espotify.DataTypes.DTAlbum_Simple;
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
public class DtAlbumsService {
    private final Fabrica fb = Fabrica.getInstance();
    private final IControlador ictrl = fb.getControlador(); 
    private Endpoint endpoint = null;
    
    @WebMethod(exclude = true)
    public void publishEndpoint() {
        endpoint = Endpoint.publish("http://localhost:8089/DtAlbumsService", this);
    }
    
    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
        return endpoint;
    }
    
    @WebMethod
    public ArrayListContainer getTodosDTAlbumsSimple() {
        ArrayList<DTAlbum_Simple> arrListDTAlbumes = ictrl.getDTAlbumesSimple();
        ArrayListContainer contenedor = new ArrayListContainer(arrListDTAlbumes);
        
        return contenedor;
    }
    
    @WebMethod
    public ArrayListContainer getDTAlbumSimplePorGenero(String genero) {
        ArrayList<DTAlbum_Simple> arrListDTAlbumes = ictrl.getDTAlbumesSimplePorGenero(genero);
        ArrayListContainer contenedor = new ArrayListContainer(arrListDTAlbumes);

        return contenedor;
    }
    
    @WebMethod
    public ArrayListContainer getDTAlbumSimplePorArtista(String artista) {
        ArrayList<DTAlbum_Simple> arrListDTAlbumes = ictrl.getDTAlbumesSimplePorArtista(artista);
        ArrayListContainer contenedor = new ArrayListContainer(arrListDTAlbumes);

        return contenedor;
    }
}
