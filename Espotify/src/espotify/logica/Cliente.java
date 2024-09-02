package espotify.logica;

import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "Clientes")
public class Cliente extends Usuario {
    
    // Referencias
    @OneToMany
    private List<Usuario> misSeguidos;
    
    //@ManyToMany /* ??? @ManyToMany(mappedBy="misAlbumesFav") -> En referencia set<Cliente> misClientesFav en Clase Album ??? */
    //private List<Album> misAlbumesFav;
    
    //@ManyToMany /* @ManyToMany(mappedBy="misTemasFav") -> En referencia set<Cliente> misClientesFav en Clase Tema */
    //private List<Tema> misTemasFav;
    
    //@ManyToMany /* @ManyToMany(mappedBy="misListasReproduccionFav") -> En referencia set<Cliente> misClientesFav en Clase ListaReproduccion */
    //private List<ListaReproduccion> misListasReproduccionFav;
    
    //@OneToMany /* @ManyToOne(mappedBy="misListasReproduccionCreadas") -> En referencia Cliente miClienteCrea en Clase ListaReproduccion->Particulares */
    //private List<ListaReproduccion> misListasReproduccionCreadas;
    
    // Constructores
    public Cliente() {
        
    }
    public Cliente(String nickname, String nombreUsuario, String apellidoUsuario, String email, Date fecNac, String fotoPerfil) {
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
