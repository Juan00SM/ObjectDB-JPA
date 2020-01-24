<%-- 
    Document   : new
    Created on : 24-ene-2020, 6:39:34
    Author     : juans
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insertar</title>
    </head>
    <body>
            <h1>Insertar Nuevo Cliente</h1>
	<table>
		<tr>
			<td><a href="Customers?action=list" >Ir atrás</a> </td>
		</tr>
	</table>
        
        <form method="POST" action="Customers?action=register">
            Nombre:     <input type="text" name="name" />
            Apellidos:        <input type="text" name="lastname" />
            Edad: <input type="number" name="age" />
            DNI: <input type="number" name="dni" />
            <input type="submit" value="Add" />
        </form>
    </body>
</html>
