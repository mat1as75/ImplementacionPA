package espotify.DataTypes;

import java.util.List;

public class DTTemaSimple {
    
    private Long idTema;
    private String nombreTema;
    private int duracionSegundos;
    private int posicionEnAlbum;
    private String nombreAlbum;
    private Long idAlbum;
    private String nombreCompletoArtista;
    private List<String> generosDeTema;
    private Long cantidadReproducciones;
    private Long cantidadDescargasOVisitas;
    private Long cantidadFavoritos;
    private float puntajeTema;

    
    public DTTemaSimple() {};
    
    public DTTemaSimple(Long idTema, String nombreTema, int duracionSegundos, int posicionEnAlbum, String nombreAlbum, String nombreCompletoArtista) {
        this.idTema = idTema;
        this.nombreTema = nombreTema;
        this.duracionSegundos = duracionSegundos;
        this.posicionEnAlbum = posicionEnAlbum;
        this.nombreAlbum = nombreAlbum;
        this.nombreCompletoArtista = nombreCompletoArtista;
    }
    
    public DTTemaSimple(Long idTema, String nombreTema, Long idAlbum) {
        this.idTema = idTema;
        this.nombreTema = nombreTema;
        this.idAlbum = idAlbum;
    }

    public DTTemaSimple(
            Long idTema, 
            String nombreTema, 
            int duracionSegundos, 
            int posicionEnAlbum, 
            String nombreAlbum, 
            String nombreCompletoArtista,
            List<String> generosDeTema
            ) {
        this.idTema = idTema;
        this.nombreTema = nombreTema;
        this.duracionSegundos = duracionSegundos;
        this.posicionEnAlbum = posicionEnAlbum;
        this.nombreAlbum = nombreAlbum;
        this.nombreCompletoArtista = nombreCompletoArtista;
        this.generosDeTema = generosDeTema;
    }
    
    //Con reproducciones/descargas/favoritos y puntaje
    public DTTemaSimple(
            Long idTema, 
            String nombreTema, 
            int duracionSegundos, 
            int posicionEnAlbum, 
            String nombreAlbum, 
            Long idAlbum,
            String nombreCompletoArtista,
            List<String> generosDeTema,
            Long cantidadReproducciones, 
            Long cantidadDescargasOVisitas,
            Long cantidadFavoritos,
            float puntaje
            ) {
        this.idTema = idTema;
        this.nombreTema = nombreTema;
        this.duracionSegundos = duracionSegundos;
        this.posicionEnAlbum = posicionEnAlbum;
        this.nombreAlbum = nombreAlbum;
        this.idAlbum = idAlbum;
        this.nombreCompletoArtista = nombreCompletoArtista;
        this.generosDeTema = generosDeTema;
        this.cantidadReproducciones = cantidadReproducciones;
        this.cantidadDescargasOVisitas = cantidadDescargasOVisitas;
        this.cantidadFavoritos = cantidadFavoritos;
        this.puntajeTema = puntaje;
    }
    
    public Long getIdTema() {
        return idTema;
    }

    public void setIdTema(Long idTema) {
        this.idTema = idTema;
    }
    
    public String getNombreTema() {
        return nombreTema;
    }

    public void setNombreTema(String nombreTema) {
        this.nombreTema = nombreTema;
    }

    public int getDuracionSegundos() {
        return duracionSegundos;
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

    public String getNombreAlbum() {
        return nombreAlbum;
    }

    public void setNombreAlbum(String nombreAlbum) {
        this.nombreAlbum = nombreAlbum;
    }
    
    public Long getIdAlbum() {
        return this.idAlbum;
    }

    public void setIdAlbum(Long idAlbum) {
        this.idAlbum = idAlbum;
    }

    public String getNombreCompletoArtista() {
        return nombreCompletoArtista;
    }

    public void setNombreCompletoArtista(String nombreCompletoArtista) {
        this.nombreCompletoArtista = nombreCompletoArtista;
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

    public float getPuntajeTema() {
        return puntajeTema;
    }

    public void setPuntajeTema(float puntajeTema) {
        this.puntajeTema = puntajeTema;
    }
    
    public String duracionToString() {
        return String.format("%02d:%02d", this.getDuracionSegundos() / 60,this.getDuracionSegundos() % 60);   
    }
    
    public String getDatosTemaToString() {
        return ("[" + this.getIdTema() 
                + "] " + this.getNombreTema() + 
                ", Album: " + this.getNombreAlbum() + 
                ", Artista: " + this.getNombreCompletoArtista());
    }

    public List<String> getGenerosDeTema() {
        return generosDeTema;
    }

    public void setGenerosDeTema(List<String> generosDeTema) {
        this.generosDeTema = generosDeTema;
    }
}
