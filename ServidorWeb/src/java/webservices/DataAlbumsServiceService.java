
package webservices;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import jakarta.xml.ws.Service;
import jakarta.xml.ws.WebEndpoint;
import jakarta.xml.ws.WebServiceClient;
import jakarta.xml.ws.WebServiceException;
import jakarta.xml.ws.WebServiceFeature;

@WebServiceClient(name = "DataAlbumsServiceService", targetNamespace = "http://webservices.espotify/", wsdlLocation = "http://localhost:8089/DataAlbumsService?wsdl")
public class DataAlbumsServiceService
    extends Service
{

    private final static URL DATAALBUMSSERVICESERVICE_WSDL_LOCATION;
    private final static WebServiceException DATAALBUMSSERVICESERVICE_EXCEPTION;
    private final static QName DATAALBUMSSERVICESERVICE_QNAME = new QName("http://webservices.espotify/", "DataAlbumsServiceService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8089/DataAlbumsService?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        DATAALBUMSSERVICESERVICE_WSDL_LOCATION = url;
        DATAALBUMSSERVICESERVICE_EXCEPTION = e;
    }

    public DataAlbumsServiceService() {
        super(__getWsdlLocation(), DATAALBUMSSERVICESERVICE_QNAME);
    }

    public DataAlbumsServiceService(WebServiceFeature... features) {
        super(__getWsdlLocation(), DATAALBUMSSERVICESERVICE_QNAME, features);
    }

    public DataAlbumsServiceService(URL wsdlLocation) {
        super(wsdlLocation, DATAALBUMSSERVICESERVICE_QNAME);
    }

    public DataAlbumsServiceService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, DATAALBUMSSERVICESERVICE_QNAME, features);
    }

    public DataAlbumsServiceService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public DataAlbumsServiceService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns DataAlbumsService
     */
    @WebEndpoint(name = "DataAlbumsServicePort")
    public DataAlbumsService getDataAlbumsServicePort() {
        return super.getPort(new QName("http://webservices.espotify/", "DataAlbumsServicePort"), DataAlbumsService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns DataAlbumsService
     */
    @WebEndpoint(name = "DataAlbumsServicePort")
    public DataAlbumsService getDataAlbumsServicePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://webservices.espotify/", "DataAlbumsServicePort"), DataAlbumsService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (DATAALBUMSSERVICESERVICE_EXCEPTION!= null) {
            throw DATAALBUMSSERVICESERVICE_EXCEPTION;
        }
        return DATAALBUMSSERVICESERVICE_WSDL_LOCATION;
    }

}
