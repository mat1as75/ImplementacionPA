
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
        
        <div id="audioFilePlayerContainer" class="audioPlayerContainer">
            
            <img class="imgReproductor" src="imagenes/spotifyLogo.png" alt="Logo de spotify"/>
            <p id="nombreTemaEnReproduccionFile">Tema</p>
            <audio id="audioFilePlayer" controls controlslist="nofullscreen" type="audio/mpeg" src="/ServidorWeb/Tema" >
                El cliente no soporta este tipo de audio
            </audio> 
        </div>
        
<!--        <div id="audioFilePlayerContainer" class="audioPlayerContainer">
            <p>Reproductor de archivos</p>
            <audio id="audioFilePlayer" controls controlslist="nofullscreen" type="audio/mpeg" src="/ServidorWeb/Tema" >
                El cliente no soporta este tipo de audio
            </audio> 
            <button id="playBtnFilePlayer" type="button" class="playButton"><i id="iconBtnFilePlayer" class="bi bi-play"></i></button>
        </div>-->
        
        <div id="audioLinkPlayerContainer" class="audioPlayerContainer">
            <iframe 
                anonymous 
                id="audioLinkPlayer"  
                referrerpolicy="strict-origin-when-cross-origin"
                src="https://youtube.com/embed/cHj8PtRdMQk">
                El cliente no soporta este tipo de audio
            </iframe> 
            <p id="nombreTemaEnReproduccionLink">Tema</p>

        </div>
    </body>
</html>
