<%@page import="espotify.DataTypes.DTDatosUsuario"%>
<%@page import="espotify.logica.IControlador"%>
<%@page import="espotify.logica.Fabrica"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="es">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <script src="scripts/script.js"></script>
        <link rel="stylesheet" href="styles/index.css"/> <!-- CSS DEL BODY -->

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link rel="icon" href="resources/logos--spotify-icon.png" type="image/png" sizes="16x16">
    </head>
    
        <jsp:include page="headerIndex.jsp"/>
        
        <%
            HttpSession sesion = request.getSession(false);
            DTDatosUsuario usuario = (DTDatosUsuario) sesion.getAttribute("usuario");
            if (usuario == null) { /* NO EXISTE USUARIO */
                sesion.setAttribute("rol", "Visitante");
            }
            
        %>
        
        <body>
            
            <% if (sesion.getAttribute("rol").equals("Visitante")) { %>
                <h3>SOY VISITANTE</h3>
            <% } else {%>
                <h3>SOY <%= (String) sesion.getAttribute("rol") %></h3>
            <% } %>

            
        </body>

</html>