package Servlets;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import webservices.ContenidoService;
import webservices.ContenidoServiceService;

@WebServlet(name = "SVQuitarTemaDeLista", urlPatterns = {"/QuitarTemaDeLista"})
public class SVQuitarTemaDeLista extends HttpServlet {

    //Clase que mapea los datos recibidos en el body
    class JsonData {
        private String nombreListaReproduccion;
        private Long idTema;
        JsonData() {};
        JsonData(String nombreListaReproduccion, Long idTema) {
            this.nombreListaReproduccion = nombreListaReproduccion;
            this.idTema = idTema;
        }
        public String getNombreListaReproduccion() {
            return nombreListaReproduccion;
        }
        public void setNombreListaReproduccion(String nombreListaReproduccion) {
            this.nombreListaReproduccion = nombreListaReproduccion;
        }
        public Long getIdTema() { return idTema; }
        public void setIdTema(Long idTema) { this.idTema = idTema; }
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        ContenidoServiceService contenidoWS = new ContenidoServiceService();
        ContenidoService contenidoPort = contenidoWS.getContenidoServicePort();
        
        //Extraigo el body del request en forma de string
        BufferedReader reader = request.getReader();
        StringBuilder body = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            body.append(line);  
        }
        reader.close();

        //Convierto el body a String
        String requestBody = body.toString();

        //Parse del body en formato Json
        //Creo un objeto json con los datos obtenidos
        Gson gson = new Gson();
        SVQuitarTemaDeLista.JsonData jsonObject = gson.fromJson(requestBody, SVQuitarTemaDeLista.JsonData.class);

        //Extraigo los datos del objeto
        String nombreLista = jsonObject.getNombreListaReproduccion();
        Long idTema = jsonObject.getIdTema();

        try {
            contenidoPort.quitarTemaDeLista(idTema, nombreLista);
            response.setStatus(response.SC_NO_CONTENT);
        } catch (Exception e) {
            response.setStatus(response.SC_INTERNAL_SERVER_ERROR);
            if (e.getMessage().contains("No se pudo encontrar el tema")) {
                response.setStatus(response.SC_NOT_FOUND);
            }
            
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("Error: " + e.getMessage());
        }
    }
}
