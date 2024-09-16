package espotify.logica;

import espotify.DataTypes.DTTemaSimple;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
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
    @ManyToMany(cascade = CascadeType.MERGE)
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
    
    public DTTemaSimple getDTTemaSimple() {
        
        String nombreArtista = "";
        String nombreAlbum = "";
        
        if (this.getMiAlbum() != null && this.getMiAlbum().getMiArtista() != null) {
            nombreArtista = this.getMiAlbum().getMiArtista().getNombreCompletoToString();
            nombreAlbum = this.getMiAlbum().getNombreAlbum();
        }
        
        return new DTTemaSimple(
                this.getIdTema(),
                this.getNombreTema(),
                this.getDuracionSegundos(),
                this.getPosicionEnAlbum(),
                nombreAlbum,
                nombreArtista
        );
    }
}
