<%-- 
    Document   : new
    Created on : 24-ene-2020, 5:59:13
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
        <h1>Insertar Nuevo Coche</h1>
	<table>
		<tr>
			<td><a href="Cars?action=list" >Ir atrás</a> </td>
		</tr>
	</table>
        
        <form method="POST" action="Cars?action=register">
            Marca:     <input type="text" name="carmake" />
            Modelo:        <input type="text" name="model" />
            Numero Asientos: <input type="number" name="seats" />
            Capacidad Deposito: <input type="number" name="depositCapacity" />
            <input type="submit" value="Add" />
        </form>
    </body>
</html>
