package espotify.DataTypes;

public abstract class DTTema  {

    //atributos
    protected Long idTema;
    protected String nombreTema;
    protected int duracion;
    protected int posicionEnAlbum;

    //constructor
    
    public DTTema() {
    }    

    public DTTema(Long idTema, String nombreTema,int duracion, int posicionEnAlbum) {
        this.idTema = idTema;
        this.nombreTema = nombreTema;
        this.duracion = duracion;
        this.posicionEnAlbum = posicionEnAlbum;
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

    public  int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }
    
    public int getPosicionEnAlbum() {
        return posicionEnAlbum;
    }

    public void setPosicionEnAlbum(int posicionEnAlbum) {
        this.posicionEnAlbum = posicionEnAlbum;
    }
    //metodos
}
