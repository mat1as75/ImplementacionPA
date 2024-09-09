package espotify.logica;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Tema implements Serializable {

    @EmbeddedId
    protected TemaID idTema;
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
        
        this.idTema = new TemaID(album.getAlbumID(), nombreTema);
        this.duracionSegundos = duracionSegundos;
        this.posicionEnAlbum = posicionEnAlbum;
        this.miAlbum = album;
    }    

    //getters y setters
    public TemaID getIdTema() {
        return idTema;
    }
    
    public void setIdTema(TemaID idTema) {
        this.idTema = idTema;
    }
    
    public String getNombreTema() {
        return this.getIdTema().getNombreTema();
    }

    public void setNombreTema(String nombreTema) {
        this.idTema.setNombreTema(nombreTema);
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
    
    public Album getMiAlbum() {
        return this.miAlbum;
    }
    
    public void setMiAlbum(Album album) {
        this.miAlbum = album;
        this.idTema.setAlbumID(album.getAlbumID());
    }
}
