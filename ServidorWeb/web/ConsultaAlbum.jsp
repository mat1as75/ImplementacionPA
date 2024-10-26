<%-- 
    Document   : ConsultaAlbum
    Created on : 22 oct. 2024, 9:48:42 a. m.
    Author     : brisa
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${album.nombreAlbum}</title>
    </head>
    <body>
        
        <h1>${album.nombreAlbum}</h1>
        <p>Artista: ${album.miArtista}</p>
        <p>Año: ${album.anioCreacion}</p>
        <p>Generos: ${album.misGeneros.join(', ')}</p>
        <img src="${album.fotoAlbum}" alt="${album.nombreAlbum}"/>
        
        <h2>Temas</h2>
        <ul>
            <c:forEach var="tema" items="${album.misTemas}">
                <li>
                    ${tema.nombreTema}
                    ${tema.duracionSegundos}
                    <c:if test= "${suscripcionValida}">
                        <c:if test="${tema.url != null}">
                            <a href="${tema.url}">Descargar</a>
                        </c:if>
                        <c:if test="${tema.url == null}">
                            <a href="${tema.ruta}">Descargar</a>
                        </c:if>
                    </c:if>
                    
                </li>
            </c:forEach>
            
        </ul>
        
    </body>
</html>
