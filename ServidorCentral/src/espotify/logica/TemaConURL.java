package espotify.logica;

import espotify.DataTypes.DTTemaGenericoConRutaOUrl;
import javax.persistence.Entity;

@Entity
public class TemaConURL extends Tema {
    
    private String urlTema;

    public TemaConURL(){
        super();
    }
    public TemaConURL(
            String urlTema, 
            String nombreTema, 
            int duracionSegundos,
            int posicionEnAlbum,
            Album album) {
        super(nombreTema, duracionSegundos, posicionEnAlbum, album);
        this.urlTema = urlTema;
    }

    public String getUrlTema() {
        return urlTema;
    }

    public void setUrlTema(String urlTema) {
        this.urlTema = urlTema;
    }
    
    @Override
    public DTTemaGenericoConRutaOUrl getDTTemaConRutaOUrl() {
        return new DTTemaGenericoConRutaOUrl(
                this.getIdTema(),
                this.getNombreTema(),
                null,
                this.getUrlTema(),
                this.getCantidadReproducciones(),
                this.getCantidadDescargasOVisitas(),
                this.getCantidadFavoritos(),
                this.getPuntajeTema()
        );
    }
    
}
