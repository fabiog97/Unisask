<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,application_logic_layer.gestione_utente.Utente, application_logic_layer.gestione_corsi_insegnamento.CorsoInsegnamento" %>
    
    
 <%
 	Utente account = (Utente) request.getSession().getAttribute("account");
  	
 	Collection<CorsoInsegnamento> corsi = (Collection<CorsoInsegnamento>) request.getAttribute("corsi");
 	Collection<CorsoInsegnamento> corsi_insegnati = (Collection<CorsoInsegnamento>) request.getAttribute("corsi_insegnati");
	if (corsi == null && corsi_insegnati == null){
		response.sendRedirect("../VisualizzaCorsi");
		return;
	}
	
	

 %>
<!DOCTYPE>
<html>
<head>
<link rel="stylesheet" href="./style.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="SHORTCUT ICON" href="./images/LOGO_Unisask.png"> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Unisask</title>
</head>
<body>

<header>

	<div class="logo_header">
		<a  href="VisualizzaCorsiView.jsp"><img  src="../images/LOGO_Unisask.png" width=150 ></a>
	</div>
	
	
	
	
	<div id="Benvenuto" align="center">
	<a href="./gestione_utente/VisualizzaProfiloView.jsp" style="text-decoration:none; color:black;">
	<i class="fa fa-user" style="font-size: 35"></i>
		<p>Benvenuto <%=account.getUsername()%></p>
		<form action="./Logout" method="get" >
			<input class="tastologout" type="submit" value="Logout">
		</form>
	</a>
	</div>
	
	<%
		if(!account.getUsername().equals("admin"))
		{
	%>
	
	<div id="contenitore_ricerca" align="center">
		<form action="Ricerca" method="post" >
			<h3>Ricerca AQ</h3>
		    	<input id="barra_ricerca" type="text" placeholder="Cerca" name="ricerca">
	    </form>
    </div>
	
	<%
		}
	%>
	
</header>
	

    	
    <div id="contenitore_link" align="center">
    <%
		if(account.getTipo().equals("docente"))
		{
	%>
		<p><a href="">I miei corsi</a>|<a href=""> Domande</a></p>
	<%
		}
	%>
		
	</div>	

<div id="corsi_window">
	<ul>
			<%
				if(account.getTipo().equals("docente"))
				{
			    		if(corsi_insegnati.size() > 0)
			    		{
				    		Iterator<CorsoInsegnamento> it = corsi_insegnati.iterator();
						while(it.hasNext())
						{
							CorsoInsegnamento corso = (CorsoInsegnamento) it.next();
		
			%>
						    <li>
						    		<div>
						    			<i class="fa fa-angle-right"></i>
						    			<a href="#"><%=corso.getNome()%></a>
						    			<div style= "float:right; font-family:futura"> 
						    				2 <i class="fa fa-question-circle" style= "font-size:20px"></i>
						    			</div>
						    		</div>
						    	</li>
	    	  <%		
	    	  				}
	    	  			}
			    } 
	    	  %>
  	</ul>
  	
  	
</div>

<div class="container2 signin2">
  		&copy; 2018 Unisask Inc. All right reserved<br>  
  </div>
	
	
</body>
</html>