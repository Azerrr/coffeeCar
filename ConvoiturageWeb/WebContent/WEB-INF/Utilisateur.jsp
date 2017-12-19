<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Utilisateur</title>
		
		<style>
			th, td{
				border-style: solid;
    			border-width: 0px 0px 1px 1px;
			}
		</style>
	</head>
	
	<body>
		<h2>Réservations</h2>
		<form method="post">
			<button type='submit' name='todo' value='retourAcceuil'>Retour Acceuil</button>		
			</form>
		
		<div>
		<jsp:useBean id="trajetReserve" type="java.util.List<String>" scope="request" />
			
		<table>
			<tr>
				<th>Date</th>
				<th>Heure</th>
				<th>Départ</th>
				<th>Etape 1</th>
				<th>Tarif 1</th>
				<th>Etape 2</th>
				<th>Tarif 2</th>
				<th>Etape 3</th>
				<th>Tarif 3</th>
				<th>Places Libres</th>
				<th>Gabarit</th>
				<th>Modèle</th>
			</tr>
		
		
			<c:forEach items="${trajetReserve}" var="ci">
				<tr>
					<td>${ci.date}</td>
					<td>${ci.heure}</td>
					<td>${ci.villeDepart}</td>	
					<td>${ci.etapes.get(0).getEtape()}</td>
					<td>${ci.etapes.get(0).getTarif()} € </td>
					<td>${ci.etapes.get(1).getEtape()}</td>
					<td>${ci.etapes.get(1).getTarif()} € </td>
					<td>${ci.etapes.get(2).getEtape()}</td>
					<td>${ci.etapes.get(2).getTarif()} € </td>
					<td>${ci.nbPlaces}</td>
					<td>${ci.typeVehicule}</td>
					<td>${ci.modele}</td>	
				</tr>
			</c:forEach>
		</table>


		</div>
		<br/>		
	</body>
</html>