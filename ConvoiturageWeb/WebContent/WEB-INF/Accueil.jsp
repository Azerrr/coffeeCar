<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Accueil</title>
</head>

<body>
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
	<br/>
	<h2>Recherche trajet</h2>
	<div>
		<form method="post">
			<input type="date" name="date">
			<br/>
			<select name="villeDepart">
				<option>default</option>
			</select>
			<br/>
			<select name="villeArrivee">
				<option>default</option>
			</select>
			<br/>
		</form>
	</div>
</body>
</html>