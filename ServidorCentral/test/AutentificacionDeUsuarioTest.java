import espotify.DataTypes.DTArtista;
import espotify.DataTypes.DTCliente;
import espotify.DataTypes.DTUsuario;
import espotify.persistencia.ControladoraPersistencia;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class AutentificacionDeUsuarioTest {
    
    private static ControladoraPersistencia cp;
    private static CargarDatosTest cdt;
    
    public AutentificacionDeUsuarioTest() {
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

    public Boolean verificarAutentificacion(String id, String passw, String tipoUsuario, String test, Boolean expected) {
    
        DTUsuario dtUsuario = cp.getUsuarioAutentificado(id, passw);
        Boolean fueAutentificado = dtUsuario != null;
        Boolean datosDeTipoCorrecto = true;
        
        if (dtUsuario != null) {
            if (tipoUsuario.equals("Artista")) {
                datosDeTipoCorrecto = dtUsuario instanceof DTArtista;
            } else if (tipoUsuario.equals("Cliente")) {
                datosDeTipoCorrecto = dtUsuario instanceof DTCliente;
            } else {
                datosDeTipoCorrecto = false;
            }
        }
        
        if (fueAutentificado.equals(expected) && datosDeTipoCorrecto) {
            System.out.println("Test Autentificacion De Usuario: " + test + ". OK");
            return true;
        } else {
            System.out.println("Test Autentificacion De Usuario: " + test + ". FAIL");
            return false;
        }
    }
    
    @Test
    public void autenticarUsuarioDatosValidos1() {
        String id = "vpeople";
        String passw = "1234";
        
        assertTrue(verificarAutentificacion(
                id, passw, 
                "Artista", 
                "Datos validos, vpeople", 
                true));
    }
    
    @Test
    public void autenticarUsuarioDatosValidos2() {
        String id = "vpeople@tuta.io";
        String passw = "1234";
        
        assertTrue(verificarAutentificacion(
                id, passw, 
                "Artista",
                "Datos validos, vpeople@tuta.io", 
                true));
    }
    
    @Test
    public void autenticarUsuarioDatosValidos3() {
        String id = "chaiko";
        String passw = "1234";
        
        assertTrue(verificarAutentificacion(
                id, passw, 
                "Artista",
                "Datos validos, chaiko", 
                true));
    }
    
    @Test
    public void autenticarUsuarioDatosValidos4() {
        String id = "chaiko@tuta.io";
        String passw = "1234";
        
        assertTrue(verificarAutentificacion(
                id, passw, 
                "Artista",
                "Datos validos, chaiko@tuta.io", 
                true));
    }
    
    @Test
    public void autenticarUsuarioDatosValidos5() {
        String id = "ppArgento";
        String passw = "1234";
        
        assertTrue(verificarAutentificacion(
                id, passw, 
                "Cliente",
                "Datos validos, ppArgento", 
                true));
    }
    
    @Test
    public void autenticarUsuarioDatosValidos6() {
        String id = "dyangounchained@gmail.com";
        String passw = "1234";
        
        assertTrue(verificarAutentificacion(
                id, passw, 
                "Artista",
                "Datos validos, dyangounchained@gmail.com", 
                true));
    }
    
    @Test
    public void autenticarUsuarioIdInexistente() {
        String id = "usuario inexistente";
        String passw = "1234";
        
        assertTrue(verificarAutentificacion(
                id, passw, 
                "",
                "Id inexistente", 
                false));
    }
    
    @Test
    public void autenticarUsuarioPasswIncorrecto() {
        String id = "ppArgento";
        String passw = "99999999";
        
        assertTrue(verificarAutentificacion(
                id, passw, 
                "Cliente",
                "Password incorrecto", 
                false));
    }
    
    @Test
    public void autenticarUsuarioIdNull() {
        String id = null;
        String passw = "1234";
        
        assertTrue(verificarAutentificacion(
                id, passw, 
                "",
                "Id null", 
                false));
    }
}
