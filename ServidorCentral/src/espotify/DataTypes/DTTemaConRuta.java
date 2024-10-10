package espotify.DataTypes;

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

    @Override
    public String toString() {
        return (
            "Nombre: " + this.getNombreTema() + 
                ", Duración: " + this.duracionToString() + 
                ", Posición: " + this.getPosicionEnAlbum() + 
                ", Ruta: " + this.getRutaTema());
    }
    
    
    
}
