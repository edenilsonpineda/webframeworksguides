<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Lista Docentes</title>
	<%@ include file='/cabecera.jsp' %>
	<link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/css/select2.min.css" rel="stylesheet" />
	<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/js/select2.min.js"></script>
</head>
<body>
	<jsp:include page="/menu.jsp"/>
	<div class="container">
		<div class="row">
			<h3>Lista Docentes</h3>
		</div>
			 <div class="col-md-12">
			 <a type="button" class="btn btn-primary btn-md" href="${contextPath}/docentes.do?op=nuevo"> Nuevo docente</a>
			 <br>
			 <br>
			 <hr>
			 <table class="table table-striped table-bordered table-hover" id="tabla">
			 	<thead>
				 <tr>
					 <th>Codigo del docente</th>
					 <th>Nombre del docente</th>
					 <th>Operaciones</th>
				 </tr>
				 </thead>
				 <tbody>
				 		<c:forEach items="${requestScope.listaDocentes}" var="docentes">
				 		<tr>
				 			<td>${docentes.codigo_docente}</td>
				 			<td>${docentes.nombre_docente}</td>
				 			<td>
				 				<a class="btn btn-primary" href="${contextPath}/docentes.do?op=obtener&id=${docentes.codigo_docente}">
				 				<span class="glyphicon glyphicon-edit"></span>Editar</a>
				 				<a class="btn btn-danger" href="javascript:eliminar('${docentes.codigo_docente}')">
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
			alertify.confirm("¿Realmente decea eliminar este Docente?", function(e) {
				if (e) {
					location.href = "docentes.do?op=eliminar&id=" + id;
				}
			});
		}
	</script>
</body>
</html>