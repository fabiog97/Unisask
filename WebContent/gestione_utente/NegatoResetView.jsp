<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String flag = (String) request.getAttribute("flag");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Unisask</title>
<link rel="stylesheet" href="./style.css">
</head>
<body>


	<div class="logo" align="center">
		<img src="./images/LOGO_Unisask.png" width="200">
	</div>
  
  	<div class="container" align="center">
  	<h1>Ci dispiace</h1>
  	<% if(flag.equals("modifica"))
  		{
  	%>
  	<p>La vecchia password inserita non è corretta</p>
  	<p><a href="gestione_utente/ModificaPasswordView.jsp">Indietro</a></p>
  	<%
  	}else{
  	%>
  	<p>Non abbiamo trovato nessun username associato ai nostri utenti</p>
  	<p><a href="gestione_utente/ResetPasswordView.html">Indietro</a></p>
  	<%} %>
  	
  	</div>
  
  
  <div class="container signin">
  		&copy; 2018 Unisask Inc. All right reserved<br>  
  </div>

</body>
</html>