<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="espotify.logica.IControlador"%>
<%@page import="espotify.logica.Fabrica"%>
<%@page import="javax.servlet.http.Cookie"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%@page import="espotify.DataTypes.DTDatosUsuario"%>
<!-- HEADER CON SESION -->
<!DOCTYPE html>

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
        String emailSesion = null;
        String fotoPerfilSesion = null;

        if (sesionId != null) {
            nombreSesion = datosUsuario.getNombreUsuario();
            apellidoSesion = datosUsuario.getApellidoUsuario();
            fotoPerfilSesion = datosUsuario.getFotoPerfil();
            fotoPerfilSesion = (fotoPerfilSesion != null) ? fotoPerfilSesion.substring(2) : "Resource/ImagenesPerfil/Default-Photo-Profile.jpg";
            emailSesion = datosUsuario.getEmail();

            System.out.println("-----------");
            System.out.println("NICKNAME SESION: " + nicknameSesion);
        }
    %>

    <script>
        // Pasar el nickname de la sesion a una variable global
        var nickname = "<%= request.getAttribute("nickname") != null ? request.getAttribute("nickname") : "" %>";
    </script>
    <script src="scripts/headerConSesion.js"></script>
    <script src="scripts/consultaPerfilUsuario.js" defer></script>

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
