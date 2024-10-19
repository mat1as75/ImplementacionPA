/*  
 * -----------------------------------------------------------
 *  CONST
 *  -----------------------------------------------------------
 */

//mensajes de error para mostrarle al usuario
const errorMsgs = {
    nombreAlbumVacio: "El nombre del album no puede estar vacío.",
    nombreAlbumRepetido: "Ya existe un album con el mismo nombre.",
    anioAlbumInvalido: "El año de creación no es válido.",
    nombreTemaInvalido: "El nombre del tema elegido no es válido.",
    nombreTemaRepetido: "Ya existe un tema con el mismo nombre.",
    duracionTemaInvalida: "La duración ingresada no tiene el formato correcto.",
    urlTemaInvalido: "La URL ingresada no es valida.",
    nombreArchivoRepetido: "Ya se seleccionó un archivo con el mismo nombre.",
    temaSinArchivo: "Todos los temas que requieran subir un archivo deben tener uno seleccionado.",
    sinGeneros: "Debe seleccionar al menos un género.",
    sinTemas: "Debe agregar al menos un tema al álbum."
};
//datos de los temas agregados
const mapTemasAgregados = new Map();

//inicializa el <ol> con drag&drop para los temas 
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

//datos del album, generos seleccionados y temas agregados en forma de objeto
let GLOBAL_data;

/* 
 * -----------------------------------------------------------
 * EVENT LISTENERS
 * -----------------------------------------------------------
 */

//Remueve el tema agregado al hacer click sobre el icono
//Uso Event Delegation para agregar el evento a los elementos creados dinamicamente
document.querySelector("body").addEventListener("click", evt => {
   if (evt.target && evt.target.getAttribute("data-name") === "iconRemoverTema") {
       li = evt.target.closest("li");
       removeLiTema(li);
   } 
});

//Agrego el nombre del archivo subido al tema
$("#olTemasCreados").on("change", "input[type='file'][data-type='file-tema']", evt => {
    const selectedFile = evt.target.value;
    const fileName = selectedFile.substring(selectedFile.lastIndexOf("\\")+1);
    const nombreTema = evt.target.getAttribute("data-name");
    const tema = mapTemasAgregados.get(nombreTema);
    
    tema.urlORuta = fileName;
    mapTemasAgregados.set(nombreTema, tema);
});

//Cuando se selecciona el radio input de url se oculta el input de archivos y viceversa
//Cuando se selecciona el radio input de ruta el input de url se deshabilita
$('input[type=radio][name=tipoDeAcceso]').on('change', function() {
    switch ($(this).val()) {
        case 'ruta':
            $("#tipoDeAcceso").attr("disabled", true);
            break;
        case 'url':
            $("#tipoDeAcceso").attr("disabled", false);
            break;
  }
});

//cierro el modal al clickear la X
document.getElementById("spanCloseModal").addEventListener("click", (evt) => {
    $("#modalDataVerification").addClass("d-none"); 
});

//redirecciono a la pagina principal luego de enviarse la solicitud de alta al cerrar el modal de confirmacion
document.getElementById("btnCloseModalResultado").addEventListener("click", (evt) => {
    $("#modalResultado").addClass("d-none");
    window.location.href = "/ServidorWeb";
});

//Valido los datos del form antes de habilitar el boton de submit
document.getElementById("btnValidar").addEventListener("click", validateForm);

//submit del form
document.getElementById("formAlbum").addEventListener("submit", handleSubmit);


/* 
 * -----------------------------------------------------------
 * FUNCTIONS
 * -----------------------------------------------------------
 */

async function handleSubmit(evt) {
    evt.preventDefault();
    if (!GLOBAL_data) return;
    
    const data = GLOBAL_data;
    const form = document.getElementById("formAlbum");    
    const formObject = new FormData(form);
                
    formObject.append("nombreAlbum", data.Album);
    formObject.append("anioAlbum", data.Anio);
    formObject.append("nombrePortada", data.Portada);
    
    for (let i=0; i<data.Generos.length; i++) {
        formObject.append("generos", data.Generos[i]);
    }
    
    formObject.append("cantidadTemas", data.Temas.length);
    
    for (let i=0; i<data.Temas.length; i++) {
        formObject.append(`tema-${i}-nombre`, data.Temas[i][1].nombreTema);
        formObject.append(`tema-${i}-duracion`, data.Temas[i][1].duracionTema);
        formObject.append(`tema-${i}-tipoDeAcceso`, data.Temas[i][1].tipoDeAccesoTema);
        formObject.append(`tema-${i}-urlOruta`, data.Temas[i][1].urlORuta);
        formObject.append(`tema-${i}-posicion`, data.Temas[i][1].posicion);
    }
        
    const request = new Request("/ServidorWeb/AltaAlbum", {
        method: "POST",
        body: formObject
    });
 
    let result;
    try {
        const response = await fetch(request);
        result = await response.text();
    } catch (e) {
        console.error("Error: " , e);
    }
    
    const modal = $("#modalResultado");
    const modalText = $("#modalText");
    modalText.text(result);
    modal.removeClass("d-none");
}

