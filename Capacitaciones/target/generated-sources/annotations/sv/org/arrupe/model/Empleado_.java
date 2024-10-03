package sv.org.arrupe.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sv.org.arrupe.model.CapacitacionEmpleado;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-08-20T15:56:41")
@StaticMetamodel(Empleado.class)
public class Empleado_ { 

    public static volatile SingularAttribute<Empleado, String> apellido;
    public static volatile CollectionAttribute<Empleado, CapacitacionEmpleado> capacitacionEmpleadoCollection;
    public static volatile SingularAttribute<Empleado, String> telefono;
    public static volatile SingularAttribute<Empleado, String> nombre;
    public static volatile SingularAttribute<Empleado, String> codEmpleado;

}