package application_logic_layer.gestione_corsi_insegnamento;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import application_logic_layer.gestione_utente.Utente;
import storage_layer.CorsoInsegnamentoDao;

@WebServlet("/VisualizzaCorsi")
public class VisualizzaCorsi extends HttpServlet
{
	private static final long serialVersionUID = 1L;

    public VisualizzaCorsi() {
        super();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    	try
			{ 
				ArrayList<CorsoInsegnamento> corsi = new ArrayList<CorsoInsegnamento>();
				
				ArrayList<CorsoInsegnamento> corsi_insegnati = new ArrayList<CorsoInsegnamento>();
				
				corsi = CorsoInsegnamentoDao.getListaCorsi();
	
				request.setAttribute("corsi", corsi);
				
				Utente utente = (Utente) request.getSession().getAttribute("account");
			
				corsi_insegnati = CorsoInsegnamentoDao.getListaCorsiInsegnanti(utente.getId());
	
				request.setAttribute("corsi", corsi);
				
				request.setAttribute("corsi_insegnati", corsi_insegnati);
				
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/gestione_corsi_insegnamento/VisualizzaCorsiView.jsp");
				dispatcher.forward(request, response);
			}
			catch (Throwable theException) 
			{ 
				System.out.println(theException); 
			} 
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		doGet(request, response);
	}
}
