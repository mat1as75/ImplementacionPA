package espotify.DataTypes;

public class DTTemaSimple {
    
    private Long idTema;
    private String nombreTema;
    private int duracionSegundos;
    private int posicionEnAlbum;
    private String nombreAlbum;
    private String nombreCompletoArtista;

    public DTTemaSimple() {};
    
    public DTTemaSimple(Long idTema, String nombreTema, int duracionSegundos, int posicionEnAlbum, String nombreAlbum, String nombreCompletoArtista) {
        this.idTema = idTema;
        this.nombreTema = nombreTema;
        this.duracionSegundos = duracionSegundos;
        this.posicionEnAlbum = posicionEnAlbum;
        this.nombreAlbum = nombreAlbum;
        this.nombreCompletoArtista = nombreCompletoArtista;
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
        return ("(Id: " + this.getIdTema() 
                + ") " + this.getNombreTema() + 
                ", Album: " + this.getNombreAlbum() + 
                ", Artista: " + this.getNombreCompletoArtista());
    }
}