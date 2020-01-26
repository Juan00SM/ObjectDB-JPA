<%-- 
    Document   : rented
    Created on : 24-ene-2020, 7:13:42
    Author     : juans
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*,web.model.Car"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administrar</title>
    </head>
    <body>
        <h1>Lista de Coches rentados por el cliente: <% out.println(request.getAttribute("id"));%></h1>
        <table>
            <tr>
                <td><a href="Customers?action=list" >Atrás</a> </td>
                <td>- - -</td>
                <td><a href="Customers?action=removecar&idc=all&idcu=<%= request.getAttribute("id")%>" >Borrar todos</a> </td>
                <td>- - - - - - -</td>
                <td><form method="POST" action="Customers?action=newcar&id=<%= request.getAttribute("id")%>">
                    <select name="carselect">
                        <option value="-1" selected="true">Selecciona un coche</option>
                        <c:forEach var="c" items="${cars}">
                            <option value="${c.getId()}">${c.getCarmake()} - ${c.getModel()} - ${c.getSeats()} asientos</option>
                        </c:forEach>
                    </select>
                    <input type="submit" value="Añadir" />
                    </form>
                </td>
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
                    <td><a href="Customers?action=removecar&idcu=<%= request.getAttribute("id")%>&idc=<c:out value="${car.getId()}"/>">Eliminar</a> </td>				
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
