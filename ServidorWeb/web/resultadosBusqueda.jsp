<%@page import="java.util.HashMap"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.TreeMap"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="java.util.Map"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <link rel="stylesheet" href="styles/resultadosBusqueda.css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link rel="icon" href="Resource/ImagenesPerfil/espotify-icon.png" type="image/png" sizes="16x16">
        <title>Espotify</title>
    </head>

    <jsp:include page="headerIndex.jsp"/>

    <body>

        <%
            // Hacer la misma funcion del JavaScript pero aca
            // Necesito a partir de los idTema & idAlbum obtener sus nombres y mostrarlos
        %>

        <div class="opciones-Bar">
            <div class="columnDiv">
                <% if ((int) request.getAttribute("n_Resultados") == 1) { %>
                    <p><%= request.getAttribute("n_Resultados") %> resultado</p>
                <% } else { %>
                    <p><%= request.getAttribute("n_Resultados") %> resultados</p>
                <% } %>
                
                <div class="opciones">
                    <label id="label-opciones" for="ordenar">Ordenar por:</label>
                    <select id="ordenar" name="opcion" onchange="sendData(this.value)">
                            <option selected disabled hidden>(seleccione una opción)</option>
                            <option value="alfabetico" type="submit">Alfabéticamente (A-Z a-z)</option>
                            <option value="anio" type="submit">Año (descendente)</option>
                    </select>
                </div>
            </div>
            <div class="divisor d-none d-sm-block"></div>
            <div class="js" id="js"></div>
            <div class="resultados" id="resultadoDiv">
                <%
                    Map<String, String> resultados = (Map<String, String>) request.getAttribute("resultados");
                    if (resultados.isEmpty()) {
                %>
                    <p id="sin-resultados">No se encontraron resultados.</p>
                <%
                    } else {    
                        // Convertir el Map en una lista para procesarlo en JavaScript
                        Gson gson = new Gson();
                        String jsonResultados = gson.toJson(resultados);
                %>
                        <script src="scripts/resultadosBusqueda.js"></script>
                        <script>
                            let resultados = <%= jsonResultados %>;
                            mostrarResultados(resultados); // Mostrar resultados iniciales
                            console.log(resultados);

                            // Funcion que envia la opcion y resultados a ordenar 
                            function sendData(opcionOrden) {
                                if (opcionOrden) {
                                    var xhr = new XMLHttpRequest();
                                    xhr.open("POST", "SVOrdenarResultadosBusqueda", true);
                                    xhr.setRequestHeader("Content-Type", "application/json");

                                    // Crear el objeto que se enviara
                                    var data = {
                                        option: opcionOrden,
                                        results: <%= jsonResultados %>
                                    };

                                    // Enviar el JSON al servlet
                                    xhr.send(JSON.stringify(data));
                                }
                            }
                        </script>
                <%      
                        String type = (String) request.getAttribute("type");
                        System.out.println("TIPO2: " + type);
                        if (type != null) {
                            System.out.println("TIPO: " + type);
                            String jsonResults = null;
                            switch(type) {
                                case "Artista":
                                case "Cliente": 
                                case "Lista":
                                    Map<String, Date> results = (Map<String, Date>) request.getAttribute("resultsOrden");
                                    jsonResults = gson.toJson(results);
                                    System.out.println("ACA2: " + jsonResults);
                %>                
                                    <script>
                                        let results = <%= jsonResults %>
                                        mostrarResultados(results);
                                    </script>
                <%                
                                    break;
                                case "Tema": 
                                case "Album": 
                                    jsonResults = gson.toJson((Map<Long, Integer>) request.getAttribute("resultsOrden"));
                %>                
                                    <script>
                                        let results = <%= jsonResults %>
                                        mostrarResultados(results);
                                    </script>
                <%
                                    break;
                            }
                        }
                    }
                %>
            </div>
        </div>

        
    </body>
</html>
