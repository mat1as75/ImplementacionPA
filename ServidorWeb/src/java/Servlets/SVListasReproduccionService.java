package Servlets;

import webservices.DtDatosListaReproduccion;
import webservices.DtTemaSimple;

//import espotify.DataTypes.DTDatosListaReproduccion;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import webservices.ListasReproduccionService;
import webservices.ListasReproduccionServiceService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import webservices.MyArrayList;

@WebServlet(name = "SVListasReproduccionService", urlPatterns = {"/SVListasReproduccionService"})
public class SVListasReproduccionService extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
    }

     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        response.setContentType("text/html");
        ListasReproduccionServiceService service = new ListasReproduccionServiceService();
        ListasReproduccionService ws = service.getListasReproduccionServicePort();
        response.getWriter().write("<h1>----------------crearListaParticular---------------</h1>");
        ws.crearListaParticular("miLista","mifotof","cbochinche",true);  
        response.getWriter().write("<h1>----------------crearListaParticularConFecha---------------</h1>");
        Date fecha=new Date();
        String lafecha=fecha.toString();
        ws.crearListaParticularConFecha("nombreL","crearListaParticularConFecha","Heisenberg", lafecha,true);
       response.getWriter().write("<h1>---------------getListasReproduccionDisponibles----------------</h1>");
        MyArrayList lista =ws.getListasReproduccionDisponibles();
        for(int i=0;i<lista.size();i++){
            Object o=lista.get(i);
            String s=(String)o;
            response.getWriter().write("<h1>"+ s+"</h1>");
        };
        response.getWriter().write("<h1>------------getNombresListasParticularesPublicas-------------------</h1>");
       
        MyArrayList lista1 =ws.getNombresListasParticularesPublicas();
        for(int i=0;i<lista1.size();i++){
            Object o=lista1.get(i);
            String s=(String)o;
            response.getWriter().write("<h1>"+ s+"</h1>");
        };
        response.getWriter().write("<h1>--------------getNombresListasPorDefecto----------------</h1>");
        MyArrayList lista2 =ws.getNombresListasPorDefecto();
        for(int i=0;i<lista2.size();i++){
            Object o=lista2.get(i);
            String s=(String)o;
            response.getWriter().write("<h1>"+ s+"</h1>");
        };
        response.getWriter().write("<h1>------------getNombresListasParticulares------------------</h1>");
        MyArrayList lista3 =ws.getNombresListasParticulares();
        for(int i=0;i<lista3.size();i++){
            Object o=lista3.get(i);
            String s=(String)o;
            response.getWriter().write("<h1>"+ s+"</h1>");
        };
        response.getWriter().write("<h1>----------------listasCreadasEstadoPrivadoTrue---------------</h1>");
        MyArrayList lista4 =ws.listasCreadasEstadoPrivadoTrue("Heisenberg");
        for(int i=0;i<lista4.size();i++){
            Object o=lista4.get(i);
            String s=(String)o;
            response.getWriter().write("<h1>"+ s+"</h1>");
        };
        response.getWriter().write("<h1>----------consultarListaReproduccion---------------------</h1>");
        DtDatosListaReproduccion dt=ws.consultarListaReproduccion("Particular","Para Cocinar");
        response.getWriter().write("<h1>"+dt.getCliente()+"</h1>");
        response.getWriter().write("<h1>"+dt.getFotoLista()+"</h1>");
        response.getWriter().write("<h1>"+dt.getGenero()+"</h1>");
        response.getWriter().write("<h1>"+dt.getNombreLista()+"</h1>");
        response.getWriter().write("<h1>"+dt.getTipoDeLista()+"</h1>");
        response.getWriter().write("<h1>temas</h1>");
        List<DtTemaSimple> temas=dt.getTemas();
        for(DtTemaSimple t:temas){
            response.getWriter().write("<h1>"+t.getNombreAlbum()+"</h1>");
            response.getWriter().write("<h1>"+t.getNombreCompletoArtista()+"</h1>");
            response.getWriter().write("<h1>"+t.getNombreTema()+"</h1>");
            response.getWriter().write("<h1>"+t.getDuracionSegundos()+"</h1>");
            List<String> generos=t.getGenerosDeTema();
            response.getWriter().write("<h2>generos</h2>");
            for(String s:generos){
                response.getWriter().write("<h1>"+s+"</h1>");
            
            }
            response.getWriter().write("<h1>"+t.getIdAlbum()+"</h1>");
            response.getWriter().write("<h1>"+t.getIdTema()+"</h1>");
            response.getWriter().write("<h1>"+t.getPosicionEnAlbum()+"</h1>");
        }
        response.getWriter().write("<h1>------------getListaDTDatosListaReproduccionDeCliente-------------------</h1>");
        
        /*MyArrayList lista4 =ws.getListaDTDatosListaReproduccionDeCliente("cbochinche");
        for(int i=0;i<lista4.size();i++){
            Object o=lista4.get(1);
            DTDatosListaReproduccion s=(DTDatosListaReproduccion)o;
            response.getWriter().write("<h1>"+ s.getNombreLista()+"</h1>");
        };*/
        response.getWriter().write("<h1>-------------------------------</h1>");
        
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
