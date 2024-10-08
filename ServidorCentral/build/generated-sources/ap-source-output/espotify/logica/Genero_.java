package espotify.logica;

import espotify.logica.Album;
import espotify.logica.Genero;
import espotify.logica.ListaPorDefecto;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-10-07T22:01:44", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Genero.class)
public class Genero_ { 

    public static volatile ListAttribute<Genero, ListaPorDefecto> misListasParticulares;
    public static volatile SingularAttribute<Genero, Genero> miPadre;
    public static volatile SingularAttribute<Genero, String> nombreGenero;
    public static volatile ListAttribute<Genero, Album> misAlbumes;
    public static volatile ListAttribute<Genero, Genero> misGenerosHijos;

}