package Servlets;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import webservices.DataTypes.DtDatosListaReproduccion;
import webservices.ListaReproduccionService;
import webservices.ListaReproduccionServiceService;
import webservices.UsuarioService;
import webservices.UsuarioServiceService;

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
        
        String nickname = (String) sesion.getAttribute("nickname");
        if (nickname == null) {
            response.setContentType("text/plain");
            response.getWriter().write("Visitante");
            return;
        }
        
        ListaReproduccionServiceService listaRepWS = new ListaReproduccionServiceService();
        ListaReproduccionService listaRepPort = listaRepWS.getListaReproduccionServicePort();
        UsuarioServiceService usuarioWS = new UsuarioServiceService();
        UsuarioService usuarioPort = usuarioWS.getUsuarioServicePort();
        
        Boolean existeCliente = usuarioPort.existeCliente(nickname);
        
        if (!existeCliente) {
            SVError.redirectUnauthorized(request, response);
        }
        
        List<Object> objList = new ArrayList();
        List<DtDatosListaReproduccion> listaDtListasRep = new ArrayList();
        try {
            objList = listaRepPort.getListaDTDatosListaReproduccionDeCliente(nickname).getColeccion();
        } catch (Exception ex) {
            SVError.redirectInternalServerError(request, response, 
                    "Error al obtener las listas de reproducción del cliente.\n"
                    + ex.getMessage());
        }
                
        if (objList == null || objList.isEmpty()) {
            response.setContentType("text/plain");
            response.getWriter().write("Sin listas");
        } else {
            
            for (Object o : objList) {
                DtDatosListaReproduccion dtLista = (DtDatosListaReproduccion) o;
                listaDtListasRep.add(dtLista);
                /**
                 * Si la lista no tiene temas agregados el datatype viene con el atributo
                 * temas en null. Si esta en null en lugar de mapearse al json como un array vacio
                 * directamente no se agrega el campo, por lo que no llega un objeto valido a la 
                 * funcion de javascript que utiliza los datos.
                 * 
                 * Al hacer un get de la lista, se ejecuta la construccion del ArrayList vacio, 
                 * lo que hace que gson si reconozca el valor del atributo como un array y agregue el
                 * campo al json
                 */
                if (dtLista.getTemas() == null) {
                    dtLista.getTemas();
                }
            }
            
            Gson gson = new Gson();
            String jsonData = gson.toJson(listaDtListasRep);
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
