package espotify.logica;

import javax.persistence.Entity;

@Entity
public class TemaConRuta extends Tema {
    //atributos
    private String rutaTema;
    
    //constructor
    public TemaConRuta(){
        super();
    };
    public TemaConRuta(
            String rutaTema, 
            String nombreTema,
            int duracionSegundos,
            int posicionEnAlbum,
            Album album) {
        super(nombreTema, duracionSegundos, posicionEnAlbum, album);
        this.rutaTema = rutaTema;
    }
   
    public String getRutaTema() {
        return rutaTema;
    }

    public void setRutaTema(String rutaTema) {
        this.rutaTema = rutaTema;
    }

}
