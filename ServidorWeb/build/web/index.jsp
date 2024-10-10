<!DOCTYPE html>

<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- BOOTSTRAP CSS  -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" 
              rel="stylesheet" 
              integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" 
              crossorigin="anonymous"
              >
        <!-- CUSTOM CSS -->
        <link rel="stylesheet" href="../${pageContext.request.contextPath}/styles/styles.css"/>

        <!-- BOOTSTRAP JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" 
                integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" 
                crossorigin="anonymous">
        </script>
        
        <!-- CUSTOM JS -->
        <script src="../${pageContext.request.contextPath}/js/altaAlbum.js"></script>
        
    </head>
    <body>
        <h1>Index</h1>
        <a href="/ServidorWeb/AltaAlbum">Ir a alta album</a>
        <button onClick="dostuff()">log</button>

    </body>
</html>
