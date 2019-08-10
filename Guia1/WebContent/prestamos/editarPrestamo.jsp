<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
 <head>
 	<title>Modificar Prestamo</title>
 	<%@ include file='/cabecera.jsp' %>
 </head>
 
 <body>
 	<jsp:include page="/menu.jsp"/>
 	<div class="container">
 		<div class="row">
 			<h3>Modificar Prestamo</h3>
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
	 				<input type="hidden" name="op" value="modificar">
	 				<div class="well well-sm"><strong><span class="glyphicon glyphiconasterisk"></span>Campos requeridos</strong></div>
	 					
					<div class="form-group">
						<label for="codigo">Codigo del prestamo</label>
						<div class="input-group">
							<input type="text" readonly class="form-control" name="codigo_prestamo" id="codigo" value="${prestamo.codigo_prestamo}" placeholder="Ingresa el codigo del prestamo" >
							<span class="input-group-addon"><span class="glyphicon glyphiconasterisk"></span></span>
						</div>
					</div>
					
					<div class="form-group">
	 					<label for="nombre">Libro</label>
	 					<div class="input-group">
	 						<input type="text" readonly class="form-control" name="codigo_libro" id="codigo_libro" value="${prestamo.codigo_libro}" placeholder="Ingresa el nombre del libro" >
	 						<span class="input-group-addon"><span class="glyphicon glyphiconasterisk"></span></span>
	 					</div>
	 				</div>
					
					<div class="form-group">
	 					<label for="existencias">Docente</label>
	 					<div class="input-group">
	 						<input type="text" readonly class="form-control" id="existencias" value="${prestamo.codigo_docente}" name="existencias" placeholder="Ingresa las existencias del libro">
	 						<span class="input-group-addon"><span class="glyphicon glyphiconasterisk"></span></span>
	 					</div>
	 				</div>
	
					<div class="form-group">
			 			<label for="precio">Fecha Prestamo</label>
	 					<div class="input-group">
	 						<input type="text" readonly class="form-control" id="fecha_prestamo" value="${prestamo.fecha_prestamo}" name="fecha_prestamo" placeholder="Ingresa el precio del libro" >
	 						<span class="input-group-addon"><span class="glyphicon glyphiconasterisk"></span></span>
	 					</div>
	 				</div>
	
					<div class="form-group">
		 				<label for="codigoEditorial">Fecha Devolución</label>
	 					<div class="input-group">
	 						<input type="text" class="form-control" id="fecha_devolucion" value="${prestamo.fecha_devolucion}" name="fecha_devolucion" placeholder="Ingresa el precio del libro" >
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