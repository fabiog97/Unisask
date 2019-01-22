package storage_layer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import application_logic_layer.gestione_corsi_insegnamento.CorsoInsegnamento;
import application_logic_layer.gestione_lezioni.Lezione;

public class LezioneDao 
{
	public static void addLezione(Lezione lezione, int id_corso) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertSQL = "INSERT INTO lezione (nome, data_lezione, descrizione, valutazione_media, id_corso) VALUES (?, ?, ?, ?, ?)";
		try 
		{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, lezione.getNome());
			preparedStatement.setString(2, lezione.getData());
			preparedStatement.setString(3, lezione.getDescrizione());
			preparedStatement.setInt(4, 0);
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
	
	public static void addValutazioneLezione(int id_utente, int id_lezione, int valutazione) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertSQL = "INSERT INTO valuta (id_utente, id_lezione, valutazione) VALUES (?, ?, ?)";
		try 
		{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1,id_utente);
			preparedStatement.setInt(2,id_lezione);
			preparedStatement.setInt(3,valutazione);
			
			
			System.out.println("addValutazioneLezione: "+ preparedStatement.toString());
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
	
	public static double getMediaValutazioniById(int id_lezione) throws SQLException
	{
		double media =  0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String selectSQL = "SELECT AVG(valutazione) AS MediaValutazione FROM valuta WHERE id_lezione = ?";
		try 
		{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1,id_lezione);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) 
			{
				media = rs.getDouble("MediaValutazione");
				connection.commit();
			}
			
			System.out.println("getMediaValutazioniById: "+ preparedStatement.toString());
			
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
		return media;
	}
	
	public static Lezione getLezioneById(int id_lezione) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Lezione lezione = new Lezione();
		
		String selectSQL = "SELECT * FROM lezione WHERE id = ?"; 
		try 
		{
			
			connection = DriverManagerConnectionPool.getConnection();
			
			preparedStatement = connection.prepareStatement(selectSQL);
			
			preparedStatement.setInt(1, id_lezione);
			
			ResultSet rs = preparedStatement.executeQuery();
			System.out.println("RS DB"+rs);
			while(rs.next()) 
			{
				lezione.setId(rs.getInt("id"));
				lezione.setNome(rs.getString("nome"));
				lezione.setData(rs.getString("data_lezione"));
				lezione.setDescrizione(rs.getString("descrizione"));
				lezione.setValutazione(rs.getString("valutazione_media"));
				connection.commit();
			}
			System.out.println("getLezioneById:" + preparedStatement.toString());
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
		return lezione;
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
	
	public static void updateValutazioneLezione(int id_lezione, double valutazione) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String updateSQL = "UPDATE lezione SET valutazione_media = ?  WHERE id = ?";
		try 
		{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
			
			preparedStatement.setDouble(1, valutazione);
			preparedStatement.setInt(2, id_lezione);
			
			
			preparedStatement.executeUpdate();
			
			System.out.println("updateValutazioneLezione: "+ preparedStatement.toString());
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
				lezione.setValutazione(rs.getString("valutazione_media"));
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
