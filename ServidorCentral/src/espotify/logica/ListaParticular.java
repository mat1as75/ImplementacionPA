package espotify.logica;


import java.util.Date;
import espotify.DataTypes.DTDatosListaReproduccion;
import espotify.DataTypes.DTTemaSimple;
import java.util.ArrayList;
import java.util.List;
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
    
    public ListaParticular(String nombreLista, String fotoLista, Cliente miCliente, Date fechaCreacion, List<Tema> misTemas, boolean soyPrivada){
         super(nombreLista, fotoLista, fechaCreacion, misTemas);
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
    
    public void setMiClienteNull() {
        this.miCliente = null;
    }
    
    public DTDatosListaReproduccion getDTDatosListaReproduccion() {
        List<DTTemaSimple> listaDtTemas = new ArrayList();

        for (Tema tema : this.getMisTemas()) {
            listaDtTemas.add(tema.getDTTemaSimple());
        }

        return new DTDatosListaReproduccion(
        this.getNombreLista(),
        this.getFotoLista(),
        "ListaParticular",
                fechaCreacion,
        listaDtTemas,
        this.getCliente().getNickname(),
        this.soyPrivada()
        );
    }
}