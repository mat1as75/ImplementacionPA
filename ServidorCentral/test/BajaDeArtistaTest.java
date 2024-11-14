import espotify.logica.Album;
import espotify.logica.Artista;
import espotify.logica.Cliente;
import espotify.logica.ListaReproduccion;
import espotify.logica.Tema;
import espotify.logica.Usuario;
import espotify.persistencia.ControladoraPersistencia;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class BajaDeArtistaTest {
    
    private static ControladoraPersistencia cp;
    private static CargarDatosTest cdt;
    
    public BajaDeArtistaTest() {
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

    public Boolean verificarBaja(String nickArtista) {
                
        Artista artista = cp.artJpa.findArtista(nickArtista);
        List<Long> temasDeArtista = new ArrayList(); 
        List<Long> albumsDeArtista = new ArrayList();
        //Extraigo los ids de los albums y temas del artista   
        for (Album a : artista.getMisAlbumesPublicados()) {
            albumsDeArtista.add(a.getIdAlbum());
            for (Tema t : a.getMisTemas()) {
                temasDeArtista.add(t.getIdTema());
            }
        }

        List<Cliente> clientes = cp.cliJpa.findClienteEntities();
        List<ListaReproduccion> listasRep = cp.lreprodccJpa.findListaReproduccionEntities();
       
        //verifico baja logica 
        //(activo == false, fechaDeBaja != null, misSeguidores vacio)
        if (artista.getActivo() 
                || artista.getFechaBaja() == null 
                || !artista.getMisSeguidores().isEmpty()) {
            return false;
        }

        for (Cliente c : clientes) {
            //verifico que ningun cliente tenga como seguido al artista
            for (Usuario u : c.getMisSeguidos()) {
                if (u.getNickname().equals(nickArtista)) {
                    return false;
                }
            }
            //verifico que ningun cliente tenga como favorito un album del artista
            for (Album a : c.getMisAlbumesFav()) {
                if (albumsDeArtista.contains(a.getIdAlbum())) {
                    return false;
                }
            }
            //verifico que ningun cliente tenga como favorito un tema del artista
            for (Tema t : c.getMisTemasFav()) {
                if (temasDeArtista.contains(t.getIdTema())) {
                    return false;
                }
            }
        }

        for (ListaReproduccion lr : listasRep) {
            //verifico que ninguna lista contenga temas del artista
            for (Tema t : lr.getMisTemas()) {
                if (temasDeArtista.contains(t.getIdTema())) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public Boolean darDeBajaArtista(
            String nicknameArtista, 
            String test, 
            Boolean expected) {
        
        Boolean fueDadoDeBaja = false;
        
        try {           
            cp.darDeBajaArtista(nicknameArtista);
            fueDadoDeBaja = verificarBaja(nicknameArtista);
        } catch (Exception e) {
            fueDadoDeBaja = false;
        }

        if (fueDadoDeBaja.equals(expected)) {
            System.out.println("Test Baja de Artista: " + test + ". OK");
            return true;
        } else {
            System.out.println("Test Baja de Artista: " + test + ". FAIL");
            return false;
        }
    }
    
    @Test
    public void bajaDeArtista1() {
        String nicknameArtista = "alcides";
        assertTrue(darDeBajaArtista(nicknameArtista, "Baja de alcides", true));
    }
    
    @Test
    public void bajaDeArtista2() {
        String nicknameArtista = "clauper";
        assertTrue(darDeBajaArtista(nicknameArtista, "Baja de clauper", true));
    }
    
    @Test
    public void bajaDeArtista3() {
        String nicknameArtista = "bruceTheBoss";
        assertTrue(darDeBajaArtista(nicknameArtista, "Baja de bruceTheBoss", true));
    }
    
    @Test
    public void bajaDeArtista4() {
        String nicknameArtista = "dmode";
        assertTrue(darDeBajaArtista(nicknameArtista, "Baja de dmode", true));
    }
    
    @Test
    public void bajaDeArtista5() {
        String nicknameArtista = "tripleNelson";
        assertTrue(darDeBajaArtista(nicknameArtista, "Baja de tripleNelson", true));
    }
    
    @Test
    public void bajaDeArtista6() {
        String nicknameArtista = "la_ley";
        assertTrue(darDeBajaArtista(nicknameArtista, "Baja de la_ley", true));
    }
    
    @Test
    public void bajaDeArtista7() {
        String nicknameArtista = "lospimpi";
        assertTrue(darDeBajaArtista(nicknameArtista, "Baja de lospimpi", true));
    }
    
    @Test
    public void bajaDeArtista8() {
        String nicknameArtista = "chaiko";
        assertTrue(darDeBajaArtista(nicknameArtista, "Baja de chaiko", true));
    }
    
    @Test
    public void bajaDeArtista9() {
        String nicknameArtista = "tigerOfWales";
        assertTrue(darDeBajaArtista(nicknameArtista, "Baja de tigerOfWales", true));
    }
    
    @Test
    public void bajaDeArtista10() {
        String nicknameArtista = "dyangounchained";
        assertTrue(darDeBajaArtista(nicknameArtista, "Baja de dyangounchained", true));
    }
    
    @Test
    public void bajaDeArtistaNull() {
        String nicknameArtista = null;
        assertTrue(darDeBajaArtista(nicknameArtista, "Artista null", false));
    }
    
    @Test
    public void bajaDeArtistaInexistente() {
        String nicknameArtista = "nickname inexistente";
        assertTrue(darDeBajaArtista(nicknameArtista, "Artista inexistente", false));
    }
}
