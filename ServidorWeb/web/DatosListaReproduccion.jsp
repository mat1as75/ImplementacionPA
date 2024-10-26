<%@page import="espotify.DataTypes.DTDatosCliente"%>
<%@page import="espotify.DataTypes.DTDatosUsuario"%>
<%@ page import="java.util.List" %>
<%@ page import="espotify.DataTypes.DTDatosListaReproduccion" %>
<%@page import="espotify.DataTypes.DTTemaSimple"%>
<%@page import="espotify.DataTypes.DTTemaGenericoConRutaOUrl"%>
<%@ page import="espotify.logica.Fabrica" %>
<%@ page import="espotify.logica.IControlador" %>

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
        <script src="scripts/ConsultaListaReproduccion.js"></script>
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
                            %>
                            <tr>
                                <td><%= nroTema++%></td>
                                <td><button class="agregar">+</button></td>
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
                                            if (puedeDescargar) {%>
                                    <a href="<%= request.getContextPath() + "/" + ruta%>" download>Descargar</a>
                                    <% } else { %>
                                    <a href="#" onclick="alert('Debe tener una suscripción vigente para descargar el tema.'); return false;">Descargar</a>
                                    <% }
                                        }
                                        // "-"
                                        if ((ruta == null || ruta.isEmpty()) && (url == null || url.isEmpty())) { %>
                                       <span>-</span>
                                    <% }
                                    %>
                                </td>
                            </tr>
                            <% }
                                }
                            %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </body>

