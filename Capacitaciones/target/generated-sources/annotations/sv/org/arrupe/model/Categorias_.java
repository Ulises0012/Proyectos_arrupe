package sv.org.arrupe.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sv.org.arrupe.model.Capacitaciones;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-08-20T15:56:41")
@StaticMetamodel(Categorias.class)
public class Categorias_ { 

    public static volatile SingularAttribute<Categorias, String> descripcion;
    public static volatile CollectionAttribute<Categorias, Capacitaciones> capacitacionesCollection;
    public static volatile SingularAttribute<Categorias, Integer> idCategoria;

}