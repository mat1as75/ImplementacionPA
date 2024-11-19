package espotify.webservices;

import espotify.DataTypes.DTAlbum;
import espotify.DataTypes.DTSuscripcion;

public class NullableContainer {
    private Long longObj;
    private DTAlbum dtAlbum;
    private DTSuscripcion dtSuscripcion;
    
    public NullableContainer() {
        this.longObj = null;
        this.dtAlbum = null;
        this.dtSuscripcion = null;
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

   
}
