package espotify.logica;

import espotify.DataTypes.DTGenero_Simple;
import espotify.DataTypes.DTAlbum;
import espotify.DataTypes.DTAlbum_Simple;
import espotify.DataTypes.DTAlbum_SinDTArtista;
import espotify.DataTypes.DTArtista;
import espotify.DataTypes.DTCliente;
import espotify.DataTypes.DTDatosArtista;
import espotify.DataTypes.DTDatosCliente;
import espotify.DataTypes.DTDatosListaReproduccion;
import espotify.DataTypes.DTDatosUsuario;
import espotify.DataTypes.DTGenero;
import espotify.DataTypes.DTRegistroAcceso;
import espotify.DataTypes.DTSuscripcion;
import espotify.DataTypes.DTTemaConPuntaje;
import espotify.DataTypes.DTTemaGenerico;
import espotify.DataTypes.DTTemaGenericoConRutaOUrl;
import espotify.DataTypes.DTTemaSimple;
import espotify.DataTypes.DTUsuario;
import espotify.logica.Suscripcion.EstadoSuscripcion;
import espotify.persistencia.ControladoraPersistencia;
import java.util.ArrayList;
import java.util.Date;
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
    public void AltaArtista(DTArtista dtArtista){
        this.contpersis.AltaArtista(dtArtista);
    }
  
    @Override
    public void AltaCliente(DTCliente dtCliente){
        this.contpersis.AltaCliente(dtCliente);
    }
    
    @Override
    public List<String>getNicknamesArtistas(){
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
        ArrayList<String> nicknames = new ArrayList<>();

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
    public void CrearListaParticular(String nombreLista, String fotoLista, String nicknameCliente, Date fechaCreacion, boolean esPrivada) {
        this.contpersis.CrearListaParticular(nombreLista, fotoLista, nicknameCliente,fechaCreacion, esPrivada);
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
    public Map<Long, DTTemaSimple> getDTTemasDisponiblesConAlbum() {
        return this.contpersis.getDTTemasDisponiblesConAlbum();
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

    @Override
    public boolean NoHayGeneros(){
        return this.contpersis.NoHayGeneros();
    }

    @Override
    public void SetGenero(){
        try {
            this.contpersis.SetGenero();
        } catch (Exception ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public String getGeneroDeListaPorDefecto(String nombreListaRep) throws Exception {
        try {
            return this.contpersis.getGeneroDeListaPorDefecto(nombreListaRep);
        } catch (Exception ex) {
            throw ex;
        }
    }
    
    @Override
    public ArrayList<DTAlbum_Simple> getDTAlbumesSimplePorGenero(String genero) {
        return this.contpersis.getDTAlbumesSimplePorGenero(genero);
    }
    @Override
    public ArrayList<DTAlbum_Simple> getDTAlbumesSimplePorArtista(String artista) {
        return this.contpersis.getDTAlbumesSimplePorArtista(artista);
    }
    

    @Override
    public List<String> getUsuariosSinEste(String nickname){
        return this.contpersis.getUsuariosSinEste(nickname);
    }

    @Override
    public DTUsuario getUsuarioAutentificado(String identificador, String contrasenaUsuario) {
        return this.contpersis.getUsuarioAutentificado(identificador, contrasenaUsuario);
    }
    
    @Override
    public DTDatosUsuario getDatosUsuario(String identificadorUsuario) {
        return this.contpersis.getDatosUsuario(identificadorUsuario);
    }
    
    @Override
    public Long buscarAlbumPorNombreYArtista(String nombreArt, String nombreAlb) {
        return this.contpersis.buscarAlbumPorNombreYArtista(nombreArt, nombreAlb);
    }
    
    @Override
    public ArrayList<DTSuscripcion> getDTSuscripciones() {
        return this.contpersis.getDTSuscripciones();
    }
    
    @Override
    public DTSuscripcion getDTSuscripcion(Long id) {
        return this.contpersis.getDTSuscripcion(id);
    }
    
    @Override
    public void ActualizarEstadoSuscripcion(Long idSuscripcion, EstadoSuscripcion estadoSuscripcion, Date fechaSuscripcion) {
        try {
            this.contpersis.ActualizarEstadoSuscripcion(idSuscripcion, estadoSuscripcion, fechaSuscripcion);
        } catch (Exception ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public DTSuscripcion getDTSuscripcionDeCliente(String nickname) throws Exception {
        try {
            return this.contpersis.getDTSuscripcionDeCliente(nickname);
        } catch (Exception e) {
            throw e;
        }
    }
    
    @Override
    public void ingresarNuevaSuscripcion(String nickname, Suscripcion.TipoSuscripcion tipoSuscripcion) throws Exception {
        try {
            this.contpersis.ingresarNuevaSuscripcion(nickname, tipoSuscripcion);
        } catch (Exception e) {
            throw e;
        }
    }
    
    @Override
    public boolean ExisteArtista(String nicknameArtista) {
        return this.contpersis.existeArtista(nicknameArtista);
    }
    
    @Override
    public DTTemaGenericoConRutaOUrl getDTTemaGenericoConRutaOUrl(Long idTema) {
        return this.contpersis.getDTTemaGenericoConRutaOUrl(idTema);
    }
    
    @Override
    public List<DTDatosListaReproduccion> getListaDTDatosListaReproduccionDeCliente(String nicknameCliente) throws Exception {
        return this.contpersis.getListaDTDatosListaReproduccionDeCliente(nicknameCliente);
    }

    @Override
    public Boolean actualizarSuscripcionVencida(Long idSuscripcion) {
        return this.contpersis.actualizarSuscripcionVencida(idSuscripcion);
    }
    
    @Override
    public void actualizarSuscripcionesVencidas() {
        this.contpersis.actualizarSuscripcionesVencidas();
    }
    
    @Override
    public List<DTArtista> getArtistas(){
        List<DTArtista> retorno = new ArrayList<>();
        for(Artista a : this.contpersis.getArtistas()){
            retorno.addLast(a.getDTArtista());
        }
        
        return retorno;
    }

    @Override
    public List<DTTemaConPuntaje> getTopTemas(int cantidadEsperada) {
        return this.contpersis.getTopTemas(cantidadEsperada);
    }
    
    @Override
    public void incrementarReproduccionesDeTema(Long idTema) throws Exception {
        this.contpersis.incrementarReproduccionesDeTema(idTema);
    }
    
    @Override
    public void incrementarDescargasOVisitasDeTema(Long idTema) throws Exception {
        this.contpersis.incrementarDescargasOVisitasDeTema(idTema);
    }

    @Override
    public void darDeBajaArtista(String nicknameArtista) throws Exception {
        this.contpersis.darDeBajaArtista(nicknameArtista);
    }
  
    @Override
    public ArrayList<DTRegistroAcceso> getDTRegistrosAccesoDisponibles() {
        return this.contpersis.getDTRegistrosAccesoDisponibles();
    }
}

