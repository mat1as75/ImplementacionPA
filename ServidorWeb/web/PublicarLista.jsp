<%@page import="webservices.ListaReproduccionService"%>
<%@page import="webservices.ListaReproduccionServiceService"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Publicar Lista</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link rel="stylesheet" href="styles/variablesGlobales.css"/>
        <link rel="stylesheet" href="styles/clasesAuxiliares.css"/>
        <link rel="stylesheet" href="styles/PublicarLista.css">
        <link rel="stylesheet" href="styles/nav.css"/>

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
            ListaReproduccionServiceService listaRepWS = new ListaReproduccionServiceService();
            ListaReproduccionService listaRepPort = listaRepWS.getListaReproduccionServicePort();
            List<Object> listasPrivadasObjs = listaRepPort.getNombresDeListasPrivadasDeCliente(nicknameSesion).getColeccion();
            List<String> listasPrivadas = new ArrayList();
            for (Object o : listasPrivadasObjs) {
                listasPrivadas.add((String) o);
            }
        %>           

        <jsp:include page="headerIndex.jsp"/>
        <%@ include file="../WEB-INF/jspf/Nav.jspf" %>
        
        <% 
            String mensaje = (String) request.getAttribute("mensaje");
            if (mensaje != null) {
        %>
            <div id="resultado" ><%=mensaje%></div>
        <% } %>

        <form id="miFormulario" action="SVPublicarLista" method="POST" onsubmit="return validarSeleccion();">
            <input type="hidden" name="listaOculta" id="listaOculta" value="">

            <div class="contenedor-listas">
                <div class="lista-contenedor nickname">
                    <h3>Nickname de Cliente</h3>
                    <p><%=nicknameSesion%></p>
                </div>

                <div class="lista-contenedor listas-privadas">
                    <h3>Listas Privadas</h3>
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


            <button class="btncustom btnPrimary" type="submit">Enviar</button>
        </form>
    </body>
</html>
