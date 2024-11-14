
package webservices;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for tipoSuscripcion.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="tipoSuscripcion"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Semanal"/&gt;
 *     &lt;enumeration value="Mensual"/&gt;
 *     &lt;enumeration value="Anual"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "tipoSuscripcion")
@XmlEnum
public enum TipoSuscripcion {

    @XmlEnumValue("Semanal")
    SEMANAL("Semanal"),
    @XmlEnumValue("Mensual")
    MENSUAL("Mensual"),
    @XmlEnumValue("Anual")
    ANUAL("Anual");
    private final String value;

    TipoSuscripcion(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TipoSuscripcion fromValue(String v) {
        for (TipoSuscripcion c: TipoSuscripcion.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
