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
        <!-- CUSTOM CSS -->
        <link rel="stylesheet" href="../${pageContext.request.contextPath}/styles/styles.css"/>

        <!-- BOOTSTRAP JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" 
                integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" 
                crossorigin="anonymous">
        </script>
        
        <!-- SORTABLE (sortable list)-->
        <script src="https://cdn.jsdelivr.net/gh/RubaXa/Sortable/Sortable.min.js"></script>
        
        <!-- JQUERY -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        
         <!-- CUSTOM JS -->
        <script src="${pageContext.request.contextPath}/js/altaAlbum.js" defer></script>
        
        
    </head>
    <body>
        <h1>Alta album</h1>
        <h3>Usuario: <%=(String)request.getAttribute("usuario")%></h3>
        <section class="sectionFormAlbum">
            <form id="formAlbum" method="POST">
                <div>
                    
                </div>
                <div class="temasContainer">
                    <div class="inputTemaContainer">
                        <label for="nombreTema">Nombre de tema: </label>
                        <input type="text" id="nombreTema"/>
                    </div>

                    <div class="inputTemaContainer">
                        <label for="duracionTema">Duracion: </label>
                        <input type="number" id="duracionTema">
                    </div>

                    <ol class="list-group listaTemas" id="olTemasCreados">
                    </ol>

                    <div class="btnsTemasContainer">
                        <button type="button" class="btn btn-primary" onClick="addLiItem()">Agregar tema</button>
                        <button type="button" class="btn btn-danger" onClick="removeLiItem()">Remover tema</button>
                    </div>
                    
                    <div class="btnsTemasContainer">
                        <button type="submit" class="btn btn-success">Submit</button>
                    </div>
                    
                        
                </div>
            </form>
            
            <a href="/ServidorWeb">Volver</a>

        </section>
        
        
    </body>
</html>
