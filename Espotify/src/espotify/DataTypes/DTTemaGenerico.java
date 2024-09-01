package espotify.DataTypes;

public class DTTemaGenerico {

    public String nombreTema;
    public int duracionSegundos;
    public int posicionEnAlbum;
    public String urlTema;
    public String rutaTema;
    
    public DTTemaGenerico(String nombreTema, int duracionSegundos, int posicionEnAlbum, String urlTema, String rutaTema) {
        this.nombreTema = nombreTema;
        this.duracionSegundos = duracionSegundos;
        this.posicionEnAlbum = posicionEnAlbum;
        this.urlTema = urlTema;
        this.rutaTema = rutaTema;
    }
    
    public String getNombreTema() {
        return nombreTema;
    }

    public void setNombreTema(String nombreTema) {
        this.nombreTema = nombreTema;
    }

    public int getDuracionSegundos() {
        return duracionSegundos;
    }

    public void setDuracionSegundos(int duracionSegundos) {
        this.duracionSegundos = duracionSegundos;
    }

    public int getPosicionEnAlbum() {
        return posicionEnAlbum;
    }

    public void setPosicionEnAlbum(int posicionEnAlbum) {
        this.posicionEnAlbum = posicionEnAlbum;
    }

    public String getUrlTema() {
        return urlTema;
    }

    public void setUrlTema(String urlTema) {
        this.urlTema = urlTema;
    }

    public String getRutaTema() {
        return rutaTema;
    }

    public void setRutaTema(String rutaTema) {
        this.rutaTema = rutaTema;
    }
    
    public String duracionToString() {
        return String.format("%02d:%02d", this.getDuracionSegundos() / 60,this.getDuracionSegundos() % 60);   
    }

    @Override
    public String toString() {
        return "Nombre: " + nombreTema + ", Duracion: " + this.duracionToString() + ", Posicion en Album:" + posicionEnAlbum + ", URL: " + urlTema;
    }
    
    
}
