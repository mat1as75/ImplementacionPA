package webservices.DataTypes;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dtGenero complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dtGenero"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="miPadre" type="{http://webservices.espotify/}dtGenero" minOccurs="0"/&gt;
 *         &lt;element name="misAlbumes" type="{http://webservices.espotify/}dtAlbum" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="misGenerosHijos" type="{http://webservices.espotify/}dtGenero" minOccurs="0"/&gt;
 *         &lt;element name="misListasParticulares" type="{http://webservices.espotify/}dtParticulares" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="nombreGenero" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtGenero", propOrder = {
    "miPadre",
    "misAlbumes",
    "misGenerosHijos",
    "misListasParticulares",
    "nombreGenero"
})
public class DtGenero {

    protected DtGenero miPadre;
    @XmlElement(nillable = true)
    protected List<DtAlbum> misAlbumes;
    protected DtGenero misGenerosHijos;
    @XmlElement(nillable = true)
    protected List<DtParticulares> misListasParticulares;
    protected String nombreGenero;
    
    /**
     * Gets the value of the miPadre property.
     * 
     * @return
     *     possible object is
     *     {@link DtGenero }
     *     
     */
    public DtGenero getMiPadre() {
        return miPadre;
    }

    /**
     * Sets the value of the miPadre property.
     * 
     * @param value
     *     allowed object is
     *     {@link DtGenero }
     *     
     */
    public void setMiPadre(DtGenero value) {
        this.miPadre = value;
    }

    /**
     * Gets the value of the misAlbumes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the misAlbumes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMisAlbumes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtAlbum }
     * 
     * 
     */
    public List<DtAlbum> getMisAlbumes() {
        if (misAlbumes == null) {
            misAlbumes = new ArrayList<DtAlbum>();
        }
        return this.misAlbumes;
    }

    /**
     * Gets the value of the misGenerosHijos property.
     * 
     * @return
     *     possible object is
     *     {@link DtGenero }
     *     
     */
    public DtGenero getMisGenerosHijos() {
        return misGenerosHijos;
    }

    /**
     * Sets the value of the misGenerosHijos property.
     * 
     * @param value
     *     allowed object is
     *     {@link DtGenero }
     *     
     */
    public void setMisGenerosHijos(DtGenero value) {
        this.misGenerosHijos = value;
    }

    /**
     * Gets the value of the misListasParticulares property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the misListasParticulares property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMisListasParticulares().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtParticulares }
     * 
     * 
     */
    public List<DtParticulares> getMisListasParticulares() {
        if (misListasParticulares == null) {
            misListasParticulares = new ArrayList<DtParticulares>();
        }
        return this.misListasParticulares;
    }

    /**
     * Gets the value of the nombreGenero property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreGenero() {
        return nombreGenero;
    }

    /**
     * Sets the value of the nombreGenero property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreGenero(String value) {
        this.nombreGenero = value;
    }

}
