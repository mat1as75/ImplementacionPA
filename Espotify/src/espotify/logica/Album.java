/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package espotify.logica;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author brisa
 */
@Entity
public class Album implements Serializable {

    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAlbum;
    private String nombreAlbum;
    private int anioCreacion;
    private String fotoAlbum;
    
    //Relaciones
    
    @OneToMany(mappedBy="miAlbum", cascade = {CascadeType.PERSIST})
    private List<Tema> misTemas;
    
    @ManyToMany(cascade = CascadeType.MERGE)
    private List<Genero> misGeneros;
    
    @ManyToOne(cascade = CascadeType.MERGE)
    private Artista miArtista;
    

     // Constructores
    public Album() {
        
    }
    public Album(String nomAlbum, int anioCreado, String foto, Artista artista, List<Tema> temas, List<Genero> generos) {
        this.nombreAlbum = nomAlbum;
        this.anioCreacion = anioCreado;
        this.fotoAlbum = foto;
        this.misTemas = temas;
        this.misGeneros = generos;
        this.miArtista = artista;
        
    }
    
    //Setters y Getters
    public Long getIdAlbum() {
        return this.idAlbum;
    }
    
    public String getNombreAlbum() {
        return nombreAlbum;
    }

    public int getAnioCreacion() {
        return anioCreacion;
    }

    public String getFotoAlbum() {
        return fotoAlbum;
    }

    public List<Tema> getMisTemas() {
        return misTemas;
    }

    public List<Genero> getMisGeneros() {
        return misGeneros;
    }

    public Usuario getMiArtista() {
        return miArtista;
    }

    public void setNombreAlbum(String nombreAlbum) {
        this.nombreAlbum = nombreAlbum;
    }

    public void setAnioCreacion(int anioCreacion) {
        this.anioCreacion = anioCreacion;
    }

    public void setFotoAlbum(String fotoAlbum) {
        this.fotoAlbum = fotoAlbum;
    }

    public void setNuevoTema(Tema tema) {
        this.misTemas.addFirst(tema);
    }
    public void setMisTemas(List<Tema> misTemas) {
        this.misTemas = misTemas;
    }

    public void setMisGeneros(List<Genero> misGeneros) {
        this.misGeneros = misGeneros;
    }
    
    public void setNuevoGenero(Genero genero) {
        this.misGeneros.addFirst(genero);
    }
    
    public void setMiArtista(Artista artista) {
        this.miArtista = artista;
    }
    
}
