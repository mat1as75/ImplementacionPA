
package webservices.DataTypes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dtTemaGenericoConRutaOUrl complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dtTemaGenericoConRutaOUrl"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="cantidadDescargasOVisitas" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="cantidadFavoritos" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="cantidadReproducciones" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="idTema" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="nombreTema" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="puntajeTema" type="{http://www.w3.org/2001/XMLSchema}float"/&gt;
 *         &lt;element name="rutaTema" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="urlTema" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtTemaGenericoConRutaOUrl", propOrder = {
    "cantidadDescargasOVisitas",
    "cantidadFavoritos",
    "cantidadReproducciones",
    "idTema",
    "nombreTema",
    "puntajeTema",
    "rutaTema",
    "urlTema"
})
public class DtTemaGenericoConRutaOUrl {

    protected Long cantidadDescargasOVisitas;
    protected Long cantidadFavoritos;
    protected Long cantidadReproducciones;
    protected Long idTema;
    protected String nombreTema;
    protected float puntajeTema;
    protected String rutaTema;
    protected String urlTema;

    /**
     * Gets the value of the cantidadDescargasOVisitas property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCantidadDescargasOVisitas() {
        return cantidadDescargasOVisitas;
    }

    /**
     * Sets the value of the cantidadDescargasOVisitas property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCantidadDescargasOVisitas(Long value) {
        this.cantidadDescargasOVisitas = value;
    }

    /**
     * Gets the value of the cantidadFavoritos property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCantidadFavoritos() {
        return cantidadFavoritos;
    }

    /**
     * Sets the value of the cantidadFavoritos property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCantidadFavoritos(Long value) {
        this.cantidadFavoritos = value;
    }

    /**
     * Gets the value of the cantidadReproducciones property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCantidadReproducciones() {
        return cantidadReproducciones;
    }

    /**
     * Sets the value of the cantidadReproducciones property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCantidadReproducciones(Long value) {
        this.cantidadReproducciones = value;
    }

    /**
     * Gets the value of the idTema property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdTema() {
        return idTema;
    }

    /**
     * Sets the value of the idTema property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdTema(Long value) {
        this.idTema = value;
    }

    /**
     * Gets the value of the nombreTema property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreTema() {
        return nombreTema;
    }

    /**
     * Sets the value of the nombreTema property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreTema(String value) {
        this.nombreTema = value;
    }

    /**
     * Gets the value of the puntajeTema property.
     * 
     */
    public float getPuntajeTema() {
        return puntajeTema;
    }

    /**
     * Sets the value of the puntajeTema property.
     * 
     */
    public void setPuntajeTema(float value) {
        this.puntajeTema = value;
    }

    /**
     * Gets the value of the rutaTema property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRutaTema() {
        return rutaTema;
    }

    /**
     * Sets the value of the rutaTema property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRutaTema(String value) {
        this.rutaTema = value;
    }

    /**
     * Gets the value of the urlTema property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrlTema() {
        return urlTema;
    }

    /**
     * Sets the value of the urlTema property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrlTema(String value) {
        this.urlTema = value;
    }

}
