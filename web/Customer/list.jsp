<%-- 
    Document   : list
    Created on : 24-ene-2020, 6:39:42
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
        <h1>Lista de Clientes</h1>
        <table>
            <tr>
                <td><a href="Customers?action=index" >Ir al menú</a> </td>
                <td>- - -</td>
                <td><a href="Customers?action=new" >Insertar Nuevo</a> </td>
                <td><a href="Customers?action=remove&id=all" >Borrar todos</a> </td>
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
                <td> COCHES RENTADOS</td>
                <td colspan=2>ACCIONES</td>
            </tr>
            <c:forEach var="customer" items="${list}">
                <tr>
                    <td><c:out value="${customer.getId()}"/></td>
                    <td><c:out value="${customer.getSigningDate()}"/></td>
                    <td><c:out value="${customer.getName()}"/></td>
                    <td><c:out value="${customer.getLastName()}"/></td>
                    <td><c:out value="${customer.getAge()}"/></td>
                    <td><c:out value="${customer.getDni()}"/></td>
                    <td><a href="Customers?action=listrented&id=<c:out value="${customer.getId()}" />">Ver</a></td>
                    <td><a href="Customers?action=showedit&id=<c:out value="${customer.getId()}" />">Editar</a></td>
                    <td><a href="Customers?action=remove&id=<c:out value="${customer.getId()}"/>">Eliminar</a> </td>				
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
