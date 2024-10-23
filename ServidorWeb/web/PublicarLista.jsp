<%@page import="espotify.DataTypes.DTDatosCliente"%>
<%@page import="java.util.ArrayList"%>
<%@page import="espotify.logica.IControlador"%>
<%@page import="espotify.logica.Fabrica"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Listas Pegadas</title>
    <link rel="stylesheet" href="styles/PublicarLista.css">
    <script type="text/javascript">
        function mostrarPopup() {
            alert('No tienes subscripciones vigentes');
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
        <% String mensaje=(String)request.getAttribute("mensaje");
        if(mensaje!=null){%>
            <div id="felisidades" class="error" style="color: red;"><%=mensaje%></div>
            <%}%><br><br><br><br> 
            <form id="miFormulario" action="SVPublicarLista" method="POST" onsubmit="return validarSeleccion();">
                <div class="contenedor-listas">
                    <div class="lista-contenedor">
                        <h3>Nickname de Clientes</h3>
                        <ul>
                            <%

                                ArrayList<String> nicknamesClientes = i.getNicknamesClientesListasPrivadas();
                                for (String nickname : nicknamesClientes) { %>
                                    <li class="selectable" onclick="seleccionarNickname(this, '<%= nickname %>')" ><%= nickname %></li>
                                <% } %>
                        </ul>
                    </div>

                    <div class="lista-contenedor">
                        <h3>Listas Privadas</h3>
                        <ul id="listasPrivadas">
                            <!-- Aquí se actualizarán las listas privadas -->
                        </ul>
                    </div>
                </div>

                <!-- Campos ocultos para almacenar los valores seleccionados -->
                <input type="hidden" name="nickname" id="inputNickname">
                <input type="hidden" name="listaPrivada" id="inputListaPrivada">

                <button type="submit">Enviar</button>
            </form>
            <script src="scripts/PublicarLista.js"></script>
             <script>
                function validarSeleccion() {
                    var nickname = document.getElementById('inputNickname').value;
                    var listaPrivada = document.getElementById('inputListaPrivada').value;

                    if (!nickname) {
                        alert("Por favor, selecciona un nickname de cliente.");
                        return false;
                    }
                    if (!listaPrivada) {
                        alert("Por favor, selecciona una lista privada.");
                        return false;
                    }
                    return true; // Permitir el envío del formulario si ambas selecciones son válidas.
                }

            </script>
        <%}%>
</body>
</html>
