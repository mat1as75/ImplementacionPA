function mostrarCamposAdicionales() {
    var userType = document.getElementById("userType").value;
    var artistaInfo = document.getElementById("artistaInfo");

    if (userType === "artista") {
        artistaInfo.style.display = "table-row-group";
    } else {
        artistaInfo.style.display = "none";
    }
}

function validarPassword() {
    
    var password = document.getElementById("password").value;
    var confirmPassword = document.getElementById("confirmPassword").value;
     document.getElementById("errorMsg3").innerHTML = ""; // Limpiar mensajes de error
    // Validaciones básicas
    if (password !== confirmPassword) {
        document.getElementById("checkpass").style.display = "none";
        document.getElementById("exclamacionpass").style.display = "block";
        document.getElementById("errorMsg3").innerHTML = "<p>Las contraseñas no coinciden.</p>";
        document.getElementById("password").value="";
        document.getElementById("confirmPassword").value="";
     
        
    }else{
        document.getElementById("checkpass").style.display = "block";
        document.getElementById("exclamacionpass").style.display = "none";
        document.getElementById("errorMsg3").innerHTML = "<p>Las contraseñas coinciden.</p>";
    }
    
}

function cargarImagen(event) {
    var canvas = document.getElementById('imagenCanvas');
    var ctx = canvas.getContext('2d');
    var file = event.target.files[0];
    var reader = new FileReader();

    reader.onload = function (event) {
        var img = new Image();
        img.onload = function () {
            // Limpiar el canvas
            ctx.clearRect(0, 0, canvas.width, canvas.height);

            // Dibujar un rectángulo
            ctx.strokeRect(10, 10, 180, 180); // Posición y tamaño del rectángulo

            // Dibujar la imagen dentro del rectángulo en la esquina superior izquierda
            ctx.drawImage(img, 10, 10, 180, 180);
        };
        img.src = event.target.result;
    };

    if (file) {
        reader.readAsDataURL(file);
    }
}
document.getElementById("nickname").addEventListener("blur", function () {
    enviarVariable();
});

document.getElementById("email").addEventListener("blur", function () {
    enviarVariable2();
});

document.getElementById("confirmPassword").addEventListener("blur", function () {
    validarPassword();
});



function enviarVariable() {
            
    // Obtiene el valor del campo de formulario
    const valor = document.getElementById("nickname").value;

    // Crea el objeto XMLHttpRequest
    const xhr = new XMLHttpRequest();

    // Define el comportamiento para cuando se reciba una respuesta
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            // Inserta la respuesta del servidor en un elemento HTML
            if((valor!==null)&&(valor!=="")){
                var respuesta=xhr.responseText;
                document.getElementById("errorMsg").innerHTML = respuesta;
                if( respuesta==="correcto"){
                    document.getElementById("checknick").style.display = "block";
                    document.getElementById("exclamacionnick").style.display = "none";

                }else{
                    document.getElementById("nickname").value="";
                    document.getElementById("exclamacionnick").style.display = "block";
                    document.getElementById("checknick").style.display = "none";

                }
            }    
        }
    };

    // Abre una conexión POST al servlet
    xhr.open("POST", "SVValidarNickyEmail", true);

    // Establece el encabezado para el tipo de contenido (enviar como formulario)
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

    // Envía la solicitud con el valor del campo
    xhr.send("NickName=" + encodeURIComponent(valor));
}

function enviarVariable2() {
    // Obtiene el valor del campo de formulario
    const valor = document.getElementById("email").value;

    // Crea el objeto XMLHttpRequest
    const xhr = new XMLHttpRequest();

    // Define el comportamiento para cuando se reciba una respuesta
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            // Inserta la respuesta del servidor en un elemento HTML
            if((valor!==null)&&(valor!=="")){
                var respuesta=xhr.responseText;
                document.getElementById("errorMsg2").innerHTML = respuesta;
                if( respuesta==="correcto"){
                    document.getElementById("checkemail").style.display = "block";
                    document.getElementById("exclamacionemail").style.display = "none";

                }else{
                    document.getElementById("email").value="";
                    document.getElementById("exclamacionemail").style.display = "block";
                    document.getElementById("checkemail").style.display = "none";

                }
            }
        
        }
    };

    // Abre una conexión POST al servlet
    xhr.open("POST", "SVValidarNickyEmail", true);

    // Establece el encabezado para el tipo de contenido (enviar como formulario)
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

    // Envía la solicitud con el valor del campo
    xhr.send("Email=" + encodeURIComponent(valor));
}

     const input = document.getElementById('nickname');
    input.addEventListener('focus', () => {
                  document.getElementById("exclamacionnick").style.display = "none";
                  document.getElementById("checknick").style.display = "none";
                  document.getElementById("errorMsg").innerHTML ="";
    });
    const input2 = document.getElementById('email');
    input2.addEventListener('focus', () => {
                  document.getElementById("exclamacionemail").style.display = "none";
                  document.getElementById("checkemail").style.display = "none";
                  document.getElementById("errorMsg2").innerHTML ="";
    });
    const input3 = document.getElementById('pasword');
    input3.addEventListener('focus', () => {
                  document.getElementById("exclamacionpass").style.display = "none";
                  document.getElementById("checkpass").style.display = "none";
                  document.getElementById("errorMsg3").innerHTML ="";
    });
    
   
function Cancelar(){
     document.getElementById("nickname").value="";
     document.getElementById("email").value="";
     document.getElementById("nombre").value="";
     document.getElementById("apellido").value="";
     document.getElementById("fechaNacimiento").value="";
     document.getElementById("password").value="";
     document.getElementById("confirmPassword").value="";
     document.getElementById("imagen").value="";
    var userType = document.getElementById("userType").value;
    if (userType === "artista") {
        document.getElementById("biografia").value = "";
        document.getElementById("sitioWeb").value = "";
    }

    document.getElementById("exclamacionnick").style.display = "none";
    document.getElementById("checknick").style.display = "none";
    document.getElementById("errorMsg").innerHTML = "";
    document.getElementById("exclamacionemail").style.display = "none";
    document.getElementById("checkemail").style.display = "none";
    document.getElementById("errorMsg2").innerHTML = "";
    document.getElementById("exclamacionpass").style.display = "none";
    document.getElementById("checkpass").style.display = "none";
    document.getElementById("errorMsg3").innerHTML = "";

    document.getElementById("felisidades").innerHTML = "<b>Se a cancelado su consulta</b>";
}
