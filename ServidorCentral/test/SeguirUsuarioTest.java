import espotify.DataTypes.DTArtista;
import espotify.logica.Fabrica;
import espotify.logica.IControlador;
import espotify.persistencia.ControladoraPersistencia;
import javax.swing.JOptionPane;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author usuario
 */
public class SeguirUsuarioTest {
    private static ControladoraPersistencia cp;
    private static Fabrica f=Fabrica.getInstance();
    private static IControlador i=f.getControlador();
    
    public SeguirUsuarioTest() {
        cp=new ControladoraPersistencia();
    }
    public Boolean comprobarAsignacion(String Seguidor,String Seguido, String test, Boolean expected) {
        
        Boolean fueCreado = false;
        try {
            cp.setSeguidorSeguido(Seguidor, Seguido);
            fueCreado = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            fueCreado = false;
        }
            
        if (fueCreado.equals(expected)) {
            System.out.println("Test: " + test + ". OK");
            return true;
        } else {
            System.out.println("Test: " + test + ". FAIL");
            return false;
        }
    }
    @Test
    public void Cliente1SigueACliente2() {
        String Seguidor="Eleven11";
        String Seguido="cbochinche";
         boolean existeRelacion = i.existeRelacion(Seguidor, Seguido);
        if (existeRelacion) {
            assertTrue(comprobarAsignacion(Seguidor,Seguido, "Cliente "+Seguidor+" Sigue a Cliente "+Seguido, false));
        }else{
            assertTrue(comprobarAsignacion(Seguidor,Seguido, "Cliente "+Seguidor+" Sigue a Cliente "+Seguido, true));
        }
        
    }
     @Test
    public void Cliente2SigueACliente1() {
        String Seguidor="cbochinche";
        String Seguido="Eleven11"; 
        boolean existeRelacion = i.existeRelacion(Seguidor, Seguido);
        if (existeRelacion) {
            assertTrue(comprobarAsignacion(Seguidor,Seguido, "Cliente "+Seguidor+" Sigue a Cliente "+Seguido, false));
        }else{
            assertTrue(comprobarAsignacion(Seguidor,Seguido, "Cliente "+Seguidor+" Sigue a Cliente "+Seguido, true));
        }
    }
    @Test
    public void ClienteSigueArtista() {
        String Seguidor="el_padrino";
        String Seguido="alcides";
        boolean existeRelacion = i.existeRelacion(Seguidor, Seguido);
        if (existeRelacion) {
            assertTrue(comprobarAsignacion(Seguidor,Seguido, "Cliente "+Seguidor+" Sigue a Cliente "+Seguido, false));
        }else{
            assertTrue(comprobarAsignacion(Seguidor,Seguido, "Cliente "+Seguidor+" Sigue a Cliente "+Seguido, true));
        }
   
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
