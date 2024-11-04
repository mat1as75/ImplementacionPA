<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="javax.servlet.http.Cookie"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%@page import="espotify.DataTypes.DTDatosUsuario"%>
<!DOCTYPE html>
    
<header>
    <link rel="stylesheet" href="styles/headerIndex.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    
    <script src="scripts/headerIndex.js" pefer></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>

    <div onclick="location.href = 'index.jsp'" class="logo">
        <i class="fa-brands fa-spotify"></i>
        <span class="logo-text">Espotify</span>
    </div>
    <div class="barra-busqueda">
        <form action="SVBarraBusqueda" id="form-searchBar" method="GET" onsubmit="return validarFormulario()">
            <i class="fa-solid fa-magnifying-glass"></i>
            <input type="text" name="consulta" placeholder="Buscar Tema, Álbum, Lista o Usuario ..." required>
            <select id="filtro" name="combo" required>
                <option selected disabled hidden>▼</option>
                <option value="tema">Tema</option>
                <option value="lista">Lista</option>
                <option value="album">Album</option>
                <option value="usuario">Usuario</option>
            </select>
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
