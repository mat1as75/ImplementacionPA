package espotify.logica;

import espotify.logica.Tema;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-10-11T19:53:08", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(ListaReproduccion.class)
public abstract class ListaReproduccion_ { 

    public static volatile SingularAttribute<ListaReproduccion, String> nombreLista;
    public static volatile SingularAttribute<ListaReproduccion, String> fotoLista;
    public static volatile ListAttribute<ListaReproduccion, Tema> misTemas;

}