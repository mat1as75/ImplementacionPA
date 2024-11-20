
package webservices.DataTypes;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtDatosListaReproduccion", propOrder = {
    "cliente",
    "fotoLista",
    "genero",
    "nombreLista",
    "privacidad",
    "temas",
    "tipoDeLista"
})
public class DtDatosListaReproduccion {

    protected String cliente;
    protected String fotoLista;
    protected String genero;
    protected String nombreLista;
    protected Boolean privacidad;
    @XmlElement(nillable = true)
    protected List<DtTemaSimple> temas;
    protected String tipoDeLista;

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String value) {
        this.cliente = value;
    }

    public String getFotoLista() {
        return fotoLista;
    }

    public void setFotoLista(String value) {
        this.fotoLista = value;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String value) {
        this.genero = value;
    }

    public String getNombreLista() {
        return nombreLista;
    }

    public void setNombreLista(String value) {
        this.nombreLista = value;
    }

    public Boolean isPrivacidad() {
        return privacidad;
    }

    public void setPrivacidad(Boolean value) {
        this.privacidad = value;
    }

    public List<DtTemaSimple> getTemas() {
        if (temas == null) {
            temas = new ArrayList<DtTemaSimple>();
        }
        return this.temas;
    }

    public String getTipoDeLista() {
        return tipoDeLista;
    }

    public void setTipoDeLista(String value) {
        this.tipoDeLista = value;
    }
}
