/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.org.arrupe.controller;

import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import sv.org.arrupe.model.Capacitaciones;
import sv.org.arrupe.model.Categorias;
import sv.org.arrupe.model.facade.CapacitacionesFacade;
import sv.org.arrupe.model.facade.CategoriasFacade;
import sv.org.arrupe.util.JSFUtil;

/**
 *
 * @author Wendita
 */

@ManagedBean
@RequestScoped
public class CapacitacionesMB {
    @EJB
    CapacitacionesFacade capacitacionesFacade;
    
    @EJB
    CategoriasFacade categoriasFacade;
    
    private Capacitaciones capacitaciones;
    private Categorias categorias;
    
    public CapacitacionesMB(){
        capacitaciones = new Capacitaciones();
        categorias = new Categorias();
        capacitaciones.setIdCategoria(categorias);
    }
    
    public List<Categorias> getListaCategorias(){
        return categoriasFacade.findAll();
    }
    
    public List<Capacitaciones> getListaCapacitaciones(){
        return capacitacionesFacade.findAll();
    }
    
    public String guardarCapacitacion(){
        capacitacionesFacade.create(capacitaciones);
        capacitaciones = new Capacitaciones();
        capacitaciones.setIdCategoria(categorias);
        JSFUtil.addGlobalMessage("Capacitación Agregada");
        return "";
    }
    
    public String modificarCapacitacion(){
        capacitacionesFacade.edit(capacitaciones);
        return "";
    }
    /**
     * 
     * @return the capacitaciones 
     */
    public Capacitaciones getCapacitaciones(){
        return capacitaciones;
    }
    /**
     * @param capacitaciones the capacitaciones to set
     */
    
    public void setCapacitaciones(Capacitaciones capacitaciones){
        this.capacitaciones = capacitaciones;
    }
}
