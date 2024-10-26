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
            <main>
                <div class="album-info">
                    <img src="${album.fotoAlbum}" alt="${album.nombreAlbum}" class="album-cover"/>
                    <h1>${album.nombreAlbum}</h1>
                    <p>Artista: ${album.miArtista}</p>
                    <p>Año: ${album.anioCreacion}</p>
                    <p>Géneros: 
                        <c:forEach var="genero" items="${album.misGeneros}">
                            <span class="genre">${genero}</span>
                        </c:forEach>
                    </p>
                    
                    <!-- Botón Play que usa el reproductor.jspf -->
                    <form action="reproductor.jspf" method="post" target="reproductorFrame">
                        <input type="hidden" name="albumId" value="${album.id}" />
                        <button type="submit">Play</button>
                    </form>

                    <!-- Mostrar el botón "Guardar" solo si el usuario es un cliente con suscripción vigente -->
                    <c:if test="${userRole == 'cliente' && suscripcionValida}">
                        <form action="SVGuardarAlbumFavorito" method="post">
                            <input type="hidden" name="albumId" value="${album.id}" />
                            <button type="submit">Guardar</button>
                        </form>
                    </c:if>
                </div>

                <!-- Lista de temas -->
                <section class="tracks">
                    <table>
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>Tema</th>
                                <th>Duración</th>
                                <th>Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="tema" items="${album.misTemas}" varStatus="status">
                                <tr>
                                    <!-- Número de tema -->
                                    <td>${status.index + 1}</td>
                                    
                                    <!-- Nombre del tema -->
                                    <td>${tema.nombreTema}</td>
                                    
                                    <!-- Duración del tema -->
                                    <td>${tema.duracionSegundos}</td>

                                    <!-- Botones -->
                                    <td>
                                        <!-- Mostrar el botón "+" solo si el usuario es un cliente con suscripción vigente -->
                                        <c:if test="${userRole == 'cliente' && suscripcionValida}">
                                            <form action="SVGuardarTemaFavorito" method="post" style="display:inline;">
                                                <input type="hidden" name="temaId" value="${tema.id}" />
                                                <button type="submit">+</button>
                                            </form>
                                        </c:if>
                                        <!-- Mostrar el enlace de descarga solo si el usuario es un cliente con suscripción vigente -->
                                        <c:if test="${userRole == 'cliente' && suscripcionValida && tema.url != null}">
                                            <a href="${tema.url}">Descargar</a>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </section>
            </main>
    </body>
</html>
