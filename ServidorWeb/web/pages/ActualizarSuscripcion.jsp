<%@page import="java.time.Year"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modificar Suscripción</title>
        <script src="../${pageContext.request.contextPath}/scripts/actualizarSuscripcion.js" defer></script>
        <link rel="stylesheet" href="../${pageContext.request.contextPath}/styles/variablesGlobales.css"/>
        <link rel="stylesheet" href="../${pageContext.request.contextPath}/styles/clasesAuxiliares.css"/>
        <link rel="stylesheet" href="../${pageContext.request.contextPath}/styles/actualizarSuscripcion.css"/>
        <link rel="stylesheet" href="../${pageContext.request.contextPath}/styles/nav.css"/>
        <link rel="icon" href="Resource/ImagenesPerfil/espotify-icon.png" type="image/png" sizes="16x16">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    </head>
    
    <% 
        String estadoSuscripcion = (String) request.getAttribute("estadoSuscripcion");
        String tipoSuscripcion = (String) request.getAttribute("tipoSuscripcion");
        String fechaSuscripcion = (String) request.getAttribute("fechaSuscripcion");
    %>
    
    <jsp:include page="../headerIndex.jsp"/>
    <%@ include file="../WEB-INF/jspf/Nav.jspf" %>

    <body class="bodyContainer">
        <section class="sectionContainer">
            <div class="opcionesContainer">
                <h1 class="textAligned">
                    Mi suscripción: 
                   <span id="estadoSuscripcion" class=""><%=estadoSuscripcion%></span>
                </h1>
                <p class="datosSuscripcion">Tipo: <%=tipoSuscripcion%></p>
                
                <% if (estadoSuscripcion.equals("Pendiente")) { %>
                    <p class="datosSuscripcion">Fecha de solicitud: <%=fechaSuscripcion%></p>
                <% } else { %>
                    <p class="datosSuscripcion">Fecha de contratación: <%=fechaSuscripcion%></p>
                <% } %>
                <div class="btnsContainer" id="btnsOpciones">
                    
                    <% if (estadoSuscripcion.equals("Pendiente") || estadoSuscripcion.equals("Vencida")) { %>
                        <% if (estadoSuscripcion.equals("Pendiente")) { %>
                            <button id="btnCancelarSuscripcion" class="btncustom btnSecondary">
                                Cancelar Suscripción
                            </button>
                        <% } %>
                        <button id="btnPagarSuscripcion" class="btncustom btnPrimary">
                            <%=estadoSuscripcion.equals("Vencida") ? "Renovar" : "Pagar Suscripción"%>
                        </button>
                     <% } else {%>
                        <p>
                            No se puede modificar esta suscripción.
                        </p>
                    <%}%>
                </div>
                
            </div>
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
                <button type="button" id="btnVolver" class="btncustom btnSecondary">Volver al formulario</button>
                <button type="button" id="btnAceptar" class="btncustom btnPrimary">Volver a la página principal</button>
            </div>
        </div>
    </body>
</html>
