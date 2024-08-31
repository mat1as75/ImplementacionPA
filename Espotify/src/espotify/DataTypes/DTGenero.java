package espotify.DataTypes;

import java.util.List;

public class DTGenero {
private String nombreGenero;
private DTGenero miPadre;
private List<DTAlbum>misAlbumes;
private List<DTListaReproduccion> misListasParticulares;

    public DTGenero() {
    }

    public DTGenero(String nombreGenero) {
        this.nombreGenero = nombreGenero;
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

    public void setMisListasParticulares(List<DTListaReproduccion> misListasParticulares) {
        this.misListasParticulares = misListasParticulares;
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

    public List<DTListaReproduccion> getMisListasParticulares() {
        return misListasParticulares;
    }

}
