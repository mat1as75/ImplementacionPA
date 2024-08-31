package espotify.DataTypes;

import espotify.logica.Usuario;
import java.util.Date;
import java.util.List;
public class DTCliente extends DTUsuario{
    
    
// Referencias
   
    private List<DTUsuario> misSeguidos;
    //private List<Album> misAlbumesFav;
    private List<DTTema> misTemasFav;
    //private List<ListaReproduccion> misListasReproduccionFav;
    //private List<ListaReproduccion> misListasReproduccionCreadas;
    
    // Constructores
    public DTCliente() {
        
    }
    public DTCliente(String nickname, String nombreUsuario, String apellidoUsuario, String email,Date fecNac, String fotoPerfil) {
        super( nickname, nombreUsuario, apellidoUsuario, email, fecNac, fotoPerfil );
    }
    
    // Getters & Setters
    public List<DTUsuario> getMisSeguidos() {
        return this.misSeguidos;
    }
    public void setMisSeguidos(DTUsuario dtu) {
        this.misSeguidos.addFirst(dtu);
    }
    
    
    public List<DTTema> getMisTemasFav() {
        return this.misTemasFav;
    }
    public void setMisTemasFav(DTTema t) {
        this.misTemasFav.addFirst(t);
    }
    /*
    public List<Album> getMisAlbumesFav() {
        return this.misAlbumesFav;
    }
    public void setMisAlbumesFav(Album a) {
        this.misAlbumesFav.addFirst(a);
    }
    
    public List<ListaReproduccion> getMisListasReproduccionFav() {
        return this.misListasReproduccionFav;
    }
    public void setMisListasReproduccionFav(ListaReproduccion lrf) {
        this.misListasReproduccionFav.addFirst(lrf);
    }
    
    public List<ListaReproduccion> getMisListasReproduccionCreadas() {
        return this.misListasReproduccionCreadas;
    }
    public void setMisListasReproduccionCreadas(ListaReproduccion lrc) {
        this.misListasReproduccionCreadas.addFirst(lrc);
    }*/
}
