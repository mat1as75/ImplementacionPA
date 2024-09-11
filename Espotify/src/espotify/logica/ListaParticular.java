
package espotify.logica;

import javax.persistence.Entity;


@Entity
public class ListaParticular extends ListaReproduccion{
    
    // Atributos
    private boolean soyPrivada;
    private String nicknameCliente;
    // Constructores
    public ListaParticular(){
        
    }
    
    public ListaParticular(String nombreLista, String fotoLista, String nicknameCliente, boolean soyPrivada){
         super(nombreLista, fotoLista);
         this.nicknameCliente = nicknameCliente;
         this.soyPrivada = soyPrivada;
    }

    // Getters & Setters
    public String getNicknameCliente(){
        return nicknameCliente;
    }
    
    public void setNicknameCliente(String nicknameCliente){
        this.nicknameCliente = nicknameCliente;
    }
    
    public boolean soyPrivada() {
        return soyPrivada;
    }

    public void setsoyPrivada(boolean soyPrivada) {
        this.soyPrivada = soyPrivada;
    }
}
