<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
 	<title>Nuevo editorial</title>
 	<%@ include file='/cabecera.jsp' %>
 </head>
 
 <body>
 	<jsp:include page="/menu.jsp"/>
 	<div class="container">
 		<div class="row">
 			<h3>Nuevo editorial</h3>
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
				
				<form role="form" action="${contextPath}/editoriales.do" method="POST">
 					 <input type="hidden" name="op" value="insertar">
					 <div class="well well-sm"><strong><span class="glyphicon glyphicon-asterisk"></span>Campos requeridos</strong></div>
					
					<div class="form-group">
 						<label for="nombre">Codigo del editorial</label>
 						<div class="input-group">
 							<input type="text" class="form-control" name="codigo" id="codigo" value="${editorial.codigoEditorial}" placeholder="Ingresa el codigo del editorial" >
 							<span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
 						</div>
 					</div>
					
					<div class="form-group">
 						<label for="nombre">Nombre del editorial</label>
 						<div class="input-group">
 							<input type="text" class="form-control" name="nombre" id="nombre" value="${editorial.nombreEditorial}" placeholder="Ingresa el nombre del editorial" >
 							<span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
 						</div>
 					</div>
 					
					<div class="form-group">
 						<label for="contacto">Contacto</label>
 						<div class="input-group">
 							<input type="text" class="form-control" id="contacto" value="${editoral.contacto}" name="contacto" placeholder="Ingresa el contacto del editorial">
 							<span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
 						</div>
 					</div>
 					
 					<div class="form-group">
 						<label for="contacto">Teléfono</label>
 						<div class="input-group">
 							<input type="text" class="form-control" id="telefono" value="${editorial.telefono}" name="telefono" placeholder="Ingresa la telefono del editorial">
 							<span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
 						</div>
 					</div>

					<input type="submit" class="btn btn-info" value="Guardar" name="Guardar">
 					<a class="btn btn-danger" href="${contextPath}/editoriales.do?op=listar">Cancelar</a>
 				</form>
 			</div>
 		</div>
 	</div>
 </body>
</html>