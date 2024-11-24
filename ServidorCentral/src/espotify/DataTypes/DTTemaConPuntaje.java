package espotify.DataTypes;

import java.util.List;

public class DTTemaConPuntaje {
       
    private Long idTema;
    private String nombreTema;
    private int duracionSegundos;
    private int posicionEnAlbum;
    private String nombreAlbum;
    private Long idAlbum;
    private String nombreCompletoArtista;
    private List<String> generosDeTema;
    private Float puntajeTema;
    private Long cantidadReproducciones;
    private Long cantidadDescargasOVisitas;
    private Long cantidadFavoritos;
    private Long cantidadListas;
    
    public DTTemaConPuntaje() {};
    
    public DTTemaConPuntaje(
            Long idTema, 
            String nombreTema, 
            int duracionSegundos, 
            int posicionEnAlbum,
            Long idAlbum,
            String nombreAlbum, 
            String nombreCompletoArtista,
            List<String> generosDeTema,
            Float puntajeTema,
            Long cantidadReproducciones,
            Long cantidadDescargasOVisitas,
            Long cantidadFavoritos,
            Long cantidadListas
            ) {
        this.idTema = idTema;
        this.nombreTema = nombreTema;
        this.duracionSegundos = duracionSegundos;
        this.posicionEnAlbum = posicionEnAlbum;
        this.idAlbum = idAlbum;
        this.nombreAlbum = nombreAlbum;
        this.nombreCompletoArtista = nombreCompletoArtista;
        this.generosDeTema = generosDeTema;
        this.puntajeTema = puntajeTema;
        this.cantidadReproducciones = cantidadReproducciones;
        this.cantidadDescargasOVisitas = cantidadDescargasOVisitas;
        this.cantidadFavoritos = cantidadFavoritos;
        this.cantidadListas = cantidadListas;
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
    
    public void setPuntajeTema(Float puntaje) {
        this.puntajeTema = puntaje;
    }
    
    public Float getPuntajeTema() {
        return puntajeTema;
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

    public Long getCantidadListas() {
        return cantidadListas;
    }

    public void setCantidadListas(Long cantidadListas) {
        this.cantidadListas = cantidadListas;
    }
    
    
}
