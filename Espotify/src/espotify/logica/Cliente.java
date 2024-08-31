/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package espotify.logica;

import java.awt.Image;
import java.util.List;
import javax.persistence.*;

@Entity
public class Cliente extends Usuario{
    
    // Referencias
    @OneToMany
    private List<Usuario> misSeguidos;
    //private List<Album> misAlbumesFav;
    //private List<Tema> misTemasFav;
    //private List<ListaReproduccion> misListasReproduccionFav;
    //private List<ListaReproduccion> misListasReproduccionCreadas;
    
    // Constructores
    public Cliente() {
        
    }
    public Cliente(String nickname, String nombreUsuario, String apellidoUsuario, String email, Date fecNac, Image fotoPerfil) {
        super( nickname, nombreUsuario, apellidoUsuario, email, fecNac, fotoPerfil );
    }
    
    // Getters & Setters
    public List<Usuario> getMisSeguidos() {
        return this.misSeguidos;
    }
    public void setMisSeguidos(Usuario u) {
        this.misSeguidos.addFirst(u);
    }
    /*
    public List<Album> getMisAlbumesFav() {
        return this.misAlbumesFav;
    }
    public void setMisAlbumesFav(Album a) {
        this.misAlbumesFav.addFirst(a);
    }
    
    public List<Tema> getMisTemasFav() {
        return this.misTemasFav;
    }
    public void setMisTemasFav(Tema t) {
        this.misTemasFav.addFirst(t);
    }
    
    public List<ListaReproduccion> getMisListasReproduccionFav() {
        return this.misListasReproduccionFav;
    }
    public void setMisListasReproduccionFav(ListaReproduccion lrf) {
        this.misListasReproduccionFav.addFirst(lrf);
    }
    
    public List<ListaReproduccion> getMisListasReproduccionCreadas() {
        return this.misListasReproduccionCreadas;
    }
    public void setMisListasReproduccionCreadas(ListaReproduccion lrc) {
        this.misListasReproduccionCreadas.addFirst(lrc);
    }*/
}
