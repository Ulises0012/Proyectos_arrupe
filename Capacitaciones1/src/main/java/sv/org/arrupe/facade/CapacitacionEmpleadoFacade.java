/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.org.arrupe.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sv.org.arrupe.model.CapacitacionEmpleado;

/**
 *
 * @author ulise
 */
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
    
}
