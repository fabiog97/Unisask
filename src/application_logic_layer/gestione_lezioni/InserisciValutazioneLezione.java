package application_logic_layer.gestione_lezioni;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import storage_layer.LezioneDao;


@WebServlet("/InserisciValutazioneLezione")
public class InserisciValutazioneLezione extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public InserisciValutazioneLezione() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		try {
			int id_lezione = Integer.parseInt(request.getParameter("id_lezione"));
			
			int val = Integer.parseInt(request.getParameter("rate"));
			
			LezioneDao.updateValutazioneLezione(id_lezione, val);
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/gestione_lezioni/NotificaInserimentoValutazione.jsp?id_lezione="+id_lezione);
			dispatcher.forward(request, response);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
