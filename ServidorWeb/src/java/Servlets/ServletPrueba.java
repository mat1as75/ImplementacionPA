package Servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import webservices.ContenidoService;
import webservices.ContenidoServiceService;
import webservices.DataAlbumsService;
import webservices.DataAlbumsServiceService;
import webservices.DataTypes.DtAlbumSimple;
import webservices.DataTypes.DtAlbumSinDTArtista;
import webservices.DataTypes.DtDatosListaReproduccion;
import webservices.DataTypes.DtDatosUsuario;
import webservices.DataTypes.DtDatosUsuarioSinPw;
import webservices.DataTypes.DtGenero;
import webservices.DataTypes.DtGeneroSimple;
import webservices.DataTypes.DtSuscripcion;
import webservices.DataTypes.DtTemaConPuntaje;
import webservices.DataTypes.DtTemaConTipo;
import webservices.DataTypes.DtTemaSimple;
import webservices.EstadoSuscripcion;
import webservices.Exception_Exception;
import webservices.ListaReproduccionService;
import webservices.ListaReproduccionServiceService;
import webservices.MapContainer;
import webservices.NullableContainer;
import webservices.PreferenciasService;
import webservices.PreferenciasServiceService;
import webservices.RankingService;
import webservices.RankingServiceService;
import webservices.SuscripcionesService;
import webservices.SuscripcionesServiceService;
import webservices.TipoSuscripcion;

@WebServlet(name = "ServletPrueba", urlPatterns = {"/ServletPrueba"})
public class ServletPrueba extends HttpServlet {

    private List<DtTemaConTipo> crearDataTemasDePrueba() {
        DtTemaConTipo tema1 = new DtTemaConTipo();
        tema1.setTipo("ruta");
        tema1.setDuracionSegundos(120);
        tema1.setNombreTema("tema1");
        tema1.setPosicionEnAlbum(1);
        tema1.setRutaOurl("rutaTema1");
        
        DtTemaConTipo tema2 = new DtTemaConTipo();
        tema2.setTipo("url");
        tema2.setDuracionSegundos(120);
        tema2.setNombreTema("tema2");
        tema2.setPosicionEnAlbum(2);
        tema2.setRutaOurl("urltema2");
        
        List<DtTemaConTipo> dataTemas = new ArrayList();
        dataTemas.add(tema1);
        dataTemas.add(tema2);
        
        return dataTemas;
    }
    
    private List<DtGenero> crearDataGenerosDePrueba() {
        DtGenero g1 = new DtGenero();
        g1.setNombreGenero("Rock");
        DtGenero g2 = new DtGenero();
        g2.setNombreGenero("Balada");
        
        List<DtGenero> dataGeneros = new ArrayList();
        dataGeneros.add(g1);
        dataGeneros.add(g2);
        
        return dataGeneros;
    }
    
    private void testGetUsuariosPorRanking(RankingService rankingPort) {
        System.out.println("10 Usuarios ordenados por cantidad de seguidores descendente...");
        List<Object> listaObjetos = rankingPort.getUsuariosOrdenadosPorRanking(10).getColeccion();
        for (Object o : listaObjetos) {
            DtDatosUsuarioSinPw dtu = (DtDatosUsuarioSinPw) o;
            System.out.println("usuario " + dtu.getNicknameUsuario());
            System.out.println("seguidores: " + dtu.getNicknamesSeguidores());
        }
    }
    
     private void testGetUsuariosPorRanking2(RankingService rankingPort) {
        System.out.println("todos los usuarios ordenados por ranking de seguidores...");
        List<Object> listaObjetos = rankingPort.getUsuariosOrdenadosPorRanking(0).getColeccion();
        for (Object o : listaObjetos) {
            DtDatosUsuarioSinPw dtu = (DtDatosUsuarioSinPw) o;
            System.out.println("usuario " + dtu.getNicknameUsuario());
            System.out.println("seguidores: " + dtu.getNicknamesSeguidores());
        }
    }
    
    private void testGetAlbumesDisponibles(ContenidoService contenidoPort) {
        System.out.println("Map albums disponibles...");
        MapContainer.MapLongString mapAlbums = contenidoPort.getAlbumesDisponibles().getMapLongString();
        for (MapContainer.MapLongString.Entry e : mapAlbums.getEntry()) {
            System.out.println("Album " + e.getKey() + " - " + e.getValue());
        }
    }
    
