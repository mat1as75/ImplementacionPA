package espotify.DataTypes;
import java.nio.file.Path;


public class DTTemaConRuta extends DTTemaGenerico {
    //atributos
    private String rutaTema;
    
    //constructor

    public DTTemaConRuta(String rutaTema, String nombreTema, int duracionSegundos, int posicionEnAlbum) {
        super(nombreTema, duracionSegundos, posicionEnAlbum);
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
