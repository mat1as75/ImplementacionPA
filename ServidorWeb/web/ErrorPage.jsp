
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
            
        %>
    </head>
    <body>
        
        <section>
            <h1>Error <%= errCodeObj == null ? 500 : errCodeObj.toString() %></h1>
            <p><%= errCodeMsg == null ? "Internal Server Error" : errCodeMsg.toString()%></p>
            
        </section>
        
    </body>
</html>
