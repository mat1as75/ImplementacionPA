package logica;

import java.net.URL;
import java.time.Duration;

public class TemaConURL extends Tema {
    //atributos
    private URL urlTema;

    //constructor
    public TemaConURL(URL urlTema, String nombreTema, Duration duracion, int posicionEnAlbum, FormaDeAccesoTema formaDeAcceso, Genero generoTema) {
        super(nombreTema, duracion, posicionEnAlbum, formaDeAcceso, generoTema);
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
