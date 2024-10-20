package Servlets;

import espotify.logica.Fabrica;
import espotify.logica.IControlador;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SVAudioFile", urlPatterns = {"/Tema"})
public class SVAudioFile extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        String idTemaStr = request.getParameter("idTema");
        if (idTemaStr == null) {
            response.setStatus(response.SC_BAD_REQUEST);
            response.setContentType("text/plain");
            response.getWriter().write("El servidor no recibió el id del tema.");
            return;
        }
        
        Fabrica fb = Fabrica.getInstance();
        IControlador ictrl = fb.getControlador();
        Long idTema = Long.parseLong(idTemaStr);
        
                
        String filePath1 = "/home/mat/Desktop/ProyectoProgramacion/ImplementacionPA/ServidorWeb/build/web/../../web/Resource/Albums/alcides/prueba1/lp";
        String filePath2 = "/home/mat/Desktop/ProyectoProgramacion/ImplementacionPA/ServidorWeb/build/web/../../web/Resource/Albums/alcides/prueba1/wc";
        
        File audioFile1 = new File(filePath1);
        File audioFile2 = new File(filePath2);
        
        if (!audioFile1.exists() || !audioFile2.exists()) {
            System.out.println("No existe el archivo");
        }
        
        response.setContentType("audio/mpeg");
        response.setContentLength((int) audioFile1.length());
        //opcional para permitir descarga
        response.setHeader("Content-Disposition", "inline; filename=\"" + audioFile1.getName() + "\"");
        System.out.println("response type: " + response.getContentType());
        try (
                FileInputStream inputStream = new FileInputStream(audioFile1);
                OutputStream outputStream = response.getOutputStream();
                ) {
            
            //leo de a 1024 bytes a la vez
            byte[] buffer = new byte[1024];
            int bytesRead;
            
            while((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.flush();
            System.out.println("Leyo el archivo");
        } catch (Exception e) {
            System.out.println("Excepcion en la lectura del archivo");
        }
        
    }
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
