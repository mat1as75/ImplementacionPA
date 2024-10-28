package Servlets;

import com.google.gson.Gson;
import espotify.DataTypes.DTGenero_Simple;
import espotify.logica.Fabrica;
import espotify.logica.IControlador;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SVConsultaListaReproduccion", urlPatterns = {"/SVConsultaListaReproduccion"})
public class SVConsultaListaReproduccion extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String tipoDeLista = request.getParameter("tipoDeLista");
        String nombreG = request.getParameter("nombre");

        Fabrica f = Fabrica.getInstance();
        IControlador iControlador = f.getControlador();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        if ("PorListaParticular".equals(tipoDeLista)) {
            List<String> listasPublicas = iControlador.getNombresListasParticularesPublicas();
            String json = new Gson().toJson(listasPublicas);
            response.getWriter().write(json);
        } else if ("PorGenero".equals(tipoDeLista)) {
            // Obtener todos los generos
            List<DTGenero_Simple> todosLosGeneros = iControlador.getListaDTGeneroSimple();
            List<DTGenero_Simple> generosPrincipales = new ArrayList<>();
            // Filtrar solo los generos principales (Con padre = "Genero")
            for (DTGenero_Simple genero : todosLosGeneros) {
                if ("Genero".equals(genero.getNombreGeneroPadre())) {
                    generosPrincipales.add(genero);
                }
            }
            String json = new Gson().toJson(generosPrincipales);
            response.getWriter().write(json);
        } else if ("PorListaPorDefecto".equals(tipoDeLista) && nombreG != null) {
            // Obtener todas las listas por defecto
            List<String> listasPorDefecto = iControlador.getNombresListasPorDefecto();
            List<String> listasFiltradas = new ArrayList<>();
            List<String> generosAsociados = new ArrayList<>();
            generosAsociados.add(nombreG); // Incluir el genero seleccionado 

            // Obtener todos los generos
            List<DTGenero_Simple> todosLosGeneros = iControlador.getListaDTGeneroSimple();

            // Filtrar subgeneros y agregar a generosAsociados
            for (DTGenero_Simple genero : todosLosGeneros) {
                if (nombreG.equals(genero.getNombreGeneroPadre())) {
                    generosAsociados.add(genero.getNombreGenero());
                }
            }

            // Filtrar listas por defecto que tengan los generos asociados
            for (String nombreLista : listasPorDefecto) {
                try {
                    String generoLista = iControlador.getGeneroDeListaPorDefecto(nombreLista);
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
