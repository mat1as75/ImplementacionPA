package webservices.DataTypes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtTemaConRuta", propOrder = {
    "rutaTema",
    "nombreTema",
    "duracionSegundos",
    "posicionEnAlbum"
})
public class DtTemaConRuta extends DtTemaGenerico {
    private String rutaTema;

    public String getRutaTema() {
        return rutaTema;
    }

    public void setRutaTema(String rutaTema) {
        this.rutaTema = rutaTema;
    }
}
