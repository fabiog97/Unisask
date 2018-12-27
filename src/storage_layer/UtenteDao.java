package storage_layer;

import java.sql.Connection;



import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

import application_logic_layer.gestione_utente.Utente;


public class UtenteDao 
{
	
	private static UtenteDao instance;
	
	public static synchronized UtenteDao getInstance() {
        if (instance == null) {
            instance = new UtenteDao();
        }
        return instance;
    }

	public static boolean registraUtente(Utente utente, int codice) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		PreparedStatement preparedStatement1 = null;
		PreparedStatement preparedStatement2 = null;
		
		int id_utente = 0;
		
		String insertSQL = "INSERT INTO utente (username, password, tipo, nome, cognome, email, nazionalita, matricola) VALUES (?, ?, ?, ?, ?, ?, ?, ?)"; // Viene eseguita una query con la quale si va ad inserire nel DB il nuovo utente

		String controlloSQL = "SELECT * FROM utente WHERE username = ?";
		
		String insertSQL1 = "INSERT INTO codici_conferma (codice, id_utente) VALUES (?, ?)"; 
		
		try 
		{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement2 = connection.prepareStatement(controlloSQL);
			preparedStatement2.setString(1, utente.getUsername());
			
			System.out.println("controlloUtenteDoppione: "+ preparedStatement2.toString());
			ResultSet result_set = preparedStatement2.executeQuery();

			if(result_set.next() == false)  
			{
			
				preparedStatement = connection.prepareStatement(insertSQL,Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setString(1, utente.getUsername());
				preparedStatement.setString(2, utente.getPassword());
				preparedStatement.setString(3, utente.getTipo());
				preparedStatement.setString(4, utente.getNome());
				preparedStatement.setString(5, utente.getCognome());
				preparedStatement.setString(6, utente.getEmail());
				preparedStatement.setString(7, utente.getNazionalita());
				preparedStatement.setString(8, utente.getMatricola());
				
				System.out.println("registraUtente: "+ preparedStatement.toString());
		
				preparedStatement.executeUpdate();
				
				ResultSet rs = preparedStatement.getGeneratedKeys();
		       
				if (rs.next()){
		            id_utente = rs.getInt(1);
		        }
		        rs.close();
		        
		        
		        preparedStatement1 = connection.prepareStatement(insertSQL1);
		        preparedStatement1.setInt(1, codice);
		        preparedStatement1.setInt(2, id_utente  );
				
		        System.out.println("confermaCodiceUtente: "+ preparedStatement1.toString());
				
		        preparedStatement1.executeUpdate();
			
		        connection.commit();
		        return true;
			}
			else {
				return false;
			}
		}
		finally 
		{
			try 
			{
				if (preparedStatement != null)
				preparedStatement.close();
			} 
			finally 
			{
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}
	
	public static boolean aggiornaUtente(int id_utente,String dominio) throws SQLException
	{
		String dominio_docenti= "unisa.it";
		String dominio_studenti= "studenti.unisa.it";
		String tipo;
		
		
		if(dominio.equals(dominio_docenti)) {
			tipo = "docente";
		} else if(dominio.equals(dominio_studenti)) {
			tipo = "studente";
		}else {
			tipo = "email_non_istituzionale";
		}
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String updateSQL = "UPDATE utente SET tipo= ? WHERE id = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
			preparedStatement.setString(1, tipo);
			preparedStatement.setInt(2, id_utente);

			System.out.println("aggiornaUtente: "+ preparedStatement.toString());
			result = preparedStatement.executeUpdate();

			connection.commit();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return (result != 0);
		
	}
	
	public static int verificaCodice(int id_utente) throws SQLException
	{
		Connection connection = null;
		Utente utente = new Utente();
		PreparedStatement preparedStatement = null;
				
				String selectSQL = "SELECT codice FROM codici_conferma WHERE id_utente= ?";
				int codice_conferma = 0;
				
				try {
					connection = DriverManagerConnectionPool.getConnection();
					preparedStatement = connection.prepareStatement(selectSQL);
					
					preparedStatement.setInt(1, id_utente);
					
					System.out.println("verificaCodice:" + preparedStatement.toString());
					
					ResultSet rs = preparedStatement.executeQuery();
					
					while(rs.next()) {	
						codice_conferma = rs.getInt(1);
						
					}
				} finally {
					try {
						if(preparedStatement != null)
							preparedStatement.close();
					} finally {
						DriverManagerConnectionPool.releaseConnection(connection);
					}
				}	
				
			return codice_conferma;
	}
	
	
	public static boolean deleteCodiceUtente(int codice) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM codici_conferma WHERE codice = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, codice);

			System.out.println("deleteCodiceUtente: "+ preparedStatement.toString());
			result = preparedStatement.executeUpdate();

			connection.commit();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return (result != 0);
		
	}
	public static Utente login(Utente utente) throws SQLException
	{

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Utente user = new Utente();
	
		String selectSQL = "SELECT * FROM utente WHERE username = ? AND password = ?"; 
		
		
		try 
		{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			preparedStatement.setString(1, utente.getUsername());
			preparedStatement.setString(2, utente.getPassword());
			
			System.out.println("doLogin: "+ preparedStatement.toString());
			
			ResultSet rs = preparedStatement.executeQuery();
			
			
				while(rs.next()) 
				{
					user.setUsername(rs.getString("username"));
					user.setNome(rs.getString("nome"));
					user.setCognome(rs.getString("cognome"));
					user.setEmail(rs.getString("email"));
					user.setMatricola(rs.getString("matricola"));
					user.setNazionalita(rs.getString("nazionalita"));
					user.setPassword(rs.getString("password"));
					user.setTipo(rs.getString("tipo"));
					connection.commit();
				}
			
		} 
		finally 
		{
			try 
			{
				if(preparedStatement != null)
					preparedStatement.close();
			} 
			finally 
			{
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}	
		
		return user;
		
		
	}
	
	public static boolean controlloResetPassword(Utente utente) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
	
		String selectSQL = "SELECT * FROM utente WHERE username = ? AND email = ?"; 
		
		try 
		{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			preparedStatement.setString(1, utente.getUsername());
			preparedStatement.setString(2, utente.getEmail());
			
			System.out.println("controlloResetPassword:" + preparedStatement.toString());
			
			ResultSet rs = preparedStatement.executeQuery(); 
			
			if(rs.next() == false)  
			{
				return false;
			}
			else {
				return true;
			}
		} 
		finally 
		{
			try 
			{
				if(preparedStatement != null)
					preparedStatement.close();
			} 
			finally 
			{
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}	
	}
	
	public static void resetPassword(Utente user) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String updateSQL = "UPDATE utente SET password= ? WHERE username = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
			preparedStatement.setString(1, user.getPassword());
			preparedStatement.setString(2, user.getUsername());

			System.out.println("aggiornaUtente: "+ preparedStatement.toString());
			preparedStatement.executeUpdate();

			connection.commit();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		
	}
	
