package webservices.DataTypes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtTemaConUrl", propOrder = {
    "nombreTema",
    "duracionSegundos",
    "posicionEnAlbum",
    "urlTema"
})
public class DtTemaConUrl extends DtTemaGenerico {
    private String urlTema;

    public String getUrlTema() {
        return urlTema;
    }

    public void setUrlTema(String urlTema) {
        this.urlTema = urlTema;
    }
}
