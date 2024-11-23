package espotify.logica;

import espotify.DataTypes.DTDatosUsuario;
import espotify.DataTypes.DTDatosUsuarioSinPw;
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
    protected String contrasenaUsuario;
    protected String email;
    @Temporal(TemporalType.DATE)
    protected Date fecNac;
    protected String fotoPerfil;
    
    // Referencias
    @OneToMany(cascade = CascadeType.MERGE) /* Hace referencia a una relacion Unidireccional de 1 a N ( 1 -> N ) */
    @JoinTable(name = "usuario_seguidores",
            joinColumns = @JoinColumn(name = "nickname_Usuario"),
            inverseJoinColumns = @JoinColumn(name = "nickname_Seguidor"))
    protected List<Usuario> misSeguidores = null;
    
    // Contructores
    public Usuario() {
        
    }
    public Usuario(String nickname, String nombreUsuario, String apellidoUsuario, String contrasenaUsuario, String email, Date fecNac, String fotoPerfil) {
        this.nickname = nickname;
        this.nombreUsuario = nombreUsuario;
        this.apellidoUsuario = apellidoUsuario;
        this.contrasenaUsuario = contrasenaUsuario;
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
    
    public String getContrasenaUsuario() {
        return this.contrasenaUsuario;
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
    public void desvincularSeguidores() {
        this.misSeguidores.clear();
    }
    public String getNombreCompletoToString() {
        return (this.getNombreUsuario() + " " + this.getApellidoUsuario());
    }
    
    public DTDatosUsuarioSinPw getDtDatosUsuarioSinPw() {
        ArrayList<String> nicknamesSeguidores = new ArrayList();
        for (Usuario u : this.getMisSeguidores()) {
            nicknamesSeguidores.add(u.getNickname());
        }
                
        return new DTDatosUsuarioSinPw(
                this.getNickname(),
                this.getNombreUsuario(),
                this.getApellidoUsuario(),
                this.getEmail(),
                this.getFecNac(),
                this.getFotoPerfil(),
                nicknamesSeguidores
        );
    }
}
