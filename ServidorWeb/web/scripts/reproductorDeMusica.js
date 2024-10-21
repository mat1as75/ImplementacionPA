const audioFilePlayerContainer = document.querySelector("#audioFilePlayerContainer");
const audioLinkPlayerContainer = document.querySelector("#audioLinkPlayerContainer");
const audioFilePlayer = document.querySelector("#audioFilePlayer");
const audioLinkPlayer = document.querySelector("#linkPlayerIframe");
const nombreTemaFileP = document.querySelector("#nombreTemaFile");
const nombreTemaLinkP = document.querySelector("#nombreTemaLink");
const filePlayerImg = document.querySelector("#filePlayerImg");
const linkTema = document.querySelector("#linkTema");
const errorAudioFilePlayer = document.querySelector("#errorAudioFilePlayer");
const logo = {src: "imagenes/spotifyLogo.png", alt: "Logo de spotify"};

/*
 * -> idTema: es el unico campo requerido, los demas son opcionales
 * 
 * -> nombreTema: se usa para mostrar el nombre del tema encima de los controles del reproductor
 *      extraer el valor desde el elemento que llamo esta funcion, que deberia contener el nombre del tema
 *      si no se quiere especificar setear este parametro como string vacio
 *      
 * -> srcPortada: es la direccion de la imagen de portada de la lista o album
 *      si no tiene portada setear este parametro como string vacio
 *      usa como fallback el icono de spotify
 */
async function play(idTema, nombreTema, srcPortada) {
    const url = `/ServidorWeb/Tema?idTema=${idTema || ""}`;
    const responseInfo = await verifyGetRequest(url);
        
    if (responseInfo.ok) {
        if (responseInfo.contentType === "audio/mpeg") {
            showFilePlayer(nombreTema, url, srcPortada);
        } else {
            showLinkPlayer(nombreTema, srcPortada, responseInfo);
        }
    } else {
        showErrorFilePlayer(responseInfo);
    }
}


/*
 * Antes de asignar el url del Servlet que provee el audio al atributo src del elemento <audio>
 * verifico que obtenga una respuesta
 * Si recibo una respuesta con contenido text/plain entonces guardo el contenido
 * Los mensajes de error por solicitudes incorrectas tambien son capturados y guardados
 */
async function verifyGetRequest(url) {
    try {
        const response = await fetch(url);
        const contentType = response.headers.get("content-type");
        let body;
        
        if (contentType === "text/plain;charset=UTF-8") {
            body = await response.text();
        }
        
        return {
            ok: response.ok,
            status: response.headers.get("status"),
            contentType: response.headers.get("content-type"),
            body: body
        };
        
    } catch (ex) {
        console.error(ex);
        return {
            ok: false,
            status: "",
            contentType: "",
            body: body
        };
    }
}

//Muestra el reproductor de archivos de musica
function showFilePlayer(nombreTema, url, srcPortada) {
    
    //oculto el iframe y remuevo el atributo src
    audioLinkPlayer.setAttribute("src", "");
    audioLinkPlayerContainer.classList.add("hidden");
    
    //remuevo el contenido del link del tema del iframe
    linkTema.innerText = "";
    linkTema.setAttribute("href", "");
    linkTema.classList.add("hidden");
    
    //remuevo mensaje de error anterior si lo hubiera
    errorAudioFilePlayer.innerText = "";

    //seteo el nombre del tema o fallback
    nombreTemaFileP.innerText = nombreTema || "Reproduciendo tema...";

    //seteo la imagen de portada o fallback
    filePlayerImg.setAttribute("src", srcPortada || logo.src);
    filePlayerImg.setAttribute("alt", srcPortada ? logo.alt : "Portada");

    //muestro el reproductor de archivos y seteo el url
    audioFilePlayerContainer.classList.remove("hidden");
    audioFilePlayer.setAttribute("src", url);
}

//Muestra el mensaje de error recibido en el request
function showErrorFilePlayer(responseInfo) {
    //oculto el iframe y remuevo el atributo src
    audioLinkPlayer.setAttribute("src", "");
    audioLinkPlayerContainer.classList.add("hidden");
    
    //Mensaje de error generico
    nombreTemaFileP.innerText = "Error al procesar la solicitud.";

    //seteo imagen de archivo no encontrado
    filePlayerImg.setAttribute("src", "imagenes/notFound.webp");
    filePlayerImg.setAttribute("alt", "Archivo no encontrado");
    
    //muestro el reproductor
    audioFilePlayer.classList.remove("hidden");
    //remuevo el src
    audioFilePlayer.setAttribute("src", "");
    //muestro el mensaje de error recibido en el body
    errorAudioFilePlayer.innerText = responseInfo.body;
}

//Muestra el reproductor de musica que lee un link 
function showLinkPlayer(nombreTema, srcPortada, responseInfo) {
    //oculto el reproductor de archivos y remuevo el atributo src
    audioFilePlayer.setAttribute("src", "");
    audioFilePlayerContainer.classList.add("hidden");

    //seteo el nombre del tema o fallback
    nombreTemaLinkP.innerText = nombreTema || "Tema";

    //seteo la imagen de portada o fallback
    filePlayerImg.setAttribute("src", srcPortada || logo.src);
    filePlayerImg.setAttribute("alt", srcPortada ? logo.alt : "Portada");

    //extraigo el url y si es un link de youtube le agrego /embed/ para poder usarlo en el iframe
    const receivedUrl = responseInfo.body;
    const embeddableUrl = parseYoutubeUrlToEmbeddable(receivedUrl);
    
    //modifico el link del tema y lo muestro
    linkTema.innerText = receivedUrl;
    linkTema.setAttribute("href", receivedUrl);
    linkTema.classList.remove("hidden");
    
    //muestro el reproductor de archivos y seteo el url
    audioLinkPlayerContainer.classList.remove("hidden");
    audioLinkPlayer.setAttribute("src", embeddableUrl);
}

/*
 * Las direcciones de youtube deben ser de la forma youtube.com/embed/videoId
 * para que puedan ser leidas desde un iframe.
 * Esta funcion recibe una url y si es una direccion de youtube sin esa caracteristica
 * la convierte a una version valida para el iframe
 */
function parseYoutubeUrlToEmbeddable(url) {
    let videoId = '';

    if (url.includes('youtube.com/embed/')) return url;

    // Verifico que sea una url estandar, por ejemplo
    // https://www.youtube.com/watch?v=videoId
    if (url.includes('youtube.com/watch')) {
        const urlParams = new URLSearchParams(new URL(url).search);
        //extraigo el parametro 'v' que contiene el id del video
        videoId = urlParams.get('v');
    
    //Verifico si es una url corta, por ejemplo
    // https://youtu.be/videoId
    } else if (url.includes('youtu.be/')) {
        //extraigo la parte siguiente a 'youtu.be/'
        videoId = url.split('youtu.be/')[1]; 
    }
    
    // retorno la url modificada
    if (videoId) {
        return `https://www.youtube.com/embed/${videoId}`;
        
    //si no es una url de youtube retorno la url sin modificar
    } else {
        return url;
    }
}