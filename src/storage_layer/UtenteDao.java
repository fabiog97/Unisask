package storage_layer;

import java.sql.Connection;



import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

import application_logic_layer.Utente;


public class UtenteDao 
{
	public static void registraUtente(Utente utente, int codice) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		PreparedStatement preparedStatement1 = null;
		
		int id_utente = 0;
		String insertSQL = "INSERT INTO utente (username, password, tipo, nome, cognome, email, nazionalita, matricola) VALUES (?, ?, ?, ?, ?, ?, ?, ?)"; // Viene eseguita una query con la quale si va ad inserire nel DB il nuovo utente

		
		String insertSQL1 = "INSERT INTO codici_conferma (codice, id_utente) VALUES (?, ?)"; 
		
		try 
		{
			connection = DriverManagerConnectionPool.getConnection();
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

		String deleteSQL = "UPDATE utente SET tipo= ? WHERE id = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
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
		boolean find = false;
		
		try 
		{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			preparedStatement.setString(1, utente.getUsername());
			preparedStatement.setString(2, utente.getPassword());
			
			System.out.println("Login:" + preparedStatement.toString());
			
			ResultSet rs = preparedStatement.executeQuery(); 
			
			
			while(rs.next()) 
			{
				
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setTipo(rs.getString("tipo"));
			
				System.out.println("doLogin: "+ preparedStatement.toString());
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
	
	public void resetPassword(Utente utente, String password)
	{
		
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
}
