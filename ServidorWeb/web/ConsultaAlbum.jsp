<%-- 
    Document   : ConsultaAlbum.jsp
    Created on : 31 oct. 2024, 10:08:08 p. m.
    Author     : brisa
--%>
<%@page import="espotify.DataTypes.DTGenero"%>
<%@page import="espotify.DataTypes.DTDatosCliente"%>
<%@page import="espotify.DataTypes.DTDatosUsuario"%>
<%@page import="java.util.List" %>
<%@page import="espotify.DataTypes.DTAlbum" %>
<%@page import="espotify.DataTypes.DTTemaSimple"%>
<%@page import="espotify.DataTypes.DTTemaGenericoConRutaOUrl"%>
<%@ page import="espotify.logica.Fabrica" %>
<%@ page import="espotify.logica.IControlador" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%
String albumIdStr = request.getParameter("albumId");
 Long albumId = null;

if(albumIdStr != null && !albumIdStr.isEmpty()){

        albumId = Long.valueOf(albumIdStr);
}

String errorMsg = null;

Fabrica f = Fabrica.getInstance();
IControlador iControlador = f.getControlador();
DTAlbum album = null;
String artista = null;


try {
    album = iControlador.ConsultaAlbum(albumId);
    
    
    if (album != null) {
        String fotoAlbum = album.getFotoAlbum();
        if (fotoAlbum != null && !fotoAlbum.isEmpty()) {
            fotoAlbum = fotoAlbum.substring(2);
        } else {
            fotoAlbum = "Resource/ImagenesPerfil/Default-Photo-Profile.jpg";
        }
    }
} catch (Exception e) {
    errorMsg = "Error al obtener los datos del album: " + e.getMessage();
}

    // Comprobar suscripcion vigente
    HttpSession sesion = request.getSession();
    String nicknameSesion = (String) sesion.getAttribute("nickname");
    String rolSesion = (String) sesion.getAttribute("rol");
    DTDatosUsuario datosU = null;
    DTDatosCliente datosC = null;
    String estadoSuscripcionSesion = null;

    if ("Cliente".equals(rolSesion) && nicknameSesion != null) {
        try {
            datosU = iControlador.getDatosUsuario(nicknameSesion);
            datosC = (DTDatosCliente) datosU;
            if (datosC.getSuscripcion() != null) {
                estadoSuscripcionSesion = datosC.getSuscripcion().getEstadoSuscripcion();
            }
        } catch (Exception e) {
            errorMsg = "Error al obtener datos del usuario: " + e.getMessage();
        }
    }
    // Comprobar si puede descargar
    boolean puedeDescargar = "Vigente".equals(estadoSuscripcionSesion);
