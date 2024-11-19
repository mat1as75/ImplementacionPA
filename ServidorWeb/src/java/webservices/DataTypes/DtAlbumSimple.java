package webservices.DataTypes;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtAlbumSimple", propOrder = {
    "anioCreacion",
    "estaDisponible",
    "generosDeAlbum",
    "idAlbum",
    "nombreAlbum",
    "nombreCompletoArtista"
})
public class DtAlbumSimple {

    protected int anioCreacion;
    protected Boolean estaDisponible;
    @XmlElement(nillable = true)
    protected List<String> generosDeAlbum;
    protected Long idAlbum;
    protected String nombreAlbum;
    protected String nombreCompletoArtista;

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

    public List<String> getGenerosDeAlbum() {
        if (generosDeAlbum == null) {
            generosDeAlbum = new ArrayList<String>();
        }
        return this.generosDeAlbum;
    }

    public Long getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(Long value) {
        this.idAlbum = value;
    }

    public String getNombreAlbum() {
        return nombreAlbum;
    }

    public void setNombreAlbum(String value) {
        this.nombreAlbum = value;
    }

    public String getNombreCompletoArtista() {
        return nombreCompletoArtista;
    }

    public void setNombreCompletoArtista(String value) {
        this.nombreCompletoArtista = value;
    }

}