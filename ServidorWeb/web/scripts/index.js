function mostrarMensajeCierreSesion(mensaje) {
    alert(mensaje);
}

function mostrarMensajeInicioSesion(mensaje) {
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





$(document).ready(function () {
    // Cargar géneros al cargar la página
    $.ajax({
        url: 'SVObtenerGeneros',
        method: 'GET',
        dataType: 'json',
        success: function (generos) {
            var generosContainer = $('.generos-list');
            generosContainer.empty(); // Limpiar cualquier contenido anterior

            generos.forEach(function (genero) {
                // Añadir cada género como un <li> y agregar un evento de clic
                generosContainer.append('<li class="genero-item" data-genero="' + genero + '">' + genero + '</li>');
            });

            // Agregar evento de clic a los elementos de género
            $('.genero-item').click(function () {
                var generoSeleccionado = $(this).data('genero');

                // Llamar al servlet para obtener álbumes de ese género
                $.ajax({
                    url: 'SVObtenerAlbumes',
                    method: 'GET',
                    data: { genero: generoSeleccionado },
                    dataType: 'json',
                    success: function (albumes) {
                        // Crear la pestaña de álbumes
                        crearPestanaAlbumes(generoSeleccionado, albumes);
                    },
                    error: function (xhr, status, error) {
                        console.error("Error al obtener álbumes:", error);
                    }
                });

                // Llamar al servlet para obtener listas de reproducción de ese género
                //$.ajax({
                  //  url: 'SVObtenerListasReproduccion',
                    //method: 'GET',
                    //data: { genero: generoSeleccionado },
                    //dataType: 'json',
                    //success: function (listas) {
                        // Crear la pestaña de listas de reproducción
                      //  crearPestanaListas(generoSeleccionado, listas);
                    //},
                    //error: function (xhr, status, error) {
                      //  console.error("Error al obtener listas de reproducción:", error);
                    //}
                });
            });
        },
        error: function (xhr, status, error) {
            console.error("Error al obtener géneros:", error);
        }
    });
});

function crearPestanaAlbumes(genero, albumes) {
    // Lógica para crear y mostrar la pestaña de álbumes
    console.log("Álbumes de " + genero + ":", albumes);
    // Aquí puedes agregar el código necesario para agregar la nueva pestaña al DOM
}

function crearPestanaListas(genero, listas) {
    // Lógica para crear y mostrar la pestaña de listas de reproducción
    console.log("Listas de reproducción de " + genero + ":", listas);
    // Aquí puedes agregar el código necesario para agregar la nueva pestaña al DOM
}


