package webservices.DataTypes;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtUsuario", propOrder = {
    "apellidoUsuario",
    "contrasenaUsuario",
    "email",
    "fecNac",
    "fotoPerfil",
    "misSeguidores",
    "nickname",
    "nombreUsuario"
})
@XmlSeeAlso({
    DtArtista.class,
    DtCliente.class
})
public abstract class DtUsuario {

    protected String apellidoUsuario;
    protected String contrasenaUsuario;
    protected String email;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fecNac;
    protected String fotoPerfil;
    @XmlElement(nillable = true)
    protected List<DtUsuario> misSeguidores;
    protected String nickname;
    protected String nombreUsuario;

    public String getApellidoUsuario() {
        return apellidoUsuario;
    }

    public void setApellidoUsuario(String value) {
        this.apellidoUsuario = value;
    }

    public String getContrasenaUsuario() {
        return contrasenaUsuario;
    }

    public void setContrasenaUsuario(String value) {
        this.contrasenaUsuario = value;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String value) {
        this.email = value;
    }

    public XMLGregorianCalendar getFecNac() {
        return fecNac;
    }

    public void setFecNac(XMLGregorianCalendar value) {
        this.fecNac = value;
    }

    public String getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(String value) {
        this.fotoPerfil = value;
    }

    /**
     * Gets the value of the misSeguidores property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the misSeguidores property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMisSeguidores().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtUsuario }
     * 
     * 
     */
    public List<DtUsuario> getMisSeguidores() {
        if (misSeguidores == null) {
            misSeguidores = new ArrayList<DtUsuario>();
        }
        return this.misSeguidores;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String value) {
        this.nickname = value;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String value) {
        this.nombreUsuario = value;
    }
}
