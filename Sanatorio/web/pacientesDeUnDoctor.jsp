<%-- 
    Document   : pacientesDeUnDoctor
    Created on : 22 nov 2021, 14:51:35
    Author     : Lihuel
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="classes.Paciente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="css/estiloPacientesDoctor.css">
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
    </head>
    <body>
        <div>
            <%ArrayList<Paciente> p = (ArrayList<Paciente>) request.getAttribute("pacientes");%>
        <div class="header">
        <img class="imagen" src="img/iconoPagina.png" alt="" width="70px">
        <h1>Pacientes atendidos por este doctor: </h1>
        </div>
        <div class="formulario">
        <table class="controls">
            <%
                for (Paciente pa: p)
                { 
                    
            %>
            
            <tr>
                <td>Nombre: <%= pa.getNombre() %> </td>
                <td>Apellido: <%= pa.getApellido() %></td>
                <td>DNI: <%= pa.getDni()%></td>  
            </tr>
            <% } %>
        </table>
        </div>
        </div>
    </body>
</html>
