package Servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SVRedirectedLinkBitly", urlPatterns = {"/SVRedirectedLinkBitly"})
public class SVRedirectedLinkBitly extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    public String getRedirectedLink(String link) {
        String linkConProtocolo = "https://" + link;
        URL url;
        HttpURLConnection urlConnection;
        try {
            url = new URL(linkConProtocolo);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setInstanceFollowRedirects(false);
            final String location = urlConnection.getHeaderField("location");
            return location;
        } catch (Exception ex) {
            return null;
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        
        String receivedUrl;
        
        BufferedReader reader = request.getReader();
        StringBuilder body = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            body.append(line);  
        }
        reader.close();

        receivedUrl = body.toString();
        
        String redirectedUrl = null;
        
        if (!receivedUrl.contains("bit.ly")) {
            response.setStatus(response.SC_BAD_REQUEST);
            response.getWriter().write("No se puede procesar el url recibido");
        } else {
            redirectedUrl = this.getRedirectedLink(receivedUrl);
            if (redirectedUrl == null) {
                response.setStatus(response.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write("No se puede procesar el url recibido");
            } else {
                response.setStatus(response.SC_OK);
                response.getWriter().write(redirectedUrl);
            }
        }
    }
}
