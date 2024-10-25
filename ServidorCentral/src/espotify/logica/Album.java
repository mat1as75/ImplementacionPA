/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package espotify.logica;

import espotify.DataTypes.DTAlbum;
import espotify.DataTypes.DTAlbum_Simple;
import espotify.DataTypes.DTGenero;
import espotify.DataTypes.DTTemaConRuta;
import espotify.DataTypes.DTTemaConURL;
import espotify.DataTypes.DTTemaGenerico;
import java.io.Serializable;
import java.util.ArrayList;
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
        return this.nombreAlbum;
    }

    public int getAnioCreacion() {
        return this.anioCreacion;
    }

    public String getFotoAlbum() {
        return this.fotoAlbum;
    }

    public List<Tema> getMisTemas() {
        return this.misTemas;
    }

    public List<Genero> getMisGeneros() {
        return this.misGeneros;
    }

    public List<String> getMisGenerosString() {
        List<String> generos = new ArrayList();
        for (Genero g : this.getMisGeneros()) {
            generos.add(g.getNombreGenero());
        }
        return generos;
    }
    
    public Usuario getMiArtista() {
        return this.miArtista;
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

    public DTAlbum getDataAlbum() {
        ArrayList<DTGenero> auxDTG = new ArrayList<>();
        ArrayList<DTTemaGenerico> auxDTTG = new ArrayList<>();
        DTTemaConRuta dttcr;
        DTTemaConURL dttcu;
        DTAlbum dta = new DTAlbum(
                this.getIdAlbum(),
                this.getNombreAlbum(),
                this.getAnioCreacion(),
                this.getFotoAlbum(),
                ((Artista)this.getMiArtista()).getDTArtista()
        );
        for(Genero g: this.getMisGeneros()){
            DTGenero dtg = new DTGenero(g.getNombreGenero());
            auxDTG.add(dtg);
            
        }
        dta.setMisgeneros(auxDTG);
        
        for(Tema t: this.getMisTemas()){
            
            if(t.getClass().equals(TemaConURL.class)){
                 dttcu = new DTTemaConURL(t.getNombreTema(),t.getDuracionSegundos(),t.getPosicionEnAlbum(),((TemaConURL)t).getUrlTema());
                auxDTTG.add(dttcu);
            }else{
                dttcr = new DTTemaConRuta(((TemaConRuta)t).getRutaTema(),t.getNombreTema(),t.getDuracionSegundos(),t.getPosicionEnAlbum());
                auxDTTG.add(dttcr);
            }
           
        }
        dta.setMisTemas(auxDTTG);
        return dta;
    }
    
    public DTAlbum_Simple getDTAlbumSimple() {
        return new DTAlbum_Simple(
                this.getIdAlbum(),
                this.getNombreAlbum(),
                this.getAnioCreacion(),
                this.getMiArtista().getNombreCompletoToString(),
                this.getMisGenerosString()
        );
    }
}
