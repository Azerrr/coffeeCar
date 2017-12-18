<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Accueil</title>
</head>

<body>
	<form method="post">	
		<button type='submit' name='task' value='initButton'>initButton</button>
		</form>
	
	<% 
	if(request.getSession().getAttribute("login") != null){
	%>
		<h2>Bienvenu <%= request.getSession().getAttribute("login") %></h2>
		
		<form method="post">
			<button type='submit' name='todo' value='disconnect'>Déconnexion</button>		
		</form>
	
		<%
		/*If admin*/
		if((int)request.getSession().getAttribute("role") <= 1){
		%>
		<form method="post">	
		<button type='submit' name='todo' value='initButton'>initButton</button>
		</form>
		<form method="post">
			<button type='submit' name='todo' value='admin'>Page Administrateur</button>		
		</form>
		<%
		}
		%>

		<form method="post">
			<button type='submit' name='todo' value='conducteur'>Page Conducteur</button>		
		</form>
		<form method="post">
			<button type='submit' name='todo' value='user'>Mes Trajets</button>		
		</form>

	<%	
	}else {
	%>
	<h2>Connexion</h2>
	<div>
		<form method="post">
			Login : <br/><input type='text' name='login'/>
			<br/>
			Password : <br/><input type="password" name='passwd'/>
			<br/>
			<button type='submit' name='todo' value='connect'>Connexion</button>
		</form>
	</div>
	<%
	}
	%>
	
	<br/>
	<h2>Recherche trajet</h2>
	<div>
		<!--On récupère les villes et les types de véhicules disponibles dans la base de donnée-->
		<jsp:useBean id="villes" type="java.util.ArrayList<String>" scope="request" />
		<jsp:useBean id="typesVehicules" type="java.util.ArrayList<String>" scope="request" />
		<form method="post">
			<table>
				<tr>
					<td><label for="date">Date :</label></td>
					<td><input id="date" type="date" name="date"></td>
				</tr>
				
				<tr>
					<td><label for="villeDepart">Ville de Départ :</label></td>
					<td><select id="villeDepart" name="villeDepart">
						<c:forEach items="${villes}" var="ci">
    						<option>${ci.ville}</option>
						</c:forEach>
						</select>
					</td>
				</tr>
				
				<tr>
					<td><label for="villeArrive">Ville d'Arrivé :</label></td>
					<td><select id="villeArrive" name="villeArrive">
						<c:forEach items="${villes}" var="ci">
    						<option>${ci.ville}</option>
						</c:forEach>
						</select>
					</td>
				</tr>
							
				<tr>
					<td><button type='submit' name='task' value='rechercheTrajet'>Rechercher Trajet</button></td>
				</tr>
				
			</table>
		
		</form>
		
		
		<div>
			<jsp:useBean id="resultatRecherche" type="java.util.ArrayList<String>" scope="request" />
			<form method="post">
			<table>
			
			<c:forEach items="${resultatRecherche}" var="ci">
			
			<tr>
				<td>${ci.date}</td>
				<td>${ci.heure}</td>
				<td>${ci.villeDepart}</td>
				<td>${ci.villeArrive}</td>
				<td>${ci.tarifTotal}</td>
				<td>${ci.nbPlaces}</td>
				<td>${ci.typeVehicule}</td>
				<td>${ci.modele}</td>
				<td><button type='submit' name='reservation' value='${ci.id}'>Réserver</button></td>
			</tr>
			</c:forEach>
			</table>
			</form>
			
		</div>

				
		
	</div>
</body>
</html>