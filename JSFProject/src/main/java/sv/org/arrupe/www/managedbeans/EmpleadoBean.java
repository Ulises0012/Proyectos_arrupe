/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.org.arrupe.www.managedbeans;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * ManagedBean para manejar empleados.
 */
@Named
@SessionScoped
public class EmpleadoBean implements Serializable {

    private List<Empleado> empleados;
    private Empleado empleadoSeleccionado;

    public EmpleadoBean() {
        empleados = new ArrayList<>();
        empleados.add(new Empleado("Daniel Souza", "001", "Desarrollo de Software", 2500.0));
        empleados.add(new Empleado("Jimena Castillo", "002", "Recursos Humanos", 2300.0));
        empleados.add(new Empleado("Carla Valle", "003", "Control de la calidad", 2200.0));
        empleados.add(new Empleado("Susana Castillo", "004", "IT", 2100.0));
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }

    public Empleado getEmpleadoSeleccionado() {
        return empleadoSeleccionado;
    }

    public void setEmpleadoSeleccionado(Empleado empleadoSeleccionado) {
        this.empleadoSeleccionado = empleadoSeleccionado;
    }

    public void editarEmpleado(Empleado empleado) {
        this.empleadoSeleccionado = empleado;
    }

    public void guardarEmpleado() {
        empleadoSeleccionado = null;
    }

    public void eliminarEmpleado(Empleado empleado) {
        empleados.remove(empleado);
    }
}



