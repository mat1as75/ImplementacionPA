package espotify.logica;

import espotify.DataTypes.DTGenero_Simple;
import espotify.DataTypes.DTAlbum;
import espotify.DataTypes.DTAlbum_SinDTArtista;
import espotify.DataTypes.DTDatosArtista;
import espotify.DataTypes.DTDatosCliente;
import espotify.DataTypes.DTTemaSimple;
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
    
    @Override
    public void AltaAlbum(DTAlbum_SinDTArtista dataAlbum) throws Exception {
        try {
            this.contpersis.AltaAlbum(dataAlbum);
        } catch (Exception ex) {
            throw ex;
        }
    }
    
    @Override
    public void cargarDatosDePrueba() {}

    @Override
    public DTDatosArtista ConsultarPerfilArtista(String nicknameArtista) {

        return this.contpersis.getDatosArtista(nicknameArtista);
    }
    
    @Override
    public DTDatosCliente ConsultarPerfilCliente(String nicknameCliente) {
        
        return this.contpersis.getDatosCliente(nicknameCliente);
    }

    @Override
    public void setSeguidorSeguido(String Seguidor, String Seguido){
        try {
            this.contpersis.setSeguidorSeguido(Seguidor,Seguido);
        } catch (Exception ex) {
            throw ex;
        }
            
    }
    
    @Override
    public Map<Long, String> getTemasDisponibles() {
        return this.contpersis.getTemasDisponibles();
    }
    
    @Override
    public Map<Long, DTTemaSimple> getDTTemasDisponibles() {
        return this.contpersis.getDTTemasDisponibles();
    }
    
    @Override
    public ArrayList<String> getListasReproduccionDisponibles() {
        return this.contpersis.getListasReproduccionDisponibles();
    }
    
    @Override
    public Map<Long, String> getAlbumesDisponibles() {
        return this.contpersis.getAlbumesDisponibles();
    }

    @Override
    public ArrayList<DTAlbum> getDTAlbumesDisponibles() {
        return this.contpersis.getDTAlbumesDisponibles();
    }
    
    @Override
    public void GuardarTemaFavorito(String nicknameCliente, Long idTema) throws Exception {
        try {
            this.contpersis.GuardarTemaFavorito(nicknameCliente, idTema);
        } catch (Exception ex) {
            throw ex;
        }
    }
    
    @Override
    public void GuardarListaFavorito(String nicknameCliente, String nombreLista) throws Exception {
        try {
            this.contpersis.GuardarListaFavorito(nicknameCliente, nombreLista);
        } catch (Exception ex) {
            throw ex;
        }
    }
    
    @Override
    public void GuardarAlbumFavorito(String nicknameCliente, Long idAlbum) throws Exception {
        try {
            this.contpersis.GuardarAlbumFavorito(nicknameCliente, idAlbum);
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override
    public List<String> listasCreadasEstadoPrivadoTrue(String cliente){
        return contpersis.listasCreadasEstadoPrivadoTrue(cliente);
    }
    
    @Override
    public void setPrivadafalse(String cliente, String lista){
        this.contpersis.setPrivadafalse(cliente,lista);
    }
    
    @Override
    public ArrayList<DTGenero_Simple> getListaDTGeneroSimple() {
        return this.contpersis.getListaDTGeneroSimple();
    }

}
