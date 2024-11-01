import espotify.DataTypes.DTCliente;
import espotify.DataTypes.DTSuscripcion;
import espotify.logica.Cliente;
import espotify.logica.Suscripcion;
import espotify.persistencia.ControladoraPersistencia;
import espotify.persistencia.exceptions.DatabaseUpdateException;
import espotify.persistencia.exceptions.NonexistentEntityException;
import espotify.persistencia.exceptions.PreexistingEntityException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class IngresarSuscripcionTest {
    
    private static ControladoraPersistencia cp;
    private static CargarDatosTest cdt;
    private static String nicknameClienteDePrueba = null;
    
    public IngresarSuscripcionTest() {
        cp = new ControladoraPersistencia();
        cdt = new CargarDatosTest();
        cdt.resetDatosDePrueba();
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
    public void tearDown() {}

    public void crearClienteDePrueba() {
        DTCliente dtClienteDePrueba = new DTCliente("clienteDePrueba", "1234");
        cp.AltaCliente(dtClienteDePrueba);
        
        if (cp.ExisteCliente(dtClienteDePrueba.getNickname())) {
            nicknameClienteDePrueba = dtClienteDePrueba.getNickname();
        }
    }
    
    public Boolean comprobarIngreso(String nickname, Suscripcion.TipoSuscripcion tipoSuscripcion) {
        DTSuscripcion dtSuscripcion = null;
        try {
            dtSuscripcion = cp.getDTSuscripcionDeCliente(nickname);
        } catch (Exception e) {
            return false;
        }
        
        if (dtSuscripcion == null) return false;
        if (!dtSuscripcion.getTipoSuscripcion().equals(tipoSuscripcion.toString())) return false;
        
        Long idSuscripcionDeCliente = dtSuscripcion.getIdSuscripcion();        
        DTSuscripcion dtSuscripcion2 = cp.getDTSuscripcion(idSuscripcionDeCliente);
        if (dtSuscripcion2 == null) return false;        
          
        return true;
    }
    
    public Boolean ingresarSuscripcion(
            String nickname, 
            Suscripcion.TipoSuscripcion tipoSuscripcion, 
            String test, 
            Boolean expected) {
        
        Boolean fueIngresada = false;
       
        try {
            cp.ingresarNuevaSuscripcion(nickname, tipoSuscripcion);
            fueIngresada = comprobarIngreso(nickname, tipoSuscripcion);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            fueIngresada = false;
        }
        
        if (fueIngresada.equals(expected)) {
            System.out.println("Test Ingresar Suscripcion: " + test + ". OK");
            return true;
        } else {
            System.out.println("Test Ingresar Suscripcion: " + test + ". FAIL");
            return false;
        }
    }
    
    @Test
    public void nuevaSuscripcionSemanal() {        
        crearClienteDePrueba();

        String nicknameCliente;
        if (nicknameClienteDePrueba != null) {
            nicknameCliente = nicknameClienteDePrueba;
        } else {
            nicknameCliente = "Heisenberg";
        }

        Suscripcion.TipoSuscripcion tipoSuscripcion = Suscripcion.TipoSuscripcion.Semanal;
        
        assertTrue(ingresarSuscripcion(
                nicknameCliente, 
                tipoSuscripcion, 
                "Nueva suscripcion semanal", 
                true));
    }
    
    @Test
    public void nuevaSuscripcionMensual() {
        crearClienteDePrueba();

        String nicknameCliente;
        if (nicknameClienteDePrueba != null) {
            nicknameCliente = nicknameClienteDePrueba;
        } else {
            nicknameCliente = "cbochinche";
        }
        Suscripcion.TipoSuscripcion tipoSuscripcion = Suscripcion.TipoSuscripcion.Mensual;
        
        assertTrue(ingresarSuscripcion(
                nicknameCliente, 
                tipoSuscripcion, 
                "Nueva suscripcion mensual", 
                true));
    }
    
    @Test
    public void nuevaSuscripcionAnual() {
        crearClienteDePrueba();

        String nicknameCliente;
        if (nicknameClienteDePrueba != null) {
            nicknameCliente = nicknameClienteDePrueba;
        } else {
            nicknameCliente = "cbochinche";
        }

        Suscripcion.TipoSuscripcion tipoSuscripcion = Suscripcion.TipoSuscripcion.Anual;
        
        assertTrue(ingresarSuscripcion(
                nicknameCliente, 
                tipoSuscripcion, 
                "Nueva suscripcion anual", 
                true));
    }
    
    @Test
    public void suscripcionConClienteInexistente() {
        
        String nicknameCliente = "cliente inexistente";
        Suscripcion.TipoSuscripcion tipoSuscripcion = Suscripcion.TipoSuscripcion.Semanal;
        
        assertTrue(ingresarSuscripcion(
                nicknameCliente, 
                tipoSuscripcion, 
                "Cliente inexistente", 
                false));
    }
    
    @Test
    public void suscripcionConTipoSuscripcionNull() {
        
        String nicknameCliente = "cbochinche";
        Suscripcion.TipoSuscripcion tipoSuscripcion = null;
        
        assertTrue(ingresarSuscripcion(
                nicknameCliente, 
                tipoSuscripcion, 
                "Tipo suscripcion null", 
                false));
    }
    
    @Test
    public void clienteConSuscripcionPrevia1() {
        
        String nicknameCliente = "Eleven11";
        Suscripcion.TipoSuscripcion tipoSuscripcion = Suscripcion.TipoSuscripcion.Semanal;
        
        assertTrue(ingresarSuscripcion(
                nicknameCliente, 
                tipoSuscripcion, 
                "Cliente con suscripcion previa 1", 
                false));
    }
    
    @Test
    public void clienteConSuscripcionPrevia2() {
        
        String nicknameCliente = "ppArgento";
        Suscripcion.TipoSuscripcion tipoSuscripcion = Suscripcion.TipoSuscripcion.Semanal;
        
        assertTrue(ingresarSuscripcion(
                nicknameCliente, 
                tipoSuscripcion, 
                "Cliente con suscripcion previa  2", 
                false));
    }
}
