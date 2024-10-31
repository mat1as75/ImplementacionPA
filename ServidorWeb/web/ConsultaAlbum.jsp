<%-- 
    Document   : ConsultaAlbum
    Created on : 22 oct. 2024, 9:48:42 a. m.
    Author     : brisa
--%>

<%@page import="espotify.DataTypes.DTTemaGenerico"%>
<%@page import="espotify.logica.IControlador"%>
<%@page import="espotify.logica.Fabrica"%>
<%@page import="espotify.DataTypes.DTTemaGenericoConRutaOUrl"%>
<%@page import="espotify.DataTypes.DTTemaSimple"%>
<%@page import="espotify.DataTypes.DTGenero"%>
<%@page import="java.util.List"%>
<%@page import="espotify.DataTypes.DTAlbum"%>
<%@page import="espotify.DataTypes.DTArtista"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    Fabrica fabrica = Fabrica.getInstance();
    IControlador iControlador = fabrica.getControlador();


%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${album.nombreAlbum}</title>
    </head>
    <body>
            <main>
                <% DTAlbum datosAlbum = (DTAlbum) request.getAttribute("datosAlbum"); %>
<div class="info-container">
    <div class="album-fotoAlbum">
        <img src="<%= request.getContextPath() + "/" + (datosAlbum.getFotoAlbum() != null ? datosAlbum.getFotoAlbum().substring(2) : "Resource/ImagenesPerfil/Default-Photo-Profile.jpg") %>" alt="Imagen del álbum" />
    </div>

    <div class="album-info">
        <h1><%= datosAlbum.getNombreAlbum()%></h1> 
        <p><span>Artista:</span> <%= datosAlbum.getMiArtista().getNombreUsuario()%></p>
        <% 
    List<DTGenero> generos = datosAlbum.getMisgeneros(); 
    StringBuilder generosConcat = new StringBuilder();
    for (int i = 0; i < generos.size(); i++) {
        generosConcat.append(generos.get(i).getNombreGenero());
        if (i < generos.size() - 1) {
            generosConcat.append(", ");
        }
    }
%>
<p><span>Género:</span> <%= generosConcat.toString() %></p>

    </div>
</div>

<div class="temas-album">
    <!-- Tabla de temas del álbum -->
    <table class="tabla-temas">
        <thead>
            <tr>
                <th>#</th>
                <th>Agregar</th>
                <th>Tema</th>
                <th>Duración</th>
                <th>Ruta/Link</th>
            </tr>
        </thead>
        <tbody>
            <% int nroTema = 1; %>
            <% if (datosAlbum != null) {
                    for (DTTemaGenerico tema : datosAlbum.getMisTemas()) {
                        int duracionSegundos = tema.getDuracionSegundos();
                        int minutos = duracionSegundos / 60;
                        int segundos = duracionSegundos % 60;

                        DTTemaGenericoConRutaOUrl temaRutaOUrl = iControlador.getDTTemaGenericoConRutaOUrl(tema.getId());
                        String srcPortada = datosAlbum.getFotoAlbum();
            %>
            <tr class="row-hover" onclick="play('<%= tema.getId()%>', '<%= tema.getNombreTema()%>', '<%= srcPortada%>')">
                <td><%= nroTema++%></td>
                <td>
                    <form action="SVGuardarTemaFavorito" method="post">
                        <input type="hidden" name="idTema" value="<%= tema.getId()%>"/> 
                        <button type="submit" class="agregar">+</button> 
                    </form>
                </td>
                <td><%= tema.getNombreTema()%></td>
                <td><%= String.format("%d:%02d", minutos, segundos)%></td>
                <td class="ruta-link">  
                    <% String url = (temaRutaOUrl != null) ? temaRutaOUrl.getUrlTema() : null;
                       String ruta = (temaRutaOUrl != null) ? temaRutaOUrl.getRutaTema() : null;
                       if (url != null && !url.isEmpty()) { 
                           if (!url.startsWith("http://") && !url.startsWith("https://")) { url = "http://" + url; }
                    %>
                    <a href="<%= url%>" target="_blank">Ver enlace</a>
                    <% } if (ruta != null && !ruta.isEmpty()) {
                           ruta = ruta.substring(ruta.lastIndexOf("Resource/"));
                           if (puedeDescargar) { %>
                    <a href="<%= request.getContextPath() + "/" + ruta%>" download="<%= tema.getNombreTema()%>.mp3">Descargar</a>
                    <% } else { %>
                    <a href="#" onclick="alert('Debe tener una suscripción vigente para descargar el tema.'); return false;">Descargar</a>
                    <% } } if ((ruta == null || ruta.isEmpty()) && (url == null || url.isEmpty())) { %>
                    <span>-</span>
                    <% } %>
                </td>
            </tr>
            <% } } %>
        </tbody>
    </table>
</div>
    </body>
</html>
