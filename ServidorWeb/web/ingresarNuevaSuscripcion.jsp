<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Nueva Suscripción</title>
        
        <script src="scripts/ingresarNuevaSuscripcion.js" defer></script>
        <link rel="stylesheet" href="styles/variablesGlobales.css"/>
        <link rel="stylesheet" href="styles/ingresarNuevaSuscripcion.css"/>

    </head>
    <body class="bodyContainer">
        <h1 class="mainTitle">Suscripciones</h1>
        <section id="section" class="sectionContainer">
            <div class="serviciosContainer">
                <h2>Suscribiéndote podrás acceder a diversos servicios:</h2>
                <ul>
                    <li>Descargar tus temas preferidos directo a tu dispositivo.</li>
                    <li>Crear tus propias listas de reproducción con temas de tu elección.</li>
                    <li>Publicar y compartir tus listas con tus amigos.</li>
                    <li>Guardar a temas, álbums o listas en tus favoritos.</li>
                    <li>Seguir a otros usuarios y artistas y estar al tanto de sus novedades.</li>
                </ul>
            </div>
            
            <h2>Nuestros planes</h2>
            <div class="planesContainer">
                    
                <div class="cardPlanes" data-idRadio="planSemanal">
                    <h3>Plan Semanal</h3>
                    <p>2.99 USD</p>
                    <input type="radio" id="planSemanal" data-plan="Semanal" name="tipoDePlan">
                </div>
                
                <div class="cardPlanes" data-idRadio="planMensual">
                    <h3>Plan Mensual</h3>
                    <p>7.99 USD</p>
                    <input type="radio" id="planMensual" data-plan="Mensual" name="tipoDePlan">

                </div>
                
                <div class="cardPlanes selectedCard" data-idRadio="planAnual">
                    <h3>Plan Anual</h3>
                    <p>79.9 USD</p>
                    <input type="radio" id="planAnual" data-plan="Anual" name="tipoDePlan" checked>
                </div>
                
            </div>
            <div>
                <button type="button" id="btnConfirmar">
                    Confirmar
                </button>
            </div>
            
            <div id="modalResultado" class="customModal hidden">
                <div class="modalContainer">
                    <p id="pResultado"></p>
                    <button type="button" id="btnSalir">Salir</button>
                    <button type="button" id="btnContinuar" class="hidden">Continuar al pago</button>
                </div>
            </div>
        </section>
    </body>
</html>
