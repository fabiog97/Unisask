package application_logic_layer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import storage_layer.UtenteDao;

public class RegistrationServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException 
	{
		try
		{ 
			Utente user = new Utente();
			String password = request.getParameter("password");
			String conf_password = request.getParameter("conferma-password");
			
			if(password.equals(conf_password))
			{
			
				user.setNome(request.getParameter("nome"));
				user.setCognome(request.getParameter("cognome"));
				user.setUsername(request.getParameter("username"));
				user.setEmail(request.getParameter("email"));
				user.setMatricola(request.getParameter("matricola"));
				user.setNazionalita(request.getParameter("nazionalita"));
				user.setPassword(password);
			
				UtenteDao.registraUtente(user); 
				
				HttpSession session = request.getSession(true); 
				session.setAttribute("User",user); 
				response.sendRedirect("login.html");
			}
			else 
			{
				System.out.println("Password e conferma password non combaciano");
				response.sendRedirect("registration.html");
			}
		}
		catch (Throwable theException) 
		{ 
			System.out.println(theException); 
		} 
	}
			
}
