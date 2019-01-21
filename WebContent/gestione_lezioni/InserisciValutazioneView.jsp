<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,application_logic_layer.gestione_utente.Utente, application_logic_layer.gestione_lezioni.Lezione, application_logic_layer.gestione_lezioni.Lezione, storage_layer.LezioneDao" %>
    
    
<%
 	Utente account = (Utente) request.getSession().getAttribute("account");

 	
 	
 	String id = request.getParameter("id_lezione");
 	int idLezione  = Integer.parseInt(id);
	 
 	
 	Lezione lezione = LezioneDao.getLezioneById(idLezione);
 	
 %>
<!DOCTYPE>
<html>
<head>
<link rel="stylesheet" href="../style.css">

<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" >
<link rel="SHORTCUT ICON" href="../images/LOGO_Unisask.png"> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Unisask</title>
</head>
<body>

<header>

	<div class="logo_header">
		<a  href="#"><img  src="../images/LOGO_Unisask.png" width=150 ></a>
	</div>
	
	<div id="Benvenuto" align="center">
	<i class="fa fa-user" style="font-size: 35"></i>
		<p>Benvenuto <%=account.getUsername()%></p>
		<form action="../Logout" method="get" >
			<input class="tastologout" type="submit" value="Logout">
		</form>
	
	</div>
</header>
	


    <%
		if(account.getTipo().equals("docente"))
		{
	%>	<div id="contenitore_link" align="center">
			<p><a href="VisualizzaCorsi?action=<%="i_miei_corsi"%> class="active">I miei corsi</a> | <a href=""> Domande</a></p>
		</div>
		
		
	<%
		}
	%>
	
	<%
		if(account.getTipo().equals("studente"))
		{
	%>	<div id="contenitore_link" align="center">
			<p><a href="../VisualizzaCorsi?action=<%="i_miei_corsi"%>" class="active">I miei corsi</a> | <a href="VisualizzaCorsi?action=<%="corsi_di_studio"%>">Corsi di studio</a> | <a href="">Domande</a></p>
		</div>	
	<%
		}
	%>
		
	<div id="profilo_view">
	
	<div align="center">
		<h2><%=lezione.getNome()%></h2>
	
		<h5><%=lezione.getDescrizione()%></h5>
		
	
		<h5>Valutazione</h5>
		
		<form name="InvioValutazione" action="../InserisciValutazioneLezione?id_lezione=<%=idLezione%>" method="post">
		<div class="rate" >
		  <input type="radio" id="star5" name="rate" value="5" />
		  <label for="star5" title="text">5 stars</label>
		  <input type="radio" id="star4" name="rate" value="4" />
		  <label for="star4" title="text">4 stars</label>
		  <input type="radio" id="star3" name="rate" value="3" />
		  <label for="star3" title="text">3 stars</label>
		  <input type="radio" id="star2" name="rate" value="2" />
		  <label for="star2" title="text">2 stars</label>
		  <input type="radio" id="star1" name="rate" value="1" />
		  <label for="star1" title="text">1 star</label>
		</div>
		<input class="tastoRispondi" type="submit" value="Invia valutazione">
		</form>
		
		
	</div>

</div>




<div class="container2 signin2">
  		&copy; 2018 Unisask Inc. All right reserved<br>  
  </div>
	
	
</body>
</html>