
package webservices;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dtTemaSimple complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="dtTemaSimple"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="duracionSegundos" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="generosDeTema" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="idAlbum" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="idTema" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="nombreAlbum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="nombreCompletoArtista" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="nombreTema" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="posicionEnAlbum" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtTemaSimple", propOrder = {
    "duracionSegundos",
    "generosDeTema",
    "idAlbum",
    "idTema",
    "nombreAlbum",
    "nombreCompletoArtista",
    "nombreTema",
    "posicionEnAlbum"
})
public class DtTemaSimple {

    protected int duracionSegundos;
    @XmlElement(nillable = true)
    protected List<String> generosDeTema;
    protected Long idAlbum;
    protected Long idTema;
    protected String nombreAlbum;
    protected String nombreCompletoArtista;
    protected String nombreTema;
    protected int posicionEnAlbum;

    /**
     * Obtiene el valor de la propiedad duracionSegundos.
     * 
     */
    public int getDuracionSegundos() {
        return duracionSegundos;
    }

    /**
     * Define el valor de la propiedad duracionSegundos.
     * 
     */
    public void setDuracionSegundos(int value) {
        this.duracionSegundos = value;
    }

    /**
     * Gets the value of the generosDeTema property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the generosDeTema property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGenerosDeTema().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getGenerosDeTema() {
        if (generosDeTema == null) {
            generosDeTema = new ArrayList<String>();
        }
        return this.generosDeTema;
    }

    /**
     * Obtiene el valor de la propiedad idAlbum.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdAlbum() {
        return idAlbum;
    }

    /**
     * Define el valor de la propiedad idAlbum.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdAlbum(Long value) {
        this.idAlbum = value;
    }

    /**
     * Obtiene el valor de la propiedad idTema.
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
     * Define el valor de la propiedad idTema.
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
     * Obtiene el valor de la propiedad nombreAlbum.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreAlbum() {
        return nombreAlbum;
    }

    /**
     * Define el valor de la propiedad nombreAlbum.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreAlbum(String value) {
        this.nombreAlbum = value;
    }

    /**
     * Obtiene el valor de la propiedad nombreCompletoArtista.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreCompletoArtista() {
        return nombreCompletoArtista;
    }

    /**
     * Define el valor de la propiedad nombreCompletoArtista.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreCompletoArtista(String value) {
        this.nombreCompletoArtista = value;
    }

    /**
     * Obtiene el valor de la propiedad nombreTema.
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
     * Define el valor de la propiedad nombreTema.
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
     * Obtiene el valor de la propiedad posicionEnAlbum.
     * 
     */
    public int getPosicionEnAlbum() {
        return posicionEnAlbum;
    }

    /**
     * Define el valor de la propiedad posicionEnAlbum.
     * 
     */
    public void setPosicionEnAlbum(int value) {
        this.posicionEnAlbum = value;
    }

}
