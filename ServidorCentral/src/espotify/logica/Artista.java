/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package espotify.logica;

import espotify.DataTypes.DTArtista;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity
public class Artista extends Usuario{
    
    // Atributos
    @Lob
    private String biografia;
    private String dirSitioWeb;
    private Boolean activo;
    @Temporal(TemporalType.DATE)
    private Date fechaBaja;
    
    // Referencias
    @OneToMany (mappedBy="miArtista")
    private List<Album> misAlbumesPublicados;
    
    // Cosntructores
    public Artista() {
        
    }
    public Artista(String nickname, String nombreUsuario, String apellidoUsuario, String contrasenaUsuario, String email, Date fecNac, String fotoPerfil, String biografia, String dirSitioWeb) {
        super( nickname, nombreUsuario, apellidoUsuario, contrasenaUsuario, email, fecNac, fotoPerfil );
        this.biografia = biografia;
        this.dirSitioWeb = dirSitioWeb;
        this.activo = true;
        this.fechaBaja = null;
    }
    
    // Getters & Setters
    public String getBiografia() {
        return this.biografia;
    }
    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }
    
    public String getDirSitioWeb() {
        return this.dirSitioWeb;
    }
    public void setDirSitioWeb(String dirSitioWeb) {
        this.dirSitioWeb = dirSitioWeb;
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
    
    
    public List<Album> getMisAlbumesPublicados() {
        return this.misAlbumesPublicados;
    }
    public void setMisAlbumesPublicados(List<Album> albumes) {
        this.misAlbumesPublicados = albumes;
    }
    public void setMisAlbumesPublicados(Album a) {
        this.misAlbumesPublicados.addFirst(a);
    }
    public DTArtista getDTArtista(){
        DTArtista aux = new DTArtista();
        aux.setApellidoUsuario(this.getApellidoUsuario());
        aux.setBiografia(this.getBiografia());
        aux.setDirSitioWeb(this.getDirSitioWeb());
        aux.setEmail(this.getEmail());
        aux.setFecNac(this.getFecNac());
        aux.setFotoPerfil(this.getFotoPerfil());
        aux.setNombreUsuario(this.getNombreUsuario());
        aux.setNickname(this.getNickname());
        
        
        return aux;
    }
    
    
}
