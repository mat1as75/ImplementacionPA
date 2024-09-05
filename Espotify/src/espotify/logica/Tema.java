package espotify.logica;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class Tema implements Serializable {

    //atributos
    
    //temporal para que no tire error o advertencia, 
    //el identificador debe ser una clave compuesta por el nombre del tema y el nombre del album
    @Id
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
            Long idTema,
            String nombreTema, 
            int duracionSegundos,
            int posicionEnAlbum) {
        this.idTema = idTema;
        this.nombreTema = nombreTema;
        this.duracionSegundos = duracionSegundos;
        this.posicionEnAlbum = posicionEnAlbum;
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
    //metodos
}
