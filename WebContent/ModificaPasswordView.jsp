<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,application_logic_layer.Utente"%>
    
 
 <%
 	Utente account = (Utente) request.getSession().getAttribute("account");
 
 	
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Unisask</title>
<link rel="stylesheet" href="style.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

</head>
<body>

<header>

	<div class="logo_header">
		<a  href="VisualizzaCorsiView.jsp"><img  src="images/LOGO_Unisask.png" width=150 ></a>
	</div>
	
	
	
	
	<div id="Benvenuto" align="center">
	<a href="VisualizzaProfiloView.jsp" style="text-decoration:none; color:black;">
	<i class="fa fa-user" style="font-size: 35"></i>
		<p>Benvenuto <%=account.getUsername()%></p>
		<form action="Logout" method="get" >
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
<form name="ModificaPassword"action="ModificaPassword?username=<%=account.getUsername()%>" method="post">

	
  <div class="container" align="center">
  
    <h1>Modifica password</h1>
    <hr>

    <label for="password"><b>Vecchia password</b></label>
    <input type="password" placeholder="Password" name="vecchia_password" required>
  

    <label for="password"><b>Nuova password</b></label>
    <input type="password" placeholder="Password" name="nuova_password" required>

	

    <button type="submit" class="registerbtn">Modifica</button>
    
    
    <p><a href="VisualizzaProfiloView.jsp">Indietro</a></p>
  </div>

  <div class="container signin">
    &copy; 2018 Unisask Inc. All right reserved<br>  
  </div>
</form>

</body>
</html>