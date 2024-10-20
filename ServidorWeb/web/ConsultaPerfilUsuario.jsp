<%@page import="espotify.logica.Fabrica"%>
<%@page import="espotify.DataTypes.DTDatosCliente"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.ArrayList"%>
<%@page import="espotify.DataTypes.DTDatosArtista"%>
<%@page import="espotify.DataTypes.DTDatosUsuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link rel="icon" href="Resource/ImagenesPerfil/espotify-icon.png" type="image/png" sizes="16x16">
        <title>Consulta Perfil Usuario</title>
    </head>

    <jsp:include page="headerIndex.jsp"/>

    <%
        Fabrica fb = Fabrica.getInstance();
        HttpSession sesion = request.getSession(false);
        String tipoUsuario = (String) sesion.getAttribute("rol");
        String perfilConsultado = (String) sesion.getAttribute("perfilConsultado");
    %>

    <%
//        if (tipoUsuario.equals("Visitante")) {
//            
//        }
//        
//        
//        
//        if (nicknameUsuarioConsultar != null) {
//            if (sesion != null && rolUsuario.equals("Visitante") && ) {
//            
//            }
//        }
            
    %>
    
    <%  if (sesion != null && tipoUsuario.equals("Cliente")) { %>
            <jsp:include page="ConsultaPerfilCliente.jsp"/>
    <%  } else if (tipoUsuario.equals("Artista")) {  %>
            <jsp:include page="ConsultaPerfilArtista.jsp"/>
    <%  }  %>
    

        

