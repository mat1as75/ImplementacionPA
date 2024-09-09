package espotify.logica;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class AlbumID implements Serializable {
    
    private String nicknameArtista;
    private String nombreAlbum;

    public AlbumID(){};
    
    public AlbumID(String nicknameArtista, String nombreAlbum) {
        this.nicknameArtista = nicknameArtista;
        this.nombreAlbum = nombreAlbum;
    }
    
    public String getNicknameArtista() {
        return nicknameArtista;
    }

    public void setNicknameArtista(String nicknameArtista) {
        this.nicknameArtista = nicknameArtista;
    }

    public String getNombreAlbum() {
        return nombreAlbum;
    }

    public void setNombreAlbum(String nombreAlbum) {
        this.nombreAlbum = nombreAlbum;
    }
    
}
