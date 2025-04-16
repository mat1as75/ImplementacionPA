
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error</title>
        <%
            
            /*
            Los atributos son seteados en el request por el Servlet quien hace el redireccionamiento hacia el SVError
            Luego el SVError redirige a este Jsp.
            El codigo de error se elige en base los codigos de errores HTTP.
            El mensaje de error es el de la excepcion capturada o, en caso contrario,
            un mensaje definido en el Servlet que hace el redireccionamiento.
            */
            
            Object errCodeObj = request.getAttribute("errorCode");
            Object errCodeMsg = request.getAttribute("errorMsg");
            int responseStatus = response.getStatus();
        %>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <link rel="stylesheet" href="../${pageContext.request.contextPath}/styles/variablesGlobales.css"/>
        <link rel="stylesheet" href="../${pageContext.request.contextPath}/styles/clasesAuxiliares.css"/>
        <link rel="stylesheet" href="../${pageContext.request.contextPath}/styles/errorPage.css"/>
        <link rel="stylesheet" href="../${pageContext.request.contextPath}/styles/nav.css"/>

    </head>
    <body class="bodyContainer">
        <jsp:include page="../headerIndex.jsp"/>
        <%@ include file="../WEB-INF/jspf/Nav.jspf"%>

        <section class="sectionContainer roundedContainer">
            <%-- 
                Si no recibo el codigo del error desde un redirect, 
                entonces uso el codigo designado por Tomcat 
            --%>
            <h1>Error <%= errCodeObj == null ? responseStatus : errCodeObj.toString() %></h1>
            
            <%--
                404: Si no existe el recurso buscado Tomcat envia un response con status 404
                Si el codigo de error no fue seteado en un redirect, entonces pongo un mensaje generico
            --%>
            <% if (responseStatus == 404) { %>
                <p>Recurso no encontrado</p>
            <% } else if (errCodeObj == null) { %>
                <p>Error interno del servidor</p>
            <% } else { %>
                <p><%= errCodeMsg == null ? "Error interno del servidor" : errCodeMsg.toString() %></p>
            <% } %>    
            <img class="errorImg" src="../${pageContext.request.contextPath}/imagenes/exclamacion.svg" alt="símbolo de exclamación"/>
        </section>
        
    </body>
</html>