    private void testGetDTTemasDisponiblesConAlbum(ContenidoService contenidoPort) {
        System.out.println("Map temas disponibles...");
        MapContainer.MapLongDatatype mapTemas = contenidoPort.getDTTemasDisponiblesConAlbum().getMapLongDatatype();
        for (MapContainer.MapLongDatatype.Entry e : mapTemas.getEntry()) {
            System.out.println(e.getKey() + " - " 
                    + e.getValue().getNombreTema() + " - " 
                    + e.getValue().getNombreCompletoArtista() + " - "
                    + e.getValue().getGenerosDeTema() + " - "
                    + e.getValue().getPuntajeTema() + " - Descargas o Visitas:"
                    + e.getValue().getCantidadDescargasOVisitas() + " - Reproducciones: " 
                    + e.getValue().getCantidadReproducciones() + " - Favoritos: "
                    + e.getValue().getCantidadFavoritos() + " - Duracion: "
                    + e.getValue().getDuracionSegundos());
        }
    }
    
    private void testGet1DTAlbumSimple(DataAlbumsService dtAlbumsPort) {
        System.out.println("Probando get de 1 DTAlbumSimple existente...");
        DtAlbumSimple dta1 = dtAlbumsPort.getDTAlbumSimplePorId(1L);
        System.out.println(dta1.getIdAlbum() + " - " 
                    + dta1.getNombreAlbum() + " - " 
                    + dta1.getNombreCompletoArtista() + " - " 
                    + dta1.getAnioCreacion() + " - " 
                    + dta1.getGenerosDeAlbum());
        
        System.out.println("Probando get de 1 DTAlbumSimple inexistente...");
        DtAlbumSimple dta2 = dtAlbumsPort.getDTAlbumSimplePorId(999L);
        System.out.println("dta2 esta vacio? id del album: " + dta2.getIdAlbum());

    }
    
    private void testGetTodosDTAlbumsSimple(DataAlbumsService dtAlbumsPort) {
        System.out.println("Probando get todos los dtalbumsimple...");
        List<Object> listaAlbums = dtAlbumsPort.getDTAlbumesSimple().getColeccion();
        for (Object o : listaAlbums) {
            DtAlbumSimple dtalb = (DtAlbumSimple) o;
            System.out.println(dtalb.getIdAlbum() + " - " 
                    + dtalb.getNombreAlbum() + " - " 
                    + dtalb.getNombreCompletoArtista() + " - " 
                    + dtalb.getAnioCreacion() + " - " 
                    + dtalb.getGenerosDeAlbum());
        }
    }
    
     private void testGetAlbumesPorGenero(DataAlbumsService dtAlbumsPort) {
        System.out.println("Lista de albums por genero Balada ...");
        List<Object> listaAlbums = dtAlbumsPort.getDTAlbumesSimplePorGenero("Balada").getColeccion();
        for (Object o : listaAlbums) {
            DtAlbumSimple dtalb = (DtAlbumSimple) o;
            System.out.println(dtalb.getIdAlbum() + " - " 
                    + dtalb.getNombreAlbum() + " - " 
                    + dtalb.getNombreCompletoArtista() + " - " 
                    + dtalb.getAnioCreacion() + " - " 
                    + dtalb.getGenerosDeAlbum());
        }
        
        System.out.println("Lista de albums por genero inexistente...");
        List<Object> listaAlbums2 = dtAlbumsPort.getDTAlbumesSimplePorGenero("asdfasd").getColeccion();
        System.out.println("tamaño lista: " + listaAlbums2.size());
        System.out.println("operacion ok");
        
    }
    
    private void testGetAlbumesPorArtista(DataAlbumsService dtAlbumsPort) {
        System.out.println("Lista de albums por artista alcides ...");
        List<Object> listaAlbums = dtAlbumsPort.getDTAlbumesSimplePorArtista("alcides").getColeccion();
        for (Object o : listaAlbums) {
            DtAlbumSimple dtalb = (DtAlbumSimple) o;
            System.out.println(dtalb.getIdAlbum() + " - " 
                    + dtalb.getNombreAlbum() + " - " 
                    + dtalb.getNombreCompletoArtista() + " - " 
                    + dtalb.getAnioCreacion() + " - " 
                    + dtalb.getGenerosDeAlbum());
        }
        
        System.out.println("Lista de albums por artista inexistente ...");
        List<Object> listaAlbums2 = dtAlbumsPort.getDTAlbumesSimplePorArtista("asdf").getColeccion();
        System.out.println("Tamaño de coleccion: " + listaAlbums2.size());
    }
    
