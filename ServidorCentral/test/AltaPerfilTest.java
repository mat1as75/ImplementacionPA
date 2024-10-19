import espotify.DataTypes.DTAlbum_SinDTArtista;
import espotify.DataTypes.DTArtista;
import espotify.DataTypes.DTCliente;
import espotify.DataTypes.DTGenero;
import espotify.DataTypes.DTTemaGenerico;
import espotify.DataTypes.DTUsuario;
import espotify.persistencia.ControladoraPersistencia;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class AltaPerfilTest {
    private static ControladoraPersistencia cp;
    private DTArtista artista;
    private DTCliente cliente;
    public AltaPerfilTest() {
        cp = new ControladoraPersistencia();
    }
      
      public DTArtista crearArtistaDePrueba() {
        DTArtista dtArtista = new DTArtista(
                "nickArtistaDePrueba", 
                null, 
                null, 
                null, 
                null, 
                null, 
                null, 
                null, 
                null, 
                null, 
                null
        );
        return dtArtista;
    }
    public DTCliente crearClienteDePrueba() {
        DTCliente dtCli = new DTCliente(
                "nicknameClienteDePrueba",
                null,
                null,
                null,
                null,
                null,
                null
        );
        return dtCli;
    
    }
    
    public Boolean comprobarCreacionArtista(DTArtista dtArt, String test, Boolean expected) {
        
        
        Boolean fueCreado = false;
        try {
            cp.AltaArtista(dtArt);
            fueCreado = true;
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
     public Boolean comprobarCreacionCliente(DTCliente dtCli, String test, Boolean expected) {
        
        
        Boolean fueCreado = false;
        try {
            cp.AltaCliente(dtCli);
            fueCreado = true;
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
    @Test
    public void testDatosCorrectoArtistas() {
        this.artista=this.crearArtistaDePrueba();
        assertTrue(comprobarCreacionArtista(artista, "Artista con datos null", true));
    }
     @Test
    public void testDatosCorrectosCliente() {
        this.cliente=this.crearClienteDePrueba();
        assertTrue(comprobarCreacionCliente(cliente, "Cliente con datos null", true));
    }
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
