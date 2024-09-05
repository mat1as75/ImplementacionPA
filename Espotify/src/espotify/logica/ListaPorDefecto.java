
package espotify.logica;

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
    
    // Getters & Setters
    public Genero getGenero(){
        return miGenero;
    }
    
    public void setGenero(Genero miGenero){
        this.miGenero = miGenero;
    }
}
