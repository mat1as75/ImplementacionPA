<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.ArrayList"%>
<%@page import="espotify.DataTypes.DTDatosCliente"%>
<%@page import="espotify.DataTypes.DTDatosUsuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    HttpSession sesion = request.getSession();
    String tipoUsuario = (String) sesion.getAttribute("rol");
    String perfilConsultado = (String) sesion.getAttribute("perfilConsultado");
    
    DTDatosUsuario datosUsuarioSesion = datosUsuarioSesion = (DTDatosUsuario) sesion.getAttribute("usuario");
    DTDatosCliente datosClienteSesion = null;
    String nicknameUsuarioSesion = datosUsuarioSesion.getNicknameUsuario();
    
    String nicknameUsuario = null, nombreCompleto = null, fotoPerfilUsuario = null, emailUsuario = null, fechaNacUsuario = null;
    ArrayList<String> nicknamesSeguidores = null, nicknamesSeguidos = null, nicknamesS = null, rolS = null, 
            nombresListasRCreadas = null, nombresTemasFav = null, nombresAlbumesFav = null, nombresListasFav = null, 
            nombresListasRPublicas = null;
            
    nicknameUsuario = datosUsuarioSesion.getNicknameUsuario();
    nombreCompleto = datosUsuarioSesion.getNombreUsuario() + " " + datosUsuarioSesion.getApellidoUsuario();
    fotoPerfilUsuario = datosUsuarioSesion.getFotoPerfil();
    
    // Sesion no pertenece a un Visitante
    if (!tipoUsuario.equals("Visitante")) {
        datosClienteSesion = (DTDatosCliente) sesion.getAttribute("usuario");
        
        
    
        // Se quiere ConsultarPerfil propio
        if (nicknameUsuarioSesion.equals(perfilConsultado)) {
         
            /* DATOS DE CLIENTE */
            emailUsuario = datosUsuarioSesion.getEmail();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            fechaNacUsuario = dateFormat.format(datosUsuarioSesion.getFecNac());
            nicknamesSeguidores = datosUsuarioSesion.getNicknamesSeguidores();

            /* Extras de Cliente con Suscripcion */
            nicknamesSeguidos = datosClienteSesion.getNicknamesSeguidos();
            nicknamesS = new ArrayList<>();
            rolS = new ArrayList<>();
            for (String n : nicknamesSeguidos) {
                int i = n.indexOf(",");
                nicknamesS.add(n.substring(0, i));
            }
            for (String n : nicknamesSeguidos) {
                int i = n.indexOf(" ");
                rolS.add(n.substring(i + 1));
            }

            nombresListasRCreadas = null;
            nombresTemasFav = null;
            nombresAlbumesFav = null;
            nombresListasFav = null;
            if (datosClienteSesion.getSuscripcion() != null && datosClienteSesion.getSuscripcion().getEstadoSuscripcion().toString().equals("Vigente")) {
                nicknamesSeguidos = datosClienteSesion.getNicknamesSeguidos();
                nombresListasRCreadas = datosClienteSesion.getNombresListasRCreadas();
                nombresTemasFav = datosClienteSesion.getNombresTemasFavoritos();
                nombresAlbumesFav = datosClienteSesion.getNombresAlbumesFavoritos();
                nombresListasFav = datosClienteSesion.getNombresListasRFavoritas();
            }

            fotoPerfilUsuario = (fotoPerfilUsuario != null) ? fotoPerfilUsuario.substring(2) : "Resource/ImagenesPerfil/Default-Photo-Profile.jpg";
        }
    } else { /* Obtengo datos que el Visitante debe de ver del Cliente */
        /* LISTA REPRODUCCION PARTICULAR PUBLICAS */
        nombresListasRPublicas = datosClienteSesion.getNombresListasRCreadasPublicas();
    }
    
    
    
    
    if (!sesion.getAttribute("rol").equals("Visitante")) {

        
    } else { /* Obtengo datos que el Visitante debe de ver del Cliente */
        /* LISTA REPRODUCCION PARTICULAR PUBLICAS */
        nombresListasRPublicas = datosCliente.getNombresListasRCreadasPublicas();
    }
%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="scripts/consultaPerfilUsuario.js"></script>
<link rel="stylesheet" href="styles/consultaPerfilCliente.css"/>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">


