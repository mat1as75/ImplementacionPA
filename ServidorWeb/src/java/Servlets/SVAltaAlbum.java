package Servlets;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import webservices.ContenidoService;
import webservices.ContenidoServiceService;
import webservices.DataTypes.DtAlbumSinDTArtista;
import webservices.DataTypes.DtGenero;
import webservices.DataTypes.DtTemaConTipo;
import webservices.UsuarioService;
import webservices.UsuarioServiceService;

@WebServlet(urlPatterns = {"/AltaAlbum"})
@MultipartConfig(
        maxFileSize = 1024 * 1024 * 20
)
public class SVAltaAlbum extends HttpServlet {
    
    private static final String UPLOAD_DIR = "../../web/Resource/Albums/";
    
    //Funcion para borrar el directorio si falla la copia de los archivos
    boolean deleteDir(File directorioABorrar) {
        File[] contenido = directorioABorrar.listFiles();
        if (contenido != null) {
            for (File file : contenido) {
                deleteDir(file);
            }
        }
        return directorioABorrar.delete();
    }
    
    private Map<String, String> uploadTemas(Map<String, Part> archivosTemas, String artista, String nombreAlbum) {
        
        Map<String, String> mapTemasConRutas = new HashMap();

        //Directorio de los temas: ../Resource/Albums/<nombre del artista>/<nombre del album>/
        String uploadPathTemas = this.getServletContext().getRealPath("") + UPLOAD_DIR + artista + "/" + nombreAlbum;
        File uploadDirTemas = new File(uploadPathTemas);
        
        //Creo el directorio del album si no existe
        if (!uploadDirTemas.exists()) {
            uploadDirTemas.mkdirs();
        }
        
        //Por cada Part copio el archivo recibido al directorio
        for (Entry<String, Part> entry : archivosTemas.entrySet()) {
            String nombreTema = entry.getKey();
            Part partTema = entry.getValue();
            File nuevoArchivoTema = new File(uploadDirTemas, nombreTema);
                        
            try {
                if (partTema != null && !nuevoArchivoTema.exists()) {
                    InputStream input = partTema.getInputStream();
                    Files.copy(input, nuevoArchivoTema.toPath());
                }
                mapTemasConRutas.put(nombreTema, nuevoArchivoTema.getPath());
                
            } catch (Exception e) {
                Boolean borrarDirectorio = deleteDir(uploadDirTemas);
                return null;
            }
        }
        
        return mapTemasConRutas;
    }
    
    private String uploadPortada(Part partPortada, String artista, String nombreAlbum) {
        
        //Directorio de la portada del album: ../Resource/Albums/Portadas
        String uploadPathPortadas = this.getServletContext().getRealPath("") + UPLOAD_DIR + artista + "/" + nombreAlbum;
        File uploadDirPortadas = new File(uploadPathPortadas);

        //Creo el directorio de las portadas si no existe
        if (!uploadDirPortadas.exists()) {
            uploadDirPortadas.mkdirs();
        }
        
        //Declaro archivo de imagen
        String nombreArchivoPortada = artista + "-" + nombreAlbum;
        File archivoPortada = new File(uploadDirPortadas, nombreArchivoPortada);
        
        //Copio la portada del album 
        try {
            if (partPortada != null && !archivoPortada.exists()) {
                InputStream input = partPortada.getInputStream();
                Files.copy(input, archivoPortada.toPath());
            } 
        } catch (Exception e) {
            Boolean borrarDirectorio = deleteDir(uploadDirPortadas);
            return null;
        }
        return archivoPortada.getPath();
    }
    
