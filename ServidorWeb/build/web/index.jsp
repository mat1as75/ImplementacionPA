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
    
    //Para consultar listas de reproduccion.//
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
    // Comprobar suscripcion vigente
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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
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
    <%  /* Mensaje de InicioSesion */
        String mensajeInicioSesion = request.getParameter("mensaje");
        if ("abierta".equals(mensajeInicioSesion)) {
    %>
    <script>
        mostrarMensajeInicioSesion("Sesion Iniciada de forma exitosa!");
    </script>
    <%  }  %>

    <%  /* Mensaje de CierreSesion */
        String mensajeCierreSesion = request.getParameter("mensaje");
        if ("cerrada".equals(mensajeCierreSesion)) {
    %>
    <script>
        mostrarMensajeCierreSesion("Sesion Cerrada de forma exitosa!");
    </script>
    <%  }  %>

    <%  /* Muestra Resultados de la SearchBar */
        Map<String, String> resultados = (Map<String, String>) request.getAttribute("resultados");
        if (resultados != null) {
    %>
    <jsp:include page="resultadosBusqueda.jsp" />
    <%  }  %>

    <%
        /*
        PONER ESTILOS DE DIV-CONTENT PARA QUE LOS DIVS TABS Y REPRODUCTOR QUEDEN EN COLUMNA
         */
    %>


    <!-- TABS-CONTENT -->
    <div class="tabs">
        <ul class="tab-links">
            <li class="active"><a href="#tab1">Generos</a></li>
            <li><a href="#tab2">Artistas</a></li>
            <li><a href="#tab3">Suscripcion</a></li>
            <li><a href="#tab4">Listas Particulares</a></li>
        </ul>

        <div class="tab-content">
            <div id="tab1" class="tab active">

                <p>Tab #1 content goes here!</p>
                <div class="generosLista">
                    <!-- Aqui va el codigo de mateo donde lista los generos como corresponde segun la imagen de la letra -->
                    <div class="tabs-AoL">
                        <ul class="tab-links-AoL">
                            <li class="active"><a href="#tabA">Albumes</a></li>
                            <li><a href="#tabB">Listas de Reproduccion</a></li>
                            
                        </ul>
                    </div>
                        
                    <div class="lista-albumes">
                        <!-- Aqui van los albumes por el genero seleccionado -->
                    </div>
                    <div class="lista-listasR">
                        <!-- Aqui van las listas por defecto del genero seleccionado -->
                        <div class="lista-detalles">
                            <div class="info-container">
                                <div class="lista-fotoLista">
                                    <!-- Foto de la lista-->
                                    <%
                                        String fotoLista = datosLista != null ? datosLista.getFotoLista() : null;
                                        if (fotoLista != null && !fotoLista.isEmpty()) {
                                            fotoLista = fotoLista.substring(2);
                                        } else {
                                            fotoLista = "Resource/ImagenesPerfil/Default-Photo-Profile.jpg";
                                        }
                                    %>
                                    <img src="<%= request.getContextPath() + "/" + fotoLista%>" alt="Imagen de la lista" />
                                </div>


                                <div class="lista-info">
                                    <!-- Nombre, Tipo y Genero/Cliente de la lista -->
                                    <h1><%= datosLista != null ? datosLista.getNombreLista() : "Lista no encontrada"%></h1> 
                                    <p><span>Tipo:</span> <%= tipoLista != null ? tipoLista : "Desconocido"%></p>
                                    <% if ("Por Defecto".equals(tipoLista)) {%>
                                    <p><span>Género:</span> <%= datosLista != null ? datosLista.getGenero() : "N/A"%></p>
                                    <% } else if ("Particular".equals(tipoLista)) {%>
                                    <p><span>Cliente:</span> <%= datosLista != null ? datosLista.getCliente() : "N/A"%></p>
                                    <% }%>

                                    <!-- Agregar lista a favoritos -->
                                    <form action="SVGuardarListaFavorito" method="post">
                                        <input type="hidden" name="nombreLista" value="<%= nombreLista%>"/>
                                        <button type="submit" class="boton-agregar">Guardar</button>
                                    </form>
                                </div>

                            </div>

                            <div class="separador"></div>

                            <div class="temas-lista">
                                <!-- Temas de la lista -->
                                <table class="tabla-temas">
                                    <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>Agregar</th>
                                            <th>Tema</th>
                                            <th>Duración</th>
                                            <th>Ruta/Link</th> 
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <% int nroTema = 1; %>
                                        <% if (datosLista != null) {
                                                for (DTTemaSimple tema : datosLista.getTemas()) {
                                                    int duracionSegundos = tema.getDuracionSegundos();
                                                    int minutos = duracionSegundos / 60;
                                                    int segundos = duracionSegundos % 60;

                                                    DTTemaGenericoConRutaOUrl temaRutaOUrl = iControlador.getDTTemaGenericoConRutaOUrl(tema.getIdTema());
                                                    String srcPortada = fotoLista;
                                        %>
                                        <!-- Escuchar tema por fila -->
                                        <tr class="row-hover"onclick="play('<%= tema.getIdTema()%>', '<%= tema.getNombreTema()%>', '<%= srcPortada%>')">
                                            <td><%= nroTema++%></td>
                                            <td>
                                                <form action="SVGuardarTemaFavorito" method="post">
                                                    <input type="hidden" name="idTema" value="<%= tema.getIdTema()%>"/> 
                                                    <button type="submit" class="agregar">+</button> 
                                                </form>
                                            </td>
                                            <td><%= tema.getNombreTema()%></td>
                                            <td><%= String.format("%d:%02d", minutos, segundos)%></td>
                                            <td class="ruta-link">  
                                                <%
                                                    String url = (temaRutaOUrl != null) ? temaRutaOUrl.getUrlTema() : null;
                                                    String ruta = (temaRutaOUrl != null) ? temaRutaOUrl.getRutaTema() : null;
                                                    // "Ver enlace"
                                                    if (url != null && !url.isEmpty()) {
                                                        if (!url.startsWith("http://") && !url.startsWith("https://")) {
                                                            url = "http://" + url;
                                                        }
                                                %>
                                                <a href="<%= url%>" target="_blank">Ver enlace</a>
                                                <%
                                                    }
                                                    // "Descargar"
                                                    if (ruta != null && !ruta.isEmpty()) {
                                                        ruta = ruta.substring(ruta.lastIndexOf("Resource/"));

                                                        if (puedeDescargar) {
                                                %>
                                                <a href="<%= request.getContextPath() + "/" + ruta%>" download="<%= tema.getNombreTema()%>.mp3">Descargar</a>
                                                <%
                                } else { %>
                                                <a href="#" onclick="alert('Debe tener una suscripción vigente para descargar el tema.'); return false;">Descargar</a>
                                                <%
                                                        }
                                                    }
                                                    // Si no hay URL ni archivo disponible
                                                    if ((ruta == null || ruta.isEmpty()) && (url == null || url.isEmpty())) { %>
                                                <span>-</span>
                                                <%
                                                    }
                                                %>
                                            </td>
                                        </tr>
                                        <%
                                                }
                                            }
                                        %>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>





            </div>
            <div id="tab2" class="tab">
                <p>Tab #2 content goes here!</p>
                <div class="ArtistasLista">
                    <!--Aqui listo los artistas del sistema --> 
                    <!--Luego de seleccionado uno de ellos le listo los albumes que le corresponden -->
                </div>

            </div>
            <div id="tab3" class="tab">
                <p>Tab #3 content goes here!</p>
                <!--Aqui va lo relacionado con suscripcion -->
            </div>
            <div id="tab4" class="tab">
                <p>Tab #4 content goes here!</p>
                <!--Aqui listo las listas de reproduccion particulares publicas para que luego se muestre su informacion -->
            </div>
        </div>
    </div>

    <!-- REPRODUCTOR-CONTENT -->
    <div class="reproductor">

    </div>

   
    <%
//                    ArrayList<String> nombresAlbumes = new ArrayList<>();
//                    ArrayList<String> nombresListasR = new ArrayList<>();
//                    
//                    String nombreGenero = null;
//                    
//                    <% if () { %>
  




</body>
