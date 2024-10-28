package Servlets;

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
      private String extractFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        for (String token : contentDisposition.split(";")) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf('=') + 2, token.length() - 1);
            }
        }
        return null;
    }
    private static final String UPLOAD_DIR = "../../web/Resource/ImagenesPerfil"; // Directorio donde guardar las imágenes  
    private static final String DIRECCION_BASE = "./Resource/ImagenesPerfil"; // Directorio donde guardar las imágenes
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener el archivo de la solicitud
        Part filePart = request.getPart("imagen");

        // Obtener el nombre del archivo desde el header "content-disposition"
        String fileName = extractFileName(filePart);

        // Construir la ruta de carga usando el contexto de la aplicación
        
        ///home/usuario/Documentos/GitHub/ImplementacionPA/ServidorWeb/web/index.jsp
        String uploadPath =getServletContext().getRealPath("")+UPLOAD_DIR;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir(); // Crear el directorio si no existe
        }

        // Verificar si el archivo ya existe y generar un nuevo nombre si es necesario
        File file = new File(uploadDir, fileName);
        int count = 1;
        String baseName = fileName.substring(0, fileName.lastIndexOf('.'));
        String extension = fileName.substring(fileName.lastIndexOf('.'));

        while (file.exists()) {
            fileName = baseName + "_" + count + extension; // Cambiar el nombre
            file = new File(uploadDir, fileName);
            count++;
        }

        // Guardar el archivo en el directorio
        try (InputStream input = filePart.getInputStream()) {
            Files.copy(input, file.toPath());
        }

        String fotoPerfil = DIRECCION_BASE+"/"+fileName;
    
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
        request.getRequestDispatcher("AltaPerfil.jsp").forward(request, response);
        
    } 
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