    private void testGetGenerosPadre(ContenidoService contenidoPort) {
        System.out.println("Generos padre...");
        List<Object> listaGenPadre = contenidoPort.getNombresGenerosPadre().getColeccion();
        for (Object o : listaGenPadre) {
            String genero = (String) o;
            System.out.println(genero);
        }
    }
    
    private void testAgregarTemaALista(ContenidoService contenidoPort) {
        System.out.println("Agregando tema a lista");
        try {
            contenidoPort.agregarTemaALista(11, "De Todo Un Poco");
            System.out.println("agregado tema 11 a lista De Todo Un Poco");
        } catch (Exception e) {
            System.out.println("Fallo agregar tema 11 a lista");
        }
    }
    
    private void testBuscarAlbumPorNombreYArtista(ContenidoService contenidoPort) {
        System.out.println("Buscando album Violator de dmode");
        Long idAlbumBuscado = contenidoPort.buscarAlbumPorNombreYArtista("dmode", "Violator");
        if (idAlbumBuscado != 0L) {
            System.out.println("id alb: " + idAlbumBuscado);
        } else {
            System.out.println("No existe album buscado");
        }
        
        System.out.println("Buscando album inexistente");
        Long idAlbumBuscado2 = contenidoPort.buscarAlbumPorNombreYArtista("alcides", "asdf");
        if (idAlbumBuscado != 0L) {
            System.out.println("id alb: " + idAlbumBuscado2);
        } else {
            System.out.println("No existe album buscado");
        }
    }
    
    private void testQuitarTemaDeLista(ContenidoService contenidoPort) {
        System.out.println("Quitando tema de lista");
        try {
            contenidoPort.quitarTemaDeLista(11, "De Todo Un Poco");
            System.out.println("quitado tema 11 a lista De Todo Un Poco");
        } catch (Exception e) {
            System.out.println("Fallo quitar tema 11 a lista");
        }
    }
    
    private void testAltaAlbum(ContenidoService contenidoPort) {
        DtAlbumSinDTArtista dataAlbum = new DtAlbumSinDTArtista();
        dataAlbum.setAnioCreacion(2000);
        dataAlbum.setEstaDisponible(true);
        dataAlbum.setFotoAlbum(  "rutaportada");
        dataAlbum.setMiArtista("alcides");
        
        List<DtTemaConTipo> dataTemas = this.crearDataTemasDePrueba();
        List<DtGenero> dataGeneros = this.crearDataGenerosDePrueba();
        
        dataAlbum.getMisTemas().addAll(dataTemas);
        dataAlbum.getMisgeneros().addAll(dataGeneros);
        dataAlbum.setNombreAlbum( "albumDePrueba");
        
        System.out.println("Creando album de prueba a alcides...");
        try {
            contenidoPort.altaAlbum(dataAlbum);
            System.out.println("alta album ok");
        } catch (Exception_Exception e) {
            System.out.println(e.getFaultInfo().getMessage());
        }
    }
    
    private void testGetDtGeneroSimple(ContenidoService contenidoPort) {
        System.out.println("Get lista dtgenerosimple...");
        List<Object> listaGeneros = contenidoPort.getListaDTGeneroSimple().getColeccion();
        for (Object o : listaGeneros) {
            DtGeneroSimple dg = (DtGeneroSimple) o;
            System.out.println(dg.getNombreGenero() 
                    + " - Padre: " + dg.getNombreGeneroPadre() 
                    + " - Subgeneros: " + dg.getSubgeneros());
        }
    }
    
    private void testGetTopTemas(RankingService rankingPort) {
        System.out.println("Get top 10 temas...");
        List<Object> listaTemas = rankingPort.getTopTemas(10).getColeccion();
        for (Object o : listaTemas) {
            DtTemaConPuntaje dttema = (DtTemaConPuntaje) o;
            System.out.println(dttema.getIdTema() + " - " 
                + dttema.getNombreTema() + " - " 
                + dttema.getNombreCompletoArtista() + " - " 
                + dttema.getPuntajeTema());
        }
    }
    
