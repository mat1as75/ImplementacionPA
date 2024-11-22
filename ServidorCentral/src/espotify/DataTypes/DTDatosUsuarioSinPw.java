package espotify.DataTypes;

import java.util.ArrayList;
import java.util.Date;

public class DTDatosUsuarioSinPw {
    private String nicknameUsuario;
    private String nombreUsuario;
    private String apellidoUsuario;
    private String email;
    private Date fecNac;
    private String fotoPerfil;
    private ArrayList<String> nicknamesSeguidores;

    public DTDatosUsuarioSinPw() {};
    
    public DTDatosUsuarioSinPw(
            String nicknameUsuario, 
            String nombreUsuario, 
            String apellidoUsuario, 
            String email, 
            Date fecNac, 
            String fotoPerfil, 
            ArrayList<String> nicknamesSeguidores) {
        this.nicknameUsuario = nicknameUsuario;
        this.nombreUsuario = nombreUsuario;
        this.apellidoUsuario = apellidoUsuario;
        this.email = email;
        this.fecNac = fecNac;
        this.fotoPerfil = fotoPerfil;
        this.nicknamesSeguidores = nicknamesSeguidores;
    }

    public String getNicknameUsuario() {
        return nicknameUsuario;
    }

    public void setNicknameUsuario(String nicknameUsuario) {
        this.nicknameUsuario = nicknameUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getApellidoUsuario() {
        return apellidoUsuario;
    }

    public void setApellidoUsuario(String apellidoUsuario) {
        this.apellidoUsuario = apellidoUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFecNac() {
        return fecNac;
    }

    public void setFecNac(Date fecNac) {
        this.fecNac = fecNac;
    }

    public String getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public ArrayList<String> getNicknamesSeguidores() {
        return nicknamesSeguidores;
    }

    public void setNicknamesSeguidores(ArrayList<String> nicknamesSeguidores) {
        this.nicknamesSeguidores = nicknamesSeguidores;
    }
    
    
}
