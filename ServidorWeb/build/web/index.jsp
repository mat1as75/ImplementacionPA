<%@page import="espotify.DataTypes.DTTemaSimple"%>
<%@page import="espotify.DataTypes.DTTemaGenericoConRutaOUrl"%>
<%@page import="java.util.List"%>
<%@page import="espotify.DataTypes.DTDatosListaReproduccion"%>
<%@page import="java.util.ArrayList"%>
<%@page import="espotify.DataTypes.DTDatosCliente"%>
<%@page import="java.util.Map"%>
<%@page import="espotify.DataTypes.DTDatosUsuario"%>
<%@page import="espotify.logica.IControlador"%>
<%@page import="espotify.logica.Fabrica"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%

    // Para consultar listas de reproducción.
    String nombreLista = request.getParameter("nombreLista");
    Fabrica f = Fabrica.getInstance();
    IControlador iControlador = f.getControlador();
    DTDatosListaReproduccion datosLista = null;
    String tipoLista = null;
    String errorMsg = null;

    try {
        List<String> nombresListasParticulares = iControlador.getNombresListasParticulares();
        List<String> nombresListasPorDefecto = iControlador.getNombresListasPorDefecto();

        if (nombresListasParticulares.contains(nombreLista)) {
            tipoLista = "Particular";
        } else if (nombresListasPorDefecto.contains(nombreLista)) {
            tipoLista = "Por Defecto";
        }
        if (tipoLista != null) {
            datosLista = iControlador.ConsultarListaReproduccion(tipoLista, nombreLista);
        }
        String fotoLista = datosLista != null ? datosLista.getFotoLista() : null;
        if (fotoLista != null) {
            fotoLista = fotoLista.substring(2);
        }
    } catch (Exception e) {
        errorMsg = "Error al obtener los datos de la lista: " + e.getMessage();
    }
    
    // Comprobar suscripción vigente
    HttpSession sesion = request.getSession();
    String nicknameSesion = (String) sesion.getAttribute("nickname");
    String rolSesion = (String) sesion.getAttribute("rol");
    DTDatosUsuario datosU = null;
    DTDatosCliente datosC = null;
    String estadoSuscripcionSesion = null;

    if ("Cliente".equals(rolSesion) && nicknameSesion != null) {
        try {
            datosU = iControlador.getDatosUsuario(nicknameSesion);
            datosC = (DTDatosCliente) datosU;
            if (datosC.getSuscripcion() != null) {
                estadoSuscripcionSesion = datosC.getSuscripcion().getEstadoSuscripcion();
            }
        } catch (Exception e) {
            errorMsg = "Error al obtener datos del usuario: " + e.getMessage();
        }
    }
    // Comprobar si puede descargar
    boolean puedeDescargar = "Vigente".equals(estadoSuscripcionSesion);
%>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="scripts/index.js"></script>

    <link rel="stylesheet" href="styles/index.css"/> <!-- CSS DEL BODY -->
    <link rel="icon" href="Resource/ImagenesPerfil/espotify-icon.png" type="image/png" sizes="16x16">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" 
          rel="stylesheet" 
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" 
          crossorigin="anonymous"
    >
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <script src="scripts/reproductorDeMusica.js" defer></script>
    <script src="scripts/ConsultaListaReproduccion.js" defer></script>
    <link rel="stylesheet" href="styles/variablesGlobales.css"/>
    <link rel="stylesheet" href="styles/reproductorDeMusica.css"/>
    <link rel="stylesheet" href="styles/clasesAuxiliares.css"/>
    <link rel="stylesheet" href="styles/DatosListaReproduccion.css"/>
</head>

<jsp:include page="headerIndex.jsp" />

<body>
    <%  /* Mensaje de Inicio Sesión */
        String mensajeInicioSesion = request.getParameter("mensaje");
        if ("abierta".equals(mensajeInicioSesion)) {
    %>
    <script>
        mostrarMensajeInicioSesion("Sesión Iniciada de forma exitosa!");
    </script>
    <%  }  %>

    <%  /* Mensaje de Cierre Sesión */
        String mensajeCierreSesion = request.getParameter("mensaje");
        if ("cerrada".equals(mensajeCierreSesion)) {
    %>
    <script>
        mostrarMensajeCierreSesion("Sesión Cerrada de forma exitosa!");
    </script>
    <%  }  %>

    <%  /* Muestra Resultados de la SearchBar */
        Map<String, String> resultados = (Map<String, String>) request.getAttribute("resultados");
        if (resultados != null) {
    %>
    <jsp:include page="resultadosBusqueda.jsp" />
    <%  }  %>

    <!-- TABS-CONTENT -->
    <div class="tabs">
        <ul class="tab-links">
            <li class="active"><a href="#tab1">Géneros</a></li>
            <li><a href="#tab2">Artistas</a></li>
            <li><a href="#tab3">Suscripción</a></li>
            <li><a href="#tab4">Listas Particulares</a></li>
        </ul>

        <div class="tab-content">
            <div id="tab1" class="tab active">
                <h2>Géneros</h2>
                <div class="lista-generos">
                    <ul class="generos-list">
                        <!-- Aquí se llenarán los géneros -->
                    </ul>
                </div>
                <div class="tabs-container">
                    <!-- Aquí se agregarán las nuevas pestañas para álbumes y listas de reproducción -->
                </div>
            </div>

            <div id="tab2" class="tab">
                <h2>Artistas</h2>
                <div class="ArtistasLista">
                    <!-- Aquí listarás los artistas del sistema --> 
                    <!-- Luego de seleccionar uno de ellos, listarás los álbumes que le corresponden -->
                </div>
            </div>

            <div id="tab3" class="tab">
                <h2>Suscripción</h2>
                <!-- Aquí va lo relacionado con la suscripción -->
            </div>

            <div id="tab4" class="tab">
                <h2>Listas Particulares</h2>
                <!-- Aquí listarás las listas de reproducción particulares públicas para que luego se muestre su información -->
            </div>
        </div>
    </div>

    <!-- REPRODUCTOR-CONTENT -->
    <div class="reproductor">
        <!-- Aquí irá el contenido del reproductor de música -->
    </div>
</body>
</html>
