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

function mostrarPorGenero() {
    document.getElementById('PorArtista').classList.remove('active');
    document.getElementById('PorGenero').classList.add('active');

    document.getElementById('mosaicoGeneros').style.display = 'grid';

    // Cargar generos 
    // Cargar géneros dinámicamente desde el nuevo servlet
    fetch('SVOtenerGeneros')
        .then(response => response.json())
        .then(data => {
            let mosaico = document.getElementById('mosaicoGeneros');
            mosaico.innerHTML = ''; // Limpiar contenido previo

            data.forEach(genero => {
                let item = document.createElement('div');
                item.className = 'mosaico-item';
                item.textContent = genero; // `genero` ya es un string
                item.onclick = function () {
                    (genero);
                };
                mosaico.appendChild(item);
            });
        })
        .catch(error => console.error('Error al cargar géneros:', error));
}

function DatosAlbum(nombreLista) {
    window.location.href = 'ConsultaAlbum.jsp?AlbumId=' + encodeURIComponent(albumId);
}