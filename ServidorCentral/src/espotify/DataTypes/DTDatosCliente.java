/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package espotify.DataTypes;

import java.util.Date;
import java.util.ArrayList;

/**
 *
 * @author tecnologo
 */
public class DTDatosCliente {
    
    private String nickname;
    private String nombreUsuario;
    private String apellidoUsuario;
    private String email;
    private Date fecNac;
    private String fotoPerfil;
    
    private ArrayList<String> nicknamesSeguidos;
    private ArrayList<String> nicknamesSeguidores;
    private ArrayList<String> nombresListasRCreadas;
    private ArrayList<String> nombresListasRFavoritas;
    private ArrayList<String> nombresAlbumesFavoritos;
    private ArrayList<String> nombresTemasFavoritos;
    
    public DTDatosCliente() {
        
    }
    
    public DTDatosCliente(String nickname, String nombre, String apellido, 
            String email, Date fecNac, String fotoPerfil, 
            ArrayList<String> nicknamesSeguidos, ArrayList<String> nicknamesSeguidores, 
            ArrayList<String> nombresListasRCreadas, ArrayList<String> nombresListasRFavoritas, 
            ArrayList<String> nombresAlbumesFavoritos, ArrayList<String> nombresTemasFavoritos) {
        
        this.nickname = nickname;
        this.nombreUsuario = nombre;
        this.apellidoUsuario = apellido;
        this.email = email;
        this.fecNac = fecNac;
        this.fotoPerfil = fotoPerfil;
        this.nicknamesSeguidos = nicknamesSeguidos;
        this.nicknamesSeguidores = nicknamesSeguidores;
        this.nombresListasRCreadas = nombresListasRCreadas;
        this.nombresListasRFavoritas = nombresListasRFavoritas;
        this.nombresAlbumesFavoritos = nombresAlbumesFavoritos;
        this.nombresTemasFavoritos = nombresTemasFavoritos;
    }
    
    public String getNickname() { return nickname; }
    
    public String getNombreUsuario() { return nombreUsuario; }
    
    public String getApellidoUsuario() { return apellidoUsuario; }
    
    public String getEmail() { return email; }
    
    public Date getFecNac() { return fecNac; }
    
    public String getFotoPerfil() { return fotoPerfil; }
    
    public ArrayList<String> getNicknamesSeguidos() { return nicknamesSeguidos; }
    
    public ArrayList<String> getNicknamesSeguidores() { return nicknamesSeguidores; }
    
    public ArrayList<String> getNombresListasRCreadas() { return nombresListasRCreadas; }
    
    public ArrayList<String> getNombresListasRFavoritas() { return nombresListasRFavoritas; }
    
    public ArrayList<String> getNombresAlbumesFavoritos() { return nombresAlbumesFavoritos; }
    
    public ArrayList<String> getNombresTemasFavoritos() { return nombresTemasFavoritos; }
   
    
    
    
}
