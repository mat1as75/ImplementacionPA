/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package espotify.logica;

import espotify.DataTypes.DTCliente;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.*;

@Entity
public class Cliente extends Usuario{
    
    // Referencias
    @OneToMany(cascade = CascadeType.MERGE) /* Hace referencia a una relacion Unidireccional de 1 a N ( 1 -> N ) */
    @JoinTable(name = "cliente_seguidos",
            joinColumns = @JoinColumn(name = "nickname_Cliente"),
            inverseJoinColumns = @JoinColumn(name = "nickname_usuario"))
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
    
    @OneToOne
    @JoinColumn(name = "idSuscripcion")
    private Suscripcion miSuscripcion;

    // Constructores
    public Cliente() {
        
    }
    public Cliente(String nickname, String nombreUsuario, String apellidoUsuario, String contrasenaUsuario, String email, Date fecNac, String fotoPerfil) {
        super( nickname, nombreUsuario, apellidoUsuario, contrasenaUsuario, email, fecNac, fotoPerfil );
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
    
    public Map<Long, String> getAlbumsFavMap(){
        Map<Long, String> misAlbumsFav = new HashMap<>();
        for(Album a : this.misAlbumesFav){
            misAlbumsFav.put(a.getIdAlbum(), a.getNombreAlbum());
        }
        return misAlbumsFav;
    }
    
    public void setMisAlbumesFav(Album a) {
        this.misAlbumesFav.addFirst(a);
    }
    
    public List<Tema> getMisTemasFav() {
        return this.misTemasFav;
    }
    
    public Map<Long, String>  getTemasFavMap(){
        Map<Long, String>  misTemasF = new HashMap<>();
        for(Tema t : this.misTemasFav){
            misTemasF.put(t.getIdTema(), t.getNombreTema());
        }
        return misTemasF;
    }
    
    public void setMisTemasFav(Tema t) {
        this.misTemasFav.addFirst(t);
    }
    
    public void setMisTemasFavLista(List<Tema> temas){
        this.misTemasFav = temas;
    }
    
    public List<ListaReproduccion> getMisListasReproduccionFav() {
        return this.misListasReproduccionFav;
    }
    
    public ArrayList<String> getListasFavString(){
        ArrayList<String> misListasFav = new ArrayList<>();
        for(ListaReproduccion lr : this.misListasReproduccionFav){
            misListasFav.addLast(lr.getNombreLista());
        }
        return misListasFav;
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
           if(lp.getNombreLista().equals(lista)){
               lp.setsoyPrivada(false);
           }
       }
    }

    public Suscripcion getMiSuscripcion() {
        return miSuscripcion;
    }

    public void setMiSuscripcion(Suscripcion miSuscripcion) {
        this.miSuscripcion = miSuscripcion;
    }

    public DTCliente getDTCliente() {
        return new DTCliente(
                this.getNickname(),
                this.getNombreUsuario(),
                this.getApellidoUsuario(),
                this.getContrasenaUsuario(), 
                this.getEmail(),
                this.getFecNac(),
                this.getFotoPerfil()
        );
    }
}
