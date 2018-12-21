package application_logic_layer;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import storage_layer.UtenteDao;


@WebServlet("/Registrazione")
public class Registrazione extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Registrazione() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{ 
			Utente user = new Utente();
			
			user.setNome(request.getParameter("nome"));
			user.setCognome(request.getParameter("cognome"));
			user.setUsername(request.getParameter("username"));
			user.setEmail(request.getParameter("email"));
			user.setMatricola(request.getParameter("matricola"));
			user.setNazionalita(request.getParameter("nazionalita"));
			user.setPassword(request.getParameter("password"));
			
			UtenteDao.registraUtente(user); 
				
			HttpSession session = request.getSession(true); 
			session.setAttribute("User",user); 
			response.sendRedirect("login.html");
			
		}
		catch (Throwable theException) 
		{ 
			System.out.println(theException); 
		} 
		doGet(request, response);
	}

}
