<%-- 
    Document   : GenerosArtistas
    Created on : 26 oct. 2024, 4:39:16 a. m.
    Author     : brisa
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Géneros y Artistas</title>
    <style>
        /* Estilos para las pestañas */
        .tab {
            display: inline-block;
            padding: 10px;
            cursor: pointer;
            border: 1px solid #000;
            background-color: #f0f0f0;
        }
        .active {
            background-color: #ddd;
        }
        .content {
            display: none;
            margin-top: 20px;
        }
        .active-content {
            display: block;
        }
    </style>
    <script>
        // Función para cambiar de pestaña
        function showContent(type) {
            document.getElementById("genres-content").classList.remove("active-content");
            document.getElementById("artists-content").classList.remove("active-content");
            document.getElementById(type + "-content").classList.add("active-content");
        }
    </script>
</head>
<body>
    <div>
        <div class="tab" onclick="showContent('genres')">Géneros</div>
        <div class="tab" onclick="showContent('artists')">Artistas</div>
    </div>
    
    <!-- Contenido de Géneros -->
    <div id="genres-content" class="content active-content">
        <h2>Géneros</h2>
        <ul>
            <c:forEach var="genero" items="${generos}">
                <li><a href="CargarAlbumes?tipo=genero&nombre=${genero}">${genero}</a></li>
            </c:forEach>
        </ul>
    </div>
    
    <!-- Contenido de Artistas -->
    <div id="artists-content" class="content">
        <h2>Artistas</h2>
        <ul>
            <c:forEach var="artista" items="${artistas}">
                <li><a href="CatgarAlbumes?tipo=artista&nombre=${artista}">${artista}</a></li>
            </c:forEach>
        </ul>
    </div>
</body>
</html>

