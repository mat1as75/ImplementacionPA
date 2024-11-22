
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
@XmlType(name = "dtDatosUsuarioSinPw", propOrder = {
    "apellidoUsuario",
    "email",
    "fecNac",
    "fotoPerfil",
    "nicknameUsuario",
    "nicknamesSeguidores",
    "nombreUsuario"
})
public class DtDatosUsuarioSinPw {

    protected String apellidoUsuario;
    protected String email;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fecNac;
    protected String fotoPerfil;
    protected String nicknameUsuario;
    @XmlElement(nillable = true)
    protected List<String> nicknamesSeguidores;
    protected String nombreUsuario;

    public String getApellidoUsuario() {
        return apellidoUsuario;
    }

    public void setApellidoUsuario(String value) {
        this.apellidoUsuario = value;
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

    public List<String> getNicknamesSeguidores() {
        if (nicknamesSeguidores == null) {
            nicknamesSeguidores = new ArrayList<String>();
        }
        return this.nicknamesSeguidores;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String value) {
        this.nombreUsuario = value;
    }

}
