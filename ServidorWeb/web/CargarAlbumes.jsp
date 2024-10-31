<%-- 
    Document   : CargarAlbumes
    Created on : 26 oct. 2024, 1:05:59 p. m.
    Author     : brisa
--%>

<%@page import="espotify.logica.IControlador"%>
<%@page import="espotify.logica.Fabrica"%>
<%@page import="espotify.DataTypes.DTDatosCliente"%>
<%@page import="espotify.DataTypes.DTDatosUsuario"%>
<%@page import="java.util.List"%>
<%@page import="espotify.DataTypes.DTAlbum"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    Fabrica f = Fabrica.getInstance();
    IControlador iControlador = f.getControlador();
    HttpSession sesion = request.getSession();
    String nicknameSesion = (String) sesion.getAttribute("nickname");
    String rolSesion = (String) sesion.getAttribute("rol");
    DTDatosUsuario datosU = null;
    DTDatosCliente datosC = null;
    String estadoSuscripcionSesion = null;
    String errorMsg = null;

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
<html>
<head>
    <title>Álbumes</title>
    <link rel="stylesheet" href="styles/albumes.css">
</head>
<body>

    <h1>Álbumes por <%= request.getAttribute("tipoBusqueda") != null ? request.getAttribute("tipoBusqueda") : "" %></h1>

    <%
        List<DTAlbum> albumes = (List<DTAlbum>) request.getAttribute("albumes");
        errorMsg = (String) request.getAttribute("errorMsg");

        if (errorMsg != null) {
    %>
        <p class="error"><%= errorMsg %></p>
    <% 
        } else if (albumes != null && !albumes.isEmpty()) { 
            for (DTAlbum album : albumes) {
    %>
                <div class="album-item">
                    <img src="<%= request.getContextPath() + "/" + album.getFotoAlbum() %>" alt="Foto de <%= album.getNombreAlbum() %>">
                    <h2><%= album.getNombreAlbum() %></h2>
                </div>
    <%
            }
        } else {
    %>
        <p>No se encontraron álbumes.</p>
    <%
        }
    %>
</body>
</html>

