package espotify.DataTypes;

import java.util.ArrayList;
import java.util.List;

public class DTAlbum {
    
private Long idAlbum;
private String nombreAlbum;
private int anioCreacion;
private String fotoAlbum;
private Boolean estaDisponible;
private ArrayList<DTTemaGenerico> misTemas = new ArrayList<>();

private ArrayList<DTTemaSimple> misTemasSimples = new ArrayList<>();

private ArrayList<DTGenero>misgeneros = new ArrayList<>();
private DTArtista miArtista;

    public DTAlbum() {
    }

    public DTAlbum(Long idAlbum, String nombreAlbum, int anioCreacion, String fotoAlbum, DTArtista miartista) {
        this.idAlbum = idAlbum;
        this.nombreAlbum = nombreAlbum;
        this.anioCreacion = anioCreacion;
        this.fotoAlbum = fotoAlbum;
        this.miArtista = miartista;
    }
    
    public DTAlbum(
            Long idAlbum, 
            String nombreAlbum, 
            int anioCreacion, 
            String fotoAlbum, 
            DTArtista miartista,
            Boolean disponibilidad) {
        this.idAlbum = idAlbum;
        this.nombreAlbum = nombreAlbum;
        this.anioCreacion = anioCreacion;
        this.fotoAlbum = fotoAlbum;
        this.miArtista = miartista;
        this.estaDisponible = disponibilidad;
    }

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

    public ArrayList<DTTemaGenerico> getMisTemas() {
        return misTemas;
    }

    
    public ArrayList<DTTemaSimple> getMisTemasSimple() {
        return misTemasSimples;

    }

    public List<DTGenero> getMisgeneros() {
        return misgeneros;
    }

    public DTArtista getMiArtista() {
        return miArtista;
    }

    public void setIdAlbum(Long idAlbum) {
        this.idAlbum = idAlbum;
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

    public void setMisTemas(ArrayList<DTTemaGenerico> misTemas) {
        this.misTemas = misTemas;
    }

    
    public void setMisTemasSimples(ArrayList<DTTemaSimple> misTemas) {
        this.misTemasSimples = misTemas;
    }

    public void setMiTema(DTTemaGenerico misTemas) {
        this.misTemas.addFirst(misTemas);
    }

    public void setMisgeneros(ArrayList<DTGenero> misgeneros) {
        this.misgeneros = misgeneros;
    }
    public void setMigenero(DTGenero misgeneros) {
        this.misgeneros.add(misgeneros);
    }
    public void setMiArtista(DTArtista miArtista) {
        this.miArtista = miArtista;
    }

    public Boolean getEstaDisponible() {
        return estaDisponible;
    }

    public void setEstaDisponible(Boolean estaDisponible) {
        this.estaDisponible = estaDisponible;
    }
    
    
    
    public String toStringSimple() {
        return ("Id: " + this.getIdAlbum() + 
                ", Nombre: " + this.getNombreAlbum() +
                ", Año: " + this.getAnioCreacion());
    }
}
