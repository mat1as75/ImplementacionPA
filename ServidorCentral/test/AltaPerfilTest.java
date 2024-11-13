import espotify.DataTypes.DTArtista;
import espotify.DataTypes.DTCliente;
import espotify.persistencia.ControladoraPersistencia;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class AltaPerfilTest {
    
    private static ControladoraPersistencia cp;
    private static CargarDatosTest cdt;

    public AltaPerfilTest() {
        cp = new ControladoraPersistencia();
        cdt = new CargarDatosTest();
    }
      
    public DTArtista crearDTArtistaDePrueba() {
        DTArtista dtArtista = new DTArtista(
                "nickArtistaDePrueba1", 
                null, 
                null, 
                "1234", 
                "artistaDePrueba@gmail.com", 
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
    
    public DTCliente crearDTClienteDePrueba() {
        DTCliente dtCli = new DTCliente(
                "nicknameClienteDePrueba1",
                null,
                null,
                "1234",
                "artistaDePrueba@gmail.com",
                null,
                null
        );
        return dtCli;
    }
    
    public Boolean comprobarCreacionArtista(String nickname, DTArtista dtArt, String test, Boolean expected) {
      
        cp.AltaArtista(dtArt);
        Boolean fueCreado = cp.existeArtista(nickname);
        
        if (fueCreado.equals(expected)) {
            System.out.println("Test Alta Artista: " + test + ". OK");
            return true;
        } else {
            System.out.println("Test Alta Artista: " + test + ". FAIL");
            return false;
        }
    }
     public Boolean comprobarCreacionCliente(String nickname, DTCliente dtCli, String test, Boolean expected) {
        
        cp.AltaCliente(dtCli);
        Boolean fueCreado = cp.ExisteCliente(nickname);
            
        if (fueCreado.equals(expected)) {
            System.out.println("Test Alta Cliente: " + test + ". OK");
            return true;
        } else {
            System.out.println("Test Alta Cliente: " + test + ". FAIL");
            return false;
        }
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
    public void altaArtistaDatosCorrectos() {
        DTArtista dtArtista = this.crearDTArtistaDePrueba();
        assertTrue(comprobarCreacionArtista(
                dtArtista.getNickname(), 
                dtArtista, 
                "Datos válidos", 
                true));
    }
    
    @Test
    public void altaClienteDatosCorrectos() {
        DTCliente dtCliente = this.crearDTClienteDePrueba();
        assertTrue(comprobarCreacionCliente(
                dtCliente.getNickname(),
                dtCliente, 
                "Datos válidos", true));
    }
    
    @Test
    public void altaArtistaDatosNull1() {
        DTArtista dtArtista = new DTArtista(
                null, 
                null, 
                null, 
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
        
        assertTrue(comprobarCreacionArtista(
                dtArtista.getNickname(),
                dtArtista,
                "Datos null",
                false
        ));
    }
    
    @Test
    public void altaArtistaDatosNull2() {
        DTArtista dtArtista = new DTArtista(
                "nicknameArtistaDePrueba2", 
                null, 
                null, 
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
        
        assertTrue(comprobarCreacionArtista(
                dtArtista.getNickname(),
                dtArtista,
                "Email y contraseña null",
                false
        ));
    }
    
    @Test
    public void altaArtistaDatosNull3() {
        DTArtista dtArtista = new DTArtista(
                "nicknameArtistaDePrueba3", 
                null, 
                null, 
                null, 
                "test@gmail.com", 
                null, 
                null, 
                null, 
                null, 
                null, 
                null, 
                null, 
                null
        );
        
        assertTrue(comprobarCreacionArtista(
                dtArtista.getNickname(),
                dtArtista,
                "Contraseña null",
                false
        ));
    }
    
    @Test
    public void altaArtistaDatosNull4() {
        DTArtista dtArtista = new DTArtista(
                "nicknameArtistaDePrueba4", 
                null, 
                null, 
                "1234", 
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
        
        assertTrue(comprobarCreacionArtista(
                dtArtista.getNickname(),
                dtArtista,
                "Email null",
                false
        ));
    }
    
    @Test
    public void altaClienteDatosNull1() {
        
        DTCliente dtCliente = new DTCliente(
                null,
                null,
                null,
                null,
                null,                
                null,
                null
        );        
        
        assertTrue(comprobarCreacionCliente(
                dtCliente.getNickname(),
                dtCliente,
                "Datos null",
                false
        ));
    }
    
    @Test
    public void altaClienteDatosNull2() {
        DTCliente dtCliente = new DTCliente(
                "nicknameClienteDePrueba2",
                null,
                null,
                null,
                null,                
                null,
                null
        );        
        
        assertTrue(comprobarCreacionCliente(
                dtCliente.getNickname(),
                dtCliente,
                "Email y contraseña null",
                false
        ));
    }
    
    @Test
    public void altaClienteDatosNull3() {
        DTCliente dtCliente = new DTCliente(
                "nicknameClienteDePrueba3",
                null,
                null,
                "1234",
                null,                
                null,
                null
        );        
        
        assertTrue(comprobarCreacionCliente(
                dtCliente.getNickname(),
                dtCliente,
                "Email null",
                false
        ));
    }
    
    @Test
    public void altaClienteDatosNull4() {
        DTCliente dtCliente = new DTCliente(
                "nicknameClienteDePrueba4",
                null,
                null,
                null,
                "clienteDePrueba@gmail.com",                
                null,
                null
        );        
        
        assertTrue(comprobarCreacionCliente(
                dtCliente.getNickname(),
                dtCliente,
                "Contraseña null",
                false
        ));
    }
}