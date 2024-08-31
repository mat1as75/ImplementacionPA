/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package espotify.logica;

import java.util.List;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
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
    @OneToMany
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
    }
    
    // Getters & Setters
    public String getNickname() {
        return this.nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    
    public String getNombreUsuarioo() {
        return this.nombreUsuario;
    }
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    
    public String getApellidoUsuario() {
        return this.apellidoUsuario;
    }
    public void setApellidoUsuario(String apellidoUsuario) {
        this.apellidoUsuario = apellidoUsuario;
    }
    
    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    
    public Date getFecNac() {
        return this.fecNac;
    }
    public void setFecNac(Date fecNac) {
        this.fecNac = fecNac;
    }
    
    public String getFotoPefil() {
        return this.fotoPerfil;
    }
    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }
    
    public List<Usuario> getMisSeguidores() {
        return this.misSeguidores;
    }
    public void setMisSeguidores(Usuario u) {
        this.misSeguidores.addFirst(u);
    }
    
}
