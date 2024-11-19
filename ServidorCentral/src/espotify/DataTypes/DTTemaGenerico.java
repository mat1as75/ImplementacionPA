package espotify.DataTypes;

import java.util.List;

public class DTTemaGenerico {

    protected String nombreTema;
    protected int duracionSegundos;
    protected int posicionEnAlbum;
    protected List<DTListaReproduccion> mislistasReproducc;
    protected DTAlbum miAlbum;
    
    public DTTemaGenerico(){};
    
    public DTTemaGenerico(String nombreTema, int duracionSegundos, int posicionEnAlbum) {
        this.nombreTema = nombreTema;
        this.duracionSegundos = duracionSegundos;
        this.posicionEnAlbum = posicionEnAlbum;
    }

    public String getNombreTema() {
        return nombreTema;
    }

    public int getDuracionSegundos() {
        return duracionSegundos;
    }

    public int getPosicionEnAlbum() {
        return posicionEnAlbum;
    }

    public List<DTListaReproduccion> getMislistasReproducc() {
        return mislistasReproducc;
    }

    public DTAlbum getMiAlbum() {
        return miAlbum;
    }
    
    
    public String duracionToString() {
        return String.format("%02d:%02d", this.getDuracionSegundos() / 60,this.getDuracionSegundos() % 60);   
    }

    public void setNombreTema(String nombreTema) {
        this.nombreTema = nombreTema;
    }

    public void setDuracionSegundos(int duracionSegundos) {
        this.duracionSegundos = duracionSegundos;
    }

    public void setPosicionEnAlbum(int posicionEnAlbum) {
        this.posicionEnAlbum = posicionEnAlbum;
    }

    public void setMislistasReproducc(List<DTListaReproduccion> mislistasReproducc) {
        this.mislistasReproducc = mislistasReproducc;
    }
    public void setMilistasReproducc(DTListaReproduccion mislistasReproducc) {
        this.mislistasReproducc.addFirst(mislistasReproducc);
    }

    public void setMiAlbum(DTAlbum miAlbum) {
        this.miAlbum = miAlbum;
    }
    
}
