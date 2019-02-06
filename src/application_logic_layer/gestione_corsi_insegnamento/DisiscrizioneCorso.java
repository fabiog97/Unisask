package application_logic_layer.gestione_corsi_insegnamento;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import storage_layer.CorsoInsegnamentoDao;
/**
 * Servlet implementation class DisiscrizioneCorso
 *
 * <p>Gestisce la disiscrizione dell'utente dal corso.
 *
 * @author AntonioVitiello
 */

@WebServlet("/DisiscrizioneCorso")
public class DisiscrizioneCorso extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /** @see
   * HttpServlet#HttpServlet()
   * */
  public DisiscrizioneCorso() {
    super();
    // TODO Auto-generated constructor stub
  }

  /** @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response) */
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

  /** @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response) */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // TODO Auto-generated method stub
    doGet(request, response);
  }
}
