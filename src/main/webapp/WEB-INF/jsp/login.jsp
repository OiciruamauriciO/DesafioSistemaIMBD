<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
	<head>
	<meta charset="charset=ISO-8859-1">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
		<title>SISTEMA IMBD : LOGIN</title>
	</head>
<body>

	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	  <a class="navbar-brand" href="/">SHOWS DE TV</a>
	  <!-- 
	  <button class="navbar-toogler" type="button" data-toogle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toogle navigation">
	  	<span class="navbar-toogler-icon"></span>
	  </button>
	   -->
	  <div class="collapse navbar-collapse" id="navbarSupportedContent">
	  	<ul class="navbar-nav mr-auto">
	  		<li class="nav-item active">
	  			<a class="nav-link" href="/login">Ingresar<span class="sr-only">(actual)</span></a>
	  		</li>
	  		<li class="nav-item">
				<a class="nav-link" href="/registration">Registro</a>
	  		</li>
	  	</ul>
	  </div>	
	</nav>
	
	<div class="container">
		
		<div class="mt-3 text-center">
		
			<c:if test="${logoutMessage != null}">
				<c:out value="${logoutMessage}"></c:out>
			</c:if>
			
			<c:if test="${errorMessage != null}">
				<c:out value="${errorMessage}"></c:out>
			</c:if>
			
		</div>
		
		<h1>Ingreso</h1>
		
		<form:form method="POST" action="/login" modelAttribute="user">
			
			<div class="form-group">
				<label for="exampleInputEmail1">Correo electrónico</label>
				<form:input path="username" name="username" type="text" class="form-control" placeholder="Nombre de usuario" />
			</div>
			
			<div class="form-group">
				<label for="exampleInputPassword1">Contraseña</label>
				<form:password path="password" name="password" class="form-control" placeholder="Contraseña" />				
			</div>
			
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			
			<input type="submit" class="btn btn-primary" value="Ingresar" />
			
		</form:form>
		
	</div>

</body>
</html>