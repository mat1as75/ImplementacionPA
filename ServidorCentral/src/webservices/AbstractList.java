package webservices;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

/**
 * <p>Clase Java para abstractList complex type.
 * 
 * <p>La clase maneja una lista interna de elementos de tipo Object y proporciona métodos
 * para manipularla y acceder a sus elementos.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CustomAbstractList", namespace = "http://webservices/")
public abstract class AbstractList extends AbstractCollection implements Iterable<Object> {

    // Lista interna para almacenar elementos
    @XmlElement(name = "item")  // Anotación que indica que los elementos de la lista son de tipo "item"
    protected List<Object> internalList = new ArrayList<>();  // Cambié a List<Object> para mejor manejo

    @Override
    public Iterator<Object> iterator() {
        return internalList.iterator();  // Devuelve el iterador de la lista interna
    }

    // Método para agregar elementos a la lista
    public void add(Object item) {
        internalList.add(item);  // Agrega un elemento a la lista interna
    }

    // Método para obtener el tamaño de la lista
    public int size() {
        return internalList.size();  // Devuelve el tamaño de la lista interna
    }

    // Método para obtener un elemento en un índice específico
    public Object get(int index) {
        return internalList.get(index);  // Devuelve el elemento en la posición indicada
    }

    // Getter para acceder a la lista interna (si es necesario)
    public List<Object> getInternalList() {
        return internalList;
    }

    // Setter para establecer la lista interna (si es necesario)
    public void setInternalList(List<Object> internalList) {
        this.internalList = internalList;
    }
}
