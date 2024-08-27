package logica;

import java.time.Duration;

public abstract class Tema {

    //atributos
    protected String nombreTema;
    protected Duration duracion;
    protected int posicionEnAlbum;
    protected FormaDeAccesoTema formaDeAcceso;
    protected Genero generoTema;
    
    //constructor
    public Tema(String nombreTema, Duration duracion, int posicionEnAlbum, FormaDeAccesoTema formaDeAcceso, Genero generoTema) {
        this.nombreTema = nombreTema;
        this.duracion = duracion;
        this.posicionEnAlbum = posicionEnAlbum;
        this.formaDeAcceso = formaDeAcceso;
        this.generoTema = generoTema;
    }    

    //getters y setters
    public String getNombreTema() {
        return nombreTema;
    }

    public void setNombreTema(String nombreTema) {
        this.nombreTema = nombreTema;
    }

    public  Duration getDuracion() {
        return duracion;
    }

    public void setDuracion(Duration duracion) {
        this.duracion = duracion;
    }

    public FormaDeAccesoTema getFormaDeAcceso() {
        return formaDeAcceso;
    }

    public void setFormaDeAcceso(FormaDeAccesoTema formaDeAcceso) {
        this.formaDeAcceso = formaDeAcceso;
    }
    
    public int getPosicionEnAlbum() {
        return posicionEnAlbum;
    }

    public void setPosicionEnAlbum(int posicionEnAlbum) {
        this.posicionEnAlbum = posicionEnAlbum;
    }
    
    public Genero getGeneroTema() {
        return generoTema;
    }

    public void setGeneroTema(Genero generoTema) {
        this.generoTema = generoTema;
    }
    
    //metodos
}
