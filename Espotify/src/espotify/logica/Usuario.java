/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package espotify.logica;

import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.*;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Usuario implements Serializable{
    
    // Atributos
    @Id
    protected String nickname;
    protected String nombreUsuario;
    protected String apellidoUsuario;
    protected String email;
    @Temporal(TemporalType.DATE)
    protected Date fecNac;
    protected String fotoPerfil;
    
    // Referencias
    @OneToMany /* Hace referencia a una relacion Unidireccional de 1 a N ( 1 -> N ) */
    protected List<Usuario> misSeguidores;
    
    // Contructores
    public Usuario() {
        
    }
    public Usuario(String nickname, String nombreUsuario, String apellidoUsuario, String email, Date fecNac, String fotoPerfil) {
        this.nickname = nickname;
        this.nombreUsuario = nombreUsuario;
        this.apellidoUsuario = apellidoUsuario;
        this.email = email;
        this.fecNac = fecNac;
        this.fotoPerfil = fotoPerfil; 
        this.misSeguidores = new ArrayList<Usuario>();
    }
    
    // Getters & Setters

    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
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

    public List<Usuario> getMisSeguidores() {
        return misSeguidores;
    }
    public void setListaMisSeguidores(List<Usuario> misSeguidores) {
        this.misSeguidores = misSeguidores;
    }
    public void setMisSeguidores(Usuario u) {
        this.misSeguidores.add(u);
    }
    public void setMisSeguidoresCompletos(List<Usuario> misSeguidores) {
        this.misSeguidores = misSeguidores;
    }
    
    public String getNombreCompletoToString() {
        return (this.getNombreUsuario() + " " + this.getApellidoUsuario());
    }
    
}
