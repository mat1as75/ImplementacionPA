package Servlets;

import com.google.gson.Gson;
import espotify.DataTypes.DTSuscripcion;
import espotify.logica.Fabrica;
import espotify.logica.IControlador;
import espotify.logica.Suscripcion;
import espotify.persistencia.exceptions.NonexistentEntityException;
import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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

        //Un cliente sin sesion no puede acceder al sitio
        HttpSession sesion = request.getSession(false);
        if (sesion == null) {
            SVError.redirectUnauthorized(request, response);
            return;
        }

        //Un usuario no logueado no puede acceder al sitio
        String nickname = (String) sesion.getAttribute("nickname");
        if (nickname == null) {
            SVError.redirectUnauthorized(request, response);
            return;
        }

        DTSuscripcion dtSuscripcion = null;
        Fabrica fb = Fabrica.getInstance();
        IControlador ictrl = fb.getControlador();
        //Obtengo la suscripcion del cliente logueado
        try {
            dtSuscripcion = ictrl.getDTSuscripcionDeCliente(nickname);
            //El cliente esta intentando acceder a una suscripcion inexistente
            if (dtSuscripcion == null) {
                SVError.redirectNotFound(request, response);
                return;
            }
            
            if  (ictrl.actualizarSuscripcionVencida(dtSuscripcion.getIdSuscripcion())) {
                DTSuscripcion suscripcionVencida = ictrl.getDTSuscripcion(dtSuscripcion.getIdSuscripcion());
                request.setAttribute("estadoSuscripcion", suscripcionVencida.getEstadoSuscripcion());
            } else {
                request.setAttribute("estadoSuscripcion", dtSuscripcion.getEstadoSuscripcion());
            }
            
            LocalDate fechaLocalDate = LocalDate.ofInstant(
                    dtSuscripcion.getFechaSuscripcion().toInstant(), 
                    ZoneId.systemDefault());            
            request.setAttribute("tipoSuscripcion", dtSuscripcion.getTipoSuscripcion());
            request.setAttribute("fechaSuscripcion", fechaLocalDate.format(DateTimeFormatter.ISO_DATE));
            
            //Paso el estado de la suscripcion al request para manejar este valor en el JSP
        } catch (Exception e) {
            //El request recibio un cliente inexistente
            if (e instanceof NonexistentEntityException) {
                SVError.redirectNotFound(request, response);
            } else {
                //Ocurrio un error inesperado en el servidor
                SVError.redirectInternalServerError(request, response, e.getMessage());
            }
        }
        
        request.getRequestDispatcher("pages/ActualizarSuscripcion.jsp").forward(request, response);
    }

    /*
    Casos posibles de actualizacion:
    Validos permitidos:
        Pendiente  -->  Cancelada    (Cancelada)   : no requiere datos
        Vencida    -->  Pendiente    (Renovada)    : requiere datos de tarjeta de credito
    
    Validos no contemplados:
        Vencida    -->  Cancelada  (Cancelada)   : deberia ser una rutina de eliminar vencidas 
                                                   despues de cierto tiempo y no manual por el usuario
        Vigente    -->  Vencida    (Vencida)     : idem anterior
        Pendiente  -->  Vigente    (Confirmada)  : accion realizada por un administrador
    No validos:
        [Estado X] --> [Estado X] : no se permite un intento de actualizacion hacia el mismo estado
        Pendiente  --> Vencida    : cambio de estado no valido segun la letra
        Cancelada  --> [Estado X] : cambio de estado no valido segun la letra
    */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //El cliente no tiene una sesion
        HttpSession sesion = request.getSession(false);
        if (sesion == null) {
            setResponseToText(response, response.SC_UNAUTHORIZED);
            response.getWriter().write("Sin sesión");
            return;
        }
        //El cliente tiene que iniciar sesion para contratar una suscripcion
        String nickname = (String) sesion.getAttribute("nickname");
        if (nickname == null) {
            setResponseToText(response, response.SC_UNAUTHORIZED);
            response.getWriter().write("El usuario no inició sesión");
            return;
        }
        //Obtengo el usuario logueado
        DTSuscripcion dtSuscripcion = null;
        
        Fabrica fb = Fabrica.getInstance();
        IControlador ictrl = fb.getControlador();
        //Obtengo el estado de la suscripcion del usuario logueado
        try {
            dtSuscripcion = ictrl.getDTSuscripcionDeCliente(nickname);
        } catch (Exception e) {
            //El request recibio un cliente inexistente
            if (e instanceof NonexistentEntityException) {
                setResponseToText(response, response.SC_NOT_FOUND);
                response.getWriter().write("El usuario no existe");
                return;
            } else {
                //Ocurrio un error inesperado en el servidor
                setResponseToText(response, response.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write(e.getMessage());
                return;
            }
        }
        //No se puede actualizar una suscripcion inexistente
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
        //Obtengo el valor actual de la suscripcion del cliente
        String estadoPrevio = dtSuscripcion.getEstadoSuscripcion();
        //No se permite un intento de actualizacion hacia el mismo estado
        if (estadoPrevio.equals(jsonObject.getNuevoEstadoSuscripcion())) {
            setResponseToText(response, response.SC_BAD_REQUEST);
            response.getWriter().write("La suscripción ya se encuentra en estado " + estadoPrevio + ".");
            return;
        }
        
        //  Vencida --> Pendiente
        if ((dtSuscripcion.getEstadoSuscripcion().equals("Vencida") 
                && jsonObject.getNuevoEstadoSuscripcion().equals("Pendiente"))) {
            //Verifico los datos de la tarjeta
            if (!validarDatosDeTarjeta(jsonObject)) {
                setResponseToText(response, response.SC_BAD_REQUEST);
                response.getWriter().write("Tarjeta rechazada, verifique los datos ingresados.");
                return;
            } else {
                //Actualizo la suscripcion hacia el estado vigente al corroborar los datos de pago
                //Asigno la fecha de hoy
                try {
                    ictrl.ActualizarEstadoSuscripcion(
                            dtSuscripcion.getIdSuscripcion(), 
                            Suscripcion.EstadoSuscripcion.Vigente, 
                            new Date()
                    );
                    response.setStatus(response.SC_NO_CONTENT);
                    return;
                } catch (Exception e) {
                    //Ocurrio un error inesperado en el servidor
                    setResponseToText(response, response.SC_INTERNAL_SERVER_ERROR);
                    response.getWriter().write("Error al actualizar la suscripción.");
                    return;
                }
            }
        }
        
        // Pendiente --> Cancelada
        if (dtSuscripcion.getEstadoSuscripcion().equals("Pendiente") 
                && jsonObject.getNuevoEstadoSuscripcion().equals("Cancelada")) {
            //Si el cliente quiere cancelar la suscripcion
            try {
                ictrl.ActualizarEstadoSuscripcion(
                        dtSuscripcion.getIdSuscripcion(), 
                        Suscripcion.EstadoSuscripcion.Cancelada, 
                        new Date()
                );
                response.setStatus(response.SC_NO_CONTENT);
            } catch (Exception e) {
                //Ocurrio un error inesperado en el servidor
                setResponseToText(response, response.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write("Error al actualizar la suscripción.");
            }
        } else {
            //Captura el resto de los casos
            setResponseToText(response, response.SC_FORBIDDEN);
            response.getWriter().write("Error. Operación no permitida.");
        }
    }
}
