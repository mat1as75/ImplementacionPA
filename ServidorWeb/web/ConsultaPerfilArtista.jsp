<%@page import="espotify.logica.IControlador"%>
<%@page import="espotify.logica.Fabrica"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Date"%>
<%@page import="espotify.DataTypes.DTDatosArtista"%>
<%@page import="espotify.DataTypes.DTDatosUsuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    Fabrica fb = Fabrica.getInstance();
    IControlador control = fb.getControlador();
    
    HttpSession sesion = request.getSession();
    String nicknameSesion = (String) sesion.getAttribute("nickname");
    String rolSesion = (String) sesion.getAttribute("rol");
    DTDatosUsuario usuarioSesion = null;
    
    DTDatosUsuario usuarioConsultado = (DTDatosUsuario) sesion.getAttribute("DTusuarioConsultado");
    DTDatosArtista artistaConsultado = null;
    
    String nicknameConsultado = null, emailConsultado = null, nombreCompletoConsultado = null, 
            fechaNacConsultado = null, fotoPerfilConsultado = null, biografiaConsultado = null,
            dirSitioWebConsultado = null;
    
    ArrayList<String> nicknamesSeguidoresConsultados = null, nombresAlbumesPublicadosConsultados = null;
    
    /*-----DATOS USUARIO CONSULTADO------*/
    artistaConsultado = (DTDatosArtista) usuarioConsultado;
    
    nicknameConsultado = usuarioConsultado.getNicknameUsuario();
    nombreCompletoConsultado = usuarioConsultado.getNombreUsuario() + " " + usuarioConsultado.getApellidoUsuario();
    fotoPerfilConsultado = usuarioConsultado.getFotoPerfil();
    fotoPerfilConsultado = (fotoPerfilConsultado != null) ? fotoPerfilConsultado.substring(2) : "Resource/ImagenesPerfil/Default-Photo-Profile.jpg";
    emailConsultado = usuarioConsultado.getEmail();
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    fechaNacConsultado = dateFormat.format(usuarioConsultado.getFecNac());
    biografiaConsultado = artistaConsultado.getBiografia();
    dirSitioWebConsultado = artistaConsultado.getDirSitioWeb();
  
    nicknamesSeguidoresConsultados = usuarioConsultado.getNicknamesSeguidores();
    nombresAlbumesPublicadosConsultados = artistaConsultado.getNombresAlbumesPublicados();

%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="scripts/consultaPerfilUsuario.js"></script>
<link rel="stylesheet" href="styles/consultaPerfilArtista.css"/>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">


<body>
    <div class="container">
        <div class="top-info">
            <img src="<%= fotoPerfilConsultado %>" alt="Foto de Perfil" class="perfil-imagen"/>
            <div class="usuario-info">
                <p class="rol">ARTISTA</p><!-- TIPO DE USUARIO -->
                <p class="nombre-usuario"><%= nombreCompletoConsultado %></p><!-- NOMBRE DE USUARIO -->
                
                <% if (dirSitioWebConsultado != null) { %>
                    <a href="<%= "https:" + dirSitioWebConsultado %>" target="_blank" class="btn-webPage"><%= dirSitioWebConsultado %></a>
                <% } %>
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
                    <p><%= nicknameConsultado %></p>
                    <h3>Email</h3>
                    <p><%= emailConsultado %></p>
                    <h3>Fecha de Nacimiento</h3>
                    <p><%= fechaNacConsultado %></p>
                    <% if (biografiaConsultado != null) { %>
                        <h3>Biografía</h3>
                        <p><%= biografiaConsultado %></p>
                    <% } %>
                    <div id="followers" class="followers-list">
                        <h3 class="followers-info"><%= nicknamesSeguidoresConsultados.size() + " " %><% if (nicknamesSeguidoresConsultados.size() == 1) { %>
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
                                <% for (String nickname : nicknamesSeguidoresConsultados) { %>
                                <tr>
                                    <td><%= nickname %></td>
                                </tr>
                                <% } %>
                            </tbody>
                        </table>
                    </div>
                </div>

                <div id="tab2" class="tab">
                    <% if (nombresAlbumesPublicadosConsultados.size() > 0) { %>
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
                                <% for (String album : nombresAlbumesPublicadosConsultados) {%>
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


