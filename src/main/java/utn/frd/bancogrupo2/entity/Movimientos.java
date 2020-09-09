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
 * @author ads
 */
@Entity
@Table(name = "Movimientos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Movimientos.findAll", query = "SELECT m FROM Movimientos m"),
    @NamedQuery(name = "Movimientos.findByIdMovimiento", query = "SELECT m FROM Movimientos m WHERE m.idMovimiento = :idMovimiento"),
    @NamedQuery(name = "Movimientos.findByTipoMovimiento", query = "SELECT m FROM Movimientos m WHERE m.tipoMovimiento = :tipoMovimiento"),
    @NamedQuery(name = "Movimientos.findByIdCuenta", query = "SELECT m FROM Movimientos m WHERE m.idCuenta = :idCuenta"),
    @NamedQuery(name = "Movimientos.findByIdCuentaDestino", query = "SELECT m FROM Movimientos m WHERE m.idCuentaDestino = :idCuentaDestino"),
    @NamedQuery(name = "Movimientos.findByImporte", query = "SELECT m FROM Movimientos m WHERE m.importe = :importe"),
    @NamedQuery(name = "Movimientos.findByFechaMovimiento", query = "SELECT m FROM Movimientos m WHERE m.fechaMovimiento = :fechaMovimiento"),
    @NamedQuery(name = "Movimientos.findByEstado", query = "SELECT m FROM Movimientos m WHERE m.estado = :estado")})
public class Movimientos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_movimiento")
    private Integer idMovimiento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo_movimiento")
    private int tipoMovimiento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_cuenta")
    private int idCuenta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_cuenta_destino")
    private int idCuentaDestino;
    @Basic(optional = false)
    @NotNull
    @Column(name = "importe")
    private double importe;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_movimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaMovimiento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private int estado;

    public Movimientos() {
    }

    public Movimientos(Integer idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public Movimientos(Integer idMovimiento, int tipoMovimiento, int idCuenta, int idCuentaDestino, double importe, Date fechaMovimiento, int estado) {
        this.idMovimiento = idMovimiento;
        this.tipoMovimiento = tipoMovimiento;
        this.idCuenta = idCuenta;
        this.idCuentaDestino = idCuentaDestino;
        this.importe = importe;
        this.fechaMovimiento = fechaMovimiento;
        this.estado = estado;
    }

    public Integer getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(Integer idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public int getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(int tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public int getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
    }

    public int getIdCuentaDestino() {
        return idCuentaDestino;
    }

    public void setIdCuentaDestino(int idCuentaDestino) {
        this.idCuentaDestino = idCuentaDestino;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public Date getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(Date fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMovimiento != null ? idMovimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Movimientos)) {
            return false;
        }
        Movimientos other = (Movimientos) object;
        if ((this.idMovimiento == null && other.idMovimiento != null) || (this.idMovimiento != null && !this.idMovimiento.equals(other.idMovimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "utn.frd.bancogrupo2.entity.Movimientos[ idMovimiento=" + idMovimiento + " ]";
    }
    
}
