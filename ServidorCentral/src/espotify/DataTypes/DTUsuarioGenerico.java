package espotify.DataTypes;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class DTUsuarioGenerico {
    // Atributos Usuarios
    private String nicknameUsuario;
    private String nombreUsuario;
    private String apellidoUsuario;
    private String contrasenaUsuario;
    private String email;
    private Date fecNac;
    private String fotoPerfil;
    // Referencias Usuarios
    private List<String> nicknamesSeguidores;
    // Referencias Clientes
    private List<String> nicknamesSeguidos;
    private List<String> nombresListasRCreadas;
    private List<String> nombresListasRCreadasPublicas;
    private List<String> nombresListasRFavoritas;
    private Map<Long, String> nombresAlbumesFavoritos;
    private Map<Long, String> nombresTemasFavoritos;
    private DTSuscripcion suscripcion;
    // Atributos Artistas
    private String biografia;
    private String dirSitioWeb;
    private Boolean activo;
    private Date fechaBaja;
    // Referencias Artistas
    private int contidadSeguidores;
    private Map<Long, String> nombresAlbumesPublicados;
    
    public DTUsuarioGenerico() {
        
    }

    public DTUsuarioGenerico(String nicknameUsuario, String nombreUsuario, String apellidoUsuario, 
            String contrasenaUsuario, String email, Date fecNac, String fotoPerfil, 
            List<String> nicknamesSeguidores, List<String> nicknamesSeguidos, 
            List<String> nombresListasRCreadas, List<String> nombresListasRCreadasPublicas, 
            List<String> nombresListasRFavoritas, Map<Long, String> nombresAlbumesFavoritos, 
            Map<Long, String> nombresTemasFavoritos, DTSuscripcion suscripcion) {
        this.nicknameUsuario = nicknameUsuario;
        this.nombreUsuario = nombreUsuario;
        this.apellidoUsuario = apellidoUsuario;
        this.contrasenaUsuario = contrasenaUsuario;
        this.email = email;
        this.fecNac = fecNac;
        this.fotoPerfil = fotoPerfil;
        this.nicknamesSeguidores = nicknamesSeguidores;
        this.nicknamesSeguidos = nicknamesSeguidos;
        this.nombresListasRCreadas = nombresListasRCreadas;
        this.nombresListasRCreadasPublicas = nombresListasRCreadasPublicas;
        this.nombresListasRFavoritas = nombresListasRFavoritas;
        this.nombresAlbumesFavoritos = nombresAlbumesFavoritos;
        this.nombresTemasFavoritos = nombresTemasFavoritos;
        this.suscripcion = suscripcion;
    }

    public DTUsuarioGenerico(String nicknameUsuario, String nombreUsuario, String apellidoUsuario, 
            String contrasenaUsuario, String email, Date fecNac, String fotoPerfil, 
            List<String> nicknamesSeguidores, String biografia, String dirSitioWeb, Boolean activo, 
            Date fechaBaja, int contidadSeguidores, Map<Long, String> nombresAlbumesPublicados) {
        this.nicknameUsuario = nicknameUsuario;
        this.nombreUsuario = nombreUsuario;
        this.apellidoUsuario = apellidoUsuario;
        this.contrasenaUsuario = contrasenaUsuario;
        this.email = email;
        this.fecNac = fecNac;
        this.fotoPerfil = fotoPerfil;
        this.nicknamesSeguidores = nicknamesSeguidores;
        this.biografia = biografia;
        this.dirSitioWeb = dirSitioWeb;
        this.activo = activo;
        this.fechaBaja = fechaBaja;
        this.contidadSeguidores = contidadSeguidores;
        this.nombresAlbumesPublicados = nombresAlbumesPublicados;
    }

    public String getNicknameUsuario() {
        return nicknameUsuario;
    }

    public void setNicknameUsuario(String nicknameUsuario) {
        this.nicknameUsuario = nicknameUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getApellidoUsuario() {
        return apellidoUsuario;
    }

    public void setApellidoUsuario(String apellidoUsuario) {
        this.apellidoUsuario = apellidoUsuario;
    }

    public String getContrasenaUsuario() {
        return contrasenaUsuario;
    }

    public void setContrasenaUsuario(String contrasenaUsuario) {
        this.contrasenaUsuario = contrasenaUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFecNac() {
        return fecNac;
    }

    public void setFecNac(Date fecNac) {
        this.fecNac = fecNac;
    }

    public String getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public List<String> getNicknamesSeguidores() {
        return nicknamesSeguidores;
    }

    public void setNicknamesSeguidores(List<String> nicknamesSeguidores) {
        this.nicknamesSeguidores = nicknamesSeguidores;
    }

    public List<String> getNicknamesSeguidos() {
        return nicknamesSeguidos;
    }

    public void setNicknamesSeguidos(List<String> nicknamesSeguidos) {
        this.nicknamesSeguidos = nicknamesSeguidos;
    }

    public List<String> getNombresListasRCreadas() {
        return nombresListasRCreadas;
    }

    public void setNombresListasRCreadas(List<String> nombresListasRCreadas) {
        this.nombresListasRCreadas = nombresListasRCreadas;
    }

    public List<String> getNombresListasRCreadasPublicas() {
        return nombresListasRCreadasPublicas;
    }

    public void setNombresListasRCreadasPublicas(List<String> nombresListasRCreadasPublicas) {
        this.nombresListasRCreadasPublicas = nombresListasRCreadasPublicas;
    }

    public List<String> getNombresListasRFavoritas() {
        return nombresListasRFavoritas;
    }

    public void setNombresListasRFavoritas(List<String> nombresListasRFavoritas) {
        this.nombresListasRFavoritas = nombresListasRFavoritas;
    }

    public Map<Long, String> getNombresAlbumesFavoritos() {
        return nombresAlbumesFavoritos;
    }

    public void setNombresAlbumesFavoritos(Map<Long, String> nombresAlbumesFavoritos) {
        this.nombresAlbumesFavoritos = nombresAlbumesFavoritos;
    }

    public Map<Long, String> getNombresTemasFavoritos() {
        return nombresTemasFavoritos;
    }

    public void setNombresTemasFavoritos(Map<Long, String> nombresTemasFavoritos) {
        this.nombresTemasFavoritos = nombresTemasFavoritos;
    }

    public DTSuscripcion getSuscripcion() {
        return suscripcion;
    }

    public void setSuscripcion(DTSuscripcion suscripcion) {
        this.suscripcion = suscripcion;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public String getDirSitioWeb() {
        return dirSitioWeb;
    }

    public void setDirSitioWeb(String dirSitioWeb) {
        this.dirSitioWeb = dirSitioWeb;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public int getContidadSeguidores() {
        return contidadSeguidores;
    }

    public void setContidadSeguidores(int contidadSeguidores) {
        this.contidadSeguidores = contidadSeguidores;
    }

    public Map<Long, String> getNombresAlbumesPublicados() {
        return nombresAlbumesPublicados;
    }

    public void setNombresAlbumesPublicados(Map<Long, String> nombresAlbumesPublicados) {
        this.nombresAlbumesPublicados = nombresAlbumesPublicados;
    }
    
    
}
