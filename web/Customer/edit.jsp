<%-- 
    Document   : edit
    Created on : 24-ene-2020, 6:40:07
    Author     : juans
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*,web.model.Customer"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar</title>
    </head>
    <body>
        <h1>Editar Cliente</h1>
	<table>
		<tr>
			<td><a href="Customers?action=list" >Ir atrás</a> </td>
		</tr>
	</table>
        
        <form method="POST" action="Customers?action=edit">
            <% Customer cust = (Customer)request.getAttribute("customer"); %>
            Id:     <input type="number" name="id" value="<%= cust.getId() %>" readonly/>
            Nombre:     <input type="text" name="name" value="<%= cust.getName()%>"/>
            Apellidos:        <input type="text" name="lastname" value="<%= cust.getLastName()%>"/>
            Edad: <input type="number" name="age" value="<%= cust.getAge()%>"/>
            DNI: <input type="text" name="dni" value="<%= cust.getDni()%>"/>
            
            <input type="submit" value="Editar" />
        </form>
    </body>
</html>