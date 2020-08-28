function ajax_process ( 
    url, 
    metodo, 
    data,
    callback
) {
    console.log(data);
    if (!data) { data = ''; }
    $.ajax({
        data: data,
        method:metodo,
        contentType: 'application/json',
        dataType:"json",
        url:url,
        success:callback,
        error:function(error){
        }
    });
}

function formatDate () {
  var d = new Date(),
      month = '' + (d.getMonth() + 1),
      day = '' + d.getDate(),
      year = d.getFullYear();

  if (month.length < 2) month = '0' + month;
  if (day.length < 2) day = '0' + day;
  return [year, month, day].join('-');
}

function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
    results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}




function request (url, metodo, data,callback) {
    console.log(data);
    if (!data) { data = ''; }
	$.ajax({
        data: data,
        method:metodo,
        contentType: 'application/json',
        dataType:"json",
        url:url,
        success:callback,
        error:function(error){
        }
    });
}

function id_url(){
		var paramstr = window.location.search.substr(1);
		var paramarr = paramstr.split ("&");
		var params = {};

		for ( var i = 0; i < paramarr.length; i++) {
	    	var tmparr = paramarr[i].split("=");
	    	params[tmparr[0]] = tmparr[1];
		}
		
		if (params['id']) {
	   		console.log('El valor del parámetro variable es: '+params['id']);
		} else {
	   		console.log('No se envió el parámetro id');
		}
		return params['id'];	
}

function fechaActual(){
		var today = new Date();
		var dd = String(today.getDate()).padStart(2, '0');
		var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
		var yyyy = today.getFullYear();
		today = yyyy + '-' + mm + '-' + dd;
		console.log(today);

	return today;
}

function calcularEdad(fecha) {
    var hoy = new Date();
    var cumpleanos = new Date(fecha);
    var edad = hoy.getFullYear() - cumpleanos.getFullYear();
    var m = hoy.getMonth() - cumpleanos.getMonth();

    if (m < 0 || (m === 0 && hoy.getDate() < cumpleanos.getDate())) {
        edad--;
    }

    return edad;
}	


//logearse
$("#btn-Ingresar").click(function(event){
    event.preventDefault();

    let documento = parseInt($("#documento").val());
    let password = $("#password").val().toString();
    console.log(typeof documento);
    if(documento == "" || password == ""){
        alert("Por favor ingrese su usuario y contraseña");
    }else{
        $.ajax({
            url:"http://localhost:8080/bancogrupo2/rest/clientes/login",
            method:"post",
            data:JSON.stringify({"documento":documento,"password":password}),
            dataType:"json",
            contentType:"application/json",
            async:false,
            success:function(myJson){
                console.log(myJson);
                if(myJson.flag_error == "0"){
                    //guardar variables session
                    realizarLogin(myJson);
                }else{
                    alert("Datos incorrectos");
                }
            },
            error:function(error){
                alert("Ha ocurrido un error");
                console.log(error);
            }
        });
    }
});
        function realizarLogin(myJson){
            let documento = myJson.documento;
            let password = myJson.password;
            let idCliente = myJson.idCliente;
            let nombre = myJson.nombre;
            let apellido = myJson.apellido;
            let fechaDeNacimiento = myJson.fechaDeNacimiento;
            
            $.ajax({
                url:"http://localhost:8080/bancogrupo2/CrearSession",
                type:"post",
                async:"false",
                data:{"documento":documento,"password":password,"idCliente":idCliente,"nombre":nombre,"apellido":apellido,"fechaDeNacimento":fechaDeNacimiento},
                success:function(response){
                    console.log(response);
                    location.href = "user/index.html";
                    //alert("Session creado y logeado");
                },
                error:function(error){
                    alert("Se produjo un error de sesión");
                    console.log(error);
                }
            });
        }
        
    






/*Registro de Cliente y cuenta
function crearCliente(){    
    var edad = calcularEdad($('#fn').val());
    var data = JSON.stringify({
        apellido : $('#apellido').val(),
        direccion : $('#direccion').val().toString(),
        documento : $('#dni').val(),
        fechaNacimiento : $('#fn').val()+'T00:00:00Z[UTC]',
        password : $('#password').val().toString(),
        nombre : $('#nombre').val()
    });
    
    if (edad >= 16){
        var xhr = new XMLHttpRequest();
        var xhr2 = new XMLHttpRequest();
        xhr.withCredentials = true;
        xhr2.withCredentials = true;

        xhr.addEventListener("readystatechange", function () {
        if (this.readyState === this.DONE) {
            console.log(this.responseText);
            }
        });
        xhr.open("POST", "http://localhost:8080/bancogrupo2/rest/clientes");
        xhr.setRequestHeader("content-type", "application/json");
        xhr.send(data);
    } else {
        alert("Para crear una cuenta debe tener mas de 16 años");
        window.location.href='google.com';                         
        return false;  
    }
};

*/
today = fechaActual();


$("#btn-registro").click(function(event){
    event.preventDefault(); //No ejecuta la accion predeterminada del boton
    
    var    apellido = $('#apellido').val();
    var    direccion = $('#direccion').val().toString();
    var    documento = $('#dni').val();
    var    fechaDeNacimiento = $('#fn').val()+'T00:00:00Z[UTC]';
    var    password = $('#password').val().toString();
    var    nombre = $('#nombre').val();
    var    edad = calcularEdad($('#fn').val());

    if(nombre === "" || password === "" || documento === "" || fechaDeNacimiento === "" || direccion === "" || apellido === ""){
        alert("Por favor complete todos los campos");
    } else {
        var data = JSON.stringify({
            apellido : $('#apellido').val(),
            direccion : $('#direccion').val().toString(),
            documento : $('#dni').val(),
            fechaDeNacimiento : $('#fn').val()+'T00:00:00Z[UTC]',
            password : $('#password').val().toString(),
            nombre : $('#nombre').val()
        });

        if (edad >= 16){
            var xhr = new XMLHttpRequest();
            xhr.withCredentials = true;
            xhr.addEventListener("readystatechange", function () {
            if (this.readyState === this.DONE) {
                console.log(this.responseText);
                }
            });
            
            xhr.open("POST", "http://localhost:8080/bancogrupo2/rest/clientes");
            xhr.setRequestHeader("content-type", "application/json");
            xhr.send(data);
            alert("Se ha creado el usuario y su cuenta correctamente");
            console.log(documento);
            //conseguirIDCliente(documento);
        } else {
        alert("Para crear una cuenta debe tener mas de 16 años");
        //window.location.href='file:///C:/Users/ads/Desktop/Mi%20web/registro.html';                         
        return false;  
        }
    } 

});











