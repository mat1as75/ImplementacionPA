package espotify.DataTypes;

import java.util.Date;
import java.util.List;
public class DTCliente extends DTUsuario{
    
    
// Referencias
   
    private List<DTUsuario> misSeguidos;
    private List<DTAlbum> misAlbumesFav;
    private List<DTTemaGenerico> misTemasFav;
    private List<DTListaReproduccion> misListasReproduccionFav;
    private List<DTParticulares> misListasReproduccionCreadas;
    private DTSuscripcion miSuscripcion;
    
    // Constructores
    public DTCliente() {
        
    }
    public DTCliente(String nickname, String contrasenaUsuario) {
        this.nickname = nickname;
        this.contrasenaUsuario = contrasenaUsuario;
    }
    public DTCliente(String nickname, String nombreUsuario, String apellidoUsuario, String contrasenaUsuario, String email, Date fecNac, String fotoPerfil) {
        super( nickname, nombreUsuario, apellidoUsuario, contrasenaUsuario, email, fecNac, fotoPerfil );
    }
    
    public List<DTUsuario> getMisSeguidos() {
        return misSeguidos;
    }

    public List<DTAlbum> getMisAlbumesFav() {
        return misAlbumesFav;
    }

    public List<DTTemaGenerico> getMisTemasFav() {
        return misTemasFav;
    }

    public List<DTListaReproduccion> getMisListasReproduccionFav() {
        return misListasReproduccionFav;
    }

    // Getters & Setters
    public List<DTParticulares> getMisListasReproduccionCreadas() {    
        return misListasReproduccionCreadas;
    }

    public void setMisSeguidos(List<DTUsuario> misSeguidos) {
        this.misSeguidos = misSeguidos;
    }
    public void setMiSeguido(DTUsuario misSeguidos) {
        this.misSeguidos.addFirst(misSeguidos);
    }

    public void setMisAlbumesFav(List<DTAlbum> misAlbumesFav) {
        this.misAlbumesFav = misAlbumesFav;
    }
    public void setMiAlbumeFav(DTAlbum misAlbumesFav) {
        this.misAlbumesFav.addFirst(misAlbumesFav);
    }
    public void setMisTemasFav(List<DTTemaGenerico> misTemasFav) {
        this.misTemasFav = misTemasFav;
    }
    public void setMiTemaFav(DTTemaGenerico misTemasFav) {
        this.misTemasFav.addFirst(misTemasFav);
    }

    public void setMisListasReproduccionFav(List<DTListaReproduccion> misListasReproduccionFav) {
        this.misListasReproduccionFav = misListasReproduccionFav;
    }
    public void setMiListasReproduccionFav(DTListaReproduccion misListasReproduccionFav) {
        this.misListasReproduccionFav.addFirst(misListasReproduccionFav);
    }

    public void setMisListasReproduccionCreadas(List<DTParticulares> misListasReproduccionCreadas) {
        this.misListasReproduccionCreadas = misListasReproduccionCreadas;
    }
    public void setMiListasReproduccionCreada(DTParticulares misListasReproduccionCreadas) {
        this.misListasReproduccionCreadas.addFirst(misListasReproduccionCreadas);
    }

    public DTSuscripcion getMiSuscripcion() {
        return miSuscripcion;
    }
    public void setMiSuscripcion(DTSuscripcion miSuscripcion) {
        this.miSuscripcion = miSuscripcion;
    }

    
}
