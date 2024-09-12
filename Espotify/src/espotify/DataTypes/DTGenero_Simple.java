package espotify.DataTypes;

import java.util.List;

public class DTGenero_Simple {
    private String nombreGenero;
    private String nombreGeneroPadre;
    private List<String> subgeneros;

    public DTGenero_Simple() {};

    public DTGenero_Simple(String nombreGenero, String nombreGeneroPadre, List<String> subgeneros) {
        this.nombreGenero = nombreGenero;
        this.nombreGeneroPadre = nombreGeneroPadre;
        this.subgeneros = subgeneros;
    }

    public String getNombreGenero() {
        return nombreGenero;
    }

    public void setNombreGenero(String nombreGenero) {
        this.nombreGenero = nombreGenero;
    }

    public String getNombreGeneroPadre() {
        return nombreGeneroPadre;
    }

    public void setNombreGeneroPadre(String nombreGeneroPadre) {
        this.nombreGeneroPadre = nombreGeneroPadre;
    }    

    public List<String> getSubgeneros() {
        return this.subgeneros;
    }

    public void setSubgeneros(List<String> subgeneros) {
        this.subgeneros = subgeneros;
    }
}
