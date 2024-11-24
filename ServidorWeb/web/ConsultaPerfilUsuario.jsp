<%@page import="java.util.Date"%>
<%@page import="java.util.ArrayList"%>
<%@page import="webservices.DataTypes.DtUsuarioGenerico"%>
<%@page import="webservices.UsuarioService"%>
<%@page import="webservices.UsuarioServiceService"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="scripts/consultaPerfilUsuario.js" defer></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link rel="icon" href="Resource/ImagenesPerfil/espotify-icon.png" type="image/png" sizes="16x16">
        <link rel="stylesheet" href="styles/nav.css"/>
        <title>Consulta Perfil Usuario</title>
    </head>

    <jsp:include page="headerIndex.jsp"/>
    <%@ include file="../WEB-INF/jspf/Nav.jspf" %>

         
    <%
        HttpSession sesion = request.getSession(false);
        String rolSesion = (String) sesion.getAttribute("rol");
        DtUsuarioGenerico DTusuarioConsultado = (DtUsuarioGenerico) sesion.getAttribute("DTusuarioConsultado");
        
        UsuarioServiceService serviceU = new UsuarioServiceService();
        UsuarioService serviceUsuario = serviceU.getUsuarioServicePort();
    %>
    <%
        boolean autoConsulta = false;
        boolean esCliente = false;
        
        /* Sesion consulto perfil propio */
        if (DTusuarioConsultado == null) { 
            autoConsulta = true;
            if (rolSesion.equals("Cliente")) {
                esCliente = true;
            }
            System.out.println("ACA");
        } else { /* Sesion consulto perfil de un tercero */
            String tipoUsuario = serviceUsuario.getTipoUsuario(DTusuarioConsultado.getNicknameUsuario());
            if (tipoUsuario.equals("Cliente")) {
                esCliente = true;
            }
            System.out.println("ACA2");
        }
    %>
    
    <%  if (autoConsulta) {
            if (esCliente) { %>
            <jsp:include page="ConsultaPerfilCliente.jsp"/>
        <%  } else {  %>
            <jsp:include page="ConsultaPerfilArtista.jsp"/>
        <%  } %> 
    <%  } else {  %>
        <%  if (esCliente) { %>
            <jsp:include page="ConsultaPerfilCliente.jsp"/>
        <%  } else {  %>
            <jsp:include page="ConsultaPerfilArtista.jsp"/>
        <%  } %> 
    <%  }  %>
    
</html>

        

