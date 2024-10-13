package espotify.logica;

import espotify.logica.Cliente;
import espotify.logica.Suscripcion.EstadoSuscripcion;
import espotify.logica.Suscripcion.TipoSuscripcion;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-10-11T19:53:08", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Suscripcion.class)
public class Suscripcion_ { 

    public static volatile SingularAttribute<Suscripcion, Long> idSuscripcion;
    public static volatile SingularAttribute<Suscripcion, TipoSuscripcion> tipoSuscripcion;
    public static volatile SingularAttribute<Suscripcion, Date> fechaSuscripcion;
    public static volatile SingularAttribute<Suscripcion, Cliente> miCliente;
    public static volatile SingularAttribute<Suscripcion, EstadoSuscripcion> estadoSuscripcion;

}