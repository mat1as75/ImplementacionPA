/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package espotify.logica;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Genero implements Serializable{
    
    // Atributos
    @Id
    private String nombreGenero;
    
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
    
}
