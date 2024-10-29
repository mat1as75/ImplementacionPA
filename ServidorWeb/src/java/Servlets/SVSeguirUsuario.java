package Servlets;

import com.google.gson.Gson;
import espotify.logica.Fabrica;
import espotify.logica.IControlador;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(urlPatterns = {"/SVSeguirUsuario"})
public class SVSeguirUsuario extends HttpServlet {
protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SVSeguirUsuario</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SVSeguirUsuario at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String nicknameSeguidor = request.getParameter("nicknameSeguidor");
        String nicknameSeguido = request.getParameter("nicknameSeguido");
       
        System.out.println("seguidor: " + request.getParameter("nicknameSesion"));
        System.out.println("seguido: " + request.getParameter("nicknameConsultado"));
        
        Fabrica fb = Fabrica.getInstance();
        IControlador control = fb.getControlador();
        
        try {
            control.setSeguidorSeguido(nicknameSeguidor, nicknameSeguido);
        } catch (Exception ex) {
            throw ex;
        }
        
        response.sendRedirect("ConsultaPerfilUsuario.jsp");

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
