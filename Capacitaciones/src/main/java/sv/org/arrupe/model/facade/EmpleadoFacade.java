/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.org.arrupe.model.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import sv.org.arrupe.model.Empleado;


@Stateless
public class EmpleadoFacade extends AbstractFacade<Empleado> {

    @PersistenceContext(unitName = "CapacitacionesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmpleadoFacade() {
        super(Empleado.class);
    }
    
    /* Inicio de las modificaciones */
    /** 
     *  Listado de empleados por capacitación 
     * 
     * @param idCapacitacion
     * @return Lista de empleados
     */
    public List<Empleado> empleadoPorCapacitacion(int idCapacitacion) {
        Query query = em.createQuery("SELECT c.codEmpleado FROM CapacitacionEmpleado c WHERE c.idCapacitacion.idCapacitacion = :idCapacitacion");
        query.setParameter("idCapacitacion", idCapacitacion);
        return query.getResultList();
    }
    /* Fin de las modificaciones */
}
