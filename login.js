




















/*
public String altaCliente(String peticionAlta) throws JSONException, MalformedURLException, IOException{
    JSONObject jsonRespuesta = new JSONObject();
    try{
        JSONObject jsonPeticionAlta = new JSONObject(peticionAlta);

        String id = jsonPeticionAlta.get("id").toString();
        int idInt = jsonPeticionAlta.getInt("id");
        //si no existe el cliente en nuestro banco
        
        if(this.findById(idInt).getInt("flag_error") == 1){
            String nombre = jsonPeticionAlta.get("nombre").toString();
            String ciudad = jsonPeticionAlta.get("ciudad").toString();
      

            String url = "http://localhost:8080/bancogamba/rest/cliente"+id;

            //hardcodeo el parametro vacio
            JSONObject jsonParam = new JSONObject();

            String clienteString = this.enviarHttpRequest(url, "GET", jsonParam);
            JSONObject cliente = new JSONObject(clienteString);
            if(cliente.getInt("id")<99){
                //return (String)"Es apto";
                //Creo cliente
                //public Cliente(String contrasenia, String usuario,String nombre,String direccion, int idTipoCliente, int du)
                //String fechaString = ciudadano.getString("fechaNacimiento").substring(0, 10);
                Clientes nuevoCliente = new Clientes(cliente.getString("nombre"),cliente.getString("direccion"),idInt);
                ejbClienteFacade.create(nuevoCliente);

                JsonObject jsonNuevoCliente = (JsonObject) this.findById(idInt);
                
                int idCliente = jsonNuevoCliente.getInt("idCliente");

                //Creo cuenta en banco central
                //JSONObject jsonIdCuenta = new JSONObject("{\"id\":16}");
                JSONObject jsonIdCiudadano = new JSONObject("{\"id\":"+id+"}");
                
                //JSONObject jsonCrearCuenta = new JSONObject();
                //jsonCrearCuenta.put("idBanco", jsonIdCuenta);
                //jsonCrearCuenta.put("idCiudadano", jsonIdCiudadano);
                //jsonCrearCuenta.put("saldo", saldoInicial);
                
                
                //Cuando sergio devuelva el idCuenta, hacemos esto
                //String urlCrearCuenta = "http://lsi.no-ip.org:8282/esferopolis/api/cuenta";
                //String idCuenta = this.enviarHttpRequest(urlCrearCuenta,"POST",jsonCrearCuenta);
                
                
                //String idCuenta = "27";
                
                //luego crear cuenta en nuestro banco
                //Cuenta nuevaCuenta = new Cuenta(Integer.parseInt(idCuenta),1,idCliente);
                //ejbCuentaFacade.create(nuevaCuenta);
                
           
                jsonRespuesta.put("flag_error", "0")
                        .put("mensaje", "Registrado exitosamente.");
                
            }else{
                jsonRespuesta.put("flag_error", "1")
                        .put("mensaje", "El cliente no es apto.");
            }
        }else{
            jsonRespuesta.put("flag_error", "1")
                        .put("mensaje", "El cliente ya existe.");            
        }
        return jsonRespuesta.toString();
    }catch(NumberFormatException e){
        jsonRespuesta.put("flag_error", "1")
                .put("mensaje", "Error inesperado: "+ e.getMessage());
        return jsonRespuesta.toString();
    }
}

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

*/