package espotify.logica;

import espotify.persistencia.ControladoraPersistencia;
import espotify.persistencia.GeneroJpaController;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Controlador implements IControlador{

    ControladoraPersistencia ctrlPersistencia = new ControladoraPersistencia();
    
    // 1 de Singleton
    private Controlador() {
        
    }
    
    // 2 de Singleton
    private static Controlador miInstancia = null;
    
    // 3 de Singleton
    public static Controlador getInstance() {
        if (Controlador.miInstancia == null) {
            Controlador.miInstancia = new Controlador();
        }
        return (Controlador.miInstancia);
    }

    @Override
    public void AltaGenero(String nombreGenero) {
        GeneroJpaController GJP = GeneroJpaController.getInstance();
        Genero g = new Genero(nombreGenero);
        
        try {
            GJP.create(g);
        } catch (Exception ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public ArrayList<String> getNicknamesArtistas() {
         ArrayList<Artista> artistas = ctrlPersistencia.getArtistas();
         ArrayList<String> nicknames = new ArrayList<String>();
         
         for (Artista a : artistas) {
             nicknames.add(a.getNickname());
         }
         
         return nicknames;
     }
}
