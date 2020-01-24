<%-- 
    Document   : edit
    Created on : 24-ene-2020, 6:02:37
    Author     : juans
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*,web.model.Car"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar</title>
    </head>
    <body>
        <h1>Editar Coche</h1>
	<table>
		<tr>
			<td><a href="Cars?action=list" >Ir atrás</a> </td>
		</tr>
	</table>
        
        <form method="POST" action="Cars?action=edit">
            <% Car car = (Car)request.getAttribute("car"); %>
            Id:     <input type="number" name="id" value="<%= car.getId() %>" readonly/>
            Marca:     <input type="text" name="carmake" value="<%= car.getCarmake() %>"/>
            Modelo:        <input type="text" name="model" value="<%= car.getModel()%>"/>
            Numero Asientos: <input type="number" name="seats" value="<%= car.getSeats()%>"/>
            Capacidad Deposito: <input type="number" name="depositcapacity" value="<%= car.getDepositCapacity()%>"/>
            
            <input type="submit" value="Editar" />
        </form>
    </body>
</html>
