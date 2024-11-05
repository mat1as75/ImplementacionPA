function validarFormulario() {
    const filtro = document.getElementById("filtro");
    
    if (filtro.value === "" || filtro.selectedIndex === 0) {
        alert("Por favor, seleccione una opcion del filtro!");
        return false;
    }
    return true;
}