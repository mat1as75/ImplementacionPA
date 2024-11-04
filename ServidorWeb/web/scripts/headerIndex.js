document.addEventListener('DOMContentLoaded', function () {
    const form = document.getElementById('form-searchBar');
    const select = document.getElementById('filtro');

    form.addEventListener('submit', function (event) {
        if (select.value === "") {
            event.preventDefault(); // Evita el envío del formulario
            alert('Por favor, selecciona una opción válida.');
        }
    });
});

