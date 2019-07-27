<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<title>Authors List</title>
	<jsp:include page="/cabecera.jsp"/>
</head>
<body>
<jsp:include page="/menu.jsp"/>
	<div class="container">
		<div class="row">
			<h3>Authors List</h3>
		</div>
		<div class="row">
			 <div class="col-md-10">
			 <a type="button" class="btn btn-primary btn-md" href="${contextPath}/autores.do?op=nuevo"> Nuevo autor</a>
			 <br>
			 <table class="table table-striped table-bordered table-hover" id="tabla">
			 	<thead>
				 <tr>
					 <th>Codigo del autor</th>
					 <th>Nombre del autor</th>
					 <th>Nacionalidad</th>
					 <th>Operaciones</th>
				 </tr>
				 </thead>
				 <tbody>
				 		<c:forEach items="${requestScope.listaAutores}" var="autores">
				 		<tr>
				 			<td>${autores.CodigoAutor}</td>
				 			<td>${autores.nombreAutor}</td>
				 			<td>${autores.nacionalidad}</td>
				 			<td>
				 				<a class="btn btn-outline-primary" href="${contextPath}/autores.do?op=obtener&id=${autores.codigoAutor}">
				 				<span class="glyphicon glyphicon-edit"></span>Editar</a>
				 				<a class="btn btn-outline-primary" href="javascript:eliminar('${autores.codigoAutor}')">
				 				<span class="glyphicon glyphicon-trash"></span>Eliminar</a>
				 			</td>
				 		</tr>
				 		</c:forEach>
				 </tbody>
			 </table>
			</div>
			
			
		</div>
	</div>
</body>
</html>