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
    document.getElementById('pre-seleccion').style.display = 'none';
    document.getElementById('post-seleccion').style.display = 'block';
    document.getElementById('volver').style.display = 'inline';
    document.getElementById('mosaicoListasPorDefecto').style.display = 'grid';
    document.getElementById('mosaicoAlbumesPorGenero').style.display = 'grid';
    document.getElementById('mosaicoGeneros').style.display = 'none';

    // Cargar listas por defecto del genero seleccionado y de sus subgeneros 
    fetch('SVConsultaListaReproduccion?tipoDeLista=PorListaPorDefecto&nombre=' + encodeURIComponent(genero))
        .then(response => response.json())
        .then(data => {
            let mosaico = document.getElementById('mosaicoListasPorDefecto');
            mosaico.innerHTML = ''; // Clear previous content
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
        .catch(error => console.error('Error al cargar listas por defecto:', error));

    // Cargar álbumes del género seleccionado
    fetch('SVCargarAlbumes?tipo=Genero&nombre=' + encodeURIComponent(genero))
        .then(response => {
            if (!response.ok) {
                throw new Error('La respuesta de la red no fue satisfactoria');
            }
            return response.json(); 
        })
        .then(data => {
            const mosaicoContainer = document.getElementById('mosaicoAlbumesPorGenero');
            mosaicoContainer.innerHTML = ''; // Clear previous content
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
    document.getElementById('pre-seleccion2').style.display = 'none';
    document.getElementById('post-seleccion2').style.display = 'block';
    document.getElementById('volver2').style.display = 'inline';
    const mosaicoContainer = document.getElementById('mosaicoAlbumesPorArtista');
    const artistContainer = document.getElementById('mosaicoArtista');

    
    mosaicoContainer.style.display = 'grid';
    artistContainer.style.display = 'none';

    
    mosaicoContainer.innerHTML = '';

    
    fetch('SVCargarAlbumes?tipo=Artista&nombre=' + encodeURIComponent(artista))
        .then(response => {
            if (!response.ok) {
                throw new Error('Error de respuesta');
            }
            return response.json();
        })
        .then(data => {
            if (data.length > 0) {
                
                data.forEach(album => {
                    const albumDiv = document.createElement('div');
                    albumDiv.className = 'mosaico-item';
                    albumDiv.textContent = album.nombre;
                    albumDiv.onclick = () => DatosAlbum(album.id);
                    mosaicoContainer.appendChild(albumDiv);
                });
            } else {
                
                const noAlbumMsg = document.createElement('p');
                noAlbumMsg.textContent = 'Este artista no tiene álbumes publicados';
                mosaicoContainer.appendChild(noAlbumMsg);
            }
        })
        .catch(error => {
            console.error('Error al cargar álbumes:', error);
            const errorMsg = document.createElement('p');
            errorMsg.textContent = 'Error al cargar álbumes. Inténtelo de nuevo más tarde.';
            mosaicoContainer.appendChild(errorMsg);
        });
}
function VolverAGeneros() {
    // Muestra el div pre-seleccion y oculta el post-seleccion
    document.getElementById('pre-seleccion').style.display = 'block';
    document.getElementById('post-seleccion').style.display = 'none';
    document.getElementById('volver').style.display = 'none';
    document.getElementById('mosaicoGeneros').style.display = 'grid';
    
    limpiarMosaicos();
}
 function limpiarMosaicos() {
    // Limpia el contenido de los álbumes
    const mosaicoAlbumes = document.getElementById('mosaicoAlbumesPorGenero');
    mosaicoAlbumes.innerHTML = ''; // Vacía el contenido del mosaico

    // Limpia el contenido de las listas de reproducción
    const mosaicoListas = document.getElementById('mosaicoListasPorDefecto');
    mosaicoListas.innerHTML = ''; // Vacía el contenido del mosaico
    
    // Limpia el contenido de los álbumes
    const mosaicoAlbumesArt = document.getElementById('mosaicoAlbumesPorArtista');
    mosaicoAlbumes.innerHTML = ''; // Vacía el contenido del mosaico

    
}
function VolverAArtistas() {
    // Muestra el div pre-seleccion y oculta el post-seleccion
    document.getElementById('pre-seleccion2').style.display = 'block';
    document.getElementById('post-seleccion2').style.display = 'none';
    document.getElementById('volver2').style.display = 'none';
    document.getElementById('mosaicoArtista').style.display = 'grid';
    
    limpiarMosaicos();
}