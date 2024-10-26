<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Alta de Perfil</title>
    <link rel="stylesheet" href="styles/AltaPerfil.css">
</head>
<body>

<h1>Formulario de Registro</h1>

<% String mensaje=(String)request.getAttribute("mensaje");
    if(mensaje!=null){%>
        <div id="felisidades" class="error"><%=mensaje%></div>
   <%}%>    
<div class="form-canvas-container">
    <form action="SVAltaPerfil" method="POST" enctype="multipart/form-data">
       <table>
            <tr>
                <td><label for="userType">Tipo de Usuario:</label></td>
                <td>
                    <select id="userType" name="userType" onchange="mostrarCamposAdicionales()" required>
                        <option value="cliente">Cliente</option>
                        <option value="artista">Artista</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td><label for="nickname">Nickname:</label></td>
                <td><input type="text" id="nickname" name="nickname"  value="${nickname != null ? nickname : ''}" required></td>
                <td><img id="checknick"src="./imagenes/check.svg" style="display: none;">
                    <img id="exclamacionnick"src="./imagenes/exclamacion.svg"style="display: none;">
                </td>
                <td><div id="errorMsg" class="error"></div></td> 
            </tr>
            <tr>
                <td><label for="email">Correo Electrónico:</label></td>
                <td><input type="email" id="email" name="email" value="${email != null ? email : ''}" required></td>
                <td><img id="checkemail"src="./imagenes/check.svg" style="display: none;">
                    <img id="exclamacionemail"src="./imagenes/exclamacion.svg" style="display: none;"></td>
                <td><div id="errorMsg2" class="error"></div></td>  
            </tr>
            <tr>
                <td><label for="nombre">Nombre:</label></td>
                <td><input type="text" id="nombre" name="nombre" value="${nombre != null ? nombre : ''}" required></td>
            </tr>
            <tr>
                <td><label for="apellido">Apellido:</label></td>
                <td><input type="text" id="apellido" name="apellido" value="${apellido != null ? apellido : ''}" required></td>
            </tr>
            <tr>
                <td><label for="fechaNacimiento">Fecha de Nacimiento:</label></td>
                <td><input type="date" id="fechaNacimiento" name="fechaNacimiento" required></td>
            </tr>
            <tr>
                <td><label for="password">Contraseña:</label></td>
                <td><input type="password" id="password" name="password" value="${password != null ? password : ''}" required></td>
            </tr>
            <tr>
                <td><label for="confirmPassword">Confirmar Contraseña:</label></td>
                <td><input type="password" id="confirmPassword" name="confirmPassword" value="${confirmPassword != null ? confirmPassword : ''}" required></td>
                <td><img id="checkpass"src="./imagenes/check.svg" style="display: none;">
                    <img id="exclamacionpass"src="./imagenes/exclamacion.svg" style="display: none;">
                </td>
                <td><div id="errorMsg3" class="error"></div></td>
            </tr>
            <tr>
                <td><label for="imagen">Imagen (opcional):</label></td>
                <td><input type="file" id="imagen" name="imagen" accept="image/*" onchange="cargarImagen(event)" ></td>
                
            </tr>
        
            <!-- Información adicional para Artistas -->
            <tbody id="artistaInfo" class="artista-info">
                <tr>
                    <td><label for="biografia">Biografía (opcional):</label></td>
                    <td><textarea id="biografia" name="biografia" rows="4" cols="30" value="${biografia != null ? biografia : ''}"></textarea></td>
                </tr>
                <tr>
                    <td><label for="sitioWeb">Sitio Web (opcional):</label></td>
                    <td><input type="url" id="sitioWeb" name="sitioWeb" value="${sitioWeb != null ? sitioWeb : ''}"></td>
                </tr>
            </tbody>
        </table>
        <button type="submit">Registrar</button> <button type="button" onclick="Cancelar()">Cancelar</button>
    </form>
     
     
<!-- Canvas para mostrar la imagen -->
    <canvas id="imagenCanvas" width="200" height="200"></canvas>
</div>
 <script src="scripts/AltaPerfil.js"></script>
</body>
</html>
