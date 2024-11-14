
package webservices.DataTypes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dtListaReproduccion complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dtListaReproduccion"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="fotoLista" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="nombreLista" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtListaReproduccion", propOrder = {
    "fotoLista",
    "nombreLista"
})
@XmlSeeAlso({
    DtParticulares.class
})
public abstract class DtListaReproduccion {

    protected String fotoLista;
    protected String nombreLista;

    /**
     * Gets the value of the fotoLista property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFotoLista() {
        return fotoLista;
    }

    /**
     * Sets the value of the fotoLista property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFotoLista(String value) {
        this.fotoLista = value;
    }

    /**
     * Gets the value of the nombreLista property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreLista() {
        return nombreLista;
    }

    /**
     * Sets the value of the nombreLista property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreLista(String value) {
        this.nombreLista = value;
    }

}
