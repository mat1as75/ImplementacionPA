package espotify.DataTypes;

public class DTTemaGenericoConRutaOUrl {
    
    private Long idTema;
    private String nombreTema;
    private String rutaTema;
    private String urlTema;

    public DTTemaGenericoConRutaOUrl() {};
    
    public DTTemaGenericoConRutaOUrl(Long idTema, String nombreTema, String rutaTema, String urlTema) {
        this.idTema = idTema;
        this.nombreTema = nombreTema;
        this.rutaTema = rutaTema;
        this.urlTema = urlTema;
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
}
