package espotify.logica;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class ListaPorDefecto extends ListaReproduccion{

    // Atributos
    @ManyToOne
    private Genero miGenero;

    // Constructores
    public ListaPorDefecto(){
        super();
    }

    public ListaPorDefecto(String nombreLista, String fotoLista, Genero miGenero){
        super(nombreLista, fotoLista);
        this.miGenero = miGenero;
    }

    public ListaPorDefecto(String nombreLista, String fotoLista, Date fechaCreacion, List<Tema> misTemas, Genero miGenero) {
        super(nombreLista, fotoLista, fechaCreacion, misTemas);
        this.miGenero = miGenero;
    }

    // Getters & Setters
    public Genero getGenero(){
        return miGenero;
    }

    public void setGenero(Genero miGenero){
        this.miGenero = miGenero;
    }
}