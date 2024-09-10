package espotify.logica;

import espotify.DataTypes.DTAlbum;
import espotify.DataTypes.DTAlbum_SinDTArtista;
import espotify.DataTypes.DTDatosArtista;
import espotify.DataTypes.DTDatosCliente;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IControlador {
    public abstract List<String>getNicknamesArtistas();
    public abstract List<String>getNicknamesClientes();
    public abstract void AltaGenero(String nombreGenero);
    public abstract void AltaArtista(Artista a);
    public abstract void AltaCliente(Cliente c);
    public abstract void AltaAlbum(DTAlbum_SinDTArtista dataAlbum) throws Exception;

    public abstract DTDatosArtista ConsultarPerfilArtista(String nicknameArtista);
    public abstract DTDatosCliente ConsultarPerfilCliente(String nicknameCliente);

    public abstract boolean ExisteNickName(String nickname);
    public abstract boolean ExisteCliente(String nicknameCliente);
    public abstract boolean ExisteEmail(String email);
    
    public abstract void cargarDatosDePrueba();
   
    public abstract void setSeguidorSeguido(String Seguidor, String Seguido);

    public abstract Map<Long, String> getTemasDisponibles();
    public abstract ArrayList<String> getListasReproduccionDisponibles();
    public abstract ArrayList<String> getAlbumesDisponibles();
    public abstract ArrayList<DTAlbum> getDTAlbumesDisponibles();
    public abstract void GuardarTemaFavorito(String nicknameCliente, Long idTema);
    public abstract void GuardarListaFavorito(String nicknameCliente, String nombreLista);
    public abstract void GuardarAlbumFavorito(String nicknameCliente, Long idAlbum);
    
}
