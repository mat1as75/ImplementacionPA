<%@page import="java.util.List"%>
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
                        mostrarPopupSesion("Sesion Iniciada de forma exitosa!");
                    </script>
            <%  }  %>
            
            <%  /* Mensaje de CierreSesion */
                String mensajeCierreSesion = request.getParameter("mensaje");
                if ("cerrada".equals(mensajeCierreSesion)) {
            %>
                    <script>
                        mostrarPopupSesion("Sesion Cerrada de forma exitosa!");
                    </script>
            <%  }  %>
            
            <%  /* Muestra Resultados de la SearchBar */
                Map<String, String> resultados = (Map<String, String>) request.getAttribute("resultados");
                if (resultados != null) {
            %>
                    <jsp:include page="resultadosBusqueda.jsp" />
            <%  }  %>
            
            <%
            Fabrica fabrica = Fabrica.getInstance();
            IControlador controlador = fabrica.getControlador();  

            %>
            
            
                <!-- TABS-CONTENT -->
                <div class="tabs">
                    <ul class="tab-links">
                        <li class="active"><a href="#tab1">Generos</a></li>
                        <li><a href="#tab2">Artistas</a></li>
                        <li><a href="#tab3">Suscripcion</a></li>
                        <li><a href="#tab4">Listas Particulares</a></li>
                    </ul>

                    <div class="tab-content">
                        <div id="tab1" class="tab active">
                            <h1>Generos</h1>
                            <div class="mosaico-container">
                                <div class="mosaico" id="mosaicoGeneros">
                                    <%
                                  
                                    ArrayList<String> generos = controlador.getNombresGenerosPadre();
                                    if(generos != null && !generos.isEmpty()){
                                        for(String gen : generos){
                                    %>
                                    <div class="mosaico-item" onclick="CargaTabGenero('<%= gen %>')"><%= gen %></div>
                                    <%    
                                        }
                                    }else{
                                    %>
                                    <p>No hay generos disponibles</p>
                                    <%
                                    }
                                    %> 
                                    
                                </div>
                                    <div class="mosaico" id="mosaicoAlbumesPorGenero" style="display:none;">
                                        <h2>Albumes disponibles</h2>
                                        
                                        <c:if test="${empty albumesGenero}">
                                            <c:forEach var="album" items="${albumesGenero}">
                                                
                                                <div class="mosaico-item" onclick="DatosAlbum('${album}')">${album}</div>
                                            </c:forEach>
                                        </c:if>
                                            <c:if test="${empty albumesGenero}">
                                                <p>No hay albumes de este genero</p>
                                            </c:if>
                                    </div>  
                                    <div class="mosaico" id="mosaicoListasPorDefecto" style="display:none;">
                                        <h2>Listas por Defecto para el Género Seleccionado</h2>
                                        <c:if test="${not empty listaPorDefecto}">
                                            <c:forEach var="lista" items="${listaPorDefecto}">
                                                <div class="mosaico-item" onclick="DatosListaReproduccion('${lista}')">${lista}</div>
                                            </c:forEach>
                                        </c:if>
                                        <c:if test="${empty listaPorDefecto}">
                                            <p>No hay listas de reproducción por defecto disponibles para este género.</p>
                                        </c:if>
                                    </div>
                            </div>
                            
                            
                        </div>
                        <div id="tab2" class="tab">
                            <h1>Artistas</h1>
                            <div class="mosaico-container">
                                <div class="mosaico" id="mosaicoArtista">
                                    <%
                                  
                                    ArrayList<String> artistas = controlador.getNicknamesArtistas();
                                    if(artistas != null && !artistas.isEmpty()){
                                        for(String art : artistas){
                                    %>
                                    <div class="mosaico-item" onclick="CargaTabArtista(<%= art %>)"><%= art %></div>
                                    <%    
                                        }
                                    }else{
                                    %>
                                    <p>No hay artistas disponibles</p>
                                    <%
                                    }
                                    %> 
                                    
                                </div> 
                                    <div class="mosaico" id="mosaicoAlbumesPorArtista" style="display:none;">
                                        <h2>Albumes disponibles</h2>
                                        
                                        <c:if test="${empty albumesArtista}">
                                            <c:forEach var="album" items="${albumesArtista}">
                                                
                                                <div class="mosaico-item" onclick="DatosAlbum('${album}')">${album}</div>
                                            </c:forEach>
                                        </c:if>
                                            <c:if test="${empty albumesArtista}">
                                                <p>Este artista no tiene albumes publicados</p>
                                            </c:if>
                                    </div>
                            </div>
                        </div>
                        <div id="tab3" class="tab">
                            <p>Tab #3 content goes here!</p>
                        </div>
                        <div id="tab4" class="tab">
                            <h1>Consulta Lista Particular</h1>
                            <div class="mosaico-container"> 
                                <div class="mosaico" id="mosaicoListasParticulares">
                                    <% 
                                        
                                        List<String> listasPublicas = controlador.getNombresListasParticularesPublicas();
                                        if (listasPublicas != null && !listasPublicas.isEmpty()) {
                                            for (String lista : listasPublicas) {
                                    %>
                                    <div class="mosaico-item" onclick="DatosListaReproduccion('<%= lista %>')"><%= lista %></div>
                                    <% 
                                            }
                                        } else { 
                                    %>
                                    <p>No hay listas de reproducción disponibles.</p>
                                    <% 
                                        } 
                                    %>
                                </div>
                            </div> 
                            <link rel="stylesheet" href="styles/ConsultaListaReproduccion.css">
                        </div>
                    </div>
                </div>
            
        </body>
