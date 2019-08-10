<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	 <title>Nuevo prestamo</title>
 	<%@ include file='/cabecera.jsp' %>
 	<link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/css/select2.min.css" rel="stylesheet" />
	<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/js/select2.min.js"></script>
</head>

<body>
	<jsp:include page="/menu.jsp"/>
 	<div class="container">
 		<div class="row">
 			<h3>Nuevo prestamo</h3>
 		</div>
 		
 		<div class="row">
 			<div class=" col-md-7">
				 <c:if test="${not empty listaErrores}">
 					<div class="alert alert-danger">
 						<ul>
 							<c:forEach var="errores" items="${requestScope.listaErrores}">
 								<li>${errores}</li>
 							</c:forEach>
 						</ul>
 					</div>
				</c:if>

				<form role="form" action="${contextPath}/prestamos.do" method="POST">
 					<input type="hidden" name="op" value="insertar">
 					<div class="well well-sm"><strong><span class="glyphicon glyphiconasterisk"></span>Campos requeridos</strong></div>
 					
					<div class="form-group">
 						<label for="codigoDocente">Docente</label>
 						<div class="input-group">
 							<select name="codigo_docente" id="codigo_docente" class="form-control">
 								<c:forEach items="${requestScope.listaDocentes}" var="docentes">
 									<option value="${docentes.codigo_docente}">${docentes.nombre_docente}</option>
 								</c:forEach>
 							</select>
 							<span class="input-group-addon"><span class="glyphicon glyphiconasterisk"></span></span>
 						</div>
 					</div>

					<div class="form-group">
 						<label for="codigoEditorial">Libro</label>
 						<div class="input-group">
 							<select name="codigo_libro" id="codigo_libro" class="form-control">
 								<c:forEach items="${requestScope.listaLibros}" var="libro">
 									<option value="${libro.codigoLibro}">${libro.nombreLibro}</option>
 								</c:forEach>
 							</select>
 							<span class="input-group-addon"><span class="glyphicon glyphiconasterisk"></span></span>
 						</div>
 					</div>
 					
 					<div class="form-group">
 						<label for="codigoEditorial">Fecha Prestamo</label>
 						<div class="input-group">
 							<input type="date" class="form-control" id="existencias" name="fecha_prestamo" required>
 							<span class="input-group-addon"><span class="glyphicon glyphiconasterisk"></span></span>
 						</div>
 					</div>
 					
 					<div class="form-group">
 						<label for="codigoEditorial">Fecha devolución</label>
 						<div class="input-group">
 							<input type="date" class="form-control" id="existencias" name="fecha_devolucion" required>
 							<span class="input-group-addon"><span class="glyphicon glyphiconasterisk"></span></span>
 						</div>
 					</div>
					
					
					<input type="submit" class="btn btn-info" value="Guardar" name="Guardar">
 					<a class="btn btn-danger" href="${contextPath}/prestamos.do?op=listar">Cancelar</a>
 				</form>
 			</div>
 		</div>
 	</div>
 </body>
</html>