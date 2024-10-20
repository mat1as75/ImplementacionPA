<%-- 
    Document   : pruebaAgregarTemaALista
    Created on : Oct 19, 2024, 4:55:25 PM
    Author     : mat
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <label for="nombreLista">Lista de reproduccion:</label>
        <input id="nombreLista" type="text"/>
        <label for="idTema">Id de tema: </label>
        <input id="idTema" type="number"/>
        
        <button type="button" id="btnSubmit">Agregar</button>
    </body>
    
    <script>
        
        /*
         * Esto esta hecho para probar que el Servlet funcione correctamente.
         * Esta funcion tiene que ir asociada a un boton de agregar tema a lista 
         * donde se muestren los temas de un album o lista de reproduccion.
         * 
         * Cuando se agregue esta funcionalidad el html deberia de tener oculto el Id del tema
         */
        document.getElementById("btnSubmit").addEventListener("click", async (evt) => {
            
            /*
             * Cuando se agregue esta funcion a un boton de agregar lista se va a tener que
             * cambiar los selectores segun como este creado el html con los temas
             */
            const nombreLista = document.querySelector("#nombreLista").value;
            const idTema = document.querySelector("#idTema").value;
           
            //
            const obj = {
                nombreListaReproduccion: nombreLista,
                idTema: idTema
            };
           
            const jsonData = JSON.stringify(obj);
           
            const request = new Request("/ServidorWeb/AgregarTemaALista", {
                method: "POST",
                body: jsonData,
                headers: {'Content-Type': 'application/json;charset=UTF-8'}
            });
            
            let result;
            try {
                const response = await fetch(request);  
                result = await response.text();
                if (response.ok) {
                    //Si se agrego el tema correctamente se devuelve 201
                    console.log("OK: ", result);
                } else {
                    console.log("ERROR: ", result);
                }
            } catch (e) {
                console.error("Error: " , e);
                return true;
            }
           
        });
    </script>
</html>
