package test_unisask;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import application_logic_layer.gestione_corsi_insegnamento.CorsoInsegnamento;
import application_logic_layer.gestione_lezioni.Lezione;
import application_logic_layer.gestione_utente.Utente;
import storage_layer.CorsoInsegnamentoDao;
import storage_layer.DriverManagerConnectionPool;
import storage_layer.LezioneDao;
import storage_layer.UtenteDao;

public class UtenteDaoTest {

  Connection connection = null;

  /**
   * setUp.
   */
  @Before
  public void setUp() throws SQLException {
    PreparedStatement preparedStatement = null;
    PreparedStatement preparedStatement1 = null;
    PreparedStatement preparedStatement2 = null;
    PreparedStatement preparedStatement3 = null;

    connection = DriverManagerConnectionPool.getConnection();
    preparedStatement = connection.prepareStatement("ALTER TABLE utente AUTO_INCREMENT = 0;");
    preparedStatement1 = connection.prepareStatement("ALTER TABLE corso AUTO_INCREMENT = 0;");
    preparedStatement2 = connection.prepareStatement("ALTER TABLE lezione AUTO_INCREMENT = 0;");
    preparedStatement3 = connection.prepareStatement("ALTER TABLE quesito AUTO_INCREMENT = 0;");

    preparedStatement.executeUpdate();
    preparedStatement1.executeUpdate();
    preparedStatement2.executeUpdate();
    preparedStatement3.executeUpdate();

    connection.commit();
  }

  @Test
  public final void testRegistraUtente() throws SQLException {
    System.out.println("TestRegistraUtente");

    setUp();

    Utente utente = new Utente();

    utente.setNome("Mariantonietta");
    utente.setCognome("Rauzzino");
    utente.setTipo("non_verificato");
    utente.setUsername("maria98");
    utente.setMatricola("0512104491");
    utente.setEmail("f.grauso1@studenti.unisa.it");
    utente.setPassword("0123456789");
    utente.setNazionalita("Italiana");
    int codice = 8912;
    UtenteDao.registraUtente(utente, codice);

    Utente result = UtenteDao.getUtenteByUsername("maria98");
    assertEquals(utente.getUsername(), result.getUsername());

    UtenteDao.deleteUtenteById(1);
    System.out.println("\n");
  }

  @Test
  public final void testMaxUsernameRegistraUtente() throws SQLException {
    System.out.println("TestMaxUsernameRegistraUtente");
    setUp();
    Utente utente = new Utente();
    utente.setNome("Mariantonietta");
    utente.setCognome("Rauzzino");
    utente.setTipo("non_verificato");
    utente.setUsername("mariantonietta_rauzzino98");
    utente.setMatricola("0512104491");
    utente.setEmail("f.grauso1@studenti.unisa.it");
    utente.setPassword("0123456789");
    utente.setNazionalita("Italiana");
    int codice = 8912;

    try {
      UtenteDao.registraUtente(utente, codice);
    } catch (Exception e) {
      e.printStackTrace();
    }

    Utente result = UtenteDao.getUtenteById(1);
    assertNull(result);

    UtenteDao.deleteUtenteById(1);
    System.out.println("\n");
  }

  @Test
  public final void testEmptyEmailRegistraUtente() throws SQLException {
    System.out.println("TestEmptyEmailRegistraUtente");
    setUp();
    Utente utente = new Utente();
    utente.setNome("Mariantonietta");
    utente.setCognome("Rauzzino");
    utente.setTipo("non_verificato");
    utente.setUsername("mariantonietta_rauzzino98");
    utente.setMatricola("0512104491");
    utente.setPassword("0123456789");
    utente.setNazionalita("Italiana");
    int codice = 8912;

    try {
      UtenteDao.registraUtente(utente, codice);
    } catch (Exception e) {
      e.printStackTrace();
    }

    Utente result = UtenteDao.getUtenteById(1);
    assertNull(result);

    UtenteDao.deleteUtenteById(1);
    System.out.println("\n");
  }

