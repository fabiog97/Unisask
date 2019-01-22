<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,application_logic_layer.gestione_utente.Utente, application_logic_layer.gestione_lezioni.Lezione, application_logic_layer.gestione_quesiti.Quesito"%>
    
<%
	Utente account = (Utente) request.getSession().getAttribute("account");
	Collection<Quesito> quesiti = (Collection<Quesito>) request.getAttribute("quesiti");


%>    
<!DOCTYPE>
<html>
<head>
<link rel="stylesheet" href="./style.css">

<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" >
<link rel="SHORTCUT ICON" href="../images/LOGO_Unisask.png"> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Unisask</title>
</head>
<body>

<header>

	<div class="logo_header">
		<a  href="#"><img  src="./images/LOGO_Unisask.png" width=150 ></a>
	</div>
	
	<div id="Benvenuto" align="center">
	<i class="fa fa-user" style="font-size: 35"></i>
		<p>Benvenuto <%=account.getUsername()%></p>
		<form action="./Logout" method="get" >
			<input class="tastologout" type="submit" value="Logout">
		</form>
	
	</div>
</header>
	


    <%
		if(account.getTipo().equals("docente"))
		{
	%>	<div id="contenitore_link" align="center">
			<p><a href="VisualizzaCorsi?action=<%="i_miei_corsi"%> class="active">I miei corsi</a> | <a href="VisualizzaDomande"> Domande</a></p>
		</div>
		
		
	<%
		}
	%>
	
	
		
	

<div id="corsi_window">
		<ul>
		<%
		
			if(account.getTipo().equals("docente"))
			{
				
				if(quesiti.size() > 0)
				    		{
							
					    		Iterator<Quesito> it = quesiti.iterator();
					    		
							while(it.hasNext())
							{
								
								Quesito quesito = (Quesito) it.next();
								
								
		
		%>
								<li >
									<div class="row">
										<div id="first_box_element" align="left">
											<i class="fas fa-angle-right"></i> <a href="#"><%=quesito.getDomanda()%></a>
										</div>
										
										<div id="second_box_element" align="center">
										
										</div>
										<div id="third_box_element" align="right">
										
										</div>
										
										<div style= "float:right; font-family:futura">
							    				<a href=""><input class="tastorispondi" type="submit" value="Rispondi"></a>
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