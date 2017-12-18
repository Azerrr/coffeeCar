<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Conducteur</title>
</head>

<body>
	<h2>Créer un Trajet</h2>
	
	<form method="post">
			<button type='submit' name='todo' value='retourAcceuil'>Retour Acceuil</button>		
	</form>
	
	<div>
	<!--On récupère les villes et les types de véhicules disponibles dans la base de donnée-->
		<jsp:useBean id="villes" type="java.util.ArrayList<String>" scope="request" />
		<jsp:useBean id="typesVehicules" type="java.util.ArrayList<String>" scope="request" />
	<form method="post">
		<table>
			<tr>
				<td><label for="modeleVehicule">Modèle du véhicule :</label></td>
				<td><input id="modeleVehicule" type="text" name="modeleVehicule" /></td>
			</tr>
		
			<tr>
				<td><label for="typesVehicules">Type de Véhicule :</label></td>
				<td><select id="typesVehicules" name="typesVehicules">
						<c:forEach items="${typesVehicules}" var="ci">
							<option>${ci.type}</option>						
						</c:forEach>
					</select>
				</td>
			</tr>
			
			<tr>
				<td><label for="date" >Date départ :</label></td>
				<td><input id="date" type="date" name="date"></td>
			</tr>
			
			<tr>
				<td><label for="time">Heure départ :</label></td>
				<td><input id="time" type="time" name="time"></td>
			</tr>
			
			<tr>
				<td><label for="villeDepart">Ville Départ</label></td>
				<td><select id="villeDepart" name="villeDepart">
						<c:forEach items="${villes}" var="ci">
    						<option>${ci.ville}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			
			<tr>
				<td><label for="villeArrive">Ville Arrivé</label></td>
				<td><select id="villeArrive" name="villeArrive">
						<c:forEach items="${villes}" var="ci">
    						<option>${ci.ville}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			
			<tr>
				<td><label>Etapes</label></td>
				<td></td>
			</tr>
			
			<tr>
				<td><label for="tarif">Tarifs</label></td>
				<td><input id="tarif" type="number" name="tarif" min="0" max="200"></td>
			</tr>
			
			<tr>
				<td><label for="placesLibres">Nombre de Places</label></td>
				<td><input id="placesLibres" type="number" name="placesLibres" min="0" max="10"></td>
			</tr>		
			
		</table>
	</form>
	
	</div>


</body>
</html>