import espotify.logica.ListaReproduccion;
import espotify.logica.Tema;
import espotify.persistencia.ControladoraPersistencia;
import espotify.persistencia.exceptions.DatabaseUpdateException;
import espotify.persistencia.exceptions.InvalidDataException;
import espotify.persistencia.exceptions.NonexistentEntityException;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class QuitarTemaDeListaTest {
    
    private static ControladoraPersistencia cp;
    private static CargarDatosTest cdt;
    
    public QuitarTemaDeListaTest() {
        cp = new ControladoraPersistencia();
        cdt = new CargarDatosTest();
        //reseteo los datos al iniciar
        cdt.resetDatosDePrueba();
    }
    
    /* Listas en datos de prueba
    
        Lista Particular "De Todo Un Poco"
        Temas: 31, 32, 51, 81
    
        Lista Particular "Fiesteras"
        Temas: 11, 12, 13, 42, 131
    
        Lista por Defecto "Música Clásica"
        Temas: 81, 91
    
        Lista por Defecto "Rock En Español"
        Temas: 61, 71, 72
    */
    
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

    public Boolean temaPerteneceALista(String nombreLista, Long idTema) {
        Boolean pertenece = false;
        
        try {
            //obtengo la lista y sus temas
            ListaReproduccion listaRep = cp.lreprodccJpa.findListaReproduccion(nombreLista);
            List<Tema> temasDeLista = listaRep.getMisTemas();
            //verifico que el tema haya sido asociado a la lista
            for (Tema t : temasDeLista) {
                if (t.getIdTema().equals(idTema)) {
                    pertenece = true;
                    break;
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return pertenece;
    }
    
    public Boolean temaEstaAsociadoALista(String nombreLista, Long idTema) {
        Boolean estaAsociado = false;
        
        try {
            //obtengo el tema y sus listas asociadas
            Tema tema = cp.temaJpa.findTema(idTema);
            List<ListaReproduccion> listasDeTema = tema.getMisReproducciones();
            //verifico que el tema haya sido asociado a la lista
            for (ListaReproduccion lrep : listasDeTema) {
                if (lrep.getNombreLista().equals(nombreLista)) {
                    estaAsociado = true;
                    break;
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return estaAsociado;
    }
    
    public Boolean comprobarResultado(String nombreLista, Long idTema, String test, Boolean expected) {
        
        Boolean temaRemovidoDeLista = false;
        Boolean listaRemovidaDeTema = false;
        
        try {
            //agrego el tema
            cp.quitarTemaDeLista(idTema, nombreLista);
            //verifico que se haya agregado
            //si quitarTemaDeLista falla por un tema repetido o recurso inexistente, 
            //esta linea no se ejecuta y se entra en el catch
            temaRemovidoDeLista = !temaPerteneceALista(nombreLista, idTema);
            listaRemovidaDeTema = !temaEstaAsociadoALista(nombreLista, idTema);
        } catch (Exception e) {
            if (//Muestro el mensaje de error si es un error no esperado
                    !(e instanceof DatabaseUpdateException) 
                    && !(e instanceof NonexistentEntityException)
                    && !(e instanceof InvalidDataException)
                    ) {
                System.out.println(e.getMessage());
            }
            temaRemovidoDeLista = false;
        }
        
        if (temaRemovidoDeLista.equals(expected) && listaRemovidaDeTema.equals(expected)) {
            System.out.println("Test Quitar Tema De Lista: " + test + ". OK");
            return true;
        } else {
            System.out.println("Test Quitar Tema De Lista: " + test + ". FAIL");
            return false;
        }
    }
    
    @Test
    public void quitarTemaDatosValidos1() {
        Long idTema = 11L;
        String nombreLista = "Fiesteras";
    
        assertTrue(comprobarResultado(nombreLista, idTema, "Datos Validos 1", true));
    }
    
    @Test
    public void quitarTemaDatosValidos2() {
        Long idTema = 31L;
        String nombreLista = "De Todo Un Poco";
    
        assertTrue(comprobarResultado(nombreLista, idTema, "Datos Validos 2", true));
    }
    
    @Test
    public void quitarTemaDatosValidos3() {
        Long idTema = 81L;
        String nombreLista = "Música Clásica";
    
        assertTrue(comprobarResultado(nombreLista, idTema, "Datos Validos 3", true));
    }
    
    @Test
    public void quitarTemaDatosValidos4() {
        Long idTema = 61L;
        String nombreLista = "Rock En Español";
    
        assertTrue(comprobarResultado(nombreLista, idTema, "Datos Validos 4", true));
    }
    
    @Test
    public void quitarTemaInexistente() {
        Long idTema = 99999L;
        String nombreLista = "Rock En Español";
        
        assertTrue(comprobarResultado(nombreLista, idTema, "Tema inexistente", false));
    }
    
    @Test
    public void quitarTemaNull() {
        Long idTema = null;
        String nombreLista = "Rock En Español";
        
        assertTrue(comprobarResultado(nombreLista, idTema, "Tema null", false));
    }
    
    @Test
    public void quitarTemaListaInexistente() {
        Long idTema = 11L;
        String nombreLista = "lista inexistente";
        
        assertTrue(comprobarResultado(nombreLista, idTema, "Lista inexistente", false));
    }
    
    @Test
    public void quitarTemaListaNull() {
        Long idTema = 11L;
        String nombreLista = null;
        
        assertTrue(comprobarResultado(nombreLista, idTema, "Lista null", false));
    }
    
}
