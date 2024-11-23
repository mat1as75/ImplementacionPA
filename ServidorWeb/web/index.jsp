<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="webservices.ContenidoService"%>
<%@page import="webservices.ContenidoServiceService"%>
<%@page import="webservices.UsuarioService"%>
<%@page import="webservices.UsuarioServiceService"%>
<%@page import="webservices.ListaReproduccionService"%>
<%@page import="webservices.ListaReproduccionServiceService"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Espotify</title>
        <link rel="stylesheet" href="styles/index.css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link rel="stylesheet" href="styles/nav.css"/>
        <link rel="stylesheet" href="styles/variablesGlobales.css"/>
        <link rel="icon" href="Resource/ImagenesPerfil/espotify-icon.png" type="image/png" sizes="16x16">
        <link rel="stylesheet" href="styles/ConsultaListaReproduccion.css">
        <style>
            /* Ocultar elementos al inicio */
            
            #post-seleccion, #volver, #volver2, #post-seleccion2 {
                display: none;
            }
            h1, h2, h3 {
                text-align: center;
                margin-bottom: 20px;
            }
            #volver,#volver2{
                font-size: 1.5em;
            }
        </style>
    </head>
    <body>
        <jsp:include page="headerIndex.jsp" />
        <%@ include file="../WEB-INF/jspf/Nav.jspf" %>

        <%  
            ContenidoServiceService contenidoWS = new ContenidoServiceService();
            ContenidoService contenidoPort = contenidoWS.getContenidoServicePort();
            UsuarioServiceService usuarioWS = new UsuarioServiceService();
            UsuarioService usuarioPort = usuarioWS.getUsuarioServicePort();
            ListaReproduccionServiceService listaRepWS = new ListaReproduccionServiceService();
            ListaReproduccionService listaRepPort = listaRepWS.getListaReproduccionServicePort();
            
            HttpSession sesionIndex = request.getSession(false);
            String rolSesionIndex = null;

            if (sesionIndex != null) {
                rolSesionIndex = (String) sesionIndex.getAttribute("rol");
            }
        %>

        <script>
            function mostrarCartelAviso() {
                document.getElementById('cartel').style.display = 'block';
            }

            function cerrarCartelAviso() {
                document.getElementById('cartel').style.display = 'none';
            }
        </script>
        
        <%  String mensajeRequest = request.getParameter("mensaje");
            String mensajeCartel = null;
            if (mensajeRequest != null) { 
                switch(mensajeRequest) {
                case "abierta":
                    mensajeCartel = "Sesion Iniciada Exitosamente!";
                    break;
                case "cerrada": 
                    mensajeCartel = "Sesion Cerrada Exitosamente!";
                    break;
                case "inactiva":
                    mensajeCartel = "Cuenta Desactivada & Sesion Cerrada Exitosamente!";
                    break;
                } %>
            
            <!-- CARTEL DE CUENTA DESACTIVADA -->
            <div id="cartel" class="overlay">
                <div class="cartel">
                    <h2>¡AVISO!</h2>
                    <p><%= mensajeCartel %></p>
                    <button class="cerrar-btn" onclick="cerrarCartelAviso()">Cerrar</button>
                </div>
            </div>
            <script>
                mostrarCartelAviso();
            </script>
        <%  }  %>
        
        <% if (!"Artista".equals(rolSesionIndex)) { %>
        <!-- TABS-CONTENT -->
        <div class="tabs">
            <ul class="tab-links">
                <li class="active"><a href="#tab1">Géneros</a></li>
                <li><a href="#tab2">Artistas</a></li>
               
                <li><a href="#tab3">Listas Particulares</a></li>
            </ul>

            <div class="tab-content">
                <div id="tab1" class="tab active">
                    <button type="button" id="volver" onclick="VolverAGeneros()">Volver</button>
                    <div id="pre-seleccion">
                        <h1>Géneros</h1>
                        <div class="mosaico-container">
                            <div class="mosaico" id="mosaicoGeneros">
                                <%
                                    ArrayList<String> generos = new ArrayList();
                                    List<Object> listaObjGeneros = contenidoPort.getNombresGenerosPadre().getColeccion();
                                    for (Object o : listaObjGeneros) {
                                        String generoObj = (String) o;
                                        generos.add(generoObj);
                                    }
                                    
                                    if (generos != null && !generos.isEmpty()) {
                                        for (String gen : generos) {
                                %>
                                <div class="mosaico-item" onclick="CargaTabGenero('<%= gen %>')"><%= gen %></div>
                                <%
                                        }
                                    } else {
                                %>
                                <p>No hay géneros disponibles</p>
                                <%
                                    }
                                %> 
                            </div>
                        </div>
                    </div>
                    <div id="post-seleccion">
                        <h2>Resultados del genero</h2>
                        <div class="mosaico-container">
                            
                            <h3>Albumes</h3>  
                            <div class="mosaico" id="mosaicoAlbumesPorGenero" style="display:none;">

                                <c:if test="${not empty albumesGenero}">
                                    <c:forEach var="album" items="${albumesGenero}">
                                        <c:if test="${not empty album.nombre}">
                                            <div class="mosaico-item" onclick="DatosAlbum('${album.id}')">${album.nombre}</div> 
                                        </c:if>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${empty albumesGenero}">
                                    <p>No hay álbumes de este género</p>
                                </c:if>

                            </div> 
                            <div class="divisor d-none d-sm-block"></div>
                            <h3>Listas de Reproducción</h3>   
                            <div class="mosaico" id="mosaicoListasPorDefecto" style="display:none;">
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
                </div>

                <div id="tab2" class="tab">
                    <button type="button" id="volver2" onclick="VolverAArtistas()">Volver</button>
                    <div id="pre-seleccion2">
                        <h1>Artistas</h1>
                        <div class="mosaico-container">
                            <div class="mosaico" id="mosaicoArtista">
                                <%
                                    List<String> artistas = new ArrayList();
                                    List<Object> listObjArtistas = usuarioPort.getNicknamesArtistas().getColeccion();
                                    for (Object o : listObjArtistas) {
                                        String nicknameArtistaObj = (String) o;
                                        artistas.add(nicknameArtistaObj);
                                    }
                                    
                                    if (artistas != null && !artistas.isEmpty()) {
                                        for (String art : artistas) {
                                %>
                                <div class="mosaico-item" onclick="CargaTabArtista('<%= art %>')"><%= art %></div>
                                <%
                                        }
                                    } else {
                                %>
                                <p>No hay artistas disponibles</p>
                                <%
                                    }
                                %> 
                            </div>
                        </div>
                    </div>
                    <div id="post-seleccion2">
                        <h2>Albumes del artista</h2>
                        <div class="mosaico-container">
                            <div class="mosaico" id="mosaicoAlbumesPorArtista" style="display:none;">
                                
                                <c:if test="${not empty albumesArtista}">
                                    <c:forEach var="album" items="${albumesArtista}">
                                        <c:if test="${not empty album.nombre}">
                                            <div class="mosaico-item" onclick="DatosAlbum('${album.id}')">${album.nombre}</div>
                                        </c:if>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${empty albumesArtista}">
                                    <p>Este artista no tiene álbumes publicados</p>
                                </c:if>
                            </div>
                        </div>
                    </div> 
                </div>

                <div id="tab3" class="tab">
                    <h1>Consulta Lista Particular</h1>
                    <div class="mosaico-container"> 
                        <div class="mosaico" id="mosaicoListasParticulares">
                            <%
                                
                                
                                List<String> listasPublicas = new ArrayList();
                                List<Object> listasPublicasObj = listaRepPort.getNombresListasParticularesPublicas().getColeccion();
                                for (Object o : listasPublicasObj) {
                                    String listaRepObj = (String) o;
                                    listasPublicas.add(listaRepObj);
                                }
                                
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
                </div>
            </div>
        </div>
        <% } %>
                    
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="scripts/index.js"></script>
    </body>
</html>

