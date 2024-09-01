package espotify.logica;

import javax.persistence.Entity;

@Entity
public class TemaConRuta extends Tema {
    //atributos
    private String rutaTema;
    
    //constructor
    public TemaConRuta(
            Long idTema,
            String rutaTema, 
            String nombreTema,
            int duracionSegundos,
            int posicionEnAlbum) {
        super(idTema, nombreTema, duracionSegundos, posicionEnAlbum);
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
