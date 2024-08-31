package espotify.logica;

import java.net.URL;
import java.time.Duration;
import javax.persistence.Entity;

@Entity
public class TemaConURL extends Tema {
    //atributos
    private URL urlTema;

    //constructor
    public TemaConURL(
            Long idTema,
            URL urlTema, 
            String nombreTema, 
            Duration duracion, 
            int posicionEnAlbum) {
        super(idTema, nombreTema, duracion, posicionEnAlbum);
        this.urlTema = urlTema;
    }

    //getters y setters
    public URL getUrlTema() {
        return urlTema;
    }

    public void setUrlTema(URL urlTema) {
        this.urlTema = urlTema;
    }
    
    //metodos
}
