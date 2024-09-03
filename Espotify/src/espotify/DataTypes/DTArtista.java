package espotify.DataTypes;

import java.util.Date;
import java.util.List;

public class DTArtista extends DTUsuario{
        // Atributos
    private String biografia;
    private String dirSitioWeb;
    
    // Referencias
    //private List<Album> misAlbumesPublicados;
    
    // Cosntructores
    public DTArtista() {
        
    }
    public DTArtista(String nickname, String nombreUsuario, String apellidoUsuario, String email, Date fecNac, String fotoPerfil, List<DTUsuario> misSeguidores, String biografia, String dirSitioWeb) {
        super(nickname,nombreUsuario,apellidoUsuario,email,fecNac,fotoPerfil);
        this.biografia = biografia;
        this.dirSitioWeb = dirSitioWeb;
    }

    public String getBiografia() {
        return biografia;
    }

    public String getDirSitioWeb() {
        return dirSitioWeb;
    }

    public List<DTUsuario> getMisSeguidores() {
        return misSeguidores;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public void setDirSitioWeb(String dirSitioWeb) {
        this.dirSitioWeb = dirSitioWeb;
    }

    public void setMisSeguidores(List<DTUsuario> misSeguidores) {
        this.misSeguidores = misSeguidores;
    }
    
}
