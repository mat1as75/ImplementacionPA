package Servlets;

import espotify.DataTypes.DTAlbum_SinDTArtista;
import espotify.DataTypes.DTGenero;
import espotify.DataTypes.DTTemaConRuta;
import espotify.DataTypes.DTTemaConURL;
import espotify.DataTypes.DTTemaGenerico;
import espotify.logica.Fabrica;
import espotify.logica.IControlador;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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
import javax.servlet.http.Part;

@WebServlet(urlPatterns = {"/AltaAlbum"})
@MultipartConfig(
        maxFileSize = 1024 * 1024 * 20
)
public class SVAltaAlbum extends HttpServlet {
    
    private static final String UPLOAD_DIR = "../Resource/Albums/";
    
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
        String uploadPathPortadas = this.getServletContext().getRealPath("") + UPLOAD_DIR + "/Portadas";
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
    
    private List<DTTemaGenerico> crearListaDTTemas(Map<String, String> mapTemasConRutas, int cantidadTemas, HttpServletRequest request) {
        List<DTTemaGenerico> dataTemas = new ArrayList();
        for (int i=0; i<cantidadTemas; i++) {
            String parameterPrefix = "tema-" + i + "-";
            String nombreTema = request.getParameter(parameterPrefix + "nombre");
            String duracionTemaString = request.getParameter(parameterPrefix + "duracion");
            int duracionMinutos = Integer.valueOf(
                    duracionTemaString.substring(0,duracionTemaString.indexOf(":")));
            int duracionSegundos = Integer.valueOf(
                    duracionTemaString.substring(duracionTemaString.indexOf(":")+1));
            
            int duracionTotalSegundos = duracionMinutos * 60 + duracionSegundos;
            String tipoDeAcceso = request.getParameter(parameterPrefix + "tipoDeAcceso");
            String archivoOurl = request.getParameter(parameterPrefix + "urlOruta");
            String posicionString = request.getParameter(parameterPrefix + "posicion");
            int posicionTema = Integer.valueOf(posicionString);
            
            if (tipoDeAcceso.equals("ruta")) {
                String rutaTema = mapTemasConRutas.get(nombreTema);
                dataTemas.add(
                        new DTTemaConRuta(
                                rutaTema, 
                                nombreTema, 
                                duracionTotalSegundos, 
                                posicionTema));
            }
            if (tipoDeAcceso.equals("url")) {
                dataTemas.add(
                        new DTTemaConURL(
                                nombreTema, 
                                duracionTotalSegundos, 
                                posicionTema, 
                                archivoOurl));
            }
        }
        return dataTemas;
    }
    
    private List<DTGenero> crearListaDTGenero(String[] generos) {
        List<DTGenero> dataGeneros = new ArrayList();
        
        for (String gen : generos) {
            dataGeneros.add(new DTGenero(gen));
        }
        
        return dataGeneros;
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("pages/altaAlbum.jsp").forward(request, response);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Fabrica fb = Fabrica.getInstance();
        IControlador ictrl = fb.getControlador();
        
        String artista = "alcides";
        if (!ictrl.ExisteNickName(artista)) {
            System.out.println("El artista no existe.");
            response.setStatus(500);
            response.setContentType("text/plain");
            response.getWriter().write("El artista no existe.");
        }
        
        //Obtengo los datos generales del album
        String nombreAlbum = request.getParameter("nombreAlbum");
        String anioAlbumString = request.getParameter("anioAlbum");
        int anioAlbum = Integer.valueOf(anioAlbumString);
        String nombrePortada = request.getParameter("nombrePortada");
        String[] generosAlbum = request.getParameterMap().get("generos");
        String cantidadTemasString = request.getParameter("cantidadTemas");
        int cantidadTemas = Integer.valueOf(cantidadTemasString);
        
        //Extraigo los archivos
        List<Part> parts = (List<Part>) request.getParts();
        Map<String, Part> archivosDeTemas = new HashMap();
        Part partPortada = null;
        for (Part p : parts) {
            if (p.getContentType() != null) {
                String partName = p.getName();
                if (partName.startsWith("file")) {
                    String nombreTema = partName.substring(partName.indexOf("-")+1);
                    archivosDeTemas.put(nombreTema, p);
                }
                if (partName.equals("inputPortadaAlbum")) {
                    partPortada = p;
                }
            }
        }
        
        //Subo los temas recibidos en el request y creo un map con el nombre del tema y la ruta del archivo subido
        Map<String, String> mapTemasConRutas = uploadTemas(archivosDeTemas, artista, nombreAlbum);
        if (mapTemasConRutas == null) {
            System.out.println("Error al subir los temas.");
            response.setStatus(500);
            response.setContentType("text/plain");
            response.getWriter().write("Error al subir los temas.");
        }
        //Creo los DTTema con los datos del request y con las rutas de los archivos subidos en los que son con ruta
        List<DTTemaGenerico> dataTemas = crearListaDTTemas(mapTemasConRutas, cantidadTemas, request);
       
        //Subo la imagen de portada
        String rutaPortada = uploadPortada(partPortada, artista, nombreAlbum);
        if (rutaPortada == null) {
            System.out.println("Error al copiar la imagen de portada.");
            response.setStatus(500);
            response.setContentType("text/plain");
            response.getWriter().write("Error al copiar la imagen de portada.");
        }
        
        //Creo la lista de DTGenero con los generos recibidos en el request
        List<DTGenero> dataGeneros = crearListaDTGenero(generosAlbum);
        
        DTAlbum_SinDTArtista dataAlbum = new DTAlbum_SinDTArtista(
                nombreAlbum,
                anioAlbum, 
                rutaPortada,
                dataTemas,
                dataGeneros,
                artista
        );
        
        try {
            ictrl.AltaAlbum(dataAlbum);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            response.setStatus(500);
            response.setContentType("text/plain");
            response.getWriter().write("Error de alta.");
        }
        
        
        //response
        response.setContentType("text/plain");
        response.getWriter().write("OK");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}