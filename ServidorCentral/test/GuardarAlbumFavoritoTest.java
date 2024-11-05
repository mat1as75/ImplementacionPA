import espotify.logica.Album;
import espotify.logica.Cliente;
import espotify.persistencia.ControladoraPersistencia;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class GuardarAlbumFavoritoTest {
    
    private static ControladoraPersistencia cp;
    private static CargarDatosTest cdt;
    
    public GuardarAlbumFavoritoTest() {
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

    public Boolean verificarGuardadoAlbum(String nickname, Long idAlbum) throws Exception {
        Cliente cliente = cp.cliJpa.findCliente(nickname);
        
        if (cliente == null) {
            throw new Exception("No se pudo verificar porque el cliente no existe.");
        }
        
        List<Album> albumsFavoritos = cliente.getMisAlbumesFav();
        
        for (Album alb : albumsFavoritos) {
            if (alb.getIdAlbum().equals(idAlbum)) {
                return true;
            }
        }
        return false;
    }
    
    public Boolean guardarAlbum(String nickname, Long idAlbum, String test, Boolean expected) {
        
        Boolean fueGuardado = false;
        
        try {
            cp.GuardarAlbumFavorito(nickname, idAlbum);
            fueGuardado = verificarGuardadoAlbum(nickname, idAlbum);
        } catch (Exception e) {
            fueGuardado = false;
        }
        
        if (fueGuardado.equals(expected)) {
            System.out.println("Test de Guardar Album Favorito: " + test + ". OK");
            return true;
        } else {
            System.out.println("Test de Guardar Album Favorito: " + test + ". FAIL");
            return false;
        }
    }
    
    /*
        Datos de prueba - Albums favoritos
        benKenobi - 8, 9
        cbochinche - 11
        el_padrino - 2, 8, 9
    */
    
    @Test
    public void guardarAlbumDatosValidos1() {
        String nicknameCliente = "el_padrino";
        Long idAlbum = 3L;
        
        assertTrue(guardarAlbum(nicknameCliente, idAlbum, "Datos validos 1", true));
    }
    
    @Test
    public void guardarAlbumDatosValidos2() {
        String nicknameCliente = "benKenobi";
        Long idAlbum = 1L;
        
        assertTrue(guardarAlbum(nicknameCliente, idAlbum, "Datos validos 2", true));
    }
    
    @Test
    public void guardarAlbumDatosValidos3() {
        String nicknameCliente = "lachiqui";
        Long idAlbum = 5L;
        
        assertTrue(guardarAlbum(nicknameCliente, idAlbum, "Datos validos 3", true));
    }
    
    @Test
    public void guardarAlbumInexistente() {
        String nicknameCliente = "lachiqui";
        Long idAlbum = 99999L;
        
        assertTrue(guardarAlbum(nicknameCliente, idAlbum, "Album inexistente", false));
    }
    
    @Test
    public void guardarAlbumClienteInexistente() {
        String nicknameCliente = "cliente inexistente";
        Long idAlbum = 1L;
        
        assertTrue(guardarAlbum(nicknameCliente, idAlbum, "Cliente inexistente", false));
    }
    
    @Test
    public void guardarAlbumNull() {
        String nicknameCliente = "lachiqui";
        Long idAlbum = 5L;
        
        assertTrue(guardarAlbum(nicknameCliente, idAlbum, "Album null", false));
    }
    
    @Test
    public void guardarAlbumClienteNull() {
        String nicknameCliente = null;
        Long idAlbum = 8L;
        
        assertTrue(guardarAlbum(nicknameCliente, idAlbum, "Cliente null", false));
    }
    
    @Test
    public void guardarAlbumRepetido() {
        String nicknameCliente = "cbochinche";
        Long idAlbum = 11L;
        
        assertTrue(guardarAlbum(nicknameCliente, idAlbum, "Album repetido", false));
    }
    
}