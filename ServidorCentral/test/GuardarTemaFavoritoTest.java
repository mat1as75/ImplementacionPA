
import espotify.logica.Cliente;
import espotify.logica.Tema;
import espotify.persistencia.ControladoraPersistencia;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class GuardarTemaFavoritoTest {
    
    private static ControladoraPersistencia cp;
    private static CargarDatosTest cdt;
    
    public GuardarTemaFavoritoTest() {
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

    public Boolean verificarGuardadoTema(String nickname, Long idTema, Long cantPreviaFavoritos) throws Exception {
        Cliente cliente = cp.cliJpa.findCliente(nickname);
        
        if (cliente == null) {
            throw new Exception("No se pudo verificar porque el cliente no existe.");
        }
        
        List<Tema> temasFavoritos = cliente.getMisTemasFav();
        
        for (Tema t : temasFavoritos) {
            if (t.getIdTema().equals(idTema) && t.getCantidadFavoritos() > cantPreviaFavoritos) {
                return true;
            }
        }
        return false;
    }
    
    public Boolean guardarTema(String nickname, Long idTema, String test, Boolean expected) {
        
        Boolean fueGuardado = false;
        
        Long cantPreviaFavoritos = 0L;
        if (idTema != null) {
            Tema tema = cp.temaJpa.findTema(idTema);
            if (tema != null) {
                cantPreviaFavoritos = tema.getCantidadFavoritos();
            }
        }
        
        try {
           cp.GuardarTemaFavorito(nickname, idTema);
           fueGuardado = verificarGuardadoTema(nickname, idTema, cantPreviaFavoritos);
        } catch (Exception e) {
            fueGuardado = false;
        }
        
        if (fueGuardado.equals(expected)) {
            System.out.println("Test de Guardar Tema Favorito: " + test + ". OK");
            return true;
        } else {
            System.out.println("Test de Guardar Tema Favorito: " + test + ". FAIL");
            return false;
        }
    }
    
    /*
        Datos de prueba - Temas Favoritos:
        cbochinche - 91
        Eleven11 - 101
        el_padrino - 71
        ppArgento - 61
        lachiqui - /
    */
    
    @Test
    public void guardarTemaDatosValidos1() {
        String nicknameCliente = "ppArgento";
        Long idTema = 71L;
        
        assertTrue(guardarTema(nicknameCliente, idTema, "Datos validos 1", true));
    }
    
    @Test
    public void guardarTemaDatosValidos2() {
        String nicknameCliente = "lachiqui";
        Long idTema = 111L;
        
        assertTrue(guardarTema(nicknameCliente, idTema, "Datos validos 2", true));
    }
    
    @Test
    public void guardarTemaDatosValidos3() {
        String nicknameCliente = "el_padrino";
        Long idTema = 101L;
        
        assertTrue(guardarTema(nicknameCliente, idTema, "Datos validos 3", true));
    }
    
    @Test
    public void guardarTemaClienteInexistente() {
        String nicknameCliente = "cliente inexistente";
        Long idTema = 71L;
        
        assertTrue(guardarTema(nicknameCliente, idTema, "Cliente inexistente", false));
    }
    
    @Test
    public void guardarTemaInexistente() {
        String nicknameCliente = "ppArgento";
        Long idTema = 99999L;
        
        assertTrue(guardarTema(nicknameCliente, idTema, "Tema inexistente", false));
    }
    
    @Test
    public void guardarTemaRepetido() {
        String nicknameCliente = "ppArgento";
        Long idTema = 61L;
        
        assertTrue(guardarTema(nicknameCliente, idTema, "Tema repetido", false));
    }
    
    @Test
    public void guardarTemaNull() {
        String nicknameCliente = "ppArgento";
        Long idTema = null;
        
        assertTrue(guardarTema(nicknameCliente, idTema, "Tema null", false));
    }
    
    @Test
    public void guardarTemaClienteNull() {
        String nicknameCliente = null;
        Long idTema = 61L;
        
        assertTrue(guardarTema(nicknameCliente, idTema, "Cliente null", false));
    }
    
}