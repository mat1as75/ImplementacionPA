/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package espotify.DataTypes;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class DTDatosArtista extends DTDatosUsuario {
    
    private String biografia;
    private String dirSitioWeb;
    private Boolean activo;
    private Date fechaBaja;
    
    private int contidadSeguidores;
    private Map<Long, String> nombresAlbumesPublicados;

    public DTDatosArtista() {
        
    }
    
    public DTDatosArtista(String nicknameUsuario, String nombreUsuario, String apellidoUsuario, 
            String contrasenaUsuario, String email, Date fecNac, String fotoPerfil, String biografia, 
            String dirSitioWeb, Boolean activo, Date fechaBaja, int cantidadSeguidores, 
            List<String> nicknamesSeguidores, Map<Long, String> nombresAlbumesPublicados) {
        
        super( nicknameUsuario, nombreUsuario, apellidoUsuario, contrasenaUsuario, email, fecNac, fotoPerfil, nicknamesSeguidores );

        this.biografia = biografia;
        this.dirSitioWeb = dirSitioWeb;
        this.activo = activo;
        this.fechaBaja = fechaBaja;
        this.contidadSeguidores = cantidadSeguidores;
        this.nicknamesSeguidores = nicknamesSeguidores;
        this.nombresAlbumesPublicados = nombresAlbumesPublicados;
    }

    public String getBiografia() { return biografia; }

    public String getDirSitioWeb() { return dirSitioWeb; }
    
    public Boolean getActivo() { return activo; }
    
    public Date getFechaBaja() { return fechaBaja; }
    
    public int getCantidadSeguidores() { return contidadSeguidores; }
    
    public Map<Long, String> getNombresAlbumesPublicados() { return nombresAlbumesPublicados; }
    
}
