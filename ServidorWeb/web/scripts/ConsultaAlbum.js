/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

document.addEventListener("DOMContentLoaded", evt => {
    document.getElementById("audioLinkPlayerContainer").classList.add("hidden");

});
 
async function GuardarAlbumFavorito() {
    var albumId = document.getElementById("albumId").value;
    var nickname = document.getElementById("nickname").value;
    
    const request = new Request("/ServidorWeb/SVGuardarAlbumFavorito", {
        method: 'POST',
        body: albumId, nickname
    });
    
    let results;
    try {
        const response = await fetch(request);
        results = await response.text();
    } catch (e) {
        console.error("Error: ", e);
    }     
}

async function GuardarTemaFavorito() {
    var idTema = document.getElementById("idTema").value;
    var nickname = document.getElementById("nicknameSesion").value;
    
    const request = new Request("/ServidorWeb/SVGuardarTemaFavorito", {
        method: 'POST',
        body: idTema, nickname
    });
    
    let results;
    try {
        const response = await fetch(request);
        results = await response.text();
    } catch (e) {
        console.error("Error: ", e);
    } 
}