/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package espotify.logica;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
//@Table(name = "Generos")
public class Genero implements Serializable{
    
    // Atributos
    @Id
    private String nombreGenero;
    @ManyToOne
    private Genero miPadre;
    //@ManyToMany(mappedBy="misGeneros") /* @ManyToMany En referencia set<Genero> misGeneros de Clase Album*/
    //private List<Album> misAlbumes;
    /* @ManyToOne En referencia Genero miGenero de Clase ListaReproduccion->PorDefecto */
    //private List<ListaReproduccion> misListasParticulares;
    
    // Constructores
    public Genero() {
        
    }
    public Genero(String nombreGenero) {
        this.nombreGenero = nombreGenero;
    }
    
    // Getters & Setters
    public String getNombreGenero() {
        return this.nombreGenero;
    }
    public void setNombreGenero(String nombreGenero) {
        this.nombreGenero = nombreGenero;
    }
    
    public Genero getMiPadre() {
        return this.miPadre;
    }
    public void setMiPadre(Genero miPadre) {
        this.miPadre = miPadre;
    }
    /*
    public List<Genero> getMisAlbumes() {
        return this.misAlbumes;
    }
    public void setMisAlbumes(Album a) {
        this.misAlbumes.addFirst(a);
    }
    
    public List<ListaReproduccion> getMisListasReproduccion() {
        return this.misListasReproduccion;
    }
    public void setMisListasReproduccion(ListaReproduccion lr) {
        this.misListasReproduccion.addFirst(lr);
    }
    */
}
