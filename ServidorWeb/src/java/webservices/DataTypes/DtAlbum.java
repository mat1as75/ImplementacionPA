
package webservices.DataTypes;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dtAlbum complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
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
    protected String nombreAlbum;

    /**
     * Gets the value of the anioCreacion property.
     * 
     */
    public int getAnioCreacion() {
        return anioCreacion;
    }

    /**
     * Sets the value of the anioCreacion property.
     * 
     */
    public void setAnioCreacion(int value) {
        this.anioCreacion = value;
    }

    /**
     * Gets the value of the estaDisponible property.
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
     * Sets the value of the estaDisponible property.
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
     * Gets the value of the fotoAlbum property.
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
     * Sets the value of the fotoAlbum property.
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
     * Gets the value of the idAlbum property.
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
     * Sets the value of the idAlbum property.
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
     * Gets the value of the miArtista property.
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
     * Sets the value of the miArtista property.
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
     * Gets the value of the nombreAlbum property.
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
     * Sets the value of the nombreAlbum property.
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
