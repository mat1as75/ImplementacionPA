import espotify.DataTypes.DTDatosArtista;
import espotify.DataTypes.DTDatosCliente;
import espotify.persistencia.ControladoraPersistencia;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import static java.util.Map.entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ConsultasDatosUsuarioTest {
    
    private static ControladoraPersistencia cp;
    private static CargarDatosTest cdt;
    
    public ConsultasDatosUsuarioTest() {
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
    public void getDatosArtista1() {
        DTDatosArtista dtArtista = cp.getDatosArtista("alcides");
        int datosIncorrectos = 0;

        List<String> seguidoresEsperados = Arrays.asList("benKenobi", "cbochinche", "Heisenberg", "lachiqui");
        Map<Long, String> albumsEsperados = Map.ofEntries(entry(13L, "20 Grandes Éxitos"));
        String nicknameEsperado = "alcides";
        String nombreEsperado = "Alcides";
        String apellidoEsperado = "";
        String emailEsperado = "alcides@tuta.io";
        String dirSitioWebEsperada = null;
        Date fechaNacEsperada = null;
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        try {
            fechaNacEsperada = dateFormatter.parse("17/07/1952");
        } catch (ParseException ex) {
            Logger.getLogger(ConsultasDatosUsuarioTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (!dtArtista.getNicknamesSeguidores().containsAll(seguidoresEsperados)) {
            datosIncorrectos++;
        }
        
        if (!dtArtista.getNombresAlbumesPublicados().equals(albumsEsperados)) {
            datosIncorrectos++;
        }
        
        if (!dtArtista.getNicknameUsuario().equals(nicknameEsperado)) {
            datosIncorrectos++;
        }
        
        if (!dtArtista.getNombreUsuario().equals(nombreEsperado)) {
            datosIncorrectos++;
        }
        
        if (!dtArtista.getApellidoUsuario().equals(apellidoEsperado)) {
            datosIncorrectos++;
        }
        
        if (!dtArtista.getEmail().equals(emailEsperado)) {
            datosIncorrectos++;
        }
        
        if (dtArtista.getDirSitioWeb() == null ? dirSitioWebEsperada != null : !dtArtista.getDirSitioWeb().equals(dirSitioWebEsperada)) {
            datosIncorrectos++;
        }
        
        if (!dtArtista.getFecNac().equals(fechaNacEsperada)) {
            datosIncorrectos++;
        }
        
        if (datosIncorrectos == 0) {
            System.out.println("Test Consultas de Datos: getDatosArtista('alcides') : OK");
        } else {
            System.out.println("Test Consultas de Datos: getDatosArtista('alcides') : FAIL");
        }
        
        assertTrue(datosIncorrectos == 0);
    }
    
    @Test
    public void getDatosArtista2() {
        DTDatosArtista dtArtista = cp.getDatosArtista("la_ley");
        int datosIncorrectos = 0;

        List<String> seguidoresEsperados = new ArrayList();
        Map<Long, String> albumsEsperados = Map.ofEntries(entry(7L, "MTV Unplugged"));
        String nicknameEsperado = "la_ley";
        String nombreEsperado = "La";
        String apellidoEsperado = "Ley";
        String emailEsperado = "la_ley@tuta.io";
        String dirSitioWebEsperada = null;
        Date fechaNacEsperada = null;
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        try {
            fechaNacEsperada = dateFormatter.parse("14/02/1987");
        } catch (ParseException ex) {
            Logger.getLogger(ConsultasDatosUsuarioTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (!dtArtista.getNicknamesSeguidores().containsAll(seguidoresEsperados)) {
            datosIncorrectos++;
        }
        
        if (!dtArtista.getNombresAlbumesPublicados().equals(albumsEsperados)) {
            datosIncorrectos++;
        }
        
        if (!dtArtista.getNicknameUsuario().equals(nicknameEsperado)) {
            datosIncorrectos++;
        }
        
        if (!dtArtista.getNombreUsuario().equals(nombreEsperado)) {
            datosIncorrectos++;
        }
        
        if (!dtArtista.getApellidoUsuario().equals(apellidoEsperado)) {
            datosIncorrectos++;
        }
        
        if (!dtArtista.getEmail().equals(emailEsperado)) {
            datosIncorrectos++;
        }
        
        if (dtArtista.getDirSitioWeb() == null ? dirSitioWebEsperada != null : !dtArtista.getDirSitioWeb().equals(dirSitioWebEsperada)) {
            datosIncorrectos++;
        }
        
        if (!dtArtista.getFecNac().equals(fechaNacEsperada)) {
            datosIncorrectos++;
        }
        
        if (datosIncorrectos == 0) {
            System.out.println("Test Consultas de Datos: getDatosArtista('la_ley') : OK");
        } else {
            System.out.println("Test Consultas de Datos: getDatosArtista('la_ley') : FAIL");
        }
        
        assertTrue(datosIncorrectos == 0);
    }
    
    @Test
    public void getDatosArtista3() {
        DTDatosArtista dtArtista = cp.getDatosArtista("tigerOfWales");
        int datosIncorrectos = 0;

        List<String> seguidoresEsperados = new ArrayList();
        Map<Long, String> albumsEsperados = Map.ofEntries(entry(5L, "It’s Not Unusual"));
        String nicknameEsperado = "tigerOfWales";
        String nombreEsperado = "Tom";
        String apellidoEsperado = "Jones";
        String emailEsperado = "tigerOfWales@tuta.io";
        String dirSitioWebEsperada = "www.tomjones.net";
        Date fechaNacEsperada = null;
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        try {
            fechaNacEsperada = dateFormatter.parse("07/06/1940");
        } catch (ParseException ex) {
            Logger.getLogger(ConsultasDatosUsuarioTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (!dtArtista.getNicknamesSeguidores().containsAll(seguidoresEsperados)) {
            datosIncorrectos++;
        }
        
        if (!dtArtista.getNombresAlbumesPublicados().equals(albumsEsperados)) {
            datosIncorrectos++;
        }
        
        if (!dtArtista.getNicknameUsuario().equals(nicknameEsperado)) {
            datosIncorrectos++;
        }
        
        if (!dtArtista.getNombreUsuario().equals(nombreEsperado)) {
            datosIncorrectos++;
        }
        
        if (!dtArtista.getApellidoUsuario().equals(apellidoEsperado)) {
            datosIncorrectos++;
        }
        
        if (!dtArtista.getEmail().equals(emailEsperado)) {
            datosIncorrectos++;
        }
        
        if (dtArtista.getDirSitioWeb() == null ? dirSitioWebEsperada != null : !dtArtista.getDirSitioWeb().equals(dirSitioWebEsperada)) {
            datosIncorrectos++;
        }
        
        if (!dtArtista.getFecNac().equals(fechaNacEsperada)) {
            datosIncorrectos++;
        }
        
        if (datosIncorrectos == 0) {
            System.out.println("Test Consultas de Datos: getDatosArtista('tigerOfWales') : OK");
        } else {
            System.out.println("Test Consultas de Datos: getDatosArtista('tigerOfWales') : FAIL");
        }
        
        assertTrue(datosIncorrectos == 0);
    }
    
    @Test
    public void getDatosCliente1() {
        DTDatosCliente dtCliente = cp.getDatosCliente("cbochinche");
        int datosIncorrectos = 0;
        
        List<String> seguidosEsperados = Arrays.asList(
                "alcides, Artista", 
                "dyangounchained, Artista", 
                "la_ley, Artista", 
                "lospimpi, Artista", 
                "ppArgento, Cliente");
        List<String> seguidoresEsperados = Arrays.asList("benKenobi", "el_padrino","ppArgento");
        List<String> listasCreadasEsperadas = Arrays.asList("Fiesteras", "Mis Favoritas");
        List<String> listasFavoritasEsperadas = Arrays.asList("Noche De La Nostalgia", "Rock En Español");
        Map<Long, String> albumsFavoritosEsperados = Map.ofEntries(
                entry(11L, "Hay Amores Que Matan"));
        Map<Long, String> temasFavoritosEsperados = Map.ofEntries(
                entry(91L, "Primer Movimiento (Allegro non troppo e molto maestoso – Allegro con spirito) "));
        Long idSuscripcionEsperada = null;
        String nicknameEsperado = "cbochinche";
        String nombreEsperado = "Cacho";
        String apellidoEsperado = "Bochinche";
        String emailEsperado = "cbochinche@vera.com.uy";
        Date fechaNacEsperada = null;
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        try {
            fechaNacEsperada = dateFormatter.parse("08/05/1937");
        } catch (ParseException ex) {
            Logger.getLogger(ConsultasDatosUsuarioTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (!dtCliente.getNicknamesSeguidos().containsAll(seguidosEsperados)) {
            datosIncorrectos++;
        }
        
        if (!dtCliente.getNicknamesSeguidores().containsAll(seguidoresEsperados)) {
            datosIncorrectos++;
        }
        
        if (!dtCliente.getNombresListasRCreadas().containsAll(listasCreadasEsperadas)) {
            datosIncorrectos++;
        }
        
        if (!dtCliente.getNombresListasRFavoritas().containsAll(listasFavoritasEsperadas)) {
            datosIncorrectos++;
        }
        
        if (!dtCliente.getNombresAlbumesFavoritos().equals(albumsFavoritosEsperados)) {
            datosIncorrectos++;
        }
        
        if (!dtCliente.getNombresTemasFavoritos().equals(temasFavoritosEsperados)) {
            datosIncorrectos++;
        }
        
        if (dtCliente.getSuscripcion() == null 
                ? idSuscripcionEsperada != null 
                : !dtCliente.getSuscripcion().getIdSuscripcion().equals(idSuscripcionEsperada)) {
            datosIncorrectos++;
        }
            
        if (!dtCliente.getNicknameUsuario().equals(nicknameEsperado)) {
            datosIncorrectos++;
        }
        
        if (!dtCliente.getNombreUsuario().equals(nombreEsperado)) {
            datosIncorrectos++;
        }
        
        if (!dtCliente.getApellidoUsuario().equals(apellidoEsperado)) {
            datosIncorrectos++;
        }
        
        if (!dtCliente.getEmail().equals(emailEsperado)) {
            datosIncorrectos++;
        }
        
        if (!dtCliente.getFecNac().equals(fechaNacEsperada)) {
            datosIncorrectos++;
        }
        
        if (datosIncorrectos == 0) {
            System.out.println("Test Consultas de Datos: getDatosCliente('cbochinche') : OK");
        } else {
            System.out.println("Test Consultas de Datos: getDatosCliente('cbochinche') : FAIL");
        }
        
        assertTrue(datosIncorrectos == 0);    
    }
    
    @Test
    public void getDatosCliente2() {
        DTDatosCliente dtCliente = cp.getDatosCliente("benKenobi");
        int datosIncorrectos = 0;
        
        List<String> seguidosEsperados = Arrays.asList(
                "alcides, Artista", 
                "bruceTheBoss, Artista",
                "cbochinche, Cliente",
                "chaiko, Artista",
                "dmode, Artista",
                "Eleven11, Cliente",
                "el_padrino, Cliente",
                "lachiqui, Cliente",
                "la_ley, Artista", 
                "lospimpi, Artista",
                "nicoleneu, Artista",
                "ppArgento, Cliente");
        List<String> seguidoresEsperados = Arrays.asList("Heisenberg", "scarlettO", "el_padrino","ppArgento");
        List<String> listasCreadasEsperadas = new ArrayList();
        List<String> listasFavoritasEsperadas = new ArrayList();
        Map<Long, String> albumsFavoritosEsperados = Map.ofEntries(
                entry(8L, "El Lago De Los Cisnes"),
                entry(9L, "Concierto Para Piano No. 1 En Si Menor, Opus 23"));
        Map<Long, String> temasFavoritosEsperados = new HashMap();        
        Long idSuscripcionEsperada = 5L;
        String nicknameEsperado = "benKenobi";
        String nombreEsperado = "Obi-Wan";
        String apellidoEsperado = "Kenobi";
        String emailEsperado = "benKenobi@gmail.com";
        Date fechaNacEsperada = null;
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        try {
            fechaNacEsperada = dateFormatter.parse("02/02/1914");
        } catch (ParseException ex) {
            Logger.getLogger(ConsultasDatosUsuarioTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (!dtCliente.getNicknamesSeguidos().containsAll(seguidosEsperados)) {
            datosIncorrectos++;
        }
        
        if (!dtCliente.getNicknamesSeguidores().containsAll(seguidoresEsperados)) {
            datosIncorrectos++;
        }
        
        if (!dtCliente.getNombresListasRCreadas().containsAll(listasCreadasEsperadas)) {
            datosIncorrectos++;
        }
        
        if (!dtCliente.getNombresListasRFavoritas().containsAll(listasFavoritasEsperadas)) {
            datosIncorrectos++;
        }
        
        if (!dtCliente.getNombresAlbumesFavoritos().equals(albumsFavoritosEsperados)) {
            datosIncorrectos++;
        }
        
        if (!dtCliente.getNombresTemasFavoritos().equals(temasFavoritosEsperados)) {
            datosIncorrectos++;
        }
        
        if (dtCliente.getSuscripcion() == null 
                ? idSuscripcionEsperada != null 
                : !dtCliente.getSuscripcion().getIdSuscripcion().equals(idSuscripcionEsperada)) {
            datosIncorrectos++;
        }
            
        if (!dtCliente.getNicknameUsuario().equals(nicknameEsperado)) {
            datosIncorrectos++;
        }
        
        if (!dtCliente.getNombreUsuario().equals(nombreEsperado)) {
            datosIncorrectos++;
        }
        
        if (!dtCliente.getApellidoUsuario().equals(apellidoEsperado)) {
            datosIncorrectos++;
        }
        
        if (!dtCliente.getEmail().equals(emailEsperado)) {
            datosIncorrectos++;
        }
        
        if (!dtCliente.getFecNac().equals(fechaNacEsperada)) {
            datosIncorrectos++;
        }
        
        if (datosIncorrectos == 0) {
            System.out.println("Test Consultas de Datos: getDatosCliente('benKenobi') : OK");
        } else {
            System.out.println("Test Consultas de Datos: getDatosCliente('benKenobi') : FAIL");
        }
        
        assertTrue(datosIncorrectos == 0);    
    } 
    
    @Test
    public void getDatosCliente3() {
        DTDatosCliente dtCliente = cp.getDatosCliente("el_padrino");
        int datosIncorrectos = 0;
        
        List<String> seguidosEsperados = Arrays.asList(
                "benKenobi, Cliente", 
                "cbochinche, Cliente",
                "clauper, Artista",
                "dmode, Artista");
        List<String> seguidoresEsperados = new ArrayList();
        List<String> listasCreadasEsperadas = Arrays.asList("Música Inspiradora");
        List<String> listasFavoritasEsperadas = Arrays.asList("Música Clásica", "Noche De La Nostalgia");
        Map<Long, String> albumsFavoritosEsperados = Map.ofEntries(
                entry(8L, "El Lago De Los Cisnes"),
                entry(9L, "Concierto Para Piano No. 1 En Si Menor, Opus 23"),
                entry(2L, "Violator"));
        Map<Long, String> temasFavoritosEsperados = Map.ofEntries(entry(71L, "El Duelo"));        
        Long idSuscripcionEsperada = 3L;
        String nicknameEsperado = "el_padrino";
        String nombreEsperado = "Vito";
        String apellidoEsperado = "Corleone";
        String emailEsperado = "el_padrino@tuta.io";
        Date fechaNacEsperada = null;
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        try {
            fechaNacEsperada = dateFormatter.parse("08/03/1972");
        } catch (ParseException ex) {
            Logger.getLogger(ConsultasDatosUsuarioTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (!dtCliente.getNicknamesSeguidos().containsAll(seguidosEsperados)) {
            datosIncorrectos++;
        }
        
        if (!dtCliente.getNicknamesSeguidores().containsAll(seguidoresEsperados)) {
            datosIncorrectos++;
        }
        
        if (!dtCliente.getNombresListasRCreadas().containsAll(listasCreadasEsperadas)) {
            datosIncorrectos++;
        }
        
        if (!dtCliente.getNombresListasRFavoritas().containsAll(listasFavoritasEsperadas)) {
            datosIncorrectos++;
        }
        
        if (!dtCliente.getNombresAlbumesFavoritos().equals(albumsFavoritosEsperados)) {
            datosIncorrectos++;
        }
        
        if (!dtCliente.getNombresTemasFavoritos().equals(temasFavoritosEsperados)) {
            datosIncorrectos++;
        }
        
        if (dtCliente.getSuscripcion() == null 
                ? idSuscripcionEsperada != null 
                : !dtCliente.getSuscripcion().getIdSuscripcion().equals(idSuscripcionEsperada)) {
            datosIncorrectos++;
        }
            
        if (!dtCliente.getNicknameUsuario().equals(nicknameEsperado)) {
            datosIncorrectos++;
        }
        
        if (!dtCliente.getNombreUsuario().equals(nombreEsperado)) {
            datosIncorrectos++;
        }
        
        if (!dtCliente.getApellidoUsuario().equals(apellidoEsperado)) {
            datosIncorrectos++;
        }
        
        if (!dtCliente.getEmail().equals(emailEsperado)) {
            datosIncorrectos++;
        }
        
        if (!dtCliente.getFecNac().equals(fechaNacEsperada)) {
            datosIncorrectos++;
        }
        
        if (datosIncorrectos == 0) {
            System.out.println("Test Consultas de Datos: getDatosCliente('el_padrino') : OK");
        } else {
            System.out.println("Test Consultas de Datos: getDatosCliente('el_padrino') : FAIL");
        }
        
        assertTrue(datosIncorrectos == 0);    
    }
    
    @Test
    public void getDatosUsuarioArtista1() {
        DTDatosArtista dtUsuario = (DTDatosArtista) cp.getDatosUsuario("nicoleneu");
        int datosIncorrectos = 0;
        
        List<String> seguidoresEsperados = new ArrayList();
        Map<Long, String> albumsPublicadosEsperados = Map.ofEntries(
                entry(10L, "Primer Amor")
        );
        String nicknameEsperado = "nicoleneu";
        String nombreEsperado = "Nicole";
        String apellidoEsperado = "Neumann";
        String emailEsperado = "nicoleneu@hotmail.com";
        Date fechaNacEsperada = null;
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        try {
            fechaNacEsperada = dateFormatter.parse("31/10/1980");
        } catch (ParseException ex) {
            Logger.getLogger(ConsultasDatosUsuarioTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (!dtUsuario.getNicknamesSeguidores().containsAll(seguidoresEsperados)) {
            datosIncorrectos++;
        }
        
        if (!dtUsuario.getNombresAlbumesPublicados().equals(albumsPublicadosEsperados)) {
            datosIncorrectos++;
        }
            
        if (!dtUsuario.getNicknameUsuario().equals(nicknameEsperado)) {
            datosIncorrectos++;
        }
        
        if (!dtUsuario.getNombreUsuario().equals(nombreEsperado)) {
            datosIncorrectos++;
        }
        
        if (!dtUsuario.getApellidoUsuario().equals(apellidoEsperado)) {
            datosIncorrectos++;
        }
        
        if (!dtUsuario.getEmail().equals(emailEsperado)) {
            datosIncorrectos++;
        }
        
        if (!dtUsuario.getFecNac().equals(fechaNacEsperada)) {
            datosIncorrectos++;
        }
        
        if (datosIncorrectos == 0) {
            System.out.println("Test Consultas de Datos: getDatosUsuario('nicoleneu') : OK");
        } else {
            System.out.println("Test Consultas de Datos: getDatosUsuario('nicoleneu') : FAIL");
        }
        
        assertTrue(datosIncorrectos == 0);  
    }
    
    @Test
    public void getDatosUsuarioCliente1() {
        DTDatosCliente dtUsuario = (DTDatosCliente) cp.getDatosUsuario("scarlettO");
        int datosIncorrectos = 0;
        
        List<String> seguidosEsperados = Arrays.asList(
                "tripleNelson, Artista",
                "tigerOfWales, Artista",
                "dmode, Artista",
                "bruceTheBoss, Artista",
                "lachiqui, Cliente", 
                "Heisenberg, Cliente",
                "benKenobi, Cliente");
        List<String> seguidoresEsperados = Arrays.asList("lachiqui", "Heisenberg", "Eleven11");
        List<String> listasCreadasEsperadas = Arrays.asList("De Todo Un Poco");
        List<String> listasFavoritasEsperadas = Arrays.asList("Música Clásica");
        Map<Long, String> albumsFavoritosEsperados = new HashMap();
        Map<Long, String> temasFavoritosEsperados = new HashMap();       
        Long idSuscripcionEsperada = 6L;
        String nicknameEsperado = "scarlettO";
        String nombreEsperado = "Scarlett";
        String apellidoEsperado = "O’Hara";
        String emailEsperado = "scarlettO@tuta.io";
        Date fechaNacEsperada = null;
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        try {
            fechaNacEsperada = dateFormatter.parse("27/11/1984");
        } catch (ParseException ex) {
            Logger.getLogger(ConsultasDatosUsuarioTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (!dtUsuario.getNicknamesSeguidos().containsAll(seguidosEsperados)) {
            datosIncorrectos++;
        }
        
        if (!dtUsuario.getNicknamesSeguidores().containsAll(seguidoresEsperados)) {
            datosIncorrectos++;
        }
        
        if (!dtUsuario.getNombresListasRCreadas().containsAll(listasCreadasEsperadas)) {
            datosIncorrectos++;
        }
        
        if (!dtUsuario.getNombresListasRFavoritas().containsAll(listasFavoritasEsperadas)) {
            datosIncorrectos++;
        }
        
        if (!dtUsuario.getNombresAlbumesFavoritos().equals(albumsFavoritosEsperados)) {
            datosIncorrectos++;
        }
        
        if (!dtUsuario.getNombresTemasFavoritos().equals(temasFavoritosEsperados)) {
            datosIncorrectos++;
        }
        
        if (dtUsuario.getSuscripcion() == null 
                ? idSuscripcionEsperada != null 
                : !dtUsuario.getSuscripcion().getIdSuscripcion().equals(idSuscripcionEsperada)) {
            datosIncorrectos++;
        }
            
        if (!dtUsuario.getNicknameUsuario().equals(nicknameEsperado)) {
            datosIncorrectos++;
        }
        
        if (!dtUsuario.getNombreUsuario().equals(nombreEsperado)) {
            datosIncorrectos++;
        }
        
        if (!dtUsuario.getApellidoUsuario().equals(apellidoEsperado)) {
            datosIncorrectos++;
        }
        
        if (!dtUsuario.getEmail().equals(emailEsperado)) {
            datosIncorrectos++;
        }
        
        if (!dtUsuario.getFecNac().equals(fechaNacEsperada)) {
            datosIncorrectos++;
        }
        
        if (datosIncorrectos == 0) {
            System.out.println("Test Consultas de Datos: getDatosCliente('scarlettO') : OK");
        } else {
            System.out.println("Test Consultas de Datos: getDatosCliente('scarlettO') : FAIL");
        }
        
        assertTrue(datosIncorrectos == 0); 
    }
    
}