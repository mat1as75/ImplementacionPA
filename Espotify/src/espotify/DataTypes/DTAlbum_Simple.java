
package espotify.DataTypes;

import java.util.List;

public class DTAlbum_Simple {
    
private Long idAlbum;
private String nombreAlbum;
private int anioCreacion;
private String nombreCompletoArtista;

    public DTAlbum_Simple() {
    }

    public DTAlbum_Simple(Long idAlbum, String nombreAlbum, int anioCreacion, String nombreCompletoArtista) {
        this.idAlbum = idAlbum;
        this.nombreAlbum = nombreAlbum;
        this.anioCreacion = anioCreacion;
        this.nombreCompletoArtista = nombreCompletoArtista;
    }

    public Long getIdAlbum() {
        return this.idAlbum;
    }
    
    public String getNombreAlbum() {
        return nombreAlbum;
    }

    public int getAnioCreacion() {
        return anioCreacion;
    }

    public String getNombreCompletoArtista() {
        return this.nombreCompletoArtista;
    }
    
    public void setIdAlbum(Long idAlbum) {
        this.idAlbum = idAlbum;
    }
    
    public void setNombreAlbum(String nombreAlbum) {
        this.nombreAlbum = nombreAlbum;
    }

    public void setAnioCreacion(int anioCreacion) {
        this.anioCreacion = anioCreacion;
    }
    
    public void setNombreCompletoArtista(String nombreCompletoArtista) {
        this.nombreCompletoArtista = nombreCompletoArtista;
    }
    
    public String datosToString() {
        return ("[" + this.getIdAlbum() + 
                "] " + this.getNombreAlbum() +
                " (" + this.getAnioCreacion() +
                ") de " + this.getNombreCompletoArtista());
    }
}

