
package webservices;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dtDatosListaReproduccion complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="dtDatosListaReproduccion"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="cliente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="fotoLista" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="genero" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="nombreLista" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="privacidad" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="temas" type="{http://webservices/}dtTemaSimple" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="tipoDeLista" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtDatosListaReproduccion", propOrder = {
    "cliente",
    "fotoLista",
    "genero",
    "nombreLista",
    "privacidad",
    "temas",
    "tipoDeLista"
})
public class DtDatosListaReproduccion {

    protected String cliente;
    protected String fotoLista;
    protected String genero;
    protected String nombreLista;
    protected Boolean privacidad;
    @XmlElement(nillable = true)
    protected List<DtTemaSimple> temas;
    protected String tipoDeLista;

    /**
     * Obtiene el valor de la propiedad cliente.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCliente() {
        return cliente;
    }

    /**
     * Define el valor de la propiedad cliente.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCliente(String value) {
        this.cliente = value;
    }
    public void setTemas(List<DtTemaSimple> temas) {
        this.temas=temas;
    }
    /**
     * Obtiene el valor de la propiedad fotoLista.
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
     * Define el valor de la propiedad fotoLista.
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
     * Obtiene el valor de la propiedad genero.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGenero() {
        return genero;
    }

    /**
     * Define el valor de la propiedad genero.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGenero(String value) {
        this.genero = value;
    }

    /**
     * Obtiene el valor de la propiedad nombreLista.
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
     * Define el valor de la propiedad nombreLista.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreLista(String value) {
        this.nombreLista = value;
    }

    /**
     * Obtiene el valor de la propiedad privacidad.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPrivacidad() {
        return privacidad;
    }

    /**
     * Define el valor de la propiedad privacidad.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPrivacidad(Boolean value) {
        this.privacidad = value;
    }

    /**
     * Gets the value of the temas property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the temas property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTemas().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtTemaSimple }
     * 
     * 
     */
    public List<DtTemaSimple> getTemas() {
        if (temas == null) {
            temas = new ArrayList<DtTemaSimple>();
        }
        return this.temas;
    }

    /**
     * Obtiene el valor de la propiedad tipoDeLista.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoDeLista() {
        return tipoDeLista;
    }

    /**
     * Define el valor de la propiedad tipoDeLista.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoDeLista(String value) {
        this.tipoDeLista = value;
    }

}
