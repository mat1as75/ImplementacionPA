function actualizarSeguidos() {
    var seguidor = document.getElementById("Seguidor").value;
    $.ajax({
        url: 'SVSeguirUsuario',
        type: 'GET',
        data: {seguidor: seguidor},
        success: function (response) {
            // Actualizar el combobox de "Seguido" con las nuevas opciones
            var seguidoSelect = document.getElementById("Seguido");
            seguidoSelect.innerHTML = ''; // Limpiar las opciones anteriores
            // Agregar las nuevas opciones obtenidas en la respuesta
            response.forEach(function (usuario) {
                var option = document.createElement("option");
                option.value = usuario;
                option.text = usuario;
                seguidoSelect.appendChild(option);
            });
        }
    });
}
       