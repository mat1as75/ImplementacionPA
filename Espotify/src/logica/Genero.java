package logica;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Genero implements Serializable  {
    //atributos
    @Id
    private String nombreGenero;
    private Genero generoPadre;
    
    //constructor
    Genero(String nombreGenero, Genero generoPadre) {
        this.nombreGenero = nombreGenero;
        this.generoPadre = generoPadre;
    }
    
    //getters y setters
    public String getNombreGenero() {
        return nombreGenero;
    }

    public void setNombreGenero(String nombreGenero) {
        this.nombreGenero = nombreGenero;
    }

    public Genero getGeneroPadre() {
        return generoPadre;
    }

    public void setGeneroPadre(Genero generoPadre) {
        this.generoPadre = generoPadre;
    }
    
}
