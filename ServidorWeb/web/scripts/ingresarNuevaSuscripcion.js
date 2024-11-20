const btnSeleccionar = document.getElementById("btnSeleccionar");
const btnSubmitPago = document.getElementById("btnSubmitPago");
const btnAceptar = document.getElementById("btnAceptar");
const btnRegresarIndex = document.getElementById("btnRegresarIndex");
const pResultado = document.getElementById("pResultado");
const modalResultado = document.getElementById("modalResultado");
const form = document.getElementById("paymentForm");
let tipoDePlanSeleccionado;

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

btnAceptar.addEventListener("click", evt => {
    modalResultado.classList.add("hidden"); 
});

btnRegresarIndex.addEventListener("click", evt => {
    window.location.href = "/ServidorWeb";
});

btnSeleccionar.addEventListener("click", async (evt) => {
    
    const seleccionado = document.querySelector("input[type='radio']:checked");
    tipoDePlanSeleccionado = seleccionado.getAttribute("data-plan");
    
    form.classList.remove("hidden");
    form.scrollIntoView({ behavior: "smooth"}); 
});

btnSubmitPago.addEventListener("click", async (evt) => {
   evt.preventDefault();
   
   const formData = getFormInputData();
    
    let result;
    if (formData) {
        result = await handleSubmit(formData);
        await handleResult(result);
    } 
});

function getFormInputData() {
    const CCNvalue = document.getElementById("inputCCN").value;
    const CVVvalue = document.getElementById("inputCVV").value;
    const vencimientoValue = document.getElementById("inputVencimiento").value;
    const propietarioValue = document.getElementById("inputPropietario").value;
    
    return {
        nuevoEstadoSuscripcion: "Pendiente",
        CCN: CCNvalue,
        CVV: CVVvalue,
        vencimiento: vencimientoValue,
        propietario: propietarioValue,
        tipoSuscripcion: tipoDePlanSeleccionado
    };
}

async function handleSubmit(tipoDeSuscripcion) {
    
    const url = "/ServidorWeb/IngresarSuscripcion";
    const obj = getFormInputData();
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
        data = e;
    }
    
    return { responseOk: response.ok, statusCode: response.status, msg: data };
}

function handleResult(result) {
    modalResultado.classList.remove("hidden");
    if (result.responseOk) {
        pResultado.innerText = "Suscripción en estado Pendiente ingresada correctamente.\n\
                \nDeberá esperar a que un administrador la habilite.";
    } else {
        if (result.statusCode === 401) {
            pResultado.innerText = "Inicia sesión para poder continuar.";
        } else {
            pResultado.innerText = `Error al ingresar la suscripción: ${result.msg}`;
        }
    }
}
