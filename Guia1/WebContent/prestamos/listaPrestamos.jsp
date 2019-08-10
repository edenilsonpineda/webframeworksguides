<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Lista Prestamos</title>
 	<%@ include file='/cabecera.jsp' %>
 	<link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/css/select2.min.css" rel="stylesheet" />
	<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/js/select2.min.js"></script>
</head>
<body>
	<jsp:include page="/menu.jsp"/>
 	<div class="container">
		<div class="row">
			<h3>Lista Prestamos</h3>
		</div>
			 <div class="col-md-12">
			 <a type="button" class="btn btn-primary btn-md" href="${contextPath}/prestamos.do?op=nuevo"> Nuevo prestamo</a>
			 <br>
			 <br>
			 <hr>
			 <table class="table table-striped table-bordered table-hover" id="tabla">
			 	<thead>
				 <tr>
					 <th>Codigo del prestamo</th>
					 <th>Libro</th>
					 <th>Docente</th>
					 <th>Fecha Prestamo</th>
					 <th>Fecha Devolucion</th>
					 <th>Operación</th>
				 </tr>
				 </thead>
				 <tbody>
				 		<c:forEach items="${requestScope.listaPrestamo}" var="prestamos">
				 		<tr>
				 			<td>${prestamos.codigo_prestamo}</td>
				 			<td>${prestamos.codigo_libro}</td>
				 			<td>${prestamos.codigo_docente}</td>
				 			<td>${prestamos.fecha_prestamo}</td>
				 			<td>${prestamos.fecha_devolucion}</td>
				 			<td>
				 				<a class="btn btn-primary" href="${contextPath}/prestamos.do?op=obtener&id=${prestamos.codigo_prestamo}">
				 				<span class="glyphicon glyphicon-edit"></span>Editar</a>
				 				<a class="btn btn-danger" href="javascript:eliminar('${prestamos.codigo_prestamo}')">
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
			alertify.confirm("¿Realmente decea eliminar este Prestamo?", function(e) {
				if (e) {
					location.href = "prestamos.do?op=eliminar&id=" + id;
				}
			});
		}
	</script>
</body>
</html>