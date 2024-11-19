package webservices.DataTypes;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

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

    public int getDuracionSegundos() {
        return duracionSegundos;
    }

    public void setDuracionSegundos(int value) {
        this.duracionSegundos = value;
    }

    public DtAlbum getMiAlbum() {
        return miAlbum;
    }

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

    public String getNombreTema() {
        return nombreTema;
    }

    public void setNombreTema(String value) {
        this.nombreTema = value;
    }

    public int getPosicionEnAlbum() {
        return posicionEnAlbum;
    }

    public void setPosicionEnAlbum(int value) {
        this.posicionEnAlbum = value;
    }
}
