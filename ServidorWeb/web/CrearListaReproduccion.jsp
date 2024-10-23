<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Crear Lista de Reproducción</title>
        <link rel="stylesheet" href="styles/CrearListaReproduccion.css">
    </head>
    <body>

        <h1 style="text-align: center;">Crear Lista de Reproducción</h1>
        
        <div class="form-container">
            <form id="form-submit" action="SvCrearListaReproduccion" method="POST" enctype="multipart/form-data" onsubmit="return validar()">
                <div class="form-group">
                    <label for="nombreLista">Nombre de la Lista:</label>
                    <input type="text" id="nombreLista" name="nombreLista">
                    <div id="errorNombreLista" class="error">
                        <i class="fa-solid fa-circle-exclamation"></i>
                        <p>(!) Por favor introduce un nombre para la lista.</p>
                    </div>
                </div>
                
                <div class="form-group">
                    <label for="imagenLista">Imagen (opcional):</label>
                    <input type="file" id="imagenLista" name="imagenLista" accept="image/*" onchange="cargarImagen(event)">
                    <div id="errorImagenLista" class="error">
                        <i class="fa-solid fa-circle-exclamation"></i>
                        <p>(!) Por favor selecciona una imagen válida.</p>
                    </div>
                </div>

                <button type="submit">Crear Lista</button>
            </form>

            <canvas id="imagenCanvas" width="200" height="200"></canvas>
        </div>
        
        <script src="scripts/CrearListaReproduccion.js"></script>
    </body>
</html>