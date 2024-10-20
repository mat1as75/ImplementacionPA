function mostrarResultados(resultados) {
    const resultadoDiv = document.getElementById('resultadoDiv');
    resultadoDiv.innerHTML = ''; // Limpiar resultados anteriores
    
    if (Object.keys(resultados).length === 0) {
        resultadoDiv.innerHTML = '<p>No se encontraron resultados.</p>';
    } else {
        for (const [key, value] of Object.entries(resultados)) {
            switch (key) {
            case "Usuario": // ConsultaPerfilUsuario
                resultadoDiv.innerHTML += `
                    <div>
                        <form action="SVConsultaPerfilUsuario" method="GET">
                            <input type="hidden" name="usuario-Consultar" value="${encodeURIComponent(value)}">
                            <button type="submit">${value} - ${key}</button> 
                        </form>
                    </div>
                `;
                break;
            case "Lista": // ConsultaListaReproduccion
                resultadoDiv.innerHTML += `
                    <div>
                        <p>
                            <a href="ConsultaListaReproduccion?userId=${value}">${value} - ${key}</a>
                        </p>
                    </div>
                `;
                break;
            case "Album": // ConsultaAlbum
                resultadoDiv.innerHTML += `
                    <div>
                        <p>
                            <a href="ConsultaAlbum?userId=${value}">${value} - ${key}</a>
                        </p>
                    </div>
                `;
                break;
            default: // Temas
                resultadoDiv.innerHTML += `
                    <div>
                        <p>${value} - ${key}</p>
                    </div>
                `;
                break;
            }
        }
    }
}

function ordenarResultados(resultados) {
    const select = document.getElementById('opciones');
    const ordenSeleccionado = select.value;
    
    // Convierto el Map a un array para ordenarlo
    const arrayResultados = Object.entries(resultados);
    
    if (ordenSeleccionado === 'alfabeticamente') {
        // Ordeno alfabeticamente por el value
        arrayResultados.sort((a, b) => a[1].localeCompare(b[1])); // a[1] y b[1] son los valores
    } else if (ordenSeleccionado) {
        // Ordeno por el valor (anio)
        arrayResultados.sort((a, b) => parseInt(b[1]) - parseInt(a[1])); // Orden descendente
    }
    
    // Volver a mostrar los resultados ordenados
    const ordenados = Object.fromEntries(arrayResultados);
    mostrarResultados(ordenados);
}