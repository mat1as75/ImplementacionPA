package espotify.DataTypes;

import java.util.Date;


public class DTRegistroAcceso {
    
    // Atributos
    private Long idRegistro;
    private String ipRegistro;
    private String urlRegistro;
    private String browserRegistro;
    private String soRegistro;
    private Date fechaRegistro;
    
    public DTRegistroAcceso() {};

    public DTRegistroAcceso(Long idRegistro, String ipRegistro, String urlRegistro, String browserRegistro, String soRegistro, Date fechaRegistro) {
        this.idRegistro = idRegistro;
        this.ipRegistro = ipRegistro;
        this.urlRegistro = urlRegistro;
        this.browserRegistro = browserRegistro;
        this.soRegistro = soRegistro;
        this.fechaRegistro = fechaRegistro;
    }

    public Long getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(Long idRegistro) {
        this.idRegistro = idRegistro;
    }

    public String getIpRegistro() {
        return ipRegistro;
    }

    public void setIpRegistro(String ipRegistro) {
        this.ipRegistro = ipRegistro;
    }

    public String getUrlRegistro() {
        return urlRegistro;
    }

    public void setUrlRegistro(String urlRegistro) {
        this.urlRegistro = urlRegistro;
    }

    public String getBrowserRegistro() {
        return browserRegistro;
    }

    public void setBrowserRegistro(String browserRegistro) {
        this.browserRegistro = browserRegistro;
    }

    public String getSoRegistro() {
        return soRegistro;
    }

    public void setSoRegistro(String soRegistro) {
        this.soRegistro = soRegistro;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    
    
}
