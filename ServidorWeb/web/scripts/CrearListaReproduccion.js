document.addEventListener("DOMContentLoaded", function () {
    // Ocultar los mensajes de error al cargar la pagina
    document.getElementById('errorNombreLista').style.display = 'none';
    document.getElementById('errorImagenLista').style.display = 'none';
});

document.getElementById('crearListaForm').addEventListener('submit', function (e) {
    e.preventDefault();

    let formData = new FormData(this);

    fetch('SvCrearListaReproduccion', {
        method: 'POST',
        body: formData
    })
    .then(response => response.json())
    .then(data => {
        if (data.Error) {
            document.getElementById('error').textContent = data.Error; 
        } else if (data.Exito) {
            alert(data.Exito); 
            document.getElementById('crearListaForm').reset();
            document.getElementById('error').textContent = ''; 
        }
    })
    .catch(error => console.error('Error:', error));
});


function validar() {
    var correct = true;

    var nombreLista = document.getElementById('nombreLista').value.trim();
    var imagenLista = document.getElementById('imagenLista').value;

    // Nombre de la lista no vacio
    if (nombreLista === '') {
        document.getElementById('errorNombreLista').style.display = 'flex';
        correct = false;
    } else {
        document.getElementById('errorNombreLista').style.display = 'none';
    }

    // Formatos validos para la imagen
    if (imagenLista !== '') {
        var allowedExtensions = /(\.jpg|\.jpeg|\.png|\.gif)$/i;
        if (!allowedExtensions.exec(imagenLista)) {
            document.getElementById('errorImagenLista').style.display = 'flex'; 
            correct = false;
        } else {
            document.getElementById('errorImagenLista').style.display = 'none'; 
        }
    } else {
        document.getElementById('errorImagenLista').style.display = 'none';
    }
    return correct;
}

function cargarImagen(event) {
    const canvas = document.getElementById("imagenCanvas");
    const ctx = canvas.getContext("2d");

    const imagen = new Image();
    imagen.onload = function () {
        ctx.clearRect(0, 0, canvas.width, canvas.height);
        ctx.drawImage(imagen, 0, 0, canvas.width, canvas.height);
    };
    imagen.src = URL.createObjectURL(event.target.files[0]);
}