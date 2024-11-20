
package webservices.DataTypes;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtGeneroSimple", propOrder = {
    "nombreGenero",
    "nombreGeneroPadre",
    "subgeneros"
})
public class DtGeneroSimple {

    protected String nombreGenero;
    protected String nombreGeneroPadre;
    @XmlElement(nillable = true)
    protected List<String> subgeneros;

    public String getNombreGenero() {
        return nombreGenero;
    }

    public void setNombreGenero(String value) {
        this.nombreGenero = value;
    }

    public String getNombreGeneroPadre() {
        return nombreGeneroPadre;
    }
    
    public void setNombreGeneroPadre(String value) {
        this.nombreGeneroPadre = value;
    }

    public List<String> getSubgeneros() {
        if (subgeneros == null) {
            subgeneros = new ArrayList<String>();
        }
        return this.subgeneros;
    }

}
