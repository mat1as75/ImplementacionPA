<%@page import="espotify.DataTypes.DTDatosCliente"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
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
    
    HttpSession sesion = request.getSession(false);
    String nicknameSesion = (String) sesion.getAttribute("nickname");
    System.out.println("ACA2: " + (String) sesion.getAttribute("nickname"));
    String rolSesion = (String) sesion.getAttribute("rol");
    System.out.println("ACA3: " + rolSesion);
    String estadoSuscripcionSesion = null;
    DTDatosUsuario usuarioSesion = null;
    
    DTDatosUsuario DTusuarioConsultado = (DTDatosUsuario) sesion.getAttribute("DTusuarioConsultado");
    DTDatosUsuario usuarioConsultado = null;
    DTDatosArtista artistaConsultado = null;
    if (DTusuarioConsultado != null)
        usuarioConsultado = control.getDatosUsuario(DTusuarioConsultado.getNicknameUsuario());
    else
        usuarioConsultado = control.getDatosUsuario(nicknameSesion);
    
    String nicknameConsultado = null, emailConsultado = null, nombreCompletoConsultado = null, 
            fechaNacConsultado = null, fotoPerfilConsultado = null, biografiaConsultado = null,
            dirSitioWebConsultado = null;
    
    ArrayList<String> nicknamesSeguidoresConsultados = null;
    Map<Long, String> albumesPublicadosConsultados = new HashMap<>();
    
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
    albumesPublicadosConsultados = artistaConsultado.getNombresAlbumesPublicados();

    // Si UsuarioSesion != ArtistaConsultado => Obtengo DatosUsuarioSesion, caso contrario null
    if (rolSesion != null) {
        usuarioSesion = (!nicknameSesion.equals(nicknameConsultado)) ? control.getDatosUsuario(nicknameSesion) : null;
    
    // Sesion es Cliente
    if (rolSesion.equals("Cliente")) {
        // Tiene Suscripcion
        if (((DTDatosCliente) usuarioSesion).getSuscripcion() != null) {
            estadoSuscripcionSesion = ((DTDatosCliente) usuarioSesion).getSuscripcion().getEstadoSuscripcion();
        }
    }
%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="scripts/consultaPerfilUsuario.js defer"></script>
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
                <%  // Sesion.rol no nula (Visitantes no pueden realizar seguimientos)
                    // Sesion.rol == Cliente (Clientes unicamente pueden Seguir)
                    // NicknameSesion != NicknameConsultado (No se permite auto-Seguimientos)
                    if (rolSesion != null && rolSesion.equals("Cliente") && !nicknameConsultado.equals(nicknameSesion)) {
                        if (usuarioSesion != null && estadoSuscripcionSesion.equals("Vigente")) {
                            if (!nicknamesSeguidoresConsultados.contains(nicknameSesion)) { %>
                                <form action="SVSeguirUsuario" method="POST">
                                    <input type="hidden" name="nicknameSeguidor" value="<%= nicknameSesion %>"/>
                                    <input type="hidden" name="nicknameSeguido" value="<%= nicknameConsultado %>"/>
                                    <button type="submit" class="boton-seguimiento">Seguir</button>
                                </form>
                            <% } else { %>
                                <form action="SVDejarSeguirUsuario" method="POST">
                                    <input type="hidden" name="nicknameSeguidor" value="<%= nicknameSesion %>"/>
                                    <input type="hidden" name="nicknameSeguido" value="<%= nicknameConsultado %>"/>
                                    <button type="submit" class="boton-seguimiento">Siguiendo</button>
                                </form>
                            <% } %>
                        <% } %>
                <%  } %>
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
                    <% if (albumesPublicadosConsultados.size() > 0) { %>
                    <%
                        // Conviertir las entradas del Map a List
                        List<Map.Entry<Long, String>> entryAlbumesPublicados = new ArrayList<>(albumesPublicadosConsultados.entrySet());
                    %>
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
                                <% for (int i = 0; i < albumesPublicadosConsultados.size(); i++) { %>
                                <tr>
                                    <%
                                        Map.Entry<Long, String> entryAlbumPublicado = entryAlbumesPublicados.get(i);
                                        Long idAlbum = entryAlbumPublicado.getKey();
                                        String nombreAlbum = entryAlbumPublicado.getValue();
                                    %>
                                    <td><a href="ConsultaAlbum?albumId=<%= idAlbum %>"><%= nombreAlbum %></a></td>
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


