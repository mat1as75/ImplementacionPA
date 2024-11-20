
package webservices;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.ws.Action;
import webservices.DataTypes.DtAlbumSimple;

@WebService(name = "DataAlbumsService", targetNamespace = "http://webservices.espotify/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface DataAlbumsService {


    /**
     * 
     * @param arg0
     * @return
     *     returns espotify.webservices.ArrayListContainer
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webservices.espotify/DataAlbumsService/getDTAlbumesSimplePorArtistaRequest", output = "http://webservices.espotify/DataAlbumsService/getDTAlbumesSimplePorArtistaResponse")
    public ArrayListContainer getDTAlbumesSimplePorArtista(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns espotify.webservices.DtAlbumSimple
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webservices.espotify/DataAlbumsService/getDTAlbumSimplePorIdRequest", output = "http://webservices.espotify/DataAlbumsService/getDTAlbumSimplePorIdResponse")
    public DtAlbumSimple getDTAlbumSimplePorId(
        @WebParam(name = "arg0", partName = "arg0")
        long arg0);

    /**
     * 
     * @return
     *     returns espotify.webservices.ArrayListContainer
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webservices.espotify/DataAlbumsService/getDTAlbumesSimpleRequest", output = "http://webservices.espotify/DataAlbumsService/getDTAlbumesSimpleResponse")
    public ArrayListContainer getDTAlbumesSimple();

    /**
     * 
     * @param arg0
     * @return
     *     returns espotify.webservices.ArrayListContainer
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://webservices.espotify/DataAlbumsService/getDTAlbumesSimplePorGeneroRequest", output = "http://webservices.espotify/DataAlbumsService/getDTAlbumesSimplePorGeneroResponse")
    public ArrayListContainer getDTAlbumesSimplePorGenero(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

}
