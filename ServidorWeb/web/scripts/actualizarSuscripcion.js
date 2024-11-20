const btnPagarSuscripcion = document.getElementById("btnPagarSuscripcion");
const btnCancelarSuscripcion = document.getElementById("btnCancelarSuscripcion");
const btnSubmitPago = document.getElementById("btnSubmitPago");
const btnVolver = document.getElementById("btnVolver");
const btnAceptar = document.getElementById("btnAceptar");
const form = document.getElementById("paymentForm");
const modalResultado = document.getElementById("modalResultado");
const pResultado = document.getElementById("pResultado");
const spanEstadoSuscripcion = document.getElementById("estadoSuscripcion");

document.addEventListener("DOMContentLoaded", evt => {
    const states = { notOk: ["Cancelada", "Vencida"], ok: ["Pendiente", "Vigente"] };
    
    if (states.notOk.includes(spanEstadoSuscripcion.innerHTML)) {
        spanEstadoSuscripcion.classList.add("suscripcionNotOk");
    }
    
    if (states.ok.includes(spanEstadoSuscripcion.innerHTML)) {
        spanEstadoSuscripcion.classList.add("suscripcionOk");
    }
});

btnVolver.addEventListener("click", evt => {
    modalResultado.classList.add("hidden");
    window.location.reload();
});

btnAceptar.addEventListener("click", evt => {
    window.location.href = "/ServidorWeb";
});

document.getElementById("btnsOpciones").addEventListener("click", async (evt) => {
    if (evt.target.getAttribute("id") === "btnCancelarSuscripcion") {
        const formData = createFormDataObject("CancelarSuscripcion");
    
        let result;
        if (formData) {
            result = await handleSubmit(formData);
            handleResult(result);
        }    
    }
    
    if (evt.target.getAttribute("id") === "btnPagarSuscripcion") {
        form.classList.remove("hidden");
        form.scrollIntoView({ behavior: "smooth"});
    } 
}); 

btnSubmitPago.addEventListener("click", async (evt) => {
    evt.preventDefault();
    const formData = createFormDataObject("PagarSuscripcion");
    
    let result;
    if (formData) {
        result = await handleSubmit(formData);
        handleResult(result);
    }
});

function getFormInputData() {
    const CCNvalue = document.getElementById("inputCCN").value;
    const CVVvalue = document.getElementById("inputCVV").value;
    const vencimientoValue = document.getElementById("inputVencimiento").value;
    const propietarioValue = document.getElementById("inputPropietario").value;
    
    return {
        CCN: CCNvalue,
        CVV: CVVvalue,
        vencimiento: vencimientoValue,
        propietario: propietarioValue
    };
}

function createFormDataObject(option) {
    if (option === "CancelarSuscripcion") {
        return {
            nuevoEstadoSuscripcion: "Cancelada",
            CCN: "",
            CVV: "",
            vencimiento: new Date(),
            propietario: ""
        };
    }
    
    if (option === "PagarSuscripcion") {
        const data = getFormInputData();
        return {
            nuevoEstadoSuscripcion: "Pendiente",
            CCN: data.CCN,
            CVV: data.CVV,
            vencimiento: data.vencimiento,
            propietario: data.propietario
        };
    }
}

async function handleSubmit(objData) {
    
    let jsonData;
    try {
        jsonData = JSON.stringify(objData);
    } catch (e) {
        pResultado.innerText = "Error al procesar los datos del formulario.";
        modalResultado.classList.remove("hidden");
        return;
    }
    
    const url = "/ServidorWeb/ActualizarSuscripcion";
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
        pResultado.innerText = "Suscripción actualizada exitosamente.";
    } else {
        if (result.statusCode === 500) {
            console.error(result.msg);
            pResultado.innerText = "Error interno del servidor.";
        } else {
            pResultado.innerText = `Error al procesar la solicitud: ${result.msg}`;
        }
    }
}