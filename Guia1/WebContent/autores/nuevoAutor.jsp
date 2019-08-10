<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<title>New Author</title>
	<jsp:include page="/cabecera.jsp" />
</head>
<body>
	<jsp:include page="/menu.jsp" />
	<div class="container">
		<div class="row">
			<h2>Nuevo Autor</h2>
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
				<form action="${contextPath}/autores.do" method="POST">
					<input type="hidden" name="op" value="insertar">
					<div class="well well-sm">
						<strong><span class="glyphicon glyphicon-asterisk"></span>Campos requeridos</strong>
					</div>
					<div class="form-group">

						<label for="codigo">Codigo del autor</label>
						<div class="input-group">
							<input type="text" class="form-control" name="codigo" id="codigo" value="${autor.codigoAutor}" placeholder="Ingresa el codigo del autor"> 
							<span class="input-group-addon">
								<span class="glyphicon glyphicon-asterisk"></span>
							</span>
						</div>
					</div>
					<div class="form-group">
						<label for="nombre">Nombre del autor</label>
						<div class="input-group">
							<input type="text" class="form-control" name="nombre" id="nombre" value="${autor.nombreAutor}" placeholder="Ingresa el nombre del autor"> <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
						</div>
					</div>
					<div class="form-group">
						<label for="contacto">Nacionalidad del autor:</label>
						<div class="input-group">
							<input type="text" class="form-control" id="contacto" value="${autor.nacionalidad}" name="nacionalidad" placeholder="Ingresa la nacionalidad del autor"> <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
						</div>
					</div>

					<input type="submit" class="btn btn-info" value="Guardar" name="Guardar"> <a class="btn btn-danger" href="${contextPath}/autores.do?op=listar">Cancelar</a>
				</form>
			</div>
		</div>
	</div>
</body>
</html>