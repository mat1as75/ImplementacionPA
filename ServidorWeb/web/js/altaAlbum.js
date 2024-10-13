const errorMsgs = {
    nombreAlbumVacio: "El nombre del album no puede estar vacío.",
    nombreAlbumRepetido: "Ya existe un album con el mismo nombre.",
    anioAlbumInvalido: "El año de creación no es válido.",
    nombreTemaInvalido: "El nombre del tema elegido no es válido.",
    nombreTemaRepetido: "Ya existe un tema con el mismo nombre.",
    duracionTemaInvalida: "La duración ingresada no tiene el formato correcto."
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

function addLiTema() { 
    const nombre = $("#nombreTema").val();
    if (!validarNombreTema(nombre)) return; 
    
    const duracion = $("#duracionTema").val();
    if (!validarDuracionTema(duracion)) return;
    
    const tipoDeAcceso = $("input[type=radio][name=tipoDeAcceso]:checked").val();
    let url, archivo;
    
    if (tipoDeAcceso === "url") {
        url = $("#tipoDeAcceso").val();
    } else {
        const ruta = $("#inputTema").val();
        archivo = ruta.substring(ruta.lastIndexOf("\\")+1);
    }
    
    const ol = document.getElementById("olTemasCreados");
    const li = document.createElement("li"); 
    const span = document.createElement("span");
    
    const content = `${nombre}  [${duracion}]  -  ${archivo || url}`;
    span.innerText = content;
    li.setAttribute("class", "list-group-item");
    li.setAttribute("id", `tema-${nombre}`);
    li.setAttribute("data-name", nombre);
    li.appendChild(span);
    li.appendChild(createRadioInputTema(nombre));
    
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

function createRadioInputTema(id) {
    const radioInput = document.createElement("input");
    radioInput.setAttribute("type", "radio");
    radioInput.setAttribute("id", `radio-${id}`);
    radioInput.setAttribute("name", "temas");
    radioInput.setAttribute("class", "radioTema");
    radioInput.setAttribute("value", id);
    
    return radioInput;
}

function removeLiTema(){
    const selectedRadio = $("input[type='radio'][name='temas']:checked");
    const li = selectedRadio.closest("li");
    const nombre = li.attr("data-name");
        
    if (li != null) {
        li.remove();
        mapTemasAgregados.delete(nombre);
    }
}


