package application_logic_layer.gestione_lezioni;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import storage_layer.LezioneDao;


@WebServlet("/VisualizzaLezioni")
public class VisualizzaLezioni extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public VisualizzaLezioni() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList <Lezione> lezioni = new ArrayList<Lezione>();
		String id_corso = request.getParameter("id_corso");
		String nome_corso = request.getParameter("nome_corso");
		int idCorso = Integer.parseInt(id_corso);
		
		try {
			lezioni = LezioneDao.getListaLezioni(idCorso);
			request.setAttribute("lezioni", lezioni);
			request.setAttribute("nome_corso", nome_corso);
			request.setAttribute("id_corso", id_corso);
	
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/gestione_lezioni/VisualizzaLezioneView.jsp");
			dispatcher.forward(request, response);
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
