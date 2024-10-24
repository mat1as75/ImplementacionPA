document.addEventListener("DOMContentLoaded", function () {
    mostrarPorGenero();
});

function mostrarPorGenero() {
    document.getElementById('PorLista').classList.remove('active');
    document.getElementById('PorGenero').classList.add('active');
    
    document.getElementById('mosaicoGeneros').style.display = 'grid';
    document.getElementById('mosaicoListas').style.display = 'none';
    document.getElementById('mosaicoListasPorDefecto').style.display = 'none';
    
    // Cargar generos 
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
                    selectGenero(genero.nombreGenero); 
                };
                mosaico.appendChild(item);
            });

            // Mostrar mosaico de genero despues de cargarlos
            document.getElementById('mosaicoGeneros').style.display = 'grid';
        })
        .catch(error => console.error('Error:', error));
}

function mostrarPorLista() {
    document.getElementById('PorGenero').classList.remove('active');
    document.getElementById('PorLista').classList.add('active');

    document.getElementById('mosaicoListas').style.display = 'grid';
    document.getElementById('mosaicoGeneros').style.display = 'none';
    document.getElementById('mosaicoListasPorDefecto').innerHTML = '';
    
    // Cargar listas particulares publicas
    fetch('SVConsultaListaReproduccion?tipoDeLista=PorListaParticular')
        .then(response => response.json())
        .then(data => {
            let mosaico = document.getElementById('mosaicoListas');
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

function selectGenero(nombreGenero) {
    document.getElementById('PorLista').classList.remove('active');
    document.getElementById('PorGenero').classList.add('active');
    console.log("Género seleccionado:", nombreGenero);
    
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

            document.getElementById('mosaicoGeneros').style.display = 'none';
            document.getElementById('mosaicoListasPorDefecto').style.display = 'grid';
        })
        .catch(error => console.error('Error:', error));
}

function DatosListaReproduccion(nombreLista) {
    window.location.href = 'DatosListaReproduccion.jsp?nombreLista=' + encodeURIComponent(nombreLista);
}