  @Test
  public final void testEmptyNazionalitaRegistraUtente() throws SQLException {
    System.out.println("TestEmptyNazionalitaRegistraUtente");
    setUp();
    Utente utente = new Utente();
    utente.setNome("Mariantonietta");
    utente.setCognome("Rauzzino");
    utente.setTipo("non_verificato");
    utente.setUsername("mariantonietta_rauzzino98");
    utente.setMatricola("0512104491");
    utente.setPassword("0123456789");
    int codice = 8912;

    try {
      UtenteDao.registraUtente(utente, codice);
    } catch (Exception e) {
      e.printStackTrace();
    }

    Utente result = UtenteDao.getUtenteById(1);
    assertNull(result);

    UtenteDao.deleteUtenteById(1);
    System.out.println("\n");
  }

  @Test
  public final void testMaxMatricolaRegistraUtente() throws SQLException {
    System.out.println("TestMaxMatricolaRegistraUtente");
    setUp();
    Utente utente = new Utente();
    UtenteDao instance = new UtenteDao();
    utente.setNome("Mariantonietta");
    utente.setCognome("Rauzzino");
    utente.setTipo("non_verificato");
    utente.setUsername("maria98");
    utente.setMatricola("051210449121");
    utente.setEmail("m.rauzzino@studenti.unisa.it");
    utente.setPassword("0123456789");
    utente.setNazionalita("Italiana");
    int codice = 8912;
    try {
      UtenteDao.registraUtente(utente, codice);
    } catch (Exception e) {
      e.printStackTrace();
    }

    Utente result = UtenteDao.getUtenteById(1);
    assertNull(result);

    UtenteDao.deleteUtenteById(1);
    System.out.println("\n");
  }

  @Test
  public final void testAggiornaUtente() throws SQLException {
    System.out.println("TestAggiornaUtente");
    setUp();
    Utente utente = new Utente();

    Utente utentedb = new Utente();
    utentedb.setNome("Mariantonietta");
    utentedb.setCognome("Rauzzino");
    utentedb.setTipo("non_verificato");
    utentedb.setUsername("maria98");
    utentedb.setMatricola("0512104491");
    utentedb.setEmail("f.grauso1@studenti.unisa.it");
    utentedb.setPassword("0123456789");
    utentedb.setNazionalita("Italiana");
    int codice = 8912;
    UtenteDao.registraUtente(utentedb, codice);

    utente = UtenteDao.getUtenteByUsername("maria98");
    String email = utente.getEmail();
    String dominio = email.substring(email.indexOf("@") + 1);
    boolean result = UtenteDao.aggiornaUtente(utente.getId(), dominio);

    assertTrue(result);
    UtenteDao.deleteUtenteById(1);
    System.out.println("\n");
  }

  @Test
  public final void testVerificaCodice() throws SQLException {
    System.out.println("TestVerificaCodice");
    setUp();
    Utente utente = new Utente();

    Utente utentedb = new Utente();
    utentedb.setNome("Mariantonietta");
    utentedb.setCognome("Rauzzino");
    utentedb.setTipo("non_verificato");
    utentedb.setUsername("maria98");
    utentedb.setMatricola("0512104491");
    utentedb.setEmail("f.grauso1@studenti.unisa.it");
    utentedb.setPassword("0123456789");
    utentedb.setNazionalita("Italiana");
    int codice = 8912;

    UtenteDao.registraUtente(utentedb, codice);
    utente = UtenteDao.getUtenteByUsername("maria98");

    int result = UtenteDao.verificaCodice(utente.getId());
    assertEquals(codice, result);

    UtenteDao.deleteUtenteById(1);
    System.out.println("\n");
  }

