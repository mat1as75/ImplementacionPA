package webservices;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import jakarta.xml.ws.Service;
import jakarta.xml.ws.WebEndpoint;
import jakarta.xml.ws.WebServiceClient;
import jakarta.xml.ws.WebServiceException;
import jakarta.xml.ws.WebServiceFeature;

@WebServiceClient(name = "ListaReproduccionServiceService", targetNamespace = "http://webservices.espotify/", wsdlLocation = "http://localhost:8089/ListaReproduccionService?wsdl")
public class ListaReproduccionServiceService
    extends Service
{

    private final static URL LISTAREPRODUCCIONSERVICESERVICE_WSDL_LOCATION;
    private final static WebServiceException LISTAREPRODUCCIONSERVICESERVICE_EXCEPTION;
    private final static QName LISTAREPRODUCCIONSERVICESERVICE_QNAME = new QName("http://webservices.espotify/", "ListaReproduccionServiceService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8089/ListaReproduccionService?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        LISTAREPRODUCCIONSERVICESERVICE_WSDL_LOCATION = url;
        LISTAREPRODUCCIONSERVICESERVICE_EXCEPTION = e;
    }

    public ListaReproduccionServiceService() {
        super(__getWsdlLocation(), LISTAREPRODUCCIONSERVICESERVICE_QNAME);
    }

    public ListaReproduccionServiceService(WebServiceFeature... features) {
        super(__getWsdlLocation(), LISTAREPRODUCCIONSERVICESERVICE_QNAME, features);
    }

    public ListaReproduccionServiceService(URL wsdlLocation) {
        super(wsdlLocation, LISTAREPRODUCCIONSERVICESERVICE_QNAME);
    }

    public ListaReproduccionServiceService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, LISTAREPRODUCCIONSERVICESERVICE_QNAME, features);
    }

    public ListaReproduccionServiceService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ListaReproduccionServiceService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns ListaReproduccionService
     */
    @WebEndpoint(name = "ListaReproduccionServicePort")
    public ListaReproduccionService getListaReproduccionServicePort() {
        return super.getPort(new QName("http://webservices.espotify/", "ListaReproduccionServicePort"), ListaReproduccionService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ListaReproduccionService
     */
    @WebEndpoint(name = "ListaReproduccionServicePort")
    public ListaReproduccionService getListaReproduccionServicePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://webservices.espotify/", "ListaReproduccionServicePort"), ListaReproduccionService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (LISTAREPRODUCCIONSERVICESERVICE_EXCEPTION!= null) {
            throw LISTAREPRODUCCIONSERVICESERVICE_EXCEPTION;
        }
        return LISTAREPRODUCCIONSERVICESERVICE_WSDL_LOCATION;
    }

}
