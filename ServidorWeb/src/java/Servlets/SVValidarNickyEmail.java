package Servlets;

import java.io.IOException;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import webservices.UsuarioService;
import webservices.UsuarioServiceService;


@WebServlet(urlPatterns = {"/SVValidarNickyEmail"})
public class SVValidarNickyEmail extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
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
  
        UsuarioServiceService usuarioWS = new UsuarioServiceService();
        UsuarioService usuarioPort = usuarioWS.getUsuarioServicePort();
        
        String resultado = "correcto";
        Boolean yaExisteNickname = true;
        Boolean yaExisteEmail = true;
        
        if (NickName != null) {
            yaExisteNickname = usuarioPort.existeNickname(NickName);
        }

        if (Email != null) {
            yaExisteEmail = usuarioPort.existeEmail(Email);
        }
        
        if (NickName != null && yaExisteNickname){
            resultado = "El nickname " + NickName + " ya existe";
        }
        
        if (Email != null && yaExisteEmail){
            resultado = "El email " + Email + " ya existe";
        }
        
        String patron = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        if ((Email!=null) && (!Pattern.matches(patron, Email)) ){
            resultado="El email "+Email+" no es correcto";
        }
        // Envía la respuesta de vuelta a la solicitud AJAX
        response.setContentType("text/plain");
        response.getWriter().write(resultado);
    }
}
