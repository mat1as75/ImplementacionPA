package Servlets;

import com.google.gson.Gson;
import espotify.DataTypes.DTDatosListaReproduccion;
import espotify.DataTypes.DTDatosUsuario;
import espotify.logica.Fabrica;
import espotify.logica.IControlador;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "SVListasParticularesDeCliente", urlPatterns = {"/ListasParticularesDeCliente"})
public class SVListasParticularesDeCliente extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");

       
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        HttpSession sesion = request.getSession(false);
        if (sesion == null) {
            SVError.redirectForbidden(request, response);
        }
        
        DTDatosUsuario datosUsuario = (DTDatosUsuario) sesion.getAttribute("usuario");
        if (datosUsuario == null) {
            response.setContentType("text/plain");
            response.getWriter().write("Visitante");
            return;
        }
        
        Fabrica fb = Fabrica.getInstance();
        IControlador ictrl = fb.getControlador();
        String nickname = datosUsuario.getNicknameUsuario();
        Boolean existeCliente = ictrl.ExisteCliente(nickname);
        
        if (!existeCliente) {
            SVError.redirectUnauthorized(request, response);
        }
        
        List<DTDatosListaReproduccion> listaDTListasRep = new ArrayList();
        try {
            listaDTListasRep = ictrl.getListaDTDatosListaReproduccionDeCliente(nickname);
        } catch (Exception ex) {
            SVError.redirectInternalServerError(request, response, 
                    "Error al obtener las listas de reproducción del cliente.\n"
                    + ex.getMessage());
        }
                
        if (listaDTListasRep == null || listaDTListasRep.isEmpty()) {
            response.setContentType("text/plain");
            response.getWriter().write("Sin listas");
        } else {
            Gson gson = new Gson();
            String jsonData = gson.toJson(listaDTListasRep);
            response.setContentType("application/json");
            response.getWriter().write(jsonData);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
