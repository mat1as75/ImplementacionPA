
package espotify.logica;

import java.awt.Image;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;


@Entity
public class ListaParticular extends ListaReproduccion{
     // Atributos
    @ManyToOne
    private Cliente miCliente;
    private boolean soyPrivada;
    
    // Constructores
    public ListaParticular(){
        
    }
    public ListaParticular(String nombreLista, Image fotoLista, Cliente miCliente, boolean soyPrivada){
         super(nombreLista, fotoLista);
         this.miCliente = miCliente;
         this.soyPrivada = soyPrivada;
    }

    // Getters & Setters
    public Cliente getCliente(){
        return miCliente;
    }
    public void setMiCliente(Cliente miCliente){
        this.miCliente = miCliente;
    }
    
    public boolean soyPrivada() {
        return soyPrivada;
    }

    public void setsoyPrivada(boolean soyPrivada) {
        this.soyPrivada = soyPrivada;
    }
}
