<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Date"%>
<%@page import="espotify.DataTypes.DTDatosArtista"%>
<%@page import="espotify.DataTypes.DTDatosUsuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    HttpSession sesion = request.getSession();
    DTDatosUsuario datosUsuario = (DTDatosUsuario) sesion.getAttribute("usuario");
    DTDatosArtista datosArtista = (DTDatosArtista) sesion.getAttribute("usuario");
    
    /* DATOS DE ARTISTA */
    String nicknameUsuario = datosUsuario.getNicknameUsuario();
    String nombreCompleto = datosUsuario.getNombreUsuario() + " " + datosUsuario.getApellidoUsuario();
    String fotoPerfilUsuario = datosUsuario.getFotoPerfil();
    String emailUsuario = datosUsuario.getEmail();
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    String fechaNacUsuario = dateFormat.format(datosUsuario.getFecNac());
    ArrayList<String> nicknamesSeguidores = datosUsuario.getNicknamesSeguidores();
    
    /* Extras de Artista */
    String webPage = datosArtista.getDirSitioWeb();
    String biografia = datosArtista.getBiografia();
    ArrayList<String> nombresAlbumesP = datosArtista.getNombresAlbumesPublicados();
    
    fotoPerfilUsuario = (fotoPerfilUsuario != null) ? fotoPerfilUsuario.substring(2) : "Resource/ImagenesPerfil/Default-Photo-Profile.jpg";
%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="scripts/consultaPerfilUsuario.js"></script>
<link rel="stylesheet" href="styles/consultaPerfilArtista.css"/>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">


<body>
    <div class="container">
        <div class="top-info">
            <img src="<%= fotoPerfilUsuario %>" alt="Foto de Perfil" class="perfil-imagen"/>
            <div class="usuario-info">
                <p class="rol">ARTISTA</p><!-- TIPO DE USUARIO -->
                <p class="nombre-usuario"><%= nombreCompleto %></p><!-- NOMBRE DE USUARIO -->
                <a href="<%= "https:" + webPage %>" target="_blank" class="btn-webPage"><%= webPage %></a>
                <!-- Si el perfil lo consulta otro Cliente, poner boton SEGUIR -->
            </div>
        </div>

        <div class="tabs">
            <ul class="tab-links">
                <li class="active"><a href="#tab1">Información</a></li>
                <li id="albumes"><a href="#tab2">Álbumes</a></li>
            </ul>

            <div class="tab-content">
                <div id="tab1" class="tab active">
                    <h3>Nickname</h3>
                    <p><%= nicknameUsuario %></p>
                    <h3>Email</h3>
                    <p><%= emailUsuario %></p>
                    <h3>Fecha de Nacimiento</h3>
                    <p><%= fechaNacUsuario %></p>
                    <h3>Biografía</h3>
                    <p><%= biografia %></p>

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
                                <% for (String nickname : nicknamesSeguidores) { %>
                                <tr>
                                    <td><%= nickname %></td>
                                </tr>
                                <% } %>
                            </tbody>
                        </table>
                    </div>
                </div>

                <div id="tab2" class="tab">
                    <% if (nombresAlbumesP.size() > 0) { %>
                    <div id="albumesP" class="lista-albumesP">
                        <div class="divisor d-none d-sm-block"></div>
                        <h3 class="registros">Registro de Álbumes Publicados</h3>
                        <h3 class="albumes-info">Álbumes</h3>
                        <table>
                            <thead>
                                <tr>
                                    <th>Nombre</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% for (String album : nombresAlbumesP) {%>
                                <tr>
                                    <td><%= album %></td>
                                </tr>
                                <% } %>
                            </tbody>
                        </table>
                    </div>
                    <% } %>
                </div>
            </div>
        </div>
    </div>
</body>


