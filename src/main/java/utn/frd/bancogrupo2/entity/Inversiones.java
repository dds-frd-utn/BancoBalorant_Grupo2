/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.frd.bancogrupo2.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dave-
 */
@Entity
@Table(name = "Inversiones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Inversiones.findAll", query = "SELECT i FROM Inversiones i"),
    @NamedQuery(name = "Inversiones.findByIdInversion", query = "SELECT i FROM Inversiones i WHERE i.idInversion = :idInversion"),
    @NamedQuery(name = "Inversiones.findByIdCuenta", query = "SELECT i FROM Inversiones i WHERE i.idCuenta = :idCuenta"),
    @NamedQuery(name = "Inversiones.findByIdBono", query = "SELECT i FROM Inversiones i WHERE i.idBono = :idBono"),
    @NamedQuery(name = "Inversiones.findByFechaDeInicio", query = "SELECT i FROM Inversiones i WHERE i.fechaDeInicio = :fechaDeInicio"),
    @NamedQuery(name = "Inversiones.findByFechaDeVencimiento", query = "SELECT i FROM Inversiones i WHERE i.fechaDeVencimiento = :fechaDeVencimiento")})
public class Inversiones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_inversion")
    private Integer idInversion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_cuenta")
    private int idCuenta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_bono")
    private int idBono;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_de_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaDeInicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_de_vencimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaDeVencimiento;

    public Inversiones() {
    }

    public Inversiones(Integer idInversion) {
        this.idInversion = idInversion;
    }

    public Inversiones(Integer idInversion, int idCuenta, int idBono, Date fechaDeInicio, Date fechaDeVencimiento) {
        this.idInversion = idInversion;
        this.idCuenta = idCuenta;
        this.idBono = idBono;
        this.fechaDeInicio = fechaDeInicio;
        this.fechaDeVencimiento = fechaDeVencimiento;
    }

    public Integer getIdInversion() {
        return idInversion;
    }

    public void setIdInversion(Integer idInversion) {
        this.idInversion = idInversion;
    }

    public int getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
    }

    public int getIdBono() {
        return idBono;
    }

    public void setIdBono(int idBono) {
        this.idBono = idBono;
    }

    public Date getFechaDeInicio() {
        return fechaDeInicio;
    }

    public void setFechaDeInicio(Date fechaDeInicio) {
        this.fechaDeInicio = fechaDeInicio;
    }

    public Date getFechaDeVencimiento() {
        return fechaDeVencimiento;
    }

    public void setFechaDeVencimiento(Date fechaDeVencimiento) {
        this.fechaDeVencimiento = fechaDeVencimiento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idInversion != null ? idInversion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Inversiones)) {
            return false;
        }
        Inversiones other = (Inversiones) object;
        if ((this.idInversion == null && other.idInversion != null) || (this.idInversion != null && !this.idInversion.equals(other.idInversion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "utn.frd.bancogrupo2.entity.Inversiones[ idInversion=" + idInversion + " ]";
    }
    
}
