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

	<div class="logo_header">
		<a  href="VisualizzaCorsiView.jsp"><img  src="images/LOGO_Unisask.png" width=150 ></a>
	</div>
	
	
	
	
	<div id="Benvenuto">
	<i class="fa fa-user" style="font-size: 35"></i>
		<p>Benvenuto<br>
		Admin</p>
		<a href="home.jsp"><input class="tastologout" type="submit" value="Logout"></a>
	</div>
	
	
</header>

<div id="inserisci_corso" align="center">
	<h1>Aggiungi Corso</h1>
  	<form id="formCorsi" action="" method="post">
	  
	    <label for="nomeCorso"><b>Nome Corso</b></label>
	    <input type="text" name="nomeCorso" required >
	
	    <label for="corsoDiLaurea"><b>Corso di laurea</b></label>
	    	<div class="tendinaCorsoLaurea" >
				<select name="selectCorsoLaurea">
					<option >Magistrale</option>
					<option >Triennale</option>
				</select>
			</div>
	    
	    <label for="docente"><b>Docente</b></label>
	    	<div class="tendinaDocenti" >
				<select name="selectDocenti">
					<option >Andrea Francesco Abate</option>
					<option >Filomena Ferrucci</option>
					<option >Alberto Negro</option>
					<option >Rosalba Zizza</option>
					<option >Roberto De Prisco</option>
					<option >Giuliana Vitiello</option>
					<option >Clelia De Felice</option>
					<option >Salvatore La Torre</option>
				</select>
			</div>
	    
	    
	    <label for="annoAccademico"><b>Anno Accademico</b></label>
	    	<div class="tendinaAnnoAccademico" >
				<select name="selectAnnoAccademico">
					<option >2018/2019</option>
					<option >2017/2018</option>
					<option >2016/2017</option>
					<option >2015/2016</option>
					<option >2014/2015</option>
				</select>
			</div>
			
		 <label for="annoDiStudio"><b>Anno di studio</b></label>
	    	<div class="tendinaAnnoDiStudio" >
				<select name="selectAnnoDiStudio">
					<option >Primo anno</option>
					<option >Secondo anno</option>
					<option >Terzo anno</option>
				</select>
			</div>
			
			<label for="semestre"><b>Semestre</b></label>
	    	<div class="tendinaSemestre" >
				<select name="selectSemestre">
					<option >Primo semestre</option>
					<option >Secondo semestre</option>
				</select>
				
			</div>
			
			<input class="tastoAggiungi" type="submit" value="Aggiungi">
	  
</form>
  	
</div>

<div class="container4 signin4">
  		&copy; 2018 Unisask Inc. All right reserved<br>  
 </div>
 
 <div id="racchiudi_facebook_instagram1" align="right">
				<a href="https://www.facebook.com/" target="_blank"><img src="images/facebook.png" width="20"></a>
				<a href="https://www.instagram.com/" target="_blank"><img src="images/instagram.png" width="19"></a>
			</div>
	
	
</body>
</html>