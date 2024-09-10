/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package espotify.logica;

import espotify.DataTypes.DTCliente;
import espotify.DataTypes.DTDatosArtista;
import espotify.DataTypes.DTDatosCliente;
import espotify.DataTypes.DTListaReproduccion;
import java.util.Date;
import java.util.List;

/**
 *
 * @author tecnologo
 */
public interface IControlador {
    public abstract List<String>getNicknamesArtistas();
    public abstract List<String>getNicknamesClientes();
    public abstract void AltaGenero(String nombreGenero);
    public abstract void AltaArtista(Artista a);
    public abstract void AltaCliente(Cliente c);

    public abstract DTDatosArtista ConsultarPerfilArtista(String nicknameArtista);
    public abstract DTDatosCliente ConsultarPerfilCliente(String nicknameCliente);

    public abstract boolean ExisteNickName(String nickname);
    public abstract boolean ExisteEmail(String email);
    public abstract boolean existeNombreLista(String nombreLista);
    public abstract void setSeguidorSeguido(String Seguidor, String Seguido);
    public abstract void CrearListaPorDefecto(String nombreLista, String fotoLista, Genero genero);
    public abstract void CrearListaParticular(String nombreLista, String fotoLista, String nicknameCliente, boolean esPrivada);
    //public abstract DTListaReproduccion ConsultarListaReproduccion(String tipoDeLista, String op);


    
}
