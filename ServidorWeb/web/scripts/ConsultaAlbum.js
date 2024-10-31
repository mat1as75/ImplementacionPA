$(document).on('click', '.album-item', function() {
    const nombreAlbum = $(this).data('nombre');
    
    $.ajax({
        url: 'SVConsultarAlbum',
        method: 'GET',
        data: { nombreAlbum: nombreAlbum },
        success: function(response) {
            $('.lista-detalles').html(response);
        },
        error: function() {
            alert("Error al cargar la información del álbum.");
        }
    });
});