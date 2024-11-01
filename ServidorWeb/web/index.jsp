<%@page import="espotify.DataTypes.DTAlbum_Simple"%>
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
    <script src="scripts/index.js"pefer></script>

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
    /*
        PONER ESTILOS DE DIV-CONTENT PARA QUE LOS DIVS TABS Y REPRODUCTOR QUEDEN EN COLUMNA
    */
    %>
    <%
     Fabrica fabrica = Fabrica.getInstance();
     IControlador controlador = fabrica.getControlador();
     String artistaStr=null; 
    %>

    <!-- TABS-CONTENT -->
    <div class="tabs">
        <ul class="tab-links">
            <li class="active"><a href="#tab1">Generos</a></li>
            <li><a href="#tab2">Artistas</a></li>
            <li><a href="#tab3">Listas Particulares</a></li>

        </ul>

        
            <div id="tab1" class="tab active">
                <div class="mosaico-container"> 
                    <div class="mosaico" id="mosaicoGeneros">
                        <% 
                                       
                            ArrayList<String> generos = controlador.getNombresGenerosPadre();
                            if (generos != null && !generos.isEmpty()) {
                                for (String genero : generos) {
                        %>
                        <div class="mosaico-item" onclick="MostrarPorGenero('<%= genero %>')"><%= genero %></div>
                        <!-- LISTADO DE ÁLBUMES Y LISTAS DE REPRODUCCIÓN POR GÉNERO -->
                        <div class="listas-genero" id="contenidoGenero_<%= genero %>" style="display: none;">
                            <!-- Álbumes del Género -->
                            <h3>Álbumes de <%= genero %></h3>
                            <ul>
                                <% 
                                    ArrayList<DTAlbum_Simple> albumes = controlador.getDTAlbumesSimplePorGenero(genero);
                                    if (albumes != null && !albumes.isEmpty()) {
                                        for (DTAlbum_Simple album : albumes) {
                                %>
                                <li><%= album.getNombreAlbum() %></li>
                                    <% 
                                            }
                                        } else { 
                                    %>
                                <p>No hay álbumes disponibles para este género.</p>
                                <% 
                                    } 
                                %>
                            </ul>

                            <!-- Listas de Reproducción del Género -->
                            <h3>Listas de Reproducción de <%= genero %></h3>
                            <ul>
                                <% 
                                    ArrayList<String> listasReproduccion = controlador.getNombresListasPorDefecto();
                                    if (listasReproduccion != null && !listasReproduccion.isEmpty()) {
                                        for (String lista : listasReproduccion) {
                                %>
                                <li><%= lista %></li>
                                    <% 
                                            }
                                        } else { 
                                    %>
                                <p>No hay listas de reproducción disponibles para este género.</p>
                                <% 
                                    } 
                                %>
                            </ul>
                        </div>
                        <% 
                                }
                            } else { 
                        %>
                        <p>No hay generos disponibles.</p>
                        <% 
                            } 
                        %>
                    </div>
                </div>  
            </div>
            <div id="tab2" class="tab">
                <h1>Artistas</h1>
                <!-- Contenedor de artistas en mosaico -->
                 
                    <div class="mosaico" id="mosaicoArtistas">
                        <% 
                                // Obtener la lista de artistas 
                        ArrayList<String> artistas = controlador.getNicknamesArtistas();
                        if (artistas != null && !artistas.isEmpty()) {
                            for (String artista : artistas) {
                        %>
                        <!-- Al hacer clic en un artista, se mostrará el contenedor de álbumes -->
                        <div class="mosaico-item" onclick="AlbumArtista('<%= artista %>')"><%= artista %></div>
                        <% 
                           artistaStr = artista;
                           }  
                        } else { 
                        %>
                        <p>No hay artistas disponibles.</p
                        <% 
                            } 
                        %>
                    </div>
                </div>
                <!-- Contenedor para mostrar los álbumes del artista seleccionado -->
                <div class="mosaico-container"> 
                    <div class="mosaico" id="mosaico-Albumes">
                        
                    
                        <%
                        ArrayList<DTAlbum_Simple> albumes = controlador.getDTAlbumesSimplePorArtista(artistaStr);
                        if(albumes !=null && !albumes.isEmpty()&&artistaStr!=null){
                            for(DTAlbum_Simple album: albumes){
                            %>
                               <div class="mosaico-item" onclick="DatosAlbum('<%= album.getIdAlbum() %>')"><%= album.getNombreAlbum() %>
                               </div> 
                            <%}
                        }
                        %>
                    </div>
                </div> 
                <link rel="stylesheet" href="styles/CargaAlbumes.css">
            </div>
            <div id="tab3" class="tab">
                <h1>Consulta Lista Particular</h1>
                <div class="mosaico-container"> 
                    <div class="mosaico" id="mosaicoListasParticulares">
                        <% 
                                        
                
                            ArrayList<String> listasPublicas = controlador.getNombresListasParticularesPublicas();
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



    <%
//                    ArrayList<String> nombresAlbumes = new ArrayList<>();
//                    ArrayList<String> nombresListasR = new ArrayList<>();
//                    
//                    String nombreGenero = null;
//                    
//                    <% if () { %>




</body>
