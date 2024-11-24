
package webservices.DataTypes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;
import java.util.List;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for dtDatosUsuario complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dtDatosUsuario"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="apellidoUsuario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="contrasenaUsuario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="fecNac" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="fotoPerfil" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="nicknameUsuario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="nombreUsuario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtDatosUsuario", propOrder = {
    "apellidoUsuario",
    "contrasenaUsuario",
    "email",
    "fecNac",
    "fotoPerfil",
    "nicknameUsuario",
    "nombreUsuario",
    "nicknamesSeguidores"
})
@XmlSeeAlso({
    DtDatosArtista.class,
    DtDatosCliente.class
})
public class DtDatosUsuario {

    protected String apellidoUsuario;
    protected String contrasenaUsuario;
    protected String email;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fecNac;
    protected String fotoPerfil;
    protected String nicknameUsuario;
    protected String nombreUsuario;
    @XmlElement(nillable = true)
    protected List<String> nicknamesSeguidores;

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

    public String getNicknameUsuario() {
        return nicknameUsuario;
    }

    public void setNicknameUsuario(String value) {
        this.nicknameUsuario = value;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String value) {
        this.nombreUsuario = value;
    }

    public List<String> getNicknamesSeguidores() {
        return nicknamesSeguidores;
    }

    public void setNicknamesSeguidores(List<String> nicknamesSeguidores) {
        this.nicknamesSeguidores = nicknamesSeguidores;
    }

    
}
