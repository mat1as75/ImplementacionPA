package Servlets;

import com.google.gson.Gson;
import espotify.logica.Fabrica;
import espotify.logica.IControlador;
import espotify.persistencia.exceptions.DatabaseUpdateException;
import espotify.persistencia.exceptions.InvalidDataException;
import espotify.persistencia.exceptions.NonexistentEntityException;
import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

        public Long getIdTema() {
            return idTema;
        }

        public void setIdTema(Long idTema) {
            this.idTema = idTema;
        }
        
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
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
        
        Fabrica fb = Fabrica.getInstance();
        IControlador ictrl = fb.getControlador();
        
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
            ictrl.quitarTemaDeLista(idTema, nombreLista);
            response.setStatus(response.SC_NO_CONTENT);
        } catch (Exception e) {
            response.setStatus(response.SC_INTERNAL_SERVER_ERROR);
            if (e instanceof NonexistentEntityException) {
                response.setStatus(response.SC_NOT_FOUND);
            }
            if (e instanceof DatabaseUpdateException) {
                response.setStatus(response.SC_INTERNAL_SERVER_ERROR);
            }
            if (e instanceof InvalidDataException) {
                response.setStatus(response.SC_BAD_REQUEST);
            }
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("Error: " + e.getMessage());
        }
    }
}
