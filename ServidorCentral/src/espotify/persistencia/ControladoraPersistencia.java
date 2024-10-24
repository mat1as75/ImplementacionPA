package espotify.persistencia;

import espotify.DataTypes.DTAlbum;
import espotify.DataTypes.DTAlbum_Simple;
import espotify.DataTypes.DTAlbum_SinDTArtista;
import espotify.DataTypes.DTArtista;
import espotify.DataTypes.DTCliente;
import espotify.DataTypes.DTGenero;
import espotify.DataTypes.DTTemaConRuta;
import espotify.DataTypes.DTTemaConURL;
import espotify.DataTypes.DTTemaGenerico;
import espotify.DataTypes.DTDatosArtista;
import espotify.DataTypes.DTDatosCliente;
import espotify.DataTypes.DTDatosListaReproduccion;
import espotify.DataTypes.DTDatosUsuario;
import espotify.DataTypes.DTGenero_Simple;
import espotify.DataTypes.DTSuscripcion;
import espotify.DataTypes.DTTemaGenericoConRutaOUrl;
import espotify.DataTypes.DTTemaSimple;
import espotify.DataTypes.DTUsuario;

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
import espotify.logica.Suscripcion;
import espotify.logica.Suscripcion.EstadoSuscripcion;
import espotify.logica.Usuario;
import espotify.persistencia.exceptions.DatabaseUpdateException;
import espotify.persistencia.exceptions.InvalidDataException;
import espotify.persistencia.exceptions.NonexistentEntityException;

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

    public UsuarioJpaController usuJpa = new UsuarioJpaController();
    public ArtistaJpaController artJpa = new ArtistaJpaController();
    public ClienteJpaController cliJpa = new ClienteJpaController();
    public GeneroJpaController genJpa = new GeneroJpaController();
    public AlbumJpaController albJpa = new AlbumJpaController();
    public ListaParticularJpaController lpartJpa = new ListaParticularJpaController();
    public ListaPorDefectoJpaController lxdefcJpa = new ListaPorDefectoJpaController();
    public ListaReproduccionJpaController lreprodccJpa = new ListaReproduccionJpaController();
    public TemaJpaController temaJpa = new TemaJpaController();
    public TemaConRutaJpaController temaconrutaJpa = new TemaConRutaJpaController();
    public TemaConURLJpaController temaurlJpa = new TemaConURLJpaController();
    public SuscripcionJpaController suscripcionJpa = new SuscripcionJpaController();

    public ControladoraPersistencia() {
    }

    ;
    
    public void AltaGenero(String nombreGenero, String nomPadre) {

        Genero padre = this.genJpa.findGenero(nomPadre); // Busco GeneroPadre
        Genero nuevoGenero = new Genero(nombreGenero, padre); // Creo NuevoGenero
        padre.getMisGenerosHijos().add(nuevoGenero);

        try {
            genJpa.edit(padre); // Agregar GeneroHijo
            //genJpa.create(nuevoGenero); // Agragar a la BD
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void AltaArtista(DTArtista dtArtista) {
        try {
            Artista art = new Artista(
                    dtArtista.getNickname(),
                    dtArtista.getNombreUsuario(),
                    dtArtista.getApellidoUsuario(),
                    dtArtista.getContrasenaUsuario(),
                    dtArtista.getEmail(),
                    dtArtista.getFecNac(),
                    dtArtista.getFotoPerfil(),
                    dtArtista.getBiografia(),
                    dtArtista.getDirSitioWeb()
            );
            this.artJpa.create(art);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void AltaCliente(DTCliente dtCliente) {
        try {
            Cliente cli = new Cliente(
                    dtCliente.getNickname(),
                    dtCliente.getNombreUsuario(),
                    dtCliente.getApellidoUsuario(),
                    dtCliente.getContrasenaUsuario(),
                    dtCliente.getEmail(),
                    dtCliente.getFecNac(),
                    dtCliente.getFotoPerfil()
            );
            this.cliJpa.create(cli);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void AltaAlbum(DTAlbum_SinDTArtista dataAlbum) throws Exception {

        if (dataAlbum.getNombreAlbum() == null
                || dataAlbum.getMiArtista() == null
                || dataAlbum.getMisTemas() == null
                || dataAlbum.getMisTemas().isEmpty()
                || dataAlbum.getMisgeneros() == null
                || dataAlbum.getMisgeneros().isEmpty()) {
            throw new InvalidDataException("Existen campos requeridos nulos o vacíos.");
        }

        //creo el album vacio
        Album nuevoAlbum = new Album();
        this.albJpa.create(nuevoAlbum);

        //busco el artista para agregarle el album
        Artista art = this.artJpa.findArtista(dataAlbum.getMiArtista());
        if (art == null) {
            this.albJpa.destroy(nuevoAlbum.getIdAlbum());
            throw new NonexistentEntityException("No se encontro el artista: " + dataAlbum.getMiArtista());
        }

        //verifico que no tenga un album con el mismo nombre
        List<Album> albumsDelArtista = art.getMisAlbumesPublicados();
        for (Album al : albumsDelArtista) {
            if (al.getNombreAlbum().equals(dataAlbum.getNombreAlbum())) {
                this.albJpa.destroy(nuevoAlbum.getIdAlbum());
                throw new InvalidDataException("Este artista ya tiene un album con el nombre: " + dataAlbum.getNombreAlbum());
            }
        }
        art.setMisAlbumesPublicados(nuevoAlbum);

        //verifico que todos los generos recibidos existan
        List<Genero> generosDeAlbum = new ArrayList();
        for (DTGenero dataG : dataAlbum.getMisgeneros()) {
            Genero gen = this.genJpa.findGenero(dataG.getNombreGenero());
            if (gen == null) {
                this.albJpa.destroy(nuevoAlbum.getIdAlbum());
                throw new NonexistentEntityException("No se encontro el genero: " + dataG.getNombreGenero());
            }
        }

        //busco los generos para agregarlos al album y agregar el album a la lista de albums que tiene genero
        for (DTGenero dataG : dataAlbum.getMisgeneros()) {
            Genero gen = this.genJpa.findGenero(dataG.getNombreGenero());
            generosDeAlbum.add(gen);
            //agrego el nuevo album en la lista de albums del genero
            gen.setMisAlbumes(nuevoAlbum);
            //guardo los cambios a cada genero modificado
            this.genJpa.edit(gen);
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

    public ArrayList<Genero> getGeneros() {
        List<Genero> generosL = genJpa.findGeneroEntities();
        ArrayList<Genero> generosAL = new ArrayList<>(generosL);

        return generosAL;
    }

    public boolean ExisteCliente(String nicknameCliente) {
        List<Cliente> clientes = this.cliJpa.findClienteEntities();
        boolean retorno = false;
        for (Cliente c : clientes) {
            String cliente = c.getNickname();
            if(cliente!=null){
                if (cliente.equals(nicknameCliente)) {
                    retorno = true;
                }
            }
        }
        return retorno;
    }

    public boolean ExisteNickName(String nickname) {
        List<Usuario> usuarios = this.usuJpa.findUsuarioEntities();
        boolean retorno = false;
        for (Usuario u : usuarios) {
            String usuario = u.getNickname();
            if(usuario!=null){
                if (usuario.equals(nickname)) {
                    retorno = true;
                }
            }
        }
        return retorno;

    }

    public boolean ExisteEmail(String email) {
        List<Usuario> usuarios = this.usuJpa.findUsuarioEntities();
        boolean retorno = false;
        for (Usuario u : usuarios) {
            String mail = u.getEmail();
            if(mail!=null){
                if (mail.equals(email)) {
                    retorno = true;
                }
            }
        }
        return retorno;

    }

    public boolean ExisteNombreLista(String nombreLista) {
        List<ListaReproduccion> listasReproduccion = this.lreprodccJpa.findListaReproduccionEntities();
        for (ListaReproduccion l : listasReproduccion) {
            String nombreList = l.getNombreLista();
            if (nombreList.equalsIgnoreCase(nombreLista)) {
                return true;
            }
        }
        return false;
    }

    /* A partir del Nickname de un Artista, se retorna 
    toda su informacion dentro de un DTDatosArtista 
    CASO DE USO: CONSULTAR PERFIL ARTISTA */
    public DTDatosArtista getDatosArtista(String nicknameArtista) {

        Artista a = artJpa.findArtista(nicknameArtista);

        // Nicknames de Seguidores del Artista
        List<Usuario> listaSeguidores = a.getMisSeguidores();
        ArrayList<String> nicknamesSeguidores = new ArrayList<>();
        for (Usuario lSeg : listaSeguidores) {
            nicknamesSeguidores.add(lSeg.getNickname());
        }
        // Cantidad de Seguidores
        int cantSeguidores = listaSeguidores.size();

        // Nombre de AlbumesPublicados del Artista
        List<Album> listaAlbumesPublicados = a.getMisAlbumesPublicados();
        ArrayList<String> nombresAlbumesPublicados = new ArrayList<>();
        for (Album nomAlbumesP : listaAlbumesPublicados) {
            nombresAlbumesPublicados.add(nomAlbumesP.getNombreAlbum());
        }

        DTDatosArtista DTDatosA = new DTDatosArtista(a.getNickname(),
                a.getNombreUsuario(), a.getApellidoUsuario(),
                a.getContrasenaUsuario(), a.getEmail(), a.getFecNac(),
                a.getFotoPerfil(), a.getBiografia(), a.getDirSitioWeb(),
                cantSeguidores, nicknamesSeguidores, nombresAlbumesPublicados);

        return DTDatosA;
    }

    public DTDatosCliente getDatosCliente(String nicknameCliente) {

        Cliente c = cliJpa.findCliente(nicknameCliente);

        // Nicknames de Seguidos del Cliente
        List<Usuario> listaSeguidos = c.getMisSeguidos();
        ArrayList<String> nicknamesSeguidos = new ArrayList<>();
        for (Usuario lSeg : listaSeguidos) {
            String nickname;

            // El seguido es Cliente ? | Sino es Artista
            if (lSeg.getClass().equals(Cliente.class)) {
                nickname = lSeg.getNickname() + ", Cliente";
            } else {
                nickname = lSeg.getNickname() + ", Artista";
            }

            nicknamesSeguidos.add(nickname);
        }

        // Nicknames de Seguidores del Cliente
        List<Usuario> listaSeguidores = c.getMisSeguidores();
        ArrayList<String> nicknamesSeguidores = new ArrayList<>();
        for (Usuario lSeg : listaSeguidores) {
            String nickname;
            nickname = lSeg.getNickname();
            nicknamesSeguidores.add(nickname);
        }

        // Nombres de ListasR Creadas del Cliente
        List<ListaParticular> listaListasRCreadas = c.getMisListasReproduccionCreadas();
        ArrayList<String> nombresListasRCreadas = new ArrayList<>();
        for (ListaParticular lPCreada : listaListasRCreadas) {
            nombresListasRCreadas.add(lPCreada.getNombreLista());
        }

        // Nombres de ListasR Favoritas del Cliente
        List<ListaReproduccion> listaListasRFavoritas = c.getMisListasReproduccionFav();
        ArrayList<String> nombresListasRFavoritas = new ArrayList<>();
        for (ListaReproduccion lRFavoritas : listaListasRFavoritas) {
            nombresListasRFavoritas.add(lRFavoritas.getNombreLista());
        }

        // Nombres de Albumes Favoritos del Cliente
        List<Album> listaAlbumesFavoritos = c.getMisAlbumesFav();
        ArrayList<String> nombresAlbumesFavoritos = new ArrayList<>();
        for (Album alb : listaAlbumesFavoritos) {
            nombresAlbumesFavoritos.add(alb.getNombreAlbum());
        }

        // Nombres de Temas Favoritos del Cliente
        List<Tema> listaTemasFavoritos = c.getMisTemasFav();
        ArrayList<String> nombresTemasFavoritos = new ArrayList<>();
        for (Tema tema : listaTemasFavoritos) {
            nombresTemasFavoritos.add(tema.getNombreTema());
        }

        Suscripcion suscripcionC = c.getMiSuscripcion();
        DTSuscripcion suscripcion = null;
        if (suscripcionC != null) {
            suscripcion = new DTSuscripcion(
                    suscripcionC.getIdSuscripcion(),
                    suscripcionC.getTipoSuscripcion().toString(),
                    suscripcionC.getEstadoSuscripcion().toString(),
                    suscripcionC.getFechaSuscripcion());
        }

        DTDatosCliente datosCliente = new DTDatosCliente(c.getNickname(),
                c.getNombreUsuario(), c.getApellidoUsuario(), c.getContrasenaUsuario(),
                c.getEmail(), c.getFecNac(), c.getFotoPerfil(), nicknamesSeguidos,
                nicknamesSeguidores, nombresListasRCreadas, null, nombresListasRFavoritas,
                nombresAlbumesFavoritos, nombresTemasFavoritos, suscripcion);

        return datosCliente;
    }

    public void setSeguidorSeguido(String Seguidor, String Seguido) {
        Cliente c = this.cliJpa.findCliente(Seguidor);
        Usuario u = this.usuJpa.findUsuario(Seguido);
        c.setMisSeguidos(u);
        u.setMisSeguidores(c);
        try {
            cliJpa.edit(c);
            usuJpa.edit(u);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ArrayList<String> getSeguidosDeCliente(String nickname) {
        Cliente c = cliJpa.findCliente(nickname);
        ArrayList<String> listaSeguidos = new ArrayList<>();

        for (Usuario u : c.getMisSeguidos()) {
            listaSeguidos.add(u.getNickname());
        }
        return listaSeguidos;
    }

    public void dejarDeSeguir(String C, String U) throws Exception {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EspotifyPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction t = em.getTransaction();

        t.begin();
        Cliente cliente = this.cliJpa.findCliente(C);
        Usuario usuario = this.usuJpa.findUsuario(U);

        ArrayList<Usuario> seguidosCliente = new ArrayList<>(cliente.getMisSeguidos());

        for (Usuario seg : seguidosCliente) {
            if (seg.getNickname().equals(usuario.getNickname())) {
                cliente.getMisSeguidos().remove(seg);
                break;
            }
        }

        ArrayList<Usuario> seguidoresUsuario = new ArrayList<>(usuario.getMisSeguidores());

        for (Usuario seg : seguidoresUsuario) {
            if (seg.getNickname().equals(cliente.getNickname())) {
                usuario.getMisSeguidores().remove(seg);
                break;
            }
        }
        t.commit();
        em.close();
        try {
            this.cliJpa.edit(cliente);
            this.usuJpa.edit(usuario);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public void DejarDeSeguirPrueba() throws Exception {
        Cliente cliente = cliJpa.findCliente("Eleven11");
        Usuario usuario = usuJpa.findUsuario("lachiqui");

        try {
            //usuario.getMisSeguidores().remove(cliente);
            cliente.getMisSeguidos().remove(usuario);
            //usuJpa.edit(usuario);
            cliJpa.edit(cliente);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public boolean clienteSigueAUsuario(String C, String U) {

        Cliente c = this.cliJpa.findCliente(C);

        // Obtener lista de seguidos del cliente
        List<Usuario> listaSeguidos = c.getMisSeguidos();

        boolean Sigue = false;
        // Recorrer la lista y comprobar si lo sigue
        for (Usuario lSeg : listaSeguidos) {
            if (lSeg.getNickname().equals(U)) {
                Sigue = true; // Si lo sigue retorna true
            }
        }
        return Sigue;  // Caso contrario retorna false
    }

    public void CrearListaPorDefecto(String nombreLista, String fotoLista, String nombreGenero) {

        // Buscar genero por su nombre
        Genero gen = this.genJpa.findGenero(nombreGenero);

        // Crear la nueva lista por defecto
        ListaPorDefecto nuevaLista = new ListaPorDefecto(nombreLista, fotoLista, gen);
        try {
            lxdefcJpa.create(nuevaLista);
            gen.getMisListasReproduccion().add(nuevaLista);
            genJpa.edit(gen);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, "Error al crear lista por defecto", ex);
            throw new RuntimeException("Error al crear la lista por defecto: " + ex.getMessage());
        }
    }

    public void CrearListaParticular(String nombreLista, String fotoLista, String nicknameCliente, boolean esPrivada) {

        // Buscar cliente por su nickname
        Cliente cli = this.cliJpa.findCliente(nicknameCliente);

        // Crear la nueva lista particular
        ListaParticular lista = new ListaParticular(nombreLista, fotoLista, cli, esPrivada);
        try {
            lpartJpa.create(lista);
            cli.getMisListasReproduccionCreadas().add(lista);
            cliJpa.edit(cli);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, "Error al crear lista particular", ex);
            throw new RuntimeException("Error al crear la lista particular: " + ex.getMessage());
        }
    }

    public List<String> getNombresListasPorTipo(String tipoDeLista, String nickOgen) {

        List<String> nombresListas = new ArrayList<>();
        if (tipoDeLista == null || nickOgen == null) {
            // Manejar el caso en que el tipoDeLista o nickOgen sean nulos
            throw new IllegalArgumentException("Tipo de lista o nombre de género/cliente nulos");
        }

        if (tipoDeLista.equalsIgnoreCase("Por Defecto")) {
            List<ListaPorDefecto> listasPorDefecto = lxdefcJpa.findListaPorDefectoEntities();
            if (listasPorDefecto != null) {
                for (ListaPorDefecto lista : listasPorDefecto) {
                    if (lista != null && lista.getGenero() != null && lista.getGenero().getNombreGenero() != null) {
                        if (lista.getGenero().getNombreGenero().equals(nickOgen)) {
                            nombresListas.add(lista.getNombreLista());
                        }
                    }
                }
            }
        } else if (tipoDeLista.equalsIgnoreCase("Particular")) {
            List<ListaParticular> listasParticulares = lpartJpa.findListaParticularEntities();
            if (listasParticulares != null) {
                for (ListaParticular lista : listasParticulares) {
                    if (lista != null && lista.getCliente() != null && lista.getCliente().getNickname() != null) {
                        if (lista.getCliente().getNickname().equals(nickOgen)) {
                            nombresListas.add(lista.getNombreLista());
                        }
                    }
                }
            }
        }
        return nombresListas;
    }

    public DTDatosListaReproduccion getDatosListaReproduccion(String tipoDeLista, String nombreLista) {

        DTDatosListaReproduccion datosLista = null;

        if (tipoDeLista.equalsIgnoreCase("Por Defecto")) {
            ListaPorDefecto listaPorDefecto = lxdefcJpa.findListaPorDefecto(nombreLista);

            if (listaPorDefecto != null) {
                // Obtener datos de la lista
                String nombreListaReproduccion = listaPorDefecto.getNombreLista();
                String fotoLista = listaPorDefecto.getFotoLista();
                String nombreGenero = listaPorDefecto.getGenero().getNombreGenero();

                // Convertir los temas a DTTemaSimple
                List<DTTemaSimple> temas = new ArrayList<>();
                for (Tema tema : listaPorDefecto.getMisTemas()) {
                    temas.add(tema.getDTTemaSimple());
                }
                // Crear el DTO para la lista por defecto
                datosLista = new DTDatosListaReproduccion(
                        nombreListaReproduccion,
                        fotoLista,
                        tipoDeLista,
                        temas,
                        nombreGenero
                );
            }
        } else if (tipoDeLista.equalsIgnoreCase("Particular")) {
            ListaParticular listaParticular = lpartJpa.findListaParticular(nombreLista);

            if (listaParticular != null) {
                // Obtener datos de la lista
                String nombreListaReproduccion = listaParticular.getNombreLista();
                String fotoLista = listaParticular.getFotoLista();
                String nicknameCliente = listaParticular.getCliente().getNickname();
                Boolean privacidad = listaParticular.soyPrivada();

                // Convertir los temas a DTTemaSimple
                List<DTTemaSimple> temas = new ArrayList<>();
                for (Tema tema : listaParticular.getMisTemas()) {

                    temas.add(tema.getDTTemaSimple());
                }

                // Crear el DTO para la lista particular
                datosLista = new DTDatosListaReproduccion(
                        nombreListaReproduccion,
                        fotoLista,
                        tipoDeLista,
                        temas,
                        nicknameCliente,
                        privacidad
                );
            }
        }
        return datosLista;
    }

    public List<DTGenero> getGenerosjTree() {

        List<Genero> generosL = genJpa.findGeneroEntities();
        ArrayList<Genero> generosAL = new ArrayList<>(generosL);

        // Crear una lista para DTGenero
        ArrayList<DTGenero> dtGeneros = new ArrayList<>();
        Map<String, DTGenero> generosMap = new HashMap<>();

        // Primera pasada: crear los DTGenero y añadirlos al mapa
        for (Genero g : generosAL) {
            DTGenero dtGenero = new DTGenero(g.getNombreGenero());
            generosMap.put(g.getNombreGenero(), dtGenero);
            dtGeneros.add(dtGenero);
        }

        // Segunda pasada: establecer relaciones padre-hijo
        for (Genero g : generosAL) {
            DTGenero dtGenero = generosMap.get(g.getNombreGenero());

            // Establecer el genero padre si existe y no es nulo
            if (g.getMiPadre() != null && g.getMiPadre().getNombreGenero() != null) {
                DTGenero dtPadre = generosMap.get(g.getMiPadre().getNombreGenero());
                if (dtPadre != null) {
                    dtGenero.setMiPadre(dtPadre);
                }
            }

            // Establecer los hijos del genero si tiene
            if (g.getMisSubgeneros() != null) {
                for (Genero hijo : g.getMisSubgeneros()) {
                    if (hijo != null && hijo.getNombreGenero() != null) {
                        DTGenero dtHijo = generosMap.get(hijo.getNombreGenero());
                        if (dtHijo != null) {
                            dtGenero.setMisGenerosHijos(dtHijo);
                        }
                    }
                }
            }
        }

        return dtGeneros;
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
        for (Tema t : listaTemas) {
            nombresTemas.put(t.getIdTema(), t.getNombreTema());
        }

        return nombresTemas;
    }

    public Map<Long, DTTemaSimple> getDTTemasDisponibles() {

        List<Tema> listaTemas = temaJpa.findTemaEntities();
        Map<Long, DTTemaSimple> mapDtTemas = new HashMap<>();
        // Recorro todos los Temas del Sistema
        for (Tema t : listaTemas) {

            mapDtTemas.put(
                    t.getIdTema(),
                    t.getDTTemaSimple()
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
        for (ListaReproduccion listaR : listaListasReproduccion) {
            // Si es de tipo ListaParticular
            if (listaR.getClass() == ListaParticular.class) {
                ListaParticular listaRP = (ListaParticular) listaR;
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

    public ArrayList<String> getNombresListasParticulares() {
        List<ListaParticular> listaListasParticulares = this.lpartJpa.findListaParticularEntities();
        ArrayList<String> nombresListasParticulares = new ArrayList<>();

        for (ListaParticular lp : listaListasParticulares) {
            nombresListasParticulares.add(lp.getNombreLista());
        }

        return nombresListasParticulares;
    }

    public ArrayList<String> getNombresListasPorDefecto() {
        List<ListaPorDefecto> listaListasPorDefecto = this.lxdefcJpa.findListaPorDefectoEntities();
        ArrayList<String> nombresListasPorDefecto = new ArrayList<>();

        for (ListaPorDefecto lpd : listaListasPorDefecto) {
            nombresListasPorDefecto.add(lpd.getNombreLista());
        }

        return nombresListasPorDefecto;
    }

    public ArrayList<String> getNombresListasParticularesPublicas() {
        List<ListaParticular> listaListasParticulares = this.lpartJpa.findListaParticularEntities();
        ArrayList<String> nombresListasParticularesPublicas = new ArrayList<>();

        for (ListaParticular lp : listaListasParticulares) {
            if (!lp.soyPrivada()) {
                nombresListasParticularesPublicas.add(lp.getNombreLista());
            }
        }

        return nombresListasParticularesPublicas;
    }

    /* Selecciona los Nombres de los Albumes que esten 
    disponibles para seleccionar en GuardarFavoritos */
    public Map<Long, String> getAlbumesDisponibles() {

        List<Album> listaAlbumes = albJpa.findAlbumEntities();
        Map<Long, String> nombresAlbumes = new HashMap<>();
        for (Album album : listaAlbumes) {
            nombresAlbumes.put(album.getIdAlbum(), album.getNombreAlbum());
        }

        return nombresAlbumes;
    }

    public ArrayList<DTAlbum> getDTAlbumesDisponibles() {
        List<Album> listaAlbumes = albJpa.findAlbumEntities();
        ArrayList<DTAlbum> dataAlbums = new ArrayList<>();

        for (Album album : listaAlbumes) {
            dataAlbums.add(album.getDataAlbum());
        }
        return dataAlbums;
    }

    public ArrayList<DTAlbum_Simple> getDTAlbumesSimple() {
        List<Album> listaAlbumes = albJpa.findAlbumEntities();
        ArrayList<DTAlbum_Simple> dataAlbums = new ArrayList<>();

        for (Album album : listaAlbumes) {
            dataAlbums.add(album.getDTAlbumSimple());
        }
        return dataAlbums;
    }

    public ArrayList<DTAlbum_Simple> getDTAlbumesSimplePorGenero(String genero) {
        ArrayList<DTAlbum_Simple> dataAlbums = new ArrayList<>();
        List<Album> listaAlbumes = albJpa.findAlbumEntities();

        for (Album album : listaAlbumes) {

            if (album.getMisGenerosString().contains(genero)) {
                dataAlbums.add(album.getDTAlbumSimple());
            }
        }
        return dataAlbums;
    }

    public void GuardarTemaFavorito(String nicknameCliente, long idTema) throws Exception {

        Cliente c = cliJpa.findCliente(nicknameCliente);
        Tema tema = temaJpa.findTema(idTema);

        List<Tema> temasFavoritosDelCliente = c.getMisTemasFav();
        for (Tema t : temasFavoritosDelCliente) {
            if (tema.getIdTema().equals(t.getIdTema())) {
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
            if (a.getIdAlbum().equals(album.getIdAlbum())) {
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

    public ArrayList<String> listasCreadasEstadoPrivadoTrue(String cliente) {
        ArrayList<String> nombresListasPrivadas = new ArrayList<>();
        Cliente c = cliJpa.findCliente(cliente);

        if (c != null) {
            List<ListaParticular> listasCreadas = c.getMisListasReproduccionCreadas();
            // Recorro las ListasParticulares
            for (ListaParticular lp : listasCreadas) {
                // Si es Privada
                if (lp.soyPrivada()) {
                    nombresListasPrivadas.add(lp.getNombreLista());
                }
            }
        }
        return nombresListasPrivadas;
    }

    public void setPrivadafalse(String cliente, String lista) {
        Cliente c = cliJpa.findCliente(cliente);
        c.setPrivadafalse(lista);
        try {
            cliJpa.edit(c);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
        ListaParticular lp = this.lpartJpa.findListaParticular(lista);
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

    public Map<Long, DTTemaSimple> getDTTemasDeAlbum(Long idAlbum) {
        Album alb = this.albJpa.findAlbum(idAlbum);
        List<Tema> temas = alb.getMisTemas();

        Map<Long, DTTemaSimple> mapDataTemas = new HashMap(temas.size());
        for (Tema t : temas) {
            mapDataTemas.put(t.getIdTema(), t.getDTTemaSimple());
        }

        return mapDataTemas;
    }

    public Map<Long, DTTemaSimple> getDTTemasDeListaParticular(String nombreListaReproduccion) {
        ListaParticular listaP = this.lpartJpa.findListaParticular(nombreListaReproduccion);
        List<Tema> temas = listaP.getMisTemas();

        Map<Long, DTTemaSimple> mapDataTemas = new HashMap(temas.size());
        for (Tema t : temas) {
            mapDataTemas.put(t.getIdTema(), t.getDTTemaSimple());
        }

        return mapDataTemas;
    }

    public Map<Long, DTTemaSimple> getDTTemasDeListaPorDefecto(String nombreListaReproduccion) {
        ListaPorDefecto listaPDef = this.lxdefcJpa.findListaPorDefecto(nombreListaReproduccion);
        List<Tema> temas = listaPDef.getMisTemas();

        Map<Long, DTTemaSimple> mapDataTemas = new HashMap(temas.size());
        for (Tema t : temas) {
            mapDataTemas.put(t.getIdTema(), t.getDTTemaSimple());
        }

        return mapDataTemas;
    }

    public DTTemaGenerico getTemaPorLista(String nombreLista, String tipoDeLista, String nombreTema) {
        if (tipoDeLista.equals("Género")) {
            ListaPorDefecto lista = this.lxdefcJpa.findListaPorDefecto(nombreLista);
            for (Tema t : lista.getMisTemas()) {
                if (t.getNombreTema().equals(nombreTema)) {
                    // Comprobar si es TemaConURL o TemaConRuta y devolver el DTO 
                    if (t instanceof TemaConURL temaConURL) {
                        return new DTTemaConURL(
                                temaConURL.getNombreTema(),
                                temaConURL.getDuracionSegundos(),
                                temaConURL.getPosicionEnAlbum(),
                                temaConURL.getUrlTema()
                        );
                    } else if (t instanceof TemaConRuta temaConRuta) {
                        return new DTTemaConRuta(
                                temaConRuta.getRutaTema(),
                                temaConRuta.getNombreTema(),
                                temaConRuta.getDuracionSegundos(),
                                temaConRuta.getPosicionEnAlbum()
                        );
                    }
                }
            }
        } else if (tipoDeLista.equals("Cliente")) {
            ListaParticular lista = this.lpartJpa.findListaParticular(nombreLista);
            for (Tema t : lista.getMisTemas()) {
                if (t.getNombreTema().equals(nombreTema)) {
                    if (t instanceof TemaConURL) {
                        TemaConURL temaConURL = (TemaConURL) t;
                        return new DTTemaConURL(
                                temaConURL.getNombreTema(),
                                temaConURL.getDuracionSegundos(),
                                temaConURL.getPosicionEnAlbum(),
                                temaConURL.getUrlTema()
                        );
                    } else if (t instanceof TemaConRuta) {
                        TemaConRuta temaConRuta = (TemaConRuta) t;
                        return new DTTemaConRuta(
                                temaConRuta.getRutaTema(),
                                temaConRuta.getNombreTema(),
                                temaConRuta.getDuracionSegundos(),
                                temaConRuta.getPosicionEnAlbum()
                        );
                    }
                }
            }
        }
        throw new RuntimeException("Tema no encontrado: " + nombreTema);
    }

    public void agregarTemaALista(Long idTema, String nombreLista) throws Exception {
        Tema tema = this.temaJpa.findTema(idTema);
        if (tema == null) throw new NonexistentEntityException("No existe el tema con id " + idTema);
        
        ListaReproduccion listaRep = this.lreprodccJpa.findListaReproduccion(nombreLista);
        if (listaRep == null) throw new NonexistentEntityException("No existe la lista con nombre " + nombreLista);
        
        //obtengo los temas de la lista de reproduccion destino
        List<Tema> temasDeListaRep = listaRep.getMisTemas();

        for (Tema t : temasDeListaRep) {
            if (t.getIdTema() == tema.getIdTema()) {
                //error si el tema ya pertenece a la lista
                throw new InvalidDataException("El tema elegido ["
                        + tema.getIdTema()
                        + "] ya pertenece a la lista "
                        + listaRep.getNombreLista()
                );
            }
        }
        //si no existe el tema en la lista destino, lo agrego
        listaRep.agregarTema(tema);

        //obtengo las listas a las que se asocia el tema
        List<ListaReproduccion> listasAsociadasAlTema = tema.getMisReproducciones();
        //si el tema no esta asociado a esta lista, entonces agrego la misma a sus listas asociadas

        Boolean temaYaEstaAsociadoALista = false;
        for (ListaReproduccion lr : listasAsociadasAlTema) {
            if (lr.getNombreLista().equals(listaRep.getNombreLista())) {
                temaYaEstaAsociadoALista = true;
                break;
            }
        }

        if (!temaYaEstaAsociadoALista) {
            tema.setMisReproducciones(listaRep);
            try {
                this.lreprodccJpa.edit(listaRep);
            } catch (Exception ex) {
                throw new DatabaseUpdateException("Ocurrio un error al agregar la lista "
                        + listaRep.getNombreLista()
                        + " a las listas asociadas al tema ["
                        + tema.getIdTema() + "]."
                );
            }
        }

        try {
            this.temaJpa.edit(tema);
        } catch (Exception ex) {
            throw new DatabaseUpdateException(
                    "Ocurrio un error al agregar el tema ["
                    + tema.getIdTema() + "] a la lista "
                    + listaRep.getNombreLista());
        }
    }

    public ArrayList<String> getNombresListasParticularesDeCliente(String nicknameCliente) throws Exception {
        Cliente cl = this.cliJpa.findCliente(nicknameCliente);

        if (cl == null) {
            throw new Exception("No se encontró el cliente " + nicknameCliente + ".");
        }

        List<ListaParticular> listasPart = cl.getMisListasReproduccionCreadas();
        ArrayList<String> nombresListasPart = new ArrayList();

        for (ListaParticular lp : listasPart) {
            nombresListasPart.add(lp.getNombreLista());
        }

        return nombresListasPart;
    }

    public ArrayList<String> getNicknamesClientesListasPrivadas() {

        List<Cliente> clientes = cliJpa.findClienteEntities();
        ArrayList<String> nicknamesClientesLPrivadas = new ArrayList<>();

        if (clientes != null) {
            //Recorro Clientes
            for (Cliente c : clientes) {
                // Recorro ListasParticularCreadas por dicho Cliente
                for (ListaParticular lpc : c.getMisListasReproduccionCreadas()) {

                    // Si la ListaP es Privada
                    if (lpc.soyPrivada()) {
                        nicknamesClientesLPrivadas.add(c.getNickname());
                        break;
                    }
                }
            }
        }

        /* Retorno nicknames de Clientes 
        que contienen ListasPrivadas */
        return nicknamesClientesLPrivadas;
    }

    public void EliminarTemaFavorito(String nicknameCliente, long idTema) throws Exception {
        Cliente c = cliJpa.findCliente(nicknameCliente);
        List<Tema> temasFavoritosDelCliente = c.getMisTemasFav();
        for (Tema t : temasFavoritosDelCliente) {
            if (t.getIdTema().equals(idTema)) {
                temasFavoritosDelCliente.remove(t);
                break;
                //c.setMisTemasFavLista(temasFavoritosDelCliente);
            }
        }

        try {
            cliJpa.edit(c);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public void EliminarListaFavorito(String nicknameCliente, String nombreListaR) throws Exception {
        Cliente c = cliJpa.findCliente(nicknameCliente);
        List<ListaReproduccion> listasFavoritasDelCliente = c.getMisListasReproduccionFav();

        for (ListaReproduccion lr : listasFavoritasDelCliente) {
            if (lr.getNombreLista().equals(nombreListaR)) {
                listasFavoritasDelCliente.remove(lr);
                break;
            }
        }
        try {
            cliJpa.edit(c);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public void EliminarAlbumFavorito(String nicknameCliente, long idAlbum) throws Exception {
        Cliente c = cliJpa.findCliente(nicknameCliente);
        List<Album> albumsFavoritosDelCliente = c.getMisAlbumesFav();

        for (Album a : albumsFavoritosDelCliente) {
            if (a.getIdAlbum().equals(idAlbum)) {
                albumsFavoritosDelCliente.remove(a);
                break;
            }
        }
        try {
            cliJpa.edit(c);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public void quitarTemaDeLista(Long idTema, String nombreLista) throws Exception {

        Tema tema = this.temaJpa.findTema(idTema);
        ListaReproduccion listaRep = this.lreprodccJpa.findListaReproduccion(nombreLista);

        if (tema == null || listaRep == null) {
            throw new Exception("No se pudo encontrar el tema [" + idTema + "] o la lista [" + nombreLista + "].");
        }

        List<Tema> temasDeListaRep = listaRep.getMisTemas();
        //remuevo el tema de la lista
        for (Tema t : temasDeListaRep) {
            if (t.getIdTema().equals(idTema)) {
                temasDeListaRep.remove(t);
                break;
            }
        }
        //remuevo la lista de reproduccion de las listas asociadas al tema
        List<ListaReproduccion> listasRepDeTema = tema.getMisReproducciones();
        for (ListaReproduccion lrep : listasRepDeTema) {
            if (lrep.getNombreLista().equals(listaRep.getNombreLista())) {
                listasRepDeTema.remove(lrep);
            }
            break;
        }

        try {
            this.temaJpa.edit(tema);
            this.lreprodccJpa.edit(listaRep);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public boolean existeRelacion(String Seguidor, String Seguido) {
        Cliente c = cliJpa.findCliente(Seguidor);
        boolean retorno = false;
        // Nicknames de Seguidos del Cliente
        List<Usuario> listaSeguidos = c.getMisSeguidos();
        for (Usuario lSeg : listaSeguidos) {
            if (lSeg.getNickname().equals(Seguido)) {
                //if (lSeg.getClass().equals(Cliente.class)){
                retorno = true;
            }
        }
        return retorno;
    }

    public DTAlbum ConsultaAlbum(Long idAlbum) {
        DTAlbum dta = null;
        Album a = this.albJpa.findAlbum(idAlbum);
        if (a != null) {
            dta = a.getDataAlbum();
        }
        return dta;
    }

    public Map<Long, String> getTemasFavCliente(String nicknameCliente) {
        Cliente c = this.cliJpa.findCliente(nicknameCliente);
        Map<Long, String> temasFavCliente;
        temasFavCliente = c.getTemasFavMap();
        return temasFavCliente;
    }

    public ArrayList<String> getListasFavCliente(String nicknameCliente) {
        Cliente c = this.cliJpa.findCliente(nicknameCliente);
        ArrayList<String> listasFavCliente = new ArrayList<>();
        listasFavCliente = c.getListasFavString();
        return listasFavCliente;
    }

    public Map<Long, String> getAlbumsFavCliente(String nicknameCliente) {
        Cliente c = this.cliJpa.findCliente(nicknameCliente);
        Map<Long, String> albumsFavCliente;
        albumsFavCliente = c.getAlbumsFavMap();
        return albumsFavCliente;
    }

    public ArrayList<String> getNombresGenerosPadre() {
        ArrayList<String> nombresGenerosPadre = new ArrayList<>();
        List<Genero> generos = genJpa.findGeneroEntities();

        for (Genero g : generos) {

            if (!g.getNombreGenero().equalsIgnoreCase("genero")) {
                // Si no es un GeneroPadre vacío (Genero->miPadre == Empty)
                if (!g.getNombreGenero().isEmpty()) {
                    // Si es GeneroPadre lo agrego
                    if (g.getMiPadre().getNombreGenero().toLowerCase().equals("genero")) {
                        nombresGenerosPadre.add(g.getNombreGenero());
                    }
                }
            }

        }

        return nombresGenerosPadre;
    }

    public ArrayList<String> getNombresGenerosHijos() {
        ArrayList<String> nombresGenerosHijos = new ArrayList<>();
        List<Genero> generos = genJpa.findGeneroEntities();

        for (Genero g : generos) {
            if (!g.getNombreGenero().equalsIgnoreCase("genero")) {
                // Si Genero no tiene GenerosHijos, y además no tiene a GeneroPadre = "Genero"
                if (g.getMisGenerosHijos().isEmpty() && !g.getMiPadre().getNombreGenero().toLowerCase().equals("genero")) {
                    nombresGenerosHijos.add(g.getNombreGenero());
                }
            }

        }

        return nombresGenerosHijos;
    }

    public Map<Long, String> getMapAlbumesGenero(String genero) {
        Map<Long, String> Albums = new HashMap<>();
        List<Album> albumes = this.albJpa.findAlbumEntities();

        for (Album a : albumes) {
            for (Genero g : a.getMisGeneros()) {
                if (g.getNombreGenero().equals(genero)) {
                    Albums.put(a.getIdAlbum(), a.getNombreAlbum());
                }
            }

        }

        return Albums;
    }

    public Map<Long, String> getMapAlbumesArtista(String artista) {
        Map<Long, String> Albums = new HashMap<>();
        List<Album> albumes = this.albJpa.findAlbumEntities();

        for (Album a : albumes) {

            if (a.getMiArtista().getNickname().equals(artista)) {
                Albums.put(a.getIdAlbum(), a.getNombreAlbum());
            }

        }

        return Albums;
    }

    public boolean NoHayGeneros() {
        return this.genJpa.findGeneroEntities().isEmpty();
    }

    public void SetGenero() throws Exception {
        Genero gen = new Genero("Genero", null);
        this.genJpa.create(gen);
    }

    public String getGeneroDeListaPorDefecto(String nombreListaRep) throws Exception {
        ListaPorDefecto listaRep = this.lxdefcJpa.findListaPorDefecto(nombreListaRep);

        if (listaRep == null) {
            throw new Exception("No se encontró la lista de reproducción nombreListaRep");
        }

        if (listaRep.getGenero() == null) {
            throw new Exception("La lista " + nombreListaRep + "no tiene un género asociado.");
        }

        return listaRep.getGenero().getNombreGenero();
    }

    public List<String> getUsuariosSinEste(String nickname) {
        List<Usuario> listaU = this.usuJpa.findUsuarioEntities();
        List<String> retorno = new ArrayList<String>();
        for (Usuario u : listaU) {
            if (!u.getNickname().equals(nickname)) {
                retorno.add(u.getNickname());
            }
        }
        return retorno;
    }

    public DTUsuario getUsuarioAutentificado(String identificadorUsuario, String contrasenaUsuario) {
        Usuario user = this.usuJpa.findUsuarioByIdentifier(identificadorUsuario);

        /* Si no encuentra al usuario, retorna null */
        if (user == null) {
            return null;
        }

        DTUsuario dtUser;
        /* Crea el DTUsuario segun el tipo de usuario */
        if (user instanceof Cliente) {
            dtUser = new DTCliente(
                    user.getNickname(),
                    user.getContrasenaUsuario()
            );
        } else {
            dtUser = new DTArtista(
                    user.getNickname(),
                    user.getContrasenaUsuario()
            );
        }

        /* Verifica la contrasena */
        if (!dtUser.getContrasenaUsuario().equals(contrasenaUsuario)) {
            return null;
        }

        return dtUser;
        /* Retorna al usuario autentificado */
    }

    public Long buscarAlbumPorNombreYArtista(String nombreArt, String nombreAlb) {
        Long idAlbum = null;
        Artista art = this.artJpa.findArtista(nombreArt);

        if (art != null) {
            List<Album> albumsDeArtista = art.getMisAlbumesPublicados();
            for (Album a : albumsDeArtista) {
                if (a.getNombreAlbum().toLowerCase().equals(nombreAlb.toLowerCase())) {
                    idAlbum = a.getIdAlbum();
                }
            }
        }

        return idAlbum;
    }

    public DTDatosUsuario getDatosUsuario(String identificadorUsuario) {
        /* Busco al Usuario por su identificador (Nickname || Email) */
        Usuario u = usuJpa.findUsuarioByIdentifier(identificadorUsuario);
        DTDatosUsuario datosUsuario = null;

        /* Es Cliente */
        if (u.getClass().equals(Cliente.class)) {

            // Nicknames de Seguidos del Cliente
            List<Usuario> listaSeguidos = ((Cliente) u).getMisSeguidos();
            ArrayList<String> nicknamesSeguidos = new ArrayList<>();
            for (Usuario lSeg : listaSeguidos) {
                String nickname;

                // El seguido es Cliente ? | Sino es Artista
                if (lSeg.getClass().equals(Cliente.class)) {
                    nickname = lSeg.getNickname() + ", Cliente";
                } else {
                    nickname = lSeg.getNickname() + ", Artista";
                }

                nicknamesSeguidos.add(nickname);
            }

            // Nicknames de Seguidores del Cliente
            List<Usuario> listaSeguidores = ((Cliente) u).getMisSeguidores();
            ArrayList<String> nicknamesSeguidores = new ArrayList<>();
            for (Usuario lSeg : listaSeguidores) {
                String nickname;
                nickname = lSeg.getNickname();
                nicknamesSeguidores.add(nickname);
            }

            // Nombres de ListasR Creadas del Cliente
            List<ListaParticular> listaListasRCreadas = ((Cliente) u).getMisListasReproduccionCreadas();
            ArrayList<String> nombresListasRCreadas = new ArrayList<>();
            for (ListaParticular lPCreada : listaListasRCreadas) {
                nombresListasRCreadas.add(lPCreada.getNombreLista());
            }

            // Nombres de ListasR Creadas Publicas del Cliente
            ArrayList<String> nombresListasRCreadasPublicas = new ArrayList<>();
            for (ListaParticular lPCreada : listaListasRCreadas) {
                if (!lPCreada.soyPrivada()) {
                    nombresListasRCreadasPublicas.add(lPCreada.getNombreLista());
                }
            }

            // Nombres de ListasR Favoritas del Cliente
            List<ListaReproduccion> listaListasRFavoritas = ((Cliente) u).getMisListasReproduccionFav();
            ArrayList<String> nombresListasRFavoritas = new ArrayList<>();
            for (ListaReproduccion lRFavoritas : listaListasRFavoritas) {
                nombresListasRFavoritas.add(lRFavoritas.getNombreLista());
            }

            // Nombres de Albumes Favoritos del Cliente
            List<Album> listaAlbumesFavoritos = ((Cliente) u).getMisAlbumesFav();
            ArrayList<String> nombresAlbumesFavoritos = new ArrayList<>();
            for (Album alb : listaAlbumesFavoritos) {
                nombresAlbumesFavoritos.add(alb.getNombreAlbum());
            }

            // Nombres de Temas Favoritos del Cliente
            List<Tema> listaTemasFavoritos = ((Cliente) u).getMisTemasFav();
            ArrayList<String> nombresTemasFavoritos = new ArrayList<>();
            for (Tema tema : listaTemasFavoritos) {
                nombresTemasFavoritos.add(tema.getNombreTema());
            }

            Suscripcion suscripcionC = ((Cliente) u).getMiSuscripcion();
            DTSuscripcion dtSuscripcion = null;
            if (suscripcionC != null) {
                dtSuscripcion = new DTSuscripcion(
                        suscripcionC.getIdSuscripcion(),
                        suscripcionC.getTipoSuscripcion().toString(),
                        suscripcionC.getEstadoSuscripcion().toString(),
                        suscripcionC.getFechaSuscripcion());
            }

            datosUsuario = new DTDatosCliente(((Cliente) u).getNickname(),
                    ((Cliente) u).getNombreUsuario(), ((Cliente) u).getApellidoUsuario(),
                    ((Cliente) u).getContrasenaUsuario(), ((Cliente) u).getEmail(),
                    ((Cliente) u).getFecNac(), ((Cliente) u).getFotoPerfil(), nicknamesSeguidos,
                    nicknamesSeguidores, nombresListasRCreadas, nombresListasRCreadasPublicas, nombresListasRFavoritas,
                    nombresAlbumesFavoritos, nombresTemasFavoritos, dtSuscripcion);

        } else {
            /* Es Artista */

            // Nicknames de Seguidores del Artista
            List<Usuario> listaSeguidores = ((Artista) u).getMisSeguidores();
            ArrayList<String> nicknamesSeguidores = new ArrayList<>();
            for (Usuario lSeg : listaSeguidores) {
                nicknamesSeguidores.add(lSeg.getNickname());
            }
            // Cantidad de Seguidores
            int cantSeguidores = listaSeguidores.size();

            // Nombre de AlbumesPublicados del Artista
            List<Album> listaAlbumesPublicados = ((Artista) u).getMisAlbumesPublicados();
            ArrayList<String> nombresAlbumesPublicados = new ArrayList<>();
            for (Album nomAlbumesP : listaAlbumesPublicados) {
                nombresAlbumesPublicados.add(nomAlbumesP.getNombreAlbum());
            }

            datosUsuario = new DTDatosArtista(((Artista) u).getNickname(),
                    ((Artista) u).getNombreUsuario(), ((Artista) u).getApellidoUsuario(),
                    ((Artista) u).getContrasenaUsuario(), ((Artista) u).getEmail(),
                    ((Artista) u).getFecNac(), ((Artista) u).getFotoPerfil(),
                    ((Artista) u).getBiografia(), ((Artista) u).getDirSitioWeb(),
                    cantSeguidores, nicknamesSeguidores, nombresAlbumesPublicados);

        }

        return datosUsuario;
    }

    public ArrayList<DTSuscripcion> getDTSuscripciones() {

        List<Suscripcion> listaSuscripciones = suscripcionJpa.findSuscripcionEntities();
        ArrayList<DTSuscripcion> dataSuscripciones = new ArrayList<>();
        // Recorro todos los Temas del Sistema
        for (Suscripcion s : listaSuscripciones) {
            dataSuscripciones.add(s.getDataSuscripcion());
        }

        return dataSuscripciones;
    }

    public DTSuscripcion getDTSuscripcion(Long id) {
        Suscripcion s = suscripcionJpa.findSuscripcion(id);

        return new DTSuscripcion(s.getIdSuscripcion(),
                s.getTipoSuscripcion().toString(),
                s.getEstadoSuscripcion().toString(),
                s.getFechaSuscripcion(),
                s.getMiCliente().getDTCliente());
    }

    public void ActualizarEstadoSuscripcion(Long idSuscripcion, EstadoSuscripcion estadoSuscripcion, Date fechaSuscripcion) throws Exception {
        try {
            Suscripcion s = suscripcionJpa.findSuscripcion(idSuscripcion);
            s.setEstadoSuscripcion(estadoSuscripcion);
            s.setFechaSuscripcion(fechaSuscripcion);
            suscripcionJpa.edit(s);
        } catch (Exception ex) {
            throw ex;
        }
    }
    
    public boolean existeArtista(String nicknameArtista) {
        Artista art = this.artJpa.findArtista(nicknameArtista);
        return art != null;
    }
     
    public DTTemaGenericoConRutaOUrl getDTTemaGenericoConRutaOUrl(Long idTema) {
        TemaConRuta temaConRuta = this.temaconrutaJpa.findTemaConRuta(idTema);
        TemaConURL temaConURL = this.temaurlJpa.findTemaConURL(idTema);
        
        if (temaConRuta != null) {
            return temaConRuta.getDTTemaConRutaOUrl();
        } else if (temaConURL != null) {
            return temaConURL.getDTTemaConRutaOUrl();
        } else {
            return null;
        }
    }
    
    public List<DTDatosListaReproduccion> getListaDTDatosListaReproduccionDeCliente(String nicknameCliente) throws NonexistentEntityException {
        Cliente cliente = this.cliJpa.findCliente(nicknameCliente);
        if (cliente == null) {
            throw new NonexistentEntityException("No se encontró el cliente");
        }

        List<ListaParticular> particularesDeCliente = cliente.getMisListasReproduccionCreadas();
        if (particularesDeCliente == null || particularesDeCliente.isEmpty()) {
            return null;
        }
        
        List<DTDatosListaReproduccion> listaDeDtListas = new ArrayList();
        for (ListaParticular lp : particularesDeCliente) {
            listaDeDtListas.add(lp.getDTDatosListaReproduccion());
        }
        return listaDeDtListas;
    }
    
    public DTSuscripcion getDTSuscripcionDeCliente(String nickname) throws Exception {
        Cliente cliente = this.cliJpa.findCliente(nickname);
        if (cliente == null) {
            throw new NonexistentEntityException("No se encontró el cliente");
        }
        
        DTSuscripcion dataSus = null;
        
        Suscripcion sus = cliente.getMiSuscripcion();
        if (sus != null) {
            dataSus = sus.getDataSuscripcion();
        }
        
        return dataSus;
    }
    
    public void ingresarNuevaSuscripcion(String nickname, Suscripcion.TipoSuscripcion tipoSuscripcion) throws Exception {
        Cliente cliente = this.cliJpa.findCliente(nickname);
        if (cliente == null) {
            throw new NonexistentEntityException("No se encontró el cliente");
        }
        
        Suscripcion nuevaSuscripcion = new Suscripcion(
                tipoSuscripcion,
                EstadoSuscripcion.Pendiente,
                new Date(),
                cliente
        );
        this.suscripcionJpa.create(nuevaSuscripcion);
        
        cliente.setMiSuscripcion(nuevaSuscripcion);
        try {
            this.cliJpa.edit(cliente);
        } catch (Exception ex) {
            this.suscripcionJpa.destroy(nuevaSuscripcion.getIdSuscripcion());
            throw new DatabaseUpdateException("No se pudo ingresar la suscripcion");
        }
    }
}
