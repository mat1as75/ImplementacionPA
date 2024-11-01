function mostrarPopupSesion(mensaje) {
    alert(mensaje);
}

jQuery(document).ready(function () {
    jQuery('.tabs .tab-links a').on('click', function (e) {
        var currentAttrValue = jQuery(this).attr('href');

        // Show/Hide Tabs
        jQuery('.tabs ' + currentAttrValue).show().siblings().hide();

        // Change/remove current tab to active
        jQuery(this).parent('li').addClass('active').siblings().removeClass('active');

        e.preventDefault();
    });
});

function mostrarPorListaParticular() {
            document.getElementById('mosaicoListasParticulares').style.display = 'grid';
        }

        function DatosListaReproduccion(nombreLista) {
            window.location.href = 'DatosListaReproduccion.jsp?nombreLista=' + encodeURIComponent(nombreLista);
        }

        // Mostrar listas particulares al cargar la página
        document.addEventListener("DOMContentLoaded", function () {
            mostrarPorListaParticular();
});