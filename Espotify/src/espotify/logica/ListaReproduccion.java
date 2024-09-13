
package espotify.logica;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class ListaReproduccion implements Serializable {
    
    // Atributos
    @Id
    protected String nombreLista;
    protected String fotoLista;
    
    
    /*@JoinTable(name="LISTAREPRODUCCION_TEMA", 
            joinColumns=@JoinColumn(name="ListaR_Nombre"),
            inverseJoinColumns=@JoinColumn(name="Tema_Id"))*/
    @ManyToMany(mappedBy="misReproducciones")
    private List<Tema> misTemas;
    
    // Constructores
    public ListaReproduccion() {
       super(); 
    }
    
    public ListaReproduccion(String nombreLista, String fotoLista) {
        this.nombreLista = nombreLista;
        this.fotoLista = fotoLista;
    }
    
    // Getters & Setters
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
    
    public List<Tema> getMisTemas() {
        return misTemas;
    }
    
    public void setMisTemas(List<Tema> misTemas) {
        this.misTemas = misTemas;
    }
    
}
