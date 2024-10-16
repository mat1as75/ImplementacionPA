package Servlets;

import espotify.logica.Fabrica;
import espotify.logica.IControlador;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns = {"/SVValidarNickyEmail"})
public class SVValidarNickyEmail extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MiServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MiServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          // Obtiene la variable enviada desde AJAX
        String NickName = request.getParameter("NickName");
        String Email = request.getParameter("Email");
        Fabrica f = Fabrica.getInstance();
        IControlador i=f.getControlador();
        String resultado ="correcto";
        if((NickName!=null)&&(i.ExisteNickName(NickName))){
            resultado="El nickname "+NickName+" ya existe";
        }
        if((Email!=null)&&(i.ExisteEmail(Email))){
            resultado="El email "+Email+" ya existe";
            
        }
        String patron = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        if ((Email!=null)&&(!Pattern.matches(patron, Email)) ){
            resultado="El email "+Email+" no es correcto";
        }
        // Envía la respuesta de vuelta a la solicitud AJAX
        response.setContentType("text/plain");
        response.getWriter().write(resultado);
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
