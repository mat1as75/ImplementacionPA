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
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "SVActualizarSuscripcion", urlPatterns = {"/ActualizarSuscripcion"})
public class SVActualizarSuscripcion extends HttpServlet {
    
    //datos de la tarjeta de credito
    private class JsonData {
        private String nuevoEstadoSuscripcion;
        private String CCN;
        private String CVV;
        private Date vencimiento;
        private String propietario;
        
        public JsonData() {};
        public JsonData(String nuevoEstadoSuscripcion, String CCN, String CVV, Date vencimiento, String propietario) {
            this.nuevoEstadoSuscripcion = nuevoEstadoSuscripcion;
            this.CCN = CCN;
            this.CVV = CVV;
            this.vencimiento = vencimiento;
            this.propietario = propietario;
        }
        
        public String getNuevoEstadoSuscripcion() { return this.nuevoEstadoSuscripcion; };
        public String getCCN() { return this.CCN; };
        public String getCVV() { return this.CVV; };
        public Date getVencimiento() { return this.vencimiento; };
        public String getPropietario() { return this.propietario; };
        public void setNuevoEstadoSuscripcion(String estadoSuscripcion) { this.nuevoEstadoSuscripcion = nuevoEstadoSuscripcion; };
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
        if (!CCN.matches(".*[^0-9].*")) return false;
        if (CVV.length() != 3) return false;
        if (!CCN.matches(".*[^0-9].*")) return false;
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
        request.getRequestDispatcher("actualizarSuscripcion.jsp").forward(request, response);
    }

    /*
    Casos posibles de actualizacion:
    Validos permitidos:
        Pendiente  -->  Vigente    (Confirmada)  : requiere datos de tarjeta de credito
        Pendiente  -->  Cancelada  (Cancelada)   : no requiere datos
        Vencida    -->  Vigente    (Renovada)    : requiere datos de tarjeta de credito
    
    Validos no contemplados:
        Vencida    -->  Cancelada  (Cancelada)   : deberia ser una rutina de eliminar vencidas 
                                                   despues de cierto tiempo y no manual por el usuario
        Vigente    -->  Vencida    (Vencida)     : idem anterior
    
    No validos:
        [Estado X] --> [Estado X] : no se permite un intento de actualizacion hacia el mismo estado
        Pendiente  --> Vencida    : cambio de estado no valido segun la letra
        Cancelada  --> [Estado X] : cambio de estado no valido segun la letra
    */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesion = request.getSession(false);
        if (sesion == null) {
            setResponseToText(response, response.SC_UNAUTHORIZED);
            response.getWriter().write("Sin sesión");
            return;
        }
        
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
        
        try {
            dtSuscripcion = ictrl.getDTSuscripcionDeCliente(nickname);
        } catch (Exception e) {
            if (e instanceof NonexistentEntityException) {
                setResponseToText(response, response.SC_NOT_FOUND);
                response.getWriter().write("El usuario no existe");
                return;
            } else {
                setResponseToText(response, response.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write(e.getMessage());
                return;
            }
        }
        
        if (dtSuscripcion == null) {
            setResponseToText(response, response.SC_BAD_REQUEST);
            response.getWriter().write("El usuario no tiene una suscripción");
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
        
        String estadoPrevio = dtSuscripcion.getEstadoSuscripcion();
        if (estadoPrevio.equals(jsonObject.getNuevoEstadoSuscripcion())) {
            setResponseToText(response, response.SC_BAD_REQUEST);
            response.getWriter().write("La suscripción ya se encuentra en estado " + estadoPrevio + ".");
            return;
        }
        
        //  Pendiente --> Vigente 
        //  Vencida   --> Vigente
        if ((dtSuscripcion.getEstadoSuscripcion().equals("Pendiente") 
                && jsonObject.getNuevoEstadoSuscripcion().equals("Vigente"))
                    ||
                (dtSuscripcion.getEstadoSuscripcion().equals("Vencida") 
                && jsonObject.getNuevoEstadoSuscripcion().equals("Vigente"))) 
        {
            if (!validarDatosDeTarjeta(jsonObject)) {
                setResponseToText(response, response.SC_BAD_REQUEST);
                response.getWriter().write("Los datos de la tarjeta no son válidos.");
                return;
            } else {
                try {
                    ictrl.ActualizarEstadoSuscripcion(
                            dtSuscripcion.getIdSuscripcion(), 
                            Suscripcion.EstadoSuscripcion.Vigente, 
                            new Date()
                    );
                    response.setStatus(response.SC_NO_CONTENT);
                    return;
                } catch (Exception e) {
                    setResponseToText(response, response.SC_INTERNAL_SERVER_ERROR);
                    response.getWriter().write("Error al actualizar la suscripción.");
                    return;
                }
            }
        }
        
        // Pendiente --> Cancelada
        if (dtSuscripcion.getEstadoSuscripcion().equals("Pendiente") 
                && jsonObject.getNuevoEstadoSuscripcion().equals("Cancelada")) {
            
            try {
                ictrl.ActualizarEstadoSuscripcion(
                        dtSuscripcion.getIdSuscripcion(), 
                        Suscripcion.EstadoSuscripcion.Vigente, 
                        new Date()
                );
                response.setStatus(response.SC_NO_CONTENT);
            } catch (Exception e) {
                setResponseToText(response, response.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write("Error al actualizar la suscripción.");
            }
        } else {
            setResponseToText(response, response.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Error operación no permitida.");
        }
    }
}