  @Test
  public final void testDeleteCodiceUtente() throws SQLException {
    System.out.println("TestDeleteCodiceUtente");
    setUp();

    Utente utentedb = new Utente();
    utentedb.setNome("Mariantonietta");
    utentedb.setCognome("Rauzzino");
    utentedb.setTipo("non_verificato");
    utentedb.setUsername("maria98");
    utentedb.setMatricola("0512104491");
    utentedb.setEmail("f.grauso1@studenti.unisa.it");
    utentedb.setPassword("0123456789");
    utentedb.setNazionalita("Italiana");
    int codice = 8912;

    UtenteDao.registraUtente(utentedb, codice);

    boolean result = UtenteDao.deleteCodiceUtente(8912);
    assertTrue(result);
    UtenteDao.deleteUtenteById(1);
    System.out.println("\n");
  }

  @Test
  public final void testFailDeleteCodiceUtente() throws SQLException {
    System.out.println("TestDeleteCodiceUtente");
    setUp();

    Utente utentedb = new Utente();
    utentedb.setNome("Mariantonietta");
    utentedb.setCognome("Rauzzino");
    utentedb.setTipo("non_verificato");
    utentedb.setUsername("maria98");
    utentedb.setMatricola("0512104491");
    utentedb.setEmail("f.grauso1@studenti.unisa.it");
    utentedb.setPassword("0123456789");
    utentedb.setNazionalita("Italiana");
    int codice = 8912;

    UtenteDao.registraUtente(utentedb, codice);

    boolean result = UtenteDao.deleteCodiceUtente(8112);
    assertFalse(result);
    UtenteDao.deleteUtenteById(1);
    System.out.println("\n");
  }

  @Test
  public final void testLogin() throws SQLException {
    System.out.println("TestLogin");
    setUp();
    Utente utente = new Utente();

    Utente utentedb = new Utente();
    utentedb.setNome("Mariantonietta");
    utentedb.setCognome("Rauzzino");
    utentedb.setTipo("non_verificato");
    utentedb.setUsername("maria98");
    utentedb.setMatricola("0512104491");
    utentedb.setEmail("f.grauso1@studenti.unisa.it");
    utentedb.setPassword("0123456789");
    utentedb.setNazionalita("Italiana");
    int codice = 8912;
    UtenteDao.registraUtente(utentedb, codice);

    utente.setUsername("maria98");
    ;
    utente.setPassword("0123456789");
    String exceptResultNome = "Mariantonietta";
    Utente result = UtenteDao.login(utente);

    assertEquals(result.getNome(), exceptResultNome);

    UtenteDao.deleteUtenteById(1);
    System.out.println("\n");
  }

  @Test
  public final void testInvalidPasswordLogin() throws SQLException {
    System.out.println("TestInvalidPasswordLogin");
    setUp();
    Utente utente = new Utente();

    Utente utentedb = new Utente();
    utentedb.setNome("Mariantonietta");
    utentedb.setCognome("Rauzzino");
    utentedb.setTipo("non_verificato");
    utentedb.setUsername("maria98");
    utentedb.setMatricola("0512104491");
    utentedb.setEmail("f.grauso1@studenti.unisa.it");
    utentedb.setPassword("0123456789");
    utentedb.setNazionalita("Italiana");
    int codice = 8912;
    UtenteDao.registraUtente(utentedb, codice);

    utente.setUsername("maria98");
    utente.setPassword("fabiog");
    ;

    Utente result = UtenteDao.login(utente);
    assertNull(result.getUsername());

    UtenteDao.deleteUtenteById(1);
    System.out.println("\n");
  }

  @Test
  public final void testInvalidUsernameLogin() throws SQLException {
    System.out.println("TestInvalidUsernameLogin");
    setUp();
    Utente utente = new Utente();

    Utente utentedb = new Utente();
    utentedb.setNome("Mariantonietta");
    utentedb.setCognome("Rauzzino");
    utentedb.setTipo("non_verificato");
    utentedb.setUsername("maria98");
    utentedb.setMatricola("0512104491");
    utentedb.setEmail("f.grauso1@studenti.unisa.it");
    utentedb.setPassword("0123456789");
    utentedb.setNazionalita("Italiana");
    int codice = 8912;
    UtenteDao.registraUtente(utentedb, codice);

    utente.setUsername("maria");
    utente.setPassword("0123456789");
    ;

    Utente result = UtenteDao.login(utente);
    assertNull(result.getUsername());

    UtenteDao.deleteUtenteById(1);
    System.out.println("\n");
  }

