//$("#titleUsuario").html(id_url());


//Transferencias
document.getElementById('transferencias').addEventListener("click", function(){
    document.querySelector('.bg-modal').style.display = 'flex';
});

document.querySelector('.close').addEventListener("click", function() {
	document.querySelector('.bg-modal').style.display = "none";
});

document.getElementById('comprar').addEventListener("click", function(){
    document.querySelector('.bg-modal2').style.display = 'flex';
});

document.querySelector('.close2').addEventListener("click", function() {
	document.querySelector('.bg-modal2').style.display = "none";
});

document.getElementById('compraBonos').addEventListener("click", function(){
    document.querySelector('.bg-modalbono').style.display = 'flex';
});

document.querySelector('.close3').addEventListener("click", function() {
	document.querySelector('.bg-modalbono').style.display = "none";
});

$("#btnEnviar").click(function(event){
    event.preventDefault(); //No ejecuta la accion predeterminada del boton
    
    var    idOrigen = $('#idOrigen').val();
    var    idDestino = $('#idDestino').val();
    var    monto = $('#monto').val();
    if(idOrigen === "" || idDestino === "" || monto === ""){
        alert("Por favor complete todos los campos");
    } else {
        var data = JSON.stringify({
            idCuenta : idOrigen,
            idCuentaDestino : idDestino,
            monto : monto,
            tipoMovimiento : 2
        })
        console.log(data);
        var xhr = new XMLHttpRequest();
        xhr.withCredentials = true;
        xhr.addEventListener("readystatechange", function () {
        if (this.readyState === this.DONE) {
            alert("Transferencia realizada con exito");
            console.log(this.responseText);
            }
        });
        xhr.open("POST", "http://localhost:8080/bancogrupo2/rest/movimientos/realizar");
        xhr.setRequestHeader("content-type", "application/json");
        xhr.send(data);
        
        
    }
})

$("#btnComprar").click(function(event){
    event.preventDefault(); //No ejecuta la accion predeterminada del boton
    
    var    idOrigen = $('#idOrigen').val();
    var    idDestino = $('#idDestino').val();
    var    monto = $('#monto').val();
    if(idOrigen === "" || idDestino === "" || monto === ""){
        alert("Por favor complete todos los campos");
    } else {
        var data = JSON.stringify({
            idCuenta : idOrigen,
            idCuentaDestino : idDestino,
            monto : monto,
            tipoMovimiento : 3
        })
        console.log(data);
        var xhr = new XMLHttpRequest();
        xhr.withCredentials = true;
        xhr.addEventListener("readystatechange", function () {
        if (this.readyState === this.DONE) {
            alert("Transferencia realizada con exito");
            console.log(this.responseText);
            }
        });
        xhr.open("POST", "http://localhost:8080/bancogrupo2/rest/movimientos/realizar");
        xhr.setRequestHeader("content-type", "application/json");
        xhr.send(data);
    }
})


$("#btnBono").click(function(event){
    event.preventDefault(); //No ejecuta la accion predeterminada del boton
    var    idOrigen = $('#cuenta').val();
    var    monto = $('#inversion').val();
    if(idOrigen === "" || idDestino === "" || monto === ""){
        alert("Por favor complete todos los campos");
    } else {
        var data = JSON.stringify({
            idCuenta : idOrigen,
            idCuentaDestino : 0,
            monto : monto,
            tipoMovimiento : 4
        })
        console.log(data);
        var xhr = new XMLHttpRequest();
        xhr.withCredentials = true;
        xhr.addEventListener("readystatechange", function () {
        if (this.readyState === this.DONE) {
            alert("Transferencia realizada con exito");
            console.log(this.responseText);
            }
        });
        xhr.open("POST", "http://localhost:8080/bancogrupo2/rest/movimientos/realizar");
        xhr.setRequestHeader("content-type", "application/json");
        xhr.send(data);
    }
})

