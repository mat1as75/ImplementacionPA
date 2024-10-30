<%@page import="java.util.ArrayList"%>
<%@page import="espotify.DataTypes.DTDatosCliente"%>
<%@page import="java.util.Map"%>
<%@page import="espotify.DataTypes.DTDatosUsuario"%>
<%@page import="espotify.logica.IControlador"%>
<%@page import="espotify.logica.Fabrica"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="scripts/index.js"></script>
        
        <link rel="stylesheet" href="styles/index.css"/> <!-- CSS DEL BODY -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link rel="stylesheet" href="styles/nav.css"/>
        <link rel="icon" href="Resource/ImagenesPerfil/espotify-icon.png" type="image/png" sizes="16x16">
    </head>

        <jsp:include page="headerIndex.jsp" />
        <%@ include file="../WEB-INF/jspf/Nav.jspf" %>
   
        <body>
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
            
            <%
            /*
                PONER ESTILOS DE DIV-CONTENT PARA QUE LOS DIVS TABS Y REPRODUCTOR QUEDEN EN COLUMNA
            */
            %>
            
            
                <!-- TABS-CONTENT -->
                <div class="tabs">
                    <ul class="tab-links">
                        <li class="active"><a href="#tab1">Generos</a></li>
                        <li><a href="#tab2">Artistas</a></li>
                        <li><a href="#tab3">Suscripcion</a></li>
                    </ul>

                    <div class="tab-content">
                        <div id="tab1" class="tab active">
                            <div class="lista-Albumes"></div>
                            <div class="lista-ListasR"></div>
                            
                        </div>
                        <div id="tab2" class="tab">
                            <p>Tab #2 content goes here!</p>
                        </div>
                        <div id="tab3" class="tab">
                            <p>Tab #3 content goes here!</p>
                        </div>
                    </div>
                </div>

                <!-- REPRODUCTOR-CONTENT -->
                <div class="reproductor">

                </div>
                
                <%
//                    ArrayList<String> nombresAlbumes = new ArrayList<>();
//                    ArrayList<String> nombresListasR = new ArrayList<>();
//                    
//                    String nombreGenero = null;
//                    
//                    <% if () { %>
                
                
                
            
        </body>
