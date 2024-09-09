package espotify.logica;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class TemaID implements Serializable {
    
    private AlbumID albumID;
    private String nombreTema;

    public TemaID() {};
    
    public TemaID(AlbumID albumID, String nombreTema) {
        this.albumID = albumID;
        this.nombreTema = nombreTema;
    }
    
    public AlbumID getAlbumID() {
        return albumID;
    }

    public void setAlbumID(AlbumID albumID) {
        this.albumID = albumID;
    }

    public String getNombreTema() {
        return nombreTema;
    }

    public void setNombreTema(String nombreTema) {
        this.nombreTema = nombreTema;
    }
    
    
    
}
