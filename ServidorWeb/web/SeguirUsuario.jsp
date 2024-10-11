<%@page import="java.util.List"%>
<%@page import="espotify.logica.IControlador"%>
<%@page import="espotify.logica.Fabrica"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Seguir Usuario</title>
        <link rel="stylesheet" href="styles/SeguirUsuario.css">
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    </head>
    <body>
        <form id="miFormulario" action="SVSeguirUsuario" method="post">
            <table>
                <tr>
                    <td>
                        <label for="Seguidor">Nickname del cliente que desea realizar el seguimiento</label>
                        <select id="Seguidor" name="Seguidor" required style="width: 200px;" onclick="actualizarSeguidos()">
                            <option value="" disabled selected>Seleccione un seguidor</option>
                            <%
                                Fabrica f = Fabrica.getInstance();
                                IControlador i = f.getControlador();
                                List<String> nicknamesClientes = i.getNicknamesClientes();
                                for (String c : nicknamesClientes) {
                            %>
                                    <option value="<%=c%>"><%=c%></option>
                                <%}%>
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
    </body>
</html>
