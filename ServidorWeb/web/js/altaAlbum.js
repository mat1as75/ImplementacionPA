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

document.getElementById("formAlbum").addEventListener("submit", evt => {
    evt.preventDefault();
    console.log("submitted");
});

function addLiItem() {
    
    const name = $("#nombreTema").val();
    const duration = $("#duracionTema").val();
    
    const ol = document.getElementById("olTemasCreados");
    const li = document.createElement("li"); 
    const span = document.createElement("span");
    
    span.innerText = `${name} - ${duration}`;
    li.setAttribute("class", "list-group-item");
    li.setAttribute("id", `tema-${name}`);
    li.appendChild(span);
    li.appendChild(createRadioInput(name));
   
    ol.appendChild(li);
}

function createRadioInput(id) {
    const radioInput = document.createElement("input");
    radioInput.setAttribute("type", "radio");
    radioInput.setAttribute("id", `radio-${id}`);
    radioInput.setAttribute("name", "temas");
    radioInput.setAttribute("class", "radioTema");
    radioInput.setAttribute("value", id);
    
    return radioInput;
}

function removeLiItem(){
    const id = $("input[type='radio']:checked").val();
    const li = document.getElementById(`tema-${id}`);
    
    if (li != null) {
        li.remove();
    }
}

function submitAltaAlbum() {
    const form = document.getElementById("formAlbum");
    form.addEventListener(evt);
}