<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
			<ul>
				<c:forEach items="${listeReservations}" var="reserv">
					<li>${reserv.villeDepart}-${reserv.villeArrivee}</li>
				</c:forEach>
			</ul>
		</div>
		<br/>		
	</body>
</html>