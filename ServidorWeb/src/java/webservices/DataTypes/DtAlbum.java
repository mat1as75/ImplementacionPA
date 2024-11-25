
package webservices.DataTypes;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dtAlbum complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="dtAlbum"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="anioCreacion" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="estaDisponible" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="fotoAlbum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="idAlbum" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="miArtista" type="{http://webservices.espotify/}dtArtista" minOccurs="0"/&gt;
 *         &lt;element name="misTemas" type="{http://webservices.espotify/}dtTemaGenerico" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="misTemasSimples" type="{http://webservices.espotify/}dtTemaSimple" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="misgeneros" type="{http://webservices.espotify/}dtGenero" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="nombreAlbum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtAlbum", propOrder = {
    "anioCreacion",
    "estaDisponible",
    "fotoAlbum",
    "idAlbum",
    "miArtista",
    "misTemas",
    "misTemasSimples",
    "misgeneros",
    "nombreAlbum"
})
public class DtAlbum {

    protected int anioCreacion;
    protected Boolean estaDisponible;
    protected String fotoAlbum;
    protected Long idAlbum;
    protected DtArtista miArtista;
    @XmlElement(nillable = true)
    protected List<DtTemaGenerico> misTemas;
    @XmlElement(nillable = true)
    protected List<DtTemaSimple> misTemasSimples;
    @XmlElement(nillable = true)
    protected List<DtGenero> misgeneros;
    protected String nombreAlbum;

    /**
     * Obtiene el valor de la propiedad anioCreacion.
     * 
     * @return 
     */
    public int getAnioCreacion() {
        return anioCreacion;
    }

    /**
     * Define el valor de la propiedad anioCreacion.
     * 
     * @param value
     */
    public void setAnioCreacion(int value) {
        this.anioCreacion = value;
    }

    /**
     * Obtiene el valor de la propiedad estaDisponible.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEstaDisponible() {
        return estaDisponible;
    }

    /**
     * Define el valor de la propiedad estaDisponible.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEstaDisponible(Boolean value) {
        this.estaDisponible = value;
    }

    /**
     * Obtiene el valor de la propiedad fotoAlbum.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFotoAlbum() {
        return fotoAlbum;
    }

    /**
     * Define el valor de la propiedad fotoAlbum.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFotoAlbum(String value) {
        this.fotoAlbum = value;
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
     * Obtiene el valor de la propiedad miArtista.
     * 
     * @return
     *     possible object is
     *     {@link DtArtista }
     *     
     */
    public DtArtista getMiArtista() {
        return miArtista;
    }

    /**
     * Define el valor de la propiedad miArtista.
     * 
     * @param value
     *     allowed object is
     *     {@link DtArtista }
     *     
     */
    public void setMiArtista(DtArtista value) {
        this.miArtista = value;
    }

    /**
     * Gets the value of the misTemas property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the misTemas property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMisTemas().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtTemaGenerico }
     * 
     * 
     */
    public List<DtTemaGenerico> getMisTemas() {
        if (misTemas == null) {
            misTemas = new ArrayList<DtTemaGenerico>();
        }
        return this.misTemas;
    }

    /**
     * Gets the value of the misTemasSimples property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the misTemasSimples property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMisTemasSimples().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtTemaSimple }
     * 
     * 
     */
    public List<DtTemaSimple> getMisTemasSimples() {
        if (misTemasSimples == null) {
            misTemasSimples = new ArrayList<DtTemaSimple>();
        }
        return this.misTemasSimples;
    }

    /**
     * Gets the value of the misgeneros property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the misgeneros property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMisgeneros().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtGenero }
     * 
     * 
     */
    public List<DtGenero> getMisgeneros() {
        if (misgeneros == null) {
            misgeneros = new ArrayList<DtGenero>();
        }
        return this.misgeneros;
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

}
