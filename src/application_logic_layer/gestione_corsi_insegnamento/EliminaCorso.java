package application_logic_layer.gestione_corsi_insegnamento;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import storage_layer.CorsoInsegnamentoDao;

@WebServlet("/EliminaCorso")
public class EliminaCorso extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public EliminaCorso() {
        super();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{ 
			
			int id_corso = Integer.parseInt(request.getParameter("id_corso"));
			
			CorsoInsegnamentoDao.removeCorso(id_corso); 
				
			response.sendRedirect("./gestione_corsi_insegnamento/VisualizzaCorsiView.jsp");
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
