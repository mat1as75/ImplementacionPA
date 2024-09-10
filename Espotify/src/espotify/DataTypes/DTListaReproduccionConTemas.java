package espotify.DataTypes;

import java.util.List;

public class DTListaReproduccionConTemas {
    private String nombreLista;
    private String fotoLista;
    private String tipoDeLista;
    private List<DTTemaGenerico> misTemas;

    public DTListaReproduccionConTemas(String nombreLista, String fotoLista, String tipoDeLista, List<DTTemaGenerico> misTemas) {
        this.nombreLista = nombreLista;
        this.fotoLista = fotoLista;
        this.tipoDeLista = tipoDeLista;
        this.misTemas = misTemas;
    }

    public String getNombreLista() {
        return nombreLista;
    }

    public void setNombreLista(String nombreLista) {
        this.nombreLista = nombreLista;
    }

    public String getFotoLista() {
        return fotoLista;
    }

    public void setFotoLista(String fotoLista) {
        this.fotoLista = fotoLista;
    }

    public String getTipoDeLista() {
        return tipoDeLista;
    }

    public void setTipoDeLista(String tipoDeLista) {
        this.tipoDeLista = tipoDeLista;
    }

    public List<DTTemaGenerico> getMisTemas() {
        return misTemas;
    }

    public void setMisTemas(List<DTTemaGenerico> misTemas) {
        this.misTemas = misTemas;
    }
}
