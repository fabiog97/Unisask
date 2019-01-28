package application_logic_layer.gestione_corsi_insegnamento;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import storage_layer.CorsoInsegnamentoDao;

@WebServlet("/DisiscrizioneCorso")
public class DisiscrizioneCorso extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DisiscrizioneCorso() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			int id_corso = Integer.parseInt(request.getParameter("id_corso"));
			int id_utente = Integer.parseInt(request.getParameter("id_utente"));

			CorsoInsegnamentoDao.disiscrizioneCorso(id_utente, id_corso);

			response.sendRedirect("./gestione_corsi_insegnamento/VisualizzaCorsiDiStudioView.jsp");

		} catch (Throwable theException) {
			System.out.println(theException);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
