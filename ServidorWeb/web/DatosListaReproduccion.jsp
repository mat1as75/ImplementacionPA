<%@page import="java.util.List" %>
<%@page import="webservices.DataTypes.DtDatosCliente"%>
<%@page import="webservices.DataTypes.DtDatosUsuario"%>
<%@page import="webservices.DataTypes.DtTemaSimple"%>
<%@page import="webservices.DataTypes.DtTemaGenericoConRutaOUrl"%>
<%@page import="webservices.DataTypes.DtDatosListaReproduccion" %>
<%@page import="webservices.DataTypes.DtDatosUsuario" %>
<%@page import="webservices.ListaReproduccionService"%>
<%@page import="webservices.ListaReproduccionServiceService"%>
<%@page import="webservices.UsuarioService"%>
<%@page import="webservices.UsuarioServiceService"%>
<%@page import="webservices.ContenidoService"%>
<%@page import="webservices.ContenidoServiceService"%>
<%@page import="webservices.ArrayListContainer"%>
<%@page import="java.util.stream.Collectors"%>
<%@page import="webservices.NullableContainer"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String nombreLista = request.getParameter("nombreLista");
    ListaReproduccionServiceService lservice = new ListaReproduccionServiceService();
    ListaReproduccionService listaService = lservice.getListaReproduccionServicePort();
    NullableContainer datosListaContainer = null;
    DtDatosListaReproduccion datosLista = null;
    String tipoLista = null;
    String errorMsg = null;

    try {
        ArrayListContainer nombresListasParticularesConteiner = listaService.getNombresListasParticulares();
        List<String> nombresListasParticulares = nombresListasParticularesConteiner.getColeccion().stream()
                    .map(String::valueOf)
                    .collect(Collectors.toList());
                    
        ArrayListContainer nombresListasPorDefectoConteiner = listaService.getNombresListasPorDefecto();
        List<String> nombresListasPorDefecto = nombresListasPorDefectoConteiner.getColeccion().stream()
                    .map(String::valueOf)
                    .collect(Collectors.toList());

        if (nombresListasParticulares.contains(nombreLista)) {
            tipoLista = "Particular";
        } else if (nombresListasPorDefecto.contains(nombreLista)) {
            tipoLista = "Por Defecto";
        }
        if (tipoLista != null) {
            datosListaContainer = listaService.consultarListaReproduccion(tipoLista, nombreLista);
            datosLista = datosListaContainer.getDtDatosListaReproduccion();
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
        
    UsuarioServiceService uservice = new UsuarioServiceService();
    UsuarioService usuarioService = uservice.getUsuarioServicePort();
    NullableContainer datosUContainer = null;
    NullableContainer datosCContainer = null;
    
    DtDatosUsuario datosU = null;
    DtDatosCliente datosC = null;
    String estadoSuscripcionSesion = null;

    if ("Cliente".equals(rolSesion) && nicknameSesion != null) {
        try {
            datosUContainer = usuarioService.getDatosUsuario(nicknameSesion);
            datosU = datosUContainer.getDtDatosUsuario();
            datosC = (DtDatosCliente) datosU;
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
    <link rel="stylesheet" href="styles/nav.css"/>

</head>
<jsp:include page="headerIndex.jsp"/>
<%@ include file="../WEB-INF/jspf/Nav.jspf" %>

<body>
    <% if (!"Artista".equals(rolSesion)) { %>
    <div class="containerMain">
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
                       <%
                       if(rolSesion != null && puedeDescargar && rolSesion != "Artista"){
                       %>
                    <!-- Agregar lista a favoritos -->
                   
                    <form action="SVGuardarListaFavorito" method="post">
                        <input type="hidden" name="nombreLista" value="<%= nombreLista%>"/>
                        <input type="hidden" name="nickname" value="<%= nicknameSesion %>"/>
                        <button type="submit" class="boton-agregar">Guardar</button>
                    </form>
                    <%
                    }
                    %>
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
                        </tr>
                    </thead>
                    <tbody>
                        <% int nroTema = 1; %>
                        <% if (datosLista != null) {
                                for (DtTemaSimple tema : datosLista.getTemas()) {
                                    int duracionSegundos = tema.getDuracionSegundos();
                                    int minutos = duracionSegundos / 60;
                                    int segundos = duracionSegundos % 60;
                                    
                                    ContenidoServiceService cservice = new ContenidoServiceService();
                                    ContenidoService contenidoService = cservice.getContenidoServicePort();
                                    
                                    DtTemaGenericoConRutaOUrl temaRutaOUrl = contenidoService.getDTTemaGenericoConRutaOUrl(tema.getIdTema());
                                    String srcPortada = fotoLista;
                        %>
                        <!-- Escuchar tema por fila -->
                        <tr class="row-hover" onclick="play('<%= tema.getIdTema()%>', '<%= tema.getNombreTema()%>', '<%= srcPortada%>')">
                            <td><%= nroTema++%></td>
                            <td>
                                 <%
                                    if(rolSesion != null && puedeDescargar && rolSesion != "Artista"){
                                %>
                                <form action="SVGuardarTemaFavorito" method="post">
                                    <input type="hidden" name="idTema" value="<%= tema.getIdTema()%>"/> 
                                    <input type="hidden" name="nickname" value="<%= nicknameSesion %>"/>
                                    <input type="hidden" name="tipo" value="Lista"/>
                                    <input type="hidden" name="identificador" value="<%= nombreLista %>"/>
                                    <button type="submit" class="agregar">+</button> 
                                </form>
                                <%
                                }
                                %>
                            </td>
                            <td><%= tema.getNombreTema()%></td>
                            <td><%= String.format("%d:%02d", minutos, segundos)%></td>
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
    <%@ include file="../WEB-INF/jspf/ReproductorDeMusica.jspf" %>

    <% } %>
</body>

