var select = document.querySelector('select');
var para = document.querySelector('p');

function myFunction() {
    var choice = select.value;
    var x = document.getElementById("mySelect").value;
            
    if(x == 'icc') {
    // para.textContent = 'Precio del estudio: $2000';
        document.getElementById("demo").innerHTML = "Precio del estudio: $2000" ;
    }
    if(x == 'isc') {
    // para.textContent = 'Precio del estudio: $4000';
        document.getElementById("demo").innerHTML = "Precio del estudio: $4000" ;
    }
    if(x == 'lab') {
    // para.textContent = 'Precio del estudio: $8000';
        document.getElementById("demo").innerHTML = "Precio del estudio: $8000" ;
    }
    if(x == 'nada') {
    // para.textContent = 'Precio del estudio: $8000';
        document.getElementById("demo").innerHTML = " " ;
    }
}