package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import webservices.PreferenciasService;
import webservices.PreferenciasServiceService;

@WebServlet("/SVPublicarLista")
public class SVPublicarLista extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesion = request.getSession();
        String nicknameCliente = (String) sesion.getAttribute("nickname");
        // Obtener el valor de la lista seleccionada
        String listaSeleccionada = request.getParameter("listaOculta");

        PreferenciasServiceService preferenciasWS = new PreferenciasServiceService();
        PreferenciasService preferenciasPort = preferenciasWS.getPreferenciasServicePort();
        
        if ((nicknameCliente != null) &&(listaSeleccionada != null)) {
            preferenciasPort.publicarLista(nicknameCliente, listaSeleccionada);
            // Establecer un mensaje
            String mensaje = "Lista '" + listaSeleccionada + "' publicada exitosamente";
            request.setAttribute("mensaje", mensaje);
            request.getRequestDispatcher("PublicarLista.jsp").forward(request, response);
        }
        
    }   

}
