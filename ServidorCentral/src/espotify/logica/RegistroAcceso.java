package espotify.logica;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class RegistroAcceso implements Serializable{
    
    // Atributos
    @Id
    private Long idRegistro;
    private String ipRegistro;
    private String urlRegistro;
    private String browserRegistro;
    private String soRegistro;
    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;

    public RegistroAcceso() {
    }

    public RegistroAcceso(Long idRegistro, String ipRegistro, String urlRegistro, String browserRegistro, String soRegistro, Date fechaRegistro) {
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
