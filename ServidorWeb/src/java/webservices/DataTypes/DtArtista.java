
package webservices.DataTypes;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtArtista", propOrder = {
    "activo",
    "biografia",
    "dirSitioWeb",
    "fechaBaja",
    "misAlbumesPublicados"
})
public class DtArtista
    extends DtUsuario
{

    protected Boolean activo;
    protected String biografia;
    protected String dirSitioWeb;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaBaja;
    @XmlElement(nillable = true)
    protected List<DtAlbum> misAlbumesPublicados;

    public Boolean isActivo() {
        return activo;
    }

    public void setActivo(Boolean value) {
        this.activo = value;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String value) {
        this.biografia = value;
    }

    public String getDirSitioWeb() {
        return dirSitioWeb;
    }

    public void setDirSitioWeb(String value) {
        this.dirSitioWeb = value;
    }

    public XMLGregorianCalendar getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(XMLGregorianCalendar value) {
        this.fechaBaja = value;
    }

    /**
     * Gets the value of the misAlbumesPublicados property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the misAlbumesPublicados property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMisAlbumesPublicados().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtAlbum }
     * 
     * 
     */
    public List<DtAlbum> getMisAlbumesPublicados() {
        if (misAlbumesPublicados == null) {
            misAlbumesPublicados = new ArrayList<DtAlbum>();
        }
        return this.misAlbumesPublicados;
    }
}
