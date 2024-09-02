package espotify.DataTypes;
import java.net.URL;



import java.net.URL;

public class DTTemaConURL extends DTTemaGenerico {
    //atributos
    private String urlTema;
    //constructor

    public DTTemaConURL(String nombreTema, int duracionSegundos, int posicionEnAlbum, String urlTema) {
        super(nombreTema, duracionSegundos, posicionEnAlbum);
        this.urlTema=urlTema;
    }  
  

    //metodos
}
