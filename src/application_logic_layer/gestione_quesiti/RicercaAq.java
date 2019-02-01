package application_logic_layer.gestione_quesiti;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import storage_layer.QuesitoDao;
/**
 * Servlet implementation class RicercaAq
 * 
 * Gestisce la ricerca delle asked question.
 * @author FabioGrauso
 * 
 */
@WebServlet("/RicercaAq")
public class RicercaAq extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RicercaAq() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String ricerca = request.getParameter("ricerca");

		try {

			request.setAttribute("quesiti_ricercati", QuesitoDao.getQuesitiByricerca(ricerca));

			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/gestione_quesiti/VisualizzaAskedQuestionView.jsp");
			dispatcher.forward(request, response);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
