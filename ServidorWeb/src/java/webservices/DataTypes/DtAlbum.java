
package webservices.DataTypes;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

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

    public int getAnioCreacion() {
        return anioCreacion;
    }

    public void setAnioCreacion(int value) {
        this.anioCreacion = value;
    }

    public Boolean isEstaDisponible() {
        return estaDisponible;
    }

    public void setEstaDisponible(Boolean value) {
        this.estaDisponible = value;
    }

    public String getFotoAlbum() {
        return fotoAlbum;
    }

    public void setFotoAlbum(String value) {
        this.fotoAlbum = value;
    }

    public Long getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(Long value) {
        this.idAlbum = value;
    }

    public DtArtista getMiArtista() {
        return miArtista;
    }

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

    public String getNombreAlbum() {
        return nombreAlbum;
    }

    public void setNombreAlbum(String value) {
        this.nombreAlbum = value;
    }
}
