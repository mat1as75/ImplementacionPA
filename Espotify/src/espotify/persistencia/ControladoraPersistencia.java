
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package espotify.persistencia;

import espotify.DataTypes.DTDatosArtista;
import espotify.logica.Album;
import espotify.logica.Artista;
import espotify.logica.Cliente;
import espotify.logica.Genero;
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

    /* A partir del Nickname de un Artista, se retorna 
    toda su informacion dentro de un DTDatosArtista 
    CASO DE USO: CONSULTAR PERFIL ARTISTA */
    public DTDatosArtista getDatosArtista(String nicknameArtista) {
        
        Artista a = artJpa.findArtista(nicknameArtista);
        
        // Nicknames de Seguidores del Artista
        List<Usuario> listaSeguidores = a.getMisSeguidores();
        ArrayList<String> nicknamesSeguidores = new ArrayList<>();
        for (Usuario lSeg: listaSeguidores) {
            nicknamesSeguidores.add(lSeg.getNickname());
        }
        // Cantidad de Seguidores
        int cantSeguidores = listaSeguidores.size();
        
        // Nombre de AlbumesPublicados del Artista
        List<Album> listaAlbumesPublicados = a.getMisAlbumesPublicados();
        ArrayList<String> nombresAlbumesPublicados = new ArrayList<>();
        for (Album nomAlbumesP: listaAlbumesPublicados) {
            nombresAlbumesPublicados.add(nomAlbumesP.getNombreAlbum());
        }
        
        DTDatosArtista DTDatosA = new DTDatosArtista(a.getNickname(), 
                a.getNombreUsuario(), a.getApellidoUsuario(), 
                a.getEmail(), a.getFecNac(), a.getFotoPerfil(), 
                a.getBiografia(), a.getDirSitioWeb(), 
                cantSeguidores, nicknamesSeguidores, nombresAlbumesPublicados);
        
        return DTDatosA;
    }
}
