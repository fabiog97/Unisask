package application_logic_layer.gestione_corsi_insegnamento;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import application_logic_layer.gestione_utente.Utente;
import storage_layer.CorsoInsegnamentoDao;
import storage_layer.UtenteDao;

@WebServlet("/InserisciCorso")
public class InserisciCorso extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public InserisciCorso() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			Collection<Utente> docenti = UtenteDao.getAllDocenti();

			try {
				request.removeAttribute("docenti");
				request.setAttribute("docenti", docenti);
			} catch (Exception e) {
				System.out.println("ERROR: " + e.getMessage());
				request.setAttribute("error", e.getMessage());
			}

			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/gestione_corsi_insegnamento/InserisciCorsiView.jsp");
			dispatcher.forward(request, response);

		} catch (Throwable theException) {
			System.out.println(theException);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CorsoInsegnamento corso = new CorsoInsegnamento();

		corso.setNome(request.getParameter("nomeCorso"));
		corso.setCorsoDiLaurea(request.getParameter("itemCorsoDiLaurea"));

		String[] docenti_scelti = request.getParameterValues("itemDocenti");
		ArrayList<Utente> docenti = new ArrayList<Utente>();

		for (int i = 0; i < docenti_scelti.length; i++) {
			String docente_scelto = docenti_scelti[i];
			String[] splitted = docente_scelto.split(" ");
			String nomeDocente = splitted[0];
			String cognomeDocente = splitted[1];

			try {
				Collection<Utente> docentiDB = UtenteDao.getAllDocenti();
				Iterator<Utente> it = docentiDB.iterator();
				while (it.hasNext()) {
					Utente docente = (Utente) it.next();
					if (docente.getNome().equals(nomeDocente) && docente.getCognome().equals(cognomeDocente)) {
						System.out.println("Docente: " + docente.toString());
						docenti.add(docente);
					}
				}

			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

		corso.setDocenti(docenti);
		corso.setAnnoAccademico(request.getParameter("itemAnnoAccademico"));
		corso.setAnnoDiStudio(request.getParameter("itemAnnoDiStudi"));
		corso.setSemestre(request.getParameter("itemSemestre"));

		try {
			CorsoInsegnamentoDao.addCorso(corso);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * HttpSession session = request.getSession(true);
		 * session.setAttribute("corso",corso);
		 * response.sendRedirect("VisualizzaCorsiView.jsp");
		 */
		doGet(request, response);
	}
}
