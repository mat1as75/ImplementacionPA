package espotify.DataTypes;

import java.util.List;

public class DTAlbum {
private String nombreAlbum;
private int anioCreacion;
private String fotoAlbum;
private List<DTTema> misTemas;
private List<DTGenero>misgeneros;
private DTArtista miArtista;

    public DTAlbum() {
    }

    public DTAlbum(String nombreAlbum, int anioCreacion, String fotoAlbum) {
        this.nombreAlbum = nombreAlbum;
        this.anioCreacion = anioCreacion;
        this.fotoAlbum = fotoAlbum;
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

    public List<DTTema> getMisTemas() {
        return misTemas;
    }

    public List<DTGenero> getMisgeneros() {
        return misgeneros;
    }

    public DTUsuario getMiArtista() {
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

    public void setMisTemas(List<DTTema> misTemas) {
        this.misTemas = misTemas;
    }

    public void setMisgeneros(List<DTGenero> misgeneros) {
        this.misgeneros = misgeneros;
    }

    public void setMiArtista(DTArtista miArtista) {
        this.miArtista = miArtista;
    }
    
    
}
