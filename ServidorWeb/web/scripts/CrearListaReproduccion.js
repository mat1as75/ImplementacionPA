document.addEventListener("DOMContentLoaded", function () {
    document.getElementById('errorNombreLista').style.display = 'none';
    document.getElementById('errorImagenLista').style.display = 'none';

    document.getElementById("crearListaForm").addEventListener("submit", function (event) {
        event.preventDefault();

        if (!validar()) {
            return;
        }

        const formData = new FormData(this);

        fetch("SVCrearListaReproduccion", {
            method: "POST",
            body: formData
        })
                .then(response => response.json())
                .then(data => {
                    if (data.Exito) {
                        alert(data.Exito);
                    } else if (data.Error) {
                        alert(data.Error);
                    }
                })
                .catch(error => {
                    console.error("Error:", error);
                    alert("Error");
                });
    });
});

function validar() {
    let correcto = true;

    const nombreLista = document.getElementById('nombreLista').value.trim();
    const imagenLista = document.getElementById('imagenLista').value;

    // Comprobar nombre 
    if (nombreLista === '') {
        document.getElementById('errorNombreLista').style.display = 'flex';
        correcto = false;
    } else {
        document.getElementById('errorNombreLista').style.display = 'none';
    }

    // Comprobar extension
    if (imagenLista !== '') {
        const allowedExtensions = /(\.jpg|\.jpeg|\.png|\.gif)$/i;
        if (!allowedExtensions.exec(imagenLista)) {
            document.getElementById('errorImagenLista').style.display = 'flex';
            correcto = false;
        } else {
            document.getElementById('errorImagenLista').style.display = 'none';
        }
    } else {
        document.getElementById('errorImagenLista').style.display = 'none';
    }
    return correcto;
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
