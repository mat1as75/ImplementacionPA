<%@page import="espotify.logica.IControlador"%>
<%@page import="espotify.logica.Fabrica"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="javax.servlet.http.Cookie"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%@page import="espotify.DataTypes.DTDatosUsuario"%>
<!-- HEADER CON SESION -->

<%
    /* Obtener la sesion actual */
    Fabrica fb = Fabrica.getInstance();
    IControlador control = fb.getControlador();
    
    /* Verificar las cookies */
    Cookie[] cookies = request.getCookies();
    String sesionId = null;

    if (cookies != null) {
        for (Cookie c : cookies) {
            if ("sessionId".equals(c.getName())) {
                sesionId = c.getValue();
                break;
            }
        }
    }
    
    HttpSession sesion = request.getSession(false);
    String nicknameSesion = (String) sesion.getAttribute("nickname");
    String rolSesion = (String) sesion.getAttribute("rol");
    
    DTDatosUsuario datosUsuario = control.getDatosUsuario(nicknameSesion);
    String nombreSesion = null;
    String apellidoSesion = null;
    String fotoPerfilSesion = null;
    String emailSesion = null;

    if (sesionId != null) {
        nombreSesion = datosUsuario.getNombreUsuario();
        apellidoSesion = datosUsuario.getApellidoUsuario();
        fotoPerfilSesion = datosUsuario.getFotoPerfil();
        //System.out.println("FOTO PERFIL: "+ fotoPerfilUsuario);
        fotoPerfilSesion = (fotoPerfilSesion != null) ? fotoPerfilSesion.substring(2) : "Resource/ImagenesPerfil/Default-Photo-Profile.jpg";
        //System.out.println("FOTO PERFIL2: "+ fotoPerfilUsuario);
        emailSesion = datosUsuario.getEmail();
        System.out.println("-----------");
        System.out.println("NICKNAME SESION: " + nicknameSesion);
    }
%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script>
    // Pasar el nickname de la sesion a una variable global
    var nickname = "<%= request.getAttribute("nickname") != null ? request.getAttribute("nickname") : "" %>";
</script>
<script src="scripts/headerConSesion.js"></script>
<script src="scripts/consultaPerfilUsuario.js" defer></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
<link rel="icon" href="Resource/ImagenesPerfil/espotify-icon.png" type="image/png" sizes="16x16">

<div class="container-info">
    <img src="<%= fotoPerfilSesion%>" alt="Foto de Perfil" class="perfil-img">
    <div class="divisor d-none d-sm-block"></div>
    <div class="info-usuario">
        <form action="SVConsultaPerfilUsuario" method="GET" onclick="asignarPerfilConsultado()" >
            <%
                String usuarioConsultado = nicknameSesion;
                request.setAttribute("usuarioConsultado", usuarioConsultado);
            %>
            <button type="submit" id="btn-nickname" class="nickname-usuario">
                <%= nombreSesion + " " + apellidoSesion%>
            </button>
        </form>
            <button onclick="window.location.href='ConsultaPerfilUsuario.jsp'" id="btn-favoritos" class="boton-favoritos">
            <i class="fa-solid fa-star"></i>
            <span class="text-favoritos">
                <% if (rolSesion.equals("Cliente")) { %>
                Favoritos
                <% } else { %>
                Albumes
                <% }%>
            </span>
        </button>
        <form id="form-CierreSesion" action="SVCierreSesion" method="GET">
            <button id="btn-clsSession" class="boton-clsSession">cerrar sesión</button>
        </form>
    </div>
</div>

</header>

