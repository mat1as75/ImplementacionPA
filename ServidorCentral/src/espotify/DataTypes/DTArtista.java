package espotify.DataTypes;

import java.util.Date;
import java.util.List;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class DTArtista extends DTUsuario{
        // Atributos
    private String biografia;
    private String dirSitioWeb;
    private Boolean activo;
    @Temporal(TemporalType.DATE)
    private Date fechaBaja;
    
    // Referencias
    private List<DTAlbum> misAlbumesPublicados;
    
    // Cosntructores
    public DTArtista() {
        
    }
    public DTArtista(String nickname, String contrasenaUsuario) {
        this.nickname = nickname;
        this.contrasenaUsuario = contrasenaUsuario;
    }
    public DTArtista(String nickname, String contrasenaUsuario, Boolean activo) {
        this.nickname = nickname;
        this.contrasenaUsuario = contrasenaUsuario;
        this.activo = activo;
    }
    public DTArtista(
            String nickname, 
            String nombreUsuario, 
            String apellidoUsuario, 
            String contrasenaUsuario, 
            String email, 
            Date fecNac, 
            String fotoPerfil, 
            List<DTUsuario> misSeguidores, 
            String biografia, 
            String dirSitioWeb, 
            Boolean activo, 
            Date fechaBaja, 
            List<DTAlbum> misAlbumesPublicados) {
        super(nickname,nombreUsuario,apellidoUsuario,contrasenaUsuario,email,fecNac,fotoPerfil);
        this.biografia = biografia;
        this.dirSitioWeb = dirSitioWeb;
        this.activo = activo;
        this.fechaBaja = fechaBaja;
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

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }
    
    public List<DTAlbum> getMisAlbumesPublicados() {
        return this.misAlbumesPublicados;
    }
}
