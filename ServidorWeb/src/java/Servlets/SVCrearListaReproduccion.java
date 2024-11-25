package Servlets;

import com.google.gson.JsonObject;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import webservices.DataTypes.DtUsuarioGenerico;
import webservices.ListaReproduccionService;
import webservices.ListaReproduccionServiceService;
import webservices.UsuarioService;
import webservices.UsuarioServiceService;

@MultipartConfig
@WebServlet(name = "SVCrearListaReproduccion", urlPatterns = {"/SVCrearListaReproduccion"})
public class SVCrearListaReproduccion extends HttpServlet {

    private String extractFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        for (String token : contentDisposition.split(";")) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf('=') + 2, token.length() - 1);
            }
        }
        return null;
    }

    private static final String UPLOAD_DIR = "../../web/Resource/ImagenesPerfil";
    private static final String DIRECCION_BASE = "./Resource/ImagenesPerfil";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession sesion = request.getSession();
        String nicknameSesion = (String) sesion.getAttribute("nickname");
        String rolSesion = (String) sesion.getAttribute("rol");

        JsonObject jsonResponse = new JsonObject();

        // Comprobar si es un cliente
        if ("Cliente".equals(rolSesion)) {
            UsuarioServiceService usuarioWS = new UsuarioServiceService();
            UsuarioService usuarioPort = usuarioWS.getUsuarioServicePort();
            ListaReproduccionServiceService listaRepWS = new ListaReproduccionServiceService();
            ListaReproduccionService listaRepPort = listaRepWS.getListaReproduccionServicePort();

            DtUsuarioGenerico datosU = usuarioPort.getDatosUsuario(nicknameSesion).getDtUsuarioGenerico();
            
            String estadoSuscripcionSesion = null;
            if (datosU.getSuscripcion() != null) {
                estadoSuscripcionSesion = datosU.getSuscripcion().getEstadoSuscripcion();
            }

            // Comprobar suscripcion vigente
            if (estadoSuscripcionSesion != null && estadoSuscripcionSesion.equals("Vigente")) {
                String nombreLista = request.getParameter("nombreLista");
                Part filePart = request.getPart("imagenLista");
                String rutaImagen = null;  

                // Comprobar si se ha seleccionado un archivo
                if (filePart != null && filePart.getSize() > 0) {
                    String fileName = extractFileName(filePart);
                    // Construir la ruta de carga
                    String uploadPath = getServletContext().getRealPath("") + UPLOAD_DIR;
                    File uploadDir = new File(uploadPath);
                    if (!uploadDir.exists()) {
                        uploadDir.mkdir();
                    }

                    // Comprobar si el archivo ya existe y generar un nuevo nombre si es necesario
                    File file = new File(uploadDir, fileName);
                    int count = 1;

                    // Comprobar si el archivo tiene una extension
                    int dotIndex = fileName.lastIndexOf('.');
                    String baseName;
                    String extension;
                    if (dotIndex > 0) {
                        baseName = fileName.substring(0, dotIndex);
                        extension = fileName.substring(dotIndex);
                    } else {
                        baseName = fileName;
                        extension = "";
                    }

                    // Cambiar el nombre si el archivo ya existe
                    while (file.exists()) {
                        fileName = baseName + "_" + count + extension;
                        file = new File(uploadDir, fileName);
                        count++;
                    }

                    // Guardar el archivo en el directorio
                    try (InputStream input = filePart.getInputStream()) {
                        Files.copy(input, file.toPath());
                    }
                    rutaImagen = DIRECCION_BASE + "/" + fileName; // Asignar la ruta de la imagen
                }

                List<Object> listasParticularesObjs = listaRepPort.getNombresListasParticulares().getColeccion();
                List<String> listasParticulares = new ArrayList();
                for (Object o : listasParticularesObjs) {
                    listasParticulares.add((String) o);
                }
                // Comprobar si la lista ya existe
                if (!listasParticulares.contains(nombreLista)) {
                    Date fechaCreacion = new Date();
                    XMLGregorianCalendar xmlDate = null;
                    GregorianCalendar gc = new GregorianCalendar();
                    gc.setTime(new Date());
                    try {
                        xmlDate =  DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
                    } catch (DatatypeConfigurationException ex) {
                        Logger.getLogger(ServletPrueba.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    boolean privada = true;

                    listaRepPort.crearListaParticular(nombreLista, rutaImagen, nicknameSesion, xmlDate, privada);
                    jsonResponse.addProperty("Exito", "Lista de reproducción creada exitosamente");
                } else {
                    jsonResponse.addProperty("Error", "La lista de reproducción con el nombre " + nombreLista + " ya existe.");
                }
            } else {
                // Suscripcion no vigente
                jsonResponse.addProperty("Error", "No puedes crear una lista de reproducción. Tu suscripción no se encuentra vigente.");
            }
        } else {
            // No es un cliente
            jsonResponse.addProperty("Error", "Debes ser un Cliente para poder crear listas de reproducción.");
        }
        response.getWriter().write(jsonResponse.toString());
    }

    @Override
    public String getServletInfo() {
        return "Servlet para crear lista de reproducción";
    }
}
