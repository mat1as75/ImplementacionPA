package espotify.DataTypes;
import java.net.URL;



import java.net.URL;

public class DTTemaConURL extends DTTema {
    //atributos
    private URL urlTema;

    //constructor
    public DTTemaConURL(Long idTema,URL urlTema,String nombreTema,int duracion,int posicionEnAlbum) {
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
