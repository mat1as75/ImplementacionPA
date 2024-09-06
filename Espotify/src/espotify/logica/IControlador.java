/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package espotify.logica;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author tecnologo
 */
public interface IControlador {
    
    public abstract void AltaPerfil(String tipoUsuario, String nickname, String nombreU, String apellidoU, String email, Date fecNac, String fotoPerfil, String biografia, String dirSitioWeb);

    public abstract void AltaGenero(String nombreGenero);
    
    
    public abstract List<Artista> ObtenerListaArtistas();
    public abstract void ConsultarPerfilArtista();
    
    
}
