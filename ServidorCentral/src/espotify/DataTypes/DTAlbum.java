package espotify.DataTypes;

import java.util.List;

public class DTAlbum {
    
private Long idAlbum;
private String nombreAlbum;
private int anioCreacion;
private String fotoAlbum;
private Boolean estaDisponible;
private List<DTTemaGenerico> misTemas;
private List<DTTemaSimple> misTemasSimples;
private List<DTGenero>misgeneros;
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

    public DTAlbum(Long idAlbum, String nombreAlbum, int anioCreacion, String fotoAlbum, Boolean estaDisponible, List<DTTemaGenerico> misTemas, List<DTTemaSimple> misTemasSimples, List<DTGenero> misgeneros, DTArtista miArtista) {
        this.idAlbum = idAlbum;
        this.nombreAlbum = nombreAlbum;
        this.anioCreacion = anioCreacion;
        this.fotoAlbum = fotoAlbum;
        this.estaDisponible = estaDisponible;
        this.misTemas = misTemas;
        this.misTemasSimples = misTemasSimples;
        this.misgeneros = misgeneros;
        this.miArtista = miArtista;
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

    public List<DTTemaGenerico> getMisTemas() {
        return misTemas;
    }

    
    public List<DTTemaSimple> getMisTemasSimple() {
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

    public void setMisTemas(List<DTTemaGenerico> misTemas) {
        this.misTemas = misTemas;
    }

    
    public void setMisTemasSimples(List<DTTemaSimple> misTemas) {
        this.misTemasSimples = misTemas;
    }

    public void setMiTema(DTTemaGenerico misTemas) {
        this.misTemas.addFirst(misTemas);
    }

    public void setMisgeneros(List<DTGenero> misgeneros) {
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

    public List<DTTemaSimple> getMisTemasSimples() {
        return misTemasSimples;
    }
    
    
    
    public String toStringSimple() {
        return ("Id: " + this.getIdAlbum() + 
                ", Nombre: " + this.getNombreAlbum() +
                ", Año: " + this.getAnioCreacion());
    }
}
