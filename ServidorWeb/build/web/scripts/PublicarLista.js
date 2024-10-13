
var nicknameSeleccionado = null;
var listaPrivadaSeleccionada = null;

// Función para manejar la selección de nickname
function seleccionarNickname(elemento, nickname) {
    // Quitar la clase 'selected' de todos los elementos en la lista de nicknames
    var elementosNickname = document.querySelectorAll('.lista-contenedor:nth-child(1) .selectable');
    elementosNickname.forEach(function (el) {
        el.classList.remove('selected');
    });

    // Añadir la clase 'selected' al elemento actual
    elemento.classList.add('selected');

    // Guardar el nickname seleccionado
    nicknameSeleccionado = nickname;

    // Actualizar el campo oculto del formulario
    document.getElementById('inputNickname').value = nickname;

    // Enviar la solicitud AJAX para obtener las listas privadas relacionadas con el nickname seleccionado
    enviarSeleccion(nickname);
}

// Función para manejar la selección de una lista privada
function seleccionarListaPrivada(elemento, listaPrivada) {
    // Quitar la clase 'selected' de todos los elementos en la lista de listas privadas
    var elementosListasPrivadas = document.querySelectorAll('.lista-contenedor:nth-child(2) .selectable');
    elementosListasPrivadas.forEach(function (el) {
        el.classList.remove('selected');
    });

    // Añadir la clase 'selected' al elemento actual
    elemento.classList.add('selected');

    // Guardar la lista privada seleccionada
    listaPrivadaSeleccionada = listaPrivada;

    // Actualizar el campo oculto del formulario
    document.getElementById('inputListaPrivada').value = listaPrivada;
}

// Función para enviar la solicitud AJAX y actualizar las listas privadas
function enviarSeleccion(nickname) {
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "SVPublicarLista?nickname=" + nickname, true);

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            // Actualizar la lista de listas privadas con la respuesta recibida del servidor
            document.getElementById("listasPrivadas").innerHTML = xhr.responseText;

            // Agregar el evento onclick a los nuevos elementos de la lista privada
            var elementosListasPrivadas = document.querySelectorAll('#listasPrivadas .selectable');
            elementosListasPrivadas.forEach(function (el) {
                el.onclick = function () {
                    seleccionarListaPrivada(el, el.textContent.trim());
                };
            });
        }
    };

    xhr.send();
}
   