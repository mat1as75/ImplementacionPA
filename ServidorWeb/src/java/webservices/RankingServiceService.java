
package webservices;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import jakarta.xml.ws.Service;
import jakarta.xml.ws.WebEndpoint;
import jakarta.xml.ws.WebServiceClient;
import jakarta.xml.ws.WebServiceException;
import jakarta.xml.ws.WebServiceFeature;

@WebServiceClient(name = "RankingServiceService", targetNamespace = "http://webservices.espotify/", wsdlLocation = "http://localhost:8089/RankingService?wsdl")
public class RankingServiceService
    extends Service
{

    private final static URL RANKINGSERVICESERVICE_WSDL_LOCATION;
    private final static WebServiceException RANKINGSERVICESERVICE_EXCEPTION;
    private final static QName RANKINGSERVICESERVICE_QNAME = new QName("http://webservices.espotify/", "RankingServiceService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8089/RankingService?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        RANKINGSERVICESERVICE_WSDL_LOCATION = url;
        RANKINGSERVICESERVICE_EXCEPTION = e;
    }

    public RankingServiceService() {
        super(__getWsdlLocation(), RANKINGSERVICESERVICE_QNAME);
    }

    public RankingServiceService(WebServiceFeature... features) {
        super(__getWsdlLocation(), RANKINGSERVICESERVICE_QNAME, features);
    }

    public RankingServiceService(URL wsdlLocation) {
        super(wsdlLocation, RANKINGSERVICESERVICE_QNAME);
    }

    public RankingServiceService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, RANKINGSERVICESERVICE_QNAME, features);
    }

    public RankingServiceService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public RankingServiceService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns RankingService
     */
    @WebEndpoint(name = "RankingServicePort")
    public RankingService getRankingServicePort() {
        return super.getPort(new QName("http://webservices.espotify/", "RankingServicePort"), RankingService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns RankingService
     */
    @WebEndpoint(name = "RankingServicePort")
    public RankingService getRankingServicePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://webservices.espotify/", "RankingServicePort"), RankingService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (RANKINGSERVICESERVICE_EXCEPTION!= null) {
            throw RANKINGSERVICESERVICE_EXCEPTION;
        }
        return RANKINGSERVICESERVICE_WSDL_LOCATION;
    }

}
