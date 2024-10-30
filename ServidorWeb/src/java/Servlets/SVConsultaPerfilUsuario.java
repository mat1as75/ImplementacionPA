/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import espotify.DataTypes.DTDatosArtista;
import espotify.DataTypes.DTDatosCliente;
import espotify.DataTypes.DTDatosUsuario;
import espotify.logica.Cliente;
import espotify.logica.Fabrica;
import espotify.logica.IControlador;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author tecnologo
 */
@WebServlet(name = "SVConsultaPerfilUsuario", urlPatterns = {"/SVConsultaPerfilUsuario"})
public class SVConsultaPerfilUsuario extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Fabrica fb = Fabrica.getInstance();
        IControlador control = fb.getControlador();
        
        /* DATOS DE LA SESION */
        HttpSession sesion = request.getSession(false);
        String nicknameUsuarioSesion = null;
        nicknameUsuarioSesion = (String) sesion.getAttribute("nickname");
        
        DTDatosUsuario datosUsuario = null;

        // Recupero el parametro enviado desde el formulario en resultados.jsp
        String usuarioConsultado = request.getParameter("usuario-Consultar");
        
        if (usuarioConsultado == null) {
            // ConsultaPerfilUsuario propio de la Sesion
            datosUsuario = control.getDatosUsuario(nicknameUsuarioSesion);
        } else {
            // ConsultaPerfilUsuario desde SearchBar
            datosUsuario = control.getDatosUsuario(usuarioConsultado);
        }
        
        if (datosUsuario instanceof espotify.DataTypes.DTDatosCliente) {
            System.out.println("SVCONSULTA " + datosUsuario.getClass());
            // Envio un DataType con los datos del Usuario consultado
            sesion.setAttribute("DTusuarioConsultado", (DTDatosCliente) datosUsuario);
        } else {
            // Envio un DataType con los datos del Usuario consultado
            sesion.setAttribute("DTusuarioConsultado", (DTDatosArtista) datosUsuario);
        }

        response.sendRedirect("ConsultaPerfilUsuario.jsp");

    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Obtengo la sesion actual
        HttpSession sesion = request.getSession(false);
        
        // Lee el valor del nicknameUsuarioConsultado enviado desde el cliente
        String nicknameUsuarioConsultado = (String) request.getAttribute("nicknameUsuarioConsultado");
        
        // Asigno el valor a la sesion
        sesion.setAttribute("perfilConsultado", "nicknameUsuarioConsultado");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
}
