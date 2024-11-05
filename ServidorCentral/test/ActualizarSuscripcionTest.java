import espotify.logica.Suscripcion;
import espotify.persistencia.ControladoraPersistencia;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ActualizarSuscripcionTest {
    
    private static ControladoraPersistencia cp;
    private static CargarDatosTest cdt;
    
    public ActualizarSuscripcionTest() {
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

    public Boolean verificarActualizacion(Long idSusc, Suscripcion.EstadoSuscripcion estadoNuevo, Date fechaSusc) {
        Suscripcion susc = cp.suscripcionJpa.findSuscripcion(idSusc);
        if (susc == null) return false;
        
        Suscripcion.EstadoSuscripcion estado = susc.getEstadoSuscripcion();
        if (!estado.equals(estadoNuevo)) return false;
        
        Date fechaSuscripcion = susc.getFechaSuscripcion();
        if (!fechaSuscripcion.equals(fechaSusc)) return false;
        
        return true;
    }
    
    public Boolean actualizarEstadoSuscripcion(
            Long idSusc, 
            Suscripcion.EstadoSuscripcion estadoNuevo, 
            Date fechaNueva,
            String test,
            Boolean expected) {
        Boolean fueActualizada = false;
    
        try {
            cp.ActualizarEstadoSuscripcion(
                    idSusc, 
                    estadoNuevo, 
                    fechaNueva);
            fueActualizada = verificarActualizacion(idSusc, estadoNuevo, fechaNueva);
        } catch (Exception e) {
            fueActualizada = false;
        }
        
        if (fueActualizada.equals(expected)) {
            System.out.println("Test Actualizar Estado Suscripcion: " + test + ". OK");
            return true;
        } else {
            System.out.println("Test Actualizar Estado Suscripcion: " + test + ". FAIL");
            return false;
        }
    }
    
    public Boolean actualizarSuscripcionesVencidas(Long idSuscAModificar, String test, Boolean expected) {
        
        //Si la suscripcion a modificar es una suscripcion en estado Vigente cuya fecha de vencimiento ya paso,
        //debera ser cambiada a Vencida tras ejecuar cp.actualizarSuscripcionesVencidas();
        Boolean fueActualizada = false;
        
        try {
            cp.actualizarSuscripcionesVencidas();
            Suscripcion susc = cp.suscripcionJpa.findSuscripcion(idSuscAModificar);
            if (susc.getEstadoSuscripcion().equals(Suscripcion.EstadoSuscripcion.Vencida)) {
                fueActualizada = true;
            } else {
                fueActualizada = false;
            }
        } catch (Exception e) {
            fueActualizada = false;
        }
        
        if (fueActualizada.equals(expected)) {
            System.out.println("Test Actualizar Suscripciones Vencidas: " + test + ". OK");
            return true;
        } else {
            System.out.println("Test Actualizar Suscripciones Vencidas: " + test + ". FAIL");
            return false;
        }        
    }
    
    
    /*
        Datos de prueba - Suscripciones
        1 - Pendiente - 2024-10-09 - Semanal - Eleven11
        2 - Vigente - 2024-10-09 - Semanal - ppArgento
        3 - Cancelada - 2024-10-09 - Semanal - el_padrino
        4 - Vencida - 2024-10-09 - Semanal - lachiqui
    */
    @Test
    public void actualizarEstadoDatosValidos1() {
        Long idSusc = 1L;
        Suscripcion.EstadoSuscripcion estadoNuevo = Suscripcion.EstadoSuscripcion.Cancelada;
        Date fechaNueva = new Date();
        
        //Suscripcion 1: Pendiente --> Cancelada con fecha de hoy
        
        assertTrue(actualizarEstadoSuscripcion(
                idSusc, 
                estadoNuevo, 
                fechaNueva, 
                "Datos Validos 1", 
                true));
    }
    
    @Test
    public void actualizarEstadoDatosValidos2() {
        Long idSusc = 2L;
        Suscripcion.EstadoSuscripcion estadoNuevo = Suscripcion.EstadoSuscripcion.Cancelada;
        Date fechaNueva = new Date();
        
        //Suscripcion 2: Vigente --> Cancelada con fecha de hoy
        
        assertTrue(actualizarEstadoSuscripcion(
                idSusc, 
                estadoNuevo, 
                fechaNueva, 
                "Datos Validos 2", 
                true));
    }
    
    @Test
    public void actualizarEstadoDatosValidos3() {
        Long idSusc = 4L;
        Suscripcion.EstadoSuscripcion estadoNuevo = Suscripcion.EstadoSuscripcion.Vigente;
        Date fechaNueva = new Date();
        
        //Suscripcion 4: Vencida --> Vigente con fecha de hoy
        
        assertTrue(actualizarEstadoSuscripcion(
                idSusc, 
                estadoNuevo, 
                fechaNueva, 
                "Datos Validos 3", 
                true));
    }
    
    @Test
    public void actualizarEstadoSuscripcionCancelada() {
        Long idSusc = 3L;
        Suscripcion.EstadoSuscripcion estadoNuevo = Suscripcion.EstadoSuscripcion.Vigente;
        Date fechaNueva = new Date();
        
        //Suscripcion 3: Cancelada --> Vigente con fecha de hoy
        //no debe permitirse la modificacion de una suscripcion cancelada
        
        assertTrue(actualizarEstadoSuscripcion(
                idSusc, 
                estadoNuevo, 
                fechaNueva, 
                "Suscripcion Cancelada", 
                false));
    }
    
     @Test
    public void actualizarEstadoSuscripcionInexistente() {
        Long idSusc = 99999L;
        Suscripcion.EstadoSuscripcion estadoNuevo = Suscripcion.EstadoSuscripcion.Vigente;
        Date fechaNueva = new Date();
        
        assertTrue(actualizarEstadoSuscripcion(
                idSusc, 
                estadoNuevo, 
                fechaNueva, 
                "Suscripcion Inexistente", 
                false));
    }
    
    @Test
    public void actualizarEstadoSuscripcionNull() {
        Long idSusc = null;
        Suscripcion.EstadoSuscripcion estadoNuevo = Suscripcion.EstadoSuscripcion.Vigente;
        Date fechaNueva = new Date();
        
        //Suscripcion 3: Cancelada --> Vigente con fecha de hoy
        //no debe permitirse la modificacion de una suscripcion cancelada
        
        assertTrue(actualizarEstadoSuscripcion(
                idSusc, 
                estadoNuevo, 
                fechaNueva, 
                "Suscripcion Null", 
                false));
    }
    
    @Test
    public void actualizarEstadoSuscripcionEstadoNull() {
        Long idSusc = 1L;
        Suscripcion.EstadoSuscripcion estadoNuevo = null;
        Date fechaNueva = new Date();
        
        assertTrue(actualizarEstadoSuscripcion(
                idSusc, 
                estadoNuevo, 
                fechaNueva, 
                "Estado null", 
                false));
    }
    
    @Test
    public void actualizarEstadoSuscripcionFechaNull() {
        Long idSusc = 1L;
        Suscripcion.EstadoSuscripcion estadoNuevo = Suscripcion.EstadoSuscripcion.Vigente;
        Date fechaNueva = null;
        
        assertTrue(actualizarEstadoSuscripcion(
                idSusc, 
                estadoNuevo, 
                fechaNueva, 
                "Fecha null", 
                false));
    }
    
    /*
        Datos de prueba - Suscripciones vigentes vencidas
        5 - Vigente - 2024-01-01 - Mensual - benKenobi
        6 - Vigente - 2023-01-01 - Anual - scarlettO
    */
    
    @Test
    public void actualizarSuscripcionesVencidas1() {
        Long idSusc = 5L;
        
        assertTrue(actualizarSuscripcionesVencidas(
                idSusc, 
                "Suscripcion 5 Vigente --> Vencida", 
                true));
    }
    
    @Test
    public void actualizarSuscripcionesVencidas2() {
        Long idSusc = 1L;
        //La suscripcion 1 no debe ser actualizada porque esta en estado pendiente 
        assertTrue(actualizarSuscripcionesVencidas(
                idSusc, 
                "Suscripcion 1 Pendiente --> Vencida", 
                false));
    }
    
    @Test
    public void actualizarSuscripcionesVencidas3() {
        Long idSusc = 6L;
        
        assertTrue(actualizarSuscripcionesVencidas(
                idSusc, 
                "Suscripcion 6 Vigente --> Vencida", 
                true));
    }
    
}