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
import javax.persistence.OneToMany;

@Entity
public class Genero implements Serializable{
    
    // Atributos
    @Id
    private String nombreGenero;
    @ManyToOne
    private Genero miPadre;
    @ManyToMany(mappedBy="misGeneros")
    private List<Album> misAlbumes;
    @OneToMany(mappedBy="miGenero")
    private List<ListaPorDefecto> misListasParticulares;
    @OneToMany
    private List<Genero> misGenerosHijos;
    
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
    
    public List<Genero> getMisSubgeneros() {
        return this.misGenerosHijos;
    }
    
    public void setMisSubgeneros(List<Genero> misSubgeneros) {
        this.misGenerosHijos = misSubgeneros;
    }
    
    public void agregarGeneroHijo(Genero nuevoGeneroHijo) {
        this.misGenerosHijos.add(nuevoGeneroHijo);
    }
    
    public List<Album> getMisAlbumes() {
        return this.misAlbumes;
    }
    
    public void setMisAlbumes(Album a) {
        this.misAlbumes.addFirst(a);
    }
   
    public List<ListaPorDefecto> getMisListasReproduccion() {
        return this.misListasParticulares;
    }
    public void setMisListasReproduccion(ListaPorDefecto lr) {
        this.misListasParticulares.addFirst(lr);
    }
}
