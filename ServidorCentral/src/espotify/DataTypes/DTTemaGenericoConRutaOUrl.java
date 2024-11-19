package espotify.DataTypes;

public class DTTemaGenericoConRutaOUrl {
    
    private Long idTema;
    private String nombreTema;
    private String rutaTema;
    private String urlTema;
    private Long cantidadReproducciones;
    private Long cantidadDescargasOVisitas;
    private Long cantidadFavoritos;
    private float puntajeTema;

    public DTTemaGenericoConRutaOUrl() {};
    
    public DTTemaGenericoConRutaOUrl(Long idTema, String nombreTema, String rutaTema, String urlTema) {
        this.idTema = idTema;
        this.nombreTema = nombreTema;
        this.rutaTema = rutaTema;
        this.urlTema = urlTema;
    }
    
    public DTTemaGenericoConRutaOUrl(
            Long idTema, 
            String nombreTema, 
            String rutaTema, 
            String urlTema,
            Long cantidadReproducciones,
            Long cantidadDescargasOVisitas,
            Long cantidadFavoritos,
            float puntaje
            ) {
        this.idTema = idTema;
        this.nombreTema = nombreTema;
        this.rutaTema = rutaTema;
        this.urlTema = urlTema;
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

    public String getRutaTema() {
        return rutaTema;
    }

    public void setRutaTema(String rutaTema) {
        this.rutaTema = rutaTema;
    }

    public String getUrlTema() {
        return urlTema;
    }

    public void setUrlTema(String urlTema) {
        this.urlTema = urlTema;
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
    
    
}
