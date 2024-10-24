package Servlets;

import com.google.gson.Gson;
import espotify.DataTypes.DTDatosUsuario;
import espotify.DataTypes.DTSuscripcion;
import espotify.logica.Fabrica;
import espotify.logica.IControlador;
import espotify.logica.Suscripcion;
import espotify.persistencia.exceptions.NonexistentEntityException;
import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "SVIngresarSuscripcion", urlPatterns = {"/IngresarSuscripcion"})
public class SVIngresarSuscripcion extends HttpServlet {

    class JsonData {
        private String tipoSuscripcion;
        public JsonData(){};
        public JsonData(String tipoSuscripcion) { this.tipoSuscripcion = tipoSuscripcion; };
        public String getTipoSuscripcion() { return this.tipoSuscripcion; }
        public void setTipoSuscripcion(String tipoSuscripcion) { this.tipoSuscripcion = tipoSuscripcion; }
    }
    
    private void setResponseToText(HttpServletResponse response, int httpCode) {
        response.setStatus(httpCode);
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("IngresarNuevaSuscripcion.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //El cliente no tiene sesion
        HttpSession sesion = request.getSession(false);
        if (sesion == null) {
            setResponseToText(response, response.SC_UNAUTHORIZED);
            response.getWriter().write("Sin sesión");
            return;
        }
        //No se permite el ingreso de una suscripcion a un usuario no logueado
        DTDatosUsuario datosUsuario = (DTDatosUsuario) sesion.getAttribute("usuario");
        if (datosUsuario == null) {
            setResponseToText(response, response.SC_UNAUTHORIZED);
            response.getWriter().write("El usuario no inició sesión");
            return;
        }
        
        String nickname = datosUsuario.getNicknameUsuario();        
        DTSuscripcion dtSuscripcion = null;
        
        Fabrica fb = Fabrica.getInstance();
        IControlador ictrl = fb.getControlador();
        //Verifico si el cliente existe y ya tiene una suscripcion
        try {
            //Obtengo la suscripcion si la tiene
            dtSuscripcion = ictrl.getDTSuscripcionDeCliente(nickname);
        } catch (Exception e) {
            //El request recibio un usuario inexistente
            if (e instanceof NonexistentEntityException) {
                setResponseToText(response, response.SC_NOT_FOUND);
                response.getWriter().write("El usuario no existe o no es un cliente");
                return;
            } else {
                //Ocurrio un error inesperado en el servidor
                setResponseToText(response, response.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write(e.getMessage());
                return;
            }
        }
        //Si el usuario logueado ya tiene una suscripcion no puede crear una nueva
        if (dtSuscripcion != null) {
            setResponseToText(response, response.SC_BAD_REQUEST);
            response.getWriter().write("El usuario ya tiene una suscripción");
            return;
        }
     
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
        JsonData jsonObject = gson.fromJson(requestBody, JsonData.class);

        //Extraigo el tipo de suscripcion
        String tipoSusc = jsonObject.getTipoSuscripcion();
        Suscripcion.TipoSuscripcion tipoSuscripcion = null;
        //Asigno el valor del tipo de suscripcion segun lo recibido en el request
        switch (tipoSusc) {
            case "Anual" -> tipoSuscripcion = Suscripcion.TipoSuscripcion.Anual;
            case "Mensual" -> tipoSuscripcion = Suscripcion.TipoSuscripcion.Mensual;
            case "Semanal" -> tipoSuscripcion = Suscripcion.TipoSuscripcion.Semanal;
        }
        
        //El request recibio un tipo de suscripcion invalido
        if (tipoSuscripcion == null) {
            setResponseToText(response, response.SC_BAD_REQUEST);
            response.getWriter().write("Tipo de suscripción inválida");
            return;
        }
        //Ingreso la nueva suscripcion        
        try {
            ictrl.ingresarNuevaSuscripcion(nickname, tipoSuscripcion);
            response.setStatus(response.SC_NO_CONTENT);
        } catch (Exception e) {
            //Ocurrio un error inesperado en el servidor
            this.setResponseToText(response, response.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Error: " + e.getMessage());
        }
    }
}
