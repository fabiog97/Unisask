<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<link rel="stylesheet" href="../style.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="SHORTCUT ICON" href="./images/LOGO_Unisask.png"> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Unisask</title>
</head>
<body>

<header>

	<div class="logo_header">
		<a  href="VisualizzaCorsiView.jsp"><img  src="./images/LOGO_Unisask.png" width=150 ></a>
	</div>
	
	<div id="Benvenuto" align="center">
		<a href="./gestione_utente/VisualizzaProfiloView.jsp" style="text-decoration:none; color:black;">
	<i class="fa fa-user" style="font-size: 35"></i>
		<p>Benvenuto Antonio</p>
		<form action="./Logout" method="get" >
			<input class="tastologout" type="submit" value="Logout">
		</form>
	</a>
	</div>

	<div id="contenitore_ricerca" align="center">
			<form action="Ricerca" method="post" >
				<h3>Ricerca AQ</h3>
		    		<input id="barra_ricerca" type="text" placeholder="Cerca" name="ricerca">
	    		</form>
		</div>
	

</header>


  
    <div id="contenitore_link" align="center">
		<p><a href="">I miei corsi</a> |<a href="">Corsi Di Studio</a> |<a href=""> Risposte</a></p>
	</div>
	
<div id="profilo_view" align="center">
	
	<div align="center">
		<h2>Introduzione al Software</h2>
	
		<h5>Prodotto e processo software</h5>
		<h5>Fondamenti di Ingegneria del software</h5>
		
		<h5>Domanda</h5>
		<p><textarea rows="7" cols="50" name="argomento_lezione"></textarea></p>
		
		<a href="home.jsp"><input class="tastoRispondi" type="submit" value="Invia domanda"></a>
		</div>

</div>

<div class="container2 signin2">
  		&copy; 2018 Unisask Inc. All right reserved<br>  
  </div>
	
	<div id="racchiudi_facebook_instagram1" align="right">
				<a href="https://www.facebook.com/" target="_blank"><img src="images/facebook.png" width="20"></a>
				<a href="https://www.instagram.com/" target="_blank"><img src="images/instagram.png" width="19"></a>
			</div>
</body>
</html>