package Servlets;

import WebServices.NuevoWebService;
import WebServices.NuevoWebServiceService;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ServletPrueba", urlPatterns = {"/ServletPrueba"})
public class ServletPrueba extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        NuevoWebServiceService service = new NuevoWebServiceService();
        NuevoWebService ws = service.getNuevoWebServicePort();
        Boolean existe = ws.existeCliente("cbochinche");
        String resultado = ws.getPepe();
        
        response.getWriter().write("<h1>"+resultado+"| " + existe +"</h1>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
