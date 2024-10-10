/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package espotify.DataTypes;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author tecnologo
 */
public class DTDatosArtista {
    
    private String nickname;
    private String nombreUsuario;
    private String apellidoUsuario;
    private String email;
    private Date fecNac;
    private String fotoPerfil;
    private String biografia;
    private String dirSitioWeb;
    
    private int contidadSeguidores;
    private ArrayList<String> nicknamesSeguidores;
    private ArrayList<String> nombresAlbumesPublicados;

    public DTDatosArtista() {
        
    }
    
    public DTDatosArtista(String nickname, String nombre, String apellido, 
            String email, Date fecNac, String fotoPerfil, String biografia, 
            String dirSitioWeb, int cantidadSeguidores, 
            ArrayList<String> nicknamesSeguidores, ArrayList<String> nombresAlbumesPublicados) {
        
        this.nickname = nickname;
        this.nombreUsuario = nombre;
        this.apellidoUsuario = apellido;
        this.email = email;
        this.fecNac = fecNac;
        this.fotoPerfil = fotoPerfil;
        this.biografia = biografia;
        this.dirSitioWeb = dirSitioWeb;
        this.contidadSeguidores = cantidadSeguidores;
        this.nicknamesSeguidores = nicknamesSeguidores;
        this.nombresAlbumesPublicados = nombresAlbumesPublicados;
    }
    
    public String getNickname() { return nickname; }

    public String getNombreUsuario() { return nombreUsuario; }

    public String getApellidoUsuario() { return apellidoUsuario; }

    public String getEmail() { return email; }

    public Date getFecNac() { return fecNac; }

    public String getFotoPerfil() { return fotoPerfil; }

    public String getBiografia() { return biografia; }

    public String getDirSitioWeb() { return dirSitioWeb; }
    
    public int getCantidadSeguidores() { return contidadSeguidores; }
    
    public ArrayList<String> getNicknamesSeguidores() { return nicknamesSeguidores; }
    
    public ArrayList<String> getNombresAlbumesPublicados() { return nombresAlbumesPublicados; }
    
}
