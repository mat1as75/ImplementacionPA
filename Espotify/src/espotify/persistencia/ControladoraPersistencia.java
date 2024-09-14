
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package espotify.persistencia;




import espotify.DataTypes.DTAlbum;
import espotify.DataTypes.DTAlbum_Simple;
import espotify.DataTypes.DTAlbum_SinDTArtista;
import espotify.DataTypes.DTGenero;
import espotify.DataTypes.DTTemaConRuta;
import espotify.DataTypes.DTTemaConURL;
import espotify.DataTypes.DTTemaGenerico;

import espotify.DataTypes.DTDatosArtista;
import espotify.DataTypes.DTDatosCliente;
import espotify.DataTypes.DTDatosListaReproduccion;
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
    
    public ArrayList<Genero> getGeneros() {
        List<Genero> generosL = genJpa.findGeneroEntities();
        ArrayList<Genero> generosAL = new ArrayList<>(generosL);

        return generosAL;
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
    
    public void dejarDeSeguir(String C, String U) {
        try {
            // Cliente que desea dejar de seguir
            Cliente c = this.cliJpa.findCliente(C);
            // Usuario que se desea dejar de seguir
            Usuario u = this.usuJpa.findUsuario(U);

            // Verificar si el cliente sigue al usuario
            if (!c.getMisSeguidos().contains(u)) {
                throw new Exception("El cliente no sigue a este usuario.");
            }

            // Eliminar al usuario de la lista de seguidos del cliente
            c.getMisSeguidos().remove(u);
            // Eliminar al cliente de la lista de seguidores del usuario
            u.getMisSeguidores().remove(c);

            // Actualizar 
            this.usuJpa.edit(c);
            this.usuJpa.edit(u);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Error al intentar dejar de seguir al usuario: " + ex.getMessage(), ex);
        }
    }

    

    public void CrearListaPorDefecto(String nombreLista, String fotoLista, String nombreGenero) {
    // Buscar el genero por su nombre
    Genero genero = this.genJpa.findGenero(nombreGenero);

    // Verificar si el genero existe
    if (genero == null) {
        throw new RuntimeException("No se encontró el género con nombre: " + nombreGenero);
    }

    // Verificar si ya existe una lista por defecto con el mismo nombre
    List<ListaPorDefecto> listasPorDefecto = genero.getMisListasReproduccion();
    for (ListaPorDefecto lista : listasPorDefecto) {
        if (lista.getNombreLista().equals(nombreLista)) {
            throw new RuntimeException("Ya existe una lista por defecto con el nombre: " + nombreLista);
        }
    }

    // Crear la nueva lista por defecto
    ListaPorDefecto nuevaLista = new ListaPorDefecto(nombreLista, fotoLista, genero);
    try {
        lxdefcJpa.create(nuevaLista);
        genero.getMisListasReproduccion().add(nuevaLista);
        genJpa.edit(genero);
    } catch (Exception ex) {
        Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, "Error al crear lista por defecto", ex);
        throw new RuntimeException("Error al crear la lista por defecto: " + ex.getMessage());
    }
    }

    public void CrearListaParticular(String nombreLista, String fotoLista, String nicknameCliente, boolean esPrivada) {
    // Buscar al cliente por su nickname
    Cliente cli = this.cliJpa.findCliente(nicknameCliente);
    
    // Verificar si el cliente existe
    if (cli == null) {
        throw new RuntimeException("No se encontró el cliente con nickname: " + nicknameCliente);
    }

    // Verificar si el cliente ya tiene una lista con el mismo nombre
    List<ListaParticular> listasDelCliente = cli.getMisListasReproduccionCreadas();
    for (ListaParticular l : listasDelCliente) {
        if (l.getNombreLista().equals(nombreLista)) {
            throw new RuntimeException("Este cliente ya tiene una lista con el nombre: " + nombreLista);
        }
    }

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
        throw new IllegalArgumentException("Tipo de lista y nombre de género/cliente no pueden ser nulos.");
    }
    
    if (tipoDeLista.equals("Genero")) {
        // Obtener todas las listas por defecto
        List<ListaPorDefecto> listasPorDefecto = lxdefcJpa.findListaPorDefectoEntities();
        if (listasPorDefecto != null) {
            for (ListaPorDefecto lista : listasPorDefecto) {
                if (lista != null && lista.getGenero() != null && lista.getGenero().getNombreGenero() != null) {
                    if (lista.getGenero().getNombreGenero().equals(nickOgen)) {  // Filtrar por género
                        nombresListas.add(lista.getNombreLista());
                    }
                }
            }
        }
    } else if (tipoDeLista.equals("Cliente")) {
        // Obtener todas las listas particulares
        List<ListaParticular> listasParticulares = lpartJpa.findListaParticularEntities();
        if (listasParticulares != null) {
            for (ListaParticular lista : listasParticulares) {
                if (lista != null && lista.getCliente() != null && lista.getCliente().getNickname() != null) {
                    if (lista.getCliente().getNickname().equals(nickOgen)) {  // Filtrar por cliente
                        nombresListas.add(lista.getNombreLista());
                    }
                }
            }
        }
    }

    return nombresListas;
    }



    
    public DTDatosListaReproduccion getDatosListaReproduccion(String tipoDeLista, String nombreLista){
        
        DTDatosListaReproduccion datosLista = null;

        if (tipoDeLista.equals("Genero")) {
        // Buscar la lista de reproduccion por defecto asociada al género
        ListaPorDefecto listaPorDefecto = lxdefcJpa.findListaPorDefecto(nombreLista);

        if (listaPorDefecto != null) {
            // Obtener los datos de la lista
            String nombreListaReproduccion = listaPorDefecto.getNombreLista();
            String fotoLista = listaPorDefecto.getFotoLista();
            String nombreGenero = listaPorDefecto.getGenero().getNombreGenero();

            // Convertir los temas a DTTemaSimple
            List<DTTemaSimple> temas = new ArrayList<>();
            for (Tema tema : listaPorDefecto.getMisTemas()) {
                temas.add(new DTTemaSimple(
                        tema.getIdTema(),
                        tema.getNombreTema(),
                        tema.getDuracionSegundos(),
                        tema.getPosicionEnAlbum(),
                        tema.getMiAlbum().getNombreAlbum(),
                        tema.getMiAlbum().getMiArtista().getNombreCompletoToString()
                ));
            }

            // Crear el DT para la lista por defecto
            datosLista = new DTDatosListaReproduccion(
                    nombreListaReproduccion,
                    fotoLista,
                    tipoDeLista,
                    temas,
                    nombreGenero
            );
        }
    } else if (tipoDeLista.equals("Cliente")) {
        // Buscar la lista particular asociada al cliente
        ListaParticular listaParticular = lpartJpa.findListaParticular(nombreLista);
        
        if (listaParticular != null) {
            // Obtener los datos de la lista
            String nombreListaReproduccion = listaParticular.getNombreLista();
            String fotoLista = listaParticular.getFotoLista();
            String nicknameCliente = listaParticular.getCliente().getNickname();
            Boolean privacidad = listaParticular.soyPrivada();        
                    
            // Convertir los temas a DTTemaSimple
            List<DTTemaSimple> temas = new ArrayList<>();
            for (Tema tema : listaParticular.getMisTemas()) {
                
                temas.add(new DTTemaSimple(
                        tema.getIdTema(),
                        tema.getNombreTema(),
                        tema.getDuracionSegundos(),
                        tema.getPosicionEnAlbum(),
                        tema.getMiAlbum().getNombreAlbum(),
                        tema.getMiAlbum().getMiArtista().getNombreCompletoToString()
                ));
            }

            // Crear el DT para la lista particular
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
    
    public ArrayList<DTAlbum_Simple> getDTAlbumesSimple() {
        List<Album> listaAlbumes = albJpa.findAlbumEntities();
        ArrayList<DTAlbum_Simple> dataAlbums = new ArrayList<>();
        
        for (Album album: listaAlbumes) {

            dataAlbums.add(new DTAlbum_Simple(
                    album.getIdAlbum(),
                    album.getNombreAlbum(),
                    album.getAnioCreacion(),
                    album.getMiArtista().getNombreCompletoToString()
                )
            );
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
        
        if (c != null){
            List<ListaParticular> listasCreadas = c.getMisListasReproduccionCreadas();
            // Recorro las ListasParticulares
            for (ListaParticular lp : listasCreadas){
                // Si es Privada
                if (lp.soyPrivada()){
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
     
     public void agregarTemaALista(Long idTema, String nombreLista) throws Exception {
        Tema tema = this.temaJpa.findTema(idTema);
        ListaReproduccion listaRep = this.lreprodccJpa.findListaReproduccion(nombreLista);
        //obtengo los temas de la lista de reproduccion destino
        List<Tema> temasDeListaRep = listaRep.getMisTemas();
        
        for (Tema t : temasDeListaRep) {
            if (t.getIdTema() == tema.getIdTema()) {
                //error si el tema ya pertenece a la lista
                throw new Exception("El tema elegido [" 
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
            } catch(Exception ex) {
                throw new Exception("Ocurrio un error al agregar la lista "
                    + listaRep.getNombreLista() 
                    + " a las listas asociadas al tema ["
                    + tema.getIdTema() + "]."
                );
            }
        }

        try {
           this.temaJpa.edit(tema);
        } catch (Exception ex) {
           throw new Exception(
                "Ocurrio un error al agregar el tema [" 
                + tema.getIdTema() + "] a la lista " 
                + listaRep.getNombreLista());
        }
    }
     
    public ArrayList<String> getNombresListasParticularesDeCliente(String nicknameCliente) throws Exception {
        Cliente cl = this.cliJpa.findCliente(nicknameCliente);
        
        if (cl == null) {
            throw new Exception ("No se encontró el cliente " + nicknameCliente + ".");
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

}


