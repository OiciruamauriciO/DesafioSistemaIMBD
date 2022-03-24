<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
	<head>
	<meta charset="charset=ISO-8859-1">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
		<title>SISTEMA IMBD : DETALLE DEL SHOW</title>
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
	
		<h1>Título: ${show.showtitle}</h1>
		<h1>Red: ${show.network}</h1>
		
		<h2 class="font-weight-bold">Usuarios que ratean este show</h2>
		
		<table class="table table-dark">
		
			<thead>
				<tr>
					<td scope="col">Nombre</td>
					<td scope="col">Rating</td>
				</tr>
			</thead>
			
			<tbody>
				<c:forEach var="row" items="${showRatings}">
					<tr>
						<td scope="row">${row.user.username}</td>
						<td>${row.rating}</td>
					</tr>
				</c:forEach>
			</tbody>
			
		</table>
		
		<c:if test="${currentUser.id == creatorShow.id}">
			<a href="/shows/${show.id}/edit" class="btn btn-info">Editar</a>
		</c:if>
		
		<br/><br/>
		
		<form:form method="POST" action="/shows/${show.id}/add" modelAttribute="addRating">
		
			<form:hidden path="user.id" value="${currentUser.id}" />
			<form:hidden path="shows" value="${show.id}" />
			
			<div class="form-inline">
				<form:label path="rating">Dejar rating: </form:label>
				<div class="mx-3">
					<form:input type="number" min="1" max="5" path="rating" />
					<form:errors/>
				</div>			
			</div>
			
			<div class="">
				<input type="submit" value="Ratear" class="btn btn-primary" />
			</div>
		
		</form:form>
		
		<div class="mt-5 pb-5">
			<a href="/shows" class="btn btn-dark">Regresar</a>
		</div>		
		
	</div>
	
</body>
</html>