package webservices;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import jakarta.xml.ws.Service;
import jakarta.xml.ws.WebEndpoint;
import jakarta.xml.ws.WebServiceClient;
import jakarta.xml.ws.WebServiceException;
import jakarta.xml.ws.WebServiceFeature;

@WebServiceClient(name = "SuscripcionesServiceService", targetNamespace = "http://webservices.espotify/", wsdlLocation = "http://localhost:8089/SuscripcionesService?wsdl")
public class SuscripcionesServiceService
    extends Service
{

    private final static URL SUSCRIPCIONESSERVICESERVICE_WSDL_LOCATION;
    private final static WebServiceException SUSCRIPCIONESSERVICESERVICE_EXCEPTION;
    private final static QName SUSCRIPCIONESSERVICESERVICE_QNAME = new QName("http://webservices.espotify/", "SuscripcionesServiceService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8089/SuscripcionesService?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        SUSCRIPCIONESSERVICESERVICE_WSDL_LOCATION = url;
        SUSCRIPCIONESSERVICESERVICE_EXCEPTION = e;
    }

    public SuscripcionesServiceService() {
        super(__getWsdlLocation(), SUSCRIPCIONESSERVICESERVICE_QNAME);
    }

    public SuscripcionesServiceService(WebServiceFeature... features) {
        super(__getWsdlLocation(), SUSCRIPCIONESSERVICESERVICE_QNAME, features);
    }

    public SuscripcionesServiceService(URL wsdlLocation) {
        super(wsdlLocation, SUSCRIPCIONESSERVICESERVICE_QNAME);
    }

    public SuscripcionesServiceService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, SUSCRIPCIONESSERVICESERVICE_QNAME, features);
    }

    public SuscripcionesServiceService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public SuscripcionesServiceService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns SuscripcionesService
     */
    @WebEndpoint(name = "SuscripcionesServicePort")
    public SuscripcionesService getSuscripcionesServicePort() {
        return super.getPort(new QName("http://webservices.espotify/", "SuscripcionesServicePort"), SuscripcionesService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns SuscripcionesService
     */
    @WebEndpoint(name = "SuscripcionesServicePort")
    public SuscripcionesService getSuscripcionesServicePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://webservices.espotify/", "SuscripcionesServicePort"), SuscripcionesService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (SUSCRIPCIONESSERVICESERVICE_EXCEPTION!= null) {
            throw SUSCRIPCIONESSERVICESERVICE_EXCEPTION;
        }
        return SUSCRIPCIONESSERVICESERVICE_WSDL_LOCATION;
    }

}
