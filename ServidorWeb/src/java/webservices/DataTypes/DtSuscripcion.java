
package webservices.DataTypes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for dtSuscripcion complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dtSuscripcion"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="estadoSuscripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="fechaSuscripcion" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="idSuscripcion" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="miCliente" type="{http://webservices.espotify/}dtCliente" minOccurs="0"/&gt;
 *         &lt;element name="tipoSuscripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
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

    /**
     * Gets the value of the estadoSuscripcion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstadoSuscripcion() {
        return estadoSuscripcion;
    }

    /**
     * Sets the value of the estadoSuscripcion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstadoSuscripcion(String value) {
        this.estadoSuscripcion = value;
    }

    /**
     * Gets the value of the fechaSuscripcion property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaSuscripcion() {
        return fechaSuscripcion;
    }

    /**
     * Sets the value of the fechaSuscripcion property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaSuscripcion(XMLGregorianCalendar value) {
        this.fechaSuscripcion = value;
    }

    /**
     * Gets the value of the idSuscripcion property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdSuscripcion() {
        return idSuscripcion;
    }

    /**
     * Sets the value of the idSuscripcion property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdSuscripcion(Long value) {
        this.idSuscripcion = value;
    }

    /**
     * Gets the value of the miCliente property.
     * 
     * @return
     *     possible object is
     *     {@link DtCliente }
     *     
     */
    public DtCliente getMiCliente() {
        return miCliente;
    }

    /**
     * Sets the value of the miCliente property.
     * 
     * @param value
     *     allowed object is
     *     {@link DtCliente }
     *     
     */
    public void setMiCliente(DtCliente value) {
        this.miCliente = value;
    }

    /**
     * Gets the value of the tipoSuscripcion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoSuscripcion() {
        return tipoSuscripcion;
    }

    /**
     * Sets the value of the tipoSuscripcion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoSuscripcion(String value) {
        this.tipoSuscripcion = value;
    }

}
