/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import webservices.DataTypes.DtTemaConPuntaje;
import webservices.RankingService;
import webservices.RankingServiceService;

@WebServlet(name = "SVRankingTemas", urlPatterns = {"/SVRankingTemas"})
public class SVRankingTemas extends HttpServlet {
    
    RankingServiceService serviceR = new RankingServiceService();
    RankingService serviceRanking = serviceR.getRankingServicePort();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SVRankingTemas</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SVRankingTemas at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
//        // Obtener Top 10 Temas
//        List<DtTemaConPuntaje> topTemas = serviceRanking.getTopTemas(10).getColeccion()
//                .stream()
//                .filter(DtTemaConPuntaje.class::isInstance)
//                .map(DtTemaConPuntaje.class::cast)
//                .collect(Collectors.toList());
//        
//        // Setteo un atributo en el Request con topTemas
//        request.setAttribute("topTemas", topTemas);
//        
//        // Redirigo al JSP
//        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
//        dispatcher.forward(request, response);
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
