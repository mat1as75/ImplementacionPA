<%@page import="espotify.DataTypes.DTDatosCliente"%>
<%@page import="java.util.Map"%>
<%@page import="espotify.DataTypes.DTDatosUsuario"%>
<%@page import="espotify.logica.IControlador"%>
<%@page import="espotify.logica.Fabrica"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true"%>

<!DOCTYPE html>
<html lang="es">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <script src="scripts/index.js"></script>
        <link rel="stylesheet" href="styles/index.css"/> <!-- CSS DEL BODY -->

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link rel="icon" href="Resource/ImagenesPerfil/espotify-icon.png" type="image/png" sizes="16x16">
    </head>
    
        <%
            // Verifico si la sesion ya existe
            if (session.isNew()) {
                // Si es nueva sesion, establezco el atributo rol
                session.setAttribute("rol", "Visitante");
            }
            
            String rolSesion = (String) session.getAttribute("rol");
            
        %>
    
        <jsp:include page="headerIndex.jsp"/>
    
        <body>
            
            <h3>SOY <%= rolSesion %></h3>

            <%  /* Mensaje de InicioSesion */
                String mensajeInicioSesion = request.getParameter("mensaje");
                if ("abierta".equals(mensajeInicioSesion)) {
            %>
            <script>
                mostrarMensajeInicioSesion("Sesion Iniciada de forma exitosa!");
            </script>
            <%  }  %>
            
            <%  /* Mensaje de CierreSesion */
                String mensajeCierreSesion = request.getParameter("mensaje");
                if ("cerrada".equals(mensajeCierreSesion)) {
            %>
            <script>
                mostrarMensajeCierreSesion("Sesion Cerrada de forma exitosa!");
            </script>
            <%  }  %>
            
            <%  /* Muestra Resultados de la SearchBar */
                Map<String, String> resultados = (Map<String, String>) request.getAttribute("resultados");
                if (resultados != null) {
            %>
                <jsp:include page="resultadosBusqueda.jsp" />
            <%  }  %>
            
        </body>

</html>