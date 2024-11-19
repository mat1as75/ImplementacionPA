package espotify.DataTypes;

public class DTTemaConTipo {
    private String tipo; //'ruta' o 'url'
    private String nombreTema;
    private int duracionSegundos;
    private int posicionEnAlbum;
    private String rutaOurl;

    public DTTemaConTipo() {};
    
    public DTTemaConTipo(String tipo, String nombreTema, int duracionSegundos, int posicionEnAlbum, String rutaOurl) {
        this.tipo = tipo;
        this.nombreTema = nombreTema;
        this.duracionSegundos = duracionSegundos;
        this.posicionEnAlbum = posicionEnAlbum;
        this.rutaOurl = rutaOurl;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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

    public String getRutaOurl() {
        return rutaOurl;
    }

    public void setRutaOurl(String rutaOurl) {
        this.rutaOurl = rutaOurl;
    }    
}
