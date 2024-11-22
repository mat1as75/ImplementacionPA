package Servlets;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import webservices.PreferenciasService;
import webservices.PreferenciasServiceService;

@WebServlet(name = "SVIncrementarReproducciones", urlPatterns = {"/SVIncrementarReproducciones"})
public class SVIncrementarReproducciones extends HttpServlet {

    private class JsonData {
        private String idTema;
        public JsonData() {};
        public String getIdTema() { return idTema; }
        public void setIdTema(String idTema) { this.idTema = idTema; }
        public Long getIdTemaLong() {
            Long id = Long.valueOf(this.idTema);
            return id;
        }
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        //Leo el body
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
        JsonData jsonObject = gson.fromJson(requestBody, JsonData.class);
        
        Long idTema = jsonObject.getIdTemaLong();
        
        PreferenciasServiceService preferenciasWS = new PreferenciasServiceService();
        PreferenciasService preferenciasPort = preferenciasWS.getPreferenciasServicePort();

        try {
            preferenciasPort.incrementarReproduccionesDeTema(idTema);
            response.setStatus(response.SC_NO_CONTENT);
        } catch (Exception e) {
            if (e.getMessage().contains("No se encontró un tema con el id") 
                    || e.getMessage().contains("El id del tema es null")) {
                response.setStatus(response.SC_NOT_FOUND);
            } else {
                response.setStatus(response.SC_INTERNAL_SERVER_ERROR);
            }
        }
    }
}
