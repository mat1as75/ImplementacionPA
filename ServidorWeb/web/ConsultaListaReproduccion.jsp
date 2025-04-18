<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Consulta Lista Reproducción</title>
        <link rel="stylesheet" href="styles/ConsultaListaReproduccion.css">
    </head>
    <jsp:include page="headerIndex.jsp"/>
    <body>
        <h1>Consulta Lista Reproducción</h1>

        <div class="op">
            <button id="PorGenero" onclick="mostrarPorGenero()">Por Género</button>
            <button id="PorLista" onclick="mostrarPorListaParticular()">Por Lista</button>
        </div>

        <div class="mosaico-container"> 
            <div class="mosaico" id="mosaicoGeneros">
                <h2>Listas de Reproducción Disponibles</h2>
                <c:if test="${not empty listaGeneros}">
                    <c:forEach var="lista" items="${listaGeneros}">
                        <div class="mosaico-item" onclick="mostrarPorListaPorDefecto('${lista}')">${lista}</div> 
                    </c:forEach>
                </c:if>
                <c:if test="${empty listaGeneros}">
                    <p>No hay listas de reproducción disponibles.</p>
                </c:if>
            </div>

            <div class="mosaico" id="mosaicoListasParticulares" style="display:none;">
                <h2>Listas de Reproducción Disponibles</h2>
                <c:if test="${not empty listaParticular}">
                    <c:forEach var="lista" items="${listaParticular}">
                        <div class="mosaico-item" onclick="DatosListaReproduccion('${lista}')">${lista}</div>
                    </c:forEach>
                </c:if>
                <c:if test="${empty listaParticular}">
                    <p>No hay listas de reproducción disponibles.</p>
                </c:if>
            </div>

            <div class="mosaico" id="mosaicoListasPorDefecto" style="display:none;">
                <h2>Listas por Defecto para el Género Seleccionado</h2>
                <c:if test="${not empty listaPorDefecto}">
                    <c:forEach var="lista" items="${listaPorDefecto}">
                        <div class="mosaico-item" onclick="DatosListaReproduccion('${lista}')">${lista}</div>
                    </c:forEach>
                </c:if>
                <c:if test="${empty listaPorDefecto}">
                    <p>No hay listas de reproducción por defecto disponibles para este género.</p>
                </c:if>
            </div>
        </div> 

        <script src="scripts/ConsultaListaReproduccion.js"></script>
    </body>
</html>
