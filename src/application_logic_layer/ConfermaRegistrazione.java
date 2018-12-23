package application_logic_layer;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import storage_layer.UtenteDao;


@WebServlet("/ConfermaRegistrazione")
public class ConfermaRegistrazione extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ConfermaRegistrazione() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String username = request.getParameter("username");
		
		int codice = Integer.parseInt(request.getParameter("codice"));
		
		try {
			
			Utente user = UtenteDao.getUtenteByUsername(username);
			
			System.out.println(user.toString());
			
			int codice_DB = UtenteDao.verificaCodice(user.getId());
			
			System.out.println("Codice conferma DB: "+codice_DB);
			
			System.out.println("Codice conferma Get: "+codice);
			
			String email = user.getEmail();
			String dominio = email.substring(email.indexOf("@") + 1);
			
			UtenteDao.deleteCodiceUtente(codice_DB);
			
			if(username.equals(user.getUsername())) {	
			
				if(codice_DB == codice) {
						
					UtenteDao.aggiornaUtente(user.getId(), dominio);
					
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/conferma_registrazione.jsp");
					dispatcher.forward(request, response);
				}else {
					System.out.println("Codice non presente nel DB");
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/negata_registrazione.jsp");
					dispatcher.forward(request, response);
				}
			}
			else {
				System.out.println("Username non presente nel DB");
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/negata_registrazione.jsp");
				dispatcher.forward(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
