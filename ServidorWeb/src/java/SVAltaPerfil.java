import espotify.DataTypes.DTArtista;
import espotify.DataTypes.DTCliente;
import espotify.logica.Fabrica;
import espotify.logica.IControlador;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import com.google.gson.JsonObject;
import com.google.gson.Gson;

@WebServlet("/SVAltaPerfil")
@MultipartConfig
public class SVAltaPerfil extends HttpServlet {
    // Carpeta de destino donde se guardarán las imágenes
    private static final String DESTINO_CARPETA = "/Resource/ImagenesPerfil/";
    
 protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet NewServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet NewServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtenemos el archivo del formulario
        Part filePart = request.getPart("imagen"); // "imagen" es el nombre del campo en el formulario HTML
        String fotoPerfil="";
        if (filePart != null && filePart.getSize() > 0) {
            // Nombre del archivo
            String header = filePart.getHeader("content-disposition");
            String nombreArchivo = header.substring(header.indexOf("filename=\"") + 10, header.lastIndexOf("\""));
            // Obtener la ruta relativa a la carpeta raíz de la aplicación web
            String destinoCarpetaPath = getServletContext().getRealPath(DESTINO_CARPETA); // Define una carpeta "Resource" dentro de la aplicación
            File destinoCarpeta = new File(destinoCarpetaPath);

            // Crear carpeta si no existe
            if (!destinoCarpeta.exists()) {
                try {
                    boolean creada = destinoCarpeta.mkdirs(); // Intentar crear la carpeta
                    if (!creada) {
                        response.getWriter().println("Error: no se pudo crear la carpeta. Verifica los permisos.");
                        return; // Salir si no se pudo crear la carpeta
                    }
                } catch (Exception e) {
                    response.getWriter().println("Excepción al intentar crear la carpeta: " + e.getMessage());
                    return; // Salir si hay una excepción
                }
            }

            // Ruta donde se guardará el archivo
            File archivoDestino = new File(destinoCarpeta, nombreArchivo);

            // Guardar el archivo
            try (InputStream input = filePart.getInputStream()) {
                Files.copy(input, archivoDestino.toPath(), StandardCopyOption.REPLACE_EXISTING);      
                fotoPerfil=archivoDestino.toPath().toString();
            } catch (Exception e) {
                response.getWriter().println("Error al guardar el archivo: " + e.getMessage());
                return; // Salir si hay un error al guardar el archivo
            }
        } else {
            System.out.println("Error al recibir la imagen.");
        }
        String fechaNacimientoStr = request.getParameter("fechaNacimiento");
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaNacimiento = null;
        try {
            fechaNacimiento = formato.parse(fechaNacimientoStr);
        } catch (ParseException ex) {
            Logger.getLogger(SVAltaPerfil.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Obtener los parámetros del formulario
        String nickname = request.getParameter("nickname");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String userType = request.getParameter("userType");
        String biografia="";
        String sitioWeb="";
        if ("artista".equals(userType)) {
            biografia = request.getParameter("biografia");
            sitioWeb = request.getParameter("sitioWeb");
        }
        
        Fabrica f=Fabrica.getInstance();
        IControlador i=f.getControlador();
        if ("artista".equals(userType)) {
            DTArtista dtart=new DTArtista(nickname, nombre,apellido,password,email,fechaNacimiento,fotoPerfil,null, biografia,sitioWeb,null);
            i.AltaArtista(dtart);
        }
        if("cliente".equals(userType)){
            DTCliente dtcli=new DTCliente(nickname,nombre,apellido,password,email,fechaNacimiento,fotoPerfil);                
            i.AltaCliente(dtcli);
        }
        // Establecer un mensaje
        String mensaje = "Felicidades te logueaste en nuestra pagina.";
        request.setAttribute("mensaje", mensaje);
        
        // Redirigir al JSP
        request.getRequestDispatcher("index.jsp").forward(request, response);
        
    } 
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
