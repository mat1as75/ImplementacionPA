package espotify.DataTypes;

import java.util.List;

public class DTAlbum_SinDTArtista {
private String nombreAlbum;
private int anioCreacion;
private String fotoAlbum;
private List<DTTemaConTipo> misTemas;
private List<DTGenero>misGeneros;
private String miArtista;
private Boolean estaDisponible;

    public DTAlbum_SinDTArtista() {
    }

    public DTAlbum_SinDTArtista(
            String nombreAlbum, 
            int anioCreacion, 
            String fotoAlbum, 
            List<DTTemaConTipo> misTemas,
            List<DTGenero> misGeneros,
            String miArtista
        ) {
        this.nombreAlbum = nombreAlbum;
        this.anioCreacion = anioCreacion;
        this.fotoAlbum = fotoAlbum;
        this.misTemas = misTemas;
        this.misGeneros = misGeneros;
        this.miArtista = miArtista;
    }
    
    public DTAlbum_SinDTArtista(
            String nombreAlbum, 
            int anioCreacion, 
            String fotoAlbum, 
            List<DTTemaConTipo> misTemas,
            List<DTGenero> misGeneros,
            String miArtista,
            Boolean disponibilidad
        ) {
        this.nombreAlbum = nombreAlbum;
        this.anioCreacion = anioCreacion;
        this.fotoAlbum = fotoAlbum;
        this.misTemas = misTemas;
        this.misGeneros = misGeneros;
        this.miArtista = miArtista;
        this.estaDisponible = disponibilidad;
    }

    public String getNombreAlbum() {
        return nombreAlbum;
    }

    public int getAnioCreacion() {
        return anioCreacion;
    }

    public String getFotoAlbum() {
        return fotoAlbum;
    }

    public List<DTTemaConTipo> getMisTemas() {
        return misTemas;
    }

    public List<DTGenero> getMisgeneros() {
        return misGeneros;
    }

    public String getMiArtista() {
        return miArtista;
    }

    public void setNombreAlbum(String nombreAlbum) {
        this.nombreAlbum = nombreAlbum;
    }

    public void setAnioCreacion(int anioCreacion) {
        this.anioCreacion = anioCreacion;
    }

    public void setFotoAlbum(String fotoAlbum) {
        this.fotoAlbum = fotoAlbum;
    }

    public void setMisTemas(List<DTTemaConTipo> misTemas) {
        this.misTemas = misTemas;
    }

    public void setMisgeneros(List<DTGenero> misgeneros) {
        this.misGeneros = misgeneros;
    }

    public void setMiArtista(String miArtista) {
        this.miArtista = miArtista;
    }

    public Boolean getEstaDisponible() {
        return estaDisponible;
    }

    public void setEstaDisponible(Boolean estaDisponible) {
        this.estaDisponible = estaDisponible;
    }
    
}
