package prueba;

public class Prueba {
    public static void main(String[] args) {
        WSPrueba_Service service = new WSPrueba_Service();
        WSPrueba ws = service.getWSPruebaPort();
    
        String resp = ws.hello("Pepe");
        
        System.out.println(resp);
    }
}
