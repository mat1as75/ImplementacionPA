import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.Test;
import static org.junit.Assert.*;

public class CargarDatosTest {
    private final String rutaDatosDePruebaSQL = "./test/datosDePrueba/DatosPrueba_Persistencia.sql";
    private final String rutaDeleteDatosSQL = "./test/datosDePrueba/delete.sql";
    private EntityManagerFactory emf;
    private EntityManager em;
    
    public CargarDatosTest() {
        emf = Persistence.createEntityManagerFactory("EspotifyPU");
        em = emf.createEntityManager();
    }
    
    public Boolean leerSQLdeArchivo(String ruta) {
        BufferedReader reader;
        em.getTransaction().begin();
        try {
            reader = new BufferedReader(new FileReader(ruta));
            String line = reader.readLine();

            while (line != null) {
                //ejecuto el query de esa linea, requiere de executeUpdate() si es una insercion/modificacion/eliminacion
                em.createNativeQuery(line).executeUpdate();
                //paso a la siguiente linea
                line = reader.readLine();
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        em.getTransaction().commit();
        return true;
    }
    
    public Boolean cargarDatosDePrueba() {
        return leerSQLdeArchivo(rutaDatosDePruebaSQL);
    }
    
    public Boolean borrarDatosDePrueba() {
        return leerSQLdeArchivo(rutaDeleteDatosSQL);
    }
    
    public Boolean resetDatosDePrueba() {
        //borro todos los datos de la base de datos
        if (borrarDatosDePrueba()) {
            //si todo se borro correctamente vuelvo al estado inicial con los datos de prueba
            return cargarDatosDePrueba();
        } else {
            return false;
        }
    }

    /*
        comentar este test cuando se quiera ver el resultado de las inserciones y modificaciones en la BD,
        de lo contrario se resetea la base de datos al ejecutar este test
    */    
//    @Test
//    public void testCargarDatos(){
//        assertTrue(resetDatosDePrueba());
//    }
    
    @Test
    public void test1() {
        assertTrue(true);
    }
    
}
