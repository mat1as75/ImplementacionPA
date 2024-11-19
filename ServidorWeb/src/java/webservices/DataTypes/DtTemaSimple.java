
package webservices.DataTypes;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtTemaSimple", propOrder = {
    "idTema",
    "nombreTema",
    "duracionSegundos",
    "posicionEnAlbum",
    "nombreAlbum",
    "idAlbum",
    "nombreCompletoArtista",
    "generosDeTema",
    "cantidadReproducciones",
    "cantidadDescargasOVisitas",
    "cantidadFavoritos",
    "puntajeTema"})
public class DtTemaSimple {
    protected Long idTema;
    protected String nombreTema;
    protected int duracionSegundos;
    protected int posicionEnAlbum;
    protected String nombreAlbum;
    protected Long idAlbum;
    protected String nombreCompletoArtista;
    @XmlElement(nillable = true)
    protected List<String> generosDeTema;
    protected Long cantidadReproducciones;
    protected Long cantidadDescargasOVisitas;
    protected Long cantidadFavoritos;
    protected float puntajeTema;

    public Long getCantidadDescargasOVisitas() {
        return cantidadDescargasOVisitas;
    }

    public void setCantidadDescargasOVisitas(Long value) {
        this.cantidadDescargasOVisitas = value;
    }

    public Long getCantidadFavoritos() {
        return cantidadFavoritos;
    }

    public void setCantidadFavoritos(Long value) {
        this.cantidadFavoritos = value;
    }

    public Long getCantidadReproducciones() {
        return cantidadReproducciones;
    }

    public void setCantidadReproducciones(Long value) {
        this.cantidadReproducciones = value;
    }

    public int getDuracionSegundos() {
        return duracionSegundos;
    }

    public void setDuracionSegundos(int value) {
        this.duracionSegundos = value;
    }

    public List<String> getGenerosDeTema() {
        if (generosDeTema == null) {
            generosDeTema = new ArrayList<String>();
        }
        return this.generosDeTema;
    }

    public Long getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(Long value) {
        this.idAlbum = value;
    }

    public Long getIdTema() {
        return idTema;
    }

    public void setIdTema(Long value) {
        this.idTema = value;
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

    public float getPuntajeTema() {
        return puntajeTema;
    }

    public void setPuntajeTema(float value) {
        this.puntajeTema = value;
    }
}
