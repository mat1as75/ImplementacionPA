package webservices.DataTypes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtTemaConTipo", propOrder = {
    "duracionSegundos",
    "nombreTema",
    "posicionEnAlbum",
    "rutaOurl",
    "tipo"
})
public class DtTemaConTipo {

    protected int duracionSegundos;
    protected String nombreTema;
    protected int posicionEnAlbum;
    protected String rutaOurl;
    protected String tipo;

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
     * Gets the value of the rutaOurl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRutaOurl() {
        return rutaOurl;
    }

    /**
     * Sets the value of the rutaOurl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRutaOurl(String value) {
        this.rutaOurl = value;
    }

    /**
     * Gets the value of the tipo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Sets the value of the tipo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipo(String value) {
        this.tipo = value;
    }

}
