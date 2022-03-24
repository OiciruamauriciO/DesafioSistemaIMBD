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
		<title>SISTEMA IMBD : EDIT</title>
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
	  	<ul class="navbar-nav ml-auto">
	  		<li class="nav-item mr-5">
	  			<c:if test="inShowId==false">
	  				<a class="nav-link" href="/shows/create">Añadir un show</a>
	  			</c:if>	  	
	  		</li>
	  		<li class="nav-item">
	  			<form id="logoutForm" method="POST" action="/logout">
	  				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token} }" />
	  				<input type="submit" value="Logout" class="btn btn-link text-secondary" />
	  			</form>
	  		</li>
	  	</ul>
	  </div>
	</nav>
	
	<div class="container mt-3">
	
		<h1></h1>
		<form:errors path="show.*" />
		
		<div class="text-center">
			<c:if test="${errors != null}">
				<c:out value="${errors}"></c:out>
			</c:if>
		</div>
		
		<form:form method="POST" action="/shows/${editShow.id}/edit" modelAttribute="editShow">
		
			<div class="form-group row pt-2">			
				<form:label path="showtitle" class="col-sm-2 col-form-label-sm">Título del show: </form:label>				
				<div class="col-sm-10">
					<form:input type="text" path="showtitle" class="form-control" />
				</div>				
			</div>
			
			<div class="text-center my-2">				
				<small><form:errors path="showtitle" /></small>
			</div>
			
			<div class="form-group row">
				<form:label path="network" class="col-sm-2 col-form-label col-form-label-sm">Red: </form:label>
				<div class="col-sm-10">
					<form:input type="text" path="network" class="form-control" />
				</div>				
			</div>
			
			<div class="text-center mt-2">
				<small><form:errors path="network" /></small>
			</div>
			
			<div class="text-center pt-4">
				<input type="submit" value="Editar show" class="btn btn-lg btn-primary" />
			</div>
			
		</form:form>
		
		<c:if test="${currentUser.id == creatorShow.id }">
			<a href="/shows/${editShow.id}/delete" class="btn btn-lg btn-danger">Delete</a>
		</c:if>
		
		<div class="mt-5 pb-5">
			<a href="/shows" class="btn btn-dark">Regresar</a>
		</div>
		
	</div>
	
</body>
</html>