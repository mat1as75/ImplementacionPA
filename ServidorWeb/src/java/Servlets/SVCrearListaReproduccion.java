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

        HttpSession sesion = request.getSession();
        String nicknameSesion = (String) sesion.getAttribute("nickname");
        System.out.println("nickname" + nicknameSesion);
        String rolSesion = (String) sesion.getAttribute("rol");
        
        // Comprobar si es un cliente
        if (rolSesion.equals("Cliente")) {
            Fabrica fb = Fabrica.getInstance();
            IControlador control = fb.getControlador();
            
            DTDatosUsuario datosU = control.getDatosUsuario(nicknameSesion);
            DTDatosCliente datosC = (DTDatosCliente) datosU;
            String estadoSuscripcionSesion = null;
            if( datosC.getSuscripcion() != null){
                estadoSuscripcionSesion = ((DTDatosCliente)datosU).getSuscripcion().getEstadoSuscripcion();
            }
            // Comprobar si la suscripcion esta vigente
            if (estadoSuscripcionSesion != null && estadoSuscripcionSesion.equals("Vigente")) {
                String nombreLista = request.getParameter("nombreLista");
                 
                // Obtener el archivo de la solicitud
                Part filePart = request.getPart("imagenLista");

                // Obtener el nombre del archivo 
                String fileName = extractFileName(filePart);

                // Construir la ruta de carga usando el contexto 
                String uploadPath = getServletContext().getRealPath("") + UPLOAD_DIR;
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdir(); // Crear el directorio si no existe
                }

                // Comprobar si el archivo ya existe y generar un nuevo nombre si es necesario
                File file = new File(uploadDir, fileName);
                int count = 1;

                // Comprobar si el archivo tiene una extensión
                int dotIndex = fileName.lastIndexOf('.');
                String baseName;
                String extension;

                if (dotIndex > 0) {
                    // Si hay un punto, separar el nombre base y la extension
                    baseName = fileName.substring(0, dotIndex);
                    extension = fileName.substring(dotIndex);
                } else {
                    // Si no hay extension, usa el nombre completo como base y deja la extension vacia
                    baseName = fileName;
                    extension = "";
                }

                // Cambiar el nombre si el archivo ya existe
                while (file.exists()) {
                    fileName = baseName + "_" + count + extension; // Cambiar el nombre
                    file = new File(uploadDir, fileName);
                    count++;
                }

                // Guardar el archivo en el directorio
                try (InputStream input = filePart.getInputStream()) {
                    Files.copy(input, file.toPath());
                }

                String rutaImagen = DIRECCION_BASE + "/" + fileName;

                
                ArrayList<String> listasParticulares = new ArrayList<>();
                listasParticulares = control.getNombresListasParticulares();
                
                // Comprobar si la lista ya existe
                if (!listasParticulares.contains(nombreLista)) {
                    Date fechaCreacion = new Date();
                    boolean privada = true;

                    try {
                        control.CrearListaParticular(nombreLista, rutaImagen, nicknameSesion, fechaCreacion, privada);
                        JsonObject jsonResponse = new JsonObject();
                        jsonResponse.addProperty("Exito", "Lista de reproducción creada exitosamente");
                        response.getWriter().write(jsonResponse.toString());
                    } catch (IOException e) {
                        JsonObject jsonResponse = new JsonObject();
                        jsonResponse.addProperty("Error", "Error al crear la lista de reproducción: " + e.getMessage());
                        response.getWriter().write(jsonResponse.toString());
                    }
                  // Nombre de la lista ya existe
                } else {
                    JsonObject jsonResponse = new JsonObject();
                    jsonResponse.addProperty("Error", "La lista de reproducción con el nombre " + nombreLista + " ya existe.");
                    response.getWriter().write(jsonResponse.toString());
                }
            } else {
                // Suscripcion no vigente
                JsonObject jsonResponse = new JsonObject();
                jsonResponse.addProperty("Error", "No puedes crear una lista de reproducción. Tu suscripción no se encuentra vigente.");
                response.getWriter().write(jsonResponse.toString());
            }
        } else {
            // No es un cliente
            JsonObject jsonResponse = new JsonObject();
            jsonResponse.addProperty("Error", "Debes ser un Cliente para poder crear listas de reproducción.");
            response.getWriter().write(jsonResponse.toString());
        }
    }

    @Override
    public String getServletInfo() {
        return "Sv CrearListaReproduccion";
    }
}