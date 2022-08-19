<%-- 
    Document   : muestraPaciente
    Created on : 12 nov. 2021, 08:03:42
    Author     : E.T 36
--%>

<%@page import="classes.Paciente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Paciente</title>
        <meta charset="UTF-8">
        <link rel="shortcut icon" href="img/iconoPagina.png">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/estiloMuestraPaciente.css">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Questrial&display=swap" rel="stylesheet">
    </head>
    <body>
        <% Paciente p = (Paciente) request.getAttribute("atribPac"); %>
        <div class="header">
            <img class="imagen" src="img/iconoPagina.png" alt="" width="70px">
            <h1>REGISTRO DEL PACIENTE</h1>
        </div>
        <div class="formulario">
        <form action="formularioPacienteRegistrado" method="POST">
            <br>Nombre
            <p class="controls" id="demo" name="nombre"><%= p.getNombre() %></p>
            <br>Apellido
            <p class="controls" id="demo" name="apellido"><%= p.getApellido() %></p>
            <br>Dni
            <p class="controls" id="demo" name="dni"><%= p.getDni() %></p><!-- comment -->
            
            <!--<br>Apellido <%= p.getApellido() %><input type="text" name="apellido" class="controls" >--> 
            <!--<br>DNI <%= p.getDni() %><input type="text" name="dni" class="controls"  >-->
            <br>Obra social
            <select name="obraSocial" class="controls" >
                <option></option>
                <option value="OSDE" >OSDE</option>
                <OPTION value="Medife">Medife</OPTION>
                <option value="Swiss medical">Swiss medical</option>
            </select>
            <br>Doctor <select name="doctor" class="controls"  >
                <option value=""></option>
                <option value="Horacio Rodriguez">Horacio Rodriguez</option>
                <option value="Carlos Alberto Miculicich">Carlos Alberto Miculicich</option>
                <option value="Nancy Schwaigoffer">Nancy Schwaigoffer</option>
            </select>
            <br>Estudio <select name="estudio" class="controls" >
                <option value=""></option>
                <option value="icc">Imagen con contraste</option>
                <option value="isc">Imagen sin contraste</option>
                <option value="l">Laboratorio</option> -->
            </select>
            <br>Fecha y hora <input type="datetime-local" name="fechahora" class="controls">
            <button type="submit" class="boton"> Enviar</button>
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

