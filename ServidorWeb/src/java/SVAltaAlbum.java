import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
    
    private static final String UPLOAD_DIR = "../../Resource/Albums/";

    private String extractFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        for (String token : contentDisposition.split(";")) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf('=') + 2, token.length() - 1);
            }
        }
        return null;
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
        
        Part dataAlbum = request.getPart("dataAlbum");
        List<Part> parts = (List<Part>) request.getParts();
        for (Part p : parts) {
            //System.out.println(p.getName() + " - " + request.getPart(p.getName()) + " - filename: " + extractFileName(p));
        }
        
        //read the part
        InputStream inputStream = dataAlbum.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        //System.out.println(sb.toString());
        
        Map<String, String[]> parameters = request.getParameterMap();
        
        for (Entry<String, String[]> entry : parameters.entrySet()) {
            System.out.println("entry: "+entry.getKey());
            for (String s : entry.getValue()) {
                System.out.println("value: " + s);
            }
        }
        System.out.println("--------------------------------------------");
        System.out.println("nombre: " + request.getParameter("nombreAlbum"));
        System.out.println("anio: " + request.getParameter("anioAlbum"));
        System.out.println("generos: " + request.getParameter("generos"));
        
        //set it to the response
        response.setContentType("text/plain");
        response.getWriter().write(sb.toString());
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
