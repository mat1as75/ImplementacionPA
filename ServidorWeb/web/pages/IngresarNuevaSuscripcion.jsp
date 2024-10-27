<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Nueva Suscripción</title>
        
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <script src="../${pageContext.request.contextPath}/scripts/ingresarNuevaSuscripcion.js" defer></script>
        <link rel="stylesheet" href="../${pageContext.request.contextPath}/styles/variablesGlobales.css"/>
        <link rel="stylesheet" href="../${pageContext.request.contextPath}/styles/clasesAuxiliares.css"/>
        <link rel="stylesheet" href="../${pageContext.request.contextPath}/styles/ingresarNuevaSuscripcion.css"/>
    </head>
    <jsp:include page="../headerIndex.jsp"/>
    
    <body class="bodyContainer">
        <section id="section" class="sectionContainer">
            <h1 class="titlePrimary textAligned">Suscripciones</h1>
            <div class="serviciosContainer">
                <h2 class="textAligned">Suscribiéndote podrás acceder a diversos servicios:</h2>
                <ul>
                    <li>Descargar tus temas preferidos directo a tu dispositivo.</li>
                    <li>Crear tus propias listas de reproducción con temas de tu elección.</li>
                    <li>Publicar y compartir tus listas con tus amigos.</li>
                    <li>Guardar a temas, álbums o listas en tus favoritos.</li>
                    <li>Seguir a otros usuarios y artistas y estar al tanto de sus novedades.</li>
                </ul>
            </div>
            
            <div class="planesContainer">
                <h2 class="textAligned">Nuestros planes</h2>

                <div class="cardPlanes" data-idRadio="planSemanal">
                    <h3 >Plan Semanal</h3>
                    <p>2.99 USD</p>
                    <input class="radioCard" type="radio" id="planSemanal" data-plan="Semanal" name="tipoDePlan">
                </div>
                
                <div class="cardPlanes" data-idRadio="planMensual">
                    <h3>Plan Mensual</h3>
                    <p>7.99 USD</p>
                    <input class="radioCard" type="radio" id="planMensual" data-plan="Mensual" name="tipoDePlan">

                </div>
                
                <div class="cardPlanes selectedCard" data-idRadio="planAnual">
                    <h3>Plan Anual</h3>
                    <p>79.9 USD</p>
                    <input class="radioCard" type="radio" id="planAnual" data-plan="Anual" name="tipoDePlan" checked>
                </div>
                
                <div class="btnConfirmarContainer">
                <button type="button" id="btnConfirmar" class="btncustom btnPrimary">
                    Confirmar
                </button>
            </div>
            </div>
            
            
            <div id="modalResultado" class="customModal hidden">
                <div class="modalContainer">
                    <p id="pResultado"></p>
                    <button type="button" id="btnSalir" class="btncustom btnSecondary">Salir</button>
                    <button type="button" id="btnContinuar" class="btncustom btnPrimary hidden">Continuar al pago</button>
                </div>
            </div>
        </section>
    </body>
</html>
