package Servlets;

import espotify.DataTypes.DTTemaGenericoConRutaOUrl;
import espotify.logica.Fabrica;
import espotify.logica.IControlador;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SVReproducirTema", urlPatterns = {"/Tema"})
public class SVReproducirTema extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    private void setResponseToText(HttpServletResponse response, int httpCode) {
        response.setStatus(httpCode);
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        String idTemaStr = request.getParameter("idTema");
        if (idTemaStr == null) {
            setResponseToText(response, response.SC_BAD_REQUEST);
            response.getWriter().write("El servidor no recibió el id del tema.");
            return;
        }
        
        Fabrica fb = Fabrica.getInstance();
        IControlador ictrl = fb.getControlador();
        Long idTema = Long.parseLong(idTemaStr);
        DTTemaGenericoConRutaOUrl dataTema = ictrl.getDTTemaGenericoConRutaOUrl(idTema);
        
        if (dataTema == null) {
            setResponseToText(response, response.SC_NOT_FOUND);
            response.getWriter().write("No se encontró el tema con id " + idTema);
            return;
        }
        
        String rutaTema = dataTema.getRutaTema();
        if (rutaTema != null) {
        
            File archivoTema = new File(rutaTema);
            if (!archivoTema.exists()) {
                setResponseToText(response, response.SC_NOT_FOUND);
                response.getWriter().write("El archivo "+ rutaTema +" no existe.");
                return;
            }
            
            try (
                    FileInputStream inputStream = new FileInputStream(archivoTema);
                    OutputStream outputStream = response.getOutputStream();
                    ) {

                response.setContentType("audio/mpeg");
                response.setContentLength((int) archivoTema.length());
                //opcional para permitir descarga
                response.setHeader("Content-Disposition", "inline; filename=\"" + archivoTema.getName() + "\"");
            
                //leo de a 1024 bytes a la vez
                byte[] buffer = new byte[1024];
                int bytesRead;
                //leo del input stream y cargo en el buffer
                while((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                outputStream.flush();
            } catch (Exception e) {
                Logger.getLogger(SVReproducirTema.class.getName()).log(Level.SEVERE, null, e);
                setResponseToText(response, response.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write("Error al procesar el archivo de audio.");
            }
            return;
        }
        
        String urlTema = dataTema.getUrlTema();
        if (urlTema != null) {
            setResponseToText(response, response.SC_OK);
            response.getWriter().write(urlTema);
            return;
        }
        
        SVError.redirectInternalServerError(request, response, null);
    }
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
