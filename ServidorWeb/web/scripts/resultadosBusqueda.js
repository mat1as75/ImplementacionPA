function mostrarResultados(resultados) {
    const resultadoDiv = document.getElementById('resultadoDiv');
    resultadoDiv.innerHTML = ''; // Limpiar resultados anteriores
    
    const jsDiv = document.getElementById('js');
    
    jsDiv.innerHTML = `
            <% 
                Fabrica fb = Fabrica.getInstance();
                IControlador control = fb.getControlador();
    
                // Obtengo todos los Temas del Sistema
                Map<Long, String> temasDisponibles = control.getTemasDisponibles();
            %>
    `;
    
    if (Object.keys(resultados).length === 0) {
        resultadoDiv.innerHTML = '<p>No se encontraron resultados.</p>';
    } else {
        for (const [key, value] of Object.entries(resultados)) {
            switch (key) {
            case "Cliente": // ConsultaPerfilUsuario
            case "Artista":
                resultadoDiv.innerHTML += `
                        <form action="SVConsultaPerfilUsuario" method="GET">
                            <input type="hidden" name="usuario-Consultar" value="${encodeURIComponent(value)}">
                            <button type="submit">${value} - ${key}</button> 
                        </form>
                `;
                break;
            case "Lista": // ConsultaListaReproduccion
                resultadoDiv.innerHTML += `
                        <form action="SVConsultaListaReproduccion" method="GET">
                            <input type="hidden" name="listaR-Consultar" value="${encodeURIComponent(value)}">
                            <button type="submit"><a href="DatosListaReproduccion.jsp?nombreLista= ${value}"> ${value} - ${key}</button>
                        </form>
                `;
                break;
            case "Album": // ConsultaAlbum
                resultadoDiv.innerHTML += `
                        <form action="SVConsultaAlbum" method="GET">
                            <input type="hidden" name="album-Consultar" value="${encodeURIComponent(value)}">
                            <button type="submit">${value} - ${key}</button>
                        </form>
                `;
                break;
            default: // Temas
                resultadoDiv.innerHTML += `      
                        // Obtengo el Tema con el idTema actual
                        String nombreTema = temasDisponibles().get(${value});
                                
                        <form action="SVConsultaTema" method="GET">
                            <input type="hidden" name="tema-Consultar" value="${encodeURIComponent(value)}">
                            <button type="submit"><%= nombreTema %> - ${key}</button>
                        </form>
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