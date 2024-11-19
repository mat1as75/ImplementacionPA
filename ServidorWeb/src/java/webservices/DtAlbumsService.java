
package webservices;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.ws.Action;

@WebService(name = "DtAlbumsService", targetNamespace = "http://webservices.espotify/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface DtAlbumsService {


    /**
     * 
     * @return
     *     returns espotify.webservices.ArrayListContainer
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webservices.espotify/DtAlbumsService/getTodosDTAlbumsSimpleRequest", output = "http://webservices.espotify/DtAlbumsService/getTodosDTAlbumsSimpleResponse")
    public ArrayListContainer getTodosDTAlbumsSimple();

    /**
     * 
     * @param arg0
     * @return
     *     returns espotify.webservices.ArrayListContainer
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webservices.espotify/DtAlbumsService/getDTAlbumSimplePorGeneroRequest", output = "http://webservices.espotify/DtAlbumsService/getDTAlbumSimplePorGeneroResponse")
    public ArrayListContainer getDTAlbumSimplePorGenero(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns espotify.webservices.ArrayListContainer
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webservices.espotify/DtAlbumsService/getDTAlbumSimplePorArtistaRequest", output = "http://webservices.espotify/DtAlbumsService/getDTAlbumSimplePorArtistaResponse")
    public ArrayListContainer getDTAlbumSimplePorArtista(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

}
