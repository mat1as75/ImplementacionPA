
package webservices.DataTypes;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dtTemaGenerico complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dtTemaGenerico"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="duracionSegundos" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="miAlbum" type="{http://webservices.espotify/}dtAlbum" minOccurs="0"/&gt;
 *         &lt;element name="mislistasReproducc" type="{http://webservices.espotify/}dtListaReproduccion" maxOccurs="unbounded" minOccurs="0"/&gt;
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
@XmlType(name = "dtTemaGenerico", propOrder = {
    "duracionSegundos",
    "miAlbum",
    "mislistasReproducc",
    "nombreTema",
    "posicionEnAlbum"
})
public class DtTemaGenerico {

    protected int duracionSegundos;
    protected DtAlbum miAlbum;
    @XmlElement(nillable = true)
    protected List<DtListaReproduccion> mislistasReproducc;
    protected String nombreTema;
    protected int posicionEnAlbum;

    /**
     * Gets the value of the duracionSegundos property.
     * 
     */
    public int getDuracionSegundos() {
        return duracionSegundos;
    }

    /**
     * Sets the value of the duracionSegundos property.
     * 
     */
    public void setDuracionSegundos(int value) {
        this.duracionSegundos = value;
    }

    /**
     * Gets the value of the miAlbum property.
     * 
     * @return
     *     possible object is
     *     {@link DtAlbum }
     *     
     */
    public DtAlbum getMiAlbum() {
        return miAlbum;
    }

    /**
     * Sets the value of the miAlbum property.
     * 
     * @param value
     *     allowed object is
     *     {@link DtAlbum }
     *     
     */
    public void setMiAlbum(DtAlbum value) {
        this.miAlbum = value;
    }

    /**
     * Gets the value of the mislistasReproducc property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the mislistasReproducc property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMislistasReproducc().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtListaReproduccion }
     * 
     * 
     */
    public List<DtListaReproduccion> getMislistasReproducc() {
        if (mislistasReproducc == null) {
            mislistasReproducc = new ArrayList<DtListaReproduccion>();
        }
        return this.mislistasReproducc;
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
     * Gets the value of the posicionEnAlbum property.
     * 
     */
    public int getPosicionEnAlbum() {
        return posicionEnAlbum;
    }

    /**
     * Sets the value of the posicionEnAlbum property.
     * 
     */
    public void setPosicionEnAlbum(int value) {
        this.posicionEnAlbum = value;
    }

}
