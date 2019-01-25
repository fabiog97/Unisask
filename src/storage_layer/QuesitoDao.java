package storage_layer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import com.mysql.jdbc.Statement;

import application_logic_layer.gestione_corsi_insegnamento.CorsoInsegnamento;
import application_logic_layer.gestione_lezioni.Lezione;
import application_logic_layer.gestione_quesiti.Quesito;
import application_logic_layer.gestione_utente.Utente;


public class QuesitoDao 
{
	public static void addDomanda(Quesito quesito, int id_lezione, int id_utente) throws SQLException
	{
		Connection connection = null;
		int id_quesito = 0;
		PreparedStatement preparedStatement = null;
		PreparedStatement preparedStatement1 = null;
		String insertSQL = "INSERT INTO quesito (domanda, risposta, data_quesito, id_lezione, id_utente, completo) VALUES (?, ?, ?, ?, ?, ?)";
		String insertRiceveSQL = "INSERT INTO riceve (id_utente, id_quesito) VALUES (?, ?)";
		
		try 
		{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL,Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, quesito.getDomanda());
			preparedStatement.setString(2, quesito.getRisposta());
			preparedStatement.setString(3, quesito.getData());
			preparedStatement.setInt(4, id_lezione);
			preparedStatement.setInt(5, id_utente);
			preparedStatement.setInt(6, 0);
			
			System.out.println("addDomanda: "+ preparedStatement.toString());
			preparedStatement.executeUpdate();
			
			ResultSet rs = preparedStatement.getGeneratedKeys();
		       
			if (rs.next()){
	            id_quesito = rs.getInt(1);
	            
	        }
	        rs.close();
	        
	        preparedStatement1 = connection.prepareStatement(insertRiceveSQL);
	        
	        Iterator<Utente> it = quesito.getDocenti().iterator();
	        
	        System.out.println(quesito.getDocenti().isEmpty());
	        
			while(it.hasNext())
			{
				Utente docente = (Utente) it.next();
				preparedStatement1.setInt(1, docente.getId());
				preparedStatement1.setInt(2, id_quesito);
				System.out.println("addRiceveQuesito: "+ preparedStatement1.toString());
				preparedStatement1.executeUpdate();
			}
	        
			
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
	
	
	public static void addRisposta(Quesito quesito) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertSQL = "UPDATE quesito SET completo = 1, risposta = ? WHERE id = ?";
		try 
		{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, quesito.getRisposta());
			preparedStatement.setInt(2, quesito.getId());
			
			System.out.println("addRisposta: "+ preparedStatement.toString());
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
	
	public static void removeQuesito(int id_quesito) throws SQLException
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
	
	public static ArrayList<Quesito> getAllRisposte() throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		ArrayList<Quesito> quesiti = new ArrayList<Quesito>();
		String selectSQL = "SELECT * FROM quesito WHERE completo = 1"; 
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
	
	public static int getCountDomandeByIdLezione(int id_lezione) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int count = 0;
		
		
		String selectSQL = "SELECT COUNT(id) AS conteggio FROM quesito WHERE id_lezione = ? AND completo = 0;"; 
		try 
		{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, id_lezione);
			
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) 
			{
				count = rs.getInt("conteggio");
				connection.commit();
			}
			System.out.println("getCountDomandeByIdLezione:" + preparedStatement.toString());
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
		return count;
	}
	
	public static int getCountDomandeByIdCorso(int id_corso) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int count = 0;
		
		
		String selectSQL = "SELECT COUNT(id) AS conteggio FROM quesito WHERE completo = 0 AND  id_lezione IN (SELECT id FROM lezione WHERE id_corso = ?);"; 
		try 
		{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, id_corso);
			
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) 
			{
				count = rs.getInt("conteggio");
				connection.commit();
			}
			System.out.println("getCountDomandeByIdCorso:" + preparedStatement.toString());
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
		return count;
	}
	
	public static ArrayList<Quesito> getRisposteByLezione(int id_lezione) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		ArrayList<Quesito> quesiti = new ArrayList<Quesito>();
		String selectSQL = "SELECT * FROM quesito WHERE id_lezione = ? AND completo = 1"; 
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
			System.out.println("getRisposteByLezione:" + preparedStatement.toString());
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
	
	public static ArrayList<Quesito> getRisposteByIdUtente(int id_utente) throws SQLException //Restituisce le risposte date ad uno studente
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		ArrayList<Quesito> quesiti = new ArrayList<Quesito>();
		String selectSQL = "SELECT * FROM quesito WHERE id_utente = ? AND completo = 1"; 
		try 
		{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			preparedStatement.setInt(1, id_utente);
			
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
			System.out.println("getRisposteByIdUtente:" + preparedStatement.toString());
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
		System.out.println("Quesiti è vuoto: "+quesiti.isEmpty());
		return quesiti;
	}
	
	public static ArrayList<Quesito> getDomandeByIdUtente(int id_utente) throws SQLException //Restituisce tutte le domande che riceve il docente
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		ArrayList<Quesito> quesiti = new ArrayList<Quesito>();
		String selectSQL = "SELECT * FROM quesito WHERE completo = 0 AND id IN (SELECT id_quesito FROM riceve WHERE id_utente = ?)";
		try 
		{
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			preparedStatement.setInt(1, id_utente);
			
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
			System.out.println("getDomandeById:" + preparedStatement.toString());
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
	
	
	
	public static ArrayList<Quesito> getDomandeByLezioneIdUtente(int id_lezione,int id_utente) throws SQLException //Restituisce tutte le domande che riceve il docente per una determinata lezione
	{
	
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			
			ArrayList<Quesito> quesiti = new ArrayList<Quesito>();
			String selectSQL = "SELECT * FROM quesito WHERE completo = 0 AND id_lezione = ? AND id IN (SELECT id_quesito FROM riceve WHERE id_utente = ?)";
			try 
			{
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				
				preparedStatement.setInt(1, id_lezione);
				preparedStatement.setInt(2, id_utente);
				
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
				System.out.println("getDomandeByLezioneUsername:" + preparedStatement.toString());
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
	
	public static CorsoInsegnamento getCorsoByIdQuesito(int id_quesito) throws SQLException //Restituisce tutte le domande che riceve il docente per una determinata lezione
	{
	
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			CorsoInsegnamento corso = null;
			
			String selectSQL = "SELECT * from corso WHERE id IN (SELECT id_corso FROM lezione WHERE id IN (SELECT id_lezione FROM quesito WHERE id = ?));";
			try 
			{
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				
				preparedStatement.setInt(1, id_quesito);
				
				
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()) 
				{
					corso = new CorsoInsegnamento();
					corso.setId(rs.getInt("id"));
					corso.setNome(rs.getString("nome"));
					
				}
				System.out.println("getDomandeByLezioneUsername:" + preparedStatement.toString());
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
			return corso;
	}
	
	public static Quesito getQuesitoById(int id_quesito) throws SQLException 
	{
	
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			Quesito quesito = null;
			
			String selectSQL = "SELECT * FROM quesito WHERE id = ?;";
			try 
			{
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				
				preparedStatement.setInt(1, id_quesito);
				
				
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()) 
				{
					quesito = new Quesito();
					quesito.setId(rs.getInt("id"));
					quesito.setDomanda(rs.getString("domanda"));
					quesito.setRisposta(rs.getString("risposta"));
					
					
				}
				System.out.println("getQuesitoById:" + preparedStatement.toString());
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
			return quesito;
		}
	
	public static Lezione getLezioneByIdQuesito(int id_quesito) throws SQLException {
	
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			Lezione lezione = null;
			
			
			String selectSQL = "select * from lezione WHERE id IN (SELECT id_lezione from quesito WHERE id = ?);";
			try 
			{
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				
				preparedStatement.setInt(1, id_quesito);
				System.out.println("TEST2");
				
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()) 
				{
					lezione = new Lezione();
					lezione.setId(rs.getInt("id"));
					lezione.setData(rs.getString("data_lezione"));
					lezione.setNome(rs.getString("nome"));
					
				}
				System.out.println("getDomandeByLezioneUsername:" + preparedStatement.toString());
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
			System.out.println("Lezione: "+lezione.toString());
			return lezione;
		}
	
}
