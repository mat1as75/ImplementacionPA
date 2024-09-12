
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package espotify.persistencia;


import espotify.DataTypes.DTAlbum;
import espotify.DataTypes.DTAlbum_SinDTArtista;
import espotify.DataTypes.DTGenero;
import espotify.DataTypes.DTTemaConRuta;
import espotify.DataTypes.DTTemaConURL;
import espotify.DataTypes.DTTemaGenerico;
import espotify.DataTypes.DTDatosArtista;
import espotify.DataTypes.DTDatosCliente;
import espotify.DataTypes.DTGenero_Simple;
import espotify.DataTypes.DTTemaSimple;
import espotify.logica.Album;
import espotify.logica.Artista;
import espotify.logica.Cliente;
import espotify.logica.Genero;
import espotify.logica.Tema;
import espotify.logica.TemaConRuta;
import espotify.logica.TemaConURL;
import espotify.logica.ListaParticular;
import espotify.logica.ListaPorDefecto;
import espotify.logica.ListaReproduccion;
import espotify.logica.Usuario;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

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
        if (art == null) {
            this.albJpa.destroy(nuevoAlbum.getIdAlbum());
            throw new Exception("No se encontro el artista: " +  dataAlbum.getMiArtista());
        }
        
        //verifico que no tenga un album con el mismo nombre
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
            if (gen == null) {
                this.albJpa.destroy(nuevoAlbum.getIdAlbum());
                throw new Exception("No se encontro el genero: " + dataG.getNombreGenero());
            }
            
            generosDeAlbum.add(gen);
            //agrego el nuevo album en la lista de albums del genero
            gen.setMisAlbumes(nuevoAlbum);
            //guardo los cambios a cada genero modificado
            try {
                this.genJpa.edit(gen);
            } catch(Exception ex) {
                this.albJpa.destroy(nuevoAlbum.getIdAlbum());
                throw new Exception("Ocurrio un error al actualizar el genero " + gen.getNombreGenero());
            }
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
        //guardar cambios en el artista y el album
        this.artJpa.edit(art);
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

    public boolean ExisteCliente(String nicknameCliente) {
        List<Cliente> clientes = this.cliJpa.findClienteEntities();
        boolean retorno = false;
        for (Cliente c: clientes) {
            String cliente = c.getNickname();
            if (cliente.equals(nicknameCliente)) {
                retorno = true;
            }
        }
        return retorno;
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
        System.out.println("Seguidores: " + c.getMisSeguidores().size());
        ArrayList<String> nicknamesSeguidores = new ArrayList<>();
        for (Usuario lSeg: listaSeguidores) {
            String nickname;
            nickname = lSeg.getNickname();
            nicknamesSeguidores.add(nickname);
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
        u.setMisSeguidores(c);
        try {
            usuJpa.edit(c);
            usuJpa.edit(u);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    /* Selecciona los Nombres de los Temas de los cuales no 
    pertenecen a ninguna ListaParticular privada disponibles
    para seleccionar en GuardarFavoritos
    
    CONSULTAR: Si dicho Tema al menos se encuentra en una 
    ListaParticular pública, se podrá seleccionar el Tema o 
    para que se pueda seleccionar todas las ListasParticulares
    deben ser Privadas ??*/
    public Map<Long, String> getTemasDisponibles() {
        
        List<Tema> listaTemas = temaJpa.findTemaEntities();
        Map<Long, String> nombresTemas = new HashMap<>();
        // Recorro todos los Temas del Sistema
        for (Tema t: listaTemas) {
            nombresTemas.put(t.getIdTema(), t.getNombreTema());
        }
        
        return nombresTemas;
    }
    
    public Map<Long, DTTemaSimple> getDTTemasDisponibles() {
        
        List<Tema> listaTemas = temaJpa.findTemaEntities();
        Map<Long, DTTemaSimple> mapDtTemas = new HashMap<>();
        // Recorro todos los Temas del Sistema
        for (Tema t: listaTemas) {
            
            mapDtTemas.put(
                    t.getIdTema(), 
                    new DTTemaSimple(
                            t.getIdTema(),
                            t.getNombreTema(),
                            t.getDuracionSegundos(),
                            t.getPosicionEnAlbum(),
                            t.getMiAlbum().getNombreAlbum(),
                            t.getMiAlbum().getMiArtista().getNombreCompletoToString()
                    )
            );
        }
        
        return mapDtTemas;
    }
    
    /* Selecciona los Nombres de las ListasParticulares que sean
    Publicas y todos los Nombres de las ListasPorDefecto disponibles
    para seleccionar en GuardarFavoritos */
    public ArrayList<String> getListasReproduccionDisponibles() {
        
        List<ListaReproduccion> listaListasReproduccion = lreprodccJpa.findListaReproduccionEntities();
        ArrayList<String> nombresListasReproduccion = new ArrayList<>();
        for (ListaReproduccion listaR: listaListasReproduccion) {
            // Si es de tipo ListaParticular
            if (listaR.getClass() == ListaParticular.class) {
                ListaParticular listaRP = (ListaParticular)listaR;
                // Si no es Privada
                if (!(listaRP.soyPrivada())) {
                    nombresListasReproduccion.add(listaRP.getNombreLista());
                }
            } else { // Si es tipo ListaPorDefecto
                nombresListasReproduccion.add(listaR.getNombreLista());
            }
        }
        
        return nombresListasReproduccion;
    }
    
    /* Selecciona los Nombres de los Albumes que esten 
    disponibles para seleccionar en GuardarFavoritos */
    public Map<Long, String> getAlbumesDisponibles() {
        
        List<Album> listaAlbumes = albJpa.findAlbumEntities();
        Map<Long, String> nombresAlbumes = new HashMap<>();
        for (Album album: listaAlbumes) {
            nombresAlbumes.put(album.getIdAlbum(), album.getNombreAlbum());
        }
        
        return nombresAlbumes;
    }
    
    public ArrayList<DTAlbum> getDTAlbumesDisponibles() {
        List<Album> listaAlbumes = albJpa.findAlbumEntities();
        ArrayList<DTAlbum> dataAlbums = new ArrayList<>();
        
        for (Album album: listaAlbumes) {
            dataAlbums.add(album.getDataAlbum());
        }
        return dataAlbums;
    }
    
    public void GuardarTemaFavorito(String nicknameCliente, long idTema) throws Exception {
        
        Cliente c = cliJpa.findCliente(nicknameCliente);
        Tema tema = temaJpa.findTema(idTema);
        
        List<Tema> temasFavoritosDelCliente = c.getMisTemasFav();
        for (Tema t : temasFavoritosDelCliente) {
            if (tema.getIdTema() == t.getIdTema()) {
                throw new Exception("El cliente ya tiene este tema en su lista de favoritos.");
            }
        }
        
        c.setMisTemasFav(tema);
        
        try {
            cliJpa.edit(c);
        } catch (Exception ex) {
            throw ex;
        }
    }
    
    public void GuardarListaFavorito(String nicknameCliente, String nombreListaR) throws Exception {
        Cliente c = cliJpa.findCliente(nicknameCliente);
        ListaReproduccion listaR = lreprodccJpa.findListaReproduccion(nombreListaR);
        
        List<ListaReproduccion> listasFavoritasDelCliente = c.getMisListasReproduccionFav();
        
        for (ListaReproduccion lr : listasFavoritasDelCliente) {
            if (lr.getNombreLista().equals(listaR.getNombreLista())) {
                throw new Exception("Este cliente ya tiene esta lista en sus favoritos.");
            }
        }
       
        c.setMisListasReproduccionFav(listaR);
        
        try {
            cliJpa.edit(c);
        } catch (Exception ex) {
            throw ex;
        }
    }
    
    public void GuardarAlbumFavorito(String nicknameCliente, Long idAlbum) throws Exception {
        Cliente c = cliJpa.findCliente(nicknameCliente);
        Album album = albJpa.findAlbum(idAlbum);
        
        List<Album> albumsFavoritosDelCliente = c.getMisAlbumesFav();
        
        for (Album a : albumsFavoritosDelCliente) {
            if (a.getIdAlbum() == album.getIdAlbum()) {
                throw new Exception("Este cliente ya tiene a este album en sus favoritos.");
            }
        }
        
        c.setMisAlbumesFav(album);
        
        try {
            cliJpa.edit(c);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public List<String> listasCreadasEstadoPrivadoTrue(String cliente) {
        List<String>retorno=new ArrayList<String>();
        Cliente c =cliJpa.findCliente(cliente);
        if(c!=null){
            List<ListaParticular> creadas =c.getMisListasReproduccionCreadas();
            for(ListaParticular lp:creadas){
                if(lp.soyPrivada()){
                    retorno.add(lp.getNombreLista());
                }
            }   
        }
        return retorno;
    }

    public void setPrivadafalse(String cliente, String lista) {
        Cliente c = cliJpa.findCliente(cliente);
        c.setPrivadafalse(lista);
        try {
            cliJpa.edit(c);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
        ListaParticular lp=this.lpartJpa.findListaParticular(lista);
        lp.setsoyPrivada(false);
        try {
            this.lpartJpa.edit(lp);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }     
    }
    
    public ArrayList<DTGenero_Simple> getListaDTGeneroSimple() {
        List<Genero> generos = genJpa.findGeneroEntities();
        ArrayList<DTGenero_Simple> dataGeneros = new ArrayList<>();

        for (Genero g : generos) {

            String padreDeG = (g.getMiPadre() == null) ? "" : g.getMiPadre().getNombreGenero();
            List<String> subgenerosDeG = (g.getMisSubgeneros() == null) ? null : g.getMisSubgenerosString();

            dataGeneros.add(new DTGenero_Simple(
                g.getNombreGenero(), 
                padreDeG,
                g.getMisSubgenerosString()
                )
            );
        }
        return dataGeneros;
    }
}


