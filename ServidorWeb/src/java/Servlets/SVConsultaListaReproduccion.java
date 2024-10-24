package servlets;

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
            List<DTGenero_Simple> listaGeneros = iControlador.getListaDTGeneroSimple();
            String json = new Gson().toJson(listaGeneros);
            response.getWriter().write(json);
        } else if ("PorListaPorDefecto".equals(tipoDeLista) && nombreG != null) {
            List<String> listasPorDefecto = iControlador.getNombresListasPorDefecto();
            List<String> listasFiltradas = new ArrayList<>();
            for (String nombreLista : listasPorDefecto) {
                try {
                    String generoLista = iControlador.getGeneroDeListaPorDefecto(nombreLista);
                    if (nombreG.equals(generoLista)) {
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