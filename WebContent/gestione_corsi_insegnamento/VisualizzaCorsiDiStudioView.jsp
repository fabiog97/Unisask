<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,application_logic_layer.gestione_utente.Utente, application_logic_layer.gestione_corsi_insegnamento.CorsoInsegnamento" %>
    
    
    
 <%
 	Utente account = (Utente) request.getSession().getAttribute("account");
  	
 	Collection<CorsoInsegnamento> corsi = (Collection<CorsoInsegnamento>) request.getAttribute("corsi");
 	Collection<CorsoInsegnamento> corsi_iscritti = (Collection<CorsoInsegnamento>) request.getAttribute("corsi_iscritti");
 	
 	if (corsi == null ){
		response.sendRedirect("../VisualizzaCorsi?action=corsi_di_studio");
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
		<a  href="VisualizzaCorsiView.jsp"><img  src="./images/LOGO_Unisask.png" width=150 ></a>
	</div>
	
	<div id="Benvenuto" align="center">
	<%
		if(!account.getTipo().equals("admin"))
		{
	%>
			<a href="./gestione_utente/VisualizzaProfiloView.jsp" style="text-decoration:none; color:black;">
	<%	
		}
	%>
	
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
	
	
	
	<%
		if(account.getTipo().equals("studente"))
		{
	%>	<div id="contenitore_link" align="center">
			<p><a href="VisualizzaCorsi?action=<%="i_miei_corsi"%>" >I miei corsi</a> | <a class="active" href="VisualizzaCorsi?action=<%="corsi_di_studio"%>">Corsi di studio</a> | <a href="">Risposte</a></p>
		</div>	
	<%
		}
	%>
		
	

<div id="corsi_window">
		<ul>
			
	    	<%
	    	  	int flag = 0;
				if(corsi.size() > 0)
			    {
				    Iterator<CorsoInsegnamento> it = corsi.iterator();
					while(it.hasNext())
					{
						CorsoInsegnamento corso = (CorsoInsegnamento) it.next();
						flag = 0;
			%>
						<li>
							<div>
							<% 
								for(CorsoInsegnamento corso_iscritto : corsi_iscritti)
								{			
							    	if(corso.getNome().equals(corso_iscritto.getNome()))
							    	{
							    		%> 
									    	<i class="fa fa-angle-right"></i>
							   				<a href=""><%=corso.getNome()%></a>
									    	<div style= "float:right; font-family:futura">
							    				<a href="DisiscrizioneCorso?id_corso=<%=corso_iscritto.getId()%>&id_utente=<%=account.getId()%>"><input class="tastorispondi" type="submit" value="Disiscrivimi"></a>
							    			</div>
							    		<%
							    			flag = 1;
							    	}	
								}
							
							%>
							   
								
							<% 
							if(flag!=1)
							{
							%>
								<i class="fa fa-angle-right"></i>
							    <a href=""><%=corso.getNome()%></a>
							<%
							} 
							%>
							
							<div style= "float:right; font-family:futura">
								<% 
								if(flag!=1)
								{ 
								%>
								   <a href="IscrizioneCorso?id_corso=<%=corso.getId()%>&id_utente=<%=account.getId()%>"><input class="tastorispondi" type="submit" value="Iscrivimi"></a>
								<%
								} 
							%>
							</div>
							  	
							    	
					</div>		
				</li>
								
		  	<%								
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