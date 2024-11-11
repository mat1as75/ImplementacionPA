
import espotify.logica.Tema;
import espotify.persistencia.ControladoraPersistencia;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ContadoresDeTemaTest {
    
    private static ControladoraPersistencia cp;
    private static CargarDatosTest cdt;
    
    public ContadoresDeTemaTest() {
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

    public Boolean verificarIncrementoReproducciones(Long idTema, Long cantPrevia) {
        Tema tema = cp.temaJpa.findTema(idTema);
        Long cantActual = tema.getCantidadReproducciones();
        
        return cantActual > cantPrevia;
    }
    
    public Boolean incrementarReproducciones(
            Long idTema, String test, Boolean expected) {
    
        Long cantPrevia = 0L;
        if (idTema != null) {
            Tema tema = cp.temaJpa.findTema(idTema);
            if (tema != null) {
                cantPrevia = tema.getCantidadReproducciones();
            }
        }
        
        Boolean fueIncrementado = false;
        
        try {
            cp.incrementarReproduccionesDeTema(idTema);
            fueIncrementado = verificarIncrementoReproducciones(idTema, cantPrevia);
        } catch (Exception e) {
            fueIncrementado = false;
        }
    
        if (fueIncrementado.equals(expected)) {
            System.out.println("Test de Incrementar Reproducciones: " + test + ". OK");
            return true;
        } else {
            System.out.println("Test de Incrementar Reproducciones: " + test + ". FAIL");
            return false;
        }
    }
    
    public Boolean verificarIncrementoDescargasOVisitas(Long idTema, Long cantPrevia) {
        Tema tema = cp.temaJpa.findTema(idTema);
        Long cantActual = tema.getCantidadDescargasOVisitas();
        
        return cantActual > cantPrevia;
    }
    
    public Boolean incrementarDescargasOVisitas(
            Long idTema, String test, Boolean expected) {
    
        Long cantPrevia = 0L;
        if (idTema != null) {
            Tema tema = cp.temaJpa.findTema(idTema);
            if (tema != null) {
                cantPrevia = tema.getCantidadDescargasOVisitas();
            }
        }
        
        Boolean fueIncrementado = false;
        
        try {
            cp.incrementarDescargasOVisitasDeTema(idTema);
            fueIncrementado = verificarIncrementoDescargasOVisitas(idTema, cantPrevia);
        } catch (Exception e) {
            fueIncrementado = false;
        }
    
        if (fueIncrementado.equals(expected)) {
            System.out.println("Test de Incrementar Descargas/Visitas: " + test + ". OK");
            return true;
        } else {
            System.out.println("Test de Incrementar Descargas/Visitas: " + test + ". FAIL");
            return false;
        }
    }
    
    @Test
    public void incReproduccionesTemaValido1() {
        Long idTema = 71L;
        
        assertTrue(incrementarReproducciones(
                idTema, 
                "Tema Valido 71", 
                true));
    }
    
    @Test
    public void incReproduccionesTemaValido2() {
        Long idTema = 101L;
        
        assertTrue(incrementarReproducciones(
                idTema, 
                "Tema Valido 101", 
                true));
    }
    
    @Test
    public void incReproduccionesTemaNull() {
        Long idTema = null;
        
        assertTrue(incrementarReproducciones(
                idTema, 
                "Tema null", 
                false));
    }
    
    @Test
    public void incReproduccionesTemaInexistente() {
        Long idTema = 999999L;
        
        assertTrue(incrementarReproducciones(
                idTema, 
                "Tema inexistente", 
                false));
    }
    
    @Test
    public void incDescargasOVisitasTemaValido1() {
        Long idTema = 41L;
        
        assertTrue(incrementarDescargasOVisitas(
                idTema, 
                "Tema Valido 41", 
                true));
    }
    
    @Test
    public void incDescargasOVisitasTemaValido2() {
        Long idTema = 32L;
        
        assertTrue(incrementarDescargasOVisitas(
                idTema, 
                "Tema Valido 32", 
                true));
    }
    
    @Test
    public void incDescargasOVisitasTemaNull() {
        Long idTema = null;
        
        assertTrue(incrementarDescargasOVisitas(
                idTema, 
                "Tema null", 
                false));
    }
    
    @Test
    public void incDescargasOVisitasTemaInexistente() {
        Long idTema = 999999L;
        
        assertTrue(incrementarDescargasOVisitas(
                idTema, 
                "Tema inexistente", 
                false));
    }
}
