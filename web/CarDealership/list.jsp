<%-- 
    Document   : mostrar
    Created on : 24-ene-2020, 1:41:59
    Author     : juans
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administrar</title>
    </head>
    <body>
        <h1>Lista de Concesionarios</h1>
	<table>
		<tr>
			<td><a href="CarDealerships?action=index" >Ir al menú</a> </td>
			<td>- - -</td>
			<td><a href="CarDealerships?action=new" >Insertar Nuevo</a> </td>
			<td><a href="CarDealerships?action=remove&id=all" >Borrar todos</a> </td>
		</tr>
	</table>
	
	<table border="1" width="100%">
		<tr>
		 <td> ID</td>
		 <td> FECHA INGRESO</td>
		 <td> NOMBRE</td>
		 <td> CEO</td>
		 <td> BENEFICIO ANUAL</td>
		 <td> COCHES</td>
		 <td colspan=2>ACCIONES</td>
		</tr>
		<c:forEach var="carD" items="${list}">
			<tr>
				<td><c:out value="${carD.getId()}"/></td>
				<td><c:out value="${carD.getSigningDate()}"/></td>
				<td><c:out value="${carD.getName()}"/></td>
				<td><c:out value="${carD.getCeo()}"/></td>
				<td><c:out value="${carD.getAnnualProfit()}"/></td>
				<td><a href="CarDealerships?action=listcars&id=<c:out value="${carD.getId()}" />">Ver</a></td>
				<td><a href="CarDealerships?action=showedit&id=<c:out value="${carD.getId()}" />">Editar</a></td>
				<td><a href="CarDealerships?action=remove&id=<c:out value="${carD.getId()}"/>">Eliminar</a> </td>				
			</tr>
		</c:forEach>
	</table>
    </body>
</html>
