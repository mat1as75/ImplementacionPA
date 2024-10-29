document.addEventListener("DOMContentLoaded", function () {
    mostrarPorGenero();
});

document.addEventListener("DOMContentLoaded", evt => {
    document.getElementById("audioLinkPlayerContainer").classList.add("hidden");
});
    
function mostrarPorGenero() {
    document.getElementById('PorLista').classList.remove('active');
    document.getElementById('PorGenero').classList.add('active');

    document.getElementById('mosaicoGeneros').style.display = 'grid';
    document.getElementById('mosaicoListasPorDefecto').style.display = 'none';
    document.getElementById('mosaicoListasParticulares').style.display = 'none';

    // Cargar generos principales 
    fetch('SVConsultaListaReproduccion?tipoDeLista=PorGenero')
            .then(response => response.json())
            .then(data => {
                let mosaico = document.getElementById('mosaicoGeneros');
                mosaico.innerHTML = '';
                data.forEach(genero => {
                    let item = document.createElement('div');
                    item.className = 'mosaico-item';
                    item.textContent = genero.nombreGenero;
                    item.onclick = function () {
                        mostrarPorListaPorDefecto(genero.nombreGenero);
                    };
                    mosaico.appendChild(item);
                });
            })
            .catch(error => console.error('Error:', error));
}

function mostrarPorListaParticular() {
    document.getElementById('PorGenero').classList.remove('active');
    document.getElementById('PorLista').classList.add('active');

    document.getElementById('mosaicoListasParticulares').style.display = 'grid';
    document.getElementById('mosaicoListasPorDefecto').style.display = 'none';
    document.getElementById('mosaicoGeneros').style.display = 'none';

    // Cargar listas particulares publicas
    fetch('SVConsultaListaReproduccion?tipoDeLista=PorListaParticular')
            .then(response => response.json())
            .then(data => {
                let mosaico = document.getElementById('mosaicoListasParticulares');
                mosaico.innerHTML = '';
                data.forEach(nombreLista => {
                    let item = document.createElement('div');
                    item.className = 'mosaico-item';
                    item.textContent = nombreLista;
                    item.onclick = function () {
                        DatosListaReproduccion(nombreLista);
                    };
                    mosaico.appendChild(item);
                });
            })
            .catch(error => console.error('Error:', error));
}

function mostrarPorListaPorDefecto(nombreGenero) {
    document.getElementById('PorLista').classList.remove('active');
    document.getElementById('PorGenero').classList.add('active');

    document.getElementById('mosaicoListasPorDefecto').style.display = 'grid';
    document.getElementById('mosaicoListasParticulares').style.display = 'none';
    document.getElementById('mosaicoGeneros').style.display = 'none';

    // Cargar listas por defecto del genero seleccionado y de sus subgeneros 
    fetch('SVConsultaListaReproduccion?tipoDeLista=PorListaPorDefecto&nombre=' + encodeURIComponent(nombreGenero))
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
}

function DatosListaReproduccion(nombreLista) {
    window.location.href = 'DatosListaReproduccion.jsp?nombreLista=' + encodeURIComponent(nombreLista);
}