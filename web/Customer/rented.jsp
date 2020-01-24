<%-- 
    Document   : rented
    Created on : 24-ene-2020, 7:13:42
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
        <h1>Lista de Coches rentados por el cliente: <% request.getAttribute("id"); %></h1>
        <table>
            <tr>
                <td><a href="Customers?action=list" >Atrás</a> </td>
                <td>- - -</td>
                <td><a href="Customers?action=newcar" >Insertar Nuevo</a> </td>
                <td><a href="Customers?action=removecar&id=all" >Borrar todos</a> </td>
            </tr>
        </table>

        <table border="1" width="100%">
            <tr>
                <td> ID</td>
                <td> FECHA INGRESO</td>
                <td> MARCA</td>
                <td> MODELO</td>
                <td> NUMERO ASIENTOS</td>
                <td> CAPACIDAD DEPOSITO</td>
                <td> ID CONCESIONARIO</td>
                <td> ACCIONES</td>
            </tr>
            <c:forEach var="car" items="${list}">
                <tr>
                    <td><c:out value="${car.getId()}"/></td>
                    <td><c:out value="${car.getSigningDate()}"/></td>
                    <td><c:out value="${car.getCarmake()}"/></td>
                    <td><c:out value="${car.getModel()}"/></td>
                    <td><c:out value="${car.getSeats()}"/></td>
                    <td><c:out value="${car.getDepositCapacity()}"/></td>
                    <td><c:out value="${car.getCarDealership().getId()}"/></td>
                    <td><a href="Customers?action=removecar&id=<c:out value="${car.getId()}"/>">Eliminar</a> </td>				
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