    private void testGetTopTema(RankingService rankingPort) {
        System.out.println("Get top 1 tema...");
        DtTemaConPuntaje dttema = rankingPort.getTopTema();
        System.out.println(dttema.getIdTema() + " - " 
                + dttema.getNombreTema() + " - " 
                + dttema.getNombreCompletoArtista() + " - " 
                + dttema.getPuntajeTema());
    }
    
    private void testGetDTSuscripcion1(SuscripcionesService suscripcionPort) {
        System.out.println("Obteniendo suscripcion 1...");
        NullableContainer dataContainer = suscripcionPort.getDTSuscripcon(1L);
        DtSuscripcion dataSusc = dataContainer.getDtSuscripcion();
        System.out.println(dataSusc.getIdSuscripcion() + " - " 
                + dataSusc.getEstadoSuscripcion() + " - " 
                + dataSusc.getTipoSuscripcion() + " - " 
                + dataSusc.getFechaSuscripcion().toString() + " - " 
                + dataSusc.getMiCliente().getNickname());
    }
    
    private void testGetDTSuscripcion2(SuscripcionesService suscripcionPort) {
        System.out.println("Obteniendo suscripcion 999...");
        NullableContainer dataContainer = suscripcionPort.getDTSuscripcon(999L);
        DtSuscripcion dataSusc = dataContainer.getDtSuscripcion();
        System.out.println("dataSusc es null: " + (dataSusc == null));
    }
    
    private void testActualizarSuscripcion(SuscripcionesService suscripcionPort) {
        System.out.println("Actualizando Suscripcion 4...");
        XMLGregorianCalendar xmlDate = null;
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(new Date());
        try {
            xmlDate =  DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
        } catch (DatatypeConfigurationException ex) {
            Logger.getLogger(ServletPrueba.class.getName()).log(Level.SEVERE, null, ex);
        }
        suscripcionPort.actualizarEstadoSuscripcion(4L, EstadoSuscripcion.VIGENTE, xmlDate);
        System.out.println("suscripcion 4 actualizada");
    }
    
    private void testActualizarSuscripcionVencida(SuscripcionesService suscripcionPort) {
        System.out.println("Actualizando suscripcion vencida (espero true)...");
        Boolean res = suscripcionPort.actualizarSuscripcionVencida(1L);
        System.out.println("Resultado: " + res);
    }

    private void testIngresarNuevaSuscripcion(SuscripcionesService suscripcionPort) {
        System.out.println("Ingresando nueva suscripcion cbochinche...");
        try {
            suscripcionPort.ingresarNuevaSuscripcion("cbochinche", TipoSuscripcion.ANUAL);
        } catch (Exception e) {
            System.out.println("Fallo ingreso de suscripcion:");
            System.out.println(e.getMessage());
        }
        System.out.println("fin de ingreso de nueva suscripcion");
    }
    
    private void testIngresarNuevaSuscripcion2(SuscripcionesService suscripcionPort) {
        System.out.println("Ingresando nueva suscripcion cliente inexistente...");
        try {
            suscripcionPort.ingresarNuevaSuscripcion("asdf", TipoSuscripcion.ANUAL);
            System.out.println("fin de ingreso de nueva suscripcion");
        } catch (Exception e) {
            System.out.println("Fallo ingreso de suscripcion:");
            System.out.println(e.getMessage());
        }
    }
    
    private void testGetDTSuscDeCliente1(SuscripcionesService suscripcionPort) {
        System.out.println("Recuperando DTSuscripcion de Eleven11...");
        try {
            NullableContainer dataContainer = suscripcionPort.getDTSuscripcionDeCliente("Eleven11");
            DtSuscripcion dataSusc = dataContainer.getDtSuscripcion();
            System.out.println("Suscripcion: " + dataSusc.getIdSuscripcion() + " - " 
                    + dataSusc.getEstadoSuscripcion() + " - " 
                    + dataSusc.getTipoSuscripcion() + " - " 
                    + dataSusc.getMiCliente().getNickname());
        } catch (Exception e) {
            System.out.println("Error al recuperar la suscripcion:");
            System.out.println(e.getMessage());
        } 
    }
    
