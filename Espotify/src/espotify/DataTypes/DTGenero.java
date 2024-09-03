package espotify.DataTypes;

import java.util.List;

public class DTGenero {
private String nombreGenero;
private DTGenero miPadre;
private List<DTAlbum>misAlbumes;
private List<DTParticulares> misListasParticulares;

    public DTGenero() {
    }

    public DTGenero(String nombreGenero) {
        this.nombreGenero = nombreGenero;
    }

    public String getNombreGenero() {
        return nombreGenero;
    }

    public DTGenero getMiPadre() {
        return miPadre;
    }

    public List<DTAlbum> getMisAlbumes() {
        return misAlbumes;
    }

    public List<DTParticulares> getMisListasParticulares() {
        return misListasParticulares;
    }

    public void setNombreGenero(String nombreGenero) {
        this.nombreGenero = nombreGenero;
    }

    public void setMiPadre(DTGenero miPadre) {
        this.miPadre = miPadre;
    }

    public void setMisAlbumes(List<DTAlbum> misAlbumes) {
        this.misAlbumes = misAlbumes;
    }
    public void setMiAlbume(DTAlbum misAlbumes) {
        this.misAlbumes.addFirst(misAlbumes);
    }

    public void setMisListasParticulares(List<DTParticulares> misListasParticulares) {
        this.misListasParticulares = misListasParticulares;
    }
    public void setMiListaParticulare(DTParticulares misListasParticulares) {
        this.misListasParticulares.addFirst(misListasParticulares);
    }
    
}
