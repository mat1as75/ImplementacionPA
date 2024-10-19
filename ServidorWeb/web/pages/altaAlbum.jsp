<%@page import="java.time.Year"%>
<%@page import="espotify.DataTypes.DTGenero_Simple"%>
<%@page import="java.util.List"%>
<%@page import="espotify.DataTypes.DTGenero"%>
<%@page import="java.util.ArrayList"%>
<%@page import="espotify.logica.IControlador"%>
<%@page import="espotify.logica.Fabrica"%>

<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
        <script src="../${pageContext.request.contextPath}/scripts/altaAlbum.js" defer></script>
        
        <!-- CUSTOM CSS -->
        <link rel="stylesheet" href="../${pageContext.request.contextPath}/styles/altaAlbum.css"/>

        
    </head>
    <body>
        <% 
        Fabrica fb = Fabrica.getInstance();
        IControlador ictrl = fb.getControlador();
        ArrayList<DTGenero_Simple> generos = ictrl.getListaDTGeneroSimple();
        %>
        
        <h1>Subir nuevo álbum</h1>
        <form class="formContainer" id="formAlbum" action="AltaAlbum" method="POST" enctype="multipart/form-data">
            
            <fieldset class="formSubContainer">
                <div class="inputsContainer">
                    <label for="nombreAlbum">Nombre del album: </label>
                    <input type="text" id="nombreAlbum" minlength="1" maxlength="255" required/>
                </div>
                <div class="errorDiv d-none">                
                    <span class="spanError" id="errorNombreAlbum"></span>
                </div>
                <div class="inputsContainer">
                    <label for="anioAlbum">Año: </label>
                    <input type="number" id="anioAlbum" min="1900" max="<%=Year.now()%>" required/>
                </div>
                <div class="errorDiv d-none">                
                    <span class="spanError" id="errorAnioAlbum"></span>
                </div>

                <div class="inputsContainer">
                    <label for="inputPortadaAlbum">Portada: </label>
                    <input class="d-block" type="file" id="inputPortadaAlbum" accept="image/*" name="inputPortadaAlbum"/>
                </div>
            </fieldset>
            
            <fieldset class="formSubContainer">
                <h2>Géneros</h2>
                <div class="inputsContainer">
                    <ul class="treeGeneros">
                        <li>
                            <details open>
                                <summary>Géneros</summary>
                                <ul>
                                    <%
                                    for (DTGenero_Simple dg : generos) {
                                        if (dg.getNombreGeneroPadre().equals("Genero")) {

                                            String nombreGenero = dg.getNombreGenero();
                                    %>
                                    <li>
                                        <details>
                                            <summary> <label for="cbox-gen-<%=nombreGenero%>"> <%=nombreGenero%> </label>
                                                <input type="checkbox" name="cbox-<%=nombreGenero%>" data-group="cboxGeneros" data-name="<%=nombreGenero%>" id="cbox-gen-<%=nombreGenero%>"/>
                                            </summary>
                                            <%if (!dg.getSubgeneros().isEmpty()) { %>
                                            <ul>
                                                <% for (String subGenero : dg.getSubgeneros()) { %>
                                                <li id="li-subgen-<%=subGenero%>">
                                                    <label for="cbox-gen-<%=subGenero%>"><%=subGenero%></label>
                                                    <input type="checkbox" name="cbox-<%=subGenero%>" data-group="cboxGeneros" data-name="<%=subGenero%>" id="cbox-gen-<%=subGenero%>"/>
                                                </li>
                                                <%}%>
                                            </ul>
                                            <%}%>
                                        </details>
                                    </li>
                                    <% }}%>
                                </ul>
                            </details>
                        </li>
                    </ul>
                </div>
                <div class="errorDiv d-none">                
                    <span class="spanError" id="errorGeneros"></span>
                </div>
            </fieldset>
            
            <fieldset class="formSubContainer">
                <h2>Temas</h2>
                <div class="inputsContainer">
                    <label for="nombreTema">Nombre de tema: </label>
                    <input type="text" id="nombreTema"/>
                </div>
                <div class="errorDiv d-none">                
                    <span class="spanError" id="errorNombreTema"></span>
                </div>

                <div class="inputsContainer">
                    <label for="duracionTema">Duracion: </label>
                    <input type="text" id="duracionTema" placeholder="3:45" maxlength="5">
                </div>
                <div class="errorDiv d-none">                
                    <span class="spanError" id="errorDuracionTema"></span>
                </div>

                <div class="inputsContainer">
                    <label for="tipoDeAcceso">Tipo de acceso: </label>
                    <input type="text" id="tipoDeAcceso" placeholder="https://dominio.com/tema">
                    <label for="radioRuta">Subir</label>
                    <input type="radio" name="tipoDeAcceso" id="radioRuta" value="ruta">
                    <label for="radioUrl">URL</label>
                    <input type="radio" name="tipoDeAcceso" id="radioUrl" value="url" checked>
                </div>
                <div class="errorDiv d-none">                
                    <span class="spanError" id="errorUrlTema"></span>
                </div>
                <button type="button" class="btn btn-primary" onClick="addLiTema()">Agregar tema</button>
            </fieldset>                
            
            
            <section class="formSubContainer">
                <h3>Temas agregados</h3>
                <div class="divAyuda">
                    <i class="bi bi-info-circle"></i>
                    <span class="spanAyuda">Arrastre los temas para reordenar su posición en el álbum.</span>
                </div>      
                <div class="temasContainer">
                    <ol class="list-group listaTemas" id="olTemasCreados">
                        <div class="divAyuda" id="divAvisoSinTemas">
                            <span class="spanAviso">No se han agregado temas.</span>
                        </div>  
                    </ol>
                </div>
                <div class="errorDiv d-none">                
                    <span class="spanError" id="errorTemasVacios"></span>
                </div>            

                <button type="button" class="btn btn-primary" id="btnValidar">Validar Datos</button>
            </section>
            
            <div id="modalDataVerification" class="customModal d-none">
                <div class="modalContainer">
                    <p>Presione confirmar para crear el album o cancelar para revisar los campos</p>
                    <button type="button" class="btn btn-danger" id="btnCancelSubmit">Cancelar</button>
                    <button type="submit" class="btn btn-primary" id="btnSubmit" disabled>Confirmar</button>
                </div>
            </div>
        </form>

        <div id="modalResultado" class="customModal d-none">
            <div class="modalContainer">
                <p id="modalText"></p>
                <button id="btnCloseModalResultado" type="button" class="btn btn-primary">Aceptar</button>
            </div>
        </div>
                            
        
                            
        <a href="/ServidorWeb">Volver</a>        
        
    </body>
</html>