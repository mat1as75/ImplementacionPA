/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import espotify.logica.IControlador;
import espotify.logica.Fabrica;
import espotify.DataTypes.DTAlbum;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * @author brisa
 */
@WebServlet(name = "SVObtenerAlbumes", urlPatterns = {"/SVObtenerAlbumes"})
public class SVObtenerAlbumes extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SVObtenerAlbumes</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SVObtenerAlbumes at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener el controlador de la lógica
        Fabrica fabrica = Fabrica.getInstance();
        IControlador iControlador = fabrica.getControlador();

        String genero = request.getParameter("genero");
        String artista = request.getParameter("artista");
        List<DTAlbum> albumes = null;

        try {
            // Decidir el método del controlador según el parámetro recibido
            if (genero != null && !genero.isEmpty()) {
                albumes = iControlador.getDTAlbumesPorGenero(genero);
                request.setAttribute("tipoBusqueda", "género");
            } else if (artista != null && !artista.isEmpty()) {
                albumes = iControlador.getDTAlbumesPorArtista(artista);
                request.setAttribute("tipoBusqueda", "artista");
            }

            // Guardar la lista de álbumes como atributo de solicitud
            request.setAttribute("albumes", albumes);
        } catch (Exception e) {
            request.setAttribute("errorMsg", "Error al obtener los álbumes: " + e.getMessage());
        }

        // Redirigir al JSP para mostrar los álbumes
        request.getRequestDispatcher("albumesPorBusqueda.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
