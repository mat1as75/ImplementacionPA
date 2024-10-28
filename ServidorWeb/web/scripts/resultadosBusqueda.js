function mostrarResultados(resultados) {
    const resultadoDiv = document.getElementById('resultadoDiv');
    resultadoDiv.innerHTML = ''; // Limpiar resultados anteriores
    
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
                        
                        <a href="DatosListaReproduccion.jsp?nombreLista=${value}">${value} - ${key}</a>
                `;
                console.log();
                break;
            case "Album": // ConsultaAlbum
                
                const album = `${value}`;
                const[idAlbum, nombreAlbum] = album.split(', ');
       
                resultadoDiv.innerHTML += `
                        <form action="SVConsultaAlbum" method="GET">
                            <input type="hidden" name="album-Consultar" value="${encodeURIComponent(value)}">
                            <button type="submit"><a href="ConsultaAlbum.jsp?albumId=${idAlbum}">${nombreAlbum} - ${key}</button>
                        </form>
                `;
                break;
            default: // ConsultaTema
                
                const tema = `${value}`;
                const[idTema, nombreTema] = tema.split(', ');
                
                resultadoDiv.innerHTML += `            
                        <form action="SVConsultaTema" method="GET">
                            <input type="hidden" name="tema-Consultar" value="${encodeURIComponent(value)}">
                            <button type="submit"><a href="ConsultaTema.jsp?temaId=${idTema}">${nombreTema} - ${key}</button>
                        </form>
                `;
                break;
            }
        }
    }
}

const orderByAlphabetical = (array, getter, order = 'asc') => {
    array.sort((a, b) => {
        const first = getter(a);
        const second = getter(b);
        
        const compare = first.localeCompare(second);
        return order === 'asc' ? compare : -compare;
    });
    return array;
};

//function ordenarResultados(resultados) {
//    const select = document.getElementById('opciones');
//    const ordenSeleccionado = select.value;
//    
//    // Convierto el Map a un array para ordenarlo
//    const arrayResultados = Object.entries(resultados);
//    
//    if (ordenSeleccionado === 'alfabeticamente') {
//        // Ordeno alfabeticamente por el value
//        arrayResultados.sort((a, b) => a[1].localeCompare(b[1])); // a[1] y b[1] son los valores
//    } else if (ordenSeleccionado) {
//        // Ordeno por el valor (anio)
//        arrayResultados.sort((a, b) => parseInt(b[1]) - parseInt(a[1])); // Orden descendente
//    }
//    
//    // Volver a mostrar los resultados ordenados
//    const ordenados = Object.fromEntries(arrayResultados);
//    mostrarResultados(ordenados);
//}