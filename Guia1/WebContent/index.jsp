<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
    	<meta charset="ISO-8859-1">
        <title>Inicio</title>
        <%@ include file='/cabecera.jsp' %>
    </head>
    <body>
        <jsp:include page="/menu.jsp"/>
        <div class="container">
            <div class="row">
                <h2>Inventario de libros</h2>
            </div>
                <div class="row">
                    <div class="col-lg-3 col-md-6">
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                <div class="row">
                                    <div class="col-xs-3">
                                        <i class="glyphicon glyphicon-book huge"></i>
                                    </div>
                                    <div class="col-xs-9 text-right">
                                        <div class="huge">${requestScope.totallib}</div>
                                        <div><h4>Libros</h4></div>
                                    </div>
                                </div>
                            </div>
                            <div class="panel-footer">
                                <a href="${contextPath}/libros.do?op=listar">
                                    <span class="pull-left">Ver libros</span>
                                    <span class="pull-right"><i class="glyphicon glyphicon-arrow-right"></i></span>
                                    <div class="clearfix"></div>
                                    </a>
                            </div>       
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-6">
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                <div class="row">
                                    <div class="col-xs-3">
                                        <i class="glyphicon glyphicon-user huge"></i>
                                    </div>
                                    <div class="col-xs-9 text-right">
                                        <div class="huge">${requestScope.totalaut}</div>
                                        <div><h4>Autores</h4></div>
                                    </div>
                                </div>
                            </div>
                            <a href="${contextPath}/autores.do?op=listar">
                                <div class="panel-footer">
                                    <span class="pull-left">Ver autores</span>
                                    <span class="pull-right"><i class="glyphicon glyphicon-arrow-right"></i></span>
                                    <div class="clearfix"></div>
                                </div>
                            </a>
                        </div>
                    </div>
                    
                    
                    <div class="col-lg-3 col-md-6">
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                <div class="row">
                                    <div class="col-xs-3">
                                        <i class="glyphicon glyphicon glyphicon-education huge"></i>
                                    </div>
                                    <div class="col-xs-9 text-right">
                                        <div class="huge">${requestScope.totaledit}</div>
                                        <div><h4>Editoriales</h4></div>
                                    </div>
                                </div>
                            </div>                         
                            <div class="panel-footer">
                                <a href="${contextPath}/editoriales.do?op=listar">
                                    <span class="pull-left">Ver editoriales</span>
                                    <span class="pull-right"><i class="glyphicon glyphicon-arrow-right"></i></span>
                                    <div class="clearfix"></div>
                                    </a>
                            </div>
                         
                        </div>
                    </div>
                    
                    
                    <div class="col-lg-3 col-md-6">
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                <div class="row">
                                    <div class="col-xs-3">
                                        <i class="glyphicon glyphicon-folder-open huge"></i>
                                    </div>
                                    <div class="col-xs-9 text-right">
                                        <div class="huge">${requestScope.totalgen}</div>
                                        <div><h4>Genero</h4></div>
                                    </div>
                                </div>
                            </div>
                            
                            <div class="panel-footer">
                                <a href="${contextPath}/generos.do?op=listar">
                                    <span class="pull-left">Ver generos</span>
                                    <span class="pull-right"><i class="glyphicon glyphicon-arrow-right"></i></span>
                                    <div class="clearfix"></div>
                                    </a>
                             </div>
                            
                        </div>
                    </div>
                    
                    
                    <div class="col-lg-3 col-md-6">
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                <div class="row">
                                    <div class="col-xs-3">
                                        <i class="glyphicon glyphicon-user huge"></i>
                                    </div>
                                    <div class="col-xs-9 text-right">
                                        <div class="huge">${requestScope.totaldo}</div>
                                        <div><h4>Docentes</h4></div>
                                    </div>
                                </div>
                            </div>
                            <a href="${contextPath}/docentes.do?op=listar">
                                <div class="panel-footer">
                                    <span class="pull-left">Ver docentes</span>
                                    <span class="pull-right"><i class="glyphicon glyphicon-arrow-right"></i></span>
                                    <div class="clearfix"></div>
                                </div>
                            </a>
                        </div>
                    </div>
                    
                    
                    
                    <div class="col-lg-3 col-md-6">
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                <div class="row">
                                    <div class="col-xs-3">
                                        <i class="glyphicon glyphicon-folder-open huge"></i>
                                    </div>
                                    <div class="col-xs-9 text-right">
                                        <div class="huge">${requestScope.totalgen}</div>
                                        <div><h4>Prestamos</h4></div>
                                    </div>
                                </div>
                            </div>
                            
                            <div class="panel-footer">
                                <a href="${contextPath}/prestamos.do?op=listar">
                                    <span class="pull-left">Ver prestamos</span>
                                    <span class="pull-right"><i class="glyphicon glyphicon-arrow-right"></i></span>
                                    <div class="clearfix"></div>
                                    </a>
                             </div>
                            
                        </div>
                    </div>
                    
                    
                </div>
        </div>
                
    </body>
</html>
