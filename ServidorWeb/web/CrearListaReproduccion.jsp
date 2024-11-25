<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Crear Lista de Reproducción</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="styles/variablesGlobales.css"/>     
    <link rel="stylesheet" href="styles/clasesAuxiliares.css"/>
    <link rel="stylesheet" href="styles/CrearListaReproduccion.css">
    <link rel="stylesheet" href="styles/nav.css"/>
</head>
<body>
    <jsp:include page="headerIndex.jsp"/>
    <%@ include file="../WEB-INF/jspf/Nav.jspf" %>

    <h1>Crear Lista de Reproducción</h1>
    
    <div class="crearListaForm">
        <form id="crearListaForm" enctype="multipart/form-data" onsubmit="return false;">
            <div class="form-group">
                <label for="nombreLista">Nombre de la Lista:</label>
                <input type="text" id="nombreLista" name="nombreLista">
                <div id="errorNombreLista" class="error">
                    <i class="fa-solid fa-circle-exclamation"></i>
                    <p>Por favor introduce un nombre para la lista.</p>
                </div>
            </div>
            
            <div class="form-group">
                <label for="imagenLista">Imagen (opcional):</label>
                <input type="file" id="imagenLista" name="imagenLista" accept="image/*" onchange="cargarImagen(event)">
                <div id="errorImagenLista" class="error">
                    <i class="fa-solid fa-circle-exclamation"></i>
                    <p>Por favor selecciona una imagen válida.</p>
                </div>
            </div>

            <button class="btncustom btnPrimary" type="submit">Crear Lista</button>
        </form>

        <canvas id="imagenCanvas" width="200" height="200"></canvas>
    </div>
    
    <script src="scripts/CrearListaReproduccion.js"></script>
</body>
</html>
