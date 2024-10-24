const btnConfirmar = document.getElementById("btnConfirmar");
const btnSalir = document.getElementById("btnSalir");
const btnContinuar = document.getElementById("btnContinuar");
const pResultado = document.getElementById("pResultado");
const modalResultado = document.getElementById("modalResultado");

document.getElementById("section").addEventListener("click", evt => {
    const closestCard = evt.target.closest(".cardPlanes");
    
    if (closestCard) {
        const radioId = closestCard.getAttribute("data-idRadio");
        const radioInput = document.getElementById(radioId);
        radioInput.checked = true;
        
        const allCards = document.querySelectorAll(".cardPlanes");
        allCards.forEach( card => card.classList.remove("selectedCard"));
        
        closestCard.classList.add("selectedCard");
    }
});

btnConfirmar.addEventListener("click", async (evt) => {
    
    const seleccionado = document.querySelector("input[type='radio']:checked");
    const planSeleccionado = seleccionado.getAttribute("data-plan");
    
    const result = await handleSubmit(planSeleccionado);
    handleResult(result);
    
});

btnSalir.addEventListener("click", evt => {
    document.getElementById("modalResultado").classList.add("hidden");
});

btnContinuar.addEventListener("click", evt => {
   console.log("continuar en otra pagina donde se paga");
});

async function handleSubmit(tipoDeSuscripcion) {
    
    const url = "/ServidorWeb/IngresarSuscripcion";
    const obj = { tipoSuscripcion: tipoDeSuscripcion };
    const jsonData = JSON.stringify(obj);
    
    const request = new Request(url, {
       method: "POST",
       body: jsonData,
       headers: {'Content-Type': "application/json;charset=UTF-8"}
    });
    
    let response, data;
    try {
        response = await fetch(request);
        data = await response.text();
    } catch (e) {
        console.error(e);
    }
    
    return { responseOk: response.ok, statusCode: response.status, msg: data };
}

function handleResult(result) {
    modalResultado.classList.remove("hidden");
    if (result.responseOk) {
        pResultado.innerText = "Suscripción ingresada correctamente.";
        btnContinuar.classList.remove("hidden");
    } else {
        if (result.statusCode === 401) {
            pResultado.innerText = "Inicia sesión para poder continuar.";
        } else {
            pResultado.innerText = `Error al ingresar la suscripción: ${result.msg}`;
        }
    }
}
