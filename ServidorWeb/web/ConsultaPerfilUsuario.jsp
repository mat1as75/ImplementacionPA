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
        DTDatosUsuario DTusuarioConsultado = (DTDatosUsuario) sesion.getAttribute("DTusuarioConsultado");
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
    
    <%  if (DTusuarioConsultado instanceof DTDatosCliente) { %>
    <%System.out.println("ACA DTDATOSCLIENTE");%>
            <jsp:include page="ConsultaPerfilCliente.jsp"/>
    <%  } else {  %>
    <%System.out.println("ACA DTDATOSARTISTA");%>
            <jsp:include page="ConsultaPerfilArtista.jsp"/>
    <%  }  %>
    

        

