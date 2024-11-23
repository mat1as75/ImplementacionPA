
package webservices.DataTypes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import java.util.Map;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtDatosArtista", propOrder = {
    "biografia",
    "dirSitioWeb",
    "activo",
    "fechaBaja",
    "contidadSeguidores",
    "nombresAlbumesPublicados",
})
public class DtDatosArtista
    extends DtDatosUsuario
{
    protected String biografia;
    protected String dirSitioWeb;
    protected Boolean activo;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaBaja;
    protected int contidadSeguidores;
    protected Map<Long, String> nombresAlbumesPublicados;

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public String getDirSitioWeb() {
        return dirSitioWeb;
    }

    public void setDirSitioWeb(String dirSitioWeb) {
        this.dirSitioWeb = dirSitioWeb;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public XMLGregorianCalendar getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(XMLGregorianCalendar fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public int getContidadSeguidores() {
        return contidadSeguidores;
    }

    public void setContidadSeguidores(int contidadSeguidores) {
        this.contidadSeguidores = contidadSeguidores;
    }

    public Map<Long, String> getNombresAlbumesPublicados() {
        return nombresAlbumesPublicados;
    }

    public void setNombresAlbumesPublicados(Map<Long, String> nombresAlbumesPublicados) {
        this.nombresAlbumesPublicados = nombresAlbumesPublicados;
    }
    
    

}
