/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package espotify.logica;

import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity
public class Artista extends Usuario{
    
    // Atributos
    private String biografia;
    private String dirSitioWeb;
    
    // Referencias
    @OneToMany (mappedBy="miArtista")
    private List<Album> misAlbumesPublicados;
    
    // Cosntructores
    public Artista() {
        
    }
    public Artista(String nickname, String nombreUsuario, String apellidoUsuario, String email, Date fecNac, String fotoPerfil, String biografia, String dirSitioWeb) {
        super( nickname, nombreUsuario, apellidoUsuario, email, fecNac, fotoPerfil );
        this.biografia = biografia;
        this.dirSitioWeb = dirSitioWeb;
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
    
    /*public List<Album> getMisAlbumesPublicados() {
        return this.misAlbumesPublicados;
    }
    public void setMisAlbumesPublicados(Album a) {
        this.misAlbumesPublicados.addFirst(a);
    }*/
    
    
}
