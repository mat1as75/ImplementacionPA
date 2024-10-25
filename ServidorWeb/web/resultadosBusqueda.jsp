<%@page import="com.google.gson.Gson"%>
<%@page import="java.util.Map"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script src="scripts/resultadosBusqueda.js"></script>
    <link rel="stylesheet" href="styles/resultadosBusqueda.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link rel="icon" href="Resource/ImagenesPerfil/espotify-icon.png" type="image/png" sizes="16x16">
    <title>Espotify</title>
</head>



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
                <label id="label-opciones" for="opciones">Ordenar por:</label>
                <select id="opciones" name="opcion" oncharge="ordenarResultados(resultados)">
                    <option selected disabled hidden>(seleccione una opción)</option>
                    <option typevalue="alfabeticamente">Alfabéticamente (A-Z a-z)</option>
                    <option value="anio">Año (descendente)</option>
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
                <p>No se encontraron resultados.</p>
            <%
                } else { 
                    // Convertir el Map en una lista para procesarlo en JavaScript
                    String jsonResultados = new Gson().toJson(resultados);
            %>
                <script>
                    var resultados = <%= jsonResultados %>;
                    mostrarResultados(resultados); // Mostrar resultados iniciales
                </script>
            <%
                }
            %>
        </div>
    </div>
    
    
</body>

