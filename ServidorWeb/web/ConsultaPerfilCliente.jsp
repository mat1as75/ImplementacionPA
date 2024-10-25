<%@page import="espotify.logica.IControlador"%>
<%@page import="espotify.logica.Fabrica"%>
<%@page import="espotify.DataTypes.DTDatosArtista"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.ArrayList"%>
<%@page import="espotify.DataTypes.DTDatosCliente"%>
<%@page import="espotify.DataTypes.DTDatosUsuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    Fabrica fb = Fabrica.getInstance();
    IControlador control = fb.getControlador();
    
    HttpSession sesion = request.getSession(false);
    String nicknameSesion = (String) sesion.getAttribute("nickname");
    String rolSesion = (String) sesion.getAttribute("rol");
    DTDatosUsuario usuarioSesion = null;
    String estadoSuscripcionSesion = null;
    
    DTDatosUsuario DTusuarioConsultado = (DTDatosUsuario) sesion.getAttribute("DTusuarioConsultado");
    DTDatosUsuario usuarioConsultado = control.getDatosUsuario(DTusuarioConsultado.getNicknameUsuario());
    DTDatosCliente clienteConsultado = null;
    
    String nicknameConsultado = null, emailConsultado = null, nombreCompletoConsultado = null, fechaNacConsultado = null, fotoPerfilConsultado = null;
    ArrayList<String> nicknamesSeguidoresConsultados = null, nicknamesSeguidosConsultados = null, nicknamesS = null, rolesS = null, 
            nombresTemasFavConsultados = null, nombresAlbumesFavConsultados = null, nombresListasRFavConsultadas = null, 
            nombresListasRConsultadas = null, nombresListasPublicasConsultadas = null;
    
    /*-----DATOS USUARIO CONSULTADO------*/
    clienteConsultado = (DTDatosCliente) usuarioConsultado;
    
    nicknameConsultado = usuarioConsultado.getNicknameUsuario();
    emailConsultado = usuarioConsultado.getEmail();
    nombreCompletoConsultado = usuarioConsultado.getNombreUsuario() + " " + usuarioConsultado.getApellidoUsuario();
    fotoPerfilConsultado = usuarioConsultado.getFotoPerfil();
    fotoPerfilConsultado = (fotoPerfilConsultado != null) ? fotoPerfilConsultado.substring(2) : "Resource/ImagenesPerfil/Default-Photo-Profile.jpg";
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    fechaNacConsultado = dateFormat.format(usuarioConsultado.getFecNac());
    nicknamesSeguidoresConsultados = usuarioConsultado.getNicknamesSeguidores();
            
    if (rolSesion != null) { /* Si existe Sesion (Cliente o Artista) */
    
        usuarioSesion = control.getDatosUsuario(nicknameSesion);
        if (rolSesion.equals("Cliente") && ((DTDatosCliente) usuarioSesion).getSuscripcion() != null) {
            estadoSuscripcionSesion = ((DTDatosCliente) usuarioSesion).getSuscripcion().getEstadoSuscripcion();
            System.out.println("ESTADO SUSCRIPCION: " + estadoSuscripcionSesion);
        }
        
        /* UsuarioSesion == UsuarioConsultado */
        if (nicknameSesion.equals(usuarioConsultado.getNicknameUsuario())) { 
            /* UsuarioSesion.Suscripcion != null */
            if (((DTDatosCliente) usuarioSesion).getSuscripcion() != null) {
                /* UsuarioSesion EstadoSuscripcion.Vigente */
                if (estadoSuscripcionSesion.equals("Vigente")) {

                    // Usuarios a los que sigue (identificando si son clientes o artistas)
                    nicknamesSeguidosConsultados = clienteConsultado.getNicknamesSeguidos();
                    nicknamesS = new ArrayList<>();
                    rolesS = new ArrayList<>();
                    for (String n : nicknamesSeguidosConsultados) {
                        int i = n.indexOf(",");
                        nicknamesS.add(n.substring(0, i));
                    }
                    for (String n : nicknamesSeguidosConsultados) {
                        int i = n.indexOf(" ");
                        rolesS.add(n.substring(i + 1));
                    }

                    // Listas de Reproduccion que creo (Publicas Y Privadas)
                    nombresListasRConsultadas = clienteConsultado.getNombresListasRCreadas();

                    // Preferencias que tiene guardadas
                    nombresTemasFavConsultados = clienteConsultado.getNombresTemasFavoritos();
                    nombresAlbumesFavConsultados = clienteConsultado.getNombresAlbumesFavoritos();
                    nombresListasRFavConsultadas = clienteConsultado.getNombresListasRFavoritas();
                }  
            }
        } else { /* UsuarioSesion != UsuarioConsultado */
            
            // Consulta un Artista o Cliente con Suscripcion Vigente
            if (rolSesion.equals("Artista") || ((rolSesion.equals("Cliente") && estadoSuscripcionSesion != null 
                                                && estadoSuscripcionSesion.equals("Vigente")))) {
                // Usuarios a los que sigue (identificando si son clientes o artistas)
                    System.out.println("SEGUIDOS");
                    nicknamesSeguidosConsultados = clienteConsultado.getNicknamesSeguidos();
                    nicknamesS = new ArrayList<>();
                    rolesS = new ArrayList<>();
                    for (String n : nicknamesSeguidosConsultados) {
                        int i = n.indexOf(",");
                        nicknamesS.add(n.substring(0, i));
                    }
                    for (String n : nicknamesSeguidosConsultados) {
                        int i = n.indexOf(" ");
                        rolesS.add(n.substring(i + 1));
                    }

                    /* UsuarioSesion es Seguidor de UsuarioConsultado */
                    if (nicknamesSeguidoresConsultados.contains(nicknameSesion)) {

                        // Listas de Reproduccion que creo (Publicas)
                        nombresListasRConsultadas = clienteConsultado.getNombresListasRCreadasPublicas();
                    }

                    // Preferencias que tiene guardadas
                    nombresTemasFavConsultados = clienteConsultado.getNombresTemasFavoritos();
                    nombresAlbumesFavConsultados = clienteConsultado.getNombresAlbumesFavoritos();
                    nombresListasRFavConsultadas = clienteConsultado.getNombresListasRFavoritas();
                    
            } 
        
//            if (rolSesion.equals("Artista") || ((rolSesion.equals("Cliente") && ((DTDatosCliente) usuarioSesion).getSuscripcion() != null))) {
//                if (rolSesion.equals("Cliente") && estadoSuscripcionSesion.equals("Vigente")) {
//                    // Usuarios a los que sigue (identificando si son clientes o artistas)
//                    System.out.println("SEGUIDOS");
//                    nicknamesSeguidosConsultados = clienteConsultado.getNicknamesSeguidos();
//                    nicknamesS = new ArrayList<>();
//                    rolesS = new ArrayList<>();
//                    for (String n : nicknamesSeguidosConsultados) {
//                        int i = n.indexOf(",");
//                        nicknamesS.add(n.substring(0, i));
//                    }
//                    for (String n : nicknamesSeguidosConsultados) {
//                        int i = n.indexOf(" ");
//                        rolesS.add(n.substring(i + 1));
//                    }
//
//                    /* UsuarioSesion es Seguidor de UsuarioConsultado */
//                    if (nicknamesSeguidoresConsultados.contains(nicknameSesion)) {
//
//                        // Listas de Reproduccion que creo (Publicas)
//                        nombresListasRConsultadas = clienteConsultado.getNombresListasRCreadasPublicas();
//                    }
//
//                    // Preferencias que tiene guardadas
//                    nombresTemasFavConsultados = clienteConsultado.getNombresTemasFavoritos();
//                    nombresAlbumesFavConsultados = clienteConsultado.getNombresAlbumesFavoritos();
//                    nombresListasRFavConsultadas = clienteConsultado.getNombresListasRFavoritas();
//                } else {
//                    // Listas de Reproduccion que creo (Publicas)
//                    nombresListasRConsultadas = clienteConsultado.getNombresListasRCreadasPublicas();
//                    
//                    // Preferencias que tiene guardadas
//                    nombresTemasFavConsultados = clienteConsultado.getNombresTemasFavoritos();
//                    nombresAlbumesFavConsultados = clienteConsultado.getNombresAlbumesFavoritos();
//                    nombresListasRFavConsultadas = clienteConsultado.getNombresListasRFavoritas();
//                    System.out.println("PREFERENCIAS");
//                }
//            }
        }
    } else { /* No existe Sesion (Visitante) */
        
        nombresListasRConsultadas = clienteConsultado.getNombresListasRCreadasPublicas();
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
            <img src="<%= fotoPerfilConsultado %>" alt="Foto de Perfil" class="perfil-imagen"/>
            <div class="usuario-info">
                <p class="rol">CLIENTE</p><!-- TIPO DE USUARIO -->
                <p class="nombre-usuario"><%= nombreCompletoConsultado %></p><!-- NOMBRE DE USUARIO -->
                <!-- Si el perfil lo consulta otro Cliente, poner boton SEGUIR -->
                
                <%  // Sesion.rol no nula (Visitantes no pueden realizar seguimientos)
                    // Sesion.rol == Cliente (Clientes unicamente pueden Seguir)
                    // NicknameSesion != NicknameConsultado (No se permite auto-Seguimientos)
                    if (rolSesion != null && rolSesion.equals("Cliente") && !nicknameConsultado.equals(nicknameSesion)) {
                        if (!nicknamesSeguidoresConsultados.contains(nicknameSesion)) { %>
                            <form action="SVSeguirUsuario" method="POST">
                                <button type="submit" class="boton-seguimiento">Seguir</button>
                            </form>
                        <% } else { %>
                            <form action="SVDejarSeguirAUsuario" method="POST">
                                <button type="submit" class="boton-seguimiento">Siguiendo</button>
                            </form>
                        <% } %>
                <%  } %>
                
                
                
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
                    <p><%= nicknameConsultado %></p>
                    
                    <% if (rolSesion != null) { %>
                        <h3>Email</h3>
                        <p><%= emailConsultado %></p>

                        <h3>Fecha de Nacimiento</h3>
                        <p><%= fechaNacConsultado %></p>

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
                                    <% for (String nickname : nicknamesSeguidoresConsultados) {%>
                                    <tr>
                                        <td><%= nickname %></td>
                                    </tr>
                                    <% } %>
                                </tbody>
                            </table>
                        </div>
                    <% } %>
                    <% if (rolSesion.equals("Artista") || (estadoSuscripcionSesion != null && estadoSuscripcionSesion.equals("Vigente"))) { %>        
                        <% if (nicknamesSeguidosConsultados != null) { %>
                            <div id="followed" class="followed-list">
                                <h3 class="followed-info"><%= nicknamesSeguidosConsultados.size() + " " %><% if (nicknamesSeguidosConsultados.size() == 1) { %>
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
                                        <% for (int i = 0; i < nicknamesSeguidosConsultados.size(); i++) {%>
                                        <tr>
                                            <td><%= nicknamesS.get(i) %></td>
                                            <td><%= rolesS.get(i) %></td>
                                        </tr>
                                        <% } %>
                                    </tbody>
                                </table>
                            </div>
                        <% } %>
                    <% } %>
                </div>

                <div id="tab2" class="tab">
                    <% if (rolSesion.equals("Artista") || (rolSesion.equals("Cliente") && estadoSuscripcionSesion != null 
                                                                && estadoSuscripcionSesion.equals("Vigente"))) { %>
                        <% // UsuarioSesion es Seguidor de UsuarioConsultado
                           // Hay ListasRConsultadas
                           if (nicknamesSeguidoresConsultados.contains(nicknameSesion) && nombresListasRConsultadas.size() > 0) { %>
                        <div id="listasRCreadas" class="lista-listasRCreados">
                            <div class="divisor d-none d-sm-block"></div>
                            <% if (rolSesion != null) { %>
                                <h3 class="registros">Registro de Listas Creadas</h3>
                            <% } else {%>
                                <h3 class="registros">Registro de Listas Publicas Creadas</h3>
                            <% } %>
                            <h3 class="listasR-info">Listas de Reproducción</h3>
                            <table>
                                <thead>
                                    <tr>
                                        <th>Nombre</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <% for (String lista : nombresListasRConsultadas) { %>
                                          <tr>
                                              <td><%= lista %></td>
                                          </tr>
                                    <% } %>
                                </tbody>
                            </table>
                        </div>
                        <% } %>

                        <div class="tabla-preferencias">
                            <% if (rolSesion != null) { %>
                                <% if (nombresTemasFavConsultados.size() > 0 || nombresAlbumesFavConsultados.size() > 0 || nombresListasRFavConsultadas.size() > 0) { %>
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
                                            int sizeTemas = nombresTemasFavConsultados.size();
                                            int sizeAlbumes = nombresAlbumesFavConsultados.size();
                                            int sizeListas = nombresListasRFavConsultadas.size();

                                            int max = Math.max(Math.max(sizeTemas, sizeAlbumes), sizeListas); 
                                            %>

                                            <% for (int i = 0; i < max; i++) {%>
                                            <tr>
                                                <% if (sizeTemas > 0) { %>
                                                    <td><%= nombresTemasFavConsultados.get(i)%></td>
                                                    <% sizeTemas--; %>
                                                <% } else { %>
                                                    <td></td>
                                                <% } %>

                                                <% if (sizeAlbumes > 0) { %>
                                                <td><a href="SVConsultaAlbum?id=<%= nombresAlbumesFavConsultados.get(i) %>"><%= nombresAlbumesFavConsultados.get(i) %></a></td>
                                                    <% sizeAlbumes--; %>
                                                <% } else { %>
                                                    <td></td>
                                                <% } %>

                                                <% if (sizeListas > 0) {%>
                                                    <td><a href="DatosListaReproduccion.jsp?nombreLista=<%= nombresListasRFavConsultadas.get(i) %>"><%= nombresListasRFavConsultadas.get(i) %></a></td>
                                                    <% sizeListas--; %>
                                                <% } else { %>
                                                    <td></td>
                                                <% } %>
                                            </tr>
                                            <% } %>
                                        </tbody>
                                    </table>
                                <% } %>
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