%>
    <head>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" 
          rel="stylesheet" 
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" 
          crossorigin="anonymous"
          >
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <script src="scripts/reproductorDeMusica.js" defer></script>
        <script src="scripts/ConsultaAlbum.js" defer></script>
        <link rel="stylesheet" href="styles/variablesGlobales.css"/>
        <link rel="stylesheet" href="styles/reproductorDeMusica.css"/>
        <link rel="stylesheet" href="styles/clasesAuxiliares.css"/>
        <link rel="stylesheet" href="styles/DatosAlbum.css"/>
    </head>

    <jsp:include page="headerIndex.jsp"/>

    <body>
        <div class="containerMain">
        <div class="album-detalles">
            <div class="info-container">
                <div class="album-fotoAlbum">
                    <!-- Foto del album-->
                    <%
                        String fotoAlbum = album != null ? album.getFotoAlbum() : null;
                        if (fotoAlbum != null && !fotoAlbum.isEmpty()) {
                            fotoAlbum = fotoAlbum.substring(2);
                        } else {
                            fotoAlbum = "Resource/ImagenesPerfil/Default-Photo-Profile.jpg";
                        }
                    %>
                    <img src="<%= request.getContextPath() + "/" + fotoAlbum%>" alt="Imagen del album" />
                </div>


                <div class="album-info">
                    <!-- Nombre, Artista y Generos del album -->
                    <h1><%= album != null ? album.getNombreAlbum() : "Album no encontrado"%></h1> 
                    <p><span>Artista:</span> <%= artista != null ? artista : "Desconocido"%></p>
                    <% 
                        StringBuilder generosConcat = new StringBuilder();
                        if(album != null){
                            List<DTGenero> generos = album.getMisgeneros(); 
                        
                            for (int i = 0; i < generos.size(); i++) {
                                generosConcat.append(generos.get(i).getNombreGenero());
                                if (i < generos.size() - 1) {
                                    generosConcat.append(", ");
                                }
                            }
                        }
                    %>
                    <p><span>Géneros:</span> <%= album != null ? generosConcat.toString() : "N/A"%></p>

                    <p><span>Año:</span> <%= album != null ? album.getAnioCreacion() : "N/A"%></p>
                    

                   <%
                       if(rolSesion != null && puedeDescargar && rolSesion != "Artista"){
                       %>
                       
                    <!-- Agregar album a favoritos -->
                    <form action="SVGuardarAlbumFavorito" method="post">
                        <%
                            System.out.println("Aca"+albumId);
                            %>
                        <input type="hidden" name="albumId" value="<%= albumId %>"/>
                        <input type="hidden" name="nickname" value="<%= nicknameSesion %>"/>
                        <button type="submit" class="boton-agregar">Guardar</button>
                    </form>
                    <%
                    }
                    %>

                </div>

            </div>

            <div class="separador"></div>

            <div class="temas-album">
                <!-- Temas de la lista -->
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
                    
                        <% int nroTema = 1; %>
                        <% if (album != null) { %>
                                 <% for (DTTemaSimple tema : album.getMisTemasSimple()) { %>
                                     <%
                                    int duracionSegundos = tema.getDuracionSegundos();
                                    int minutos = duracionSegundos / 60;
                                    int segundos = duracionSegundos % 60;

                                    DTTemaGenericoConRutaOUrl temaRutaOUrl = iControlador.getDTTemaGenericoConRutaOUrl(tema.getIdTema());
                                    String srcPortada = fotoAlbum;
                                    %>
                        <!-- Escuchar tema por fila -->

                        
                        <tr class="row-hover" onclick="play('<%= tema.getIdTema()%>', '<%= tema.getNombreTema()%>', '<%= srcPortada%>')">
                            <td><%= nroTema++%></td>
                            <td>
                                <%
                                    if(rolSesion != null && puedeDescargar && rolSesion != "Artista"){
                                %>
                                <form action="SVGuardarTemaFavorito" method="post">
                                    <input type="hidden" name="idTema" value="<%= tema.getIdTema()%>"/> 
                                    <input type="hidden" name="nickname" value="<%= nicknameSesion %>"/>
                                    <input type="hidden" name="tipo" value="Album"/>
                                    <input type="hidden" name="identificador" value="<%= albumId %>"/>
                                    <button type="submit" class="agregar">+</button> 
                                </form>
                                <%
                                }
                                %>

                            </td>
                            <td><%= tema.getNombreTema()%></td>
                            <td><%= String.format("%d:%02d", minutos, segundos)%></td>
                            <td class="ruta-link">  
                                <%
                                    String url = (temaRutaOUrl != null) ? temaRutaOUrl.getUrlTema() : null;
                                    String ruta = (temaRutaOUrl != null) ? temaRutaOUrl.getRutaTema() : null;
                                    // "Ver enlace"
                                    if (url != null && !url.isEmpty()) {
                                        if (!url.startsWith("http://") && !url.startsWith("https://")) {
                                            url = "http://" + url;
                                        }
                                %>
                                <a href="<%= url%>" target="_blank">Ver enlace</a>
                                <%
                                    }
                                    // "Descargar"
                                    if (ruta != null && !ruta.isEmpty()) {
                                        ruta = ruta.substring(ruta.lastIndexOf("Resource/"));

                                        if (puedeDescargar) {
                                %>
                                <a href="<%= request.getContextPath() + "/" + ruta%>" download="<%= tema.getNombreTema()%>.mp3">Descargar</a>
                                <%
                                } else { %>
                                <a href="#" onclick="alert('Debe tener una suscripción vigente para descargar el tema.'); return false;">Descargar</a>
                                <%
                                        }
                                    }
                                    // Si no hay URL ni archivo disponible
                                    if ((ruta == null || ruta.isEmpty()) && (url == null || url.isEmpty())) { %>
                                <span>-</span>
                                <%
                                    }
                                %>
                            </td>
                        </tr>
                        <%
                                }
                            }
                        %>
                    
                </table>
            </div>
        </div>
        
        <%@ include file="../WEB-INF/jspf/ReproductorDeMusica.jspf" %>
    </div>
    </body>

</html>

