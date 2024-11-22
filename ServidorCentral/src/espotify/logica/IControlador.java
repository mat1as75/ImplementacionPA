package espotify.logica;

import espotify.DataTypes.DTAlbum;
import espotify.DataTypes.DTAlbum_Simple;
import espotify.DataTypes.DTAlbum_SinDTArtista;
import espotify.DataTypes.DTArtista;
import espotify.DataTypes.DTCliente;
import espotify.DataTypes.DTDatosArtista;
import espotify.DataTypes.DTDatosCliente;
import espotify.DataTypes.DTDatosListaReproduccion;
import espotify.DataTypes.DTDatosUsuario;
import espotify.DataTypes.DTDatosUsuarioSinPw;
import espotify.DataTypes.DTGenero;
import espotify.DataTypes.DTGenero_Simple;
import espotify.DataTypes.DTRegistroAcceso;
import espotify.DataTypes.DTSuscripcion;
import espotify.DataTypes.DTTemaConPuntaje;
import espotify.DataTypes.DTTemaGenerico;
import espotify.DataTypes.DTTemaGenericoConRutaOUrl;
import espotify.DataTypes.DTTemaSimple;
import espotify.DataTypes.DTUsuario;
import espotify.logica.Suscripcion.EstadoSuscripcion;
import espotify.logica.Suscripcion.TipoSuscripcion;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IControlador {
    
    public abstract void AltaGenero(String nombreGenero, String nomPadre);
    
    public abstract boolean existeNombreLista(String nombreLista);
    public abstract ArrayList<String> getSeguidosDeCliente(String nickname);
    
    public abstract void CrearListaPorDefecto(String nombreLista, String fotoLista, String nombreGenero);
    public abstract void CrearListaParticular(String nombreLista, String fotoLista, String nicknameCliente, boolean esPrivada);

    public abstract Map<Long, String> getTemasDisponibles();
    public abstract Map<Long, DTTemaSimple> getDTTemasDisponibles();
    public abstract Map<Long, DTTemaSimple> getDTTemasDeListaParticular(String nombreListaReproduccion);
    public abstract Map<Long, DTTemaSimple> getDTTemasDeListaPorDefecto(String nombreListaReproduccion);
    public abstract Map<Long, DTTemaSimple> getDTTemasDeAlbum(Long idAlbum);
    public abstract String getGeneroDeListaPorDefecto(String nombreListaRep) throws Exception;
    public abstract List<String> ConsultarNombresListasPorTipo(String tipoDeLista, String nickOgen);
    public abstract ArrayList<DTAlbum> getDTAlbumesDisponibles();
    public abstract DTTemaGenerico getTemaPorLista(String nombreLista, String tipoDeLista, String nombreTema);
    public abstract List<DTGenero> getGenerosjTree();
  
    public abstract void EliminarTemaFavorito(String nicknameCliente, Long idTema) throws Exception;
    public abstract void EliminarListaFavorito(String nicknameCliente, String nombreLista) throws Exception;
    public abstract void EliminarAlbumFavorito(String nicknameCliente, Long idAlbum) throws Exception;

    public abstract boolean clienteSigueAUsuario(String C, String U);

    public abstract ArrayList<String> getNicknamesClientesListasPrivadas();

    public abstract boolean existeRelacion(String Seguidor, String Seguido);

    public abstract Map<Long, String> getTemasFavCliente(String nicknameCliente);
    public abstract Map<Long, String> getAlbumsFavCliente(String nicknameCliente);
    public abstract ArrayList<String> getListasFavCliente(String nicknameCliente);
    
    public abstract ArrayList<String> getNombresGenerosHijos();
    
    public abstract Map<Long,String>  getMapAlbumesGenero(String genero);
    public abstract Map<Long,String>  getMapAlbumesArtista(String artista);
    
    public abstract boolean NoHayGeneros();
    public abstract void SetGenero();
    public abstract List<String> getUsuariosSinEste(String nickname);
    
    public abstract ArrayList<DTSuscripcion> getDTSuscripciones();
    
    public abstract void actualizarSuscripcionesVencidas();
    
    public abstract List<DTDatosListaReproduccion> getListaDTDatosListaReproduccionDeCliente(String nicknameCliente) throws Exception;
    public abstract List<DTArtista> getArtistas();

    public abstract ArrayList<DTRegistroAcceso> getDTRegistrosAccesoDisponibles();

    public abstract void guardarRegistroAcceso(String direccionIP, String url, String navegador, String sistemaOperativo, Date fechaAcceso);
  
    /* -----OPERACIONES DE USUARIO WEB SERVICE----- */
    public abstract void AltaArtista(DTArtista dtArtista);
    public abstract void AltaCliente(DTCliente dtCliente);
    public abstract List<String>getNicknamesArtistas();
    public abstract List<String>getNicknamesClientes();
    public abstract DTDatosUsuario getDatosUsuario(String identificadorUsuario);
    public abstract DTUsuario getUsuarioAutentificado(String identificador, String contrasenaUsuario);
    public abstract DTDatosArtista ConsultarPerfilArtista(String nicknameArtista);
    public abstract DTDatosCliente ConsultarPerfilCliente(String nicknameCliente);
    public abstract boolean ExisteCliente(String nicknameCliente);
    public abstract boolean ExisteArtista(String nicknameArtista);
    public abstract boolean ExisteNickName(String nickname);
    public abstract boolean ExisteEmail(String email);
    public abstract void darDeBajaArtista(String nicknameArtista) throws Exception;
   
    /* ----- OPERACIONES DE SUSCRIPCIONES WEB SERVICE ----- */
    public abstract DTSuscripcion getDTSuscripcion(Long id);
    public abstract DTSuscripcion getDTSuscripcionDeCliente(String nickname) throws Exception;
    public abstract void ingresarNuevaSuscripcion(String nickname, TipoSuscripcion tipoSuscripcion) throws Exception;
    public abstract void ActualizarEstadoSuscripcion(Long idSuscripcion, EstadoSuscripcion estadoSuscripcion, Date fechaSuscripcion);
    public abstract Boolean actualizarSuscripcionVencida(Long idSuscripcion);

    /* ----- OPERACIONES DE RANKING WEB SERVICE ----- */
    public abstract List<DTTemaConPuntaje> getTopTemas(int cantidadEsperada);
    public abstract ArrayList<DTDatosUsuarioSinPw> getUsuariosOrdenadosPorRanking(int cantidadEsperada);

    /* ----- OPERACIONES DE CONTENIDO WEB SERVICE ----- */
    public abstract void AltaAlbum(DTAlbum_SinDTArtista dataAlbum) throws Exception;
    public abstract Map<Long, String> getAlbumesDisponibles();
    public abstract Long buscarAlbumPorNombreYArtista(String nombreArt, String nombreAlb);
    public abstract DTAlbum ConsultaAlbum(Long idAlbum);
    public abstract void agregarTemaALista(Long idTema, String nombreLista) throws Exception;
    public abstract void quitarTemaDeLista(Long idTema, String nombreLista) throws Exception;
    public abstract Map<Long, DTTemaSimple> getDTTemasDisponiblesConAlbum();
    public abstract DTTemaGenericoConRutaOUrl getDTTemaGenericoConRutaOUrl(Long idTema);
    public abstract ArrayList<DTGenero_Simple> getListaDTGeneroSimple();
    public abstract ArrayList<String> getNombresGenerosPadre();

    /* ----- OPERACIONES DE DATAALBUMS WEB SERVICE ----- */
    public abstract ArrayList<DTAlbum_Simple> getDTAlbumesSimple();
    public abstract ArrayList<DTAlbum_Simple> getDTAlbumesSimplePorGenero(String genero);
    public abstract ArrayList<DTAlbum_Simple> getDTAlbumesSimplePorArtista(String artista);
        
    /* ----- OPERACIONES DE PREFERENCIAS WEB SERVICE ----- */
    public abstract void dejarDeSeguir(String C, String U);
    public abstract void setSeguidorSeguido(String Seguidor, String Seguido);
    public abstract void GuardarTemaFavorito(String nicknameCliente, Long idTema) throws Exception;
    public abstract void GuardarListaFavorito(String nicknameCliente, String nombreLista) throws Exception;
    public abstract void GuardarAlbumFavorito(String nicknameCliente, Long idAlbum) throws Exception;
    public abstract void setPrivadafalse(String cliente, String lista);
    public abstract void incrementarReproduccionesDeTema(Long idTema) throws Exception;
    public abstract void incrementarDescargasOVisitasDeTema(Long idTema) throws Exception;

    /* ----- OPERACIONES DE LISTAREPRODUCCION WEB SERVICE ----- */
    public abstract void CrearListaParticular(String nombreLista, String fotoLista, String nicknameCliente, Date fechaCreacion, boolean esPrivada);
    public abstract DTDatosListaReproduccion ConsultarListaReproduccion(String tipoDeLista, String op);
    public abstract ArrayList<String> getListasReproduccionDisponibles();
    public abstract ArrayList<String> getNombresListasPorDefecto();
    public abstract ArrayList<String> getNombresListasParticulares();
    public abstract ArrayList<String> getNombresListasParticularesPublicas();
    public abstract ArrayList<String> getNombresListasParticularesDeCliente(String nicknameCliente) throws Exception;
    public abstract ArrayList<String> listasCreadasEstadoPrivadoTrue(String cliente);

}
