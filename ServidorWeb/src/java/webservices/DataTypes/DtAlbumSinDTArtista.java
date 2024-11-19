package webservices.DataTypes;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtAlbumSinDTArtista", propOrder = {
    "anioCreacion",
    "estaDisponible",
    "fotoAlbum",
    "miArtista",
    "misTemas",
    "misgeneros",
    "nombreAlbum"
})
public class DtAlbumSinDTArtista {

    protected int anioCreacion;
    protected Boolean estaDisponible;
    protected String fotoAlbum;
    protected String miArtista;
    @XmlElement(nillable = true)
    protected List<DtTemaConTipo> misTemas;
    @XmlElement(nillable = true)
    protected List<DtGenero> misgeneros;
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

    public String getMiArtista() {
        return miArtista;
    }

    public void setMiArtista(String value) {
        this.miArtista = value;
    }

    public List<DtTemaConTipo> getMisTemas() {
        if (misTemas == null) {
            misTemas = new ArrayList<DtTemaConTipo>();
        }
        return this.misTemas;
    }

    public List<DtGenero> getMisgeneros() {
        if (misgeneros == null) {
            misgeneros = new ArrayList<DtGenero>();
        }
        return this.misgeneros;
    }

    public String getNombreAlbum() {
        return nombreAlbum;
    }

    public void setNombreAlbum(String value) {
        this.nombreAlbum = value;
    }
}
