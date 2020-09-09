/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.frd.bancogrupo2.rest.services;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.json.JSONException;
import org.json.JSONObject;

import utn.frd.bancogrupo2.entity.Movimientos;
import utn.frd.bancogrupo2.session.MovimientosFacade;
import utn.frd.bancogrupo2.entity.Cuentas;
import utn.frd.bancogrupo2.session.CuentasFacade;
import utn.frd.bancogrupo2.entity.Inversiones;
import utn.frd.bancogrupo2.session.InversionesFacade;
import utn.frd.bancogrupo2.entity.Bonos;
import utn.frd.bancogrupo2.session.BonosFacade;

/**
 *
 * @author ads
 */
@Path("/movimientos")
public class MovimientoRest {
    public String enviarHttpRequest(String urlParam, String method, JSONObject jsonParam){
        try {
            URL url = new URL(urlParam);

            HttpURLConnection urlConnection = null;
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            if(jsonParam.length() >0){
                urlConnection.setFixedLengthStreamingMode(jsonParam.toString().getBytes().length);
            }
            urlConnection.setRequestProperty(
                                   "Content-Type", "application/json;charset=utf-8");
            urlConnection.setRequestProperty("X-Requested-With", "XMLHttpRequest");
            urlConnection.connect();
            if(jsonParam.length() >0){
                OutputStream os;
                os = new BufferedOutputStream(urlConnection.getOutputStream());
                os.write(jsonParam.toString().getBytes());
                os.flush();
            }
            StringBuilder sBuilder;
            InputStream inputStream;
            inputStream= urlConnection.getInputStream();
            
            BufferedReader bReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"), 5);
            sBuilder = new StringBuilder();
            String line = null;
            while ((line = bReader.readLine()) != null) {
                sBuilder.append(line + "\n");
            }
            String texto = sBuilder.toString();
            return texto;
        } catch (IOException e) {
           return e.getMessage();
        }
    }
    public String makePUTRequest(String urlParam, String method, JSONObject jsonParam){
        try {
            URL url = new URL(urlParam);

            HttpURLConnection urlConnection = null;
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            urlConnection.setRequestMethod("PUT");
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            if(jsonParam.length() >0){
                urlConnection.setFixedLengthStreamingMode(jsonParam.toString().getBytes().length);
            }
            urlConnection.setRequestProperty(
                                   "Content-Type", "application/json;charset=utf-8");
            urlConnection.setRequestProperty("X-Requested-With", "XMLHttpRequest");
            urlConnection.connect();
            if(jsonParam.length() >0){
                OutputStream os;
                os = new BufferedOutputStream(urlConnection.getOutputStream());
                os.write(jsonParam.toString().getBytes());
                os.flush();
            }
            StringBuilder sBuilder;
            InputStream inputStream;
            inputStream= urlConnection.getInputStream();
            
            BufferedReader bReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"), 5);
            sBuilder = new StringBuilder();
            String line = null;
            while ((line = bReader.readLine()) != null) {
                sBuilder.append(line + "\n");
            }
            String texto = sBuilder.toString();
            return texto;
        } catch (IOException e) {
           return e.getMessage();
        }
    }
    
    public String makePOSTRequest(String urlParam, String method, JSONObject jsonParam){
        try {
            URL url = new URL(urlParam);

            HttpURLConnection urlConnection = null;
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            if(jsonParam.length() >0){
                urlConnection.setFixedLengthStreamingMode(jsonParam.toString().getBytes().length);
            }
            urlConnection.setRequestProperty(
                                   "Content-Type", "application/json;charset=utf-8");
            urlConnection.setRequestProperty("X-Requested-With", "XMLHttpRequest");
            urlConnection.connect();
            if(jsonParam.length() >0){
                OutputStream os;
                os = new BufferedOutputStream(urlConnection.getOutputStream());
                os.write(jsonParam.toString().getBytes());
                os.flush();
            }
            StringBuilder sBuilder;
            InputStream inputStream;
            inputStream= urlConnection.getInputStream();
            
            BufferedReader bReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"), 5);
            sBuilder = new StringBuilder();
            String line = null;
            while ((line = bReader.readLine()) != null) {
                sBuilder.append(line + "\n");
            }
            String texto = sBuilder.toString();
            return texto;
        } catch (IOException e) {
           return e.getMessage();
        }
    }
    
