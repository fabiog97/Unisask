package storage_layer;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import application_logic_layer.gestione_utente.Utente;

public class UtenteDaoTest {


	@Before
	public  void setUp() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		connection = DriverManagerConnectionPool.getConnection();
		preparedStatement = connection.prepareStatement("ALTER TABLE utente AUTO_INCREMENT = 0;");
		preparedStatement.executeUpdate();
		connection.commit();
	}
	
	
    @Test
    public void testGetInstance() {
        UtenteDao result = UtenteDao.getInstance();
        assertNotNull(result);
    }
	
    @Test
	public final void testRegistraUtente() throws SQLException {
		System.out.println("TestRegistraUtente");
		
		setUp();
		
		Utente utente = new Utente();
		UtenteDao instance = new UtenteDao();
		utente.setNome("Mariantonietta");
		utente.setCognome("Rauzzino");
		utente.setTipo("non_verificato");
		utente.setUsername("maria98");
		utente.setMatricola("0512104491");
		utente.setEmail("f.grauso1@studenti.unisa.it");
		utente.setPassword("ciao");
		utente.setNazionalita("Italiana");
		int codice = 8912;
		instance.registraUtente(utente, codice);
		
		Utente result = instance.getUtenteByUsername("maria98");
		assertEquals(utente.getUsername(), result.getUsername());
		
		instance.deleteUtenteById(1);
		System.out.println("\n");
	}
	
	@Test
	public final void testMaxUsernameRegistraUtente() throws SQLException {
		System.out.println("TestMaxUsernameRegistraUtente");
		setUp();
		Utente utente = new Utente();
		UtenteDao instance = new UtenteDao();
		utente.setNome("Mariantonietta");
		utente.setCognome("Rauzzino");
		utente.setTipo("non_verificato");
		utente.setUsername("mariantonietta_rauzzino98");
		utente.setMatricola("0512104491");
		utente.setEmail("f.grauso1@studenti.unisa.it");
		utente.setPassword("ciao");
		utente.setNazionalita("Italiana");
		int codice = 8912;
		
		try 
		{
			instance.registraUtente(utente, codice);
		}catch(Exception e) {
			
		}
		finally {
			Utente result = instance.getUtenteById(1);
			assertNull(result);
		}
		instance.deleteUtenteById(1);
		System.out.println("\n");
	}

	@Test
	public final void testEmptyEmailRegistraUtente() throws SQLException {
		System.out.println("TestEmptyEmailRegistraUtente");
		setUp();
		Utente utente = new Utente();
		UtenteDao instance = new UtenteDao();
		utente.setNome("Mariantonietta");
		utente.setCognome("Rauzzino");
		utente.setTipo("non_verificato");
		utente.setUsername("mariantonietta_rauzzino98");
		utente.setMatricola("0512104491");
		utente.setPassword("ciao");
		utente.setNazionalita("Italiana");
		int codice = 8912;
		
		try 
		{
			instance.registraUtente(utente, codice);
		}catch(Exception e) {
			
		}
		finally {
			Utente result = instance.getUtenteById(1);
			assertNull(result);
		}
		instance.deleteUtenteById(1);
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
		utente.setEmail("f.grauso1@studenti.unisa.it");
		utente.setPassword("ciao");
		utente.setNazionalita("Italiana");
		int codice = 8912;
		try 
		{
			instance.registraUtente(utente, codice);
		}catch(Exception e) {
			
		}
		finally {
			Utente result = instance.getUtenteById(1);
			assertNull(result);
		}
		instance.deleteUtenteById(1);
		System.out.println("\n");
	}

	

	@Test
	public final void testAggiornaUtente() {
		
		fail("Not yet implemented");
		
	}

	@Test
	public final void testVerificaCodice() {
		
		fail("Not yet implemented");
	}

	@Test
	public final void testDeleteCodiceUtente() {
		
		fail("Not yet implemented");
		
	}

	@Test
	public final void testLogin() throws SQLException {
		System.out.println("TestLogin");
		setUp();
		Utente utente = new Utente();
		UtenteDao instance = UtenteDao.getInstance();
		
		Utente utenteDB = new Utente();
		utenteDB.setNome("Mariantonietta");
		utenteDB.setCognome("Rauzzino");
		utenteDB.setTipo("non_verificato");
		utenteDB.setUsername("maria98");
		utenteDB.setMatricola("0512104491");
		utenteDB.setEmail("f.grauso1@studenti.unisa.it");
		utenteDB.setPassword("ciao");
		utenteDB.setNazionalita("Italiana");
		int codice = 8912;
		instance.registraUtente(utenteDB, codice);
		
		utente.setUsername("maria98");;
        utente.setPassword("ciao");
        String exceptResultNome = "Mariantonietta";
        Utente result = instance.login(utente);
        
        assertEquals(result.getNome(),exceptResultNome);
        
        instance.deleteUtenteById(1);
        System.out.println("\n");
	}

	@Test
	public final void testInvalidPasswordLogin() throws SQLException {
		System.out.println("TestInvalidPasswordLogin");
		setUp();
		Utente utente = new Utente();
		UtenteDao instance = UtenteDao.getInstance();
		
		Utente utenteDB = new Utente();
		utenteDB.setNome("Mariantonietta");
		utenteDB.setCognome("Rauzzino");
		utenteDB.setTipo("non_verificato");
		utenteDB.setUsername("maria98");
		utenteDB.setMatricola("0512104491");
		utenteDB.setEmail("f.grauso1@studenti.unisa.it");
		utenteDB.setPassword("ciao");
		utenteDB.setNazionalita("Italiana");
		int codice = 8912;
		instance.registraUtente(utenteDB, codice);
		
		utente.setUsername("maria98");
		utente.setPassword("fabiog");;

        Utente result = instance.login(utente);
        assertNull(result.getUsername());
       
        instance.deleteUtenteById(1);
        System.out.println("\n");
	}
	
	@Test
	public final void testInvalidUsernameLogin() throws SQLException {
		System.out.println("TestInvalidUsernameLogin");
		setUp();
		Utente utente = new Utente();
		UtenteDao instance = UtenteDao.getInstance();
		
		Utente utenteDB = new Utente();
		utenteDB.setNome("Mariantonietta");
		utenteDB.setCognome("Rauzzino");
		utenteDB.setTipo("non_verificato");
		utenteDB.setUsername("maria98");
		utenteDB.setMatricola("0512104491");
		utenteDB.setEmail("f.grauso1@studenti.unisa.it");
		utenteDB.setPassword("ciao");
		utenteDB.setNazionalita("Italiana");
		int codice = 8912;
		instance.registraUtente(utenteDB, codice);
		
		utente.setUsername("maria");
		utente.setPassword("ciao");;

        Utente result = instance.login(utente);
        assertNull(result.getUsername());
        
        instance.deleteUtenteById(1);
        System.out.println("\n");
	}
	
	@Test
	public final void testControlloResetPassword() {
		fail("Not yet implemented");
	}


	
	@Test
	public final void testResetPassword() throws SQLException {
		System.out.println("TestResetPassword");
		setUp();
		Utente utente = new Utente();
		UtenteDao instance = UtenteDao.getInstance();
		
		Utente utenteDB = new Utente();
		utenteDB.setNome("Mariantonietta");
		utenteDB.setCognome("Rauzzino");
		utenteDB.setTipo("non_verificato");
		utenteDB.setUsername("maria98");
		utenteDB.setMatricola("0512104491");
		utenteDB.setEmail("f.grauso@studenti.unisa.it");
		utenteDB.setPassword("ciao");
		utenteDB.setNazionalita("Italiana");
		int codice = 8912;
		instance.registraUtente(utenteDB, codice);
	
		String nuova_password = "maria";
		
		utenteDB.setPassword(nuova_password);
		
		instance.resetPassword(utenteDB);
		
		Utente result = instance.getUtenteById(1);
		
		assertEquals(nuova_password, result.getPassword());
		
		instance.deleteUtenteById(1);
		System.out.println("\n");
	}
	
	@Test
	public final void testGetUtenteByUsername() {
		fail("Not yet implemented");
	}

}
