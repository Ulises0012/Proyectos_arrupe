package sv.org.arrupe.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sv.org.arrupe.model.CapacitacionEmpleado;
import sv.org.arrupe.model.Categorias;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-08-20T15:56:41")
@StaticMetamodel(Capacitaciones.class)
public class Capacitaciones_ { 

    public static volatile SingularAttribute<Capacitaciones, String> descripcion;
    public static volatile SingularAttribute<Capacitaciones, Date> fecha;
    public static volatile SingularAttribute<Capacitaciones, Integer> idCapacitacion;
    public static volatile CollectionAttribute<Capacitaciones, CapacitacionEmpleado> capacitacionEmpleadoCollection;
    public static volatile SingularAttribute<Capacitaciones, Categorias> idCategoria;

}