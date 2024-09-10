package espotify.logica;

import espotify.DataTypes.DTAlbum;
import espotify.DataTypes.DTAlbum_SinDTArtista;
import espotify.DataTypes.DTDatosArtista;
import espotify.DataTypes.DTDatosCliente;
import espotify.persistencia.ArtistaJpaController;
import espotify.persistencia.ControladoraPersistencia;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controlador implements IControlador{
    ControladoraPersistencia contpersis = new ControladoraPersistencia();
    // 1 de Singleton
    private Controlador() {
        
    }
    
    // 2 de Singleton
    private static Controlador miInstancia = null;
    
    // 3 de Singleton
    public static Controlador getInstance() {
        if (Controlador.miInstancia == null) {
            Controlador.miInstancia = new Controlador();
        }
        return (Controlador.miInstancia);
    }

    @Override
    public void AltaGenero(String nombreGenero) {
       /* GeneroJpaController GJP = GeneroJpaController.getInstance();
        Genero g = new Genero(nombreGenero);
        
        try {
            GJP.create(g);
        } catch (Exception ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
       this.contpersis.AltaGenero(nombreGenero);
    }
    
    @Override
    public void AltaArtista(Artista a){
        this.contpersis.AltaArtista(a);
    }
  
    @Override
    public void AltaCliente(Cliente c){
        this.contpersis.AltaCliente(c);
    }
    
    @Override
    public List<String>getNicknamesArtistas(){
        ArrayList<Artista> artistas = contpersis.getArtistas();
        ArrayList<String> nicknames = new ArrayList<String>();
        for (Artista a : artistas) {
            nicknames.add(a.getNickname());
        }

        return nicknames;
    };
    
    @Override
    public List<String>getNicknamesClientes() {
        ArrayList<Cliente> clientes = contpersis.getClientes();
        ArrayList<String> nicknames = new ArrayList<String>();

        for (Cliente c : clientes) {
            nicknames.add(c.getNickname());
        }

        return nicknames;
    }
    
    @Override
    public boolean ExisteNickName(String nickname){
        return this.contpersis.ExisteNickName(nickname);
    }
    
    @Override
    public boolean ExisteCliente(String nicknameCliente) {
        return this.contpersis.ExisteCliente(nicknameCliente);
    }
  
    @Override
    public boolean ExisteEmail(String email){
        return this.contpersis.ExisteEmail(email);
    }
    
    public void AltaAlbum(DTAlbum_SinDTArtista dataAlbum) throws Exception {
        try {
            this.contpersis.AltaAlbum(dataAlbum);
        } catch (Exception ex) {
            throw ex;
        }
    }
    
    public void cargarDatosDePrueba() {}

    @Override
    public DTDatosArtista ConsultarPerfilArtista(String nicknameArtista) {

        return this.contpersis.getDatosArtista(nicknameArtista);
    }
    
    @Override
    public DTDatosCliente ConsultarPerfilCliente(String nicknameCliente) {
        
        return this.contpersis.getDatosCliente(nicknameCliente);
    }


    public void setSeguidorSeguido(String Seguidor, String Seguido){
        this.contpersis.setSeguidorSeguido(Seguidor,Seguido);
    };
    
    @Override
    public Map<Long, String> getTemasDisponibles() {
        return this.contpersis.getTemasDisponibles();
    }
    
    @Override
    public ArrayList<String> getListasReproduccionDisponibles() {
        return this.contpersis.getListasReproduccionDisponibles();
    }
    
    @Override
    public ArrayList<String> getAlbumesDisponibles() {
        return this.contpersis.getAlbumesDisponibles();
    }

    @Override
    public ArrayList<DTAlbum> getDTAlbumesDisponibles() {
        return this.contpersis.getDTAlbumesDisponibles();
    }
    
    @Override
    public void GuardarTemaFavorito(String nicknameCliente, Long idTema) {
        this.contpersis.GuardarTemaFavorito(nicknameCliente, idTema);
    }
    
    @Override
    public void GuardarListaFavorito(String nicknameCliente, String nombreLista) {
        this.contpersis.GuardarListaFavorito(nicknameCliente, nombreLista);
    }
    
    @Override
    public void GuardarAlbumFavorito(String nicknameCliente, Long idAlbum) {
        this.contpersis.GuardarAlbumFavorito(nicknameCliente, idAlbum);
    }
    
    
}
