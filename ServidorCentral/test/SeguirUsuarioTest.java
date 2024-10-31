import espotify.DataTypes.DTDatosCliente;
import espotify.DataTypes.DTDatosUsuario;
import espotify.persistencia.ControladoraPersistencia;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class SeguirUsuarioTest {
    private static ControladoraPersistencia cp;
    private static CargarDatosTest cdt;
    
    public SeguirUsuarioTest() {
        cp = new ControladoraPersistencia();
        cdt = new CargarDatosTest();
    }
    
    public Boolean comprobarAsignacion(String seguidor,String seguido, String test, Boolean expected) {
       
        cp.setSeguidorSeguido(seguidor, seguido);
        DTDatosCliente dtClienteSeguidor = cp.getDatosCliente(seguidor);
        DTDatosUsuario dtUsuarioSeguido = cp.getDatosUsuario(seguido);
        
        Boolean usuariosExisten = dtClienteSeguidor != null && dtUsuarioSeguido != null;
        Boolean existeRelacion = cp.existeRelacion(seguidor, seguido);
        
        Boolean actualizacionCorrecta = usuariosExisten && existeRelacion;
        
        if (actualizacionCorrecta.equals(expected)) {
            System.out.println("Test: " + test + ". OK");
            return true;
        } else {
            System.out.println("Test: " + test + ". FAIL");
            return false;
        }
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
        cdt.resetDatosDePrueba();
    }
    
    @Before
    public void setUp() {
        cdt.resetDatosDePrueba();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void seguirUsuarioDatosValidos1() {
        String seguidor = "Eleven11";
        String seguido = "cbochinche";
        
        assertTrue(comprobarAsignacion(
                seguidor, seguido, 
                "Eleven11 sigue a cbochinche", 
                true
        ));
    }
    
    @Test
    public void seguirUsuarioDatosValidos2() {
        String seguidor = "cbochinche";
        String seguido = "Eleven11";
        
        assertTrue(comprobarAsignacion(
                seguidor, seguido, 
                "cbochinche sigue a Eleven11", 
                true
        ));
    }
 
    @Test
    public void seguirUsuarioDatosValidos3() {
        String seguidor = "cbochinche";
        String seguido = "clauper";
        
        assertTrue(comprobarAsignacion(
                seguidor, seguido, 
                "cbochinche sigue a clauper", 
                true
        ));
    }
    
    @Test
    public void seguirUsuarioDatosValidos4() {
        String seguidor = "el_padrino";
        String seguido = "alcides";
        
        assertTrue(comprobarAsignacion(
                seguidor, seguido, 
                "el_padrino sigue a alcides", 
                true
        ));
    }
    
}
