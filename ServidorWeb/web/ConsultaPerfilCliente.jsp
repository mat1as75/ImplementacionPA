<%@page import="webservices.SuscripcionesServiceService"%>
<%@page import="webservices.SuscripcionesService"%>
<%@page import="webservices.ArrayListContainer"%>
<%@page import="webservices.UsuarioService"%>
<%@page import="webservices.UsuarioServiceService"%>
<%@page import="webservices.DataTypes.DtDatosCliente"%>
<%@page import="webservices.DataTypes.DtDatosUsuario"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    
    HttpSession sesion = request.getSession(false);
    String nicknameSesion = (String) sesion.getAttribute("nickname");
    String rolSesion = (String) sesion.getAttribute("rol");
    String estadoSuscripcionSesion = null;
    DtDatosUsuario usuarioSesion = null;
    
    DtDatosUsuario DTusuarioConsultado = (DtDatosUsuario) sesion.getAttribute("DTusuarioConsultado");
    DtDatosUsuario usuarioConsultado = null;
    DtDatosCliente clienteConsultado = null;
    
    UsuarioServiceService serviceU = new UsuarioServiceService();
    UsuarioService serviceUsuario = serviceU.getUsuarioServicePort();
    
    /* Si el atributo DTusuarioConsultado de la sesion de distinto de null, 
        es porq se consulto a otro Usuario. En caso contrario, es porq se 
            consulto su propio perfil */
    
    if (DTusuarioConsultado != null) // Se consulto el Perfil de otro Usuario
        usuarioConsultado = serviceUsuario.getDatosCliente((DTusuarioConsultado.getNicknameUsuario()));
    else // Se consulto el Perfil del Usuario Sesion
        usuarioConsultado = serviceUsuario.getDatosCliente(nicknameSesion);

    /* MUESTRO TODOS LOS DATOS DEL USUARIO CONSULTADO */
    System.out.println("tipo: " + serviceUsuario.getTipoUsuario(nicknameSesion));
    System.out.println("nickname: " + usuarioConsultado.getNicknameUsuario());
    System.out.println("nombre completo: " + usuarioConsultado.getNombreUsuario() + " " + usuarioConsultado.getApellidoUsuario());
    System.out.println("email: " + usuarioConsultado.getEmail());
    System.out.println("fecnac: " + usuarioConsultado.getFecNac());
    System.out.println("misSeguidores: " + usuarioConsultado.getNicknamesSeguidores());
    System.out.println("misSeguidos: " + ((DtDatosCliente) usuarioConsultado).getNicknamesSeguidos());
    System.out.println("misListasCreadas: " + ((DtDatosCliente) usuarioConsultado).getNombresListasRCreadas());
    System.out.println("misListasCreadasPublicas " + ((DtDatosCliente) usuarioConsultado).getNombresListasRCreadasPublicas());
    System.out.println("misListasFav: " + ((DtDatosCliente) usuarioConsultado).getNombresListasRFavoritas());
    System.out.println("misAlbumesFav: " + ((DtDatosCliente) usuarioConsultado).getNombresAlbumesFavoritos());
    System.out.println("misTemasFav: " + ((DtDatosCliente) usuarioConsultado).getNombresTemasFavoritos());
    System.out.println("miSuscripcion: " + ((DtDatosCliente) usuarioConsultado).getSuscripcion());
    
    String nicknameConsultado = null, emailConsultado = null, nombreCompletoConsultado = null, fechaNacConsultado = null, fotoPerfilConsultado = null;
    List<String> nicknamesSeguidoresConsultados = null, nicknamesSeguidosConsultados = null, nicknamesS = null, rolesS = null, 
            nombresListasRFavConsultadas = null, nombresListasRConsultadas = null, nombresListasPublicasConsultadas = null;
    
    Map<Long, String> temasFavConsultados = new HashMap<>(), albumesFavConsultados = new HashMap<>();
    
    /*-----DATOS USUARIO CONSULTADO------*/
    clienteConsultado = (DtDatosCliente) usuarioConsultado;
    
    nicknameConsultado = usuarioConsultado.getNicknameUsuario();
    emailConsultado = usuarioConsultado.getEmail();
    nombreCompletoConsultado = usuarioConsultado.getNombreUsuario() + " " + usuarioConsultado.getApellidoUsuario();
    fotoPerfilConsultado = usuarioConsultado.getFotoPerfil();
    fotoPerfilConsultado = (fotoPerfilConsultado != null) ? fotoPerfilConsultado.substring(2) : "Resource/ImagenesPerfil/Default-Photo-Profile.jpg";
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    fechaNacConsultado = dateFormat.format(usuarioConsultado.getFecNac().toGregorianCalendar().getTime());
    nicknamesSeguidoresConsultados = usuarioConsultado.getNicknamesSeguidores();
    
    if (rolSesion != null) { /* Si existe Sesion (Cliente o Artista) */
        
        usuarioSesion = serviceUsuario.getDatosUsuario(nicknameSesion).getDtDatosUsuario();
        
        if (rolSesion.equals("Cliente")) {
            DtDatosCliente clienteSesion = (DtDatosCliente) usuarioSesion;
            if (clienteSesion.getSuscripcion() != null) {
                //((DTDatosCliente) usuarioSesion).getSuscripcion().setEstadoSuscripcion("Vigente");
                estadoSuscripcionSesion = ((DtDatosCliente) usuarioSesion).getSuscripcion().getEstadoSuscripcion();
                System.out.println("### " + estadoSuscripcionSesion);
            }
        }
        
        /* UsuarioSesion == UsuarioConsultado */
        if (nicknameSesion.equals(usuarioConsultado.getNicknameUsuario())) { 
            /* UsuarioSesion.Suscripcion != null */
            if (((DtDatosCliente) usuarioSesion).getSuscripcion() != null) {
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
                    temasFavConsultados = clienteConsultado.getNombresTemasFavoritos();
                    albumesFavConsultados = clienteConsultado.getNombresAlbumesFavoritos();
                    nombresListasRFavConsultadas = clienteConsultado.getNombresListasRFavoritas();
                }  
            }
        } else { /* UsuarioSesion != UsuarioConsultado */
            
            // Consulta un Artista o Cliente con Suscripcion Vigente
            if (rolSesion.equals("Artista") || ((rolSesion.equals("Cliente") && estadoSuscripcionSesion != null 
                                                && estadoSuscripcionSesion.equals("Vigente")))) {
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
                    
                    // Consulta un Artista
                    // UsuarioSesion es Seguidor de UsuarioConsultado
                    if (rolSesion.equals("Artista") || nicknamesSeguidoresConsultados.contains(nicknameSesion)) {

                        // Listas de Reproduccion que creo (Publicas)
                        nombresListasRConsultadas = clienteConsultado.getNombresListasRCreadasPublicas();
                    }

                    // Preferencias que tiene guardadas
                    temasFavConsultados = clienteConsultado.getNombresTemasFavoritos();
                    albumesFavConsultados = clienteConsultado.getNombresAlbumesFavoritos();
                    nombresListasRFavConsultadas = clienteConsultado.getNombresListasRFavoritas();
                    
            }
        }
    } else { /* No existe Sesion (Visitante) */
        
        nombresListasRConsultadas = clienteConsultado.getNombresListasRCreadasPublicas();
    }

