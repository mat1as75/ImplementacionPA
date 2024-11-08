package WebServices;

import jakarta.xml.ws.Service;
import jakarta.xml.ws.WebEndpoint;
import jakarta.xml.ws.WebServiceClient;
import jakarta.xml.ws.WebServiceException;
import jakarta.xml.ws.WebServiceFeature;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;

@WebServiceClient(name = "NuevoWebServiceService", targetNamespace = "http://webservices/", wsdlLocation = "http://localhost:8089/webservice?wsdl")
public class NuevoWebServiceService
    extends Service
{

    private final static URL NUEVOWEBSERVICESERVICE_WSDL_LOCATION;
    private final static WebServiceException NUEVOWEBSERVICESERVICE_EXCEPTION;
    private final static QName NUEVOWEBSERVICESERVICE_QNAME = new QName("http://webservices/", "NuevoWebServiceService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8089/webservice?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        NUEVOWEBSERVICESERVICE_WSDL_LOCATION = url;
        NUEVOWEBSERVICESERVICE_EXCEPTION = e;
    }

    public NuevoWebServiceService() {
        super(__getWsdlLocation(), NUEVOWEBSERVICESERVICE_QNAME);
    }

    public NuevoWebServiceService(WebServiceFeature... features) {
        super(__getWsdlLocation(), NUEVOWEBSERVICESERVICE_QNAME, features);
    }

    public NuevoWebServiceService(URL wsdlLocation) {
        super(wsdlLocation, NUEVOWEBSERVICESERVICE_QNAME);
    }

    public NuevoWebServiceService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, NUEVOWEBSERVICESERVICE_QNAME, features);
    }

    public NuevoWebServiceService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public NuevoWebServiceService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns NuevoWebService
     */
    @WebEndpoint(name = "NuevoWebServicePort")
    public NuevoWebService getNuevoWebServicePort() {
        return super.getPort(new QName("http://webservices/", "NuevoWebServicePort"), NuevoWebService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns NuevoWebService
     */
    @WebEndpoint(name = "NuevoWebServicePort")
    public NuevoWebService getNuevoWebServicePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://webservices/", "NuevoWebServicePort"), NuevoWebService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (NUEVOWEBSERVICESERVICE_EXCEPTION!= null) {
            throw NUEVOWEBSERVICESERVICE_EXCEPTION;
        }
        return NUEVOWEBSERVICESERVICE_WSDL_LOCATION;
    }

}
