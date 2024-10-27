
import espotify.persistencia.ControladoraPersistencia;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class IngresarSuscripcionTest {
    
    private static ControladoraPersistencia cp;
    private static CargarDatosTest cdt;
    
    public IngresarSuscripcionTest() {
        cp = new ControladoraPersistencia();
        cdt = new CargarDatosTest();
        //reseteo los datos al iniciar
        cdt.resetDatosDePrueba();
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
