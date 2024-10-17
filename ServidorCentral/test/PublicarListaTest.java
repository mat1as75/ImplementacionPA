import espotify.logica.Fabrica;
import espotify.logica.IControlador;
import espotify.persistencia.ControladoraPersistencia;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass; 
import org.junit.Test;
import static org.junit.Assert.*;

public class PublicarListaTest {
    private static ControladoraPersistencia cp;
    private static Fabrica f=Fabrica.getInstance();
    private static IControlador i=f.getControlador();
   
    public PublicarListaTest() {
        cp=new ControladoraPersistencia();
    }
    public Boolean comprobarSetPrivadafalse(String nicknameCliente,String nombreLista, String test, Boolean expected) {
        
        Boolean fueCreado = false;
        try {
            cp.setPrivadafalse(nicknameCliente, nombreLista);
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
    public void publicarLista1() {
        String nicknameCliente="cbochinche";
        String nombreLista="Mis Favoritas";
        ArrayList<String> estPrivTrue=cp.listasCreadasEstadoPrivadoTrue(nicknameCliente);
        boolean existe=false;
        for(String ept:estPrivTrue){
            if(ept.equals(nombreLista)){
                existe=true;
            }
        }
        if (existe) {
            assertTrue(comprobarSetPrivadafalse(nicknameCliente,nombreLista,"publicar lista cbochinche Mis Favoritas", true));
        }else{
            assertTrue(comprobarSetPrivadafalse(nicknameCliente,nombreLista,"publicar lista cbochinche Mis Favoritas", false));
        }
     
    }
    @Test
    public void publicarLista2() {
        String nicknameCliente="Heisenberg";
        String nombreLista="Para Cocinar";
        ArrayList<String> estPrivTrue=cp.listasCreadasEstadoPrivadoTrue(nicknameCliente);
        boolean existe=false;
        for(String ept:estPrivTrue){
            if(ept.equals(nombreLista)){
                existe=true;
            }
        }
        if (existe) {
            assertTrue(comprobarSetPrivadafalse(nicknameCliente,nombreLista,"publicar lista Heisenberg Para Cocinar", true));
        }else{
            assertTrue(comprobarSetPrivadafalse(nicknameCliente,nombreLista,"publicar lista Heisenberg Para Cocinar", false));
        }
        
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

}
