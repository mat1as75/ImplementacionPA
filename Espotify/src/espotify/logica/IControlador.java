package espotify.logica;

import espotify.DataTypes.DTAlbum_SinDTArtista;
import java.util.Date;
import java.util.List;

public interface IControlador {
    public abstract List<String>getNicknamesArtistas();
    public abstract List<String>getNicknamesClientes();
    
    public abstract void AltaGenero(String nombreGenero);
    public abstract void AltaArtista(Artista a);
    public abstract void AltaCliente(Cliente c);
    public abstract void AltaAlbum(DTAlbum_SinDTArtista dataAlbum);

    public abstract boolean ExisteNickName(String nickname);
    public abstract boolean ExisteEmail(String email);
    
    public abstract void cargarDatosDePrueba();
    
}
