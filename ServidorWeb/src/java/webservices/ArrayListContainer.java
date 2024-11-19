
package webservices;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "arrayListContainer", propOrder = {
    "coleccion"
})
public class ArrayListContainer {

    @XmlElement(nillable = true)
    protected List<Object> coleccion;

    public List<Object> getColeccion() {
        if (coleccion == null) {
            coleccion = new ArrayList<Object>();
        }
        return this.coleccion;
    }

}
