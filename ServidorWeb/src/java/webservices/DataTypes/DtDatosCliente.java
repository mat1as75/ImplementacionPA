
package webservices.DataTypes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import java.util.List;
import java.util.Map;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtDatosCliente", propOrder = {
    "nicknamesSeguidos",
    "nombresListasRCreadas",
    "nombresListasRCreadasPublicas",
    "nombresListasRFavoritas",
    "nombresAlbumesFavoritos",
    "nombresTemasFavoritos",
    "suscripcion"
})
public class DtDatosCliente
    extends DtDatosUsuario
{
    @XmlElement(nillable = true)
    protected List<String> nicknamesSeguidos;
    @XmlElement(nillable = true)
    protected List<String> nombresListasRCreadas;
    @XmlElement(nillable = true)
    protected List<String> nombresListasRCreadasPublicas;
    @XmlElement(nillable = true)
    protected List<String> nombresListasRFavoritas;
    protected Map<Long, String> nombresAlbumesFavoritos;
    protected Map<Long, String> nombresTemasFavoritos;
    protected DtSuscripcion suscripcion;

    public List<String> getNicknamesSeguidos() {
        return nicknamesSeguidos;
    }

    public void setNicknamesSeguidos(List<String> nicknamesSeguidos) {
        this.nicknamesSeguidos = nicknamesSeguidos;
    }

    public List<String> getNombresListasRCreadas() {
        return nombresListasRCreadas;
    }

    public void setNombresListasRCreadas(List<String> nombresListasRCreadas) {
        this.nombresListasRCreadas = nombresListasRCreadas;
    }

    public List<String> getNombresListasRCreadasPublicas() {
        return nombresListasRCreadasPublicas;
    }

    public void setNombresListasRCreadasPublicas(List<String> nombresListasRCreadasPublicas) {
        this.nombresListasRCreadasPublicas = nombresListasRCreadasPublicas;
    }

    public List<String> getNombresListasRFavoritas() {
        return nombresListasRFavoritas;
    }

    public void setNombresListasRFavoritas(List<String> nombresListasRFavoritas) {
        this.nombresListasRFavoritas = nombresListasRFavoritas;
    }

    public Map<Long, String> getNombresAlbumesFavoritos() {
        return nombresAlbumesFavoritos;
    }

    public void setNombresAlbumesFavoritos(Map<Long, String> nombresAlbumesFavoritos) {
        this.nombresAlbumesFavoritos = nombresAlbumesFavoritos;
    }

    public Map<Long, String> getNombresTemasFavoritos() {
        return nombresTemasFavoritos;
    }

    public void setNombresTemasFavoritos(Map<Long, String> nombresTemasFavoritos) {
        this.nombresTemasFavoritos = nombresTemasFavoritos;
    }

    public DtSuscripcion getSuscripcion() {
        return suscripcion;
    }

    public void setSuscripcion(DtSuscripcion suscripcion) {
        this.suscripcion = suscripcion;
    }
    
    

}
