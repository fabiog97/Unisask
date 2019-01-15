package application_logic_layer.gestione_corsi_insegnamento;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import storage_layer.CorsoInsegnamentoDao;


@WebServlet("/IscrizioneCorso")
public class IscrizioneCorso extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public IscrizioneCorso() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id_corso = Integer.parseInt(request.getParameter("id_corso"));
		int id_utente = Integer.parseInt(request.getParameter("id_utente"));
		
		try {
			CorsoInsegnamentoDao.iscrizioneCorso(id_corso, id_utente);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.sendRedirect("./gestione_corsi_insegnamento/VisualizzaCorsiDiStudioView.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
