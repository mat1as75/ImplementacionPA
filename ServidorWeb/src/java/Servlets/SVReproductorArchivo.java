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

@WebServlet(name = "DejarSeguirUsuario", urlPatterns = {"/DejarSeguirUsuario"})
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
        
        String nicknameSeguidor = request.getParameter("nicknameSeguidor");
        String nicknameSeguido = request.getParameter("nicknameSeguido");
        
        Fabrica fb = Fabrica.getInstance();
        IControlador control = fb.getControlador();
        
        control.dejarDeSeguir(nicknameSeguidor, nicknameSeguido);
    }

    @Override
    public String getServletInfo() {
        return "Servlet para dejar de seguir usuario";
    }

}
