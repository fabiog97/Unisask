package storage_layer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import application_logic_layer.Utente;

public class UtenteDao 
{
	public static void registraUtente(Utente utente) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL = "INSERT INTO utente (username, password, tipo, nome, cognome, email, nazionalita, matricola) VALUES (?, ?, ?, ?, ?, ?, ?, ?)"; // Viene eseguita una query con la quale si va ad inserire nel DB il nuovo utente
		try 
		{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, utente.getUsername());
			preparedStatement.setString(2, utente.getPassword());
			preparedStatement.setInt(3, 0);
			preparedStatement.setString(4, utente.getNome());
			preparedStatement.setString(5, utente.getCognome());
			preparedStatement.setString(6, utente.getEmail());
			preparedStatement.setString(7, utente.getNazionalita());
			preparedStatement.setString(4, utente.getMatricola());
			
			System.out.println("registraUtente: "+ preparedStatement.toString());
			preparedStatement.executeUpdate();
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
	
	public void login(Utente utente)
	{
		
	}
	
	public void resetPassword(Utente utente, String password)
	{
		
	}
	
	public void getUtenteByUsername(String username)
	{
		
	}
}
