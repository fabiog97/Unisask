package application_logic_layer;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import storage_layer.UtenteDao;


@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Utente user = new Utente();
		Utente user1 = new Utente();
		
		user.setUsername(request.getParameter("username"));
		
		String passwordToHash = request.getParameter("password");
        String generatedPassword = null;
        
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(passwordToHash.getBytes());
            //Get the hash's bytes
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        
		
        user.setPassword(generatedPassword);
		
		try {
			user1 = UtenteDao.login(user);
			
			 if (user.getUsername().equals(user1.getUsername()) && user.getPassword().equals(user1.getPassword()) && (!user1.getTipo().equals("non_verificato")))
		     {
			        
		          HttpSession session = request.getSession(true);	    
		          session.setAttribute("currentSessionUser",user);
		          System.out.println("Autenticazione Riuscita");
		          response.sendRedirect("VisualizzaCorsiView.jsp"); //logged-in page      		
		     }
			        
		     else {
		    	 	System.out.println("Autenticazione NON Riuscita");
		    	 	response.sendRedirect("invalid_login.jsp"); //error page 
		     }
		
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		doGet(request, response);
	}

}
