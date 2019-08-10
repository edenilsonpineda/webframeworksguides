<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
 	<title>Nuevo genero</title>
 	<%@ include file='/cabecera.jsp' %>
 </head>
 
 <body>
 	<jsp:include page="/menu.jsp"/>
 	<div class="container">
 		<div class="row">
 			<h3>Nuevo genero</h3>
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
				
				<form role="form" action="${contextPath}/generos.do" method="POST">
 					 <input type="hidden" name="op" value="insertar">
					 <div class="well well-sm"><strong><span class="glyphicon glyphicon-asterisk"></span>Campos requeridos</strong></div>
					
					<div class="form-group">
 						<label for="nombre">Nombre del genero</label>
 						<div class="input-group">
 							<input type="text" class="form-control" name="nombre" id="nombre" value="${generos.nombreGenero}" placeholder="Ingresa el nombre del genero" >
 							<span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
 						</div>
 					</div>
 					
					<div class="form-group">
 						<label for="contacto">Descripción:</label>
 						<div class="input-group">
 							<input type="text" class="form-control" id="descripcion" value="${generos.descripcion}" name="descripcion" placeholder="Ingresa la descripcion del genero">
 							<span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
 						</div>
 					</div>

					<input type="submit" class="btn btn-info" value="Guardar" name="Guardar">
 					<a class="btn btn-danger" href="${contextPath}/generos.do?op=listar">Cancelar</a>
 				</form>
 			</div>
 		</div>
 	</div>
 </body>
</html>