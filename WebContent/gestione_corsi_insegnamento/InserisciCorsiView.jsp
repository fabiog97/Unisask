<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,application_logic_layer.gestione_utente.Utente"%>
<head>

<%

	Collection<Utente> docenti = (Collection<Utente>) request.getAttribute("docenti");
	if (docenti == null){
		response.sendRedirect("../InserisciCorso");
		//Non ci sono docenti
		
		System.out.println("Non ci sono docenti");
		return;
	}
	
%>
<meta charset="UTF-8">
<title>Unisask</title>
<link rel="stylesheet" href="./style.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">



</head>
<body>

<header>
	<a  href="VisualizzaCorsiView.jsp"><img  src="./images/LOGO_Unisask.png" width=150 ></a>
	
	<div id="Benvenuto" align="center">
	<a href="../gestione_utente/VisualizzaProfiloView.jsp" style="text-decoration:none; color:black;">
	<i class="fa fa-user" style="font-size: 35"></i>
		<p>Benvenuto Tizio</p>
		<form action="../Logout" method="get" >
			<input class="tastologout" type="submit" value="Logout">
		</form>
	</a>
	</div>
</header>


<div id="inserisci_lezione" align="center">
	<h1>Aggiungi Corso</h1>
  	<form id="formCorsi" action="InserisciCorso" method="post">
	  
	    <label for="nomeCorso"><b>Nome Corso</b></label>
	    <input type="text" name="nomeCorso" required >
	
	    <label for="corsoDiLaurea"><b>Corso di laurea</b></label>
		    <div class="custom-select">
					<select name="itemCorsoDiLaurea">
						<option >Informatica - Triennale</option>
						<option >Informatica - Magistrale</option>
					</select>
					
			</div>
	    
	    <label for="docente"><b>Docente</b></label>
	    	<label for="docente"><b>Docente</b></label>
			<div class="custom-select">
				<select name="itemDocenti"  size="3" multiple="multiple" tabindex="1">
	    <% 
	    		if(docenti.size() > 0)
	    		{
		    		Iterator<Utente> it = docenti.iterator();
				while(it.hasNext())
				{
					Utente docente = (Utente) it.next();
		
		%>
					<option><%=docente.getNome()%> <%=docente.getCognome() %></option>
	    <% 
	    			}
			}
		%>
	    			</select>	
			</div>

	    <label for="annoAccademico"><b>Anno Accademico</b></label>
	    <div class="custom-select">
					<select name="itemAnnoAccademico">
						<option >2018/2019</option>
						<option >2017/2018</option>
						<option >2016/2017</option>
					</select>
					
			</div>
			
		 <label for="annoDiStudio"><b>Anno di studio</b></label>
	    <input type="text" name="annoDiStudio" required >
			
		<label for="semestre"><b>Semestre</b></label>
	    		<div class="custom-select">
				<select name="itemSemestre">
					<option >Primo semestre</option>
					<option >Secondo semestre</option>
				</select>
				
			</div>
			
			<input class="registerbtn" type="submit" value="Aggiungi" tabindex="2">
	  
</form>
  	
</div>

	
                

<div class="container2 signin2">
  		&copy; 2018 Unisask Inc. All right reserved<br>  
 </div>
 

	
	
</body>
</html>