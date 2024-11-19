package espotify.DataTypes;

public class DTTemaConURL extends DTTemaGenerico {
    //atributos
    private String urlTema;
    //constructor

    public DTTemaConURL(String nombreTema, int duracionSegundos, int posicionEnAlbum, String urlTema) {
        super(nombreTema, duracionSegundos, posicionEnAlbum);
        this.urlTema=urlTema;
    }  

    public String getUrlTema() {
        return urlTema;
    }

    public void setUrlTema(String urlTema) {
        this.urlTema = urlTema;
    }    

    @Override
    public String toString() {
        return (
            "Nombre: " + this.getNombreTema() + 
                ", Duración: " + this.duracionToString() + 
                ", Posición: " + this.getPosicionEnAlbum() + 
                ", URL: " + this.getUrlTema());
    }
}
