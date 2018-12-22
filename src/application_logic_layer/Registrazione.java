package application_logic_layer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
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
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			
			response.setContentType("text/html");
			
			Utente user = new Utente();
			
			user.setNome(request.getParameter("nome"));
			user.setCognome(request.getParameter("cognome"));
			user.setUsername(request.getParameter("username"));
			user.setEmail(request.getParameter("email"));
			user.setMatricola(request.getParameter("matricola"));
			user.setNazionalita(request.getParameter("nazionalita"));
			user.setPassword(request.getParameter("password"));
			user.setTipo("non_verificato");
			
			
			try {
				UtenteDao.registraUtente(user);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			
			
			String name = (String)request.getParameter("nome");
			String surname = (String)request.getParameter("cognome");
			String mail = (String)request.getParameter("email");
			
			
			int codice = (int)Math.random();
			
			
			try {
				
				final String username = "unisaskplatform@gmail.com";
				final String password = "Unisask2018";

				Properties props = new Properties();
				props.put("mail.smtp.auth", "true");
				props.put("mail.smtp.starttls.enable", "true");
				props.put("mail.smtp.host", "smtp.gmail.com");
				props.put("mail.smtp.port", "587");
				Session session = Session.getInstance(props,
				  new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				  });
			
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress(username));
				message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(mail));
				message.setSubject("Conferma Registrazione Unisask");
				message.setText("Ciao "+name+" "+surname+", \n" + 
						"Grazie per averti registrato, tu che dicevi che c'erano tante guide su come fare la conferma di registrazione per email trovane una e inviacela!\nGrazie!");
				

				Transport.send(message);

				System.out.println("Done");
				
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/notifica_registrazione.jsp");
				dispatcher.forward(request, response);
				
				
			
			} catch(Exception e) {}
			
			//response.getWriter().write(res);		
		}
			
			
			
			
}
