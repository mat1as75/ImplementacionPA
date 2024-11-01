import espotify.DataTypes.DTDatosCliente;
import espotify.DataTypes.DTDatosUsuario;
import espotify.persistencia.ControladoraPersistencia;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class DejarSeguirUsuarioTest {
    private static ControladoraPersistencia cp;
    private static CargarDatosTest cdt;
    
    public DejarSeguirUsuarioTest() {
        cp = new ControladoraPersistencia();
        cdt = new CargarDatosTest();
    }
    
    public Boolean comprobarDesasignacion(String seguidor,String seguido, String test, Boolean expected) throws Exception {
       
        cp.dejarDeSeguir(seguidor, seguido);
        DTDatosCliente dtClienteSeguidor = cp.getDatosCliente(seguidor);
        DTDatosUsuario dtUsuarioSeguido = cp.getDatosUsuario(seguido);
        
        Boolean usuariosExisten = dtClienteSeguidor != null && dtUsuarioSeguido != null;
        Boolean existeRelacion = cp.existeRelacion(seguidor, seguido);
        
        Boolean actualizacionCorrecta = usuariosExisten && !existeRelacion;
        
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
    public void dejarseguirUsuarioDatosValidos1() throws Exception {
        String seguidor = "benKenobi";
        String seguido = "alcides";
        
        assertTrue(comprobarDesasignacion(
                seguidor, seguido, 
                "benKenobi dejo de seguir a cbochinche", 
                true
        ));
    }
    
    @Test
    public void dejarseguirUsuarioDatosValidos2() throws Exception {
        String seguidor = "el_padrino";
        String seguido = "benKenobi";
        
        assertTrue(comprobarDesasignacion(
                seguidor, seguido, 
                "el_padrino dejo de seguir a alcides", 
                true
        ));
    }
 
    @Test
    public void dejarseguirUsuarioDatosValidos3() throws Exception {
        String seguidor = "ppArgento";
        String seguido = "cbochinche";
        
        assertTrue(comprobarDesasignacion(
                seguidor, seguido, 
                "ppArgento dejo de seguir a alcides", 
                true
        ));
    }
    
    @Test
    public void dejarseguirUsuarioDatosValidos4() throws Exception {
        String seguidor = "Heisenberg";
        String seguido = "dmode";
        
        assertTrue(comprobarDesasignacion(
                seguidor, seguido, 
                "Heisenberg sigue a alcides", 
                true
        ));
    }
}
