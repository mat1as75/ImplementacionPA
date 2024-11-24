package espotify.logica;

import espotify.DataTypes.DTTemaGenericoConRutaOUrl;
import espotify.DataTypes.DTTemaSimple;
import espotify.DataTypes.DTTemaConPuntaje;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Tema implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long idTema;
    protected String nombreTema;
    protected int duracionSegundos;
    protected int posicionEnAlbum;
    protected Long cantidadReproducciones;
    protected Long cantidadDescargasOVisitas;
    protected Long cantidadFavoritos;
   
    @ManyToOne
    protected Album miAlbum;
    @ManyToMany(cascade = CascadeType.MERGE)
    protected List<ListaReproduccion>misReproducciones;
    
    //constructor
    public Tema(){};
    public Tema(
            String nombreTema, 
            int duracionSegundos,
            int posicionEnAlbum,
            Album album) {
        this.nombreTema = nombreTema;
        this.duracionSegundos = duracionSegundos;
        this.posicionEnAlbum = posicionEnAlbum;
        this.miAlbum = album;
        this.misReproducciones = null;
        this.cantidadDescargasOVisitas = 0L;
        this.cantidadReproducciones = 0L;
        this.cantidadFavoritos = 0L;
    }    

    //getters y setters
    public Long getIdTema() {
        return idTema;
    }
    
    public void setIdTema(Long id) {
        this.idTema = id;
    }
    
    public String getNombreTema() {
        return nombreTema;
    }

    public void setNombreTema(String nombreTema) {
        this.nombreTema = nombreTema;
    }
    
    public int getDuracionSegundos() {
        return this.duracionSegundos;
    }
    
    public void setDuracionSegundos(int duracionSegundos) {
        this.duracionSegundos = duracionSegundos;
    }
    
    public int getPosicionEnAlbum() {
        return posicionEnAlbum;
    }

    public void setPosicionEnAlbum(int posicionEnAlbum) {
        this.posicionEnAlbum = posicionEnAlbum;
    }
    
    public void setMiAlbum(Album album) {
        this.miAlbum = album;
    }
    
    public Album getMiAlbum() {
        return this.miAlbum;
    }
    
    public Boolean removerListaReproduccion(String nombreLista) {
        for (ListaReproduccion lr : misReproducciones) {
            if (lr.getNombreLista().equals(nombreLista)) {
                misReproducciones.remove(lr);
                return true;
            }
        }
        return false;
    }
    
    public void desvincularListasDeReproduccion() {
        this.misReproducciones.clear();
    }
    
    public List<ListaReproduccion> getMisReproducciones() {
        return misReproducciones;
    }
    
    public void setMisReproducciones(ListaReproduccion lr) {
        this.misReproducciones.add(lr);
    }

    public Long getCantidadReproducciones() {
        return cantidadReproducciones;
    }

    public void setCantidadReproducciones(Long cantidadReproducciones) {
        this.cantidadReproducciones = cantidadReproducciones;
    }

    public Long getCantidadDescargasOVisitas() {
        return cantidadDescargasOVisitas;
    }

    public void setCantidadDescargasOVisitas(Long cantidadDescargasOVisitas) {
        this.cantidadDescargasOVisitas = cantidadDescargasOVisitas;
    }

    public Long getCantidadFavoritos() {
        return cantidadFavoritos;
    }

    public void setCantidadFavoritos(Long cantidadFavoritos) {
        this.cantidadFavoritos = cantidadFavoritos;
    }
    
    public void incrementarCantidadFavoritos() {
        this.cantidadFavoritos++;
    }
    
    public void decrementarCantidadFavoritos() {
        this.cantidadFavoritos--;
    }
    
    public void incrementarReproducciones() {
        this.cantidadReproducciones++;
    }
    
    public void incrementarDescargasOVisitas() {
        this.cantidadDescargasOVisitas++;
    }
    
    public Float getPuntajeTema() {
        int cantidadDeListas = this.getMisReproducciones() == null 
            ? 0 
            : this.getMisReproducciones().size();
    
        Float puntaje = this.getCantidadDescargasOVisitas() * 0.2f 
                + this.getCantidadReproducciones() * 0.3f
                + cantidadDeListas * 0.2f
                + this.getCantidadFavoritos() * 0.3f;
        
        return puntaje;
    }
    
    public DTTemaSimple getDTTemaSimple() {
        
        String nombreArtista = "";
        String nombreAlbum = "";
        Long idAlbum = null;
        List<String> generosDeTema = new ArrayList<>();
        
        if (this.getMiAlbum() != null && this.getMiAlbum().getMiArtista() != null) {
            nombreArtista = this.getMiAlbum().getMiArtista().getNombreCompletoToString();
            nombreAlbum = this.getMiAlbum().getNombreAlbum();
            idAlbum = this.getMiAlbum().getIdAlbum();
            generosDeTema = this.getMiAlbum().getMisGenerosString();
        }
        
        return new DTTemaSimple(
                this.getIdTema(),
                this.getNombreTema(),
                this.getDuracionSegundos(),
                this.getPosicionEnAlbum(),
                nombreAlbum,
                idAlbum,
                nombreArtista,
                generosDeTema,
                this.getCantidadReproducciones(),
                this.getCantidadDescargasOVisitas(),
                this.getCantidadFavoritos(),
                this.getPuntajeTema()
        );
    }
    
    public DTTemaSimple getDTTema() {
        return new DTTemaSimple(
                this.getIdTema(),
                this.getNombreTema(),
                this.getMiAlbum().getIdAlbum()
        );
    }
    
    public DTTemaConPuntaje getDTTemaConPuntaje() {
        return new DTTemaConPuntaje(
                this.getIdTema(),
                this.getNombreTema(),
                this.getDuracionSegundos(),
                this.getPosicionEnAlbum(),
                this.getMiAlbum().getIdAlbum(),
                this.getMiAlbum().getNombreAlbum(),
                this.getMiAlbum().getMiArtista().getNombreCompletoToString(),
                this.getMiAlbum().getMisGenerosString(),
                this.getPuntajeTema(),
                this.getCantidadReproducciones(),
                this.getCantidadDescargasOVisitas(),
                this.getCantidadFavoritos(),
                Long.valueOf(this.getMisReproducciones().size())
        );
    }
    
    public abstract DTTemaGenericoConRutaOUrl getDTTemaConRutaOUrl();
}