//Validar datos del form antes de habilitar el submit
function validateForm() {
    const arrGeneros = getGenerosSeleccionados();
    const nombreAlbum = $("#nombreAlbum").val();
    const anioAlbum = $("#anioAlbum").val();
    const rutaPortada = $("#inputPortadaAlbum").val();
    const nombrePortada = rutaPortada.substring(rutaPortada.lastIndexOf("\\")+1);
    
    if (!validarDatosAlbum(nombreAlbum, anioAlbum)) return;
    if (!validarSeleccionGeneros(arrGeneros)) return;
    if (!validarCreacionDeTemas(mapTemasAgregados)) return;
    if (!validarArchivosDeTemaVacio()) return;
    
    actualizarValorPosicionDeTemas();
    
    const formData = {
        Album: nombreAlbum,
        Anio: anioAlbum,
        Portada: nombrePortada || "",
        Generos: [...arrGeneros],
        Temas: [...mapTemasAgregados]
    };
    
    $("#modalDataVerification").removeClass("d-none");
    $("#btnSubmit").removeAttr("disabled");
    GLOBAL_data = formData;
}

function resetInputs() {
    const inputs = document.querySelectorAll("input");
    const typesToReset = ["text", "file", "number"];
    
    for (let i = 0; i< inputs.length; i++) {
        const type = inputs[i].getAttribute("type");
        
        if (typesToReset.includes(type)) {
            inputs[i].value = "";
        }
        
        if (type === "checkbox") {
            inputs[i].checked = false;
        }
    }
    document.querySelector("#radioUrl").checked = true;
    document.querySelector("#btnSubmit").disabled = true;
}

function validarDatosAlbum(nombre, anio) {
    const errorSpanNombre = $("#errorNombreAlbum");
    const errorSpanAnio = $("#errorAnioAlbum");
    const spanNombreContainer = errorSpanNombre.closest("div");
    const spanAnioContainer = errorSpanAnio.closest("div");
    
    if (!nombre) {
        errorSpanNombre.text(errorMsgs.nombreAlbumVacio);
        spanNombreContainer.removeClass("d-none");
        return false;
    }
    errorSpanNombre.text("");
    spanNombreContainer.addClass("d-none");
    
    if (!anio) {
        errorSpanAnio.text(errorMsgs.anioAlbumInvalido);
        spanAnioContainer.removeClass("d-none");
        return false;
    }
    errorSpanAnio.text("");
    spanAnioContainer.addClass("d-none")
    
    return true;
}

//Verifica que todos los temas de tipo ruta tengan seleccionado un archivo
function validarArchivosDeTemaVacio() {
    const errorSpan = $("#errorTemasVacios");
    const spanContainer = errorSpan.closest("div");
    const fileInputs =  document.querySelectorAll("input[data-type='file-tema'");
    
    for (let i = 0; i < fileInputs.length; i++) {
        if (!fileInputs[i].value) {
            errorSpan.text(errorMsgs.temaSinArchivo);
            spanContainer.removeClass("d-none");
            return false;
        }
    }
    errorSpan.text("");
    spanContainer.addClass("d-none");
    return true;
}

//Valida que se haya creado al menos un tema
function validarCreacionDeTemas(mapTemas) {
    const errorSpan = $("#errorTemasVacios");
    const spanContainer = errorSpan.closest("div");
    
    if (mapTemas.size < 1) {
        errorSpan.text(errorMsgs.sinTemas);
        spanContainer.removeClass("d-none");
        return false;
    }
    errorSpan.text("");
    spanContainer.addClass("d-none");
    return true;
}

//Valida que se haya seleccionado al menos 1 genero
function validarSeleccionGeneros(generos) {
    const errorSpan = $("#errorGeneros");
    const spanContainer = errorSpan.closest("div");
    
    if (generos.length < 1) {
        errorSpan.text(errorMsgs.sinGeneros);
        spanContainer.removeClass("d-none");
        return false;
    }
    errorSpan.text("");
    spanContainer.addClass("d-none");
    return true;
}

