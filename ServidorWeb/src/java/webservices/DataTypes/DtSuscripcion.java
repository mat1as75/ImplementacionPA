
package webservices.DataTypes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtSuscripcion", propOrder = {
    "estadoSuscripcion",
    "fechaSuscripcion",
    "idSuscripcion",
    "miCliente",
    "tipoSuscripcion"
})
public class DtSuscripcion {

    protected String estadoSuscripcion;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaSuscripcion;
    protected Long idSuscripcion;
    protected DtCliente miCliente;
    protected String tipoSuscripcion;

    public String getEstadoSuscripcion() {
        return estadoSuscripcion;
    }

    public void setEstadoSuscripcion(String value) {
        this.estadoSuscripcion = value;
    }

    public XMLGregorianCalendar getFechaSuscripcion() {
        return fechaSuscripcion;
    }

    public void setFechaSuscripcion(XMLGregorianCalendar value) {
        this.fechaSuscripcion = value;
    }

    public Long getIdSuscripcion() {
        return idSuscripcion;
    }

    public void setIdSuscripcion(Long value) {
        this.idSuscripcion = value;
    }

    public DtCliente getMiCliente() {
        return miCliente;
    }

    public void setMiCliente(DtCliente value) {
        this.miCliente = value;
    }

    public String getTipoSuscripcion() {
        return tipoSuscripcion;
    }

    public void setTipoSuscripcion(String value) {
        this.tipoSuscripcion = value;
    }
}
