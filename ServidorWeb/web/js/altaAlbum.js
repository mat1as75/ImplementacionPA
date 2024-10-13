const errorMsgs = {
    nombreAlbumVacio: "El nombre del album no puede estar vacío.",
    nombreAlbumRepetido: "Ya existe un album con el mismo nombre.",
    anioAlbumInvalido: "El año de creación no es válido.",
    nombreTemaInvalido: "El nombre del tema elegido no es válido.",
    nombreTemaRepetido: "Ya existe un tema con el mismo nombre.",
    duracionTemaInvalida: "La duración ingresada no tiene el formato correcto.",
    urlTemaInvalido: "La URL ingresada no es valida."
}
const mapTemasAgregados = new Map();

/*
 * inicializa el <ol> con drag&drop para los temas
 */
const sortableList = Sortable.create(document.getElementById("olTemasCreados"), { 
                animation: 100, 
                group: 'list-1', 
                draggable: '.list-group-item', 
                handle: '.list-group-item', 
                sort: true, 
                filter: '.sortable-disabled', 
                chosenClass: 'active'
            }
); 

document.querySelector("body").addEventListener("click", evt => {
   if (evt.target && evt.target.getAttribute("data-name") === "iconRemoverTema") {
       li = evt.target.closest("li");
       removeLiTema(li);
   } 
});

$('input[type=radio][name=tipoDeAcceso]').on('change', function() {
  switch ($(this).val()) {
        case 'ruta':
            $("#tipoDeAcceso").attr("disabled", true);
            $("#inputFileTemaContainer").removeClass("d-none");
            break;
        case 'url':
            $("#tipoDeAcceso").attr("disabled", false);
            $("#inputFileTemaContainer").addClass("d-none");
            break;
  }
});

function validarNombreTema(nombre) {
    const errorSpan = $("#errorNombreTema");
    const spanContainer = errorSpan.closest("div");

    if (mapTemasAgregados.has(nombre)) {        
        errorSpan.text(errorMsgs.nombreTemaRepetido);
        spanContainer.removeClass("d-none");
        
        return false;
    }
    errorSpan.text("");
    spanContainer.addClass("d-none");
    
    return true;
}

function validarDuracionTema(duracion) {
    const regex =  /(?<!\S)(([0-5]?\d):)([0-5]?\d)(?!\S)/;
    const errorSpan = $("#errorDuracionTema");
    const spanContainer = errorSpan.closest("div");
    
    if (!regex.test(duracion)) {
        errorSpan.text(errorMsgs.duracionTemaInvalida);
        spanContainer.removeClass("d-none");
        
        return false;
    }
    errorSpan.text("");
    spanContainer.addClass("d-none");
    
    return true;
}

function validarUrl(url) {
    const errorSpan = $("#errorUrlTema");
    const spanContainer = errorSpan.closest("div");
    
    try {
        new URL(url);
        errorSpan.text("");
        spanContainer.addClass("d-none");
        return true;
    } catch (err) {
        errorSpan.text(errorMsgs.urlTemaInvalido);
        spanContainer.removeClass("d-none");
        return false;
    }
}

function addLiTema() { 
    const nombre = $("#nombreTema").val();
    if (!validarNombreTema(nombre)) return; 
    
    const duracion = $("#duracionTema").val();
    if (!validarDuracionTema(duracion)) return;
    
    const tipoDeAcceso = $("input[type=radio][name=tipoDeAcceso]:checked").val();
    let url, archivo;
    
    if (tipoDeAcceso === "url") {
        url = $("#tipoDeAcceso").val();
        if (!validarUrl(url)) return;
    } else {
        const ruta = $("#inputTema").val();
        archivo = ruta.substring(ruta.lastIndexOf("\\")+1);
    }
    
    const ol = document.getElementById("olTemasCreados");
    const li = document.createElement("li"); 
    const trashIcon = document.createElement("i");
    
    trashIcon.setAttribute("data-name", "iconRemoverTema");
    trashIcon.setAttribute("class", "bi bi-trash3");    
    
    const content = `${nombre}  [${duracion}]  -  ${archivo || url}`;
    li.innerText = content;
    li.setAttribute("class", "list-group-item");
    li.setAttribute("id", `tema-${nombre}`);
    li.setAttribute("data-name", nombre);
    li.appendChild(trashIcon);
    
    //los temas siempre se agregan al final de la lista, 
    //entonces la posicon del ultimo tema agregado es la cantidad de elementos
    const cantTemasAgregados = $("#olTemasCreados").children().length + 1;
    
    const tema = {
        nombreTema: nombre, 
        duracionTema: duracion, 
        tipoDeAccesoTema: tipoDeAcceso
    };
    mapTemasAgregados.set(nombre, tema);
    ol.appendChild(li);
}

function removeLiTema(liElement){
    const nombre = liElement.getAttribute("data-name");
        
    if (liElement != null) {
        liElement.remove();
        mapTemasAgregados.delete(nombre);
    }
}


