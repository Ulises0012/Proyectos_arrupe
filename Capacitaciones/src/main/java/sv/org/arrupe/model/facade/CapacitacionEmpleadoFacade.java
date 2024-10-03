/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.org.arrupe.model.facade;

import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import sv.org.arrupe.model.CapacitacionEmpleado;
import sv.org.arrupe.model.Capacitaciones;
import sv.org.arrupe.model.Empleado;
import sv.org.arrupe.util.JSFUtil;


@Stateless
public class CapacitacionEmpleadoFacade extends AbstractFacade<CapacitacionEmpleado> {

    @PersistenceContext(unitName = "CapacitacionesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CapacitacionEmpleadoFacade() {
        super(CapacitacionEmpleado.class);
    }
    
    /* Inicio de modificaciones*/
    /** 
     * Para agregar un listado de empleados a una capacitacion. Recibe un listado 
     * de códigos de empleados y verifica que previamente no haya sido 
     * ingresado en la capacitacion y lo inserta en la tabla
     * capacitacion_empleado
     * 
     * @param codigosEmpleados
     * @param idCapacitacion
     */
    
    public void agregarEmpleadosACapacitacion(String [] codigosEmpleados, int idCapacitacion){
        for (String codigo: codigosEmpleados){
            Query query 
                    = em.createQuery("SELECT count(CE) from capacitacionesEmpleado"
                    + "CE where CE.codEmpleado.codEmpleado = : codEmpleado"
                    + " and CE.idCapacitacion.idCapacitacion = :idCapacitacion");
            
            query.setParameter("codEmpleado", codigo);
            query.setParameter("idCapacitacion", idCapacitacion);
            
            Long validacionEmpleadoInscrito = (Long) query.getSingleResult();
            
            if (validacionEmpleadoInscrito > 0){
                JSFUtil.addGlobalErrorMessage("El empleado ya ha sido registrado:" + codigo);
                continue;
            }
            
            Empleado empleado = new Empleado();
            empleado.setCodEmpleado(codigo);
            
            Capacitaciones capacitacion = new Capacitaciones();
            capacitacion.setIdCapacitacion(idCapacitacion);
            
            CapacitacionEmpleado capacitacionEmpleado = new CapacitacionEmpleado();
            capacitacionEmpleado.setCodEmpleado(empleado);
            capacitacionEmpleado.setIdCapacitacion(capacitacion);
            capacitacionEmpleado.setFechaInscripcion(new Date());
            
            this.create(capacitacionEmpleado);
        }
        JSFUtil.addGlobalMessage("Empleados agregados al curso.");
       }
    
}
