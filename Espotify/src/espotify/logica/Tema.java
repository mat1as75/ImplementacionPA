package espotify.logica;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Tema implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long idTema;
    protected String nombreTema;
    protected int duracionSegundos;
    protected int posicionEnAlbum;
    @ManyToOne
    protected Album miAlbum;
    @ManyToMany
    protected List<ListaReproduccion>misReproducciones;
    
    //constructor
    public Tema(){};
    public Tema(
            String nombreTema, 
            int duracionSegundos,
            int posicionEnAlbum,
            Album album) {
        this.nombreTema = nombreTema;
        this.duracionSegundos = duracionSegundos;
        this.posicionEnAlbum = posicionEnAlbum;
        this.miAlbum = album;
    }    

    //getters y setters
    public Long getIdTema() {
        return idTema;
    }
    
    public void setIdTema(Long id) {
        this.idTema = id;
    }
    
    public String getNombreTema() {
        return nombreTema;
    }

    public void setNombreTema(String nombreTema) {
        this.nombreTema = nombreTema;
    }
    
    public int getDuracionSegundos() {
        return this.duracionSegundos;
    }
    
    public void setDuracionSegundos(int duracionSegundos) {
        this.duracionSegundos = duracionSegundos;
    }
    
    public int getPosicionEnAlbum() {
        return posicionEnAlbum;
    }

    public void setPosicionEnAlbum(int posicionEnAlbum) {
        this.posicionEnAlbum = posicionEnAlbum;
    }
    
    public void setMiAlbum(Album album) {
        this.miAlbum = album;
    }
    
    public Album getMiAlbum() {
        return this.miAlbum;
    }
    
    public List<ListaReproduccion> getMisReproducciones() {
        return misReproducciones;
    }
    
    public void setMisReproducciones(ListaReproduccion lr) {
        this.misReproducciones.add(lr);
    }
}
