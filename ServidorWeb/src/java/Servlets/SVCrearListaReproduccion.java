package Servlets;

import com.google.gson.JsonObject;
import espotify.DataTypes.DTDatosCliente;
import espotify.DataTypes.DTDatosUsuario;
import espotify.logica.Fabrica;
import espotify.logica.IControlador;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

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
            Fabrica fb = Fabrica.getInstance();
            IControlador control = fb.getControlador();

            DTDatosUsuario datosU = control.getDatosUsuario(nicknameSesion);
            DTDatosCliente datosC = (DTDatosCliente) datosU;
            String estadoSuscripcionSesion = null;
            if (datosC.getSuscripcion() != null) {
                estadoSuscripcionSesion = datosC.getSuscripcion().getEstadoSuscripcion();
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

                ArrayList<String> listasParticulares = control.getNombresListasParticulares();
                // Comprobar si la lista ya existe
                if (!listasParticulares.contains(nombreLista)) {
                    Date fechaCreacion = new Date();
                    boolean privada = true;

                    control.CrearListaParticular(nombreLista, rutaImagen, nicknameSesion, fechaCreacion, privada);
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
