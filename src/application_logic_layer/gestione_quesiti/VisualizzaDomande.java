package application_logic_layer.gestione_quesiti;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import application_logic_layer.gestione_utente.Utente;
import storage_layer.QuesitoDao;
/**
 * Servlet implementation class VisualizzaDomande
 *
 * <p>Gestisce la visualizzazione delle domande fatte dagli studenti.
 *
 * @author FabioGrauso
 */
@WebServlet("/VisualizzaDomande")
public class VisualizzaDomande extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /** @see HttpServlet#HttpServlet() */
  public VisualizzaDomande() {
    super();
    // TODO Auto-generated constructor stub
  }

  /** @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response) */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    System.out.println("TEST_doGet");
    Utente utente = (Utente) request.getSession().getAttribute("account");

    try {

      ArrayList<Quesito> quesiti = QuesitoDao.getDomandeByIdUtente(utente.getId());
      request.setAttribute("quesiti", quesiti);
      RequestDispatcher dispatcher =
          getServletContext().getRequestDispatcher("/gestione_quesiti/VisualizzaDomandeView.jsp");
      dispatcher.forward(request, response);

    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /** @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response) */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // TODO Auto-generated method stub
    doGet(request, response);
  }
}
