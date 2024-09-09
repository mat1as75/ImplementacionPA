
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package espotify.persistencia;

import espotify.DataTypes.DTAlbum_SinDTArtista;
import espotify.DataTypes.DTGenero;
import espotify.DataTypes.DTTemaConRuta;
import espotify.DataTypes.DTTemaConURL;
import espotify.DataTypes.DTTemaGenerico;
import espotify.logica.Album;
import espotify.logica.Artista;
import espotify.logica.Cliente;
import espotify.logica.Genero;
import espotify.logica.Tema;
import espotify.logica.TemaConRuta;
import espotify.logica.TemaConURL;
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
         //creo el album vacio
        Album nuevoAlbum = new Album();
        this.albJpa.create(nuevoAlbum);
        
        //busco el artista para agregarle el album
        Artista art = this.artJpa.findArtista(dataAlbum.getMiArtista());
        if (art == null) throw new Exception("No se encontro el artista: " +  dataAlbum.getMiArtista());
        List<Album> albumsDelArtista = art.getMisAlbumesPublicados();
        for (Album al : albumsDelArtista) {
            if (al.getNombreAlbum().equals(dataAlbum.getNombreAlbum())) {
                this.albJpa.destroy(nuevoAlbum.getIdAlbum());
                throw new Exception("Este artista ya tiene un album con el nombre: " + dataAlbum.getNombreAlbum());
            }
        }
        art.setMisAlbumesPublicados(nuevoAlbum);
        
        //busco los generos para agregarlos al album y agregar el album a la lista de albums que tiene genero
        List<Genero> generosDeAlbum = new ArrayList();
        for (DTGenero dataG : dataAlbum.getMisgeneros()) {
            Genero gen = this.genJpa.findGenero(dataG.getNombreGenero());
            if (gen == null) throw new Exception("No se encontro el genero: " + dataG.getNombreGenero());
            
            generosDeAlbum.add(gen);
            //agrego el nuevo album en la lista de albums del genero
            gen.setMisAlbumes(nuevoAlbum);
        }
        
        //creo los temas y los agrego a una lista
        List<Tema> temas = new ArrayList();
        for (DTTemaGenerico dataTema : dataAlbum.getMisTemas()) {
            if (dataTema instanceof DTTemaConRuta) {
                TemaConRuta nuevoTemaConRuta = new TemaConRuta(
                        ((DTTemaConRuta) dataTema).getRutaTema(),
                        dataTema.getNombreTema(),
                        dataTema.getDuracionSegundos(),
                        dataTema.getPosicionEnAlbum(),
                        nuevoAlbum
                        );
                this.temaJpa.create(nuevoTemaConRuta);
                temas.add(nuevoTemaConRuta);
            } else {
                
                TemaConURL nuevoTemaConURL = new TemaConURL(
                        ((DTTemaConURL) dataTema).getUrlTema(),
                        dataTema.getNombreTema(),
                        dataTema.getDuracionSegundos(),
                        dataTema.getPosicionEnAlbum(),
                        nuevoAlbum
                    );
                this.temaJpa.create(nuevoTemaConURL);
                temas.add(nuevoTemaConURL);
            }
        }
        
        //set atributos
        nuevoAlbum.setAnioCreacion(dataAlbum.getAnioCreacion());
        nuevoAlbum.setFotoAlbum(dataAlbum.getFotoAlbum());
        nuevoAlbum.setNombreAlbum(dataAlbum.getNombreAlbum());
        //set pseudoatributos
        nuevoAlbum.setMiArtista(art);
        nuevoAlbum.setMisGeneros(generosDeAlbum);
        nuevoAlbum.setMisTemas(temas);
        //guardar cambios
        this.albJpa.edit(nuevoAlbum);
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


