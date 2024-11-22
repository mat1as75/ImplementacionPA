/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import webservices.DataTypes.DtDatosUsuario;
import webservices.Exception_Exception;
import webservices.NullableContainer;
import webservices.UsuarioService;
import webservices.UsuarioServiceService;

@WebServlet(name = "SVBajaArtista", urlPatterns = {"/SVBajaArtista"})
public class SVBajaArtista extends HttpServlet {

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
        processRequest(request, response);

        UsuarioServiceService serviceU = new UsuarioServiceService();
        UsuarioService serviceUsuario = serviceU.getUsuarioServicePort();
        
        String nicknameSesion = request.getParameter("nicknameSesion");
        
        /* Buscar Artista */
        NullableContainer dtDatosArtistaContainer = serviceUsuario.getDatosUsuario(nicknameSesion);
        DtDatosUsuario dtDatosArtista = dtDatosArtistaContainer.getDtDatosUsuario();
        
        /* Foto Perfil a eliminar */
        File fotoPerfilArchivo = null;
        if (dtDatosArtista.getFotoPerfil() != null) {
            fotoPerfilArchivo = new File("./././web" + dtDatosArtista.getFotoPerfil().substring(2));
            
            if (fotoPerfilArchivo.exists()) {
                fotoPerfilArchivo.delete();
            }
        }
        
        try {
            serviceUsuario.darDeBajaArtista(nicknameSesion);
        } catch (Exception_Exception ex) {
            Logger.getLogger(SVBajaArtista.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /* Paso el mensaje al JSP como atributo de solicitud */
        request.setAttribute("mensaje", "inactiva");
        
        /* Redirigo a SVCierreSesion */
        response.sendRedirect("SVCierreSesion");
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