//Verifica que el nombre de tema ingresado no se repita
function validarNombreTema(nombre) {
    const errorSpan = $("#errorNombreTema");
    const spanContainer = errorSpan.closest("div");

    if (nombre.trim() === "") {
        errorSpan.text(errorMsgs.nombreTemaInvalido);
        spanContainer.removeClass("d-none");
        return false;
    }

    if (mapTemasAgregados.has(nombre)) {        
        errorSpan.text(errorMsgs.nombreTemaRepetido);
        spanContainer.removeClass("d-none");
        return false;
    }
    errorSpan.text("");
    spanContainer.addClass("d-none");
    
    return true;
}

//Verifica que la duracion del tema tenga el formato correcto mm:ss o m:s
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

//Verifica que la url ingresada para el tema sea valida, 
//Intenta crear un url con el constructor new URL, capturando el error si no es valida
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

//Crea un label e input tipo file para subir el archivo de musica del tema agregado
function createFileInput(nombreTema) {
    const label = document.createElement("label");
    const input = document.createElement("input");
    
    input.setAttribute("name", `file-${nombreTema}`);
    input.setAttribute("data-name", nombreTema);
    input.setAttribute("data-type", "file-tema");
    input.setAttribute("type", "file");
    input.setAttribute("accept", ".mp3, .wav");
    
    label.setAttribute("for", `file-${nombreTema}`);
    label.innerText = "Seleccione el archivo: ";
        
    return {label, input};
}

//Crea un nuevo <li> con la informacion del tema 
function addLiTema() { 
    const nombre = $("#nombreTema").val();
    if (!validarNombreTema(nombre)) return; 
    
    const duracion = $("#duracionTema").val();
    if (!validarDuracionTema(duracion)) return;
    
    const tipoDeAcceso = $("input[type=radio][name=tipoDeAcceso]:checked").val();
    let url;
    let fileInput;
    
    if (tipoDeAcceso === "url") {
        url = $("#tipoDeAcceso").val();
        if (!validarUrl(url)) return;
    } else { //si se selecciono la opcion de subir archivo creo un input type file para el tema
        fileInput = createFileInput(nombre);
    }
    //creo los elementos a agregar al DOM
    const ol = document.getElementById("olTemasCreados");
    const li = document.createElement("li"); 
    const trashIcon = document.createElement("i");
    trashIcon.setAttribute("data-name", "iconRemoverTema");
    trashIcon.setAttribute("class", "bi bi-trash3");    
    
    const content = `${nombre}  [${duracion}]  ${url || ""}`;
    li.innerText = content;
    li.setAttribute("class", "list-group-item");
    li.setAttribute("data-name", nombre);
    li.setAttribute("data-group", "temas-agregados");
    if (fileInput) {
        li.appendChild(fileInput.label);
        li.appendChild(fileInput.input);
    }
    li.appendChild(trashIcon);
    
    //los creo con posicion 0 y antes del submit verifico el orden elegido por el usuario
    const tema = {
        nombreTema: nombre, 
        duracionTema: duracion, 
        tipoDeAccesoTema: tipoDeAcceso,
        urlORuta: url || "",
        posicion: 0
    };
    mapTemasAgregados.set(nombre, tema);
    ol.appendChild(li);
}

//Remueve el <li> del tema seleccionado y lo borra del map
function removeLiTema(liElement){
    const nombre = liElement.getAttribute("data-name");
        
    if (liElement != null) {
        liElement.remove();
        mapTemasAgregados.delete(nombre);
    }
}

//Obtiene una lista con los nombres de los generos seleccionados
function getGenerosSeleccionados() {
    const elements = document.querySelectorAll("input[type='checkbox'][data-group='cboxGeneros']:checked");
    const values = [];
    for (let i = 0; i < elements.length; i++) {
        values.push(elements[i].getAttribute("data-name"));
    }
    return values;
}

//Obtiene los li con los datos de los temas y actualiza el valor posicion de cada tema basado en su orden
function actualizarValorPosicionDeTemas() {
    
    const elements = document.querySelectorAll("li[data-group='temas-agregados']");
    //no deberia pasar pero por si acaso no se creo el li con el tema
    if (elements.length !== mapTemasAgregados.size) return;
      
    for (let i = 0; i < elements.length; i++) {
        const nombre = elements[i].getAttribute("data-name");
        const tema = mapTemasAgregados.get(nombre);
 
        if (!tema) return;
        
        tema.posicion = i+1;
        mapTemasAgregados.set(nombre, tema);       
    }
}