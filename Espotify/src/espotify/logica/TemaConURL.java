package espotify.logica;

import java.net.URL;
import java.time.Duration;
import javax.persistence.Entity;

@Entity
public class TemaConURL extends Tema {
    //atributos
    private String urlTema;

    //constructor
    public TemaConURL(
            Long idTema,
            String urlTema, 
            String nombreTema, 
            int duracionSegundos,
            int posicionEnAlbum) {
        super(idTema, nombreTema, duracionSegundos, posicionEnAlbum);
        this.urlTema = urlTema;
    }

    //getters y setters
    public String getUrlTema() {
        return urlTema;
    }

    public void setUrlTema(String urlTema) {
        this.urlTema = urlTema;
    }
    
    //metodos
}
