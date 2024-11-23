
package webservices.DataTypes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import java.util.ArrayList;
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
    protected ArrayList<String> nicknamesSeguidos;
    @XmlElement(nillable = true)
    protected ArrayList<String> nombresListasRCreadas;
    @XmlElement(nillable = true)
    protected ArrayList<String> nombresListasRCreadasPublicas;
    @XmlElement(nillable = true)
    protected ArrayList<String> nombresListasRFavoritas;
    protected Map<Long, String> nombresAlbumesFavoritos;
    protected Map<Long, String> nombresTemasFavoritos;
    @XmlElement(nillable = true)
    protected DtSuscripcion suscripcion;

    public ArrayList<String> getNicknamesSeguidos() {
        return nicknamesSeguidos;
    }

    public void setNicknamesSeguidos(ArrayList<String> nicknamesSeguidos) {
        this.nicknamesSeguidos = nicknamesSeguidos;
    }

    public ArrayList<String> getNombresListasRCreadas() {
        return nombresListasRCreadas;
    }

    public void setNombresListasRCreadas(ArrayList<String> nombresListasRCreadas) {
        this.nombresListasRCreadas = nombresListasRCreadas;
    }

    public ArrayList<String> getNombresListasRCreadasPublicas() {
        return nombresListasRCreadasPublicas;
    }

    public void setNombresListasRCreadasPublicas(ArrayList<String> nombresListasRCreadasPublicas) {
        this.nombresListasRCreadasPublicas = nombresListasRCreadasPublicas;
    }

    public ArrayList<String> getNombresListasRFavoritas() {
        return nombresListasRFavoritas;
    }

    public void setNombresListasRFavoritas(ArrayList<String> nombresListasRFavoritas) {
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
