<%@page import="java.util.List"%>
<%@page import="espotify.DataTypes.DTDatosCliente"%>
<%@page import="java.util.ArrayList"%>
<%@page import="espotify.logica.IControlador"%>
<%@page import="espotify.logica.Fabrica"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Publicar Lista</title>
    <link rel="stylesheet" href="styles/PublicarLista.css">
    <script>
        function validarSeleccion() {
            const selectedValue = document.querySelector('input[name="listaSeleccionada"]:checked');
            if (!selectedValue) {
                alert("Por favor, selecciona una lista.");
                return false;
            }
            document.getElementById('listaOculta').value = selectedValue.value;
            return true;
        }
    </script>
</head>
<body>

<%
    HttpSession sesion = request.getSession();
    String nicknameSesion = (String) sesion.getAttribute("nickname");
    // Solo para pruebas, elimina esto en producción
    Fabrica f = Fabrica.getInstance();
    IControlador i = f.getControlador();
%>           
<header>
    <div class="main-container">
        <h1>Publicar Lista</h1>
    </div>
</header> 
<% String mensaje = (String) request.getAttribute("mensaje");
if (mensaje != null) {%>
<div id="felisidades" class="error" style="color: red;"><%=mensaje%></div>
<%}%>

<form id="miFormulario" action="SVPublicarLista" method="POST" onsubmit="return validarSeleccion();">
    <input type="hidden" name="listaOculta" id="listaOculta" value="">

    <div class="contenedor-listas">
        <div class="lista-contenedor nickname">
            <h3>Nickname de Cliente</h3>
            <p><%=nicknameSesion%></p>
        </div>

        <div class="lista-contenedor listas-privadas">
            <h3>Listas Privadas</h3>
            <% 
                List<String> listasPrivadas = i.listasCreadasEstadoPrivadoTrue(nicknameSesion); %>
            <ul id="listasPrivadas">
                <%
                    for (String lista : listasPrivadas) {
                %>
                <li>
                    <label>
                        <input type="radio" name="listaSeleccionada" value="<%= lista%>">
                        <%= lista%>
                    </label>
                </li>
                <% }%>
            </ul>
        </div>
    </div>


    <button type="submit">Enviar</button>
</form>
</body>
</html>
