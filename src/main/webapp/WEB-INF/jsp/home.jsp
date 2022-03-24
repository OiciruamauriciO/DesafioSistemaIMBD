<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="charset=ISO-8859-1">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
		<title>SISTEMA IMBD : HOME</title>
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
	  			<a class="nav-link" href="/shows/new">Añadir un show</a>
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

	<div class="container">
	
		<div class="py-3">
			<h1 class="font-weight-bold">
				Bienvenido, <c:out value="${currentUser.username}"></c:out>
			</h1>
		</div>
		
		<h2>Shows de TV</h2>
		
		<table class="table table-dark">
			<thead>
				<tr>
					<td scope="col">Show</td>
					<td scope="col">Red</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${allShows}" var="show">
					<tr>
						<td scope="row">
							<a href="/shows/${show.id}" class="text-info font-weight-bold">${show.showtitle}</a>
						</td>
						<td>${show.network}</td>
					</tr>				
				</c:forEach>			
			</tbody>
		</table>
		
	</div>
	
		

</body>
</html>