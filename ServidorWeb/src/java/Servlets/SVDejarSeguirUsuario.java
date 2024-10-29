package Servlets;

import espotify.logica.Fabrica;
import espotify.logica.IControlador;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SVDejarSeguirUsuario", urlPatterns = {"/SVDejarSeguirUsuario"})
public class SVDejarSeguirUsuario extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
          
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String nicknameSeguidor = request.getParameter("nicknameSesion");
        String nicknameSeguido = request.getParameter("nicknameConsultado");
        
        System.out.println("seguidor: " + request.getParameter("nicknameSesion"));
        System.out.println("seguido: " + request.getParameter("nicknameConsultado"));
        
        Fabrica fb = Fabrica.getInstance();
        IControlador control = fb.getControlador();
        
        try {
            control.dejarDeSeguir(nicknameSeguidor, nicknameSeguido);
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
