package espotify.DataTypes;
import java.nio.file.Path;


public class DTTemaConRuta extends DTTema {
    //atributos
    private String rutaTema;
    
    //constructor
    public DTTemaConRuta(Long idTema,String rutaTema,String nombreTema,int duracion,int posicionEnAlbum) {
        super(idTema, nombreTema, duracion, posicionEnAlbum);
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
