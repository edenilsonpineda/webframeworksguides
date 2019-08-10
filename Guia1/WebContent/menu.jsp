<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
    <nav class="navbar navbar-inverse">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Desplegar navegación</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
            <a class="navbar-brand" href="#">Ejemplo MVC</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
              <li class="active"><a href="${contextPath}/index.jsp">Inicio</a></li> 
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" 
                 role="button" aria-haspopup="true" 
                 aria-expanded="false">Autores <span class="caret"></span></a>
              <ul class="dropdown-menu">
                <li><a href="${contextPath}/autores.do?op=nuevo">Registrar autor</a></li>
                <li><a href="${contextPath}/autores.do?op=listar">Ver lista de autores</a></li>
              </ul>
            </li>
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" 
                 role="button" aria-haspopup="true" 
                 aria-expanded="false">Generos<span class="caret"></span></a>
              <ul class="dropdown-menu">
                <li><a href="${contextPath}/generos.do?op=nuevo">Registrar genero</a></li>
                <li><a href="${contextPath}/generos.do?op=listar">Ver lista de generos</a></li>
              </ul>
            </li>
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" 
                 role="button" aria-haspopup="true" 
                 aria-expanded="false">Libros<span class="caret"></span></a>
              <ul class="dropdown-menu">
                  <li><a href="${contextPath}/libros.do?op=nuevo">Registrar libro</a></li>
                <li><a href="${contextPath}/libros.do?op=listar">Ver lista de libros</a></li>
              </ul>
            </li>
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" 
                 role="button" aria-haspopup="true" 
                 aria-expanded="false">Editoriales<span class="caret"></span></a>
              <ul class="dropdown-menu">
                  <li><a href="${contextPath}/editoriales.do?op=nuevo">Registrar editorial</a></li>
                <li><a href="${contextPath}/editoriales.do?op=listar">Ver lista de editoriales</a></li>
              </ul>
            </li>
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" 
                 role="button" aria-haspopup="true" 
                 aria-expanded="false">Docentes<span class="caret"></span></a>
              <ul class="dropdown-menu">
                  <li><a href="${contextPath}/docentes.do?op=nuevo">Registrar docente</a></li>
                <li><a href="${contextPath}/docentes.do?op=listar">Ver lista de docentes</a></li>
              </ul>
            </li>
            
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" 
                 role="button" aria-haspopup="true" 
                 aria-expanded="false">Prestamos<span class="caret"></span></a>
              <ul class="dropdown-menu">
                  <li><a href="${contextPath}/prestamos.do?op=nuevo">Registrar prestamo</a></li>
                <li><a href="${contextPath}/prestamos.do?op=listar">Ver lista de prestamos</a></li>
              </ul>
            </li>
          </ul>
          
        </div>
      </div>
    </nav>
        
