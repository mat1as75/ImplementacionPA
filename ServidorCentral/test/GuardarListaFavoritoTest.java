import espotify.logica.Cliente;
import espotify.logica.ListaReproduccion;
import espotify.persistencia.ControladoraPersistencia;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class GuardarListaFavoritoTest {
    
    private static ControladoraPersistencia cp;
    private static CargarDatosTest cdt;
    
    
    public GuardarListaFavoritoTest() {
        cp = new ControladoraPersistencia();
        cdt = new CargarDatosTest();
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

    public Boolean verificarGuardadoLista(String nickname, String nombreLista) throws Exception {
        Cliente cliente = cp.cliJpa.findCliente(nickname);
        
        if (cliente == null) {
            throw new Exception("No se pudo verificar porque el cliente no existe.");
        }
        
        List<ListaReproduccion> listasFavoritas = cliente.getMisListasReproduccionFav();
        
        for (ListaReproduccion lrep : listasFavoritas) {
            if (lrep.getNombreLista().equals(nombreLista)) {
                return true;
            }
        }
        return false;
    }
    
    public Boolean guardarLista(String nickname, String nombreLista, String test, Boolean expected) {
        
        Boolean fueGuardado = false;
        
        try {
            cp.GuardarListaFavorito(nickname, nombreLista);
            fueGuardado = verificarGuardadoLista(nickname, nombreLista);
        } catch (Exception e) {
            fueGuardado = false;
        }
        
        if (fueGuardado.equals(expected)) {
            System.out.println("Test de Guardar Lista Favorito: " + test + ". OK");
            return true;
        } else {
            System.out.println("Test de Guardar Lista Favorito: " + test + ". FAIL");
            return false;
        }
    }
    
    /*
        Datos de prueba - Listas favoritas
        cbochinche - Noche De La Nostalgia, Rock En Español
        el_padrino - Música Clásica, Noche De La Nostalgia
        Heisenberg - Noche De La Nostalgia
        ppArgento - Noche De La Nostalgia, Rock En Español
        scarlettO - Música Clásica
    */
    
    @Test
    public void guardarListaDatosValidos1() {
        String nicknameCliente = "el_padrino";
        String nombreLista = "Para Cocinar";
        
        assertTrue(guardarLista(nicknameCliente, nombreLista, "Datos validos 1", true));
    }
    
    @Test
    public void guardarListaDatosValidos2() {
        String nicknameCliente = "el_padrino";
        String nombreLista = "Fiesteras";
        
        assertTrue(guardarLista(nicknameCliente, nombreLista, "Datos validos 2", true));
    }
    
    @Test
    public void guardarListaDatosValidos3() {
        String nicknameCliente = "lachiqui";
        String nombreLista = "Rock En Español";
        
        assertTrue(guardarLista(nicknameCliente, nombreLista, "Datos validos 3", true));
    }
    
    @Test
    public void guardarListaInexistente() {
        String nicknameCliente = "cbochinche";
        String nombreLista = "lista inexistente";
        
        assertTrue(guardarLista(nicknameCliente, nombreLista, "Lista inexistente", false));
    }
    
    @Test
    public void guardarListaClienteInexistente() {
        String nicknameCliente = "cliente inexistente";
        String nombreLista = "Fiesteras";
        
        assertTrue(guardarLista(nicknameCliente, nombreLista, "Cliente inexistente", false));
    }
    
    @Test
    public void guardarListaNull() {
        String nicknameCliente = "lachiqui";
        String nombreLista = null;
        
        assertTrue(guardarLista(nicknameCliente, nombreLista, "Lista null", false));
    }
    
    @Test
    public void guardarListaClienteNull() {
        String nicknameCliente = null;
        String nombreLista = "Para Cocinar";
        
        assertTrue(guardarLista(nicknameCliente, nombreLista, "Cliente null", false));
    }
    
    @Test
    public void guardarListaRepetida() {
        String nicknameCliente = "Heisenberg";
        String nombreLista = "Noche De La Nostalgia";
        
        assertTrue(guardarLista(nicknameCliente, nombreLista, "Lista repetida", false));
    }
    
}