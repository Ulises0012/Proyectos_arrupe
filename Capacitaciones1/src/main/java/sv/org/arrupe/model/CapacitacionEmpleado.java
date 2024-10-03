/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.org.arrupe.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author ulise
 */
@Entity
@Table(name = "capacitacion_empleado")
@NamedQueries({
    @NamedQuery(name = "CapacitacionEmpleado.findAll", query = "SELECT c FROM CapacitacionEmpleado c"),
    @NamedQuery(name = "CapacitacionEmpleado.findByIdCapacitacionEmpleado", query = "SELECT c FROM CapacitacionEmpleado c WHERE c.idCapacitacionEmpleado = :idCapacitacionEmpleado"),
    @NamedQuery(name = "CapacitacionEmpleado.findByFechaInscripcion", query = "SELECT c FROM CapacitacionEmpleado c WHERE c.fechaInscripcion = :fechaInscripcion")})
public class CapacitacionEmpleado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_capacitacion_empleado")
    private Integer idCapacitacionEmpleado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_inscripcion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInscripcion;
    @JoinColumn(name = "id_capacitacion", referencedColumnName = "id_capacitacion")
    @ManyToOne
    private Capacitaciones idCapacitacion;
    @JoinColumn(name = "cod_empleado", referencedColumnName = "cod_empleado")
    @ManyToOne
    private Empleado codEmpleado;

    public CapacitacionEmpleado() {
    }

    public CapacitacionEmpleado(Integer idCapacitacionEmpleado) {
        this.idCapacitacionEmpleado = idCapacitacionEmpleado;
    }

    public CapacitacionEmpleado(Integer idCapacitacionEmpleado, Date fechaInscripcion) {
        this.idCapacitacionEmpleado = idCapacitacionEmpleado;
        this.fechaInscripcion = fechaInscripcion;
    }

    public Integer getIdCapacitacionEmpleado() {
        return idCapacitacionEmpleado;
    }

    public void setIdCapacitacionEmpleado(Integer idCapacitacionEmpleado) {
        this.idCapacitacionEmpleado = idCapacitacionEmpleado;
    }

    public Date getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(Date fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public Capacitaciones getIdCapacitacion() {
        return idCapacitacion;
    }

    public void setIdCapacitacion(Capacitaciones idCapacitacion) {
        this.idCapacitacion = idCapacitacion;
    }

    public Empleado getCodEmpleado() {
        return codEmpleado;
    }

    public void setCodEmpleado(Empleado codEmpleado) {
        this.codEmpleado = codEmpleado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCapacitacionEmpleado != null ? idCapacitacionEmpleado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CapacitacionEmpleado)) {
            return false;
        }
        CapacitacionEmpleado other = (CapacitacionEmpleado) object;
        if ((this.idCapacitacionEmpleado == null && other.idCapacitacionEmpleado != null) || (this.idCapacitacionEmpleado != null && !this.idCapacitacionEmpleado.equals(other.idCapacitacionEmpleado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.org.arrupe.capacitaciones1.CapacitacionEmpleado[ idCapacitacionEmpleado=" + idCapacitacionEmpleado + " ]";
    }
    
}
