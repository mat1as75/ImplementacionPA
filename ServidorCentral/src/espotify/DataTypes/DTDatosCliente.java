/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package espotify.DataTypes;

import java.util.Date;
import java.util.ArrayList;

public class DTDatosCliente extends DTDatosUsuario {
    
    private ArrayList<String> nicknamesSeguidos;
    private ArrayList<String> nombresListasRCreadas;
    private ArrayList<String> nombresListasRCreadasPublicas;
    private ArrayList<String> nombresListasRFavoritas;
    private ArrayList<String> nombresAlbumesFavoritos;
    private ArrayList<String> nombresTemasFavoritos;
    private DTSuscripcion suscripcion;
    
    public DTDatosCliente() {
        
    }
    
    public DTDatosCliente(String nicknameUsuario, String nombreUsuario, String apellidoUsuario, 
            String contrasenaUsuario, String email, Date fecNac, String fotoPerfil, 
            ArrayList<String> nicknamesSeguidos, ArrayList<String> nicknamesSeguidores, 
            ArrayList<String> nombresListasRCreadas, ArrayList<String> nombresListasRCreadasPublicas, 
            ArrayList<String> nombresListasRFavoritas, ArrayList<String> nombresAlbumesFavoritos, 
            ArrayList<String> nombresTemasFavoritos, 
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
    
    public ArrayList<String> getNicknamesSeguidos() { return nicknamesSeguidos; }
    
    public ArrayList<String> getNombresListasRCreadas() { return nombresListasRCreadas; }
    
    public ArrayList<String> getNombresListasRCreadasPublicas() { return nombresListasRCreadasPublicas; }
    
    public ArrayList<String> getNombresListasRFavoritas() { return nombresListasRFavoritas; }
    
    public ArrayList<String> getNombresAlbumesFavoritos() { return nombresAlbumesFavoritos; }
    
    public ArrayList<String> getNombresTemasFavoritos() { return nombresTemasFavoritos; }
   
    public DTSuscripcion getSuscripcion() { return suscripcion; }
    
}
