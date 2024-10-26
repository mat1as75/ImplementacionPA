<%@page import="espotify.DataTypes.DTDatosCliente"%>
<%@page import="espotify.DataTypes.DTDatosUsuario"%>
<%@page import="java.util.List"%>
<%@page import="espotify.logica.IControlador"%>
<%@page import="espotify.logica.Fabrica"%>
<%@page import="javax.servlet.ServletException"%>
<%@page import="javax.servlet.annotation.MultipartConfig"%>
<%@page import="javax.servlet.annotation.WebServlet"%>
<%@page import="javax.servlet.http.HttpServlet"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>
<%@page import="javax.servlet.http.HttpServletResponse"%>
<%@page import="javax.servlet.http.Part"%>
<%@page import="javax.servlet.http.HttpSession"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Seguir Usuario</title>
    <link rel="stylesheet" href="styles/SeguirUsuario.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script type="text/javascript">
        function mostrarPopup() {
            alert('No tienes inscripciones vigentes');
        }
    </script>
</head>
<body>
    <%  
        HttpSession sesion = request.getSession();
        String nicknameSesion = (String) sesion.getAttribute("nickname");
        String rolSesion = (String) sesion.getAttribute("rol");
        Fabrica f = Fabrica.getInstance();
        IControlador i = f.getControlador();
        
        boolean vigente = false; // Declarar la variable antes de usarla
        
        if ((nicknameSesion!=null)&&(rolSesion!=null)&&(rolSesion.equals("Cliente"))) {
            DTDatosCliente datosC = (DTDatosCliente) i.getDatosUsuario(nicknameSesion);
            if((datosC!=null)&&(datosC.getSuscripcion()!=null)){
                String estadoSuscripcionSesion = datosC.getSuscripcion().getEstadoSuscripcion();
                vigente = estadoSuscripcionSesion.equals("Vigente");
            }
            
            if (!vigente) {
    %>
                <script type="text/javascript">
                    window.onload = mostrarPopup; // Llama a la función para mostrar el popup
                </script>
    <%
            }
        }
    %>

    <% if (vigente) { %>
     <header>
        <div class="main-container">
            <h1>Seguir Usuario</h1>
        </div>
    </header>   
        <form id="miFormulario" action="SVSeguirUsuario" method="post">
            <table>
                <tr>
                    <td>
                        <label for="Seguidor">Nickname del cliente que desea realizar el seguimiento</label>
                        <select id="Seguidor" name="Seguidor" required style="width: 200px;" onclick="actualizarSeguidos()">
                            <option value="" disabled selected>Seleccione un seguidor</option>
                            <%
                                List<String> nicknamesClientes = i.getNicknamesClientes();
                                for (String c : nicknamesClientes) {
                            %>
                            <option value="<%= c %>"><%= c %></option>
                            <% } %>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="Seguido">Nickname del usuario (cliente/artista) al que desea seguir</label>
                        <select id="Seguido" name="Seguido" required style="width: 200px;">
                            <!-- Aquí se actualizarán las opciones mediante AJAX -->
                        </select>
                    </td>
                </tr>
            </table> 
            <button type="submit">Registrar</button>    
        </form>      
        <script src="scripts/SeguirUsuario.js"></script>                
    <% } %>
</body>
</html>
