import espotify.persistencia.ControladoraPersistencia;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class BajaDeArtistaTest {
    
    private static ControladoraPersistencia cp;
    private static CargarDatosTest cdt;
    
    public BajaDeArtistaTest() {
        cp = new ControladoraPersistencia();
        cdt = new CargarDatosTest();
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        cdt.resetDatosDePrueba();
    }
    
    @After
    public void tearDown() {
    }

   
    @Test
    public void bajaDeArtista1() {
        try {
            cp.darDeBajaArtista("alcides");
            assertTrue(true);
        } catch (Exception e) {
            assertTrue(false);
        }
    }
}
