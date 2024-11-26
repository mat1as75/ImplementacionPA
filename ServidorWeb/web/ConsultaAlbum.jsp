<%@page import="espotify.DataTypes.DTGenero"%>
<%@page import="java.util.List" %>
<%@page import="webservices.DataTypes.DtAlbum"%>
<%@page import="webservices.DataTypes.DtUsuarioGenerico"%>
<%@page import="webservices.DataTypes.DtGenero"%>
<%@page import="webservices.DataTypes.DtTemaSimple"%>
<%@page import="webservices.DataTypes.DtTemaGenericoConRutaOUrl"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="webservices.NullableContainer"%>
<%@page import="webservices.UsuarioServiceService"%>
<%@page import="webservices.UsuarioService"%>
<%@page import="webservices.ContenidoServiceService"%>
<%@page import="webservices.ContenidoService"%>
<%@page import="webservices.PreferenciasServiceService"%>
<%@page import="webservices.PreferenciasService"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%
    UsuarioServiceService serviceU = new UsuarioServiceService();
    UsuarioService serviceUsuario = serviceU.getUsuarioServicePort();
    
    ContenidoServiceService serviceC = new ContenidoServiceService();
    ContenidoService serviceContenido = serviceC.getContenidoServicePort();
    
String albumIdStr = request.getParameter("albumId");
 Long albumId = null;

if(albumIdStr != null && !albumIdStr.isEmpty()){

        albumId = Long.valueOf(albumIdStr);
}

String errorMsg = null;

DtAlbum album = null;
String artista = null;


try {
    album = serviceContenido.consultaAlbum(albumId).getDtAlbum();
    artista = album.getMiArtista().getNickname();
    
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
    DtUsuarioGenerico datosU = null;
    String estadoSuscripcionSesion = null;

    if ("Cliente".equals(rolSesion) && nicknameSesion != null) {
        try {
            datosU = serviceUsuario.getDatosUsuario(nicknameSesion).getDtUsuarioGenerico();
            if (datosU.getSuscripcion() != null) {
                estadoSuscripcionSesion = datosU.getSuscripcion().getEstadoSuscripcion();
            }
        } catch (Exception e) {
            errorMsg = "Error al obtener datos del usuario: " + e.getMessage();
        }
    }
    // Comprobar si puede descargar
    boolean puedeDescargar = "Vigente".equals(estadoSuscripcionSesion);
    
    // Comprobar favortios
    boolean albumEsFavorito = false;
    boolean temaEsFavorito = false;
    
    ContenidoServiceService cservice = new ContenidoServiceService();
    ContenidoService contenidoService = cservice.getContenidoServicePort();
    
    albumEsFavorito = contenidoService.esAlbumFavorito(nicknameSesion, album.getIdAlbum());
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
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <link rel="stylesheet" href="styles/nav.css"/>
        <link rel="stylesheet" href="styles/variablesGlobales.css"/>
        <link rel="stylesheet" href="styles/reproductorDeMusica.css"/>
        <link rel="stylesheet" href="styles/clasesAuxiliares.css"/>
        <link rel="stylesheet" href="styles/DatosAlbum.css"/>
    </head>

    <body>
        <jsp:include page="headerIndex.jsp"/>
        <%@ include file="../WEB-INF/jspf/Nav.jspf" %>

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
                            List<DtGenero> generos = album.getMisgeneros();
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
                       if(!albumEsFavorito && rolSesion != null && puedeDescargar && rolSesion != "Artista"){
                       %>
                       
                    <!-- Agregar album a favoritos -->
                    <form action="SVGuardarAlbumFavorito" method="post" onclick="GuardarAlbumFavorito()">
                        <input type="hidden" id="albumId" name="albumId" value="<%= albumId %>"/>
                        <input type="hidden" id="nickname" name="nickname" value="<%= nicknameSesion %>"/>
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
                        </tr>
                    </thead>
                    
                        <% int nroTema = 1; %>
                        <% if (album != null) { %>
                                 <% for (DtTemaSimple tema : album.getMisTemasSimples()) { %>
                                     <%
                                        int duracionSegundos = tema.getDuracionSegundos();
                                        int minutos = duracionSegundos / 60;
                                        int segundos = duracionSegundos % 60;

                                        DtTemaGenericoConRutaOUrl temaRutaOUrl = serviceContenido.getDTTemaGenericoConRutaOUrl(tema.getIdTema());
                                        String srcPortada = fotoAlbum;
                                    %>
                        <!-- Escuchar tema por fila -->

                        
                        <tr class="row-hover" onclick="play('<%= tema.getIdTema()%>', '<%= tema.getNombreTema()%>', '<%= srcPortada%>')">
                            <td><%= nroTema++%></td>
                            <td>
                                <%
                                    if(!contenidoService.esTemaFavorito(nicknameSesion, tema.getIdTema()) && rolSesion != null && puedeDescargar && rolSesion != "Artista"){
                                %>
                                <form action="SVGuardarTemaFavorito" method="post" onclick="GuardarTemaFavorito()">
                                    <input type="hidden" id="idTema" name="idTema" value="<%= tema.getIdTema()%>"/> 
                                    <input type="hidden" id="nicknameSesion" name="nickname" value="<%= nicknameSesion %>"/>
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
                        </tr>
                        <%
                                }
                            }
                        %>
                    
                </table>
            </div>   
        </div>
        
        <div class="contenedor-reproductor">
            <%@ include file="../WEB-INF/jspf/ReproductorDeMusica.jspf" %>
        </div>
    </div>
    </body>

</html>

