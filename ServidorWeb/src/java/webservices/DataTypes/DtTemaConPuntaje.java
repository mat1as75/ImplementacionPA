
package webservices.DataTypes;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtTemaConPuntaje", propOrder = {
    "duracionSegundos",
    "generosDeTema",
    "idAlbum",
    "idTema",
    "nombreAlbum",
    "nombreCompletoArtista",
    "nombreTema",
    "posicionEnAlbum",
    "puntajeTema"
})
public class DtTemaConPuntaje {

    protected int duracionSegundos;
    @XmlElement(nillable = true)
    protected List<String> generosDeTema;
    protected Long idAlbum;
    protected Long idTema;
    protected String nombreAlbum;
    protected String nombreCompletoArtista;
    protected String nombreTema;
    protected int posicionEnAlbum;
    protected Float puntajeTema;

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

    /**
     * Gets the value of the nombreCompletoArtista property.
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
     * Sets the value of the nombreCompletoArtista property.
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

    /**
     * Gets the value of the puntajeTema property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getPuntajeTema() {
        return puntajeTema;
    }

    /**
     * Sets the value of the puntajeTema property.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setPuntajeTema(Float value) {
        this.puntajeTema = value;
    }

}
