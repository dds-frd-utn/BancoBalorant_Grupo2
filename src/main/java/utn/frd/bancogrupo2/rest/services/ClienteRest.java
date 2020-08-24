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
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.servlet.http.HttpServlet;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.json.JSONException;
import org.json.JSONObject;
import utn.frd.bancogrupo2.entity.Clientes;
import utn.frd.bancogrupo2.entity.Cuentas;
import utn.frd.bancogrupo2.session.ClientesFacade;
import utn.frd.bancogrupo2.session.CuentasFacade;
/**
 *
 * @author Sergio
 */
@Path("/clientes")
public class ClienteRest extends HttpServlet{
    @Inject
    private CuentasFacade ejbCuentaFacade;
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
    
    public JSONObject stringArrayToJSON(String[] datos) throws JSONException{
        JSONObject json = new JSONObject();
//        int i = 0;
        String key = "";
        String value ="";
        String[] aux = null;
        try{
            for(String d:datos){
                aux = d.split("=>");
                key = aux[0];
                value = aux[1];
                json.put(key,value);
            }
            return json;
        }catch(JSONException e){
            return new JSONObject();
        }
        
    }
    
    @EJB
    private ClientesFacade ejbClienteFacade;
    
    //obtener todas las entidades
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Clientes> findAll(){
        return ejbClienteFacade.findAll();
    }
    
    //crear entidades
    //regalamos 300 pesos porque somos el mejor banco del mundo
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Clientes cliente){
        java.util.Date fecha = new Date();
        ejbClienteFacade.create(cliente);
        int idCliente = cliente.getIdCliente();
        Cuentas nuevaCuenta = new Cuentas(1,idCliente, 300.0, fecha);
        ejbCuentaFacade.create(nuevaCuenta);
        
    }
    
    //actualizar entidades
    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/{id}")
    public void edit(@PathParam("id")int id, Clientes cliente){
        ejbClienteFacade.edit(cliente);
    }
    
    //eliminar entidades
    @DELETE
    @Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
    @Path("/{id}")
    public void remove(@PathParam("id")int id){
        ejbClienteFacade.remove( ejbClienteFacade.find(id) );
    }
    
    //obtener una entidad por id
    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Clientes findById(@PathParam("id")int id){
        return ejbClienteFacade.find(id);
    }
    
    //Filtra los clientes segun su numero de documento
    @GET
    @Path("/documento/{documento}")
    @Produces({MediaType.APPLICATION_JSON})
        public JsonObject findByDocumento(@PathParam("documento")int documento){
            try{
                Clientes resultado;
                Query query = ejbClienteFacade.getEntityManager().createNamedQuery("Clientes.findByDocumento",Clientes.class);
                query.setParameter("documento", documento);
                
                resultado = (Clientes)query.getSingleResult();
                
                JsonObject jsonResultado = Json.createObjectBuilder()
                                        .add("flag_error", 0)
                                        .add("idCliente",resultado.getIdCliente())
                                        .add("documento", resultado.getDocumento())
                                        .add("nombre", resultado.getNombre())
                                        .add("apellido", resultado.getApellido())
                                        .add("password", resultado.getPassword())
                                        .add("fechaDeNacimiento", resultado.getFechaDeNacimiento().toString())
                                        .add("direccion", resultado.getDireccion())
                                        .build();
                return jsonResultado;
            } catch(NoResultException e) {
                 JsonObject jsonError = Json.createObjectBuilder()
                                        .add("flag_error", 1)
                                        .add("error", "No encontrado").build();
                
                return jsonError;
            }
    }
 
    @POST
    @Path("/login") 
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.TEXT_PLAIN})
    public String login(String peticion) throws JSONException{
        JSONObject jsonRespuesta = new JSONObject();
        try{
            JSONObject jsonPeticion = new JSONObject(peticion);
    
            int documento = (int) jsonPeticion.get("documento");
            String password = (String) jsonPeticion.get("password");
            
            Query query =  ejbClienteFacade.getEntityManager().createQuery("SELECT c from Clientes c WHERE c.documento='"+documento+"' AND c.password = '"+password+"'");
            Clientes resultado = (Clientes)query.getSingleResult();
            
            jsonRespuesta.put("flag_error", "0")
                    .put("password", password)
                    .put("documento", documento)
                    .put("nombre", resultado.getNombre())
                    .put("apellido", resultado.getApellido())
                    .put("idCliente", resultado.getIdCliente())
                    .put("fechaDeNacimiento", resultado.getFechaDeNacimiento())
                    ;
            
            return jsonRespuesta.toString();
        }catch(NoResultException e){
            jsonRespuesta.put("flag_error", "1")
                    .put("mensaje", "No encontrado");
            return jsonRespuesta.toString();
        }
    } 
    
}
