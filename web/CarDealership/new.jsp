<%-- 
    Document   : register
    Created on : 24-ene-2020, 1:41:01
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
        <h1>Insertar Nuevo Concesionario</h1>
	<table>
		<tr>
			<td><a href="CarDealerships?action=list" >Ir atrás</a> </td>
		</tr>
	</table>
        
        <form method="POST" action="CarDealerships?action=register">
            Nombre:     <input type="text" name="name" />
            Ceo:        <input type="text" name="ceo" />
            Beneficios anuales: <input type="number" name="annualProfit" />
            <input type="submit" value="Add" />
        </form>
    </body>
</html>
