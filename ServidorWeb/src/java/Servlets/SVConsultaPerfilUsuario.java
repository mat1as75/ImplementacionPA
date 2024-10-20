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
        
        Fabrica fb = Fabrica.getInstance();
        IControlador control = fb.getControlador();
        
        /* Obtengo datos del Cliente */
        DTDatosCliente user = control.ConsultarPerfilCliente(request.getParameter("nickname"));
        
        /* Traigo la Session del Usuario y setteo al
        atributo de la misma los datos de dicho Cliente */
        HttpSession miSesion = request.getSession(false);
        miSesion.setAttribute("user", user);
        
        /* Redirecciono a ConsultarPerfilUsuario que pertenezca */
        if (user.getClass().equals(Cliente.class)) {
            response.sendRedirect("ConsultarPerfilCliente.jsp");
        } else {
            response.sendRedirect("ConsultarPerfilArtista.jsp");
        }
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Fabrica fb = Fabrica.getInstance();
        IControlador control = fb.getControlador();
        
        /* DATOS DE LA SESION */
        HttpSession sesion = request.getSession(false);
        String tipoUsuarioSesion = (String) sesion.getAttribute("rol");
        String nicknameUsuarioSesion = null;
        DTDatosUsuario datosUsuarioSesion = null;
        ArrayList<String> nicknamesSeguidoresSesion = null;
        /* ------------------ */
        
        /* DATOS DE PERFIL CONSULTADO */
        DTDatosUsuario datosUsuarioConsultado = (DTDatosUsuario) sesion.getAttribute("perfilConsultado");
        DTDatosCliente datosClienteConsultado = null;
        DTDatosArtista datosArtistaConsultado = null;
        String nicknameUsuarioConsultado = datosUsuarioConsultado.getNicknameUsuario();
        ArrayList<String> nombresListasRPublicas = null, nicknamesSeguidoresConsultados = null;
        
        
        /* ------------------ */
        
        // Sesion no pertenece a un Visitante
        if (tipoUsuarioSesion.equals("Visitante")) {
            datosUsuarioSesion = (DTDatosUsuario) sesion.getAttribute("usuario");
            nicknameUsuarioSesion = datosUsuarioSesion.getNicknameUsuario();
            
            // ConsultaPerfilCliente
            if (tipoUsuarioSesion.equals("Cliente")) { 
                datosClienteConsultado = (DTDatosCliente) sesion.getAttribute("perfilConsultado");
                
                // Cliente no se ConsultaPerfil propio
                if (!nicknameUsuarioSesion.equals(nicknameUsuarioConsultado)) {
                    
                    nicknamesSeguidoresConsultados = datosUsuarioSesion.getNicknamesSeguidores();
                    // Sesion es Seguidor del que se consulta
                    if (nicknamesSeguidoresConsultados.contains(nicknameUsuarioSesion)) {
                        
                        // Lista Reproduccion Particular Publicas
                        nombresListasRPublicas = datosClienteConsultado.getNombresListasRCreadasPublicas();
                    }
                }
                
                
                
            }
        }
        
        // Si la sesion pertenece a un Cliente o Artista
//        if (rolSesion.equals("Cliente") || rolSesion.equals("Artista")) {
//            
//        }
        response.sendRedirect("ConsultaPerfilUsuario.jsp");

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

    public void ConsultaPerfilCliente(String nicknameCliente) {
        
    }
    
    public void ConsultaPerfilArtista(String nicknameArtista) {
        
    }
    
}
