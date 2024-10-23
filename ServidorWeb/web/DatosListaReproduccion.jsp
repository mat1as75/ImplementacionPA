<%@ page import="java.util.List" %>
<%@ page import="espotify.DataTypes.DTDatosListaReproduccion" %>
<%@page import="espotify.DataTypes.DTTemaSimple"%>
<%@ page import="espotify.logica.Fabrica" %>
<%@ page import="espotify.logica.IControlador" %>

<%
    String nombreLista = request.getParameter("nombreLista");
    Fabrica f = Fabrica.getInstance();
    IControlador iControlador = f.getControlador();
    DTDatosListaReproduccion datosLista = null;
    String tipoLista = null;
    String errorMsg = null;

    try {
        List<String> nombresListasParticulares = iControlador.getNombresListasParticulares();
        List<String> nombresListasPorDefecto = iControlador.getNombresListasPorDefecto();

        if (nombresListasParticulares.contains(nombreLista)) {
            tipoLista = "Particular";
        } else if (nombresListasPorDefecto.contains(nombreLista)) {
            tipoLista = "Por Defecto";
        }
        if (tipoLista != null) {
            datosLista = iControlador.ConsultarListaReproduccion(tipoLista, nombreLista);
        }
    } catch (Exception e) {
        errorMsg = "Error al obtener los datos de la lista: " + e.getMessage();
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <script src="scripts/ConsultarListaReproduccion.js"></script>
        <link rel="stylesheet" href="styles/DatosListaReproduccion.css"/>
    </head>
    <body>
        <div class="container">
            <div class="lista-detalles">
                <div class="info-container">
                    <div class="lista-fotoLista">
                       
                    </div>

                     <div class="lista-info">
                    <h1><%= datosLista != null ? datosLista.getNombreLista() : "Lista no encontrada" %></h1> 
                    <p><span>Tipo:</span> <%= tipoLista != null ? tipoLista : "Desconocido" %></p>
                    <% if ("Por Defecto".equals(tipoLista)) { %>
                        <p><span>Género:</span> <%= datosLista != null ? datosLista.getGenero() : "N/A" %></p>
                    <% } else if ("Particular".equals(tipoLista)) { %>
                        <p><span>Cliente:</span> <%= datosLista != null ? datosLista.getCliente() : "N/A" %></p>
                    <% } %>
                </div>

                </div>

                <div class="separador"></div>

                <div class="temas-lista">
                    <table>
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>Agregar</th>
                                <th>Tema</th>
                                <th>Duración</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% int index = 1; %>
                            <% if (datosLista != null) {
                                    for (DTTemaSimple tema : datosLista.getTemas()) {
                                        int duracionSegundos = tema.getDuracionSegundos();
                                        int minutos = duracionSegundos / 60;
                                    int segundos = duracionSegundos % 60;%>
                            <tr>
                                <td><%= index++%></td>
                                <td><button class="agregar">+</button></td>
                                <td><%= tema.getNombreTema()%></td>
                                <td><%= String.format("%d:%02d", minutos, segundos)%></td>
                            </tr>
                            <% }
                            }%>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>