    private void testGetDTSuscDeCliente2(SuscripcionesService suscripcionPort) {
        System.out.println("Recuperando DTSuscripcion de cliente inexistente...");
        try {
            NullableContainer dataContainer = suscripcionPort.getDTSuscripcionDeCliente("asdfasdf");
            DtSuscripcion dataSusc = dataContainer.getDtSuscripcion();
            System.out.println("Suscripcion es null: " + dataSusc == null);
        } catch (Exception e) {
            System.out.println("Error al recuperar la suscripcion:");
            System.out.println(e.getMessage());
        } 
    }
    
    
    private void testConsultaListaReproduccion(ListaReproduccionService listaRepPort) {
        DtDatosListaReproduccion dtLR = (DtDatosListaReproduccion) listaRepPort.consultarListaReproduccion("Particular", "Fiesteras").getDtDatosListaReproduccion();
        System.out.println("Lista particular - " + dtLR.getNombreLista());
        List<DtTemaSimple> dataTemas = dtLR.getTemas();
        for (DtTemaSimple dttema : dataTemas) {
            System.out.println("Tema " + dttema.getNombreTema() + " - " + dttema.getCantidadReproducciones());
        }
        //
        DtDatosListaReproduccion dtLR2 = (DtDatosListaReproduccion) listaRepPort.consultarListaReproduccion("Por Defecto", "Noche De La Nostalgia").getDtDatosListaReproduccion();
        System.out.println("Lista por defecto - " + dtLR2.getNombreLista());
        List<DtTemaSimple> dataTemas2 = dtLR2.getTemas();
        for (DtTemaSimple dttema : dataTemas2) {
            System.out.println("Tema " + dttema.getNombreTema() + " - " + dttema.getCantidadReproducciones());
        }
        
        System.out.println("Probando consultar lista con tipo lista invalido...");
        DtDatosListaReproduccion dtLR3 = (DtDatosListaReproduccion) listaRepPort.consultarListaReproduccion("asd", "Fiesteras").getDtDatosListaReproduccion();
        System.out.println("data type es null? : " + (dtLR3 == null));
    }
    
    private void testNombresListasParticularesPublicas(ListaReproduccionService listaRepPort) {
        System.out.println("Listas particulares publicas...");
        List<Object> listasPublicas = listaRepPort.getNombresListasParticularesPublicas().getColeccion();
        for (Object o : listasPublicas) {
            String s = (String) o;
            System.out.println(s);
        }
    }
    
