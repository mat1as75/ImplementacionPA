package webservices;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for estadoSuscripcion.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="estadoSuscripcion"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Pendiente"/&gt;
 *     &lt;enumeration value="Vigente"/&gt;
 *     &lt;enumeration value="Cancelada"/&gt;
 *     &lt;enumeration value="Vencida"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "estadoSuscripcion")
@XmlEnum
public enum EstadoSuscripcion {

    @XmlEnumValue("Pendiente")
    PENDIENTE("Pendiente"),
    @XmlEnumValue("Vigente")
    VIGENTE("Vigente"),
    @XmlEnumValue("Cancelada")
    CANCELADA("Cancelada"),
    @XmlEnumValue("Vencida")
    VENCIDA("Vencida");
    private final String value;

    EstadoSuscripcion(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EstadoSuscripcion fromValue(String v) {
        for (EstadoSuscripcion c: EstadoSuscripcion.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
