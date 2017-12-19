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
				<td><input id="modeleVehicule" type="text" name="modeleVehicule" required="required" /></td>
			</tr>
		
			<tr>
				<td><label for="typesVehicules">Type de Véhicule :</label></td>
				<td><select id="typesVehicules" name="typesVehicules" required="required">
						<c:forEach items="${typesVehicules}" var="ci">
							<option>${ci.type}</option>						
						</c:forEach>
					</select>
				</td>
			</tr>
			
			<tr>
				<td><label for="date" >Date départ :</label></td>
				<td><input id="date" type="date" name="date" required="required"></td>
			</tr>
			
			<tr>
				<td><label for="time">Heure départ :</label></td>
				<td><input id="time" type="time" name="time" required="required"></td>
			</tr>
			
			<tr>
				<td><label for="villeDepart">Ville Départ</label></td>
				<td><select id="villeDepart" name="villeDepart" required="required">
						<c:forEach items="${villes}" var="ci">
    						<option>${ci.ville}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			
			<tr>
				<td><label>Etape 1</label></td>
				<td>
					<select id="etape1" name="etape1">
						<option>default</option>
						<c:forEach items="${villes}" var="ci">
    						<option>${ci.ville}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td><label for="tarif1">Tarif étape 1</label></td>
				<td><input id="tarif1" type="number" name="tarif1" min="0" max="200" required="required"></td>
			</tr>
			<tr>
				<td><label>Etape 2</label></td>
				<td>
					<select id="etape2" name="etape2">
						<option>default</option>
						<c:forEach items="${villes}" var="ci">
    						<option>${ci.ville}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td><label for="tarif2">Tarif étape 2</label></td>
				<td><input id="tarif2" type="number" name="tarif2" min="0" max="200" required="required"></td>
			</tr>
			<tr>
				<td><label>Etape 3</label></td>
				<td>
					<select id="etape3" name="etape3">
						<option>default</option>
						<c:forEach items="${villes}" var="ci">
    						<option>${ci.ville}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			
			<tr>
				<td><label for="tarif3">Tarif étape 3</label></td>
				<td><input id="tarif3" type="number" name="tarif3" min="0" max="200" required="required"></td>
			</tr>
			
			<tr>
				<td><label for="placesLibres">Nombre de Places</label></td>
				<td><input id="placesLibres" type="number" name="placesLibres" min="0" max="10" required="required"></td>
			</tr>	
			
			<tr>
				<td><button type='submit' name='todo' value='addTrajet'>Ajouter le Trajet</button></td>
			</tr>	
			
		</table>
		
		
	</form>
	
	</div>


</body>
</html>