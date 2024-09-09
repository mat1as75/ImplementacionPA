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
            String nombreTema, 
            int duracionSegundos,
            int posicionEnAlbum,
            Album album,
            String rutaTema) {
        super(nombreTema, duracionSegundos, posicionEnAlbum, album);
        this.rutaTema = rutaTema;
    }
   
    
    //getters y setters
    public String getRutaTema() {
        return rutaTema;
    }

    public void setRutaTema(String rutaTema) {
        this.rutaTema = rutaTema;
    }
    
    //metodos
}
