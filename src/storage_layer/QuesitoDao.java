package storage_layer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import application_logic_layer.Lezione;
import application_logic_layer.Quesito;
import application_logic_layer.gestione_utente.Utente;


public class QuesitoDao 
{
	public void addQuesito(Quesito quesito, Lezione lezione, Utente utente) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertSQL = "INSERT INTO quesito (domanda, risposta, data_quesito, id_lezione, id_utente) VALUES (?, ?, ?, ?, ?)";
		try 
		{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, quesito.getDomanda());
			preparedStatement.setString(2, quesito.getRisposta());
			preparedStatement.setString(3, quesito.getData());
			preparedStatement.setInt(4, lezione.getId());
			preparedStatement.setInt(5, utente.getId());
			
			System.out.println("addQuesito: "+ preparedStatement.toString());
			preparedStatement.executeUpdate();
			connection.commit();
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
	
	public void removeQuesito(int id_quesito) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String deleteSQL = "DELETE FROM quesito WHERE id = ?";
		try 
		{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			
			preparedStatement.setInt(1, id_quesito);
			
			preparedStatement.executeUpdate();
			
			System.out.println("removeQuesito: "+ preparedStatement.toString());
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
	
	public ArrayList<Quesito> getAllQuesiti() throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		ArrayList<Quesito> quesiti = new ArrayList<Quesito>();
		String selectSQL = "SELECT * FROM quesito"; 
		try 
		{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) 
			{
				Quesito quesito = new Quesito();
				quesito.setId(rs.getInt("id"));
				quesito.setDomanda(rs.getString("domanda"));
				quesito.setRisposta(rs.getString("risposta"));
				quesito.setData(rs.getString("data_quesito"));
				quesiti.add(quesito);
			}
			System.out.println("getAllQuesiti:" + preparedStatement.toString());
			connection.commit();
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
		return quesiti;
	}
	
	public ArrayList<Quesito> getQuesitiByLezione(int id_lezione) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		ArrayList<Quesito> quesiti = new ArrayList<Quesito>();
		String selectSQL = "SELECT * FROM quesito WHERE id_lezione = ?"; 
		try 
		{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			preparedStatement.setInt(1, id_lezione);
			
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) 
			{
				Quesito quesito = new Quesito();
				quesito.setId(rs.getInt("id"));
				quesito.setDomanda(rs.getString("domanda"));
				quesito.setRisposta(rs.getString("risposta"));
				quesito.setData(rs.getString("data_quesito"));
				quesiti.add(quesito);
			}
			System.out.println("getAllQuesiti:" + preparedStatement.toString());
			connection.commit();
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
		return quesiti;
	}
	
	public void getQuesitiByUsername(String username)
	{
		
	}
	
	public void getQuesitiByLezioneUsername(int id_lezione,String username)
	{
		
	}
	
	public void getQuesitiByParola(String parola)
	{
		
	}
}
