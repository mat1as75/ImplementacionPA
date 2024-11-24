
package webservices;

import webservices.DataTypes.DtUsuarioGenerico;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import webservices.DataTypes.DtAlbum;
import webservices.DataTypes.DtArtista;
import webservices.DataTypes.DtCliente;
import webservices.DataTypes.DtDatosArtista;
import webservices.DataTypes.DtDatosCliente;
import webservices.DataTypes.DtDatosListaReproduccion;
import webservices.DataTypes.DtDatosUsuario;
import webservices.DataTypes.DtSuscripcion;
import webservices.DataTypes.DtUsuario;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "nullableContainer", propOrder = {
    "dtAlbum",
    "dtDatosArtista",
    "dtDatosCliente",
    "dtDatosListaReproduccion",
    "dtDatosUsuario",
    "dtSuscripcion",
    "dtUsuario",
    "dtArtista",
    "dtCliente",
    "dtUsuarioGenerico",
    "longObj"
})
public class NullableContainer {

    @XmlElement(nillable = true)
    protected DtAlbum dtAlbum;
    @XmlElement(nillable = true)
    protected DtDatosArtista dtDatosArtista;
    @XmlElement(nillable = true)    
    protected DtDatosCliente dtDatosCliente;
    @XmlElement(nillable = true) 
    protected DtDatosListaReproduccion dtDatosListaReproduccion;
    @XmlElement(nillable = true) 
    protected DtDatosUsuario dtDatosUsuario;
    @XmlElement(nillable = true) 
    protected DtSuscripcion dtSuscripcion;
    @XmlElement(nillable = true) 
    protected DtUsuario dtUsuario;
    @XmlElement(nillable = true) 
    protected DtArtista dtArtista;
    @XmlElement(nillable = true) 
    protected DtCliente dtCliente;
    @XmlElement(nillable = true) 
    protected DtUsuarioGenerico dtUsuarioGenerico;
    @XmlElement(nillable = true) 
    protected Long longObj;

    public DtAlbum getDtAlbum() {
        return dtAlbum;
    }

    public void setDtAlbum(DtAlbum value) {
        this.dtAlbum = value;
    }

    public DtDatosArtista getDtDatosArtista() {
        return dtDatosArtista;
    }

    public void setDtDatosArtista(DtDatosArtista value) {
        this.dtDatosArtista = value;
    }

    public DtDatosCliente getDtDatosCliente() {
        return dtDatosCliente;
    }

    public void setDtDatosCliente(DtDatosCliente value) {
        this.dtDatosCliente = value;
    }

    public DtDatosListaReproduccion getDtDatosListaReproduccion() {
        return dtDatosListaReproduccion;
    }

    public void setDtDatosListaReproduccion(DtDatosListaReproduccion value) {
        this.dtDatosListaReproduccion = value;
    }

    public DtDatosUsuario getDtDatosUsuario() {
        return dtDatosUsuario;
    }

    public void setDtDatosUsuario(DtDatosUsuario value) {
        this.dtDatosUsuario = value;
    }

    public DtSuscripcion getDtSuscripcion() {
        return dtSuscripcion;
    }

    public void setDtSuscripcion(DtSuscripcion value) {
        this.dtSuscripcion = value;
    }

    public DtUsuario getDtUsuario() {
        return dtUsuario;
    }

    public void setDtUsuario(DtUsuario value) {
        this.dtUsuario = value;
    }

    public DtArtista getDtArtista() {
        return dtArtista;
    }

    public void setDtArtisa(DtArtista dtArtisa) {
        this.dtArtista = dtArtisa;
    }

    public DtCliente getDtCliente() {
        return dtCliente;
    }

    public void setDtCliente(DtCliente dtCliente) {
        this.dtCliente = dtCliente;
    }

    public DtUsuarioGenerico getDtUsuarioGenerico() {
        return dtUsuarioGenerico;
    }

    public void setDtUsuarioGenerico(DtUsuarioGenerico dtUsuarioGenerico) {
        this.dtUsuarioGenerico = dtUsuarioGenerico;
    }

    public Long getLongObj() {
        return longObj;
    }

    public void setLongObj(Long value) {
        this.longObj = value;
    }
}