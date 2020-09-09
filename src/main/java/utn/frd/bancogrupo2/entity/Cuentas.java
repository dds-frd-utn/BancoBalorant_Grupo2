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
@Table(name = "Cuentas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cuentas.findAll", query = "SELECT c FROM Cuentas c"),
    @NamedQuery(name = "Cuentas.findByIdCuenta", query = "SELECT c FROM Cuentas c WHERE c.idCuenta = :idCuenta"),
    @NamedQuery(name = "Cuentas.findByIdCliente", query = "SELECT c FROM Cuentas c WHERE c.idCliente = :idCliente"),
    @NamedQuery(name = "Cuentas.findBySaldo", query = "SELECT c FROM Cuentas c WHERE c.saldo = :saldo"),
    @NamedQuery(name = "Cuentas.updateSaldo", query = "UPDATE Cuentas as c set c.saldo = :saldo WHERE c.idCuenta = :idCuenta"),
    @NamedQuery(name = "Cuentas.findSaldo", query = "SELECT c.saldo FROM Cuentas c WHERE c.idCuenta = :idCuenta"),
    @NamedQuery(name = "Cuentas.findByFechaCreacion", query = "SELECT c FROM Cuentas c WHERE c.fechaCreacion = :fechaCreacion")})
public class Cuentas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_cuenta")
    private Integer idCuenta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_cliente")
    private int idCliente;
    @Basic(optional = false)
    @NotNull
    @Column(name = "saldo")
    private double saldo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;

    public Cuentas() {
    }

    public Cuentas(Integer idCuenta) {
        this.idCuenta = idCuenta;
    }

    public Cuentas(Integer idCuenta, int idCliente, double saldo, Date fechaCreacion) {
        this.idCuenta = idCuenta;
        this.idCliente = idCliente;
        this.saldo = saldo;
        this.fechaCreacion = fechaCreacion;
    }

    public Integer getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(Integer idCuenta) {
        this.idCuenta = idCuenta;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCuenta != null ? idCuenta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cuentas)) {
            return false;
        }
        Cuentas other = (Cuentas) object;
        if ((this.idCuenta == null && other.idCuenta != null) || (this.idCuenta != null && !this.idCuenta.equals(other.idCuenta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "utn.frd.bancogrupo2.entity.Cuentas[ idCuenta=" + idCuenta + " ]";
    }
    
}