  @Test
  public final void testgetDocentiByLezioneId() throws SQLException {
    System.out.println("testgetDocentiByLezioneId");
    setUp();

    Utente utente = new Utente();
    utente.setNome("Filomena");
    utente.setCognome("Ferrucci");
    utente.setTipo("docente");
    utente.setUsername("fferrucci");
    utente.setMatricola("0512104491");
    utente.setEmail("f.grauso1@studenti.unisa.it");
    utente.setPassword("0123456789");
    utente.setNazionalita("Italiana");
    int codice = 8912;
    UtenteDao.registraUtente(utente, codice);

    Utente utente1 = UtenteDao.getUtenteByUsername("fferrucci");
    ArrayList<Utente> docenti = new ArrayList<Utente>();
    docenti.add(utente1);

    CorsoInsegnamento corso = new CorsoInsegnamento();

    corso.setNome("Ingegneria del Software");
    corso.setCorsoDiLaurea("Informatica - Triennale");
    corso.setAnnoAccademico("2018/2019");
    corso.setSemestre("Primo");
    corso.setAnnoDiStudio("Primo");
    corso.setDocenti(docenti);

    CorsoInsegnamentoDao.addCorso(corso);

    Lezione lezione = new Lezione();

    lezione.setNome("Scenari e casi d'uso");
    lezione.setData("21/09/2018");
    lezione.setDescrizione(
        "Scenario visionary, scenario as is, training, casi d’uso, diagrammi dei casi d’uso");

    LezioneDao.addLezione(lezione, 1);

    ArrayList<Utente> utenti = UtenteDao.getDocentiByLezioneId(1);

    Utente user = null;
    Iterator<Utente> i = utenti.iterator();

    while (i.hasNext()) {
      user = (Utente) i.next();
      assertEquals(user.getUsername(), "fferrucci");
    }

    UtenteDao.deleteUtenteById(1);
    CorsoInsegnamentoDao.removeCorso(1);
    LezioneDao.removeLezione(1);
    System.out.println("\n");
  }

  @Test
  public final void testControlloResetPassword() throws SQLException {}

  @Test
  public final void testFailControlloResetPassword() throws SQLException {
    System.out.println("TestControlloResetPassword");
    setUp();

    Utente utentedb = new Utente();
    utentedb.setNome("Mariantonietta");
    utentedb.setCognome("Rauzzino");
    utentedb.setTipo("non_verificato");
    utentedb.setUsername("maria98");
    utentedb.setMatricola("0512104491");
    utentedb.setEmail("f.grauso@studenti.unisa.it");
    utentedb.setPassword("0123456789");
    utentedb.setNazionalita("Italiana");
    int codice = 8912;
    UtenteDao.registraUtente(utentedb, codice);

    utentedb.setEmail("grauso@studenti.unisa.it");
    boolean result = UtenteDao.controlloResetPassword(utentedb);

    assertFalse(result);

    UtenteDao.deleteUtenteById(1);
    System.out.println("\n");
  }

  @Test
  public final void testResetPassword() throws SQLException {
    System.out.println("TestResetPassword");
    setUp();

    Utente utentedb = new Utente();
    utentedb.setNome("Mariantonietta");
    utentedb.setCognome("Rauzzino");
    utentedb.setTipo("non_verificato");
    utentedb.setUsername("maria98");
    utentedb.setMatricola("0512104491");
    utentedb.setEmail("f.grauso@studenti.unisa.it");
    utentedb.setPassword("0123456789");
    utentedb.setNazionalita("Italiana");
    int codice = 8912;
    UtenteDao.registraUtente(utentedb, codice);

    final String nuova_password = "mariamariamaria";

    utentedb.setPassword(nuova_password);

    UtenteDao.resetPassword(utentedb);

    Utente result = UtenteDao.getUtenteById(1);

    assertEquals(nuova_password, result.getPassword());

    UtenteDao.deleteUtenteById(1);
    System.out.println("\n");
  }