%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="scripts/consultaPerfilUsuario.js" defer></script>
<link rel="stylesheet" href="styles/consultaPerfilCliente.css"/>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">


<body>
    <div class="container">
        <div class="top-info">
            <img src="<%= fotoPerfilConsultado %>" alt="Foto de Perfil" class="perfil-imagen"/>
            <div class="usuario-info">
                <p class="rol">CLIENTE</p><!-- TIPO DE USUARIO -->
                <p class="nombre-usuario"><%= nombreCompletoConsultado %></p><!-- NOMBRE DE USUARIO -->
               
                <%  // Sesion.rol no nula (Visitantes no pueden realizar seguimientos)
                    // Sesion.rol == Cliente (Clientes unicamente pueden Seguir)
                    // NicknameSesion != NicknameConsultado (No se permite auto-Seguimientos)
                    if (rolSesion != null && rolSesion.equals("Cliente") && !nicknameConsultado.equals(nicknameSesion)) {
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
                            <% if (nicknamesSeguidoresConsultados != null) { %>
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
                        <% } %>
                        </div>
                    <% } %>
                    <% if ((rolSesion != null && rolSesion.equals("Artista")) || (estadoSuscripcionSesion != null && estadoSuscripcionSesion.equals("Vigente"))) { %>        
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
                <%  // Consulta un Visitante
                    // Consulta un Artista
                    // Consulta un Cliente que tiene Suscripcion con estado == Vigente
                    if ((rolSesion == null || rolSesion.equals("Artista") || 
                        (rolSesion.equals("Cliente") && estadoSuscripcionSesion != null && 
                        estadoSuscripcionSesion.equals("Vigente")))) { %>
                    <%
                        
                            // 
                            if (nombresListasRConsultadas != null && nombresListasRConsultadas.size() > 0) { %>
                            <%System.out.println("DIV ListasRCreadas");%>
                                <div id="listasRCreadas" class="lista-listasRCreados">
                                    <div class="divisor d-none d-sm-block"></div>
                                    
                                <%  // Consulta un Visitante
                                    // Consulta un Artista
                                    // Consulta un Usuario & ese Usuario es Seguidor del Consultado
                                    if (rolSesion == null || rolSesion.equals("Artista") ||
                                            (rolSesion.equals("Cliente") && 
                                            nicknamesSeguidoresConsultados.contains(nicknameSesion))) { %>
                                        <h3 class="registros">Registro de Listas Publicas Creadas</h3>
                                    <% } else { %>
                                        <h3 class="registros">Registro de Listas Creadas</h3>
                                    <% } %>
                                    <h3 class="listasR-info">Listas de Reproducción</h3>
                                    <table>
                                        <thead>
                                            <tr>
                                                <th>Nombre</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <% for (int i = 0; i < nombresListasRConsultadas.size(); i++) { %>
                                            <tr>
                                                <td><a href="DatosListaReproduccion.jsp?nombreLista=<%= nombresListasRConsultadas.get(i) %>"><%= nombresListasRConsultadas.get(i) %></a></td>
                                            </tr>
                                            <% } %>
                                        </tbody>
                                    </table>
                                </div>
                                        
                                <%
                                    boolean autoConsulta = false;
                                    
                                    // Existe sesion
                                    if (rolSesion != null) {
                                        // UsuarioConsultado != null
                                        // UsuarioConsultado.nickname == Sesion.nickname
                                        if (DTusuarioConsultado != null && DTusuarioConsultado.getNicknameUsuario().equals(nicknameSesion)) {
                                            /* Se auto-consulto el perfil desde la barra de busqueda */
                                            autoConsulta = true;
                                        }
                                    }
                                %>
                                        
                                <% 
                                    // Existe una Sesion y se autoConsulto su perfil
                                    // No existe una Sesion
                                    System.out.println("???: " + autoConsulta + " " + rolSesion);
                                    if (autoConsulta) { %>
                                        <div class="publicar-lista">
                                            <form>
                                                <a href="PublicarLista.jsp">Publicar Mis Listas</a>
                                            </form>
                                        </div>
                                <%  }  %>
                        <% } %>

                        <div class="tabla-preferencias">
                            <% if (rolSesion != null) { %>
                                <% if (temasFavConsultados.size() > 0 || albumesFavConsultados.size() > 0 || nombresListasRFavConsultadas.size() > 0) { %>
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
                                                int sizeTemas = temasFavConsultados.size();
                                                int sizeAlbumes = albumesFavConsultados.size();
                                                int sizeListas = nombresListasRFavConsultadas.size();

                                                int max = Math.max(Math.max(sizeTemas, sizeAlbumes), sizeListas);

                                                // Conviertir las entradas del Map a List
                                                List<Map.Entry<Long, String>> entryTemasFav = new ArrayList<>(temasFavConsultados.entrySet());
                                                List<Map.Entry<Long, String>> entryAlbumesFav = new ArrayList<>(albumesFavConsultados.entrySet());
                                            %>

                                            <% for (int i = 0; i < max; i++) {%>
                                            <tr>
                                                <% if (sizeTemas > 0) { %>
                                                    <%
                                                        Map.Entry<Long, String> entryTemaFav = entryTemasFav.get(i);
                                                        Long idTema = entryTemaFav.getKey();
                                                        String nombreTema = entryTemaFav.getValue();
                                                    %>
                                                    <td><%= nombreTema %></td>
                                                    <% sizeTemas--; %>
                                                <% } else { %>
                                                    <td></td>
                                                <% } %>

                                                <% if (sizeAlbumes > 0) { %>
                                                    <%
                                                        Map.Entry<Long, String> entryAlbumFav = entryAlbumesFav.get(i);
                                                        Long idAlbum = entryAlbumFav.getKey();
                                                        String nombreAlbum = entryAlbumFav.getValue();
                                                    %>
                                                    <td><a href="ConsultaAlbum.jsp?albumId=<%= idAlbum %>"><%= nombreAlbum %></a></td>
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


