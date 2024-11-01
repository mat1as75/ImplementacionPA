import espotify.DataTypes.DTDatosListaReproduccion;
import espotify.persistencia.ControladoraPersistencia;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ConsultaListaReproduccionTest {

    private static ControladoraPersistencia cp;
    private static CargarDatosTest cdt;

    public ConsultaListaReproduccionTest() {
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

    @Test
    public void getDatosListaPorDef1() {
        DTDatosListaReproduccion dtListaPorDef = cp.getDatosListaReproduccion("Por Defecto", "Rock En Español");
        int datosIncorrectos = 0;

        String nombreEsperado = "Rock En Español";
        String GeneroEsperado = "Rock Latino";
        String fechaCreacionEsperada = null;
        String fotoListaEsperada = null;

        if (!dtListaPorDef.getNombreLista().equalsIgnoreCase(nombreEsperado)) {
            datosIncorrectos++;
        }

        if (!dtListaPorDef.getGenero().equalsIgnoreCase(GeneroEsperado)) {
            datosIncorrectos++;
        }

        if (!(fechaCreacionEsperada == null ? dtListaPorDef.getFechaCreacion() == null : fechaCreacionEsperada.equals(dtListaPorDef.getFechaCreacion()))) {
            datosIncorrectos++;
        }

        if (!(fotoListaEsperada == null ? dtListaPorDef.getFotoLista() == null : fotoListaEsperada.equalsIgnoreCase(dtListaPorDef.getFotoLista()))) {
            datosIncorrectos++;
        }

        if (datosIncorrectos == 0) {
            System.out.println("Test Consultas de Datos: getDatosListaPorDef('Rock En Español') : OK");
        } else {
            System.out.println("Test Consultas de Datos: getDatosListaPorDef('Rock En Español') : FAIL");
        }
        
        assertTrue(datosIncorrectos == 0);
    }

    @Test
    public void getDatosListaPorDef2() {
        DTDatosListaReproduccion dtListaPorDef = cp.getDatosListaReproduccion("Por Defecto", "Noche De La Nostalgia");
        int datosIncorrectos = 0;

        String nombreEsperado = "Noche De La Nostalgia";
        String GeneroEsperado = "Pop Clásico";
        String fechaCreacionEsperada = null;
        String fotoListaEsperada = "./Resource/ImagenesPerfil/laNocheNostalgia.jpg";

        if (!dtListaPorDef.getNombreLista().equalsIgnoreCase(nombreEsperado)) {
            datosIncorrectos++;
        }

        if (!dtListaPorDef.getGenero().equalsIgnoreCase(GeneroEsperado)) {
            datosIncorrectos++;
        }

        if (!(fechaCreacionEsperada == null ? dtListaPorDef.getFechaCreacion() == null : fechaCreacionEsperada.equals(dtListaPorDef.getFechaCreacion()))) {
            datosIncorrectos++;
        }

        if (!dtListaPorDef.getFotoLista().equalsIgnoreCase(fotoListaEsperada)) {
            datosIncorrectos++;
        }

        if (datosIncorrectos == 0) {
            System.out.println("Test Consultas de Datos: getDatosListaPorDef('Noche De La Nostalgia') : OK");
        } else {
            System.out.println("Test Consultas de Datos: getDatosListaPorDef('Noche De La Nostalgia') : FAIL");
        }
        
        assertTrue(datosIncorrectos == 0);
    }

    @Test
    public void getDatosListaPart1() {
        DTDatosListaReproduccion dtListaPart = cp.getDatosListaReproduccion("Particular", "Fiesteras");
        int datosIncorrectos = 0;

        String nombreEsperado = "Fiesteras";
        String clienteEsperado = "cbochinche";
        String fechaCreacionEsperada = null;
        String fotoListaEsperada = "./Resource/ImagenesPerfil/fiestaFiesta.jpg";
        Boolean privacidadEsperada = false;

        if (!dtListaPart.getNombreLista().equalsIgnoreCase(nombreEsperado)) {
            datosIncorrectos++;
        }

        if (!dtListaPart.getCliente().equalsIgnoreCase(clienteEsperado)) {
            datosIncorrectos++;
        }

        if (!(fechaCreacionEsperada == null ? dtListaPart.getFechaCreacion() == null : fechaCreacionEsperada.equals(dtListaPart.getFechaCreacion()))) {
            datosIncorrectos++;
        }

        if (!(fotoListaEsperada == null ? dtListaPart.getFotoLista() == null : fotoListaEsperada.equalsIgnoreCase(dtListaPart.getFotoLista()))) {
            datosIncorrectos++;
        }

        if (!privacidadEsperada.equals(dtListaPart.getPrivacidad())) {
            datosIncorrectos++;
        }

        if (datosIncorrectos == 0) {
            System.out.println("Test Consultas de Datos: getDatosListaPart('Fiesteras') : OK");
        } else {
            System.out.println("Test Consultas de Datos: getDatosListaPart('Fiesteras') : FAIL");
        }
        
        assertTrue(datosIncorrectos == 0);
    }

    @Test
    public void getDatosListaPart2() {
        DTDatosListaReproduccion dtListaPart = cp.getDatosListaReproduccion("Particular", "Para Cocinar");
        int datosIncorrectos = 0;

        String nombreEsperado = "Para Cocinar";
        String clienteEsperado = "Heisenberg";
        String fechaCreacionEsperada = null;
        String fotoListaEsperada = "./Resource/ImagenesPerfil/ParaCocinar.jpg";
        Boolean privacidadEsperada = true;

        if (!dtListaPart.getNombreLista().equalsIgnoreCase(nombreEsperado)) {
            datosIncorrectos++;
        }

        if (!dtListaPart.getCliente().equalsIgnoreCase(clienteEsperado)) {
            datosIncorrectos++;
        }
        
        if (!(fechaCreacionEsperada == null ? dtListaPart.getFechaCreacion() == null : fechaCreacionEsperada.equals(dtListaPart.getFechaCreacion()))) {
            datosIncorrectos++;
        }

        if (!(fotoListaEsperada == null ? dtListaPart.getFotoLista() == null : fotoListaEsperada.equalsIgnoreCase(dtListaPart.getFotoLista()))) {
            datosIncorrectos++;
        }

        if (!privacidadEsperada.equals(dtListaPart.getPrivacidad())) {
            datosIncorrectos++;
        }

        if (datosIncorrectos == 0) {
            System.out.println("Test Consultas de Datos: getDatosListaPart('Para Cocinar') : OK");
        } else {
            System.out.println("Test Consultas de Datos: getDatosListaPart('Para Cocinar') : FAIL");
        }
        
        assertTrue(datosIncorrectos == 0);
    }
}