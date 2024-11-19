package webservices;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;

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
