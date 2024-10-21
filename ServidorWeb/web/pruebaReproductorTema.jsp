<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
        <!-- BOOTSTRAP CSS  -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" 
              rel="stylesheet" 
              integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" 
              crossorigin="anonymous"
              >
        
        <!-- BOOTSTRAP ICONS -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        
        <!-- CUSTOM JS -->
        <script src="scripts/reproductorDeMusica.js" defer></script>
        
        <!-- CUSTOM CSS -->
        <link rel="stylesheet" href="styles/variablesGlobales.css"/>
        <link rel="stylesheet" href="styles/reproductorDeMusica.css"/>
    </head>
    <body>
        <h1>Reproductor</h1>
        
        <%@include file="../WEB-INF/jspf/ReproductorDeMusica.jspf" %>
        
        <br/>
        <button id="btn1">tema1 con ruta</button>
        <button id="btn2">tema2 con ruta</button>

        <script>
            document.querySelector("#btn1").addEventListener("click", () => {
                play(570,  "", "");
            });

            document.querySelector("#btn2").addEventListener("click", () => {
                play(5711,  "", "");
            });
        </script>
    </body>
</html>
