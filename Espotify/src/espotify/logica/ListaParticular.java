
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
    
    // Cosntructores
    public ListaParticular(){
        
    }
    public ListaParticular(String nombreLista, Image fotoLista, Cliente miCliente, boolean soyPrivada){
         super(nombreLista, fotoLista);
         this.miCliente = miCliente;
         this.soyPrivada = soyPrivada;
    }

    // Getters & Setters
    public Cliente getCliente(){
        return this.miCliente;
    }
    public void setMiCliente(Cliente miCliente){
        this.miCliente = miCliente;
    }
    
    public boolean soyPrivada() {
        return this.soyPrivada;
    }

    public void setsoyPrivada(boolean soyPrivada) {
        this.soyPrivada = soyPrivada;
    }
}
