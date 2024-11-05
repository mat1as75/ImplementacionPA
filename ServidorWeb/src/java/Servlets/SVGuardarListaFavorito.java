/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import espotify.DataTypes.DTDatosCliente;
import espotify.logica.Fabrica;
import espotify.logica.IControlador;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author brisa
 */
@WebServlet(name = "SVGuardarListaFavorito", urlPatterns = {"/SVGuardarListaFavorito"})
public class SVGuardarListaFavorito extends HttpServlet {

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
                // Obtener el controlador
        Fabrica fb = Fabrica.getInstance();
        IControlador control = fb.getControlador();
        
        
        String nombreLista = request.getParameter("lista");
        
        DTDatosCliente user = control.ConsultarPerfilCliente(request.getParameter("nickname"));
         // Obtener la sesión del usuario//
        HttpSession miSesion = request.getSession(false);
        miSesion.setAttribute("user", user);
        //Boolean suscripcionValida = (Boolean) miSesion.getAttribute("suscripcionValida");
        //String userRole = (String) miSesion.getAttribute("userRole");
        
        // Verificar que el usuario tenga rol de cliente y una suscripción válida
        //if (suscripcionValida == null || !suscripcionValida || !"cliente".equals(userRole)) {
          //  response.sendRedirect("error.jsp?message=Acceso denegado");
            //return;
        //}
        
         // Validar que el usuario esté autenticado y que se haya proporcionado el nombreLista
        //if (user == null || nombreLista == null ) {
          //  response.sendRedirect("error.jsp?message=Información incompleta");
            //return;
        //}
        
        try {
            control.GuardarListaFavorito(user.getNicknameUsuario(), nombreLista);
        } catch (Exception ex) {
            Logger.getLogger(SVGuardarListaFavorito.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.sendRedirect("DatosListaDeReproduccion.jsp?nombreLista="+ nombreLista);
       
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "SV Guardar lista favorito";
    }// </editor-fold>

}