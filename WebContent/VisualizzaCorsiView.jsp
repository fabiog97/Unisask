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
	<a  href="VisualizzaCorsiView.jsp"><img  src="images/LOGO_Unisask.png" width=150 ></a>
	
	<div id="Benvenuto">
		<p>Benvenuto<br>
		Filomena Ferrucci</p>
		<a href="home.jsp"><input class="tastologout" type="submit" value="Logout"></a>
	</div>
	
</header>
	
	<div id="contenitore_ricerca">
		<form action="Ricerca" method="post">
			<label id="testo_ricerca" for="ricerca"><b>Ricerca AQ</b></label>
	    	<input id="barra_ricerca" type="text" placeholder="Cerca" name="ricerca" required>
	    </form>
    </div>
    
    <div id="contenitore_link">
		<p><a href="">I miei corsi</a> |<a href="">Corsi Di Studio</a> |<a href=""> Risposte</a></p>
	</div>	

<div id="corsi_window">
	<ul>
	    <li>
	    		<div>
	    			<i class="fa fa-angle-right"></i>
	    			<a href="#">Ingegneria del Software</a>
	    			<div style= "float:right; font-family:futura"> 
	    				2 <i class="fa fa-question-circle" style= "font-size:20px"></i>
	    			</div>
	    		</div>
	    	</li>
	    	  <li>
	    		<div>
	    			<i class="fa fa-angle-right"></i>
	    			<a href="#">Gestione dei progetti software</a>
	    			<div style= "float:right; font-family:futura"> 
	    				3 <i class="fa fa-question-circle" style= "font-size:20px"></i>
	    			</div>
	    		</div>
	    	</li>    
  	</ul>
  	
  	
</div>

<div class="container2 signin2">
  		&copy; 2018 Unisask Inc. All right reserved<br>  
  </div>
	
	
</body>
</html>