    @EJB
    private MovimientosFacade ejbMovimientoFacade;
    @EJB
    private CuentasFacade ejbCuentaFacade;
    
    //obtener todas las entidades
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Movimientos> findAll(){
        return ejbMovimientoFacade.findAll();
    }
    
    //crear entidades
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Movimientos movimiento){
        ejbMovimientoFacade.create(movimiento);
    }
    
    //actualizar entidades
    
    
    
    @Path("/estado/{estado}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String findByEstado(@PathParam("estado") int estado) throws JSONException{
        try{
            Query query = ejbMovimientoFacade.getEntityManager().createQuery("SELECT c from Movimientos c WHERE c.estado = "+estado +" order by c.fechaMovimiento DESC");
            List<Movimientos> listMov = query.getResultList();
            
            JSONObject jsonArray = new JSONObject();
            JSONObject jsonElement;
            String movString;
            
            for(Movimientos unMov : listMov){
                jsonElement = new JSONObject()
                        .put("idMovimiento", unMov.getIdMovimiento())
                        .put("idCuenta", unMov.getIdCuenta())
                        .put("idCuentaDestino", unMov.getIdCuentaDestino())
                        .put("importe", unMov.getImporte())
                        .put("FechaMovimiento", unMov.getFechaMovimiento())
                        .put("estado", unMov.getEstado())
                        .put("tipoMovimiento",unMov.getTipoMovimiento())
                        ;
                jsonArray.put(String.valueOf(unMov.getIdMovimiento()),jsonElement);
            }
            
            return jsonArray.toString();

        }catch(JSONException e){
            return e.getMessage();
        }
    }
    
    
    @POST
    @Path("/realizar")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.TEXT_PLAIN})
    public String registrarMovimiento(String peticionInicial) throws JSONException, ParseException{
        JSONObject jsonPeticion = new JSONObject(peticionInicial);
        Date date = new Date();
        int id_cuenta = jsonPeticion.getInt("idCuenta");
        int cuenta_destino = jsonPeticion.getInt("idCuentaDestino");
        int tipo_movimiento = jsonPeticion.getInt("tipoMovimiento");
        double monto = (float) jsonPeticion.getDouble("monto");
        /*
        date fecha_vencimiento = jsonPeticion.getDate("fecha_vencimiento");
        date fecha_vencimiento = jsonPeticion.getDate("fecha_vencimiento");
        int id_bono = jsonPeticion.getInt("id_bono");
        string descripcion = jsonPeticion.getString("descripcion");
        int intereses = jsonPeticion.getInt("intereses");
        double impuesto = (float) jsonPeticion.getDouble("impuesto");
        date fechaDePago = jsonPeticion.getDate("fechaDePago");
        */
        //Declaro variables
        double porcImpuesto = 0.23f;
        double valorImpuesto = 0;
        String str = "";
        
        
        JSONObject jsonDevolver = new JSONObject();
        String respuesta = "";
        String respuestaOrigen = "";
        String respuestaDestino = "";
        if(monto > 0){
            try{
                if(tipo_movimiento == 2 || tipo_movimiento == 3){
                    //Si es compra-venta, agrego el valor del impuesto
                    JSONObject jsonParam = new JSONObject();
                    if(tipo_movimiento == 3){
                        valorImpuesto = monto * porcImpuesto / 100;
                    }
                    //Averiguo cual es el saldo actual de la cuenta origen.
                    respuestaOrigen = this.enviarHttpRequest("http://localhost:8080/bancogrupo2/rest/cuentas/"+id_cuenta,"GET",jsonParam);
                    respuestaDestino = this.enviarHttpRequest("http://localhost:8080/bancogrupo2/rest/cuentas/"+cuenta_destino,"GET",jsonParam);
                    double saldoDisponible = Double.parseDouble((new JSONObject(respuestaOrigen)).getString("saldo"));
                    double saldoDisponibleDest = Double.parseDouble((new JSONObject(respuestaDestino)).getString("saldo"));
                    double importeFinal = monto + valorImpuesto;
                    //int importeFinalInt = (int)importeFinal;
                    
                    
                    if (saldoDisponible >= importeFinal){
                        //Consulto si la cuenta destino pertenece a mi banco
                    //    String cuentaDestino = this.enviarHttpRequest("http://localhost:8080/bancogrupo2/rest/cuentas/"+cuenta_destino,"GET",new JSONObject());
                    //    String flagErrorCtaDestino = new JSONObject(cuentaDestino).getString("flag_error");
                        //0:la encontró, 1:no la encontró (es de otro banco)
                        
                        
                        //si es interbancaria realizo el movimiento e informo
                        java.util.Date fecha = new Date();  
                    //  //Movimientos(Integer idMovimiento, int tipoMovimiento, int idCuenta, int idCuentaDestino, double importe, Date fechaMovimiento, int estado)
                        Movimientos mov = new Movimientos(1,tipo_movimiento,id_cuenta,cuenta_destino,monto,fecha,2);
                        ejbMovimientoFacade.create(mov);
                        ejbCuentaFacade.updateSaldo((saldoDisponible - importeFinal), id_cuenta);
                        
                        
                        ejbCuentaFacade.updateSaldo((saldoDisponibleDest + importeFinal), cuenta_destino);
                        
                    }
                    else{
                       jsonDevolver.put("flag_error", "1");
                       jsonDevolver.put("error", "No tienen saldo suficiente para realizar la transacción.");
                    }
                    
                }else if(tipo_movimiento == 4){
                    //4: compra de bono
                    // Averiguo cual es el saldo actual de la cuenta origen.
                    respuesta = this.enviarHttpRequest("http://localhost:8080/bancogrupo2/rest/cuentas/"+id_cuenta,"GET",new JSONObject());
                    
                    Double saldoDisponible = Double.parseDouble((new JSONObject(respuesta)).getString("saldo"));
                    
                    if (saldoDisponible >= 50){
                        Movimientos mov = new Movimientos(1,tipo_movimiento,id_cuenta,cuenta_destino,monto,date,1);
                        //Inversiones(Integer idInversion, int idCuenta, int idBono, Date fechaDeInicio, Date fechaDeVencimiento)
                        //Inversiones inversion = new Inversiones(1, id_bono, fechaDeInicio, fechaVencimiento);
                        
                        //Bono = (Integer idBono, String idMovimiento, String descripcion, double montoInvertido, Date fechaDePago, int interes, double impuesto) 
                        //Bonos bono = new Bonos(1, descripcion_bono, monto, fechaDePago, interes , impuesto);
                        ejbMovimientoFacade.create(mov);
                        ejbCuentaFacade.updateSaldo((saldoDisponible - monto), id_cuenta);
                        
                        //int rowsUpdated = query.executeUpdate();
                        jsonDevolver.put("flag_error", "0");
                        jsonDevolver.put("mensaje", "Ok");
                    }else{
                     jsonDevolver.put("flag_error", "1");
                     jsonDevolver.put("error", "No tienen saldo suficiente para realizar la transacción.");
                    }
                }
                return jsonDevolver.toString();

            }catch(NumberFormatException | JSONException e){
                jsonDevolver.put("flag_error", "1");
                jsonDevolver.put("error",e.getMessage());
                return jsonDevolver.toString();
            }
        }else{
            jsonDevolver.put("flag_error", "1");
            jsonDevolver.put("error", "El importe no puede ser negativo");
            return jsonDevolver.toString();        
        }
    }
    
    @Path("/ultimos/{idCuenta}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String findByIdCuenta(@PathParam("idCuenta") int id) throws JSONException{
        try{
            Query query = ejbMovimientoFacade.getEntityManager().createQuery("SELECT c from Movimientos c WHERE c.idCuenta = "+id +" order by c.fechaMovimiento DESC");
            List<Movimientos> listMov = query.getResultList();
            
            JSONObject jsonArray = new JSONObject();
            JSONObject jsonElement;
            String movString;
            
            for(Movimientos unMov : listMov){
                jsonElement = new JSONObject()
                        .put("idMovimiento", unMov.getIdMovimiento())
                        .put("idCuenta", unMov.getIdCuenta())
                        .put("idCuentaDestino", unMov.getIdCuentaDestino())
                        .put("importe", unMov.getImporte())
                        .put("FechaMovimiento", unMov.getFechaMovimiento())
                        .put("estado", unMov.getEstado())
                        .put("tipoMovimiento",unMov.getTipoMovimiento())
                        ;
                jsonArray.put(String.valueOf(unMov.getIdMovimiento()),jsonElement);
            }
            
            return jsonArray.toString();

        }catch(JSONException e){
            return e.getMessage();
        }
    }
    
    

}