  @Test
  public final void testGetUtenteByUsername() throws SQLException {
    System.out.println("TestResetPassword");
    setUp();

    Utente utentedb = new Utente();
    utentedb.setNome("Mariantonietta");
    utentedb.setCognome("Rauzzino");
    utentedb.setTipo("non_verificato");
    utentedb.setUsername("maria98");
    utentedb.setMatricola("0512104491");
    utentedb.setEmail("f.grauso@studenti.unisa.it");
    utentedb.setPassword("0123456789");
    utentedb.setNazionalita("Italiana");
    int codice = 8912;
    UtenteDao.registraUtente(utentedb, codice);

    Utente result = UtenteDao.getUtenteByUsername("maria98");

    assertEquals(utentedb.getUsername(), result.getUsername());

    UtenteDao.deleteUtenteById(1);
  }

  @Test
  public final void testFailGetUtenteByUsername() throws SQLException {
    System.out.println("TestResetPassword");
    setUp();

    Utente utentedb = new Utente();
    utentedb.setNome("Mariantonietta");
    utentedb.setCognome("Rauzzino");
    utentedb.setTipo("non_verificato");
    utentedb.setUsername("maria98");
    utentedb.setMatricola("0512104491");
    utentedb.setEmail("f.grauso@studenti.unisa.it");
    utentedb.setPassword("0123456789");
    utentedb.setNazionalita("Italiana");
    int codice = 8912;
    UtenteDao.registraUtente(utentedb, codice);

    Utente result = UtenteDao.getUtenteByUsername("prova");

    assertNull(result.getUsername());

    UtenteDao.deleteUtenteById(1);
  }

  @Test
  public final void testBranchRegistraUtente() throws SQLException {

    setUp();
    Utente utentedb = new Utente();
    utentedb.setNome("Mariantonietta");
    utentedb.setCognome("Rauzzino");
    utentedb.setTipo("non_verificato");
    utentedb.setUsername("maria98");
    utentedb.setMatricola("0512104491");
    utentedb.setEmail("f.grauso@studenti.unisa.it");
    utentedb.setPassword("0123456789");
    utentedb.setNazionalita("Italiana");
    int codice = 8912;

    assertTrue(UtenteDao.registraUtente(utentedb, codice));
    assertFalse(UtenteDao.registraUtente(utentedb, codice));
    UtenteDao.deleteUtenteById(1);
  }

  @Test
  public final void testBranchDeleteCodiceUtente() throws SQLException {
    setUp();
    Utente utentedb = new Utente();
    utentedb.setNome("Mariantonietta");
    utentedb.setCognome("Rauzzino");
    utentedb.setTipo("non_verificato");
    utentedb.setUsername("maria98");
    utentedb.setMatricola("0512104491");
    utentedb.setEmail("f.grauso@studenti.unisa.it");
    utentedb.setPassword("0123456789");
    utentedb.setNazionalita("Italiana");
    int codice = 8912;

    UtenteDao.registraUtente(utentedb, codice);

    assertTrue(UtenteDao.deleteCodiceUtente(8912));
    assertFalse(UtenteDao.deleteCodiceUtente(8912));
    UtenteDao.deleteUtenteById(1);
  }

  @Test
  public final void testBranchControlloResetPassword() throws SQLException {
    setUp();
    Utente utentedb = new Utente();
    utentedb.setNome("Mariantonietta");
    utentedb.setCognome("Rauzzino");
    utentedb.setTipo("non_verificato");
    utentedb.setUsername("maria98");
    utentedb.setMatricola("0512104491");
    utentedb.setEmail("f.grauso@studenti.unisa.it");
    utentedb.setPassword("0123456789");
    utentedb.setNazionalita("Italiana");
    int codice = 8912;

    UtenteDao.registraUtente(utentedb, codice);

    assertTrue(UtenteDao.controlloResetPassword(utentedb));
    utentedb.setUsername("test");
    assertFalse(UtenteDao.controlloResetPassword(utentedb));
    UtenteDao.deleteUtenteById(1);
  }
}
