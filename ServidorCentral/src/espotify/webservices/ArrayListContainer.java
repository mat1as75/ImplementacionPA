package espotify.webservices;

import java.util.ArrayList;

public class ArrayListContainer {
    private ArrayList coleccion;

    public ArrayListContainer() {};
    
    public ArrayListContainer(ArrayList coleccion) {
        this.coleccion = coleccion;
    }
    
    public ArrayList getColeccion() {
        return coleccion;
    }

    public void setColeccion(ArrayList coleccion) {
        this.coleccion = coleccion;
    }
    
}
