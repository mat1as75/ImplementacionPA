import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import mail.MailHandler;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class MailTest {
    
    public MailTest() {
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

    /*
    @Test
    public void testSendMail1() {
    
        LocalDate fechaDeHoy = LocalDate.now();
        String usuario = "Pepe Argento";
    
        //Si cambian el correo emailUsuario pueden probar enviarse a si mismos el correo y probarlo
        String emailUsuario = "espotify.g3.mail@gmail.com";
    
        String tipoSuscripcion = "Anual";
        String fechaSuscripcion = fechaDeHoy.format(DateTimeFormatter.ISO_DATE);
        
        String asunto = MailHandler.buildMsgSubject();
        String cuerpo = MailHandler.buildMsgBody(
                usuario, 
                tipoSuscripcion, 
                fechaSuscripcion);
        
        assertTrue(MailHandler.sendEmail(
                emailUsuario, 
                asunto, 
                cuerpo));
    }
    */
    
    @Test
    public void test() {
        assertTrue(true);
    }
}
