<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,application_logic_layer.gestione_utente.Utente"%>
    
<%
 	Utente account = (Utente) request.getSession().getAttribute("account");
 
 	
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
	<a  href="VisualizzaProfiloView.jsp"><img  src="../images/LOGO_Unisask.png" width=150 ></a>
	
	<div id="Benvenuto" align="center">
	<a href="VisualizzaProfiloView.jsp" style="text-decoration:none; color:black;">
	<i class="fa fa-user" style="font-size: 35"></i>
		<p>Benvenuto <%=account.getUsername()%></p>
		<form action="../Logout" method="get" >
			<input class="tastologout" type="submit" value="Logout">
		</form>
	</a>
	</div>
</header>

<div class="container" align="center">
	<div id="profilo_view" align="center">
		
		<h2 id="nomeProfilo"><%=account.getNome()%> <%=account.getCognome()%></h2><br>
		<h3 id="generalita">Username:<span style="font-weight:lighter"> <%=account.getUsername()%></span></h3>
		<h3 id="generalita">E-mail:<span style="font-weight:lighter"> <%=account.getEmail()%></span></h3>
		<h3 id="generalita">Matricola:<span style="font-weight:lighter"> <%=account.getMatricola()%></span></h3>
		<h3 id="generalita">Nazionalità:<span style="font-weight:lighter"> <%=account.getNazionalita()%></span></h3>	
		<a href="ModificaPasswordView.jsp"><input  type="submit" value="Modifica Password"></a>
	</div>
	
	<p><a href="../VisualizzaCorsiView.jsp">Indietro</a></p>
</div>

<div class="container3 signin3">
  		&copy; 2018 Unisask Inc. All right reserved<br>  
  </div>
  
</body>
</html>