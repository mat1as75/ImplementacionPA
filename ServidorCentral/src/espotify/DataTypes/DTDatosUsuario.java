/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package espotify.DataTypes;

import java.util.ArrayList;
import java.util.Date;

public class DTDatosUsuario {
    // Atributos
    protected String nicknameUsuario;
    protected String nombreUsuario;
    protected String apellidoUsuario;
    protected String contrasenaUsuario;
    protected String email;
    protected Date fecNac;
    protected String fotoPerfil;
    // Referencia
    protected ArrayList<String> nicknamesSeguidores;

    public DTDatosUsuario() {
    }

    public DTDatosUsuario(String nicknameUsuario, String nombreUsuario, 
            String apellidoUsuario, String contrasenaUsuario, String email, 
            Date fecNac, String fotoPerfil, ArrayList<String> nicknamesSeguidores) {
        this.nicknameUsuario = nicknameUsuario;
        this.nombreUsuario = nombreUsuario;
        this.apellidoUsuario = apellidoUsuario;
        this.contrasenaUsuario = contrasenaUsuario;
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

    public String getContrasenaUsuario() {
        return contrasenaUsuario;
    }

    public void setContrasenaUsuario(String contrasenaUsuario) {
        this.contrasenaUsuario = contrasenaUsuario;
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

    public void setSeguidores(ArrayList<String> nicknamesSeguidores) {
        this.nicknamesSeguidores = nicknamesSeguidores;
    }
    
    
}
