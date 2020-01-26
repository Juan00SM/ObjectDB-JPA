<%-- 
    Document   : editar
    Created on : 24-ene-2020, 2:03:30
    Author     : juans
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*,web.model.Car_Dealership"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar</title>
    </head>
    <body>
        <h1>Editar Concesionario </h1>
	<table>
		<tr>
			<td><a href="CarDealerships?action=list" >Ir atrás</a> </td>
		</tr>
	</table>
        
        <form method="POST" action="CarDealerships?action=edit">
            <% Car_Dealership carD = (Car_Dealership)request.getAttribute("carD"); %>
            Id:     <input type="number" name="id" value="<%= carD.getId() %>" readonly/>
            Nombre:     <input type="text" name="name" value="<%= carD.getName() %>"/>
            Ceo:        <input type="text" name="ceo" value="<%= carD.getCeo()%>"/>
            Beneficios anuales: <input type="number" name="annualProfit" value="<%= carD.getAnnualProfit()%>"/>
            
            <input type="submit" value="Editar" />
        </form>
    </body>
</html>
