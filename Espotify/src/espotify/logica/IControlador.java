package espotify.logica;


import espotify.DataTypes.DTCliente;
import espotify.DataTypes.DTDatosArtista;
import espotify.DataTypes.DTDatosCliente;
import espotify.DataTypes.DTListaReproduccion;

import espotify.DataTypes.DTAlbum;
import espotify.DataTypes.DTAlbum_Simple;
import espotify.DataTypes.DTAlbum_SinDTArtista;
import espotify.DataTypes.DTDatosArtista;
import espotify.DataTypes.DTDatosCliente;
import espotify.DataTypes.DTDatosListaReproduccion;
import espotify.DataTypes.DTGenero;
import espotify.DataTypes.DTGenero_Simple;
import espotify.DataTypes.DTTemaGenerico;
import espotify.DataTypes.DTTemaSimple;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IControlador {
    public abstract ArrayList<String>getNicknamesArtistas();
    public abstract List<String>getNicknamesClientes();
    public abstract void AltaGenero(String nombreGenero, String nomPadre);
    public abstract void AltaArtista(Artista a);
    public abstract void AltaCliente(Cliente c);
    public abstract void AltaAlbum(DTAlbum_SinDTArtista dataAlbum) throws Exception;

    public abstract DTDatosArtista ConsultarPerfilArtista(String nicknameArtista);
    public abstract DTDatosCliente ConsultarPerfilCliente(String nicknameCliente);

    public abstract boolean ExisteNickName(String nickname);
    public abstract boolean ExisteCliente(String nicknameCliente);
    public abstract boolean ExisteEmail(String email);

    public abstract boolean existeNombreLista(String nombreLista);
    public abstract void setSeguidorSeguido(String Seguidor, String Seguido);
    public abstract ArrayList<String> getSeguidosDeCliente(String nickname);
    public abstract void dejarDeSeguir(String C, String U);
    public abstract void DejarDeSeguirPrueba();
    
    public abstract void CrearListaPorDefecto(String nombreLista, String fotoLista, String nombreGenero);
    public abstract void CrearListaParticular(String nombreLista, String fotoLista, String nicknameCliente, boolean esPrivada);
    public abstract DTDatosListaReproduccion ConsultarListaReproduccion(String tipoDeLista, String op);
    
    
   
    public abstract void cargarDatosDePrueba();
   
   

    public abstract Map<Long, String> getTemasDisponibles();
    public abstract Map<Long, DTTemaSimple> getDTTemasDisponibles();
    public abstract Map<Long, DTTemaSimple> getDTTemasDeListaParticular(String nombreListaReproduccion);
    public abstract Map<Long, DTTemaSimple> getDTTemasDeListaPorDefecto(String nombreListaReproduccion);
    public abstract Map<Long, DTTemaSimple> getDTTemasDeAlbum(Long idAlbum);
    public abstract ArrayList<String> getListasReproduccionDisponibles();
    public abstract ArrayList<String> getNombresListasPorDefecto();
    public abstract ArrayList<String> getNombresListasParticulares();
    public abstract ArrayList<String> getNombresListasParticularesPublicas();
    public abstract ArrayList<String> getNombresListasParticularesDeCliente(String nicknameCliente) throws Exception;
    public abstract List<String> ConsultarNombresListasPorTipo(String tipoDeLista, String nickOgen);
    public abstract Map<Long, String> getAlbumesDisponibles();
    public abstract ArrayList<DTAlbum> getDTAlbumesDisponibles();
    public abstract ArrayList<DTAlbum_Simple> getDTAlbumesSimple();
    public abstract ArrayList<DTGenero_Simple> getListaDTGeneroSimple();
    public abstract DTTemaGenerico getTemaPorLista(String nombreLista, String tipoDeLista, String nombreTema);
    public abstract List<DTGenero> getGenerosjTree();
    public abstract void GuardarTemaFavorito(String nicknameCliente, Long idTema) throws Exception;
    public abstract void GuardarListaFavorito(String nicknameCliente, String nombreLista) throws Exception;
    public abstract void GuardarAlbumFavorito(String nicknameCliente, Long idAlbum) throws Exception;

    public abstract void EliminarTemaFavorito(String nicknameCliente, Long idTema) throws Exception;
    public abstract void EliminarListaFavorito(String nicknameCliente, String nombreLista) throws Exception;
    public abstract void EliminarAlbumFavorito(String nicknameCliente, Long idAlbum) throws Exception;



    public abstract boolean clienteSigueAUsuario(String C, String U);

    public abstract ArrayList<String> listasCreadasEstadoPrivadoTrue(String cliente);

    public abstract void setPrivadafalse(String cliente, String lista);

    
    public abstract void agregarTemaALista(Long idTema, String nombreLista) throws Exception;
    public abstract void quitarTemaDeLista(Long idTema, String nombreLista) throws Exception;
    public abstract ArrayList<String> getNicknamesClientesListasPrivadas();


    public abstract boolean existeRelacion(String Seguidor, String Seguido);


    
    public abstract DTAlbum ConsultaAlbum(Long idAlbum);
    public abstract Map<Long, String>  getTemasFavCliente(String nicknameCliente);
    public abstract Map<Long, String> getAlbumsFavCliente(String nicknameCliente);
    public abstract ArrayList<String> getListasFavCliente(String nicknameCliente);
    
    public abstract ArrayList<String> getNombresGenerosPadre();
    public abstract ArrayList<String> getNombresGenerosHijos();
    
    public abstract Map<Long,String>  getMapAlbumesGenero(String genero);
    public abstract Map<Long,String>  getMapAlbumesArtista(String artista);

}
