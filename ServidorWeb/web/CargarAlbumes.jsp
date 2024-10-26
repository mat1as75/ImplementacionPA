<%-- 
    Document   : CargarAlbumes
    Created on : 26 oct. 2024, 1:05:59 p. m.
    Author     : brisa
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Lista de Álbumes</title>
</head>
<body>
    <h1>Álbumes</h1>
    <ul>
        <c:forEach var="album" items="${albumes}">
            <li>
                <a href="ConsultaAlbum?albumId=${album.id}">${album.nombre}</a>
            </li>
        </c:forEach>
    </ul>
</body>
</html>

