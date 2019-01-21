<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%

	
	String id = (String) request.getParameter("id_lezione");
	int id_lezione = Integer.parseInt(id);
	
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
  	
  	<h1>Grazie per aver inviato il quesito</h1>
  	<p><a href="gestione_quesiti/InvioDomandaView.jsp?id_lezione=<%=id_lezione%>">Indietro</a></p> 
  	
  	</div>
  
  
  <div class="container signin">
  		&copy; 2018 Unisask Inc. All right reserved<br>  
  </div>

</body>
</html>