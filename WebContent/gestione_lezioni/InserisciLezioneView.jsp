<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<link rel="stylesheet" href="style.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="SHORTCUT ICON" href="images/LOGO_Unisask.png"> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Unisask</title>
</head>
<body>

<header>
	<a  href="InserisciLezioneView"><img  src="images/LOGO_Unisask.png" width=150 ></a>
	
	<div id="Benvenuto">
		<p>Benvenuto<br>
		Filomena Ferrucci</p>
		<a href="home.jsp"><input class="tastologout" type="submit" value="Logout"></a>
	</div>
	
</header>

    <div id="contenitore_link_inserisciLezioneView" align="center">
		<p><a href="">I miei corsi</a> | <a href="">Domande</a></p>
	</div>	

<div id="inserisci_lezione" align="center">
	<h1>Aggiungi Lezione</h1>
	
	<form id="formLezioneView" action="" method="post">

	<label for="nomeLezione"><b>Nome Lezione</b></label>
	<input id="nomelezione" type="text" name="nomeLezione" required >
	
	<label for="dataLezione"><b>Data Lezione</b></label>
	<input id="data" type="text" name="dataLezione" required>

	
	<label for="argomentoLezione"><b>Argomento Lezione</b></label>
	<p><textarea id="argomento" rows="4" cols="50" name="argomento_lezione"></textarea></p>
	</form>
	
	<input class="registerbtn" type="submit" value="Aggiungi">
	
</div>

<div class="container2 signin2">
  		&copy; 2018 Unisask Inc. All right reserved<br>  
  </div>


	
</body>
</html>