/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.frd.bancogrupo2.rest.services;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import utn.frd.bancogrupo2.entity.Cuentas;
import utn.frd.bancogrupo2.session.CuentasFacade;
import utn.frd.bancogrupo2.entity.Clientes;
import java.sql.SQLException;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import org.json.JSONException;
import org.json.JSONObject;


/**
 *
 * @author ads
 */

@Path("/cuentas")
public class CuentaRest {
    @EJB
    private CuentasFacade ejbCuentaFacade;
    
    //obtener todas las entidades
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Cuentas> findAll(){
        return ejbCuentaFacade.findAll();
    }
    
    //crear entidades
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Cuentas cuenta){
        ejbCuentaFacade.create(cuenta);
    }
    
    //encontrar cuenta por ID
    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Cuentas findById(@PathParam("id")int id){
        return ejbCuentaFacade.find(id);
    }
    
    //encontrar un cuenta segun el id del cliente
    @GET
    @Path("/cliente/{idCliente}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Cuentas> findByIdCliente(@PathParam("idCliente")int id){
        return ejbCuentaFacade.cuentasidCliente(id);
    }
    
    @GET
    @Path("/{idCuenta}/saldo")
    @Produces({MediaType.APPLICATION_JSON})
    public Object getSaldos(@PathParam("idCuenta")int id){
        return ejbCuentaFacade.findSaldo(id);
    }
    
    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/{id}")
    public void edit(@PathParam("id")int id, Cuentas cuenta){
        ejbCuentaFacade.edit(cuenta);
    }
    
}

