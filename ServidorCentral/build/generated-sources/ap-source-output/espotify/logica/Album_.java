package espotify.logica;

import espotify.logica.Artista;
import espotify.logica.Genero;
import espotify.logica.Tema;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-10-16T20:58:01", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Album.class)
public class Album_ { 

    public static volatile SingularAttribute<Album, String> fotoAlbum;
    public static volatile ListAttribute<Album, Genero> misGeneros;
    public static volatile SingularAttribute<Album, Integer> anioCreacion;
    public static volatile SingularAttribute<Album, Long> idAlbum;
    public static volatile ListAttribute<Album, Tema> misTemas;
    public static volatile SingularAttribute<Album, Artista> miArtista;
    public static volatile SingularAttribute<Album, String> nombreAlbum;

}