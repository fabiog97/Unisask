<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,application_logic_layer.gestione_utente.Utente, application_logic_layer.gestione_lezioni.Lezione" %>
    
<%
	Utente account = (Utente) request.getSession().getAttribute("account");
    
    int id_corso = Integer.valueOf(request.getParameter("id_corso"));
%>
<!DOCTYPE>
<html>
<head>
<link rel="stylesheet" href="../style.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
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
	
</header>

   
<div id="inserisci_lezione" align="center">
	<h1>Aggiungi Lezione</h1>
	
	<form name="AggiungiLezione" id="formLezioneView" action="../AggiungiLezione?id_corso=<%=id_corso%>" method="post" >
		<label for="nomeLezione"><b>Nome Lezione</b></label>
		<input id="nomelezione" type="text" name="nomeLezione" required >
		
		<label for="dataLezione"><b>Data Lezione</b></label>
		<input id="data" type="text" name="dataLezione" required>
	
		<label for="argomentoLezione"><b>Argomento Lezione</b></label>
		<p><textarea id="argomento" rows="3" cols="50" name="argomento_lezione"></textarea></p>
		
		<input class="registerbtn" type="submit" value="Aggiungi" tabindex="2">
	</form>
	
</div>

<div class="container2 signin2">
  		&copy; 2018 Unisask Inc. All right reserved<br>  
</div>
	
</body>
</html>