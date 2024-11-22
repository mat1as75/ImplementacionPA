package Servlets;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import webservices.DataTypes.DtArtista;
import webservices.DataTypes.DtCliente;
import webservices.UsuarioService;
import webservices.UsuarioServiceService;

@WebServlet("/SVAltaPerfil")
@MultipartConfig
public class SVAltaPerfil extends HttpServlet {
    
    private static final String UPLOAD_DIR = "../../web/Resource/ImagenesPerfil"; // Directorio donde guardar las imágenes  
    private static final String DIRECCION_BASE = "./Resource/ImagenesPerfil"; // Directorio donde guardar las imágenes
   
    private String extractFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        for (String token : contentDisposition.split(";")) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf('=') + 2, token.length() - 1);
            }
        }
        return null;
    }
    
    private XMLGregorianCalendar getXMLGregorianCalendarFromDate(Date date) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        XMLGregorianCalendar xmlDate;
        try {
            xmlDate = DatatypeFactory
                    .newInstance()
                    .newXMLGregorianCalendar(gc);
        } catch (DatatypeConfigurationException ex) {
            xmlDate = null;
        }
        return xmlDate;
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener el archivo de la solicitud
        Part filePart = request.getPart("imagen");
        String fotoPerfil = "";
        String fileName;
        if (filePart != null && filePart.getSize() > 0) {
            fileName = extractFileName(filePart);

            if (fileName != null && !fileName.isEmpty()) {
                String baseName;
                String extension;

                if (fileName.contains(".")) {
                    baseName = fileName.substring(0, fileName.lastIndexOf('.'));
                    extension = fileName.substring(fileName.lastIndexOf('.'));
                } else {
                    baseName = fileName;
                    extension = ""; // O asigna una extensión predeterminada si es necesario
                }

                // Construir la ruta de carga
                String uploadPath = getServletContext().getRealPath("") + UPLOAD_DIR;
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdir(); // Crear el directorio si no existe
                }

                // Verificar si el archivo ya existe y generar un nuevo nombre si es necesario
                File file = new File(uploadDir, fileName);
                int count = 1;

                while (file.exists()) {
                    fileName = baseName + "_" + count + extension; // Cambiar el nombre
                    file = new File(uploadDir, fileName);
                    count++;
                }

                // Guardar el archivo en el directorio
                try (InputStream input = filePart.getInputStream()) {
                    Files.copy(input, file.toPath());
                }

                
            } else {
                throw new ServletException("El nombre del archivo es inválido.");
            }
            fotoPerfil = DIRECCION_BASE + "/" + fileName;
        } else {
            fotoPerfil=null;
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
        
        UsuarioServiceService usuarioWS = new UsuarioServiceService();
        UsuarioService usuarioPort = usuarioWS.getUsuarioServicePort();
        
        if ("artista".equals(userType)) {
            DtArtista dtart = new DtArtista();
            dtart.setNickname(nickname);
            dtart.setNombreUsuario(nombre);
            dtart.setApellidoUsuario(apellido);
            dtart.setContrasenaUsuario(password);
            dtart.setEmail(email);
            dtart.setFecNac(getXMLGregorianCalendarFromDate(fechaNacimiento));
            dtart.setBiografia(biografia);
            dtart.setFotoPerfil(fotoPerfil);
            dtart.setDirSitioWeb(sitioWeb);
            usuarioPort.altaArtista(dtart);
        }
        if("cliente".equals(userType)){
            DtCliente dtcli = new DtCliente();
            dtcli.setNickname(nickname);
            dtcli.setNombreUsuario(nombre);
            dtcli.setApellidoUsuario(apellido);
            dtcli.setContrasenaUsuario(password);
            dtcli.setEmail(email);
            dtcli.setFecNac(getXMLGregorianCalendarFromDate(fechaNacimiento));
            dtcli.setFotoPerfil(fotoPerfil);             
            usuarioPort.altaCliente(dtcli);
        }
        
        //verifico que se haya creado el usuario
        Boolean fueCreado = usuarioPort.existeNickname(nickname);
        String mensaje = null;
        
        if (!fueCreado) {
            mensaje = "Ocurrió un error al crear el usuario.";
        } else {
            mensaje = "Su registro se ha completado de forma exitosa!";
        }
        // Establecer un mensaje
        request.setAttribute("mensaje", mensaje);
        
        // Redirigir al JSP
        request.getRequestDispatcher("AltaPerfil.jsp").forward(request, response);
        
    } 
}
