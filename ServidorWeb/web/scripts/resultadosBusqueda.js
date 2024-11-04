function mostrarResultados(resultados) {
    const resultadoDiv = document.getElementById('resultadoDiv');
    resultadoDiv.innerHTML = ''; // Limpiar resultados anteriores
    
    if (Object.keys(resultados).length === 0) {
        resultadoDiv.innerHTML = '<p>No se encontraron resultados.</p>';
    } else {
        for (const [key, value] of Object.entries(resultados)) {
            switch (value) {
            case "Cliente": // ConsultaPerfilUsuario
            case "Artista":
                resultadoDiv.innerHTML += `
                        <form action="SVConsultaPerfilUsuario" method="GET">
                            <input type="hidden" name="usuario-Consultar" value="${encodeURIComponent(key)}">
                            <button type="submit">${key} - ${value}</button> 
                        </form>
                `;
                break;
            case "Lista": // ConsultaListaReproduccion
                resultadoDiv.innerHTML += `
                        
                        <a href="DatosListaReproduccion.jsp?nombreLista=${key}">${key} - ${value}</a>
                `;
                console.log();
                break;
            case "Album": // ConsultaAlbum
                
                const album = `${key}`;
                const[idAlbum, nombreAlbum] = album.split(', ');
       
                resultadoDiv.innerHTML += `
                        <input type="hidden" name="album-Consultar" value="${encodeURIComponent(value)}">
                        <button type="submit"><a href="ConsultaAlbum.jsp?albumId=${idAlbum}">${nombreAlbum} - ${value}</button>
                `;
                break;
            default: // ConsultaTema
                
                const tema = `${key}`;
                const[idAlbum_Tema, nombreTema] = tema.split(', ');
                
                resultadoDiv.innerHTML += `            
                        <input type="hidden" name="tema-Consultar" value="${encodeURIComponent(key)}">
                        <button type="submit"><a href="ConsultaAlbum.jsp?albumId=${idAlbum_Tema}">${nombreTema} - ${value}</button>
                `;
                break;
            }
        }
    }
}

//const orderByAlphabetical = (array, getter, order = 'asc') => {
//    array.sort((a, b) => {
//        const first = getter(a);
//        const second = getter(b);
//        
//        const compare = first.localeCompare(second);
//        return order === 'asc' ? compare : -compare;
//    });
//    return array;
//};

//document.getElementById('ordenar').addEventListener('change', function() {
//    const selectedOption = this.value;
//    let sortedResults;
//    
//    if (selectedOption === 'alfabetico') {
//        sortedResults = resultados.sort((a, b) => a.localeCompare(b));
//    } else {
//        sortedResults = resultados.sort((a, b) => a.anio - b.anio);
//    }
//    
//    mostrarResultados(sortedResults);
//});

document.addEventListener('DOMContentLoaded', function() {
    const sortOption = document.getElementById('ordenar');
    
    console.log('Resultados iniciales: ', resultados);
    
    sortOption.addEventListener('change', function() {
        const selectedOption = this.value;
        
        // Crear un nuevo objeto para guardar los resultados ordenados
        const sortedResults = {};
        
        for (const value in resultados) {
            if (resultados.hasOwnProperty(value)) {
                // Convertir el string a un arreglo
                let arreglo = resultados[value];
                
                if (selectedOption === 'alfabetico') {
                    // Ordenar alfabeticamente
                    arreglo.sort((a, b) => a.localeCompare(b));
                } else if (selectedOption === 'anio') {
                    // Asumiento que los strings pueden representarse como anios (ej. '2020', '2021')
                    arreglo.sort((a, b) => parseInt(a) - parseInt(b));
                }
                
                // Asignar el arreglo ordenado al nuevo objeto
                sortedResults[value] = arreglo.join();
            }
        }
        
        // Asegurarse de que esta funcion maneja la estructura de sortedResults
        mostrarResultados(sortedResults);
    });
});

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