/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package espotify.logica;

import espotify.DataTypes.DTCliente;
import espotify.DataTypes.DTDatosArtista;
import espotify.DataTypes.DTDatosCliente;
import espotify.DataTypes.DTListaReproduccion;
import espotify.persistencia.ArtistaJpaController;
import espotify.persistencia.ControladoraPersistencia;
import espotify.persistencia.GeneroJpaController;
import java.util.ArrayList;
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
    ControladoraPersistencia contpersis = new ControladoraPersistencia();
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
    
    @Override
    public void AltaArtista(Artista a){
        this.contpersis.AltaArtista(a);
    }
  
    @Override
    public void AltaCliente(Cliente c){
        this.contpersis.AltaCliente(c);
    }
    
    @Override
    public List<String>getNicknamesArtistas(){
        ArrayList<Artista> artistas = contpersis.getArtistas();
        ArrayList<String> nicknames = new ArrayList<String>();
        for (Artista a : artistas) {
            nicknames.add(a.getNickname());
        }

        return nicknames;
    };
    
    @Override
    public List<String>getNicknamesClientes() {
        ArrayList<Cliente> clientes = contpersis.getClientes();
        ArrayList<String> nicknames = new ArrayList<String>();

        for (Cliente c : clientes) {
            nicknames.add(c.getNickname());
        }

        return nicknames;
    }
    
    @Override
    public boolean ExisteNickName(String nickname){
        return this.contpersis.ExisteNickName(nickname);
    }
  
    @Override
    public boolean ExisteEmail(String email){
        return this.contpersis.ExisteEmail(email);
    }
    
    @Override
    public boolean existeNombreLista(String nombreLista){
        return this.contpersis.ExisteNombreLista(nombreLista);
    }
    
    @Override
    public DTDatosArtista ConsultarPerfilArtista(String nicknameArtista) {

        return this.contpersis.getDatosArtista(nicknameArtista);
    }
    
    @Override
    public DTDatosCliente ConsultarPerfilCliente(String nicknameCliente) {
        
        return this.contpersis.getDatosCliente(nicknameCliente);
    }

    
    public  void setSeguidorSeguido(String Seguidor, String Seguido){
        this.contpersis.setSeguidorSeguido(Seguidor,Seguido);
    };
    
    @Override
    public void CrearListaPorDefecto(String nombreLista, String fotoLista, Genero genero) {
        this.contpersis.CrearListaPorDefecto(nombreLista, fotoLista, genero);
    }

    @Override
    public void CrearListaParticular(String nombreLista, String fotoLista, String nicknameCliente, boolean esPrivada) {
        this.contpersis.CrearListaParticular(nombreLista, fotoLista, nicknameCliente, esPrivada);
    }
    
   /* @Override
    public DTListaReproduccion ConsultarListaReproduccion(String tipoDeLista, String op) {
        return this.contpersis.getDatosListaReproduccion(tipoDeLista, op);
    }
    */
}
