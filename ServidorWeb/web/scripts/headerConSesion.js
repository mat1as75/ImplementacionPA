function asignarPerfilConsultado() {
    // Usa la variable global definida en el JSP
    const valor = nickname;
    
    fetch('asignarPerfilConsultado', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({nicknameUsuarioConsultado: valor })
    })
    .then(response => {
        if (response.ok) {
            return response.text();
        }
        throw new Error('Error en la asignacion del Perfil a Consultar');
    })
    .then(data => {
        console.log(data); // Maneja la respuesta del servidor
    })
    .catch(error => {
        console.error(error);
    });
}
