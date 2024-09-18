package espotify.logica;

import espotify.DataTypes.DTGenero_Simple;
import espotify.DataTypes.DTAlbum;
import espotify.DataTypes.DTAlbum_Simple;
import espotify.DataTypes.DTAlbum_SinDTArtista;
import espotify.DataTypes.DTDatosArtista;
import espotify.DataTypes.DTDatosCliente;
import espotify.DataTypes.DTDatosListaReproduccion;
import espotify.DataTypes.DTGenero;
import espotify.DataTypes.DTTemaGenerico;
import espotify.DataTypes.DTTemaSimple;
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
    public void AltaGenero(String nombreGenero, String nomPadre) {
        try {
            this.contpersis.AltaGenero(nombreGenero, nomPadre);
        } catch (Exception ex) {
            throw ex;
        }
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
    public ArrayList<String>getNicknamesArtistas(){
        ArrayList<Artista> artistas = contpersis.getArtistas();
        ArrayList<String> nicknames = new ArrayList<>();
        for (Artista a : artistas) {
            nicknames.add(a.getNickname());
        }

        return nicknames;
    };
    
    @Override
    public List<DTGenero> getGenerosjTree(){
        return this.contpersis.getGenerosjTree();
    }

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

    public boolean existeNombreLista(String nombreLista){
        return this.contpersis.ExisteNombreLista(nombreLista);
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
    public void CrearListaPorDefecto(String nombreLista, String fotoLista, String nombreGenero) {
        this.contpersis.CrearListaPorDefecto(nombreLista, fotoLista, nombreGenero);
    }

    @Override
    public void CrearListaParticular(String nombreLista, String fotoLista, String nicknameCliente, boolean esPrivada) {
        this.contpersis.CrearListaParticular(nombreLista, fotoLista, nicknameCliente, esPrivada);
    }
    
    @Override
    public DTDatosListaReproduccion ConsultarListaReproduccion(String tipoDeLista, String nombreLista) {
        return this.contpersis.getDatosListaReproduccion(tipoDeLista, nombreLista);
    }
    
    @Override
    public List<String> ConsultarNombresListasPorTipo(String tipoDeLista, String nickOgen) {
        return this.contpersis.getNombresListasPorTipo(tipoDeLista, nickOgen);
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
    public ArrayList<String> getSeguidosDeCliente(String nickname) {
        return this.contpersis.getSeguidosDeCliente(nickname);
    }
    
    @Override
    public void dejarDeSeguir(String C, String U){
        try {
            this.contpersis.dejarDeSeguir(C,U);
        } catch (Exception ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void DejarDeSeguirPrueba() {
        try {
            this.contpersis.DejarDeSeguirPrueba();
        } catch (Exception ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public boolean clienteSigueAUsuario(String C, String U){
        return this.contpersis.clienteSigueAUsuario(C, U);
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
    public Map<Long, DTTemaSimple> getDTTemasDeListaParticular(String nombreListaReproduccion) {
        return this.contpersis.getDTTemasDeListaParticular(nombreListaReproduccion);
    }
    
    @Override
    public Map<Long, DTTemaSimple> getDTTemasDeListaPorDefecto(String nombreListaReproduccion) {
        return this.contpersis.getDTTemasDeListaPorDefecto(nombreListaReproduccion);
    }
    
    @Override
    public DTTemaGenerico getTemaPorLista(String nombreLista, String tipoDeLista, String nombreTema){
        return this.contpersis.getTemaPorLista(nombreLista, tipoDeLista, nombreTema);
    }
    
    @Override
    public Map<Long, DTTemaSimple> getDTTemasDeAlbum(Long idAlbum) {
        return this.contpersis.getDTTemasDeAlbum(idAlbum);
    }
    
    @Override
    public ArrayList<String> getListasReproduccionDisponibles() {
        return this.contpersis.getListasReproduccionDisponibles();
    }
    
    @Override
    public ArrayList<String> getNombresListasPorDefecto() {
        return this.contpersis.getNombresListasPorDefecto();
    }
    
    @Override
    public ArrayList<String> getNombresListasParticulares() {
        return this.contpersis.getNombresListasParticulares();
    }
    
    @Override
    public ArrayList<String> getNombresListasParticularesPublicas() {
        return this.contpersis.getNombresListasParticularesPublicas();
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
    public ArrayList<DTAlbum_Simple> getDTAlbumesSimple() {
        return this.contpersis.getDTAlbumesSimple();
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
    public void EliminarTemaFavorito(String nicknameCliente, Long idTema) throws Exception {
        try {
            this.contpersis.EliminarTemaFavorito(nicknameCliente, idTema);
        } catch (Exception ex) {
            throw ex;
        }
    }
    
    @Override
    public void EliminarListaFavorito(String nicknameCliente, String nombreLista) throws Exception {
        try {
            this.contpersis.EliminarListaFavorito(nicknameCliente, nombreLista);
        } catch (Exception ex) {
            throw ex;
        }
    }
    
    @Override
    public void EliminarAlbumFavorito(String nicknameCliente, Long idAlbum) throws Exception {
        try {
            
            this.contpersis.EliminarAlbumFavorito(nicknameCliente, idAlbum);
        } catch (Exception ex) {
            
            throw ex;
        }
    }

    @Override
    public ArrayList<String> listasCreadasEstadoPrivadoTrue(String cliente){
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


    @Override
    public void agregarTemaALista(Long idTema, String nombreLista) throws Exception {
        try {
            this.contpersis.agregarTemaALista(idTema, nombreLista);
        } catch (Exception ex) {
            throw ex;
        }
    }
    
    @Override
    public void quitarTemaDeLista(Long idTema, String nombreLista) throws Exception {
        try {
            this.contpersis.quitarTemaDeLista(idTema, nombreLista);
        } catch (Exception ex) {
            throw ex;
        }
    }

    
    @Override
    public ArrayList<String> getNombresListasParticularesDeCliente(String nicknameCliente) throws Exception {
        try {
            return this.contpersis.getNombresListasParticularesDeCliente(nicknameCliente);
        } catch (Exception ex) {
            throw ex;
        }
    }

    @Override  
    public ArrayList<String> getNicknamesClientesListasPrivadas() {
        return this.contpersis.getNicknamesClientesListasPrivadas();
    }

    @Override
    public boolean existeRelacion(String Seguidor, String Seguido){
        return this.contpersis.existeRelacion(Seguidor,Seguido);
    }


    @Override
    public DTAlbum ConsultaAlbum(Long idAlbum){
        return this.contpersis.ConsultaAlbum(idAlbum);
    }
    
    @Override
    public Map<Long, String>  getTemasFavCliente(String nicknameCliente){
        return this.contpersis.getTemasFavCliente(nicknameCliente);
    }
    
     @Override
    public Map<Long, String> getAlbumsFavCliente(String nicknameCliente){
        return this.contpersis.getAlbumsFavCliente(nicknameCliente);
    }
    
     @Override
    public ArrayList<String> getListasFavCliente(String nicknameCliente){
        return this.contpersis.getListasFavCliente(nicknameCliente);
    }
    
    @Override
    public ArrayList<String> getNombresGenerosPadre() {
        return this.contpersis.getNombresGenerosPadre();
    }
    
    @Override
    public ArrayList<String> getNombresGenerosHijos() {
        return this.contpersis.getNombresGenerosHijos();
    }
    
    @Override
    public Map<Long,String>  getMapAlbumesGenero(String genero){
        return this.contpersis.getMapAlbumesGenero(genero);
    }
    
    @Override
    public Map<Long,String>  getMapAlbumesArtista(String artista){
        return this.contpersis.getMapAlbumesArtista(artista);
    }

   

}

