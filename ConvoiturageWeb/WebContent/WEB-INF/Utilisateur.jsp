<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Utilisateur</title>
	</head>
	
	<body>
		<h2>Réservations</h2>
		<form method="post">
			<button type='submit' name='todo' value='retourAcceuil'>Retour Acceuil</button>		
			</form>
		
		<div>
		<jsp:useBean id="trajetReserve" type="java.util.List<String>" scope="request" />
			
		<table>
			<c:forEach items="${trajetReserve}" var="ci">
				<tr>
					<td>${ci.id}</td>
					<td>${ci.date}</td>
					<td>${ci.heure}</td>
					<td>${ci.villeDepart}</td>	
					<td>${ci.villeArrive}</td>	
					<td>${ci.tarifTotal}</td>
					<td>${ci.typeVehicule}</td>
					<td>${ci.modele}</td>	
				</tr>
			</c:forEach>
		</table>


		</div>
		<br/>		
	</body>
</html>