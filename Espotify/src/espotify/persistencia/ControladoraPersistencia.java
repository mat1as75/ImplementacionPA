/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package espotify.persistencia;

import espotify.logica.Artista;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ControladoraPersistencia {
    
    UsuarioJpaController usuJpa = new UsuarioJpaController();
    ArtistaJpaController artJpa = new ArtistaJpaController();
    ClienteJpaController cliJpa = new ClienteJpaController();
    GeneroJpaController genJpa = new GeneroJpaController();
    AlbumJpaController albJpa = new AlbumJpaController();
    ListaParticularJpaController lpartJpa = new ListaParticularJpaController();
    ListaPorDefectoJpaController lxdefcJpa = new ListaPorDefectoJpaController();
    ListaReproduccionJpaController lreprodccJpa = new ListaReproduccionJpaController();
    TemaJpaController temaJpa = new TemaJpaController();
    TemaConRutaJpaController temaconrutaJpa = new TemaConRutaJpaController();
    TemaConURLJpaController temaurlJpa = new TemaConURLJpaController();

    public ArrayList<Artista> getArtistas() {
        
        List<Artista> artistasL = artJpa.findArtistaEntities();
        ArrayList<Artista> artistasAL = new ArrayList<Artista>(artistasL);
        
        return artistasAL;
    }
}
