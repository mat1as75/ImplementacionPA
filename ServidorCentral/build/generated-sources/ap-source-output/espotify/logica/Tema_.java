package espotify.logica;

import espotify.logica.Album;
import espotify.logica.ListaReproduccion;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-10-20T18:22:47", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Tema.class)
public abstract class Tema_ { 

    public static volatile SingularAttribute<Tema, String> nombreTema;
    public static volatile SingularAttribute<Tema, Long> idTema;
    public static volatile SingularAttribute<Tema, Integer> posicionEnAlbum;
    public static volatile SingularAttribute<Tema, Integer> duracionSegundos;
    public static volatile ListAttribute<Tema, ListaReproduccion> misReproducciones;
    public static volatile SingularAttribute<Tema, Album> miAlbum;

}