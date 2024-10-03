/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.org.arrupe.model;

import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author ulise
 */
@Entity
@Table(name = "capacitaciones")
@NamedQueries({
    @NamedQuery(name = "Capacitaciones.findAll", query = "SELECT c FROM Capacitaciones c"),
    @NamedQuery(name = "Capacitaciones.findByIdCapacitacion", query = "SELECT c FROM Capacitaciones c WHERE c.idCapacitacion = :idCapacitacion"),
    @NamedQuery(name = "Capacitaciones.findByDescripcion", query = "SELECT c FROM Capacitaciones c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "Capacitaciones.findByFecha", query = "SELECT c FROM Capacitaciones c WHERE c.fecha = :fecha")})
public class Capacitaciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_capacitacion")
    private Integer idCapacitacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 400)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @JoinColumn(name = "id_categoria", referencedColumnName = "id_categoria")
    @ManyToOne
    private Categorias idCategoria;
    @OneToMany(mappedBy = "idCapacitacion")
    private Collection<CapacitacionEmpleado> capacitacionEmpleadoCollection;

    public Capacitaciones() {
    }

    public Capacitaciones(Integer idCapacitacion) {
        this.idCapacitacion = idCapacitacion;
    }

    public Capacitaciones(Integer idCapacitacion, String descripcion, Date fecha) {
        this.idCapacitacion = idCapacitacion;
        this.descripcion = descripcion;
        this.fecha = fecha;
    }

    public Integer getIdCapacitacion() {
        return idCapacitacion;
    }

    public void setIdCapacitacion(Integer idCapacitacion) {
        this.idCapacitacion = idCapacitacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Categorias getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Categorias idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Collection<CapacitacionEmpleado> getCapacitacionEmpleadoCollection() {
        return capacitacionEmpleadoCollection;
    }

    public void setCapacitacionEmpleadoCollection(Collection<CapacitacionEmpleado> capacitacionEmpleadoCollection) {
        this.capacitacionEmpleadoCollection = capacitacionEmpleadoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCapacitacion != null ? idCapacitacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Capacitaciones)) {
            return false;
        }
        Capacitaciones other = (Capacitaciones) object;
        if ((this.idCapacitacion == null && other.idCapacitacion != null) || (this.idCapacitacion != null && !this.idCapacitacion.equals(other.idCapacitacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sv.org.arrupe.capacitaciones1.Capacitaciones[ idCapacitacion=" + idCapacitacion + " ]";
    }
    
}
