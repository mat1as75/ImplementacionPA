
import espotify.DataTypes.DTCliente;
import espotify.DataTypes.DTDatosListaReproduccion;
import espotify.DataTypes.DTTemaSimple;
import espotify.persistencia.ControladoraPersistencia;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class CrearListaReproduccionTest {

    private static ControladoraPersistencia cp;
    private static CargarDatosTest cdt;
    private static final String nickClienteDePrueba = "ppArgento";
    private String nickClienteDePruebaNuevo;

    public CrearListaReproduccionTest() {
        cp = new ControladoraPersistencia();
        cdt = new CargarDatosTest();
        cdt.resetDatosDePrueba();
    }

    public List<DTTemaSimple> crearDataTemasDePrueba() {
        DTTemaSimple tema1 = new DTTemaSimple(
                1L, 
                "tema1", 
                120, 
                1, 
                "nombreAlbum1", 
                "nombreArtista1");
        DTTemaSimple tema2 = new DTTemaSimple(
                2L, 
                "tema2", 
                120, 
                2, 
                "nombreAlbum2", 
                "nombreArtista2");
        DTTemaSimple tema3 = new DTTemaSimple(
                3L, 
                "tema3", 
                120, 
                3, 
                "nombreAlbum3", 
                "nombreArtista3");
        DTTemaSimple tema4 = new DTTemaSimple(
                4L, 
                "tema4", 
                120, 
                4, 
                "nombreAlbum4", 
                "nombreArtista4");

        List<DTTemaSimple> dataTemas = new ArrayList();
        dataTemas.add(tema1);
        dataTemas.add(tema2);
        dataTemas.add(tema3);
        dataTemas.add(tema4);

        return dataTemas;
    }
    
    public void crearClienteDePrueba() {
        DTCliente dtCliente = new DTCliente(
                "nickClienteDePrueba",
                "nombre",
                "apellido",
                "1234",
                "emaildepruebcrearlista@gmail.com",
                null,
                null
        );

        try {
            cp.AltaCliente(dtCliente);
            this.nickClienteDePruebaNuevo = dtCliente.getNickname();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            //fallback si no se logra crear el cliente de prueba nuevo
            this.nickClienteDePruebaNuevo = "Heisenberg";
        }
    }

    public String crearListaPorDef(String nombreLista, String fotoLista, String nombreGenero) {

        String nombreNuevaLista = null;

        try {
            cp.CrearListaPorDefecto(nombreLista, fotoLista, nombreGenero);
            nombreNuevaLista = cp.buscarListaPorDefectoPorNombre(nombreLista);
        } catch (Exception e) {
            throw e;
        }
        return nombreNuevaLista;
    }

    public Boolean comprobarCreacionListaPorDef(
            String nombreLista, 
            String fotoLista, 
            String nombreGenero, 
            String test, 
            Boolean expected) {

        String nomLista = null;
        Boolean fueCreado = false;
        try {
            nomLista = crearListaPorDef(nombreLista, fotoLista, nombreGenero);
            fueCreado = (nomLista != null);
        } catch (Exception e) {
            fueCreado = false;
        }

        if (fueCreado.equals(expected)) {
            System.out.println("Test de Crear Lista Por Defecto: " + test + ". OK");
            return true;
        } else {
            System.out.println("Test de Crear Lista Por Defecto: " + test + ". FAIL");
            return false;
        }
    }

    @Before
    public void setUp() {
        cdt.resetDatosDePrueba();
    }

    @AfterClass
    public static void tearDownClass() {
        //con @AfterClass esta funcion se ejecuta tras finalizar todos los tests de esta clase
        cdt.resetDatosDePrueba();
    }

    @Test
    public void testDatosCorrectosLPorDeF() {
        List<DTTemaSimple> dataTemas = crearDataTemasDePrueba();

        DTDatosListaReproduccion dtDatosLista = new DTDatosListaReproduccion(
                "nombreListaDePrueba",
                "Resource/Listas/portadasListas/img.jpg",
                "PorDefecto",
                dataTemas,
                "Rock"
        );

        assertTrue(comprobarCreacionListaPorDef(
                dtDatosLista.getNombreLista(), 
                dtDatosLista.getFotoLista(), 
                dtDatosLista.getGenero(), 
                "Datos correctos Lista Por Defecto", 
                true));
    }

    @Test
    public void testDatosCorrectos2LPorDeF() {
        List<DTTemaSimple> dataTemas = crearDataTemasDePrueba();

        DTDatosListaReproduccion dtDatosLista = new DTDatosListaReproduccion(
                "nombreListaDePrueba2",
                "Resource/Listas/portadasListas/img.jpg",
                "PorDefecto",
                dataTemas,
                "Rock"
        );

        assertTrue(comprobarCreacionListaPorDef(
                dtDatosLista.getNombreLista(), 
                dtDatosLista.getFotoLista(), 
                dtDatosLista.getGenero(), 
                "Datos correctos Lista Por Defecto 2", 
                true));
    }

    public String crearListaPart(
            String nombreLista, 
            String fotoLista, 
            String miCliente, 
            Date fechaCreacion, 
            boolean soyPrivada) {
        
        String nombreNuevaLista = null;

        try {
            cp.CrearListaParticular(
                    nombreLista, 
                    fotoLista, 
                    miCliente, 
                    fechaCreacion, 
                    soyPrivada);
            nombreNuevaLista = cp.buscarListaParticularPorNombre(nombreLista);
        } catch (Exception e) {
            throw e;
        }
        return nombreNuevaLista;
    }

    public Boolean comprobarCreacionListaPart(
            String nombreLista, 
            String fotoLista, 
            String miCliente, 
            Date fechaCreacion, 
            boolean soyPrivada, 
            String test, 
            Boolean expected) {

        String nomLista = null;
        Boolean fueCreado = false;
        try {
            nomLista = crearListaPart(nombreLista, fotoLista, miCliente, fechaCreacion, soyPrivada);
            fueCreado = (nomLista != null);
        } catch (Exception e) {
            fueCreado = false;
        }

        if (fueCreado.equals(expected)) {
            System.out.println("Test de Crear Lista Particular: " + test + ". OK");
            return true;
        } else {
            System.out.println("Test de Crear Lista Particular: " + test + ". FAIL");
            return false;
        }
    }

    @Test
    public void testDatosCorrectosLPart() {
        List<DTTemaSimple> dataTemas = crearDataTemasDePrueba();
        Date fechaCreacion = new Date();
        DTDatosListaReproduccion dtDatosListaPart = new DTDatosListaReproduccion(
                "nombreListaDePruebaPart",
                "Resource/Listas/portadasListas/img.jpg",
                "PorDefecto",
                fechaCreacion,
                dataTemas,
                this.nickClienteDePrueba,
                true
        );

        assertTrue(comprobarCreacionListaPart(
                dtDatosListaPart.getNombreLista(), 
                dtDatosListaPart.getFotoLista(), 
                dtDatosListaPart.getCliente(), 
                dtDatosListaPart.getFechaCreacion(), 
                dtDatosListaPart.getPrivacidad(), 
                "Datos correctos Lista Particular", 
                true));
    }

    @Test
    public void testDatosCorrectosLPart2() {
        List<DTTemaSimple> dataTemas = crearDataTemasDePrueba();
        Date fechaCreacion = new Date();
        DTDatosListaReproduccion dtDatosListaPart = new DTDatosListaReproduccion(
                "nombreListaDePruebaPart2",
                "Resource/Listas/portadasListas/img.jpg",
                "Particular",
                fechaCreacion,
                dataTemas,
                this.nickClienteDePrueba,
                true
        );

        assertTrue(comprobarCreacionListaPart(
                dtDatosListaPart.getNombreLista(), 
                dtDatosListaPart.getFotoLista(), 
                dtDatosListaPart.getCliente(), 
                dtDatosListaPart.getFechaCreacion(), 
                dtDatosListaPart.getPrivacidad(), 
                "Datos correctos Lista Particular 2", 
                true));
    }

    @Test
    public void testDatosCorrectosClienteNuevo() {
        List<DTTemaSimple> dataTemas = crearDataTemasDePrueba();
        Date fechaCreacion = new Date();

        crearClienteDePrueba();

        DTDatosListaReproduccion dtDatosListaPart = new DTDatosListaReproduccion(
                "nombreListaDePruebaPartClienteNuevo",
                "Resource/Listas/portadasListas/img.jpg",
                "Particular",
                fechaCreacion,
                dataTemas,
                this.nickClienteDePruebaNuevo,
                true
        );

        assertTrue(comprobarCreacionListaPart(
                dtDatosListaPart.getNombreLista(), 
                dtDatosListaPart.getFotoLista(), 
                dtDatosListaPart.getCliente(), 
                dtDatosListaPart.getFechaCreacion(), 
                dtDatosListaPart.getPrivacidad(), 
                "Datos correctos cliente nuevo", 
                true));
    }

    @Test
    public void testDatosCorrectosClienteNuevo2() {
        List<DTTemaSimple> dataTemas = crearDataTemasDePrueba();
        Date fechaCreacion = new Date();

        crearClienteDePrueba();

        DTDatosListaReproduccion dtDatosListaPart = new DTDatosListaReproduccion(
                "nombreListaDePruebaPartClienteNuevo2",
                "Resource/Listas/portadasListas/img.jpg",
                "Particular",
                fechaCreacion,
                dataTemas,
                this.nickClienteDePruebaNuevo,
                true
        );

        assertTrue(comprobarCreacionListaPart(
                dtDatosListaPart.getNombreLista(), 
                dtDatosListaPart.getFotoLista(), 
                dtDatosListaPart.getCliente(), 
                dtDatosListaPart.getFechaCreacion(), 
                dtDatosListaPart.getPrivacidad(), 
                "Datos correctos cliente nuevo 2", 
                true));
    }

    @Test
    public void testClienteNull() {
        List<DTTemaSimple> dataTemas = crearDataTemasDePrueba();
        Date fechaCreacion = new Date();

        DTDatosListaReproduccion dtDatosListaPart = new DTDatosListaReproduccion(
                "nombreListaDePruebaPartClienteNuevo2",
                "Resource/Listas/portadasListas/img.jpg",
                "Particular",
                fechaCreacion,
                dataTemas,
                null,
                true
        );
        //la creacion de una lista con cliente en null debe tirar un error
        assertTrue(comprobarCreacionListaPart(
                dtDatosListaPart.getNombreLista(), 
                dtDatosListaPart.getFotoLista(), 
                dtDatosListaPart.getCliente(), 
                dtDatosListaPart.getFechaCreacion(), 
                dtDatosListaPart.getPrivacidad(), 
                "Cliente null", 
                false));
    }

    @Test
    public void testNombreListaPorDefNull() {
        List<DTTemaSimple> dataTemas = crearDataTemasDePrueba();

        DTDatosListaReproduccion dtDatosLista = new DTDatosListaReproduccion(
                null,
                "Resource/Listas/portadasListas/img.jpg",
                "PorDefecto",
                dataTemas,
                "Rock"
        );
        //la creacion de una lista con nombre en null debe tirar un error
        assertTrue(comprobarCreacionListaPorDef(
                dtDatosLista.getNombreLista(), 
                dtDatosLista.getFotoLista(), 
                dtDatosLista.getGenero(), 
                "Nombre lista null", 
                false));
    }

    @Test
    public void testNombreListaPartNull() {
        List<DTTemaSimple> dataTemas = crearDataTemasDePrueba();
        Date fechaCreacion = new Date();
        DTDatosListaReproduccion dtDatosListaPart = new DTDatosListaReproduccion(
                null,
                "Resource/Listas/portadasListas/img.jpg",
                "PorDefecto",
                fechaCreacion,
                dataTemas,
                this.nickClienteDePrueba,
                true
        );
        //la creacion de una lista con nombre en null debe tirar un error
        assertTrue(comprobarCreacionListaPart(
                dtDatosListaPart.getNombreLista(), 
                dtDatosListaPart.getFotoLista(), 
                dtDatosListaPart.getCliente(), 
                dtDatosListaPart.getFechaCreacion(), 
                dtDatosListaPart.getPrivacidad(), 
                "Nombre lista null", 
                false));
    }

    @Test
    public void testGeneroNull() {
        List<DTTemaSimple> dataTemas = crearDataTemasDePrueba();

        DTDatosListaReproduccion dtDatosLista = new DTDatosListaReproduccion(
                "listaPruebaGeneroNull",
                "Resource/Listas/portadasListas/img.jpg",
                "PorDefecto",
                dataTemas,
                null
        );
        //la creacion de una lista con genero en null debe tirar un error
        assertTrue(comprobarCreacionListaPorDef(
                dtDatosLista.getNombreLista(), 
                dtDatosLista.getFotoLista(), 
                dtDatosLista.getGenero(), 
                "Nombre lista null", 
                false));
    }
    
    @Test
    public void testClienteInexistente() {
        List<DTTemaSimple> dataTemas = crearDataTemasDePrueba();
        Date fechaCreacion = new Date();
        DTDatosListaReproduccion dtDatosListaPart = new DTDatosListaReproduccion(
                "nombreListaDePruebaPartClienteInexistente",
                "Resource/Listas/portadasListas/img.jpg",
                "PorDefecto",
                fechaCreacion,
                dataTemas,
                "clienteInexistente",
                true
        );
        //la creacion de una lista con un cliente que no existe debe tirar un error
        assertTrue(comprobarCreacionListaPart(
                dtDatosListaPart.getNombreLista(), 
                dtDatosListaPart.getFotoLista(), 
                dtDatosListaPart.getCliente(), 
                dtDatosListaPart.getFechaCreacion(), 
                dtDatosListaPart.getPrivacidad(), 
                "Cliente inexistente", 
                false));
    }

    @Test
    public void testListaRepetida() {
        List<DTTemaSimple> dataTemas = crearDataTemasDePrueba();

        DTDatosListaReproduccion dtDatosLista = new DTDatosListaReproduccion(
                "Rock En Español",
                "Resource/Listas/portadasListas/img.jpg",
                "PorDefecto",
                dataTemas,
                "Rock"
        );
        //la creacion de una lista con un nombre de lista repetido debe tirar un error
        assertTrue(comprobarCreacionListaPorDef(
                dtDatosLista.getNombreLista(), 
                dtDatosLista.getFotoLista(), 
                dtDatosLista.getGenero(), 
                "Lista Repetida", 
                false));
    }
    
    @Test
    public void testGeneroInexistente() {
         List<DTTemaSimple> dataTemas = crearDataTemasDePrueba();

        DTDatosListaReproduccion dtDatosLista = new DTDatosListaReproduccion(
                "listaPruebaGeneroInexistente",
                "Resource/Listas/portadasListas/img.jpg",
                "PorDefecto",
                dataTemas,
                "generoInexistente"
        );
        //la creacion de una lista con un genero inexistente debe tirar un error
        assertTrue(comprobarCreacionListaPorDef(
                dtDatosLista.getNombreLista(), 
                dtDatosLista.getFotoLista(), 
                dtDatosLista.getGenero(), 
                "Genero inexistente", 
                false));
    }
}
