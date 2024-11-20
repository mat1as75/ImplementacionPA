<%@page import="java.time.Year"%>
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
        <link rel="stylesheet" href="../${pageContext.request.contextPath}/styles/nav.css"/>
        <link rel="icon" href="Resource/ImagenesPerfil/espotify-icon.png" type="image/png" sizes="16x16">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    </head>
    <jsp:include page="../headerIndex.jsp"/>
    <%@ include file="../WEB-INF/jspf/Nav.jspf" %>

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
                <button type="button" id="btnSeleccionar" class="btncustom btnPrimary">
                    Seleccionar
                </button>
            </div>
            </div>
            
        </section>
        
        <section class="sectionContainer">
             <form id="paymentForm" class="formContainer hidden" method="POST">
                
                <div class="inputsContainer">
                    <label for="inputCCN">Número de tarjeta:</label>
                    <input id="inputCCN" name="CCN" 
                           type="text" 
                           class="inputValidated" 
                           placeholder="1234123412341234"
                           minlength="16" maxlength="16" required
                           />
                </div>
                
                <div class="inputsContainer">  
                    <label for="inputCVV">CVV:</label>
                    <input id="inputCVV" name="CVV" 
                           type="text" 
                           class="inputValidated" 
                           placeholder="123"
                           minlength="3" maxlength="3" required
                           />
                </div>
                
                <div class="inputsContainer">  
                    <label for="inputVencimiento">Vencimiento:</label>
                    <input id="inputVencimiento" name="vencimiento" 
                           type="date"
                           class="inputValidated" 
                           min="<%=Year.now()%>" required
                           />
                </div>
                           
                <div class="inputsContainer">  
                    <label for="inputPropietario">Nombre del propietario:</label>
                    <input id="inputPropietario" name="propietario" 
                           type="text" 
                           class="inputValidated" 
                           minlength="3" maxlength="255" required
                           />
                </div>
                           
                <button id="btnSubmitPago" class="btncustom btnPrimary" type="button">Confirmar</button>
            </form>
        </section>
        <div id="modalResultado" class="customModal hidden">
            <div class="modalContainer">
                <p id="pResultado"></p>
                <button type="button" id="btnAceptar" class="btncustom btnSecondary">Aceptar</button>
                <button type="button" id="btnRegresarIndex" class="btncustom btnPrimary">Volver a la página principal</button>
            </div>
        </div>
    </body>
</html>
