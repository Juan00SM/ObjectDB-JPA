<%-- 
    Document   : rented
    Created on : 24-ene-2020, 7:12:37
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
        <h1>Lista de Clientes que han alquilado el coche: <% out.println(request.getAttribute("id"));%></h1>
        <table>
            <tr>
                <td><a href="Cars?action=list" >Atrás</a> </td>
                <td>- - -</td>
                <td><a href="Cars?action=removecar&idcu=all&idc=<%= request.getAttribute("id")%>" >Borrar todos</a> </td>
                <td>- - - - - - -</td>
                <td><form method="POST" action="Cars?action=newcar&id=<%= request.getAttribute("id")%>">
                    <select name="custselect">
                        <option value="-1" selected="true">Selecciona un cliente</option>
                        <c:forEach var="c" items="${cust}">
                            <option value="${c.getId()}">${c.getName()} - ${c.getAge()}años - ${c.getDni()}</option>
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
                <td> NOMBRE</td>
                <td> APELLIDOS</td>
                <td> EDAD</td>
                <td> DNI</td>
                <td> ACCION</td>
            </tr>
            <c:forEach var="customer" items="${list}">
                <tr>
                    <td><c:out value="${customer.getId()}"/></td>
                    <td><c:out value="${customer.getSigningDate()}"/></td>
                    <td><c:out value="${customer.getName()}"/></td>
                    <td><c:out value="${customer.getLastName()}"/></td>
                    <td><c:out value="${customer.getAge()}"/></td>
                    <td><c:out value="${customer.getDni()}"/></td>
                    <td><a href="Cars?action=removecar&idc=<%= request.getAttribute("id")%>&idcu=<c:out value="${customer.getId()}"/>">Eliminar</a> </td>				
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
