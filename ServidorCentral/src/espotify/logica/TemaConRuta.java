package espotify.logica;

import espotify.DataTypes.DTTemaGenericoConRutaOUrl;
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
    
    @Override
    public DTTemaGenericoConRutaOUrl getDTTemaConRutaOUrl() {
        return new DTTemaGenericoConRutaOUrl(
                this.getIdTema(),
                this.getNombreTema(),
                this.getRutaTema(),
                null
        );
    }
}
