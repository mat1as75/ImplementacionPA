<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="javax.servlet.http.Cookie"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%@page import="espotify.DataTypes.DTDatosUsuario"%>
<!-- HEADER CON SESION -->

<%
    /* Obtener la sesion actual */
    HttpSession sesion = request.getSession(false);
    String rolUsuario = (String) sesion.getAttribute("rol");
    DTDatosUsuario datosUsuario = (DTDatosUsuario) sesion.getAttribute("usuario");
    String nickname = null;
    String nombreUsuario = null;
    String apellidoUsuario = null;
    String fotoPerfilUsuario = null;
    String emailUsuario = null;

    if (rolUsuario != null) {
        nickname = datosUsuario.getNicknameUsuario();
        nombreUsuario = datosUsuario.getNombreUsuario();
        apellidoUsuario = datosUsuario.getApellidoUsuario();
        fotoPerfilUsuario = datosUsuario.getFotoPerfil();
        //System.out.println("FOTO PERFIL: "+ fotoPerfilUsuario);
        fotoPerfilUsuario = (fotoPerfilUsuario != null) ? fotoPerfilUsuario.substring(2) : "Resource/ImagenesPerfil/Default-Photo-Profile.jpg";
        //System.out.println("FOTO PERFIL2: "+ fotoPerfilUsuario);
        emailUsuario = datosUsuario.getEmail();
    }
%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="scripts/consultaPerfilUsuario.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
<link rel="icon" href="./Resource/ImagenesPerfil/espotify-icon.png" type="image/png" sizes="16x16">

<div class="container-info">
    <img src="<%= fotoPerfilUsuario%>" alt="Foto de Perfil" class="perfil-img">
    <div class="divisor d-none d-sm-block"></div>
    <div class="info-usuario">
        <form action="SVConsultaPerfilUsuario" method="GET" >
            <button type="submit" id="btn-nickname" class="nickname-usuario">
                <%= nombreUsuario + " " + apellidoUsuario%>
            </button>
        </form>
        <button onclick="window.location.href='ConsultaPerfilUsuario.jsp#favoritos'" id="btn-favoritos" class="boton-favoritos">
            <i class="fa-solid fa-star"></i>
            <span class="text-favoritos">
                <% if (rolUsuario.equals("Cliente")) { %>
                Favoritos
                <% } else { %>
                Albumes
                <% }%>
            </span>
        </button>
        <button id="btn-clsSession" class="boton-clsSession">cerrar sesión</button>
    </div>
</div>

</header>

