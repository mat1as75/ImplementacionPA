
package webservices;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import webservices.DataTypes.DtAlbum;
import webservices.DataTypes.DtArtista;
import webservices.DataTypes.DtCliente;
import webservices.DataTypes.DtParticulares;
import webservices.DataTypes.DtSuscripcion;
import webservices.DataTypes.DtTemaGenerico;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the webservices package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Exception_QNAME = new QName("http://webservices.espotify/", "Exception");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: espotify.webservices
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Exception }
     * 
     */
    public Exception createException() {
        return new Exception();
    }

    /**
     * Create an instance of {@link DtSuscripcion }
     * 
     */
    public DtSuscripcion createDtSuscripcion() {
        return new DtSuscripcion();
    }

    /**
     * Create an instance of {@link DtCliente }
     * 
     */
    public DtCliente createDtCliente() {
        return new DtCliente();
    }

    /**
     * Create an instance of {@link DtAlbum }
     * 
     */
    public DtAlbum createDtAlbum() {
        return new DtAlbum();
    }

    /**
     * Create an instance of {@link DtArtista }
     * 
     */
    public DtArtista createDtArtista() {
        return new DtArtista();
    }

    /**
     * Create an instance of {@link DtTemaGenerico }
     * 
     */
    public DtTemaGenerico createDtTemaGenerico() {
        return new DtTemaGenerico();
    }

    /**
     * Create an instance of {@link DtParticulares }
     * 
     */
    public DtParticulares createDtParticulares() {
        return new DtParticulares();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Exception }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Exception }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservices.espotify/", name = "Exception")
    public JAXBElement<Exception> createException(Exception value) {
        return new JAXBElement<Exception>(_Exception_QNAME, Exception.class, null, value);
    }

}
