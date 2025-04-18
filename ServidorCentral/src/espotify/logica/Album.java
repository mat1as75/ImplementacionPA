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
import espotify.DataTypes.DTTemaSimple;
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
    
    //un album esta disponible si su artista esta activo 
    //y deja de estar activo cuando el artista se da de baja
    public Boolean estaDisponible() {
        return this.miArtista.getActivo();
    }

    public DTAlbum getDataAlbum() {
        List<DTGenero> auxDTG = new ArrayList<>();
        List<DTTemaGenerico> auxDTTG = new ArrayList<>();
        List<DTTemaSimple> auxDTTS = new ArrayList<>();
        DTTemaConRuta dttcr;
        DTTemaSimple dtts;
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
            dtts = t.getDTTemaSimple();
            auxDTTS.add(dtts);
            if(t.getClass().equals(TemaConURL.class)){
                 dttcu = new DTTemaConURL(t.getNombreTema(),t.getDuracionSegundos(),t.getPosicionEnAlbum(),t.getCantidadDescargasOVisitas(),t.getCantidadFavoritos(),t.getCantidadReproducciones(),((TemaConURL)t).getUrlTema());
                auxDTTG.add(dttcu);
            }else{
                dttcr = new DTTemaConRuta(((TemaConRuta)t).getRutaTema(),t.getNombreTema(),t.getDuracionSegundos(),t.getPosicionEnAlbum(),t.getCantidadDescargasOVisitas(),t.getCantidadFavoritos(),t.getCantidadReproducciones());
                auxDTTG.add(dttcr);
            }
           
        }
        dta.setMisTemas(auxDTTG);

        dta.setMisTemasSimples(auxDTTS);
        
        
        dta.setEstaDisponible(this.estaDisponible());

        return dta;
    }
    
    public DTAlbum_Simple getDTAlbumSimple() {
        return new DTAlbum_Simple(
                this.getIdAlbum(),
                this.getNombreAlbum(),
                this.getAnioCreacion(),
                this.getMiArtista().getNombreCompletoToString(),
                this.getMisGenerosString(),
                this.estaDisponible()
        );
    }
    
    public DTAlbum getDTAlbumConTemasYGeneros() {
        DTAlbum dtAlbum = new DTAlbum(
                this.getIdAlbum(),
                this.getNombreAlbum(),
                this.getAnioCreacion(),
                this.getFotoAlbum(),
                this.miArtista.getDTArtista(),
                this.estaDisponible());
        
        ArrayList<DTTemaSimple> dtTemas = new ArrayList();
        for (Tema t : this.misTemas) {
            dtTemas.add(t.getDTTemaSimple());
        }
        
        ArrayList<DTGenero> dtGeneros = new ArrayList();
        for (Genero g : this.misGeneros) {
            dtGeneros.add(new DTGenero(g.getNombreGenero()));
        }
        
        dtAlbum.setMisTemasSimples(dtTemas);
        dtAlbum.setMisgeneros(dtGeneros);
        
        return dtAlbum;
    }
}
