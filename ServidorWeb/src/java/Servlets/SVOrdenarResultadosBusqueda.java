package Servlets;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author tecnologo
 */
@WebServlet(name = "SVOrdenarResultadosBusqueda", urlPatterns = {"/SVOrdenarResultadosBusqueda"})
public class SVOrdenarResultadosBusqueda extends HttpServlet {

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
        
        // Opcion seleccionada del Filtro Ordenar
        String ordenSeleccionado = request.getParameter("ordenar");
        System.out.println("SELECCION: " + ordenSeleccionado);
        
        // Leer el cuerpo de la solicitud
        StringBuilder sb = new StringBuilder();
        String line;
        BufferedReader reader = request.getReader();
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        
        // Convertir JSON a MAP
        Gson gson = new Gson();
        Map<String, Object> resultsMap = gson.fromJson(sb.toString(), Map.class);
        
        // Obtener la opcion y los resultados
        String opcionSeleccionada = (String) resultsMap.get("option");
        Map<String, String> resutls = (Map<String, String>) resultsMap.get("results");
        
        // Procesar los resultados segun la opcion seleccionada
        if (opcionSeleccionada.equals("alfabeto")) {
            // Logica para ordenar alfabeticamente
            System.out.println("ALFABETO");
        } else {
            // Logica para ordenar por anio
            System.out.println("ANIO");
        }
        
        // Responder con el request enviando los datos ordenados segun pertenezca
        
//        String value = resultsMap.values().toArray()[0].toString();
//        System.out.println("ACA1: " + value);
//        // Existen Resultados
//        if (resultsMap != null) {
//            
//            // Orden por ALFABETO
//            if (ordenSeleccionado.equals("alfabeto")) {
//                
//                
//                
//            } else { // Orden por ANIO
//                
//            }
//        }
//        
//        Map<String, String> results = (Map<String, String>) request.getAttribute("resultados");
//        if (results != null) {
//            System.out.println("SI");
//        } else {
//            System.out.println("NO");
//        }
////        for (String key : results.keySet()) {
////            System.out.println(key + " - ");
////        }
//        
//        // Orden por ALFABETO
//        if (ordenSeleccionado.equals("alfabeto")) {
//            
//        } else { // Orden por ANIO
//            
//        }
//        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
