package Servlets;

import com.google.gson.Gson;
import espotify.logica.Fabrica;
import espotify.logica.IControlador;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.servlet.RequestDispatcher;
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

    private EntityManagerFactory emf;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
    }
    
    @Override
    public void init() {
        emf = Persistence.createEntityManagerFactory("EspotifyPU");
    }
    
    @Override
    public void destroy() {
        // Cierra el EntityManagerFactory
        if (emf != null) {
            emf.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Leer el cuerpo de la solicitud
        StringBuilder jsonData = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));

        String line;
        while ((line = reader.readLine()) != null) {
            jsonData.append(line);  // Construimos el JSON completo
        }
        
        // Convertir JSON a MAP
        Gson gson = new Gson();
        Map<String, Object> resultsMap = gson.fromJson(jsonData.toString(), Map.class);
        
        // Obtener la opcion y los resultados
        String opcionSeleccionada = (String) resultsMap.get("option");
        Map<String, String> results = (Map<String, String>) resultsMap.get("results");
        
        ArrayList<String> valores = new ArrayList<>(results.keySet());
        
        // Procesar los resultados ordenando por alfabeto
        if (opcionSeleccionada.equals("alfabetico")) {
            
            // Es Tema o Album
            if (results.values().toArray()[0].toString().equals("Tema") || results.values().toArray()[0].toString().equals("Album")) {
                
                for (int numPasada = valores.size() - 1; numPasada > 0; numPasada--) {
                    for (int i = 0; i < numPasada; i++) {
                        String valores_aux1 = valores.get(i);
                        String valores_aux2 = valores.get(i + 1);
                        String valores1[] = valores_aux1.split(", ");
                        String valores2[] = valores_aux2.split(", ");

                        if (valores1[1].compareTo(valores2[1]) > 0) {
                            // Intercambiar los elementos
                            String temp = valores.get(i);
                            valores.set(i, valores.get(i + 1));
                            valores.set(i + 1, temp);
                        }
                    }
                }
            } else { // Es Usuario o ListaReproduccion
                
                Map<String, String> results_Aux = new TreeMap<>(results);
                valores = new ArrayList<>(results_Aux.keySet());
            }
            
        } else { // Procesar los resultados ordenando por anio
            
            switch(results.values().toArray()[0].toString()) {
                case "Artista", "Cliente" -> {
                    Map<String, Date> consultaFechaUsuarios = buscarFechaUsuariosOrdenadas(valores);
                    request.setAttribute("type", "Usuario");
                    request.setAttribute("resultados", consultaFechaUsuarios);
                    break;
                }
                case "Tema" -> {
                    Map<Long, Integer> consultaFechaTemas = buscarFechaTemasOrdenadas(valores);
                    request.setAttribute("type", "Tema");
                    request.setAttribute("resultados", consultaFechaTemas);
                    break;
                }
                case "Album" -> {
                    Map<Long, Integer> consultaFechaAlbumes = buscarFechaAlbumesOrdenadas(valores);
                    request.setAttribute("type", "Album");
                    request.setAttribute("resultados", consultaFechaAlbumes);
                    break;
                }
                case "Lista" -> {
                    Map<String, Date> consultaFechaListasR = buscarFechaListasROrdenadas(valores);
                    request.setAttribute("type", "Lista");
                    request.setAttribute("resultados", consultaFechaListasR);
                    break;
                }
            }
        }
        
        // Redirigir a la pagina de resultados
        response.sendRedirect("resultadosBusqueda.jsp");
    }
    
    private Map<String, Date> buscarFechaUsuariosOrdenadas(ArrayList<String> nicknamesUsuarios) {
        EntityManager em = emf.createEntityManager();
        Map<String, Date> results = new HashMap<>();
        Map<String, Date> resultadosOrdenados = new LinkedHashMap<>();
        
        try {
            // Consulta JPQL con la cláusula WHERE para filtrar por nicknames
            String jpqlFechaUsuarios = "SELECT u.nickname, u.fecNac FROM Usuario u WHERE u.nickname IN :nicknames";

            // Crear el TypedQuery y asignar el parámetro de la lista de nicknames
            TypedQuery<Object[]> consultaFechaUsuarios = em.createQuery(jpqlFechaUsuarios, Object[].class);
            consultaFechaUsuarios.setParameter("nicknames", nicknamesUsuarios);  // Usar el nombre correcto del parámetro

            // Ejecutar la consulta y obtener los resultados
            List<Object[]> resultados = consultaFechaUsuarios.getResultList();
            
            // Crear un SimpleDateFormat para formatear las fechas
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

            // Procesar los resultados y agregarlos al mapa
            for (Object[] resultado : resultados) {
                String nickname = (String) resultado[0];  // Primer elemento: nickname
                Date fecNac = (Date) resultado[1];        // Segundo elemento: fecNac

                // Formatear la fecha antes de agregarla al mapa
                String fechaFormateada = dateFormat.format(fecNac);
                
                // Añadir el resultado al mapa
                results.put(nickname, fecNac);
            }
            
            // Convertir el mapa a una lista de entradas (para ordenar)
        List<Map.Entry<String, Date>> listaEntradas = new ArrayList<>(results.entrySet());

        // Ordenar la lista por fecha en orden descendente (más recientes primero)
        listaEntradas.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));

        // Recorro el LinkedHashMap para preservar el orden al insertarlo
        for (Map.Entry<String, Date> entry : listaEntradas) {
            resultadosOrdenados.put(entry.getKey(), entry.getValue());
        }
            
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
        
        return resultadosOrdenados;
    }
    
    private Map<Long, Integer> buscarFechaTemasOrdenadas(ArrayList<String> idTemas) {
        EntityManager em = emf.createEntityManager();
        Map<Long, Integer> results = new HashMap<>();
        Map<Long, Integer> resultadosOrdenados = new LinkedHashMap<>();
        
        ArrayList<Long> idTemas_Long = new ArrayList<>();
        // Convertir cada elemento de String a Long y agregarlo al nuevo ArrayList
        for (String str : idTemas) {
            try {
                idTemas_Long.add(Long.valueOf(str));  // Convertir String a Long
            } catch (NumberFormatException e) {
                System.out.println("Error al convertir el valor: " + str);
            }
        }
        
        try {
            // Consulta JPQL con la cláusula WHERE para filtrar por idTema
            String jpqlFechaTemas = "SELECT t.IDTEMA, a.ANIOCREACION FROM TEMA t JOIN ALBUM a ON t.MIALBUM_IDALBUM = a.IDALBUM WHERE t.IDTEMA IN :idTemas";

            // Crear el TypedQuery y asignar el parámetro de la lista de idTema
            TypedQuery<Object[]> consultaFechaTemas = em.createQuery(jpqlFechaTemas, Object[].class);
            consultaFechaTemas.setParameter("idTemas", idTemas_Long);  // Usar el nombre correcto del parámetro

            // Ejecutar la consulta y obtener los resultados
            List<Object[]> resultados = consultaFechaTemas.getResultList();

            // Procesar los resultados y agregarlos al mapa
            for (Object[] resultado : resultados) {
                Long idTema = (Long) resultado[0];  // Primer elemento: idTema
                int anioCreacion = (int) resultado[1];        // Segundo elemento: anioCreacion
                
                // Añadir el resultado al mapa
                results.put(idTema, anioCreacion);
            }
            
            // Convertir el mapa a una lista de entradas (para ordenar)
            List<Map.Entry<Long, Integer>> listaEntradas = new ArrayList<>(results.entrySet());

            // Ordenar la lista por fecha en orden descendente (más recientes primero)
            listaEntradas.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));

            // Recorro el LinkedHashMap para preservar el orden al insertarlo
            for (Map.Entry<Long, Integer> entry : listaEntradas) {
                resultadosOrdenados.put(entry.getKey(), entry.getValue());
            }
            
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
        
        return resultadosOrdenados;
    }

    private Map<Long, Integer> buscarFechaAlbumesOrdenadas(ArrayList<String> idAlbumes) {
        EntityManager em = emf.createEntityManager();
        Map<Long, Integer> results = new HashMap<>();
        Map<Long, Integer> resultadosOrdenados = new LinkedHashMap<>();
        
        ArrayList<Long> idAlbumes_Long = new ArrayList<>();
        // Convertir cada elemento de String a Long y agregarlo al nuevo ArrayList
        for (String str : idAlbumes) {
            try {
                idAlbumes_Long.add(Long.valueOf(str));  // Convertir String a Long
            } catch (NumberFormatException e) {
                System.out.println("Error al convertir el valor: " + str);
            }
        }
        
        try {
            // Consulta JPQL con la cláusula WHERE para filtrar por idAlbum
            String jpqlFechaAlbumes = "SELECT a.IDALBUM, a.ANIOCREACION FROM ALBUM a WHERE a.IDALBUM IN :idAlbumes";

            // Crear el TypedQuery y asignar el parámetro de la lista de idAlbum
            TypedQuery<Object[]> consultaFechaAlbumes = em.createQuery(jpqlFechaAlbumes, Object[].class);
            consultaFechaAlbumes.setParameter("idAlbumes", idAlbumes_Long);  // Usar el nombre correcto del parámetro

            // Ejecutar la consulta y obtener los resultados
            List<Object[]> resultados = consultaFechaAlbumes.getResultList();

            // Procesar los resultados y agregarlos al mapa
            for (Object[] resultado : resultados) {
                Long idAlbum = (Long) resultado[0];  // Primer elemento: idAlbum
                int anioCreacion = (int) resultado[1];        // Segundo elemento: anioCreacion
                
                // Añadir el resultado al mapa
                results.put(idAlbum, anioCreacion);
            }
            
            // Convertir el mapa a una lista de entradas (para ordenar)
            List<Map.Entry<Long, Integer>> listaEntradas = new ArrayList<>(results.entrySet());

            // Ordenar la lista por fecha en orden descendente (más recientes primero)
            listaEntradas.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));

            // Recorro el LinkedHashMap para preservar el orden al insertarlo
            for (Map.Entry<Long, Integer> entry : listaEntradas) {
                resultadosOrdenados.put(entry.getKey(), entry.getValue());
            }
            
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
        
        return resultadosOrdenados;
    }
    
    private Map<String, Date> buscarFechaListasROrdenadas(ArrayList<String> nombresListasR) {
        EntityManager em = emf.createEntityManager();
        Map<String, Date> results = new HashMap<>();
        Map<String, Date> resultadosOrdenados = new LinkedHashMap<>();
        
        try {
            // Consulta JPQL con la cláusula WHERE para filtrar por nombreLista
            String jpqlFechaListasR = "SELECT l.nombreLista, l.fechaCreacion FROM ListaReproduccion l WHERE l.nombreLista IN :nombresListasR";

            // Crear el TypedQuery y asignar el parámetro de la lista de nombreLista
            TypedQuery<Object[]> consultaFechaListasR = em.createQuery(jpqlFechaListasR, Object[].class);
            consultaFechaListasR.setParameter("nombresListasR", nombresListasR);  // Usar el nombre correcto del parámetro

            // Ejecutar la consulta y obtener los resultados
            List<Object[]> resultados = consultaFechaListasR.getResultList();
            
            // Crear un SimpleDateFormat para formatear las fechas
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

            // Procesar los resultados y agregarlos al mapa
            for (Object[] resultado : resultados) {
                String nombreLista = (String) resultado[0];  // Primer elemento: nombreLista
                Date fechaCreacion = (Date) resultado[1];        // Segundo elemento: fechaCreacion

                // Formatear la fecha antes de agregarla al mapa
                String fechaFormateada = dateFormat.format(fechaCreacion);
                
                // Añadir el resultado al mapa
                results.put(nombreLista, fechaCreacion);
            }
            
            // Convertir el mapa a una lista de entradas (para ordenar)
            List<Map.Entry<String, Date>> listaEntradas = new ArrayList<>(results.entrySet());

            // Ordenar la lista por fecha en orden descendente (más recientes primero)
            listaEntradas.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));

            // Recorro el LinkedHashMap para preservar el orden al insertarlo
            for (Map.Entry<String, Date> entry : listaEntradas) {
                resultadosOrdenados.put(entry.getKey(), entry.getValue());
            }
            
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
        
        return resultadosOrdenados;
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
