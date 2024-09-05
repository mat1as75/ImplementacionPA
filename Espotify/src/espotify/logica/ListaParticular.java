
package espotify.logica;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;


@Entity
public class ListaParticular extends ListaReproduccion{
    
    // Atributos
    private boolean soyPrivada;
    @ManyToOne
    private Cliente miCliente;
    
    // Constructores
    public ListaParticular(){
        
    }
    
    public ListaParticular(String nombreLista, String fotoLista, Cliente miCliente, boolean soyPrivada){
         super(nombreLista, fotoLista);
         this.miCliente = miCliente;
         this.soyPrivada = soyPrivada;
    }

    // Getters & Setters
    public Cliente getMiCliente(){
        return miCliente;
    }
    
    public void setMiCliente(Cliente miCliente){
        this.miCliente = miCliente;
    }
    
    public boolean isSoyPrivada() {
        return soyPrivada;
    }

    public void setSoyPrivada(boolean soyPrivada) {
        this.soyPrivada = soyPrivada;
    }
}
