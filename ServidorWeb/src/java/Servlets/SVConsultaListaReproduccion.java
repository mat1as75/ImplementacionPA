package Servlets;

import com.google.gson.Gson;
import webservices.DataTypes.DtGeneroSimple;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import webservices.ArrayListContainer;
import webservices.ContenidoService;
import webservices.ContenidoServiceService;
import webservices.ListaReproduccionService;
import webservices.ListaReproduccionServiceService;

@WebServlet(name = "SVConsultaListaReproduccion", urlPatterns = {"/SVConsultaListaReproduccion"})
public class SVConsultaListaReproduccion extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String tipoDeLista = request.getParameter("tipoDeLista");
        String nombreG = request.getParameter("nombre");
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        ListaReproduccionServiceService serviceL = new ListaReproduccionServiceService();
        ListaReproduccionService serviceLista = serviceL.getListaReproduccionServicePort();
        
        ContenidoServiceService serviceC = new ContenidoServiceService();
        ContenidoService serviceContenido = serviceC.getContenidoServicePort();
        
        if ("PorListaParticular".equals(tipoDeLista)) {
            ArrayListContainer listaPublicasConteiner = serviceLista.getNombresListasParticularesPublicas();
            List<String> listasPublicas =  listaPublicasConteiner.getColeccion().stream()
                    .map(String::valueOf)
                    .collect(Collectors.toList());
            String json = new Gson().toJson(listasPublicas);
            response.getWriter().write(json);
        } else if ("PorGenero".equals(tipoDeLista)) {
            // Obtener todos los generos
            ArrayListContainer todosLosGenerosConteiner = serviceContenido.getListaDTGeneroSimple();
            List<DtGeneroSimple> todosLosGeneros = todosLosGenerosConteiner.getColeccion().stream()
                    .filter(DtGeneroSimple.class::isInstance)
                    .map(DtGeneroSimple.class::cast)
                    .collect(Collectors.toList());
            List<DtGeneroSimple> generosPrincipales = new ArrayList<>();
            // Filtrar solo los generos principales (Con padre = "Genero")
            for (DtGeneroSimple genero : todosLosGeneros) {
                if ("Genero".equals(genero.getNombreGeneroPadre())) {
                    generosPrincipales.add(genero);
                }
            }
            String json = new Gson().toJson(generosPrincipales);
            response.getWriter().write(json);
        } else if ("PorListaPorDefecto".equals(tipoDeLista) && nombreG != null) {
            // Obtener todas las listas por defecto
            ArrayListContainer listasPorDefectoConteiner = serviceLista.getNombresListasPorDefecto();
            List<String> listasPorDefecto = listasPorDefectoConteiner.getColeccion().stream()
                    .map(String::valueOf)
                    .collect(Collectors.toList());;
            List<String> listasFiltradas = new ArrayList<>();
            List<String> generosAsociados = new ArrayList<>();
            generosAsociados.add(nombreG); // Incluir el genero seleccionado 

            // Obtener todos los generos
            // Obtener todos los generos
            ArrayListContainer todosLosGenerosConteiner = serviceContenido.getListaDTGeneroSimple();
            List<DtGeneroSimple> todosLosGeneros = todosLosGenerosConteiner.getColeccion().stream()
                    .filter(DtGeneroSimple.class::isInstance)
                    .map(DtGeneroSimple.class::cast)
                    .collect(Collectors.toList());

            // Filtrar subgeneros y agregar a generosAsociados
            for (DtGeneroSimple genero : todosLosGeneros) {
                if (nombreG.equals(genero.getNombreGeneroPadre())) {
                    generosAsociados.add(genero.getNombreGenero());
                }
            }

            // Filtrar listas por defecto que tengan los generos asociados
            for (String nombreLista : listasPorDefecto) {
                try {
                    String generoLista = serviceContenido.getGeneroDeListaPorDefecto(nombreLista);
                    if (generosAsociados.contains(generoLista)) {
                        listasFiltradas.add(nombreLista);
                    }
                } catch (Exception e) {
                    System.err.println("Error al obtener el género de la lista: " + e.getMessage());
                }
            }
            String json = new Gson().toJson(listasFiltradas);
            response.getWriter().write(json);
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet para consultar lista de reproducción";
    }
}
