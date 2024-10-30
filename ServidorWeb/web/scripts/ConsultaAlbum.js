document.addEventListener("DOMContentLoaded", function () {
    mostrarPorGenero();
});

document.addEventListener("DOMContentLoaded", evt => {
    document.getElementById("audioLinkPlayerContainer").classList.add("hidden");
});

function mostrarPorGenero() {
    document.getElementById('PorArtista').classList.remove('active');
    document.getElementById('PorGenero').classList.add('active');

    document.getElementById('mosaicoGeneros').style.display = 'grid';
    document.getElementById('mosaicoAlbumesG').style.display = 'none';//por genero?
    document.getElementById('mosaicoAlbumesA').style.display = 'none';

    // Cargar generos 
    fetch('SVConsultaAlbum?tipo=genero')
            .then(response => response.json())
            .then(data => {
                let mosaico = document.getElementById('mosaicoGeneros');
                mosaico.innerHTML = '';
                data.forEach(genero => {
                    let item = document.createElement('div');
                    item.className = 'mosaico-item';
                    item.textContent = genero.nombreGenero;
                    item.onclick = function () {
                        ConsultaAlbum(genero.nombreGenero);
                    };
                    mosaico.appendChild(item);
                });
            })
            .catch(error => console.error('Error:', error));
}

function ConsultaAlbum(nombreGenero) {
    document.getElementById('PorArtista').classList.remove('active');
    document.getElementById('PorGenero').classList.add('active');

    document.getElementById('mosaicoAlbumesG').style.display = 'grid';
    document.getElementById('mosaicoAlbumesA').style.display = 'none';
    document.getElementById('mosaicoGeneros').style.display = 'none';

    // Cargar albumes por genero 
    fetch('SVConsultaAlbum?tipo=PorGenero&nombre=' + encodeURIComponent(nombreGenero))
            .then(response => response.json())
            .then(data => {
                let mosaico = document.getElementById('mosaicoAlbumesG');
                mosaico.innerHTML = '';
                data.forEach(album => {
                    let item = document.createElement('div');
                    item.className = 'mosaico-item';
                    item.textContent = album;
                    item.onclick = function () {
                        ConsultaAlbum(album);
                    };
                    mosaico.appendChild(item);
                });
            })
            .catch(error => console.error('Error:', error));
}
function DatosAlbum(idAlbum) {
    window.location.href = 'ConsultaAlbum.jsp?albumId=' + encodeURIComponent(idAlbum);
}