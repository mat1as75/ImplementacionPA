package espotify.DataTypes;

import java.util.Date;
import java.util.List;

public class DTArtista extends DTUsuario{
        // Atributos
    private String biografia;
    private String dirSitioWeb;
    
    // Referencias
    private List<DTAlbum> misAlbumesPublicados;
    
    // Cosntructores
    public DTArtista() {
        
    }
    public DTArtista(
            String nickname, 
            String nombreUsuario, 
            String apellidoUsuario, 
            String email, 
            Date fecNac, 
            String fotoPerfil, 
            List<DTUsuario> misSeguidores, 
            String biografia, 
            String dirSitioWeb, 
            List<DTAlbum> misAlbumesPublicados) {
        super(nickname,nombreUsuario,apellidoUsuario,email,fecNac,fotoPerfil);
        this.biografia = biografia;
        this.dirSitioWeb = dirSitioWeb;
        this.misAlbumesPublicados = misAlbumesPublicados;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public void setDirSitioWeb(String dirSitioWeb) {
        this.dirSitioWeb = dirSitioWeb;
    }

    public void setMisAlbumesPublicados(List<DTAlbum> misAlbumesPublicados) {
        this.misAlbumesPublicados = misAlbumesPublicados;
    }
    public void setMiAlbumePublicado(DTAlbum misAlbumesPublicados) {
        this.misAlbumesPublicados.addFirst(misAlbumesPublicados);
    }

    public String getBiografia() {
        return this.biografia;
    }

    public String getDirSitioWeb() {
        return this.dirSitioWeb;
    }

    public List<DTAlbum> getMisAlbumesPublicados() {
        return this.misAlbumesPublicados;
    }
}
