package application_logic_layer.gestione_utente;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import application_logic_layer.CryptWithMD5;
import storage_layer.UtenteDao;


@WebServlet("/ResetPassword")
public class ResetPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ResetPassword() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
		Utente user = new Utente();
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		
		user.setEmail(email);
		user.setUsername(username);
		
		
			try {
				if(UtenteDao.controlloResetPassword(user)==false) {
					// Non esiste nessun utente con eamil e username specificato
					request.setAttribute("flag", "reset");
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/gestione_utente/NegatoResetView.jsp");
					dispatcher.forward(request, response);
				}
				else
				{
					
					//Generazione password casuale
					
					String password = "";
					String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
				    Random rnd = new Random(System.currentTimeMillis());
				    int LENGHT = 8;
				 
				    StringBuilder sb = new StringBuilder(LENGHT);
				    
				    for (int i = 0; i < LENGHT; i++) {
				            sb.append(ALPHABET.charAt(rnd.nextInt(ALPHABET.length())));
				        }
				    
				    password = sb.toString();
				    
				    final String email_platform = "unisaskplatform@gmail.com";
					final String pass_word = "Unisask2018";

					Properties props = new Properties();
					props.put("mail.smtp.auth", "true");
					props.put("mail.smtp.starttls.enable", "true");
					props.put("mail.smtp.host", "smtp.gmail.com");
					props.put("mail.smtp.port", "587");
					Session session = Session.getInstance(props,
					  new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(email_platform, pass_word);
						}
					  });
					
					Message message = new MimeMessage(session);
					message.setFrom(new InternetAddress(username));
					message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(email));
					message.setSubject("Reset Password Unisask");
					message.setText("Ciao "+ username +", \n" + 
							"La tua nuova password è: "+password+"\n Grazie!");
					
					
			        String generatedPassword1 = CryptWithMD5.cryptWithMD5(password);
			        
			        user.setPassword(generatedPassword1);
			        
			        UtenteDao.resetPassword(user);
			        
					Transport.send(message);
					
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/gestione_utente//NotificaResetView.jsp");
					dispatcher.forward(request, response);
				}
			} catch (AddressException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		doGet(request, response);
	}
	}

