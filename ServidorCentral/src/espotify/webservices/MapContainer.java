package espotify.webservices;

import espotify.DataTypes.DTTemaSimple;
import java.util.HashMap;

public class MapContainer {
    private HashMap<Long, String> mapLongString;
    private HashMap<Long, DTTemaSimple> mapLongDatatype;
    private HashMap<String, String> mapStringString;
    
    public MapContainer() {};
   
    public HashMap<Long, String> getMapLongString() {
        return mapLongString;
    }

    public void setMapLongString(HashMap<Long, String> mapLongString) {
        this.mapLongString = mapLongString;
    }

    public HashMap<Long, DTTemaSimple> getMapLongDatatype() {
        return mapLongDatatype;
    }

    public void setMapLongDatatype(HashMap<Long, DTTemaSimple> mapLongDatatype) {
        this.mapLongDatatype = mapLongDatatype;
    }

    public HashMap<String, String> getMapStringString() {
        return mapStringString;
    }

    public void setMapStringString(HashMap<String, String> mapStringString) {
        this.mapStringString = mapStringString;
    }
    
    
}
