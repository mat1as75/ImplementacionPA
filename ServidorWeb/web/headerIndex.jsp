<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="javax.servlet.http.Cookie"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%@page import="espotify.DataTypes.DTDatosUsuario"%>
<!DOCTYPE html>
    
<header>
    <link rel="stylesheet" href="styles/headerIndex.css"/>

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
        HttpSession sesion = request.getSession(false);

        /* Verificar las cookies */
        Cookie[] cookies = request.getCookies();
        boolean loggedIn = false;

        if (cookies != null) {
            for (Cookie c : cookies) {
                if ("sessionId".equals(c.getName()) && sesion.getId().equals(c.getValue())) {
                    loggedIn = true;
                    break;
                }
            }
        }
        if (loggedIn) {
            sesion = request.getSession(false);
            if (sesion != null) {
                /* EXISTE SESION */ %>
                <% System.out.println("CON SESION: " + (String) sesion.getAttribute("nickname")); %>
                <jsp:include page="headerConSesion.jsp"/>
        <%  }
        } else { %>
            <% /* NO EXISTE SESION */ %>
            <% System.out.println("SIN SESION"); %>
            <jsp:include page="headerSinSesion.jsp"/>
    <%  }  %>
