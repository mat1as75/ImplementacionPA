
package webservices;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import webservices.DataTypes.DtAlbum;
import webservices.DataTypes.DtAlbumSimple;
import webservices.DataTypes.DtAlbumSinDTArtista;
import webservices.DataTypes.DtArtista;
import webservices.DataTypes.DtCliente;
import webservices.DataTypes.DtDatosListaReproduccion;
import webservices.DataTypes.DtGenero;
import webservices.DataTypes.DtParticulares;
import webservices.DataTypes.DtSuscripcion;
import webservices.DataTypes.DtTemaConPuntaje;
import webservices.DataTypes.DtTemaConRuta;
import webservices.DataTypes.DtTemaConTipo;
import webservices.DataTypes.DtTemaConUrl;
import webservices.DataTypes.DtTemaGenerico;
import webservices.DataTypes.DtTemaGenericoConRutaOUrl;
import webservices.DataTypes.DtTemaSimple;

@XmlRegistry
public class ObjectFactory {

    private final static QName _Exception_QNAME = new QName("http://webservices.espotify/", "Exception");

    public ObjectFactory() {
    }

    public NullableContainer createNullableContainer() {
        return new NullableContainer();
    }
    
    public DtTemaConTipo createDtTemaConTipo() {
        return new DtTemaConTipo();
    }
    
     public DtAlbumSimple createDtAlbumSimple() {
        return new DtAlbumSimple();
    }
    
    public DtSuscripcion createDtSuscripcion() {
        return new DtSuscripcion();
    }

    public DtCliente createDtCliente() {
        return new DtCliente();
    }

    public DtAlbum createDtAlbum() {
        return new DtAlbum();
    }

    public DtArtista createDtArtista() {
        return new DtArtista();
    }

    public DtTemaGenerico createDtTemaGenerico() {
        return new DtTemaGenerico();
    }

    public DtTemaConPuntaje createDtTemaConPuntaje() {
        return new DtTemaConPuntaje();
    }
    
    public DtParticulares createDtParticulares() {
        return new DtParticulares();
    }
    
    public Exception createException() {
        return new Exception();
    }

    public MapContainer createMapContainer() {
        return new MapContainer();
    }

    public MapContainer.MapLongString createMapContainerMapLongString() {
        return new MapContainer.MapLongString();
    }

    public MapContainer.MapLongDatatype createMapContainerMapLongDatatype() {
        return new MapContainer.MapLongDatatype();
    }

    public DtTemaSimple createDtTemaSimple() {
        return new DtTemaSimple();
    }

    public ArrayListContainer createArrayListContainer() {
        return new ArrayListContainer();
    }

    public DtDatosListaReproduccion createDtDatosListaReproduccion() {
        return new DtDatosListaReproduccion();
    }
    
    public DtTemaGenericoConRutaOUrl createDtTemaGenericoConRutaOUrl() {
        return new DtTemaGenericoConRutaOUrl();
    }

    public DtAlbumSinDTArtista createDtAlbumSinDTArtista() {
        return new DtAlbumSinDTArtista();
    }

    public DtGenero createDtGenero() {
        return new DtGenero();
    }

    public MapContainer.MapLongString.Entry createMapContainerMapLongStringEntry() {
        return new MapContainer.MapLongString.Entry();
    }

    public MapContainer.MapLongDatatype.Entry createMapContainerMapLongDatatypeEntry() {
        return new MapContainer.MapLongDatatype.Entry();
    }

    @XmlElementDecl(namespace = "http://webservices.espotify/", name = "Exception")
    public JAXBElement<Exception> createException(Exception value) {
        return new JAXBElement<Exception>(_Exception_QNAME, Exception.class, null, value);
    }

}
