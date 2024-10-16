package espotify.logica;

import espotify.logica.Album;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-10-16T19:56:38", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Artista.class)
public class Artista_ extends Usuario_ {

    public static volatile SingularAttribute<Artista, String> dirSitioWeb;
    public static volatile ListAttribute<Artista, Album> misAlbumesPublicados;
    public static volatile SingularAttribute<Artista, String> biografia;

}