    private List<DtTemaConTipo> crearListaDTTemas(Map<String, String> mapTemasConRutas, int cantidadTemas, HttpServletRequest request) {
        List<DtTemaConTipo> dataTemas = new ArrayList();
        for (int i=0; i<cantidadTemas; i++) {
            String parameterPrefix = "tema-" + i + "-";
            String nombreTema = convertToUTF8(request.getParameter(parameterPrefix + "nombre"));
            String duracionTemaString = convertToUTF8(request.getParameter(parameterPrefix + "duracion"));
            int duracionMinutos = Integer.valueOf(
                    duracionTemaString.substring(0,duracionTemaString.indexOf(":")));
            int duracionSegundos = Integer.valueOf(
                    duracionTemaString.substring(duracionTemaString.indexOf(":")+1));
            
            int duracionTotalSegundos = duracionMinutos * 60 + duracionSegundos;
            String tipoDeAcceso = convertToUTF8(request.getParameter(parameterPrefix + "tipoDeAcceso"));
            String archivoOurl = convertToUTF8(request.getParameter(parameterPrefix + "urlOruta"));
            String posicionString = convertToUTF8(request.getParameter(parameterPrefix + "posicion"));
            int posicionTema = Integer.valueOf(posicionString);
            
            if (tipoDeAcceso.equals("ruta")) {
                String rutaTema = mapTemasConRutas.get(nombreTema);
                DtTemaConTipo dataTema = new DtTemaConTipo();
                dataTema.setTipo("ruta");
                dataTema.setNombreTema(nombreTema);
                dataTema.setPosicionEnAlbum(posicionTema);
                dataTema.setDuracionSegundos(duracionTotalSegundos);
                dataTema.setRutaOurl(rutaTema);
                dataTemas.add(dataTema);
            }
            if (tipoDeAcceso.equals("url")) {
                DtTemaConTipo dataTema = new DtTemaConTipo();
                dataTema.setTipo("url");
                dataTema.setNombreTema(nombreTema);
                dataTema.setPosicionEnAlbum(posicionTema);
                dataTema.setDuracionSegundos(duracionTotalSegundos);
                dataTema.setRutaOurl(archivoOurl);
                dataTemas.add(dataTema);
            }
        }
        return dataTemas;
    }
    
    private List<DtGenero> crearListaDTGenero(String[] generos) {
        List<DtGenero> dataGeneros = new ArrayList();
        
        for (String gen : generos) {
            DtGenero dataGenero = new DtGenero();
            dataGenero.setNombreGenero(convertToUTF8(gen));
            dataGeneros.add(dataGenero);
        }
        
        return dataGeneros;
    }
    
