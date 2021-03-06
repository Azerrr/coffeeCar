<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Accueil</title>
<style>
	th, td{
		border-style: solid;
    	border-width: 0px 0px 1px 1px;
	}
	th:first-child, td:first-child{
    	border-width: 0px 0px 1px 0px;
	}
</style>
</head>

<body>
	
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
						<c:forEach items="${villes}" var="vi">
    						<option>${vi.ville}</option>
						</c:forEach>
						</select>
					</td>
				</tr>
				
				<tr>
					<td><label for="villeArrive">Ville d'Arrivé :</label></td>
					<td><select id="villeArrive" name="villeArrive">
						<c:forEach items="${villes}" var="vi">
    						<option>${vi.ville}</option>
						</c:forEach>
						</select>
					</td>
				</tr>
							
				<tr>
					<td><button type='submit' name='task' value='rechercheTrajet'>Rechercher Trajet</button></td>
				</tr>
				
			</table>
		
		</form>
		
		<br/>
		<div>
			<jsp:useBean id="resultatRecherche" type="java.util.ArrayList<String>" scope="request" />
			<form method="post">
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
			
			<c:forEach items="${resultatRecherche}" var="rech">
			
			<tr>
				<td>${rech.date}</td>
				<td>${rech.heure}</td>
				<td>${rech.villeDepart}</td>
				<td>${rech.etapes.get(0).getEtape()}</td>
				<td>${rech.etapes.get(0).getTarif()} € </td>
				<td>${rech.etapes.get(1).getEtape()}</td>
				<td>${rech.etapes.get(1).getTarif()} € </td>
				<td>${rech.etapes.get(2).getEtape()}</td>
				<td>${rech.etapes.get(2).getTarif()} € </td>
				<td>${rech.nbPlaces}</td>
				<td>${rech.typeVehicule}</td>
				<td>${rech.modele}</td>
				<% if(request.getSession().getAttribute("login") != null){%><td><button type='submit' name='reservation' value='${rech.id}'>Réserver</button></td><%} %>
			</tr>
			</c:forEach>
			</table>
			</form>
			
		</div>

				
		
	</div>
</body>
</html>