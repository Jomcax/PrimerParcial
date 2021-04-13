<%@page import="com.emergentes.modelo.Vacunas"%>
<%@page import="com.emergentes.modelo.GestorVacunas"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
    if (session.getAttribute("vac") == null){
        GestorVacunas objeto1 = new GestorVacunas();
        
        objeto1.insertarVacuna(new Vacunas(1, "Bruno Diaz", 25, "1.40", "SI"));
        objeto1.insertarVacuna(new Vacunas(2, "Juancito Pinto", 30, "1.52", "NO"));
        
        session.setAttribute("vac", objeto1);
    }   
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PRIMER PARCIAL</title>
    </head>
    <body>
        <hr>
        <p style="color:#154360;">PRIMER PARCIAL TEM-742</p>
        <p style="color:#4A235A;">Nombre: Jobe Escalante Quispe</p>
        <p style="color:#145A32;">Carnet: 9975951</p>
        <hr>
        <h1 style="color:#943126;">Registro de Vacunas</h1>
        <a href="Controller?op=nuevo">Nuevo</a>
        <p></p>
        <table border="1">
            <tr>
                <th>Id</th>
                <th>Nombre</th>
                <th>Peso</th>
                <th>Talla</th>
                <th>Vacuna</th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach var="item" items="${sessionScope.vac.getLista()}">
            <tr>
                <td>${item.id}</td>
                <td>${item.nombre}</td> 
                <td>${item.peso}</td>
                <td>${item.talla}</td>
                <td>${item.vacuna}</td> 
                <td><a href="Controller?op=modificar&id=${item.id}">Modificar</a></td>
                <td><a href="Controller?op=eliminar&id=${item.id}">Eliminar</a></td>
            </tr>
            </c:forEach>
        </table>
    </body>
</html>
