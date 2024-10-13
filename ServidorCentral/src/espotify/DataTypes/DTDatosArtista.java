/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package espotify.DataTypes;

import java.util.ArrayList;
import java.util.Date;

public class DTDatosArtista extends DTDatosUsuario {
    
    private String biografia;
    private String dirSitioWeb;
    
    private int contidadSeguidores;
    private ArrayList<String> nombresAlbumesPublicados;

    public DTDatosArtista() {
        
    }
    
    public DTDatosArtista(String nicknameUsuario, String nombreUsuario, String apellidoUsuario, 
            String contrasenaUsuario, String email, Date fecNac, String fotoPerfil, String biografia, 
            String dirSitioWeb, int cantidadSeguidores, 
            ArrayList<String> nicknamesSeguidores, ArrayList<String> nombresAlbumesPublicados) {
        
        super( nicknameUsuario, nombreUsuario, apellidoUsuario, contrasenaUsuario, email, fecNac, fotoPerfil, nicknamesSeguidores );

        this.biografia = biografia;
        this.dirSitioWeb = dirSitioWeb;
        this.contidadSeguidores = cantidadSeguidores;
        this.nicknamesSeguidores = nicknamesSeguidores;
        this.nombresAlbumesPublicados = nombresAlbumesPublicados;
    }

    public String getBiografia() { return biografia; }

    public String getDirSitioWeb() { return dirSitioWeb; }
    
    public int getCantidadSeguidores() { return contidadSeguidores; }
    
    public ArrayList<String> getNombresAlbumesPublicados() { return nombresAlbumesPublicados; }
    
}
