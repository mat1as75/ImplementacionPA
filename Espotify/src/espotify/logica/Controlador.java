/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package espotify.logica;

import espotify.persistencia.ControladoraPersistencia;
import espotify.persistencia.GeneroJpaController;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author tecnologo
 */
public class Controlador implements IControlador{
    ControladoraPersistencia contpersis=new ControladoraPersistencia();
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
       /* GeneroJpaController GJP = GeneroJpaController.getInstance();
        Genero g = new Genero(nombreGenero);
        
        try {
            GJP.create(g);
        } catch (Exception ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
       this.contpersis.AltaGenero(nombreGenero);
    }
    public void AltaArtista(Artista a){
        this.contpersis.AltaArtista(a);
    };
    public void AltaCliente(Cliente c){
        this.contpersis.AltaCliente(c);
    }
    public List<String>getNicknamesArtistas(){
        return null;
    };
    
    public boolean ExisteNickName(String nickname){
        return this.contpersis.ExisteNickName(nickname);
    }
    public boolean ExisteEmail(String email){
        return this.contpersis.ExisteEmail(email);
    }
}
