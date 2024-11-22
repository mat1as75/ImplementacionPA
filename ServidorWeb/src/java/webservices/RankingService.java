
package webservices;

import webservices.DataTypes.DtTemaConPuntaje;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.ws.Action;
import webservices.DataTypes.DtDatosUsuarioSinPw;

@WebService(name = "RankingService", targetNamespace = "http://webservices.espotify/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface RankingService {

    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webservices.espotify/RankingService/getTopTemasRequest", output = "http://webservices.espotify/RankingService/getTopTemasResponse")
    public ArrayListContainer getTopTemas(
        @WebParam(name = "arg0", partName = "arg0")
        int arg0);

    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webservices.espotify/RankingService/getTopTemaRequest", output = "http://webservices.espotify/RankingService/getTopTemaResponse")
    public DtTemaConPuntaje getTopTema();

    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webservices.espotify/RankingService/getUsuariosOrdenadosPorRankingRequest", output = "http://webservices.espotify/RankingService/getUsuariosOrdenadosPorRankingResponse")
    public ArrayListContainer getUsuariosOrdenadosPorRanking(
        @WebParam(name = "arg0", partName = "arg0")
        int arg0);
    
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webservices.espotify/RankingService/getTopUsuarioRequest", output = "http://webservices.espotify/RankingService/getTopUsuarioResponse")
    public DtDatosUsuarioSinPw getTopUsuario();


}
