package Servlets;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import webservices.DataTypes.DtSuscripcion;
import webservices.SuscripcionesService;
import webservices.SuscripcionesServiceService;
import webservices.TipoSuscripcion;

@WebServlet(name = "SVIngresarSuscripcion", urlPatterns = {"/IngresarSuscripcion"})
public class SVIngresarSuscripcion extends HttpServlet {

    //datos de la suscripcion y tarjeta de credito
    private class JsonData {
        private String tipoSuscripcion;
        private String nuevoEstadoSuscripcion;
        private String CCN;
        private String CVV;
        private Date vencimiento;
        private String propietario;
        
        public JsonData() {};
        public JsonData(
                String tipoSuscripcion, 
                String nuevoEstadoSuscripcion, 
                String CCN, String CVV, 
                Date vencimiento, 
                String propietario) {
            this.tipoSuscripcion = tipoSuscripcion;
            this.nuevoEstadoSuscripcion = nuevoEstadoSuscripcion;
            this.CCN = CCN;
            this.CVV = CVV;
            this.vencimiento = vencimiento;
            this.propietario = propietario;
        }
        public String getTipoSuscripcion() { return this.tipoSuscripcion; }
        public String getNuevoEstadoSuscripcion() { return this.nuevoEstadoSuscripcion; };
        public String getCCN() { return this.CCN; };
        public String getCVV() { return this.CVV; };
        public Date getVencimiento() { return this.vencimiento; };
        public String getPropietario() { return this.propietario; };
        public void setTipoSuscripcion(String tipoSuscripcion) { this.tipoSuscripcion = tipoSuscripcion; }
        public void setNuevoEstadoSuscripcion(String nuevoEstadoSuscripcion) { this.nuevoEstadoSuscripcion = nuevoEstadoSuscripcion; };
        public void setCCN(String CCN) { this.CCN = CCN; };
        public void setCVV(String CVV) { this.CVV = CVV; };
        public void setVencimiento(Date vencimiento) { this.vencimiento = vencimiento; };
        public void setPropietario(String propietario) { this.propietario = propietario; };
    }
    
    private Boolean validarDatosDeTarjeta(JsonData data) {
        String CCN = data.getCCN();
        String CVV = data.getCVV();
        Date vencimiento = data.getVencimiento();
        String propietario = data.getPropietario();
        
        if (CCN.length() != 16) return false;
        if (CCN.matches(".*[^0-9].*")) return false;
        if (CVV.length() != 3) return false;
        if (CCN.matches(".*[^0-9].*")) return false;
        if (vencimiento.getTime() < new Date().getTime()) return false;
        if (propietario == null || propietario.isBlank() || propietario.isEmpty()) return false;
        
        return true;
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
        request.getRequestDispatcher("pages/IngresarNuevaSuscripcion.jsp").forward(request, response);
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
        String nickname = (String) sesion.getAttribute("nickname");
        if (nickname == null) {
            setResponseToText(response, response.SC_UNAUTHORIZED);
            response.getWriter().write("El usuario no inició sesión");
            return;
        }
        
        DtSuscripcion dtSuscripcion = null;
        SuscripcionesServiceService suscripcionesWS = new SuscripcionesServiceService();
        SuscripcionesService suscripcionesPort = suscripcionesWS.getSuscripcionesServicePort();
        
        //Verifico si el cliente existe y ya tiene una suscripcion
        try {
            //Obtengo la suscripcion si la tiene
            dtSuscripcion = suscripcionesPort.getDTSuscripcionDeCliente(nickname).getDtSuscripcion();
        } catch (Exception e) {
            //El request recibio un usuario inexistente
            if (e.getMessage().contains("No se encontró el cliente")) {
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
        TipoSuscripcion tipoSuscripcion = null;
        //Asigno el valor del tipo de suscripcion segun lo recibido en el request
        switch (tipoSusc) {
            case "Anual" -> tipoSuscripcion = TipoSuscripcion.ANUAL;
            case "Mensual" -> tipoSuscripcion = TipoSuscripcion.MENSUAL;
            case "Semanal" -> tipoSuscripcion = TipoSuscripcion.SEMANAL;
        }
        
        //El request recibio un tipo de suscripcion invalido
        if (tipoSuscripcion == null) {
            setResponseToText(response, response.SC_BAD_REQUEST);
            response.getWriter().write("Tipo de suscripción inválida");
            return;
        }
        
        if (!validarDatosDeTarjeta(jsonObject)) {
            setResponseToText(response, response.SC_BAD_REQUEST);
            response.getWriter().write("Tarjeta Rechazada, verifique los datos ingresados.");
            return;
        }

        //Ingreso la nueva suscripcion        
        try {
            suscripcionesPort.ingresarNuevaSuscripcion(nickname, tipoSuscripcion);
            response.setStatus(response.SC_NO_CONTENT);
        } catch (Exception e) {
            //Ocurrio un error inesperado en el servidor
            this.setResponseToText(response, response.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Error: " + e.getMessage());
        }
        
        response.setStatus(response.SC_NO_CONTENT);

    }
}
