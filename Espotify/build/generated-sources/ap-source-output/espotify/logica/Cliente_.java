package espotify.logica;

import espotify.logica.Album;
import espotify.logica.ListaParticular;
import espotify.logica.ListaReproduccion;
import espotify.logica.Tema;
import espotify.logica.Usuario;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-09-20T01:14:13", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Cliente.class)
public class Cliente_ extends Usuario_ {

    public static volatile ListAttribute<Cliente, ListaReproduccion> misListasReproduccionFav;
    public static volatile ListAttribute<Cliente, Tema> misTemasFav;
    public static volatile ListAttribute<Cliente, Album> misAlbumesFav;
    public static volatile ListAttribute<Cliente, ListaParticular> misListasReproduccionCreadas;
    public static volatile ListAttribute<Cliente, Usuario> misSeguidos;

}