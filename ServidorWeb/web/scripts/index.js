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

function CargaTabGenero(genero) {
    document.getElementById('mosaicoListasPorDefecto').style.display = 'grid';
    document.getElementById('mosaicoAlbumesPorGenero').style.display = 'grid';
    document.getElementById('mosaicoGeneros').style.display = 'none';

    // Cargar listas por defecto del genero seleccionado y de sus subgeneros 
    fetch('SVConsultaListaReproduccion?tipoDeLista=PorListaPorDefecto&nombre=' + encodeURIComponent(genero))
            .then(response => response.json())
            .then(data => {
                let mosaico = document.getElementById('mosaicoListasPorDefecto');
                mosaico.innerHTML = '';
                data.forEach(lista => {
                    let item = document.createElement('div');
                    item.className = 'mosaico-item';
                    item.textContent = lista;
                    item.onclick = function () {
                        DatosListaReproduccion(lista);
                    };
                    mosaico.appendChild(item);
                });
            })
            .catch(error => console.error('Error:', error));
    
    fetch('SVCargarAlbumes?tipo=Genero&nombre=' + encodeURIComponent(genero))
        .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json(); 
        })
        .then(data => {
            const mosaicoContainer = document.getElementById('mosaicoAlbumesPorGenero');
            data.forEach(album => {
                
                const albumDiv = document.createElement('div');
                albumDiv.className = 'mosaico-item';
                albumDiv.textContent = album.nombre;
                albumDiv.onclick = () => DatosAlbum(album.id); 
                mosaicoContainer.appendChild(albumDiv); 
            });
        })
        .catch(error => {
            console.error('Error al cargar álbumes:', error);
        });
    

}

function DatosAlbum(album) {
            window.location.href = 'ConsultaAlbum.jsp?albumId=' + encodeURIComponent(album);
        }
        
        
        
        
function CargaTabArtista(artista) {
    document.getElementById('mosaicoAlbumesPorArtista').style.display = 'grid';
    document.getElementById('mosaicoArtistas').style.display = 'none';
    
    fetch('SVCargarAlbumes?tipo=Artista&nombre=' + encodeURIComponent(artista))
        .then(response => {
        if (!response.ok) {
            throw new Error('Error de respuesta');
        }
        return response.json(); 
        })
        .then(data => {
            const mosaicoContainer = document.getElementById('mosaicoAlbumesPorArtista');
            data.forEach(album => {
                
                const albumDiv = document.createElement('div');
                albumDiv.className = 'mosaico-item';
                albumDiv.textContent = album.nombre;
                albumDiv.onclick = () => DatosAlbum(album.id); 
                mosaicoContainer.appendChild(albumDiv); 
            });
        })
        .catch(error => {
            console.error('Error al cargar álbumes:', error);
        });
    
}    
