
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error</title>
    </head>
    <body>
        
        <section>
            <h1>Error <%= (String) request.getAttribute("errorCode")%></h1>
            <p><%=(String) request.getAttribute("errorMsg")%></p>
            
        </section>
        
    </body>
</html>
