package espotify.logica;

import java.io.Serializable;
import java.time.Duration;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class Tema implements Serializable {

    //atributos
    @Id
    protected Long idTema;
    protected String nombreTema;
    protected Duration duracion;
    protected int posicionEnAlbum;

    //constructor
    Tema(
            Long idTema,
            String nombreTema, 
            Duration duracion, 
            int posicionEnAlbum) {
        this.idTema = idTema;
        this.nombreTema = nombreTema;
        this.duracion = duracion;
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

    public  Duration getDuracion() {
        return duracion;
    }

    public void setDuracion(Duration duracion) {
        this.duracion = duracion;
    }
    
    public int getPosicionEnAlbum() {
        return posicionEnAlbum;
    }

    public void setPosicionEnAlbum(int posicionEnAlbum) {
        this.posicionEnAlbum = posicionEnAlbum;
    }
    //metodos
}
