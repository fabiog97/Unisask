package test_unisask;

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

import storage_layer.DriverManagerConnectionPool;
import storage_layer.UtenteDao;

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
		
		try 
		{
			UtenteDao.registraUtente(utente, codice);
		}catch(Exception e) {
			
		}
		finally {
			Utente result = UtenteDao.getUtenteById(1);
			assertNull(result);
		}
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
		
		try 
		{
			UtenteDao.registraUtente(utente, codice);
		}catch(Exception e) {
			
		}
		finally {
			Utente result = UtenteDao.getUtenteById(1);
			assertNull(result);
		}
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
		
		try 
		{
			UtenteDao.registraUtente(utente, codice);
		}catch(Exception e) {
			
		}
		finally {
			Utente result = UtenteDao.getUtenteById(1);
			assertNull(result);
		}
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
		try 
		{
			UtenteDao.registraUtente(utente, codice);
		}catch(Exception e) {
			
		}
		finally {
			Utente result = UtenteDao.getUtenteById(1);
			assertNull(result);
		}
		UtenteDao.deleteUtenteById(1);
		System.out.println("\n");
	}

	

	@Test
	public final void testAggiornaUtente() throws SQLException {
		System.out.println("TestAggiornaUtente");
		setUp();
		Utente utente = new Utente();
		
		Utente utenteDB = new Utente();
		utenteDB.setNome("Mariantonietta");
		utenteDB.setCognome("Rauzzino");
		utenteDB.setTipo("non_verificato");
		utenteDB.setUsername("maria98");
		utenteDB.setMatricola("0512104491");
		utenteDB.setEmail("f.grauso1@studenti.unisa.it");
		utenteDB.setPassword("0123456789");
		utenteDB.setNazionalita("Italiana");
		int codice = 8912;
		UtenteDao.registraUtente(utenteDB, codice);
		
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
		
		Utente utenteDB = new Utente();
		utenteDB.setNome("Mariantonietta");
		utenteDB.setCognome("Rauzzino");
		utenteDB.setTipo("non_verificato");
		utenteDB.setUsername("maria98");
		utenteDB.setMatricola("0512104491");
		utenteDB.setEmail("f.grauso1@studenti.unisa.it");
		utenteDB.setPassword("0123456789");
		utenteDB.setNazionalita("Italiana");
		int codice = 8912;
		
		UtenteDao.registraUtente(utenteDB, codice);
		utente = UtenteDao.getUtenteByUsername("maria98");
		
		int result = UtenteDao.verificaCodice(utente.getId());
		assertEquals(codice,result);
		
		UtenteDao.deleteUtenteById(1);
        System.out.println("\n");
	}

	
	
	@Test
	public final void testDeleteCodiceUtente() throws SQLException {
		System.out.println("TestDeleteCodiceUtente");
		setUp();
		
		Utente utenteDB = new Utente();
		utenteDB.setNome("Mariantonietta");
		utenteDB.setCognome("Rauzzino");
		utenteDB.setTipo("non_verificato");
		utenteDB.setUsername("maria98");
		utenteDB.setMatricola("0512104491");
		utenteDB.setEmail("f.grauso1@studenti.unisa.it");
		utenteDB.setPassword("0123456789");
		utenteDB.setNazionalita("Italiana");
		int codice = 8912;
		
		UtenteDao.registraUtente(utenteDB, codice);
		
		boolean result = UtenteDao.deleteCodiceUtente(8912);
		assertTrue(result);
		UtenteDao.deleteUtenteById(1);
        System.out.println("\n");
	}

	@Test
	public final void testFailDeleteCodiceUtente() throws SQLException {
		System.out.println("TestDeleteCodiceUtente");
		setUp();
		
		Utente utenteDB = new Utente();
		utenteDB.setNome("Mariantonietta");
		utenteDB.setCognome("Rauzzino");
		utenteDB.setTipo("non_verificato");
		utenteDB.setUsername("maria98");
		utenteDB.setMatricola("0512104491");
		utenteDB.setEmail("f.grauso1@studenti.unisa.it");
		utenteDB.setPassword("0123456789");
		utenteDB.setNazionalita("Italiana");
		int codice = 8912;
		
		UtenteDao.registraUtente(utenteDB, codice);
		
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
		
		Utente utenteDB = new Utente();
		utenteDB.setNome("Mariantonietta");
		utenteDB.setCognome("Rauzzino");
		utenteDB.setTipo("non_verificato");
		utenteDB.setUsername("maria98");
		utenteDB.setMatricola("0512104491");
		utenteDB.setEmail("f.grauso1@studenti.unisa.it");
		utenteDB.setPassword("0123456789");
		utenteDB.setNazionalita("Italiana");
		int codice = 8912;
		UtenteDao.registraUtente(utenteDB, codice);
		
		utente.setUsername("maria98");;
        utente.setPassword("0123456789");
        String exceptResultNome = "Mariantonietta";
        Utente result = UtenteDao.login(utente);
        
        assertEquals(result.getNome(),exceptResultNome);
        
        UtenteDao.deleteUtenteById(1);
        System.out.println("\n");
	}

	@Test
	public final void testInvalidPasswordLogin() throws SQLException {
		System.out.println("TestInvalidPasswordLogin");
		setUp();
		Utente utente = new Utente();
		
		Utente utenteDB = new Utente();
		utenteDB.setNome("Mariantonietta");
		utenteDB.setCognome("Rauzzino");
		utenteDB.setTipo("non_verificato");
		utenteDB.setUsername("maria98");
		utenteDB.setMatricola("0512104491");
		utenteDB.setEmail("f.grauso1@studenti.unisa.it");
		utenteDB.setPassword("0123456789");
		utenteDB.setNazionalita("Italiana");
		int codice = 8912;
		UtenteDao.registraUtente(utenteDB, codice);
		
		utente.setUsername("maria98");
		utente.setPassword("fabiog");;

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
		
		Utente utenteDB = new Utente();
		utenteDB.setNome("Mariantonietta");
		utenteDB.setCognome("Rauzzino");
		utenteDB.setTipo("non_verificato");
		utenteDB.setUsername("maria98");
		utenteDB.setMatricola("0512104491");
		utenteDB.setEmail("f.grauso1@studenti.unisa.it");
		utenteDB.setPassword("0123456789");
		utenteDB.setNazionalita("Italiana");
		int codice = 8912;
		UtenteDao.registraUtente(utenteDB, codice);
		
		utente.setUsername("maria");
		utente.setPassword("0123456789");;

        Utente result = UtenteDao.login(utente);
        assertNull(result.getUsername());
        
        UtenteDao.deleteUtenteById(1);
        System.out.println("\n");
	}
	
	@Test
	public final void testControlloResetPassword() throws SQLException {
		System.out.println("TestControlloResetPassword");
		setUp();
		
		Utente utenteDB = new Utente();
		utenteDB.setNome("Mariantonietta");
		utenteDB.setCognome("Rauzzino");
		utenteDB.setTipo("non_verificato");
		utenteDB.setUsername("maria98");
		utenteDB.setMatricola("0512104491");
		utenteDB.setEmail("f.grauso@studenti.unisa.it");
		utenteDB.setPassword("0123456789");
		utenteDB.setNazionalita("Italiana");
		int codice = 8912;
		UtenteDao.registraUtente(utenteDB, codice);
		
		boolean result = UtenteDao.controlloResetPassword(utenteDB);
		
		assertTrue(result);
		
		UtenteDao.deleteUtenteById(1);
		System.out.println("\n");
	}
	
	@Test
	public final void testFailControlloResetPassword() throws SQLException {
		System.out.println("TestControlloResetPassword");
		setUp();
		
		Utente utenteDB = new Utente();
		utenteDB.setNome("Mariantonietta");
		utenteDB.setCognome("Rauzzino");
		utenteDB.setTipo("non_verificato");
		utenteDB.setUsername("maria98");
		utenteDB.setMatricola("0512104491");
		utenteDB.setEmail("f.grauso@studenti.unisa.it");
		utenteDB.setPassword("0123456789");
		utenteDB.setNazionalita("Italiana");
		int codice = 8912;
		UtenteDao.registraUtente(utenteDB, codice);
		
		utenteDB.setEmail("grauso@studenti.unisa.it");
		boolean result = UtenteDao.controlloResetPassword(utenteDB);
		
		assertFalse(result);
		
		UtenteDao.deleteUtenteById(1);
		System.out.println("\n");
	}


	
	@Test
	public final void testResetPassword() throws SQLException {
		System.out.println("TestResetPassword");
		setUp();
		
		
		Utente utenteDB = new Utente();
		utenteDB.setNome("Mariantonietta");
		utenteDB.setCognome("Rauzzino");
		utenteDB.setTipo("non_verificato");
		utenteDB.setUsername("maria98");
		utenteDB.setMatricola("0512104491");
		utenteDB.setEmail("f.grauso@studenti.unisa.it");
		utenteDB.setPassword("0123456789");
		utenteDB.setNazionalita("Italiana");
		int codice = 8912;
		UtenteDao.registraUtente(utenteDB, codice);
	
		String nuova_password = "mariamariamaria";
		
		utenteDB.setPassword(nuova_password);
		
		UtenteDao.resetPassword(utenteDB);
		
		Utente result = UtenteDao.getUtenteById(1);
		
		assertEquals(nuova_password, result.getPassword());
		
		UtenteDao.deleteUtenteById(1);
		System.out.println("\n");
	}
	
	@Test
	public final void testGetUtenteByUsername() throws SQLException {
		System.out.println("TestResetPassword");
		setUp();
		
		Utente utenteDB = new Utente();
		utenteDB.setNome("Mariantonietta");
		utenteDB.setCognome("Rauzzino");
		utenteDB.setTipo("non_verificato");
		utenteDB.setUsername("maria98");
		utenteDB.setMatricola("0512104491");
		utenteDB.setEmail("f.grauso@studenti.unisa.it");
		utenteDB.setPassword("0123456789");
		utenteDB.setNazionalita("Italiana");
		int codice = 8912;
		UtenteDao.registraUtente(utenteDB, codice);
		
		Utente result = UtenteDao.getUtenteByUsername("maria98");
		
		assertEquals(utenteDB.getUsername(), result.getUsername());
		
		UtenteDao.deleteUtenteById(1);
	}
	
	@Test
	public final void testFailGetUtenteByUsername() throws SQLException {
		System.out.println("TestResetPassword");
		setUp();
		
		
		Utente utenteDB = new Utente();
		utenteDB.setNome("Mariantonietta");
		utenteDB.setCognome("Rauzzino");
		utenteDB.setTipo("non_verificato");
		utenteDB.setUsername("maria98");
		utenteDB.setMatricola("0512104491");
		utenteDB.setEmail("f.grauso@studenti.unisa.it");
		utenteDB.setPassword("0123456789");
		utenteDB.setNazionalita("Italiana");
		int codice = 8912;
		UtenteDao.registraUtente(utenteDB, codice);
		
		
		Utente result = UtenteDao.getUtenteByUsername("prova");
		
		assertNull(result.getUsername());
		
		UtenteDao.deleteUtenteById(1);
	}

}
