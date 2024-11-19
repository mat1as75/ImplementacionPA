package webservices;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import webservices.DataTypes.DtAlbum;
import webservices.DataTypes.DtSuscripcion;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "nullableContainer", propOrder = {
    "dtAlbum",
    "dtSuscripcion",
    "longObj"
})
public class NullableContainer {

    @XmlElement(nillable = true)
    protected DtAlbum dtAlbum;
    @XmlElement(nillable = true)
    protected DtSuscripcion dtSuscripcion;
    @XmlElement(nillable = true)
    protected Long longObj;

    public DtAlbum getDtAlbum() {
        return dtAlbum;
    }

    public void setDtAlbum(DtAlbum value) {
        this.dtAlbum = value;
    }

    public DtSuscripcion getDtSuscripcion() {
        return dtSuscripcion;
    }

    public void setDtSuscripcion(DtSuscripcion value) {
        this.dtSuscripcion = value;
    }

    public Long getLongObj() {
        return longObj;
    }

    public void setLongObj(Long value) {
        this.longObj = value;
    }
}
