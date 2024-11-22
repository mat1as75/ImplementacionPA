package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import webservices.PreferenciasService;
import webservices.PreferenciasServiceService;

@WebServlet(name = "SVDejarSeguirUsuario", urlPatterns = {"/SVDejarSeguirUsuario"})
public class SVDejarSeguirUsuario extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String nicknameSeguidor = request.getParameter("nicknameSeguidor");
        String nicknameSeguido = request.getParameter("nicknameSeguido");

        PreferenciasServiceService preferenciasWS = new PreferenciasServiceService();
        PreferenciasService preferenciasPort = preferenciasWS.getPreferenciasServicePort();
        
        try {
            preferenciasPort.dejarDeSeguir(nicknameSeguidor, nicknameSeguido);
        } catch (Exception ex) {
            throw ex;
        }
        
        response.sendRedirect("ConsultaPerfilUsuario.jsp");
        
    }

    @Override
    public String getServletInfo() {
        return "Servlet para dejar de seguir usuario";
    }

}
