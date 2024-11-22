package espotify.webservices;

import espotify.DataTypes.DTDatosUsuarioSinPw;
import espotify.DataTypes.DTTemaConPuntaje;
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
public class RankingService {
    private final Fabrica fb = Fabrica.getInstance();
    private final IControlador ictrl = fb.getControlador(); 
    private Endpoint endpoint = null;
    
    @WebMethod(exclude = true)
    public void publishEndpoint() {
        endpoint = Endpoint.publish("http://localhost:8089/RankingService", this);
    }
    
    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
        return endpoint;
    }
    
    @WebMethod
    public ArrayListContainer getTopTemas(int cantidadEsperada) {
        List<DTTemaConPuntaje> listDataTemas = this.ictrl.getTopTemas(cantidadEsperada);
        ArrayList<DTTemaConPuntaje> arrayListDataTemas = new ArrayList(listDataTemas);
        ArrayListContainer contenedor = new ArrayListContainer(arrayListDataTemas);
        
        return contenedor;
    }
    
    @WebMethod
    public DTTemaConPuntaje getTopTema() {
        List<DTTemaConPuntaje> listDataTemas = this.ictrl.getTopTemas(1);
        
        return listDataTemas.getFirst();
    }
    
    @WebMethod
    public ArrayListContainer getUsuariosOrdenadosPorRanking(int cantidadEsperada) {
        ArrayList<DTDatosUsuarioSinPw> listaUsuarios = this.ictrl.getUsuariosOrdenadosPorRanking(cantidadEsperada);
        ArrayListContainer contenedor = new ArrayListContainer(listaUsuarios);
                
        return contenedor;
    }
    
    @WebMethod
    public DTDatosUsuarioSinPw getTopUsuario() {
        List<DTDatosUsuarioSinPw> listaUsuarios = this.ictrl.getUsuariosOrdenadosPorRanking(1);
        
        return listaUsuarios.getFirst();
    }
}
