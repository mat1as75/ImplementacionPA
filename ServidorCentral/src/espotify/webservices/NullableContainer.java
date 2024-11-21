package espotify.webservices;

import espotify.DataTypes.DTAlbum;
import espotify.DataTypes.DTArtista;
import espotify.DataTypes.DTCliente;
import espotify.DataTypes.DTDatosArtista;
import espotify.DataTypes.DTDatosCliente;
import espotify.DataTypes.DTDatosListaReproduccion;
import espotify.DataTypes.DTDatosUsuario;
import espotify.DataTypes.DTSuscripcion;
import espotify.DataTypes.DTUsuario;

public class NullableContainer {
    private Long longObj;
    private DTAlbum dtAlbum;
    private DTSuscripcion dtSuscripcion;
    private DTDatosUsuario dtDatosUsuario;
    private DTDatosArtista dtDatosArtista;
    private DTDatosCliente dtDatosCliente;
    private DTUsuario dtUsuario;
    private DTDatosListaReproduccion dtDatosListaReproduccion;
    private DTArtista dtArtista;
    private DTCliente dtCliente;
    
    public NullableContainer() {
        this.longObj = null;
        this.dtAlbum = null;
        this.dtSuscripcion = null;
        this.dtDatosUsuario = null;
        this.dtDatosArtista = null;
        this.dtDatosCliente = null;
        this.dtUsuario = null;
        this.dtDatosListaReproduccion = null;
        this.dtArtista = null;
        this.dtCliente = null;
    };

    public Long getLongObj() {
        return longObj;
    }

    public void setLongObj(Long longObj) {
        this.longObj = longObj;
    }

    public DTAlbum getDtAlbum() {
        return dtAlbum;
    }

    public void setDtAlbum(DTAlbum dtAlbum) {
        this.dtAlbum = dtAlbum;
    }

    public DTSuscripcion getDtSuscripcion() {
        return dtSuscripcion;
    }

    public void setDtSuscripcion(DTSuscripcion dtSuscripcion) {
        this.dtSuscripcion = dtSuscripcion;
    }
    
    public DTDatosUsuario getDtDatosUsuario() {
        return dtDatosUsuario;
    }
    
    public void setDtDatosUsuario(DTDatosUsuario dtDatosUsuario) {
        this.dtDatosUsuario = dtDatosUsuario;
    }

    public DTUsuario getDtUsuario() {
        return dtUsuario;
    }

    public void setDtUsuario(DTUsuario dtUsuario) {
        this.dtUsuario = dtUsuario;
    }

    public DTDatosArtista getDtDatosArtista() {
        return dtDatosArtista;
    }

    public void setDtDatosArtista(DTDatosArtista dtDatosArtista) {
        this.dtDatosArtista = dtDatosArtista;
    }

    public DTDatosCliente getDtDatosCliente() {
        return dtDatosCliente;
    }

    public void setDtDatosCliente(DTDatosCliente dtDatosCliente) {
        this.dtDatosCliente = dtDatosCliente;
    }

    public DTDatosListaReproduccion getDtDatosListaReproduccion() {
        return dtDatosListaReproduccion;
    }

    public void setDtDatosListaReproduccion(DTDatosListaReproduccion dtDatosListaReproduccion) {
        this.dtDatosListaReproduccion = dtDatosListaReproduccion;
    }

    public DTArtista getDtArtista() {
        return dtArtista;
    }

    public void setDtArtista(DTArtista dtArtista) {
        this.dtArtista = dtArtista;
    }

    public DTCliente getDtCliente() {
        return dtCliente;
    }

    public void setDtCliente(DTCliente dtCliente) {
        this.dtCliente = dtCliente;
    }
    
}