    private void testgetDTListaRepDeCliente(ListaReproduccionService listaRepPort) {
        System.out.println("Listas de cbochinche...");
        try {
            List<Object> listasDeCliente = listaRepPort
                    .getListaDTDatosListaReproduccionDeCliente("cbochinche")
                    .getColeccion();
            for (Object o : listasDeCliente) {
                DtDatosListaReproduccion dataLr = (DtDatosListaReproduccion) o;
                System.out.println("DTDatosListaRep: " + dataLr.getNombreLista() + " - Tipo: " + dataLr.getTipoDeLista());
            }
        } catch (Exception_Exception ex) {
            System.out.println(ex.getFaultInfo().getMessage());
        }
        //
        System.out.println("Listas de cliente inexistente...");
        try {
            List<Object> listasDeCliente = listaRepPort
                    .getListaDTDatosListaReproduccionDeCliente("asdfasd")
                    .getColeccion();
            for (Object o : listasDeCliente) {
                DtDatosListaReproduccion dataLr = (DtDatosListaReproduccion) o;
                System.out.println("DTDatosListaRep: " + dataLr.getNombreLista() + " - Tipo: " + dataLr.getTipoDeLista());
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    private void testGetNombresListasPrivadasDeCliente(ListaReproduccionService listaRepPort) {
        System.out.println("Listas privadas de Heisenberg...");
        List<Object> listasPrivDeHeisenberg = listaRepPort.getNombresDeListasPrivadasDeCliente("Heisenberg").getColeccion();
        for (Object o : listasPrivDeHeisenberg) {
            String lista = (String) o;
            System.out.println(lista);
        }
        
        System.out.println("Listas privadas de cbochinche...");
        List<Object> listasPrivDecbochinche = listaRepPort.getNombresDeListasPrivadasDeCliente("cbochinche").getColeccion();
        for (Object o : listasPrivDecbochinche) {
            String lista = (String) o;
            System.out.println(lista);
        }
        
        System.out.println("Listas privadas de cliente inexistente...");
        List<Object> listasPriv = listaRepPort.getNombresDeListasPrivadasDeCliente("asdfasd").getColeccion();
        for (Object o : listasPriv) {
            String lista = (String) o;
            System.out.println(lista);
        }
    }
    
    private void testCrearListaParticular(ListaReproduccionService listaRepPort) {
        System.out.println("Creando lista reproduccion...");
        XMLGregorianCalendar xmlDate = null;
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(new Date());
        try {
            xmlDate =  DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
        } catch (DatatypeConfigurationException ex) {
            Logger.getLogger(ServletPrueba.class.getName()).log(Level.SEVERE, null, ex);
        }
        listaRepPort.crearListaParticular("listanueva", "", "cbochinche", xmlDate, true);
    }
    
    private void testListasReproduccionDisponibles(ListaReproduccionService listaRepPort) {
        System.out.println("Listas reproduccion disponibles...");
        List<Object> listasDisponibles = listaRepPort.getListasReproduccionDisponibles().getColeccion();
        for (Object o : listasDisponibles) {
            String lista = (String) o;
            System.out.println("Lista: " + lista);
        }
    }
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        //ContenidoService
        ContenidoServiceService contenidoWS = new ContenidoServiceService();
        ContenidoService contenidoPort = contenidoWS.getContenidoServicePort();
        //SuscripcionesService
        SuscripcionesServiceService suscripcionesWS = new SuscripcionesServiceService();
        SuscripcionesService suscripcionesPort = suscripcionesWS.getSuscripcionesServicePort();
        //PreferenciasService
        PreferenciasServiceService preferenciasWS = new PreferenciasServiceService();
        PreferenciasService preferenciasPort = preferenciasWS.getPreferenciasServicePort();
        //DtAlbumsService
        DataAlbumsServiceService DtAlbumsWS = new DataAlbumsServiceService();
        DataAlbumsService dtAlbumsPort = DtAlbumsWS.getDataAlbumsServicePort();
        //RankingService
        RankingServiceService RankingWS = new RankingServiceService();
        RankingService rankingPort = RankingWS.getRankingServicePort();
        //ListaReproduccionService
        ListaReproduccionServiceService ListaReproduccionWS = new ListaReproduccionServiceService();
        ListaReproduccionService listaReproduccionPort = ListaReproduccionWS.getListaReproduccionServicePort();
        
        
        //pruebas ContenidoService
        /*
        this.testAgregarTemaALista(contenidoPort); //OK
        this.testBuscarAlbumPorNombreYArtista(contenidoPort);//OK
        this.testQuitarTemaDeLista(contenidoPort);//OK
        this.testGetAlbumesDisponibles(contenidoPort);//OK
        this.testGetDTTemasDisponiblesConAlbum(contenidoPort);//OK
        this.testAltaAlbum(contenidoPort); //OK
        this.testGetDtGeneroSimple(contenidoPort);//OK
        */
        
        //pruebas DataAlbumsService
        /*
        this.testGet1DTAlbumSimple(dtAlbumsPort);//OK
        this.testGetAlbumesPorGenero(dtAlbumsPort);//OK
        this.testGetAlbumesPorArtista(dtAlbumsPort);//OK
        this.testGetTodosDTAlbumsSimple(dtAlbumsPort);//OK
        */
                
        //pruebas RankingService
        /*
        this.testGetTopTema(rankingPort);//OK
        this.testGetTopTemas(rankingPort);//OK
        this.testGetUsuariosPorRanking(rankingPort);//OK
        this.testGetUsuariosPorRanking2(rankingPort);//OK
        */
        
        //pruebas SuscripcionesService
        /*
        this.testGetDTSuscripcion1(suscripcionesPort);//OK
        this.testGetDTSuscripcion2(suscripcionesPort);//OK
        this.testActualizarSuscripcion(suscripcionesPort);//OK
        this.testActualizarSuscripcionVencida(suscripcionesPort);//OK
        this.testIngresarNuevaSuscripcion(suscripcionesPort);//OK    
        this.testIngresarNuevaSuscripcion2(suscripcionesPort);//OK
        this.testGetDTSuscDeCliente1(suscripcionesPort);//OK
        this.testGetDTSuscDeCliente2(suscripcionesPort);//OK
        */
        
        //pruebas ListaReproduccionService
        /*
        this.testConsultaListaReproduccion(listaReproduccionPort);//OK
        this.testGetNombresListasPrivadasDeCliente(listaReproduccionPort);//OK
        this.testListasReproduccionDisponibles(listaReproduccionPort);//OK
        this.testNombresListasParticularesPublicas(listaReproduccionPort);//OK
        this.testgetDTListaRepDeCliente(listaReproduccionPort);//OK
        */
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
