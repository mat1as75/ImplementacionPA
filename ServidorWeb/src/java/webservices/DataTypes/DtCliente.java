
package webservices.DataTypes;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dtCliente complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dtCliente"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://webservices.espotify/}dtUsuario"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="miSuscripcion" type="{http://webservices.espotify/}dtSuscripcion" minOccurs="0"/&gt;
 *         &lt;element name="misAlbumesFav" type="{http://webservices.espotify/}dtAlbum" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="misListasReproduccionCreadas" type="{http://webservices.espotify/}dtParticulares" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="misListasReproduccionFav" type="{http://webservices.espotify/}dtListaReproduccion" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="misSeguidos" type="{http://webservices.espotify/}dtUsuario" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="misTemasFav" type="{http://webservices.espotify/}dtTemaGenerico" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtCliente", propOrder = {
    "miSuscripcion",
    "misAlbumesFav",
    "misListasReproduccionCreadas",
    "misListasReproduccionFav",
    "misSeguidos",
    "misTemasFav"
})
public class DtCliente
    extends DtUsuario
{

    protected DtSuscripcion miSuscripcion;

    /**
     *
     */
    @XmlElement(nillable = true)
    protected List<DtAlbum> misAlbumesFav;
    @XmlElement(nillable = true)
    protected List<DtParticulares> misListasReproduccionCreadas;
    @XmlElement(nillable = true)
    protected List<DtListaReproduccion> misListasReproduccionFav;
    @XmlElement(nillable = true)
    protected List<DtUsuario> misSeguidos;
    @XmlElement(nillable = true)
    protected List<DtTemaGenerico> misTemasFav;

    /**
     * Gets the value of the miSuscripcion property.
     * 
     * @return
     *     possible object is
     *     {@link DtSuscripcion }
     *     
     */
    public DtSuscripcion getMiSuscripcion() {
        return miSuscripcion;
    }

    /**
     * Sets the value of the miSuscripcion property.
     * 
     * @param value
     *     allowed object is
     *     {@link DtSuscripcion }
     *     
     */
    public void setMiSuscripcion(DtSuscripcion value) {
        this.miSuscripcion = value;
    }

    /**
     * Gets the value of the misAlbumesFav property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the misAlbumesFav property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMisAlbumesFav().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtAlbum }
     * 
     * 
     */
    public List<DtAlbum> getMisAlbumesFav() {
        if (misAlbumesFav == null) {
            misAlbumesFav = new ArrayList<DtAlbum>();
        }
        return this.misAlbumesFav;
    }

    /**
     * Gets the value of the misListasReproduccionCreadas property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the misListasReproduccionCreadas property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMisListasReproduccionCreadas().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtParticulares }
     * 
     * 
     */
    public List<DtParticulares> getMisListasReproduccionCreadas() {
        if (misListasReproduccionCreadas == null) {
            misListasReproduccionCreadas = new ArrayList<DtParticulares>();
        }
        return this.misListasReproduccionCreadas;
    }

    /**
     * Gets the value of the misListasReproduccionFav property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the misListasReproduccionFav property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMisListasReproduccionFav().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtListaReproduccion }
     * 
     * 
     */
    public List<DtListaReproduccion> getMisListasReproduccionFav() {
        if (misListasReproduccionFav == null) {
            misListasReproduccionFav = new ArrayList<DtListaReproduccion>();
        }
        return this.misListasReproduccionFav;
    }

    /**
     * Gets the value of the misSeguidos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the misSeguidos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMisSeguidos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtUsuario }
     * 
     * 
     */
    public List<DtUsuario> getMisSeguidos() {
        if (misSeguidos == null) {
            misSeguidos = new ArrayList<DtUsuario>();
        }
        return this.misSeguidos;
    }

    /**
     * Gets the value of the misTemasFav property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the misTemasFav property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMisTemasFav().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtTemaGenerico }
     * 
     * 
     */
    public List<DtTemaGenerico> getMisTemasFav() {
        if (misTemasFav == null) {
            misTemasFav = new ArrayList<DtTemaGenerico>();
        }
        return this.misTemasFav;
    }

}