    /*
    El form tiene el Content-Type multipart/form-data por tener que subir archivos multimedia y texto.
    Por esta razon es que no se puede setear el charset del texto a UTF-8.
    Los datos de Strings llegan al servidor con formato ISO-8859-1, que lee de forma distinta los tildes,
    mientras que los datos almacenados en la BD estan en formato UTF-8.
    Para evitar problemas en la comparacion de Strings con tildes u otros caracteres especiales 
    hay que convertir los strings al mismo formato que utiliza la BD.
    */
    private String convertToUTF8(String str) {
        
        byte[] bytes = str.getBytes(StandardCharsets.ISO_8859_1);
        String encoded = new String(bytes, StandardCharsets.UTF_8);
        
        return encoded;
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        UsuarioServiceService usuarioWS = new UsuarioServiceService();
        UsuarioService usuarioPort = usuarioWS.getUsuarioServicePort();
        
        //No se permite acceder a un usuario sin sesion
        HttpSession sesion = request.getSession(false);
        if (sesion == null) {
            SVError.redirectUnauthorized(request, response);
            return;
        }
        
        //No se permite el acceso a un usuario no logueado
        String nickname = (String) sesion.getAttribute("nickname");
        if (nickname == null) {
            SVError.redirectUnauthorized(request, response);
            return;
        }
        
        Boolean existeArtista = usuarioPort.existeArtista(nickname);
        //No se permite el acceso a un usuario que no sea artista
        if (!existeArtista) {
            SVError.redirectUnauthorized(request, response);
            return;
        }
        //Seteo el como atributo el nickname del artista logueado para usarlo en el jsp
        request.setAttribute("nicknameArtista", nickname);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        processRequest(request, response);
        request.getRequestDispatcher("pages/altaAlbum.jsp").forward(request, response);

    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        processRequest(request, response);
        
        ContenidoServiceService contenidoWS = new ContenidoServiceService();
        ContenidoService contenidoPort = contenidoWS.getContenidoServicePort();
        
        String artista = (String) request.getAttribute("nicknameArtista");

        //Obtengo los datos generales del album
        String nombreAlbum = convertToUTF8(request.getParameter("nombreAlbum"));
        String anioAlbumString = convertToUTF8(request.getParameter("anioAlbum"));
        int anioAlbum = Integer.valueOf(anioAlbumString);
        String[] generosAlbum = request.getParameterMap().get("generos");
        String cantidadTemasString = convertToUTF8(request.getParameter("cantidadTemas"));
        int cantidadTemas = Integer.valueOf(cantidadTemasString);
        
        //Extraigo los archivos de audio e imagen de portada
        List<Part> parts = (List<Part>) request.getParts();
        Map<String, Part> archivosDeTemas = new HashMap();
        Part partPortada = null;
        for (Part p : parts) {
            if (p.getContentType() != null) {
                String partName = p.getName();
                if (partName.startsWith("file")) {
                    String nombreTema = convertToUTF8(partName.substring(partName.indexOf("-")+1));
                    archivosDeTemas.put(nombreTema, p);
                }
                if (partName.equals("inputPortadaAlbum")) {
                    if (p.getSize() > 0) {
                        partPortada = p;
                    }
                }
            }
        }
        
        //Subo los temas recibidos en el request y creo un map con el nombre del tema y la ruta del archivo subido
        Map<String, String> mapTemasConRutas = uploadTemas(archivosDeTemas, artista, nombreAlbum);
        if (mapTemasConRutas == null) {
            response.setStatus(response.SC_INTERNAL_SERVER_ERROR);
            response.setContentType("text/plain");
            response.getWriter().write("Error al subir los temas.");
            return;
        }
        //Creo los DTTema con los datos del request y con las rutas de los archivos subidos en los que son con ruta
        List<DtTemaConTipo> dataTemas = crearListaDTTemas(mapTemasConRutas, cantidadTemas, request);
       
        String rutaPortada = null;
        //Subo la imagen de portada
        if (partPortada != null) {
            rutaPortada = uploadPortada(partPortada, artista, nombreAlbum);
            if (rutaPortada == null) {
                response.setStatus(response.SC_INTERNAL_SERVER_ERROR);
                response.setContentType("text/plain");
                response.getWriter().write("Error al copiar la imagen de portada.");
                return;
            }
        }
        
        //Creo la lista de DTGenero con los generos recibidos en el request
        List<DtGenero> dataGeneros = crearListaDTGenero(generosAlbum);
        
        DtAlbumSinDTArtista dataAlbum = new DtAlbumSinDTArtista();
        dataAlbum.setNombreAlbum(nombreAlbum);
        dataAlbum.setAnioCreacion(anioAlbum);
        dataAlbum.setFotoAlbum(rutaPortada);
        dataAlbum.setMiArtista(artista);
        dataAlbum.getMisTemas().addAll(dataTemas);
        dataAlbum.getMisgeneros().addAll(dataGeneros);
        
        try {
            contenidoPort.altaAlbum(dataAlbum);
            response.setStatus(201);
        } catch (Exception e) {
            //Si ocurrio un error inesperado en el servidor
            response.setStatus(response.SC_INTERNAL_SERVER_ERROR);
            //Si no se encontro uno de los recursos buscados (artista/genero)
            if (e.getMessage().contains("No se encontro")) {
                response.setStatus(response.SC_NOT_FOUND);
            }
            
            //Si se recibieron datos invalidos en el request
            if (e.getMessage().contains("Este artista ya tiene un album con el nombre") 
                    || e.getMessage().contains("Existen campos requeridos nulos")) {
                response.setStatus(response.SC_BAD_REQUEST);
            }
            response.getWriter().write("Error al procesar la solicitud.");
            return;
        }
        
        //response
        response.setContentType("text/plain");
        response.getWriter().write("Album creado exitosamente.");
    }
}