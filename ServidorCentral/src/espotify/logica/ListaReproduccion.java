
package espotify.logica;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class ListaReproduccion implements Serializable {
    
    // Atributos
    @Id
    protected String nombreLista;
    protected String fotoLista;
 
    // Referencias
    @ManyToMany(mappedBy="misReproducciones")
    protected List<Tema> misTemas;
     
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
    
    public void setMisTemas(List<Tema> temas) {
        this.misTemas = temas;
    }
    
    public void agregarTema(Tema tema) {
        this.misTemas.add(tema);
    }
    
    public void removerTema(Tema tema) {
        this.misTemas.remove(tema);

    }
    
}
