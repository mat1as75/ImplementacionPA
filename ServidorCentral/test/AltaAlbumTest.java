import espotify.DataTypes.DTAlbum;
import espotify.DataTypes.DTAlbum_SinDTArtista;
import espotify.DataTypes.DTArtista;
import espotify.DataTypes.DTGenero;
import espotify.DataTypes.DTTemaConRuta;
import espotify.DataTypes.DTTemaConURL;
import espotify.DataTypes.DTTemaGenerico;
import espotify.DataTypes.DTTemaSimple;
import espotify.logica.Album;
import espotify.logica.Genero;
import espotify.persistencia.ControladoraPersistencia;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class AltaAlbumTest {
    
    private static ControladoraPersistencia cp;
    private static CargarDatosTest cdt;
    private String nickArtistaDePrueba;
    
    public AltaAlbumTest() {
        cp = new ControladoraPersistencia();
        cdt = new CargarDatosTest();
    }
    
    public List<DTTemaGenerico> crearDataTemasDePrueba() {
        DTTemaGenerico tema1 = new DTTemaConRuta("rutaTema1", "tema1", 120, 1);
        DTTemaGenerico tema2 = new DTTemaConRuta("rutaTema2", "tema2", 120, 2);
        DTTemaGenerico tema3 = new DTTemaConURL("tema3", 120, 3, "urlTema3");
        DTTemaGenerico tema4 = new DTTemaConURL("tema4", 120, 4, "urlTema4");
        
        List<DTTemaGenerico> dataTemas = new ArrayList();
        dataTemas.add(tema1);
        dataTemas.add(tema2);
        dataTemas.add(tema3);
        dataTemas.add(tema4);
        
        return dataTemas;
    }
    
    public List<DTGenero> crearDataGenerosDePrueba() {
        DTGenero g1 = new DTGenero("Rock");
        DTGenero g2 = new DTGenero("Rock & Roll");
        
        List<DTGenero> dataGeneros = new ArrayList();
        dataGeneros.add(g1);
        dataGeneros.add(g2);
        
        return dataGeneros;
    }
    
    public void crearArtistaDePrueba() {
        DTArtista dtArtista = new DTArtista(
                "nickArtistaDePrueba", 
                "nombre", 
                "apellido", 
                "1234", 
                null, 
                null, 
                null, 
                null, 
                null, 
                null, 
                null
        );
        
        try {
            cp.AltaArtista(dtArtista);
            this.nickArtistaDePrueba = dtArtista.getNickname();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            //fallback
            this.nickArtistaDePrueba = "alcides";
        }
    }
    
    public Long crearAlbum(DTAlbum_SinDTArtista dtAlbum) throws Exception {
        
        Long idNuevoAlbum = null;
        
        try {
            cp.AltaAlbum(dtAlbum);
            idNuevoAlbum = cp.buscarAlbumPorNombreYArtista(
                    dtAlbum.getMiArtista(), 
                    dtAlbum.getNombreAlbum());
        } catch (Exception e) {
            throw e;
        } finally {
            return idNuevoAlbum;
        }
    }

    public Boolean comprobarCreacion(DTAlbum_SinDTArtista dtAlbum, String test, Boolean expected) {
        
        Long idAlb = null;
        Boolean fueCreado = false;
        try {
            idAlb = crearAlbum(dtAlbum);
            fueCreado = (idAlb != null);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            fueCreado = false;
        }
            
        if (fueCreado.equals(expected)) {
            System.out.println("Test: " + test + ". OK");
            return true;
        } else {
            System.out.println("Test: " + test + ". FAIL");
            return false;
        }
    }
    
    @Before
    public void setUp() {
        //con @Before esta funcion se ejecuta antes de cada test
        //reseteo la base de datos a un estado que tenga solo los datos de prueba
        cdt.resetDatosDePrueba();
        crearArtistaDePrueba();
    }
    
    @AfterClass
    public static void tearDownClass() {
        //con @AfterClass esta funcion se ejecuta tras finalizar todos los tests de esta clase
        cdt.resetDatosDePrueba();
    }
    
    @Test
    public void testDatosCorrectos() {
        List<DTTemaGenerico> dataTemas = crearDataTemasDePrueba();
        List<DTGenero> dataGeneros = crearDataGenerosDePrueba();
        
        DTAlbum_SinDTArtista dtAlbum = new DTAlbum_SinDTArtista(
                "albumPrueba",
                2000,
                "Resource/Albums/portadasAlbum/img.jpg",
                dataTemas,
                dataGeneros,
                this.nickArtistaDePrueba        
        );
        
        assertTrue(comprobarCreacion(dtAlbum, "Datos correctos", true));
    }
    
    @Test
    public void testArtistaNull() {
        List<DTTemaGenerico> dataTemas = crearDataTemasDePrueba();
        List<DTGenero> dataGeneros = crearDataGenerosDePrueba();
        
        DTAlbum_SinDTArtista dtAlbum = new DTAlbum_SinDTArtista(
                "albumPrueba",
                2000,
                "Resource/Albums/portadasAlbum/img.jpg",
                dataTemas,
                dataGeneros,
                null        
        );
        //la creacion de un album con artista en null debe tirar un error
        assertTrue(comprobarCreacion(dtAlbum, "Artista null", false));
    }
    
    @Test
    public void testNombreAlbumNull() {
        List<DTTemaGenerico> dataTemas = crearDataTemasDePrueba();
        List<DTGenero> dataGeneros = crearDataGenerosDePrueba();
        
        DTAlbum_SinDTArtista dtAlbum = new DTAlbum_SinDTArtista(
                null,
                2000,
                "Resource/Albums/portadasAlbum/img.jpg",
                dataTemas,
                dataGeneros,
                this.nickArtistaDePrueba        
        );
        //la creacion de un album con nombre en null debe tirar un error
        assertTrue(comprobarCreacion(dtAlbum, "Nombre album null", false));
    }
    
    @Test
    public void testGenerosNull() {
        List<DTTemaGenerico> dataTemas = crearDataTemasDePrueba();
        
        DTAlbum_SinDTArtista dtAlbum = new DTAlbum_SinDTArtista(
                "albumPrueba",
                2000,
                "Resource/Albums/portadasAlbum/img.jpg",
                dataTemas,
                null,
                this.nickArtistaDePrueba        
        );
        //la creacion de un album con generos en null debe tirar un error
        assertTrue(comprobarCreacion(dtAlbum, "Generos null", false));
    }
    
    @Test
    public void testTemasNull() {
        List<DTTemaGenerico> dataTemas = crearDataTemasDePrueba();
        List<DTGenero> dataGeneros = crearDataGenerosDePrueba();
        
        DTAlbum_SinDTArtista dtAlbum = new DTAlbum_SinDTArtista(
                "albumPrueba",
                2000,
                "Resource/Albums/portadasAlbum/img.jpg",
                null,
                dataGeneros,
                this.nickArtistaDePrueba        
        );
        //la creacion de un album con temas en null debe tirar un error
        assertTrue(comprobarCreacion(dtAlbum, "Temas null", false));
    }
    
    @Test
    public void testTemasVacio() {
        List<DTTemaGenerico> dataTemas = new ArrayList();
        List<DTGenero> dataGeneros = crearDataGenerosDePrueba();
        
        DTAlbum_SinDTArtista dtAlbum = new DTAlbum_SinDTArtista(
                "albumPrueba",
                2000,
                "Resource/Albums/portadasAlbum/img.jpg",
                dataTemas,
                dataGeneros,
                this.nickArtistaDePrueba        
        );
        //la creacion de un album con temas vacios debe tirar un error
        assertTrue(comprobarCreacion(dtAlbum, "Temas vacio", false));
    }
    
    @Test
    public void testGenerosVacio() {
        List<DTTemaGenerico> dataTemas = crearDataTemasDePrueba();
        List<DTGenero> dataGeneros = new ArrayList();
        
        DTAlbum_SinDTArtista dtAlbum = new DTAlbum_SinDTArtista(
                "albumPrueba",
                2000,
                "Resource/Albums/portadasAlbum/img.jpg",
                dataTemas,
                dataGeneros,
                this.nickArtistaDePrueba        
        );
        //la creacion de un album con generos vacios debe tirar un error
        assertTrue(comprobarCreacion(dtAlbum, "Generos vacios", false));
    }
    
    @Test
    public void testArtistaInexistente() {
        List<DTTemaGenerico> dataTemas = crearDataTemasDePrueba();
        List<DTGenero> dataGeneros = crearDataGenerosDePrueba();
        
        DTAlbum_SinDTArtista dtAlbum = new DTAlbum_SinDTArtista(
                "albumPrueba",
                2000,
                "Resource/Albums/portadasAlbum/img.jpg",
                dataTemas,
                dataGeneros,
                "artistaInexistente"        
        );
        //la creacion de un album con un artista que no existe debe tirar un error
        assertTrue(comprobarCreacion(dtAlbum, "Artista inexistente", false));
    }
    
    @Test
    public void testAlbumRepetido() {
        List<DTTemaGenerico> dataTemas = crearDataTemasDePrueba();
        List<DTGenero> dataGeneros = crearDataGenerosDePrueba();
        
        DTAlbum_SinDTArtista dtAlbum = new DTAlbum_SinDTArtista(
                "20 Grandes Éxitos",
                2000,
                "Resource/Albums/portadasAlbum/img.jpg",
                dataTemas,
                dataGeneros,
                "alcides"        
        );
        //la creacion de un album con un nombre de album repetido debe tirar un error
        assertTrue(comprobarCreacion(dtAlbum, "Album repetido", false));
    }
    
    @Test
    public void testGeneroInexistente() {
        List<DTTemaGenerico> dataTemas = crearDataTemasDePrueba();
        List<DTGenero> dataGeneros = new ArrayList();
        DTGenero dg1 = new DTGenero("generoInexistente");
        DTGenero dg2 = new DTGenero("Rock Latino");
        dataGeneros.add(dg1);
        dataGeneros.add(dg2);
        
        DTAlbum_SinDTArtista dtAlbum = new DTAlbum_SinDTArtista(
                "20 Grandes Éxitos",
                2000,
                "Resource/Albums/portadasAlbum/img.jpg",
                dataTemas,
                dataGeneros,
                this.nickArtistaDePrueba        
        );
        //la creacion de un album con un genero inexistente debe tirar un error
        assertTrue(comprobarCreacion(dtAlbum, "Genero inexistente", false));
    }
    
    @Test
    public void testVerificarTemas() {
       
        List<DTTemaGenerico> dataTemas = crearDataTemasDePrueba();
        List<DTGenero> dataGeneros = crearDataGenerosDePrueba();
        String test = "Test: Verificar temas. "; 
        
        DTAlbum_SinDTArtista dtAlbum = new DTAlbum_SinDTArtista(
                "albumPrueba",
                2000,
                "Resource/Albums/portadasAlbum/img.jpg",
                dataTemas,
                dataGeneros,
                this.nickArtistaDePrueba        
        );
        
        Long idAlb = null;
        try {
            idAlb = crearAlbum(dtAlbum);
        } catch (Exception e) {
            System.out.println(test + "FAIL");
            assertTrue(false);
        }
        //obtengo los temas del album creado
        Map<Long, DTTemaSimple> temasDeAlb = cp.getDTTemasDeAlbum(idAlb);
        Boolean contieneTemas = temasDeAlb.size() == dataTemas.size();
        Boolean temasAsociadosAAlbum = true;
        //verifico que cada tema tenga asociado el album creado
        for (Entry<Long, DTTemaSimple> entry : temasDeAlb.entrySet()) {
            if (!entry.getValue().getNombreAlbum().equals(dtAlbum.getNombreAlbum())) {
                temasAsociadosAAlbum = false;
                break;
            }
        }
        
        if (contieneTemas && temasAsociadosAAlbum) {
            System.out.println(test + "OK");
            assertTrue(true);
        } else {
            System.out.println(test + "FAIL");
            assertTrue(false);
        }
        
    }
    
    @Test
    public void testVerificarGeneros() {
    
        List<DTTemaGenerico> dataTemas = crearDataTemasDePrueba();
        List<DTGenero> dataGeneros = crearDataGenerosDePrueba();
        String test = "Test: Verificar generos. "; 
        
        DTAlbum_SinDTArtista dtAlbum = new DTAlbum_SinDTArtista(
                "albumPrueba",
                2000,
                "Resource/Albums/portadasAlbum/img.jpg",
                dataTemas,
                dataGeneros,
                this.nickArtistaDePrueba        
        );
        
        Long idAlb = null;
        try {
            idAlb = crearAlbum(dtAlbum);
        } catch (Exception e) {
            System.out.println(test + "FAIL");
            assertTrue(false);
        }
        
        //busco el album y obtengo los generos 
        DTAlbum nuevoAlbum = cp.ConsultaAlbum(idAlb);
        List<DTGenero> generosDeNuevoAlbum = nuevoAlbum.getMisgeneros();
        
        Boolean contieneGeneros = true; 
        
        //por cada genero del album creado verifico que el album este asociado a dicho genero
        for (DTGenero dtGen : generosDeNuevoAlbum) {
            Genero gen = cp.genJpa.findGenero(dtGen.getNombreGenero());
            Album alb = cp.albJpa.findAlbum(idAlb);
            //albums asociados al genero
            List<Album> albumsDeGenero = gen.getMisAlbumes();
            Boolean genContieneAlb = false;
            //busco entre los albums asociados al genero y verifico que el nuevo album este presente
            for (Album a : albumsDeGenero) {
                if (a.getIdAlbum().equals(idAlb)) {
                    genContieneAlb = true;
                    break;
                }
            }
            //si este genero no tiene al album creado asociado entonces no se relaciono correctamente
            if (!genContieneAlb) {
                contieneGeneros = false;
                break;
            }
        }
        System.out.println(test + (contieneGeneros ? "OK" : "FAIL"));
        assertTrue(contieneGeneros);
    }
    
}