<body>
    <div class="container">
        <div class="top-info">
            <img src="<%= fotoPerfilUsuario %>" alt="Foto de Perfil" class="perfil-imagen"/>
            <div class="usuario-info">
                <p class="rol">CLIENTE</p><!-- TIPO DE USUARIO -->
                <p class="nombre-usuario"><%= nombreCompleto %></p><!-- NOMBRE DE USUARIO -->
                <!-- Si el perfil lo consulta otro Cliente, poner boton SEGUIR -->
            </div>
        </div>
    
        <div class="tabs">
            <ul class="tab-links">
                <li class="active"><a href="#tab1">Información</a></li>
                <li id="favoritos"><a href="#tab2">Favoritos</a></li>
            </ul>

            <div class="tab-content">
                <div id="tab1" class="tab active">
                    <h3>Nickname</h3>
                    <p><%= nicknameUsuario %></p>
                    
                    <h3>Email</h3>
                    <p><%= emailUsuario %></p>
                    <h3>Fecha de Nacimiento</h3>
                    <p><%= fechaNacUsuario %></p>

                    <div id="followers" class="followers-list">
                        <h3 class="followers-info"><%= nicknamesSeguidores.size() + " " %><% if (nicknamesSeguidores.size() == 1) { %>
                                                                            seguidor</h3>
                                                                   <% } else { %>
                                                                            seguidores</h3>
                                                                   <% } %>
                        <table>
                            <thead>
                                <tr>
                                    <th>Nickname</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% for (String nickname : nicknamesSeguidores) {%>
                                <tr>
                                    <td><%= nickname %></td>
                                </tr>
                                <% } %>
                            </tbody>
                        </table>
                    </div>
                    <% if (datosCliente.getSuscripcion() != null && datosCliente.getSuscripcion().getEstadoSuscripcion().equals("Vigente")) { %>        
                        <div id="followed" class="followed-list">
                            <h3 class="followed-info"><%= nicknamesSeguidos.size() + " " %><% if (nicknamesSeguidores.size() == 1) { %>
                                                                            seguido</h3>
                                                                   <% } else { %>
                                                                            seguidos</h3>
                                                                   <% } %>
                            <table>
                                <thead>
                                    <tr>
                                        <th>Nickname</th>
                                        <th>Rol</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <% for (int i = 0; i < nicknamesSeguidos.size(); i++) {%>
                                    <tr>
                                        <td><%= nicknamesS.get(i) %></td>
                                        <td><%= rolS.get(i) %></td>
                                    </tr>
                                    <% } %>
                                </tbody>
                            </table>
                        </div>
                    <% } %>
                </div>

                <div id="tab2" class="tab">
                    <% if (datosCliente.getSuscripcion() != null && datosCliente.getSuscripcion().getEstadoSuscripcion().equals("Vigente")) { %>
                        <% if (nombresListasRCreadas.size() > 0) { %>
                        <div id="listasRCreadas" class="lista-listasRCreados">
                            <div class="divisor d-none d-sm-block"></div>
                            <h3 class="registros">Registro de Listas Creadas</h3>
                            <h3 class="listasR-info">Listas de Reproducción</h3>
                            <table>
                                <thead>
                                    <tr>
                                        <th>Nombre</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <% for (String lista : nombresListasRCreadas) { %>
                                          <tr>
                                              <td><%= lista %></td>
                                          </tr>
                                    <% } %>
                                </tbody>
                            </table>
                        </div>
                        <% } %>
                        
                        <div class="tabla-preferencias">
                            <% if (nombresTemasFav.size() > 0 || nombresAlbumesFav.size() > 0 || nombresListasFav.size() > 0) { %>
                            <div class="divisor d-none d-sm-block"></div>    
                            <h3 class="preferencias">Preferencias</h3>
                                <table>
                                    <thead>
                                        <tr>
                                            <th>Temas</th>
                                            <th>Álbumes</th> 
                                            <th>Listas de Reproducción</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                         
                                        <% 
                                        int max = Math.max(Math.max(nombresTemasFav.size(), nombresAlbumesFav.size()), 
                                        nombresListasFav.size()); 

                                        int sizeTemas = nombresTemasFav.size();
                                        int sizeAlbumes = nombresAlbumesFav.size();
                                        int sizeListas = nombresListasFav.size();
                                        %>
                                        
                                        <% for (int i = 0; i < max; i++) {%>
                                        <tr>
                                            <% if (sizeTemas > 0) { %>
                                                <td><%= nombresTemasFav.get(i)%></td>
                                                <% sizeTemas--; %>
                                            <% } else { %>
                                                <td></td>
                                            <% } %>
                                            
                                            <% if (sizeAlbumes > 0) { %>
                                            <td><a href="SVConsultaAlbum?id=<%= nombresAlbumesFav.get(i) %>"><%= nombresAlbumesFav.get(i) %></a></td>
                                                <% sizeAlbumes--; %>
                                            <% } else { %>
                                                <td></td>
                                            <% } %>
                                            
                                            <% if (sizeListas > 0) {%>
                                                <td><a href="SVConsultaListaReproduccion?id=<%= nombresListasFav.get(i) %>"><%= nombresListasFav.get(i) %></a></td>
                                                <% sizeListas--; %>
                                            <% } else { %>
                                                <td></td>
                                            <% } %>
                                        </tr>
                                        <% } %>
                                    </tbody>
                                </table>
                            <% } %>
                        </div>
                    <%  } else {  %>
                            <h2 class="sin-suscripcion">SIN SUSCRIPCIÓN VIGENTE</h2>
                    <%  }  %>
                </div>
            </div>
        </div>
    </div>

</body>


