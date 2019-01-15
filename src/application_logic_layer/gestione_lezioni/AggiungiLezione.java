package application_logic_layer.gestione_lezioni;

import java.io.IOException;
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
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{ 
			Lezione lezione = new Lezione();
			
			lezione.setNome(request.getParameter("nomeLezione"));
			lezione.setData(request.getParameter("dataLezione"));
			lezione.setDescrizione(request.getParameter("argomento_lezione"));
			
			int id_corso = Integer.parseInt(request.getParameter("id_corso"));//Quando il docente premer� su aggiungi, 
			
			
			LezioneDao.addLezione(lezione,id_corso); 
				
			HttpSession session = request.getSession(true); 
			session.setAttribute("lezione",lezione); 
			response.sendRedirect("VisualizzaLezioneView.jsp");
			
		}
		catch (Throwable theException) 
		{ 
			System.out.println(theException); 
		} 
		doGet(request, response);
	}

}
