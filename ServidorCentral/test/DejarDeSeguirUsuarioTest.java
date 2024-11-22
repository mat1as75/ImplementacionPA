import espotify.logica.Cliente;
import espotify.logica.Usuario;
import espotify.persistencia.ControladoraPersistencia;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DejarDeSeguirUsuarioTest {
    private static ControladoraPersistencia cp;
    private static CargarDatosTest cdt;
    
    
    public DejarDeSeguirUsuarioTest() {
        cp = new ControladoraPersistencia();
        cdt = new CargarDatosTest();
    }
    
    public Boolean verificarActualizacion(String seguidor,String seguido, String test, Boolean expected) {
       
        Boolean clienteExiste = cp.ExisteCliente(seguidor);
        Boolean usuarioExiste = cp.ExisteNickName(seguido);
        
        Boolean usuariosExisten = clienteExiste && usuarioExiste;
        Boolean existeRelacion = false;
        Boolean errorDeOperacion = false;
        
        try {
            cp.dejarDeSeguir(seguidor, seguido);
        } catch (Exception ex) {
            Logger.getLogger(DejarDeSeguirUsuarioTest.class.getName()).log(Level.SEVERE, null, ex);
            errorDeOperacion = true;
        }
        
        if (usuariosExisten) {
            Cliente clienteSeguidor = cp.cliJpa.findCliente(seguidor);
            Usuario usuarioSeguido = cp.usuJpa.findUsuario(seguido);
            List<Usuario> seguidosDeCliente = clienteSeguidor.getMisSeguidos();
            List<Usuario> seguidoresDeUsuarioSeguido = usuarioSeguido.getMisSeguidores();

            for (Usuario usuario : seguidosDeCliente) {
                if (usuario.getNickname().equals(seguido)) {
                    //el usuario seguido sigue estando en la lista de seguidos del cliente
                    System.out.println(usuario.getNickname() + " sigue estando en seguidos de " + seguidor);
                    existeRelacion = true;
                    break;
                }
            }

            for (Usuario cliente : seguidoresDeUsuarioSeguido) {
                if (cliente.getNickname().equals(seguidor)) {
                    //el usuario seguido sigue teniendo al cliente en su lista de seguidores
                    System.out.println(cliente.getNickname() + " sigue estando en seguidores de " + seguido);
                    existeRelacion = true;
                    break;
                }
            }
        }
        
        Boolean actualizacionCorrecta = usuariosExisten && !existeRelacion && !errorDeOperacion;
        
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

    /**
     * Algunos datos de prueba:
     * alcides es seguido por benKenobi
     * benKenobi es seguido por ppArgento
     * cbochinche es seguido por benKenobi
     * dyangounchained es seguido por cbochinche
     */
    
    @Test
    public void dejarDeSeguirDatosValidos1() {
        String seguidor = "benKenobi";
        String seguido = "alcides";
        
        assertTrue(verificarActualizacion(
                seguidor, seguido, "benKenobi deja de seguir a alcides", true
            )    
        );
    }
    
    @Test
    public void dejarDeSeguirDatosValidos2() {
        String seguidor = "ppArgento";
        String seguido = "benKenobi";
        
        assertTrue(verificarActualizacion(
                seguidor, seguido, "ppArgento deja de seguir a benKenobi", true
            )    
        );
    }
    
    @Test
    public void dejarDeSeguirDatosValidos3() {
        String seguidor = "benKenobi";
        String seguido = "cbochinche";
        
        assertTrue(verificarActualizacion(
                seguidor, seguido, "benKenobi deja de seguir a cbochinche", true
            )    
        );
    }
    
    @Test
    public void dejarDeSeguirDatosValidos4() {
        String seguidor = "cbochinche";
        String seguido = "dyangounchained";
        
        assertTrue(verificarActualizacion(
                seguidor, seguido, "cbochinche deja de seguir a dyangounchained", true
            )    
        );
    }
    
}
