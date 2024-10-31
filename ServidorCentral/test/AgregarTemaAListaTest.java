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

public class AgregarTemaAListaTest {
    private static ControladoraPersistencia cp;
    private static CargarDatosTest cdt;
    
    public AgregarTemaAListaTest() {
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
        //reseteo los datos al finalizar los tests de esta clase
        cdt.resetDatosDePrueba();
    }
    
    @Before
    public void setUp() {}
    
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
        
        Boolean temaPerteneceALista = false;
        Boolean listaEstaAsociadaATema = false;
        
        try {
            //agrego el tema
            cp.agregarTemaALista(idTema, nombreLista);
            //verifico que se haya agregado
            //si agregarTemaALista falla por un tema repetido o recurso inexistente, 
            //esta linea no se ejecuta y se entra en el catch
            temaPerteneceALista = temaPerteneceALista(nombreLista, idTema);
            listaEstaAsociadaATema = temaEstaAsociadoALista(nombreLista, idTema);
        } catch (Exception e) {
            if (//Muestro el mensaje de error si es un error no esperado
                    !(e instanceof DatabaseUpdateException) 
                    && !(e instanceof NonexistentEntityException)
                    && !(e instanceof InvalidDataException)
                    ) {
                System.out.println(e.getClass().getName() + ": " + e.getMessage());
            }
            temaPerteneceALista = false;
            listaEstaAsociadaATema = false;
        }
        
        if (temaPerteneceALista.equals(expected) && listaEstaAsociadaATema.equals(expected)) {
            System.out.println("Test Agregar Tema A Lista: " + test + ". OK");
            return true;
        } else {
            System.out.println("Test Agregar Tema A Lista: " + test + ". FAIL");
            return false;
        }
    }
    
    @Test
    public void agregarTemaDatosValidos1() {
        Long idTema = 11L;
        String nombreLista = "De Todo Un Poco";
        
        assertTrue(comprobarResultado(nombreLista, idTema, "Datos validos 1", true));
    }
    
    @Test
    public void agregarTemaDatosValidos2() {
        Long idTema = 31L;
        String nombreLista = "Fiesteras";
        
        assertTrue(comprobarResultado(nombreLista, idTema, "Datos validos 2", true));
    }
    
    @Test
    public void agregarTemaDatosValidos3() {
        Long idTema = 32L;
        String nombreLista = "Música Clásica";
        
        assertTrue(comprobarResultado(nombreLista, idTema, "Datos validos 3", true));
    }
    
    @Test
    public void agregarTemaDatosValidos4() {
        Long idTema = 81L;
        String nombreLista = "Rock En Español";
        
        assertTrue(comprobarResultado(nombreLista, idTema, "Datos validos 4", true));
    }
    
    @Test
    public void agregarTemaListaInexistente1() {
        Long idTema = 11L;
        String nombreLista = "lista inexistente";
        
        assertTrue(comprobarResultado(nombreLista, idTema, "Lista inexistente 1", false));
    }
    
    @Test
    public void agregarTemaListaInexistente2() {
        Long idTema = 81L;
        String nombreLista = "lista inexistente";
        
        assertTrue(comprobarResultado(nombreLista, idTema, "Lista inexistente 2", false));
    }
    
    @Test
    public void agregarTemaInexistente1() {
        Long idTema = 99999L;
        String nombreLista = "Música Clásica";
        
        assertTrue(comprobarResultado(nombreLista, idTema, "Tema inexistente 1", false));
    }
    
    @Test
    public void agregarTemaInexistente2() {
        Long idTema = 88888L;
        String nombreLista = "Fiesteras";
        
        assertTrue(comprobarResultado(nombreLista, idTema, "Tema inexistente 2", false));
    }
    
    @Test
    public void agregarTemaListaNull() {
        Long idTema = 999L;
        String nombreLista = null;
        
        assertTrue(comprobarResultado(nombreLista, idTema, "Lista null", false));
    }
    
    @Test
    public void agregarTemaNull() {
        Long idTema = null;
        String nombreLista = "Fiesteras";
        
        assertTrue(comprobarResultado(nombreLista, idTema, "Tema null", false));
    }
    
    @Test
    public void agregarTemaRepetido1() {
        Long idTema = 11L;
        String nombreLista = "Fiesteras";
        
        assertTrue(comprobarResultado(nombreLista, idTema, "Tema repetido 1", false));
    }
    
    @Test
    public void agregarTemaRepetido2() {
        Long idTema = 51L;
        String nombreLista = "De Todo Un Poco";
        
        assertTrue(comprobarResultado(nombreLista, idTema, "Tema repetido 2", false));
    }
}
