/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package espotify.logica;

import espotify.persistencia.ArtistaJpaController;
import espotify.persistencia.ClienteJpaController;
import espotify.persistencia.GeneroJpaController;
import espotify.persistencia.UsuarioJpaController;
import espotify.persistencia.exceptions.UsuarioRepetidoException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author tecnologo
 */
public class Controlador implements IControlador{

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
    public void AltaPerfil(String tipoUsuario, String nickname, String nombreU, String apellidoU, String email, Date fecNac, String fotoPerfil, String biografia, String dirSitioWeb) {
        /*
        UsuarioJpaController UJP = UsuarioJpaController.getInstance();
        ArtistaJpaController AJP = ArtistaJpaController.getInstance();
        ClienteJpaController CJP = ClienteJpaController.getInstance();
        Usuario u = UJP.findUsuario(nickname);
        
        if (u != null) {
            try {
                throw new UsuarioRepetidoException("El usuario " + nickname + " ya está registrado");
            } catch (UsuarioRepetidoException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if (tipoUsuario.equals("artista")) {
            Artista a = new Artista(nickname, nombreU, apellidoU, email, fecNac, fotoPerfil, biografia, dirSitioWeb);
            AJP.create(a);
        } else { 
            Cliente c = new Cliente(nickname, nombreU, apellidoU, email, fecNac, fotoPerfil);
            CJP.create(c);
        }
        
        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("Espotify");
        //EntityManager em = emf.createEntityManager();
        //EntityTransaction t = em.getTransaction();
        //try {
        //    t.begin();
        //    em.persist(u);
        //    t.commit();
        //} catch (Exception e) {
        //    t.rollback();
        //}
        */
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
    
    @Override 
    public List<Artista> ObtenerListaArtistas() {
        ArtistaJpaController AJP = ArtistaJpaController.getInstance();             
        return AJP.findArtistaEntities();
    }
    
    @Override
    public void ConsultarPerfilArtista() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EspotifyPU");
        EntityManager em = emf.createEntityManager();
        
        
        
    }
}
