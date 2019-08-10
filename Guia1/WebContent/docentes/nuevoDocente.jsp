<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
 	<title>Nuevo docente</title>
 	<%@ include file='/cabecera.jsp' %>
 </head>
 
 <body>
 	<jsp:include page="/menu.jsp"/>
 	<div class="container">
 		<div class="row">
 			<h3>Nuevo docente</h3>
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
				
				<form role="form" action="${contextPath}/docentes.do" method="POST">
 					 <input type="hidden" name="op" value="insertar">
					 <div class="well well-sm"><strong><span class="glyphicon glyphicon-asterisk"></span>Campos requeridos</strong></div>
 						
 					 <div class="form-group">
						<label for="codigo">Codigo del docente</label>
 						<div class="input-group">
 							<input type="text" class="form-control" name="codigo" id="codigo" value="${docente.codigo_docente}" placeholder="Ingresa el codigo del docente" >
 							<span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
 						</div>
 					</div>
					
					<div class="form-group">
 						<label for="nombre">Nombre del docente</label>
 						<div class="input-group">
 							<input type="text" class="form-control" name="nombre" id="nombre" value="${docente.nombre_docente}" placeholder="Ingresa el nombre del docente" >
 							<span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
 						</div>
 					</div>
 		

					<input type="submit" class="btn btn-info" value="Guardar" name="Guardar">
 					<a class="btn btn-danger" href="${contextPath}/docentes.do?op=listar">Cancelar</a>
 				</form>
 			</div>
 		</div>
 	</div>
 </body>
</html>