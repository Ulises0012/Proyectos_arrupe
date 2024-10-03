/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.org.arrupe.controller;

/**
 *
 * @author Wendita
 */
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import sv.org.arrupe.model.Capacitaciones;
import sv.org.arrupe.model.Empleado;
import sv.org.arrupe.model.facade.CapacitacionEmpleadoFacade;
import sv.org.arrupe.model.facade.CapacitacionesFacade;
import sv.org.arrupe.model.facade.EmpleadoFacade;

@ManagedBean
@RequestScoped

public class TrainingMB {
    
    private String codigosEmpleadoSelecionado[];
    private Integer idCapacitacion = 0;
    
    @EJB
    CapacitacionesFacade capacitacionesFacade;
    @EJB 
    CapacitacionEmpleadoFacade capacitacionesEmpleadoFacade;
    @EJB
    EmpleadoFacade empleadoFacade;
    
    public List<Capacitaciones> getCapacitacionesList(){
        return capacitacionesFacade.findAll();
    }
    public List<Empleado> getListaEmpleadosPorCapacitacion(){
        return empleadoFacade.empleadoPorCapacitacion(idCapacitacion);
    }
    public String registrarEmpleado(){
        capacitacionesEmpleadoFacade.agregarEmpleadosACapacitacion(codigosEmpleadoSelecionado,
                idCapacitacion);
        return "";
    }
    
    public String[] getCodigosEmpleadosSeleccionado(){
        return codigosEmpleadoSelecionado;
    }
    public void setCodigosEmpleadosSeleccionado(String[] codigosEmpleadoSeleccionado){
        this.codigosEmpleadoSelecionado = codigosEmpleadoSeleccionado;
    }
    
    public Integer getIdCapacitacion(){
        return idCapacitacion;
    }
  
    public void setIdCapacitacion(Integer idCapacitacion){
        this.idCapacitacion = idCapacitacion;
    }
    
    
    
}
