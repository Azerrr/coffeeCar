<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Administrateur</title>
	</head>

	<body>
		<h2>Administrateur</h2>
		<div>
			<form method="post">
			<button type='submit' name='todo' value='retourAcceuil'>Retour Acceuil</button>		
			</form>
			
		
		<table>
			<tr>
			<form method="post">
				<td><label for="ajoutGabarit">Ajout Gabarit :</label></td>
				<td><input id="ajoutGabarit" type="text" name="ajoutGabarit" /></td>
				<td><button type='submit' name='todo' value='ajoutGabaritButton'>Ajout Gabarit</button></td>
			</form>
			</tr>
			
			
			
			<tr>
			<form method="post">
				<td><label for="ajoutVille">Ajout Ville :</label></td>
				<td><input id="ajoutVille" type="text" name="ajoutVille" /></td>
				<td><button type='submit' name='todo' value='ajoutVilleButton'>Ajouter Ville</button></td>
			</form>
			</tr>
			
			
			
		</table>

			
		
		
		</div>


	</body>
</html>