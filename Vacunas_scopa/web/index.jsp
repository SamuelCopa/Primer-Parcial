
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.emergentes.modelo.Vacuna"%>
<%@page import="com.emergentes.modelo.GestorVacunas"%>

<%
    if (session.getAttribute("vacuna") == null) {
        GestorVacunas v1 = new GestorVacunas();
        
        v1.insertarRegistro(new Vacuna(1,"Brunito Diaz",25.0,1.40,"SI"));
        v1.insertarRegistro(new Vacuna(2,"Juancito Pinto",30.0,1.52,"NO"));
       
        
        session.setAttribute("vacuna", v1);
    }
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
       
        <textarea name="textarea" rows="5" cols="40">         PRIMER PARCIAL TEM - 742

        Nombre: Samuel Elias Copa Ticona
        Carnet: 5995792 LP</textarea>

        <h1>Registro de Vacunas</h1>
        <a href="Controller?op=nuevo">NUEVO </a>
        <br><br>
        <table border="1">
            <tr>
                <th>Id</th>
                <th>Nombre</th>
                <th>Peso</th>
                <th>Talla</th>
                <th>Vacuna</th>
                <th colspan="2">Opcion</th>                     
            </tr>

            <c:forEach var="item" items="${sessionScope.vacuna.getLista()}">
                <tr>
                    <td>${item.id}</td>  
                    <td>${item.nombre}</td>  
                    <td>${item.peso}</td>  
                    <td>${item.talla}</td>  
                    <td>${item.vacuna}</td>  
                    <td><a href="Controller?op=modificar&id=${item.id}">Modificar</a></td>
                    <td><a href="Controller?op=eliminar&id=${item.id}"  onclick="return(confirm('Esta seguro de eliminar ?'))">Eliminar</a></td>
                </tr>
            </c:forEach>

        </table>
    </body>
</html>