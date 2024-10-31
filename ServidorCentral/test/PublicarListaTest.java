import espotify.logica.ListaParticular;
import espotify.persistencia.ControladoraPersistencia;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass; 
import org.junit.Test;
import static org.junit.Assert.*;

public class PublicarListaTest {
    private static ControladoraPersistencia cp;
    private static CargarDatosTest cdt;
    
    public PublicarListaTest() {
        cp = new ControladoraPersistencia();
        cdt = new CargarDatosTest();
    }
    
    public Boolean comprobarSetPrivadafalse(String nicknameCliente,String nombreLista, String test, Boolean expected) {
        
        cp.setPrivadafalse(nicknameCliente, nombreLista);
        ListaParticular listaRep = cp.lpartJpa.findListaParticular(nombreLista);
        
        Boolean fuePublicada = !listaRep.soyPrivada();
        
        if (fuePublicada.equals(expected)) {
            System.out.println("Test Publicar Lista: " + test + ". OK");
            return true;
        } else {
            System.out.println("Test Publicar Lista: " + test + ". FAIL");
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
    public void publicarLista1() {
        
        String nicknameCliente="cbochinche";
        String nombreLista="Mis Favoritas";
                
        assertTrue(comprobarSetPrivadafalse(
                nicknameCliente,
                nombreLista,
                "Publicar 'Mis Favoritas' de cbochinche", 
                true));
    }
    
    @Test
    public void publicarLista2() {
        String nicknameCliente="Heisenberg";
        String nombreLista="Para Cocinar";
              
        assertTrue(comprobarSetPrivadafalse(
                nicknameCliente,
                nombreLista,
                "Publicar 'Para Cocinar' de Heisenberg", 
                true));
    }
    
    @Test
    public void publicarListaClienteNoPropietario() {
        String nicknameCliente="cbochinche";
        String nombreLista="Para Cocinar";
              
        assertTrue(comprobarSetPrivadafalse(
                nicknameCliente,
                nombreLista,
                "Publicar lista de otro cliente", 
                false));
    }
    
    @Test
    public void publicarListaClienteInexistente() {
        String nicknameCliente="cliente inexistente";
        String nombreLista="Para Cocinar";
              
        assertTrue(comprobarSetPrivadafalse(
                nicknameCliente,
                nombreLista,
                "Publicar lista de cliente inexistente", 
                false));
    }
}
