jQuery(document).ready(function () {
    jQuery('.tabs .tab-links a').on('click', function (e) {
        var currentAttrValue = jQuery(this).attr('href');

        // Show/Hide Tabs
        jQuery('.tabs ' + currentAttrValue).show().siblings().hide();

        // Change/remove current tab to active
        jQuery(this).parent('li').addClass('active').siblings().removeClass('active');

        e.preventDefault();
    });
});

function redirigirFavoritosCliente() {
    window.location.href = "ConsultaPerfilUsuario.jsp#favoritos";
}

/* Anadir un event listener para redirigir cuando se haga click en los nombres de 
    Albumes o Listas de Reproduccion en las preferencias del Cliente */
document.querySelector(".body").addEventListener("click", function(event) {
    const target = event.target;
    
    // Verificar si el click ocurrio en la columna de Albumes
    if (target.classList.contains("album")) {
        const nombreAlbum = target.getAttribute("data-name").trim();
        if (nombreAlbum) {
            window.location.href = 'ConsultaAlbum?nombreAlbum=${nombreAlbum}';
        }
    }
    
    // Verificar si el click ocurrio en la columna de Listas de Reproduccion
    if (target.classList.contains("listaR")) {
        const nombreListaR = target.getAttribute("data-name").trim();
        if (nombreListaR) {
            window.location.href = 'ConsultaListaReproduccion?nombreListaR=${nombreListaR}';
        }
    }
});