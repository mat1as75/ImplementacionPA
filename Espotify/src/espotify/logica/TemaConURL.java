package espotify.logica;

import javax.persistence.Entity;

@Entity
public class TemaConURL extends Tema {
    //atributos
    private String urlTema;

    //constructor
    public TemaConURL(){
        super();
    }
    
    public TemaConURL(
            String nombreTema,
            int duracionSegundos,
            int posicionEnAlbum,
            String urlTema, 
            Album album
            ) {
        super(nombreTema, duracionSegundos, posicionEnAlbum, album);
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
