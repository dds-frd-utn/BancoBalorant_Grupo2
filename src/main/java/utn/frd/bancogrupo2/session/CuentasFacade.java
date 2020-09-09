/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.frd.bancogrupo2.session;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import utn.frd.bancogrupo2.entity.Cuentas;

/**
 *
 * @author ads
 */
@Stateless
public class CuentasFacade extends AbstractFacade<Cuentas> {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    public CuentasFacade() {
        super(Cuentas.class);
    }
    
     
    public List<Cuentas> cuentasidCliente(int id) {
        return em.createNamedQuery("Cuentas.findByIdCliente")
                .setParameter("idCliente", id)
                .getResultList();
    }
    
    public double findSaldo(int id){
        return (double) em.createNamedQuery("Cuentas.findSaldo")
                .setParameter("idCuenta", id)
                .getSingleResult();
    }
    
    public int updateSaldo(double saldo, int id){
         return em.createNamedQuery("Cuentas.updateSaldo")
                .setParameter("saldo", saldo)
                .setParameter("idCuenta", id)
                .executeUpdate();
        
    }
    
    
}
