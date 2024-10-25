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

//cierra el modal al clickear el boton de cancelar
document.getElementById("btnCancelSubmit").addEventListener("click", (evt) => {
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

document.getElementById("nombreAlbum").addEventListener("change", async (evt) => {
    
    
    const value = evt.target.value;
    if (value.trim() === "") {
        return;
    } 
    
    const inputNombre = document.querySelector("#nombreAlbum");
    const errorSpanNombre = document.querySelector("#errorNombreAlbum");
    const spanNombreContainer = errorSpanNombre.closest("div");


    if (await validarNombreAlbumRepetido(value) === false) {
        errorSpanNombre.innerText = errorMsgs.nombreAlbumRepetido;
        spanNombreContainer.classList.remove("d-none");
        inputNombre.scrollIntoView({ behavior: "smooth"});
        return false;
    }
    errorSpanNombre.innerText = "";
    spanNombreContainer.classList.add("d-none");
});

/* 
 * -----------------------------------------------------------
 * FUNCTIONS
 * -----------------------------------------------------------
 */

async function validarNombreAlbumRepetido(inputValue) {
    
    const request = new Request("/ServidorWeb/ExisteAlbum", {
        method: "POST",
        body: inputValue,
        headers: {'Content-Type': 'text/plain;charset=UTF-8'}
    });
 
    let result;
    try {
        const response = await fetch(request);
        
        if (response.ok) {
            result = await response.text();
        }
        if (result === "Existe") {
            return false;
        } else {
            return true;
        }
    } catch (e) {
        console.error("Error: " , e);
        return true;
    }
}

async function handleSubmit(evt) {
    evt.preventDefault();
    if (!GLOBAL_data) return;
    
    const data = GLOBAL_data;
    const form = document.getElementById("formAlbum");    
    const formObject = new FormData(form);
                
    formObject.append("nombreAlbum", data.Album);
    formObject.append("anioAlbum", data.Anio);
    
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
    const rutaPortada = $("#inputPortadaAlbum").val() || "";
    
    if (!validarDatosAlbum(nombreAlbum, anioAlbum)) return;
    if (!validarSeleccionGeneros(arrGeneros)) return;
    if (!validarCreacionDeTemas(mapTemasAgregados)) return;
    if (!validarArchivosDeTemaVacio()) return;
    
    actualizarValorPosicionDeTemas();
    
    const formData = {
        Album: nombreAlbum,
        Anio: anioAlbum,
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
    const inputNombre = document.querySelector("#nombreAlbum");
    const inputAnio = document.querySelector("#anioAlbum");
    const errorSpanNombre = document.querySelector("#errorNombreAlbum");
    const errorSpanAnio = document.querySelector("#errorAnioAlbum");
    const spanNombreContainer = errorSpanNombre.closest("div");
    const spanAnioContainer = errorSpanAnio.closest("div");
    
    if (!nombre) {
        errorSpanNombre.innerText = errorMsgs.nombreAlbumVacio;
        spanNombreContainer.classList.remove("d-none");
        inputNombre.scrollIntoView({ behavior: "smooth"});
        return false;
    }
    errorSpanNombre.innerText = "";
    spanNombreContainer.classList.add("d-none");
    
    if (!anio) {
        errorSpanAnio.innerText = errorMsgs.anioAlbumInvalido;
        spanAnioContainer.classList.remove("d-none");
        inputAnio.scrollIntoView({ behavior: "smooth"});
        return false;
    }
    errorSpanAnio.innerText = "";
    spanAnioContainer.classList.add("d-none");
    
    return true;
}

//Verifica que todos los temas de tipo ruta tengan seleccionado un archivo
function validarArchivosDeTemaVacio() {
    const errorSpan = document.querySelector("#errorTemasVacios");
    const spanContainer = errorSpan.closest("div");
    const fileInputs =  document.querySelectorAll("input[data-type='file-tema'");
    
    for (let i = 0; i < fileInputs.length; i++) {
        if (!fileInputs[i].value) {
            errorSpan.innerText = errorMsgs.temaSinArchivo;
            spanContainer.classList.remove("d-none");
            return false;
        }
    }
    errorSpan.innerText = "";
    spanContainer.classList.add("d-none");
    return true;
}

//Valida que se haya creado al menos un tema
function validarCreacionDeTemas(mapTemas) {
    const errorSpan = document.querySelector("#errorTemasVacios");
    const spanContainer = errorSpan.closest("div");
    
    if (mapTemas.size < 1) {
        errorSpan.innerText = errorMsgs.sinTemas;
        spanContainer.classList.remove("d-none");
        return false;
    }
    errorSpan.innerText = "";
    spanContainer.classList.add("d-none");
    return true;
}

//Valida que se haya seleccionado al menos 1 genero
function validarSeleccionGeneros(generos) {
    const errorSpan = document.querySelector("#errorGeneros");
    const spanContainer = errorSpan.closest("div");
    
    if (generos.length < 1) {
        errorSpan.innerText = errorMsgs.sinGeneros;
        spanContainer.classList.remove("d-none");
        return false;
    }
    errorSpan.innerText = "";
    spanContainer.classList.add("d-none");
    return true;
}

//Verifica que el nombre de tema ingresado no se repita
function validarNombreTema(nombre) {
    const errorSpan = document.querySelector("#errorNombreTema");
    const spanContainer = errorSpan.closest("div");

    if (nombre.trim() === "") {
        errorSpan.innerText = errorMsgs.nombreTemaInvalido;
        spanContainer.classList.remove("d-none");
        return false;
    }

    if (mapTemasAgregados.has(nombre)) {        
        errorSpan.innerText = errorMsgs.nombreTemaRepetido;
        spanContainer.classList.remove("d-none");
        return false;
    }
    errorSpan.innerText = "";
    spanContainer.classList.add("d-none");
    
    return true;
}

//Verifica que la duracion del tema tenga el formato correcto mm:ss o m:s
function validarDuracionTema(duracion) {
    const regex =  /(?<!\S)(([0-5]?\d):)([0-5]?\d)(?!\S)/;
    const errorSpan = document.querySelector("#errorDuracionTema");
    const spanContainer = errorSpan.closest("div");
    
    if (!regex.test(duracion)) {
        errorSpan.innerText = errorMsgs.duracionTemaInvalida;
        spanContainer.classList.remove("d-none");
        
        return false;
    }
    errorSpan.innerText = "";
    spanContainer.classList.add("d-none");
    
    return true;
}

//Verifica que la url ingresada para el tema sea valida, 
//Intenta crear un url con el constructor new URL, capturando el error si no es valida
function validarUrl(url) {
    const errorSpan = document.querySelector("#errorUrlTema");
    const spanContainer = errorSpan.closest("div");
    
    try {
        new URL(url);
        errorSpan.innerText = "";
        spanContainer.classList.add("d-none");
        return true;
    } catch (err) {
        errorSpan.innerText = errorMsgs.urlTemaInvalido;
        spanContainer.classList.remove("d-none");
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
        
    return {label, input};
}

function updateTemasAgregadosContent() {
    const divAviso = document.querySelector("#divAvisoSinTemas");
    const cantTemas = mapTemasAgregados.size;
    
    if (cantTemas < 1) {
        divAviso.classList.remove("d-none");
    } else {
        divAviso.classList.add("d-none");
    }
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
    updateTemasAgregadosContent();
}

//Remueve el <li> del tema seleccionado y lo borra del map
function removeLiTema(liElement){
    if (liElement) {
        const nombre = liElement.getAttribute("data-name");
        liElement.remove();
        mapTemasAgregados.delete(nombre);
    }
    updateTemasAgregadosContent();
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