	public static Utente getUtenteByUsername(String username) throws SQLException
	{
		Connection connection = null;
		Utente utente = new Utente();
		PreparedStatement preparedStatement = null;
				
				String selectSQL = "SELECT * FROM utente WHERE username= ?";
				
				try {
					connection = DriverManagerConnectionPool.getConnection();
					preparedStatement = connection.prepareStatement(selectSQL);
					
					preparedStatement.setString(1, username);
					
					System.out.println("getUtenteByUsername:" + preparedStatement.toString());
					
					ResultSet rs = preparedStatement.executeQuery();
					
					while(rs.next()) {	
					
						utente.setId(rs.getInt(1));
						utente.setUsername(rs.getString(2));
						utente.setPassword(rs.getString(3));
						utente.setTipo(rs.getString(4));
						utente.setNome(rs.getString(5));
						utente.setCognome(rs.getString(6));
						utente.setEmail(rs.getString(7));
						utente.setNazionalita(rs.getString(8));
						utente.setMatricola(rs.getString(9));
					}
				} finally {
					try {
						if(preparedStatement != null)
							preparedStatement.close();
					} finally {
						DriverManagerConnectionPool.releaseConnection(connection);
					}
				}	
				
				return utente;
			}
	public static Utente getUtenteById(int id) throws SQLException
	{
		Connection connection = null;
		Utente utente = new Utente();
		PreparedStatement preparedStatement = null;
				
				String selectSQL = "SELECT * FROM utente WHERE id= ?";
				
				try {
					connection = DriverManagerConnectionPool.getConnection();
					preparedStatement = connection.prepareStatement(selectSQL);
					
					preparedStatement.setInt(1, id);
					
					System.out.println("getUtenteById:" + preparedStatement.toString());
					
					ResultSet rs = preparedStatement.executeQuery();
					
					if(rs.next()) {	
						utente.setId(rs.getInt(1));
						utente.setUsername(rs.getString(2));
						utente.setPassword(rs.getString(3));
						utente.setTipo(rs.getString(4));
						utente.setNome(rs.getString(5));
						utente.setCognome(rs.getString(6));
						utente.setEmail(rs.getString(7));
						utente.setNazionalita(rs.getString(8));
						utente.setMatricola(rs.getString(9));
						
					}
					else {
						utente = null;
					}
					return utente;

				} finally {
					try {
						if(preparedStatement != null)
							preparedStatement.close();
					} finally {
						DriverManagerConnectionPool.releaseConnection(connection);
					}
				}	
				
				
			}
	
	public static void deleteUtenteById(int id) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
				
				String deleteSQL = "DELETE FROM utente WHERE id= ?";
				
				try {
					connection = DriverManagerConnectionPool.getConnection();
					preparedStatement = connection.prepareStatement(deleteSQL);
					
					preparedStatement.setInt(1, id);
					
					System.out.println("deleteUtenteById:" + preparedStatement.toString());
					
					preparedStatement.executeUpdate();
					
					connection.commit();
				} finally {
					try {
						if(preparedStatement != null)
							preparedStatement.close();
					} finally {
						DriverManagerConnectionPool.releaseConnection(connection);
					}
				}	
				
				
			}
	
	}
