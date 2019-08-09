<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Nuevo libro</title>
 	<%@ include file='/cabecera.jsp' %>
 	<link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/css/select2.min.css" rel="stylesheet" />
	<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/js/select2.min.js"></script>
</head>

<body>
	<jsp:include page="/menu.jsp"/>
 	<div class="container">
 		<div class="row">
 			<h3>Nuevo libro</h3>
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
		
				<form role="form" action="${contextPath}/libros.do" method="POST">
	 				<input type="hidden" name="op" value="modificar">
	 				<div class="well well-sm"><strong><span class="glyphicon glyphiconasterisk"></span>Campos requeridos</strong></div>
	
	 				<div class="form-group">
						<label for="codigo">Codigo del libro:</label>
	 					<div class="input-group">
	 						<input type="text" readonly class="form-control" name="codigo" id="codigo" value="${libro.codigoLibro}" placeholder="Ingresa el codigo del libro" >
	 						<span class="input-group-addon"><span class="glyphicon glyphiconasterisk"></span></span>
	 					</div>
	 				</div>
	
					<div class="form-group">
	 					<label for="nombre">Nombre del libro:</label>
	 					<div class="input-group">
	 						<input type="text" class="form-control" name="nombre" id="nombre" value="${libro.nombreLibro}" placeholder="Ingresa el nombre del libro" >
	 						<span class="input-group-addon"><span class="glyphicon glyphiconasterisk"></span></span>
	 					</div>
	 				</div>
	 				
					<div class="form-group">
	 					<label for="existencias">existencias:</label>
	 					<div class="input-group">
	 						<input type="text" class="form-control" id="existencias" value="${libro.existencias}" name="existencias" placeholder="Ingresa las existencias del libro">
	 						<span class="input-group-addon"><span class="glyphicon glyphiconasterisk"></span></span>
	 					</div>
	 				</div>
	 				
					<div class="form-group">
	 					<label for="precio">precio:</label>
	 					<div class="input-group">
	 						<input type="number" step="0.1" min="0" class="form-control" id="precio" value="${libro.precio}" name="precio" placeholder="Ingresa el precio del libro" >
	 						<span class="input-group-addon"><span class="glyphicon glyphiconasterisk"></span></span>
	 					</div>
	 				</div>
	 				
					<div class="form-group">
	 					<label for="codigoEditorial">Editorial:</label>
	 					<div class="input-group">
	 						<select name="codigoEditorial" id="codigoEditorial" class="form-control">
	 							<c:forEach items="${requestScope.listaEditoriales}" var="editorial">
	 								<option value="${editorial.codigoEditorial}">${editorial.nombreEditorial}</option>
	 							</c:forEach>
	 						</select>
	 						<span class="input-group-addon"><span class="glyphicon glyphiconasterisk"></span></span>
	 					</div>
	 				</div>
	 				
					<div class="form-group">
	 					<label for="codigoAutor">Autor:</label>
	 					<div class="input-group">
	 						<select name="codigoAutor" id="codigoAutor" class="form-control">
	 							<c:forEach var="autor" items="${requestScope.listaAutores}">
	 								<option value="${autor.codigoAutor}">${autor.nombreAutor}</option>
	 							</c:forEach>
	 						</select>
	 						<span class="input-group-addon"><span class="glyphicon glyphiconasterisk"></span></span>
	 					</div>
	 				</div>
	 				
					<div class="form-group">
	 					<label for="codigoGenero">Genero:</label>
	 					<div class="input-group">
	 						<select name="idGenero" id="codigoGenero" class="form-control">
	 							<c:forEach var="genero" items="${requestScope.listaGeneros}">
	 								<option value="${genero.idGenero}">${genero.nombreGenero}</option>
	 							</c:forEach>
	 						</select>
	 						<span class="input-group-addon"><span class="glyphicon glyphiconasterisk"></span></span>
						 </div>
	 				</div>
	 				
					<div class="form-group">
	 					<label for="descripcion">descripcion del libro:</label>
	 					<div class="input-group col-md-12">
	 						<textarea class="form-control" name="descripcion">
	 							${libro.descripcion}
	 						</textarea>
	 					</div>
	 				</div>
	 				
					<input type="submit" class="btn btn-info" value="Guardar" name="Guardar">
	 				<a class="btn btn-danger" href="${contextPath}/libros.do?op=listar">Cancelar</a>
	 			</form>
	 		</div>
	 	</div>
 	</div>
 
 <script>
 	$("#codigoAutor option[value="+ '${libro.codigoAutor}' +"]").attr("selected",true);
 	$("#codigoEditorial option[value="+ '${libro.codigoEditorial}'+"]").attr("selected",true);
 	$("#codigoGenero option[value="+ '${libro.idGenero}' +"]").attr("selected",true);
</script>
</html>