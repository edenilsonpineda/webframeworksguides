<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Books List</title>
 	<%@ include file='/cabecera.jsp' %>
 	<link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/css/select2.min.css" rel="stylesheet" />
	<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/js/select2.min.js"></script>
</head>
<body>
	<jsp:include page="/menu.jsp"/>
 	<div class="container">
		<div class="row">
			<h3>Books List</h3>
		</div>
			 <div class="col-md-12">
			 <a type="button" class="btn btn-primary btn-md" href="${contextPath}/libros.do?op=nuevo"> Nuevo libro</a>
			 <br>
			 <br>
			 <hr>
			 <table class="table table-striped table-bordered table-hover" id="tabla">
			 	<thead>
				 <tr>
					 <th>Codigo del libro</th>
					 <th>Nombre del libro</th>
					 <th>Existencias</th>
					 <th>Precio</th>
					 <th>Autor</th>
					 <th>Editorial</th>
					 <th>Género</th>
					 <th>Descripción</th>
					 <th>Operación</th>
				 </tr>
				 </thead>
				 <tbody>
				 		<c:forEach items="${requestScope.listaLibros}" var="libros">
				 		<tr>
				 			<td>${libros.codigoLibro}</td>
				 			<td>${libros.nombreLibro}</td>
				 			<td>${libros.existencias}</td>
				 			<td>${libros.precio}</td>
				 			<td>${libros.autor.nombreAutor}</td>
				 			<td>${libros.editorial.nombreEditorial}</td>
				 			<td>${libros.genero.nombreGenero}</td>
				 			<td>${libros.descripcion}</td>
				 			<td>
				 				<a class="btn btn-primary" href="${contextPath}/libros.do?op=obtener&id=${libros.codigoLibro}">
				 				<span class="glyphicon glyphicon-edit"></span>Editar</a>
				 				<a class="btn btn-danger" href="javascript:eliminar('${libros.codigoLibro}')">
				 				<span class="glyphicon glyphicon-trash"></span>Eliminar</a>
				 			</td>
				 		</tr>
				 		</c:forEach>
				 </tbody>
			 </table>
			</div>
		</div>
	<script>

		$(document).ready(function() {
			$('#tabla').DataTable();
		});
		
		<c:if test="${not empty exito}">
			alertify.success('${exito}');
			<c:set var="exito" value="" scope="session" />
		</c:if>
		
		<c:if test="${not empty fracaso}">
			alertify.error('${fracaso}');
			<c:set var="fracaso" value="" scope="session" />
		</c:if>
		
		function eliminar(id) {
			alertify.confirm("¿Realmente decea eliminar este Libro?", function(
					e) {
				if (e) {
					location.href = "libros.do?op=eliminar&id=" + id;
				}
			});
		}
	</script>
</body>
</html>