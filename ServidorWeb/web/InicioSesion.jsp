<%@page contentType="text/html" pageEncoding="UTF-8"%>

<head>
    <script src="scripts/inicioSesion.js"></script>
    <link rel="stylesheet" href="styles/InicioSesion.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link rel="icon" href="Resource/ImagenesPerfil/espotify-icon.png" type="image/png" sizes="16x16">
    <title>Iniciar Sesión</title>
</head>
<body>
    <!-- BANNER RECTANGULAR -->
    <div class="contenido-banner">

        <div class="logo">
            <i onclick="window.location.href='index.jsp';" id="logoBanner" class="fa-brands fa-spotify"></i>
        </div>
        <h1 id="tituloPrincipal" class="titulo">Inicia sesión en Espotify</h1>

        <div class="mensaje_request">
            <%
                String mensaje = (String) request.getAttribute("mensaje");
                if (mensaje != null) {%>
            <i class="fa-solid fa-circle-exclamation"></i>
            <p class="mensaje-texto" ><%= mensaje%></p>
            <%  }%>
        </div>
        
        <hr role="presentation" class="barra-divisora">

        <!-- LOGIN USUARIO -->
        <div class="usuario-login">

            <!-- GRUPO INPUTS -->
            <div class="grupo-inputs">
                <form id="form-submit" action="SVInicioSesion" method="POST" onsubmit="return validate()">

                    <!-- INPUT USUARIO -->
                    <div class="grupo-form">
                        <label class="label-usuario">Email o Nickname</label>
                        <input
                            type="text" id="usuario" 
                            name="usuario" placeholder="Email o Nickname de usuario" 
                            pattern="^[a-zA-Z0-9_-]{3,16}$|^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$" 
                        />
                        <div id="errorUsuario" class="error-usuario">
                            <i class="fa-solid fa-circle-exclamation"></i>
                            <!-- SUGERENCIA DE ERROR -->
                            <p id="errorU" class="texto">Ingresa tu nickname de Espotify o tu dirección de correo electrónico.</p>
                        </div>
                    </div>

                    <!-- INPUT CONTRASENA -->
                    <div id="grupo-form-pass" class="grupo-form">
                        <label class="label-contrasena">Contraseña</label>
                        <input
                            type="password" id="contrasena" 
                            name="contrasena" placeholder="Contraseña" 
                            />
                        <button type="button" id="alternarVisibilidadPass" class="alternar-visible">
                            <i id="btnVisibilidadP" class="fa-regular fa-eye-slash" onclick="pass()"></i>
                        </button>
                        <div id="errorContrasena" class="error-contrasena">
                            <i class="fa-solid fa-circle-exclamation"></i>
                            <!-- SUGERENCIA DE ERROR -->
                            <p id="errorP" class="texto">Por favor introduce tu contraseña.</p>
                        </div>
                    </div>

                    <!-- BOTON CONFIRMAR -->
                    <div class="grupo-form">
                        <button type="submit" id="btnLogin" class="btn-confirmar">
                            <p>Iniciar Sesion</p>
                        </button>
                    </div>

                </form>
                <div id="mensaje"></div>
            </div>

        </div>

    </div>
</body>
</html>

