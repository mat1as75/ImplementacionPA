const mapTemas = new Map();

/*
 * inicializa el <ol> con drag&drop para los temas
 */
Sortable.create(document.getElementById("olTemasCreados"), { 
                animation: 100, 
                group: 'list-1', 
                draggable: '.list-group-item', 
                handle: '.list-group-item', 
                sort: true, 
                filter: '.sortable-disabled', 
                chosenClass: 'active' }
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
    if (mapTemas.has(nombre)) {
        return false;
    }
}

function addLiTema() { 
    const nombre = $("#nombreTema").val();
    const duracion = $("#duracionTema").val();
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
    li.appendChild(span);
    li.appendChild(createRadioInputTema(nombre));
    //aca
    const tema = {
        nombreTema: nombre, 
        duracionTema: duracion, 
        tipoDeAccesoTema: tipoDeAcceso, 
        posicionTema: 0
    };
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
    const id = $("input[type='radio']:checked").val();
    const li = document.getElementById(`tema-${id}`);
    
    if (li != null) {
        li.remove();
    }
}


