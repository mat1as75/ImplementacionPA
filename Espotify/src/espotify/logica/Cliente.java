/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package espotify.logica;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity
public class Cliente extends Usuario{
    
    // Referencias
    @OneToMany /* Hace referencia a una relacion Unidireccional de 1 a N ( 1 -> N ) */
    private List<Usuario> misSeguidos;
    
    @ManyToMany /* Hace referencia a una relacion Unidireccional de N a N ( * --> * ) */
    @JoinTable(
            name = "cliente_albumes_fav",
            joinColumns = @JoinColumn(name = "nickname_Cliente"),
            inverseJoinColumns = @JoinColumn(name = "id_Album")
    )
    private List<Album> misAlbumesFav;
    
    @ManyToMany /* Hace referencia a una relacion Unidireccional de N a N ( * --> * ) */
    @JoinTable(
            name = "cliente_temas_fav",
            joinColumns = @JoinColumn(name = "nickname_Cliente"),
            inverseJoinColumns = @JoinColumn(name = "id_Tema")
    )
    private List<Tema> misTemasFav;
    
    @ManyToMany /* Hace referencia a una relacion Unidireccional de N a N ( * --> * ) */
    @JoinTable(
            name = "cliente_listasReproduccion_fav",
            joinColumns = @JoinColumn(name = "nickname_Cliente"),
            inverseJoinColumns = @JoinColumn(name = "nombre_ListaReproduccion")
    )
    private List<ListaReproduccion> misListasReproduccionFav;
    
    @ManyToMany
    @JoinTable(
            name = "cliente_listasReproduccion_creadas",
            joinColumns = @JoinColumn(name = "nickname_Cliente"),
            inverseJoinColumns = @JoinColumn(name = "nombre_ListaReproduccion")
    )
    private List<ListaParticular> misListasReproduccionCreadas;

    // Constructores
    public Cliente() {
        
    }
    public Cliente(String nickname, String nombreUsuario, String apellidoUsuario, String email, Date fecNac, String fotoPerfil) {
        super( nickname, nombreUsuario, apellidoUsuario, email, fecNac, fotoPerfil );
        misSeguidos=new ArrayList<Usuario>();
        misListasReproduccionCreadas=new ArrayList<ListaParticular>();
    }
    
    // Getters & Setters
    public List<Usuario> getMisSeguidos() {
        return this.misSeguidos;
    }
    public void setMisSeguidos(Usuario u) {
        this.misSeguidos.addFirst(u);
    }
    
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
    
    public List<ListaParticular> getMisListasReproduccionCreadas() {
        return this.misListasReproduccionCreadas;
    }
    public void setMisListasReproduccionCreadas(ListaParticular lrc) {
        this.misListasReproduccionCreadas.addFirst(lrc);
    }
    public void setMisListasReproduccionCreadasCompleta(List<ListaParticular> lrc) {
        this.misListasReproduccionCreadas = lrc;
    }
    public void setPrivadafalse(String lista){ 
       for(ListaParticular lp:misListasReproduccionCreadas){
           if(lp.getNombreLista()==lista){
               lp.setsoyPrivada(false);
           }
       
       }
    }
}
