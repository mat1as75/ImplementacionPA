package Servlets;

import com.google.gson.JsonObject;
import espotify.logica.Fabrica;
import espotify.logica.IControlador;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession sesion = request.getSession();
        //String nicknameSesion = (String) sesion.getAttribute("nickname");
        //String rolSesion = (String) sesion.getAttribute("rol");
        
        String nicknameSesion = "cbochinche";
        String rolSesion = "Cliente";
       
        // Comprobar si es un cliente
        if (rolSesion.equals("Cliente")) {
            Fabrica fb = Fabrica.getInstance();
            IControlador control = fb.getControlador();
            
            //DTDatosUsuario datosU = control.getDatosUsuario(nicknameSesion); 
            //String estadoSuscripcionSesion = datosU.getDTSuscripcion().getEstadoSuscripcion();
            String estadoSuscripcionSesion = "Vigente"; //Momentaneo para testear
            // Comprobar si la suscripciun esta vigente
            if (estadoSuscripcionSesion.equals("Vigente")) {
                String nombreLista = request.getParameter("nombreLista");
                String rutaImagen = null;

                // Manejo del archivo de imagen
                Part filePart = request.getPart("imagenLista");
                if (filePart != null && filePart.getSize() > 0) {
                    String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                    String uploadsDir = getServletContext().getRealPath("") + File.separator + "uploads";
                    File uploadsFolder = new File(uploadsDir);
                    if (!uploadsFolder.exists()) {
                        uploadsFolder.mkdirs();
                    }

                    File file = new File(uploadsFolder, fileName);
                    try (InputStream input = filePart.getInputStream()) {
                        Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
                        rutaImagen = file.getAbsolutePath();
                    }
                }

                
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