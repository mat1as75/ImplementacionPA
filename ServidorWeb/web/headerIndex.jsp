<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="javax.servlet.http.Cookie"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%@page import="espotify.DataTypes.DTDatosUsuario"%>
<!-- HEADER INDEX -->

    <header>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="styles/headerIndex.css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link rel="icon" href="./Resource/ImagenesPerfil/espotify-icon.png" type="image/png" sizes="16x16">
        <title>Espotify</title>
        
        <div onclick="location.href = 'index.jsp'" class="logo">
            <i class="fa-brands fa-spotify"></i>
            <span class="logo-text">Espotify</span>
        </div>
        <div class="barra-busqueda">
            <form action="SVBarraBusqueda" method="GET">
                <i class="fa-solid fa-magnifying-glass"></i>
                <input type="text" name="consulta" placeholder="Buscar Tema, Álbum, Lista o Usuario ..."required>
                <button type="submit" class="boton-buscar">Buscar</button>
            </form>
        </div>
        
        <% 
            /* Obtener la sesion actual */
            HttpSession sesion = request.getSession(false);
            DTDatosUsuario usuario = (DTDatosUsuario) sesion.getAttribute("usuario");
            /* Verificar las cookies */
//            Cookie[] cookies = request.getCookies();
//            boolean cookieFound = false;
//            
//            if (cookies != null) {
//                for (Cookie c : cookies) {
//                    if ("JSESSIONID".equals(c.getName()) && 
//                        nickname != null && 
//                        c.getValue().equals(usuario.getNickname())) {
//                        cookieFound = true;
//                        break;
//                    }
//                }
//            }
            
        if (usuario != null) { %>
                <!-- EXISTE SESION -->
                <jsp:include page="headerConSesion.jsp"/>
        <%  } else { %>
                <!-- NO EXISTE SESION -->
                <jsp:include page="headerSinSesion.jsp"/>
        <%  } %>
        
