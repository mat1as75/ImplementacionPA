package espotify.DataTypes;

import espotify.logica.Usuario;
import java.util.Date;
import java.util.List;

public abstract class DTUsuario{
 // Atributos
    private String nickname;
    private String nombreUsuario;
    private String apellidoUsuario;
    private String email;
    private Date fecNac;
    private String fotoPerfil;
    // Referencia
    protected List<DTUsuario> misSeguidores;

    public DTUsuario() {
    }

    public DTUsuario(String nickname, String nombreUsuario, String apellidoUsuario, String email, Date fecNac, String fotoPerfil) {
        this.nickname = nickname;
        this.nombreUsuario = nombreUsuario;
        this.apellidoUsuario = apellidoUsuario;
        this.email = email;
        this.fecNac = fecNac;
        this.fotoPerfil = fotoPerfil;
    }

    public String getNickname() {
        return nickname;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getApellidoUsuario() {
        return apellidoUsuario;
    }

    public String getEmail() {
        return email;
    }

    public Date getFecNac() {
        return fecNac;
    }

    public String getFotoPerfil() {
        return fotoPerfil;
    }

    public List<DTUsuario> getMisSeguidores() {
        return misSeguidores;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public void setApellidoUsuario(String apellidoUsuario) {
        this.apellidoUsuario = apellidoUsuario;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFecNac(Date fecNac) {
        this.fecNac = fecNac;
    }

    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public void setMisSeguidores(List<DTUsuario> misSeguidores) {
        this.misSeguidores = misSeguidores;
    }
    
}