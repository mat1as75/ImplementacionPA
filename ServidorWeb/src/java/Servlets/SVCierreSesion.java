/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "SVCierreSesion", urlPatterns = {"/SVCierreSesion"})
public class SVCierreSesion extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SVCierreSesion</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SVCierreSesion at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Obtengo la sesion actual
        HttpSession sesion = request.getSession(false);
        
        if (sesion != null) {
            // Invalido la sesion
            sesion.invalidate();
        }
        
        // Elimino la cookie de sesion
        Cookie cookie = new Cookie("sessionId", null);
        cookie.setMaxAge(0); // Invalido la cookie
        response.addCookie(cookie);
        
        // Redirigo
        response.sendRedirect("index.jsp?mensaje=cerrada");
        
//        if (sesion != null) {
//            // Restablecer atributos de sesion
//            sesion.setAttribute("usuario", null);
//            sesion.setAttribute("rol", "Visitante");
//            
//            // Invalidar la sesion
//            sesion.invalidate();
//            
//            response.sendRedirect("index.jsp?mensaje=cerrada");
//        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
