package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SVError", urlPatterns = {"/Error"})
public class SVError extends HttpServlet {

    public static void redirectUnauthorized(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("errorCode", "401");
        request.setAttribute("errorName", "Unauthorized");
        request.setAttribute("errorMsg", "Usuario no autorizado.");
        request.getRequestDispatcher("/Error").forward(request, response);
    }
    
    public static void redirectForbidden(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("errorCode", "403");
        request.setAttribute("errorName", "Forbidden");
        request.setAttribute("errorMsg", "Acceso no autorizado.");
        request.getRequestDispatcher("/Error").forward(request, response);
    }
    
    public static void redirectNotFound(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("errorCode", "404");
        request.setAttribute("errorName", "Not Found");
        request.setAttribute("errorMsg", "Recurso no encontrado.");
        request.getRequestDispatcher("/Error").forward(request, response);        
    }
    
    public static void redirectInternalServerError(HttpServletRequest request, HttpServletResponse response, String optionalMsg) throws ServletException, IOException {
        request.setAttribute("errorCode", "500");
        request.setAttribute("errorName", "Internal Server Error");
        String errorMsg = (optionalMsg == null ? "Error al procesar la solicitud." : optionalMsg);
        request.setAttribute("errorMsg", errorMsg);
        request.getRequestDispatcher("/Error").forward(request, response);
    }
    
    public static void redirectBadRequest(HttpServletRequest request, HttpServletResponse response, String optionalMsg) throws ServletException, IOException {
        request.setAttribute("errorCode", "400");
        request.setAttribute("errorName", "Bad Request");
        String errorMsg = (optionalMsg == null ? "El servidor no puede procesar la solicitud" : optionalMsg);
        request.setAttribute("errorMsg", errorMsg);
        request.getRequestDispatcher("/Error").forward(request, response);
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/ErrorPage.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
