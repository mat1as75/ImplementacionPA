///* Seleccionamos los elementos necesarios */
//const inputUsuario = document.getElementById("usuario");
//const inputContrasena = document.getElementById("contrasena");
//const errorUsuario = document.getElementById("errorUsuario");
//const errorContrasena = document.getElementById("errorContrasena");
//const form = document.getElementById(".grupo-inputs");
//
//
//
//
///* Validar usuario (Email o Nickname) */
//inputUsuario.addEventListener('input', function() {
//    const usuarioValue = inputUsuario.value;
//    const usuarioPattern = /^[a-zA-Z0-9_-]{3,16}$|^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
//
//    if (!usuarioPattern.test(usuarioValue)) {
//        errorUsuario.style.display = 'block';
//        inputUsuario.classList.add('error');
//    } else {
//        errorUsuario.style.display = 'none';
//        inputUsuario.classList.remove('error');
//    }
//});
//
///* Validar contrasena */
//inputContrasena.addEventListener('input', function() {
//    const contrasenaValue = inputContrasena.value;
//    
//    if (contrasenaValue.trim() ==="") {
//        errorContrasena.style.display = 'block';
//        inputContrasena.classList.add('error');
//    } else {
//        errorContrasena.style.display = 'none';
//        inputContrasena.classList.remove('error');
//    }
//});
//
///* Validar en el envio del formulario */
//form.addEventListener('submit', function(event) {
//    let valido = true;
//    
//    if (inputUsuario.value.match(/^[a-zA-Z0-9_-]{3,16}$|^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/)) {
//        errorUsuario.style.display = 'block';
//        inputUsuario.classList.add('error');
//        valido = false;
//    } 
//    
//    if (inputContrasena.value.trim() === "") {
//        errorContrasena.style.display = 'block';
//        inputContrasena.classList.add('error');
//        valido = false;
//    }
//    
//    if (!valido) {
//        event.preventDefault(); /* Prevenir envio del formulario si hay errores */
//    }
//    
//});
//
//
//
//// Espera a que el DOM se cargue completamente antes de ejecutar el código
//document.addEventListener('DOMContentLoaded', function() {
//    // Selecciona el botón y el campo de contraseña
//    const btnVisibilidadPass = document.getElementById('alternarVisibilidadPass');
//    const inputContrasena = document.getElementById('contrasena');
//    const iconoVisibilidad = document.getElementById('btnVisibilidadP');
//
//    // Verifica que ambos elementos existan
//    if (btnVisibilidadPass && inputContrasena && iconoVisibilidad) {
//        // Event listener para el botón
//        btnVisibilidadPass.addEventListener('click', function(event) {
//            event.preventDefault(); // Evita el comportamiento por defecto del botón
//
//            // Alternar entre mostrar/ocultar la contraseña
//            if (inputContrasena.type === 'password') {
//                inputContrasena.type = 'text'; // Muestra la contraseña
//                iconoVisibilidad.classList.remove('fa-eye-slash'); // Quita el icono de "ocultar"
//                iconoVisibilidad.classList.add('fa-eye'); // Añade el icono de "mostrar"
//            } else {
//                inputContrasena.type = 'password'; // Oculta la contraseña
//                iconoVisibilidad.classList.remove('fa-eye'); // Quita el icono de "mostrar"
//                iconoVisibilidad.classList.add('fa-eye-slash'); // Añade el icono de "ocultar"
//            }
//        });
//    }
//});

//function validate() {
//    var correct = true;
//    
//    var user = ('#usuario').val();
//    if (user === '') {
//        $('#errorU').show();
//        correct = false;
//    } else 
//        $('#errorU').hide();
//    
//    var pass = $('#contrasena').val();
//    if ($('contrasena') === '') {
//        $('#errorU').show();
//        corrent = false;
//    } else 
//        $('#errorU').hide();
//    
//    return correct;
//}


// FUNCIONABA
//document.getElementById('btnVisibilidadPass').addEventListener('click', function () {
//    const passwordInput = document.getElementById('contrasena');
//    const icon = document.getElementById('btnVisibilidadP');
//
//    // Cambiar tipo de input
//    if (passwordInput.type === 'password') {
//        passwordInput.type = 'text'; // Muestra la contraseña
//        icon.classList.remove('fa-eye-slash'); // Cambia el icono
//        icon.classList.add('fa-eye');
//    } else {
//        passwordInput.type = 'password'; // Oculta la contraseña
//        icon.classList.remove('fa-eye'); // Cambia de nuevo el icono
//        icon.classList.add('fa-eye-slash');
//    }
//});



function validate() {
    var correct = true;

    // Obtener los valores de los inputs
    var user = document.getElementById('usuario').value;
    var pass = document.getElementById('contrasena').value;

    // Validar el campo de usuario
    if (user.trim() === '') {
        document.getElementById('errorUsuario').style.display = 'flex'; // Mostrar mensaje de error
        console.log('User Vacio');
        correct = false;
    } else {
        document.getElementById('errorUsuario').style.display = 'none'; // Ocultar mensaje de error
    }

    // Validar el campo de contraseña
    if (pass.trim() === '') {
        document.getElementById('errorContrasena').style.display = 'flex'; // Mostrar mensaje de error
        console.log('Pass Vacia');
        correct = false;
    } else {
        document.getElementById('errorContrasena').style.display = 'none'; // Ocultar mensaje de error
    }

    return correct; // Retornar true si todo está correcto
}

let flag = true;
function pass() {
    if (flag) {
        document.getElementById("contrasena").type = "password";
        document.getElementById("btnVisibilidadP").class = "fa-regular fa-eye";
        flag = false;
    } else {
        document.getElementById("contrasena").type = "text";
        document.getElementById("btnVisibilidadP").class = "fa-regular fa-eye-slash";
        flag = true;
    }
}