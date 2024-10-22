package espotify.logica;

import espotify.logica.Usuario;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-10-08T00:21:55", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Usuario.class)
public abstract class Usuario_ { 

    public static volatile SingularAttribute<Usuario, String> fotoPerfil;
    public static volatile SingularAttribute<Usuario, String> apellidoUsuario;
    public static volatile SingularAttribute<Usuario, String> contrasenaUsuario;
    public static volatile ListAttribute<Usuario, Usuario> misSeguidores;
    public static volatile SingularAttribute<Usuario, Date> fecNac;
    public static volatile SingularAttribute<Usuario, String> nickname;
    public static volatile SingularAttribute<Usuario, String> nombreUsuario;
    public static volatile SingularAttribute<Usuario, String> email;

}