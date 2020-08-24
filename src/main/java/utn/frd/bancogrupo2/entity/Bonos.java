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
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ads
 */
@Entity
@Table(name = "Bonos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bonos.findAll", query = "SELECT b FROM Bonos b"),
    @NamedQuery(name = "Bonos.findByIdBono", query = "SELECT b FROM Bonos b WHERE b.idBono = :idBono"),
    @NamedQuery(name = "Bonos.findByDescripcion", query = "SELECT b FROM Bonos b WHERE b.descripcion = :descripcion"),
    @NamedQuery(name = "Bonos.findByMontoInvertido", query = "SELECT b FROM Bonos b WHERE b.montoInvertido = :montoInvertido"),
    @NamedQuery(name = "Bonos.findByFechaDePago", query = "SELECT b FROM Bonos b WHERE b.fechaDePago = :fechaDePago"),
    @NamedQuery(name = "Bonos.findByInteres", query = "SELECT b FROM Bonos b WHERE b.interes = :interes"),
    @NamedQuery(name = "Bonos.findByImpuesto", query = "SELECT b FROM Bonos b WHERE b.impuesto = :impuesto")})
public class Bonos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_bono")
    private Integer idBono;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 16777215)
    @Column(name = "id_movimiento")
    private String idMovimiento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 225)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "montoInvertido")
    private double montoInvertido;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechaDePago")
    @Temporal(TemporalType.DATE)
    private Date fechaDePago;
    @Basic(optional = false)
    @NotNull
    @Column(name = "interes")
    private int interes;
    @Basic(optional = false)
    @NotNull
    @Column(name = "impuesto")
    private double impuesto;

    public Bonos() {
    }

    public Bonos(Integer idBono) {
        this.idBono = idBono;
    }

    public Bonos(Integer idBono, String idMovimiento, String descripcion, double montoInvertido, Date fechaDePago, int interes, double impuesto) {
        this.idBono = idBono;
        this.idMovimiento = idMovimiento;
        this.descripcion = descripcion;
        this.montoInvertido = montoInvertido;
        this.fechaDePago = fechaDePago;
        this.interes = interes;
        this.impuesto = impuesto;
    }

    public Integer getIdBono() {
        return idBono;
    }

    public void setIdBono(Integer idBono) {
        this.idBono = idBono;
    }

    public String getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(String idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getMontoInvertido() {
        return montoInvertido;
    }

    public void setMontoInvertido(double montoInvertido) {
        this.montoInvertido = montoInvertido;
    }

    public Date getFechaDePago() {
        return fechaDePago;
    }

    public void setFechaDePago(Date fechaDePago) {
        this.fechaDePago = fechaDePago;
    }

    public int getInteres() {
        return interes;
    }

    public void setInteres(int interes) {
        this.interes = interes;
    }

    public double getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(double impuesto) {
        this.impuesto = impuesto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBono != null ? idBono.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bonos)) {
            return false;
        }
        Bonos other = (Bonos) object;
        if ((this.idBono == null && other.idBono != null) || (this.idBono != null && !this.idBono.equals(other.idBono))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "utn.frd.bancogrupo2.entity.Bonos[ idBono=" + idBono + " ]";
    }
    
}
