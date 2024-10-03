package sv.org.arrupe.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sv.org.arrupe.model.Capacitaciones;
import sv.org.arrupe.model.Empleado;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-08-20T15:56:41")
@StaticMetamodel(CapacitacionEmpleado.class)
public class CapacitacionEmpleado_ { 

    public static volatile SingularAttribute<CapacitacionEmpleado, Capacitaciones> idCapacitacion;
    public static volatile SingularAttribute<CapacitacionEmpleado, Integer> idCapacitacionEmpleado;
    public static volatile SingularAttribute<CapacitacionEmpleado, Date> fechaInscripcion;
    public static volatile SingularAttribute<CapacitacionEmpleado, Empleado> codEmpleado;

}