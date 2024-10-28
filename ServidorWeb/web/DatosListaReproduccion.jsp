<%@page import="espotify.DataTypes.DTDatosCliente"%>
<%@page import="espotify.DataTypes.DTDatosUsuario"%>
<%@page import="java.util.List" %>
<%@page import="espotify.DataTypes.DTDatosListaReproduccion" %>
<%@page import="espotify.DataTypes.DTTemaSimple"%>
<%@page import="espotify.DataTypes.DTTemaGenericoConRutaOUrl"%>
<%@ page import="espotify.logica.Fabrica" %>
<%@ page import="espotify.logica.IControlador" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
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
<jsp:include page="headerIndex.jsp"/>
<body>
    <div class="container">
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
                                    System.out.println("context: " + request.getContextPath());
                                    System.out.println("ruta " + ruta);
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
        <div class="reproductor-contenedor">
            <%@ include file="../WEB-INF/jspf/ReproductorDeMusica.jspf" %>
        </div>
    </div>
</body>

