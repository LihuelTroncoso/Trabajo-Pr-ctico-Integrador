<%-- 
    Document   : dayos
    Created on : 12 nov. 2021, 09:07:10
    Author     : E.T 36
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Datos de la Clinica</title>
    <link rel="stylesheet" href="css/estilosDatosClinica.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Questrial&display=swap" rel="stylesheet">
</head>
<body>
    <% String hola = "chau"; %>
    <div class="header">
        <h1>DATOS DE LA CLINICA</h1>
    </div>

    <div class="formulario">
        <form action="pacientes" method="POST">
            <br><H4>Doctor</H4>
            <select name="doctor" class="controls"  >
                <option value=""></option>
                <option value="Horacio Rodriguez">Horacio Rodriguez</option>
                <option value="Carlos Alberto Miculicich">Carlos Alberto Miculicich</option>
                <option value="Nancy Schwaigoffer">Nancy Schwaigoffer</option>
            </select>
            <br><button type="submit" class="boton">Buscar</button><hr>
        </form>
        
        <form action="doctorMasEstudios" method="POST">
            <br><h4>Doctor con mas estudios solicitados</h4>
            <div class="desplegable">
                <button class="boton" type="submit">Ver</button>
            </div>
        </form>
                
        <form method="POST" action="facturaObraSocial">
            <br><h4>Obra social</h4>
            <select name="obraSocial" class="controls" >
                <option></option>
                <option value="OSDE">OSDE</option>
                <OPTION value="Medife">Medifé</OPTION>
                <option value="Swiss medical">Swiss medical</option>
            </select>
            <br><button type="submit" class="boton"> Ver total facturado
            </button>
        </form>
                
        <form action="estudiosEntreFechas" methos="POST">
            <br><h4>Ver estudios entre fecha y fecha</h4>
            <h3>FECHA INICIO</h3>
            <br>Año <input type="number" name="año" class="controls" min="1920" max="2050">
            <br>Mes <input type="number" name="mes" class="controls" min="1" max="12">
            <br>Dia <input type="number" name="dia" class="controls" min="1" max="31">
            <br>
            <h3>FECHA FIN</h3>
            <br>Año <input type="number" name="año1" class="controls" min="1920" max="2050">
            <br>Mes <input type="number" name="mes1" class="controls" min="1" max="12">
            <br>Dia <input type="number" name="dia1" class="controls" min="1" max="31">
            <br><button type="submit" class="boton"> Ver cantidad de estudios</button>
        </form>
        
    </div>
    <div id="divVolver" class="formulario">
        <button class="boton" type="submit" value="Volver" onclick="location.href='index.html'">Volver</button>
    </div>
    <footer>
        <h6>Vita Sanatory © Copyright 2021 </h6>
    </footer>
</body>
</html>
