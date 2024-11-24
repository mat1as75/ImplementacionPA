/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package espotify.DataTypes;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DTDatosCliente extends DTDatosUsuario {
    
    private List<String> nicknamesSeguidos;
    private List<String> nombresListasRCreadas;
    private List<String> nombresListasRCreadasPublicas;
    private List<String> nombresListasRFavoritas;
    private Map<Long, String> nombresAlbumesFavoritos;
    private Map<Long, String> nombresTemasFavoritos;
    private DTSuscripcion suscripcion;
    
    public DTDatosCliente() {
        
    }
    
    public DTDatosCliente(String nicknameUsuario, String nombreUsuario, String apellidoUsuario, 
            String contrasenaUsuario, String email, Date fecNac, String fotoPerfil, 
            List<String> nicknamesSeguidos, List<String> nicknamesSeguidores, 
            List<String> nombresListasRCreadas, List<String> nombresListasRCreadasPublicas, 
            List<String> nombresListasRFavoritas, Map<Long, String> nombresAlbumesFavoritos, 
            Map<Long, String> nombresTemasFavoritos, 
            DTSuscripcion suscripcion) {
        super( nicknameUsuario, nombreUsuario, apellidoUsuario, contrasenaUsuario, email, fecNac, fotoPerfil, nicknamesSeguidores );

        this.nicknamesSeguidos = nicknamesSeguidos;
        this.nombresListasRCreadas = nombresListasRCreadas;
        this.nombresListasRCreadasPublicas = nombresListasRCreadasPublicas;
        this.nombresListasRFavoritas = nombresListasRFavoritas;
        this.nombresAlbumesFavoritos = nombresAlbumesFavoritos;
        this.nombresTemasFavoritos = nombresTemasFavoritos;
        this.suscripcion = suscripcion;
    }
    
    public List<String> getNicknamesSeguidos() { return nicknamesSeguidos; }
    
    public List<String> getNombresListasRCreadas() { return nombresListasRCreadas; }
    
    public List<String> getNombresListasRCreadasPublicas() { return nombresListasRCreadasPublicas; }
    
    public List<String> getNombresListasRFavoritas() { return nombresListasRFavoritas; }
    
    public Map<Long, String> getNombresAlbumesFavoritos() { return nombresAlbumesFavoritos; }
    
    public Map<Long, String> getNombresTemasFavoritos() { return nombresTemasFavoritos; }
   
    public DTSuscripcion getSuscripcion() { return suscripcion; }
    
}
