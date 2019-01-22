package application_logic_layer.gestione_lezioni;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import storage_layer.LezioneDao;


@WebServlet("/AggiungiLezione")
public class AggiungiLezione extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AggiungiLezione() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{ 
			Lezione lezione = new Lezione();
			
			int id_corso = Integer.valueOf(request.getParameter("id_corso"));
			lezione.setNome(request.getParameter("nomeLezione"));
			lezione.setData(request.getParameter("dataLezione"));
			lezione.setDescrizione(request.getParameter("argomento_lezione"));
			
			LezioneDao.addLezione(lezione,id_corso); 
				
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/gestione_lezioni/NotificaInserimentoLezione.jsp?id_corso="+id_corso);
			dispatcher.forward(request, response);
			
		}
		catch (Throwable theException) 
		{ 
			System.out.println(theException); 
		} 
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
