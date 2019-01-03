package storage_layer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import application_logic_layer.Lezione;
import application_logic_layer.gestione_corsi_insegnamento.CorsoInsegnamento;

public class LezioneDao 
{
	public static void addLezione(Lezione lezione, int id_corso) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertSQL = "INSERT INTO lezione (nome, data_lezione, descrizione, valutazione, id_corso) VALUES (?, ?, ?, ?, ?)";
		try 
		{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, lezione.getNome());
			preparedStatement.setString(2, lezione.getData());
			preparedStatement.setString(3, lezione.getDescrizione());
			preparedStatement.setString(4, lezione.getValutazione());
			preparedStatement.setInt(5, id_corso);
			
			System.out.println("addLezione: "+ preparedStatement.toString());
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
	
	public static void removeLezione(int id_lezione) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String deleteSQL = "DELETE FROM lezione WHERE id = ?";
		try 
		{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			
			preparedStatement.setInt(1, id_lezione);
			
			preparedStatement.executeUpdate();
			
			System.out.println("removeLezione: "+ preparedStatement.toString());
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
	
	public static ArrayList<Lezione> getListaLezioni(int id_corso) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		ArrayList<Lezione> lezioni = new ArrayList<Lezione>();
		String selectSQL = "SELECT * FROM lezione WHERE id_corso = ?"; 
		try 
		{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			preparedStatement.setInt(1, id_corso);
			
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) 
			{
				Lezione lezione = new Lezione();
				lezione.setId(rs.getInt("id"));
				lezione.setNome(rs.getString("nome"));
				lezione.setData(rs.getString("data_lezione"));
				lezione.setDescrizione(rs.getString("descrizione"));
				lezione.setValutazione(rs.getString("valutazione"));
				lezioni.add(lezione);
			}
			System.out.println("getListaLezioni:" + preparedStatement.toString());
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
		return lezioni;
	}
}
