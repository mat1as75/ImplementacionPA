package espotify.logica;

import java.nio.file.Path;
import java.time.Duration;
import javax.persistence.Entity;

@Entity
public class TemaConRuta extends Tema {
    //atributos
    private Path rutaTema;
    
    //constructor
    public TemaConRuta(
            Long idTema,
            Path rutaTema, 
            String nombreTema, 
            Duration duracion, 
            int posicionEnAlbum) {
        super(idTema, nombreTema, duracion, posicionEnAlbum);
        this.rutaTema = rutaTema;
    }
   
    
    //getters y setters
    public Path getRutaTema() {
        return rutaTema;
    }

    public void setRutaTema(Path rutaTema) {
        this.rutaTema = rutaTema;
    }
    
    //metodos
}
