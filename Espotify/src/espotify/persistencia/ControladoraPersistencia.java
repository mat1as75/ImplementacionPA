
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package espotify.persistencia;

import espotify.DataTypes.DTAlbum_SinDTArtista;
import espotify.DataTypes.DTTemaConRuta;
import espotify.DataTypes.DTTemaGenerico;
import espotify.logica.Artista;
import espotify.logica.Cliente;
import espotify.logica.Genero;
import espotify.logica.Tema;
import espotify.logica.Usuario;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ControladoraPersistencia {
    
    UsuarioJpaController usuJpa = new UsuarioJpaController();
    ArtistaJpaController artJpa = new ArtistaJpaController();
    ClienteJpaController cliJpa = new ClienteJpaController();
    GeneroJpaController genJpa = new GeneroJpaController();
    AlbumJpaController albJpa = new AlbumJpaController();
    ListaParticularJpaController lpartJpa = new ListaParticularJpaController();
    ListaPorDefectoJpaController lxdefcJpa = new ListaPorDefectoJpaController();
    ListaReproduccionJpaController lreprodccJpa = new ListaReproduccionJpaController();
    TemaJpaController temaJpa = new TemaJpaController();
    TemaConRutaJpaController temaconrutaJpa = new TemaConRutaJpaController();
    TemaConURLJpaController temaurlJpa = new TemaConURLJpaController();

    public ControladoraPersistencia(){};
    
    public void AltaGenero(String nombreGenero) {
        Genero genero=new Genero(nombreGenero);
        try {
            genJpa.create(genero);//para que lo guarde en la BD
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void AltaArtista(Artista u) {
        try {
            this.artJpa.create(u);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void AltaCliente(Cliente c) {
        try {
            this.cliJpa.create(c);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void AltaAlbum(DTAlbum_SinDTArtista dataAlbum) throws Exception {
        Artista art = this.artJpa.findArtista(dataAlbum.getMiArtista());
        if (art == null) throw new Exception("No se encontro el artista: " +  dataAlbum.getMiArtista());
        
        List<Tema> temas;
        for (DTTemaGenerico dataTema : dataAlbum.getMisTemas()) {
            if (dataTema instanceof DTTemaConRuta) {
                //tengo que solucionar lo de la clave compuesta de tema antes
            }
        }
    }
    
    public Artista getArtista(String nickname) {
        Artista art = this.artJpa.findArtista(nickname);
        return art;
    }
    
    public ArrayList<Artista> getArtistas() {
        
        List<Artista> artistasL = artJpa.findArtistaEntities();
        ArrayList<Artista> artistasAL = new ArrayList<Artista>(artistasL);
        
        return artistasAL;
    }
    
    public ArrayList<Cliente> getClientes() {
        
        List<Cliente> clientesL = cliJpa.findClienteEntities();
        ArrayList<Cliente> clientesAL = new ArrayList<Cliente>(clientesL);
        
        return clientesAL;
    }

    public boolean ExisteNickName(String nickname) {
        List<Usuario> usuarios=this.usuJpa.findUsuarioEntities();
        boolean retorno=false;
        for (Usuario u : usuarios) {
            String usuario=u.getNickname();
            if(usuario.equals(nickname)){
                retorno=true;
            }
        }        
        return retorno;
                
    }
    public boolean ExisteEmail(String email) {
        List<Usuario> usuarios=this.usuJpa.findUsuarioEntities();
        boolean retorno=false;
        for (Usuario u : usuarios) {
            String mail=u.getEmail();
            if(mail.equals(email)){
                retorno=true;
            }
        }        
        return retorno;
                
    }

}


