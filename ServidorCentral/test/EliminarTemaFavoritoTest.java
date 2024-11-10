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

public class EliminarTemaFavoritoTest {
    
    private static ControladoraPersistencia cp;
    private static CargarDatosTest cdt;
    
    public EliminarTemaFavoritoTest() {
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

    public Boolean verificarEliminacion(String nickname, Long idTema, Long cantPreviaFavoritos) throws Exception {
        Cliente cliente = cp.cliJpa.findCliente(nickname);
        
        if (cliente == null) {
            throw new Exception("No se pudo verificar porque el cliente no existe.");
        }
        
        //obtengo la cantidad actual de favoritos del tema, post-actualizacion si ocurrio
        Boolean decrementoCantFavoritos = false;
        Long cantActualFavoritos = 0L;
        if (idTema != null) {
            Tema tema = cp.temaJpa.findTema(idTema);
            if (tema != null) {
                cantActualFavoritos = tema.getCantidadFavoritos();
            }
        }
        //si la cantidad actual es menor a la previa entonces se actualizo correctamente
        if (cantActualFavoritos < cantPreviaFavoritos) {
            decrementoCantFavoritos = true;
        }
        
        List<Tema> temasFavoritos = cliente.getMisTemasFav();
        Boolean fueEliminado = true;
        
        //si encuentro el tema en la lista de favoritos del cliente, entonces no fue eliminado
        for (Tema t : temasFavoritos) {
            if (t.getIdTema().equals(idTema)) {
                fueEliminado = false;
            }
        }
        
        return fueEliminado;
    }
    
    public Boolean eliminarTemaFavorito(String nickname, Long idTema, String test, Boolean expected) {
        
        Boolean fueEliminado = false;
        //obtengo la cantidad de favoritos previo a la actualizacion
        Long cantPreviaFavoritos = 0L;
        if (idTema != null) {
            Tema tema = cp.temaJpa.findTema(idTema);
            if (tema != null) {
                cantPreviaFavoritos = tema.getCantidadFavoritos();
            }
        }
        
        try {
            cp.EliminarTemaFavorito(nickname, idTema);
            fueEliminado = verificarEliminacion(nickname, idTema, cantPreviaFavoritos);
        } catch (Exception e) {
            fueEliminado = false;
        }
        
        if (fueEliminado.equals(expected)) {
            System.out.println("Test de Eliminar Tema Favorito: " + test + ". OK");
            return true;
        } else {
            System.out.println("Test de Eliminar Tema Favorito: " + test + ". FAIL");
            return false;
        }
    }
    
    /*
        Datos de prueba - Temas Favoritos:
        cbochinche - 91
        Eleven11 - 101
        el_padrino - 71
        ppArgento - 61
    */
    
    @Test
    public void eliminarTemaFavDatosValidos1() {
        String nicknameCliente = "cbochinche";
        Long idTema = 91L;
        
        assertTrue(eliminarTemaFavorito(nicknameCliente, idTema, "Datos validos 1", true));
    }
    
    @Test
    public void eliminarTemaFavDatosValidos2() {
        String nicknameCliente = "Eleven11";
        Long idTema = 101L;
        
        assertTrue(eliminarTemaFavorito(nicknameCliente, idTema, "Datos validos 2", true));
    }
    
    @Test
    public void eliminarTemaFavDatosValidos3() {
        String nicknameCliente = "el_padrino";
        Long idTema = 71L;
        
        assertTrue(eliminarTemaFavorito(nicknameCliente, idTema, "Datos validos 3", true));
    }
    
    @Test
    public void eliminarTemaFavClienteInexistente() {
        String nicknameCliente = "cliente inexistente";
        Long idTema = 71L;
        
        assertTrue(eliminarTemaFavorito(nicknameCliente, idTema, "Cliente inexistente", false));
    }
    
    @Test
    public void eliminarTemaFavInexistente() {
        String nicknameCliente = "ppArgento";
        Long idTema = 99999L;
        
        assertTrue(eliminarTemaFavorito(nicknameCliente, idTema, "Tema inexistente", false));
    }
    
    @Test
    public void eliminarTemaFavNoPresente() {
        String nicknameCliente = "ppArgento";
        Long idTema = 12L;
        
        assertTrue(eliminarTemaFavorito(nicknameCliente, idTema, "Tema no presente", false));
    }
    
    @Test
    public void eliminarTemaFavNull() {
        String nicknameCliente = "ppArgento";
        Long idTema = null;
        
        assertTrue(eliminarTemaFavorito(nicknameCliente, idTema, "Tema null", false));
    }
    
    @Test
    public void eliminarTemaFavClienteNull() {
        String nicknameCliente = null;
        Long idTema = 61L;
        
        assertTrue(eliminarTemaFavorito(nicknameCliente, idTema, "Cliente null", false));
    }
}
