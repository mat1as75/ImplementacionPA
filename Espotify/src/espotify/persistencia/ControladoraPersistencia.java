
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package espotify.persistencia;

import espotify.DataTypes.DTCliente;
import espotify.DataTypes.DTDatosArtista;
import espotify.DataTypes.DTDatosCliente;
import espotify.logica.Album;
import espotify.logica.Artista;
import espotify.logica.Cliente;
import espotify.logica.Genero;
import espotify.logica.ListaParticular;
import espotify.logica.ListaPorDefecto;
import espotify.logica.ListaReproduccion;
import espotify.logica.Tema;
import espotify.logica.Usuario;
import java.text.SimpleDateFormat;
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
    
    public boolean ExisteNombreLista(String nombreLista) {
        List<ListaReproduccion> listaReproducciones = this.lreprodccJpa.findListaReproduccionEntities();
        boolean retorno=false;
        for (ListaReproduccion l : listaReproducciones) {
            String nombreList = l.getNombreLista();
            if(nombreList.equals(nombreLista)){
                retorno=true;
            }
        }        
        return retorno;
                
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
    
    public DTDatosCliente getDatosCliente(String nicknameCliente) {
        
        Cliente c = cliJpa.findCliente(nicknameCliente);
        
        // Nicknames de Seguidos del Cliente
        List<Usuario> listaSeguidos = c.getMisSeguidos();
        ArrayList<String> nicknamesSeguidos = new ArrayList<>();
        for (Usuario lSeg: listaSeguidos) {
            String nickname;
            
            // El seguido es Cliente ? | Sino es Artista
            if (lSeg.getClass().equals(Cliente.class))
                nickname = lSeg.getNickname() + ", Cliente";
            else
                nickname = lSeg.getNickname() + ", Artista";
            
            nicknamesSeguidos.add(nickname);
        }
        
        // Nicknames de Seguidores del Cliente
        List<Usuario> listaSeguidores = c.getMisSeguidores();
        ArrayList<String> nicknamesSeguidores = new ArrayList<>();
        for (Usuario lSeg: listaSeguidores) {
            nicknamesSeguidores.add(lSeg.getNickname());
        }
        
        // Nombres de ListasR Creadas del Cliente
        List<ListaParticular> listaListasRCreadas = c.getMisListasReproduccionCreadas();
        ArrayList<String> nombresListasRCreadas = new ArrayList<>();
        for (ListaParticular lPCreada: listaListasRCreadas) {
            nombresListasRCreadas.add(lPCreada.getNombreLista());
        }
        
        // Nombres de ListasR Favoritas del Cliente
        List<ListaReproduccion> listaListasRFavoritas = c.getMisListasReproduccionFav();
        ArrayList<String> nombresListasRFavoritas = new ArrayList<>();
        for (ListaReproduccion lRFavoritas: listaListasRFavoritas) {
            nombresListasRFavoritas.add(lRFavoritas.getNombreLista());
        }
        
        // Nombres de Albumes Favoritos del Cliente
        List<Album> listaAlbumesFavoritos = c.getMisAlbumesFav();
        ArrayList<String> nombresAlbumesFavoritos = new ArrayList<>();
        for (Album alb: listaAlbumesFavoritos) {
            nombresAlbumesFavoritos.add(alb.getNombreAlbum());
        }
        
        // Nombres de Temas Favoritos del Cliente
        List<Tema> listaTemasFavoritos = c.getMisTemasFav();
        ArrayList<String> nombresTemasFavoritos = new ArrayList<>();
        for (Tema tema: listaTemasFavoritos) {
            nombresTemasFavoritos.add(tema.getNombreTema());
        }
        
        DTDatosCliente datosCliente = new DTDatosCliente(c.getNickname(), 
        c.getNombreUsuario(), c.getApellidoUsuario(), c.getEmail(), 
        c.getFecNac(), c.getFotoPerfil(), nicknamesSeguidos, 
        nicknamesSeguidores, nombresListasRCreadas, nombresListasRFavoritas, 
        nombresAlbumesFavoritos, nombresTemasFavoritos);
        
        return datosCliente;
    }
    
    public void setSeguidorSeguido(String Seguidor, String Seguido) {
        Cliente c=this.cliJpa.findCliente(Seguidor);
        Usuario u=this.usuJpa.findUsuario(Seguido);
        c.setMisSeguidos(u);
        try {
            usuJpa.edit(c);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void CrearListaPorDefecto(String nombreLista, String fotoLista, Genero genero) {
        ListaPorDefecto lista = new ListaPorDefecto(nombreLista, fotoLista, genero);
        try {
            lxdefcJpa.create(lista);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, "Error al crear lista por defecto", ex);
        }
    }

    public void CrearListaParticular(String nombreLista, String fotoLista, Cliente cliente, boolean esPrivada) {
        ListaParticular lista = new ListaParticular(nombreLista, fotoLista, cliente, esPrivada);
        try {
            lpartJpa.create(lista);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, "Error al crear lista particular", ex);
        }
    }
    
    public Cliente getClientePorNickname(String nickname) {
        // Busca el cliente en la base de datos
        Cliente c = cliJpa.findCliente(nickname);
        
        if (c == null){
            return null;
        }
        else{
        return c;
        } 
    }
    
    
}


