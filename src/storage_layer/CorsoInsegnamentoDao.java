package storage_layer;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import com.mysql.jdbc.Statement;

import application_logic_layer.gestione_corsi_insegnamento.CorsoInsegnamento;
import application_logic_layer.gestione_utente.Utente;

public class CorsoInsegnamentoDao {
	public static boolean removeCorso(int id_corso) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;
		String deleteSQL = "DELETE FROM corso WHERE id = ?";
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);

			preparedStatement.setInt(1, id_corso);

			result = preparedStatement.executeUpdate();

			System.out.println("removeCorso: " + preparedStatement.toString());
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

	public static boolean iscrizioneCorso(int id_corso, int id_utente) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String insertSQL = "INSERT INTO iscrive (id_utente, id_corso) VALUES (?, ?)";
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, id_utente);
			preparedStatement.setInt(2, id_corso);

			System.out.println("iscrizioneCorso: " + preparedStatement.toString());
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

	public static boolean disiscrizioneCorso(int id_utente, int id_corso) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;
		String deleteSQL = "DELETE FROM iscrive WHERE id_utente = ? AND id_corso = ?";
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);

			preparedStatement.setInt(1, id_utente);
			preparedStatement.setInt(2, id_corso);

			result = preparedStatement.executeUpdate();

			System.out.println("disiscrizioneCorso: " + preparedStatement.toString());
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

	public static ArrayList<CorsoInsegnamento> getListaCorsi() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<CorsoInsegnamento> corsi = new ArrayList<CorsoInsegnamento>();
		String selectSQL = "SELECT * FROM corso";
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				CorsoInsegnamento corso = new CorsoInsegnamento();
				corso.setId(rs.getInt("id"));
				corso.setNome(rs.getString("nome"));
				corso.setCorsoDiLaurea(rs.getString("corso_di_laurea"));
				corso.setAnnoAccademico(rs.getString("anno_accademico"));
				corso.setAnnoDiStudio(rs.getString("anno_di_studio"));
				corso.setSemestre(rs.getString("semestre"));
				corsi.add(corso);
			}
			System.out.println("getListaCorsi:" + preparedStatement.toString());
			connection.commit();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return corsi;
	}

	public static CorsoInsegnamento getCorsoById(int id_corso) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		CorsoInsegnamento corso = null;
		ArrayList<CorsoInsegnamento> corsi = new ArrayList<CorsoInsegnamento>();
		String selectSQL = "SELECT * FROM corso WHERE id = ?";
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				corso = new CorsoInsegnamento();
				corso.setId(rs.getInt("id"));
				corso.setNome(rs.getString("nome"));
				corso.setCorsoDiLaurea(rs.getString("corso_di_laurea"));
				corso.setAnnoAccademico(rs.getString("anno_accademico"));
				corso.setAnnoDiStudio(rs.getString("anno_di_studio"));
				corso.setSemestre(rs.getString("semestre"));

			}
			System.out.println("getCorsoById:" + preparedStatement.toString());
			connection.commit();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return corso;
	}

	public static boolean addCorso(CorsoInsegnamento corso) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		PreparedStatement preparedStatement1 = null;

		int id_corso = 0;

		String insertCorsoSQL = "INSERT INTO corso (nome, corso_di_laurea, anno_accademico, anno_di_studio, semestre, id_utente) VALUES (?, ?, ?, ?, ?, ?)";

		String insertInsegnaSQL = "INSERT INTO insegna (id_utente, id_corso) VALUES (?, ?)";
		int result1 = 0;
		int result2 = 0;
		try {
			connection = DriverManagerConnectionPool.getConnection();

			preparedStatement = connection.prepareStatement(insertCorsoSQL, Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setString(1, corso.getNome());
			preparedStatement.setString(2, corso.getCorsoDiLaurea());
			preparedStatement.setString(3, corso.getAnnoAccademico());
			preparedStatement.setString(4, corso.getAnnoDiStudio());
			preparedStatement.setString(5, corso.getSemestre());
			preparedStatement.setInt(6, 1); // id amministratore

			System.out.println("addCorso: " + preparedStatement.toString());
			result1 = preparedStatement.executeUpdate();

			ResultSet rs = preparedStatement.getGeneratedKeys();

			if (rs.next()) {
				id_corso = rs.getInt(1);
			}
			rs.close();

			preparedStatement1 = connection.prepareStatement(insertInsegnaSQL);

			Iterator<Utente> it = corso.getDocenti().iterator();
			while (it.hasNext()) {
				Utente docente = (Utente) it.next();
				preparedStatement1.setInt(1, docente.getId());
				preparedStatement1.setInt(2, id_corso);
				System.out.println("addInsegnaCorso: " + preparedStatement1.toString());
				result2 = preparedStatement1.executeUpdate();
			}

			connection.commit();

		}

		finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return (result1 != 0 && result2 != 0);

	}

	public static ArrayList<CorsoInsegnamento> getListaCorsiInsegnanti(int id_utente) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<CorsoInsegnamento> corsi = new ArrayList<CorsoInsegnamento>();
		String selectSQL = "select * from corso, (SELECT id_corso AS id_corso from insegna where id_utente= ? ) AS insegna where id = id_corso;";
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			preparedStatement.setInt(1, id_utente);

			ResultSet rs = preparedStatement.executeQuery();

			System.out.println("getListaCorsiInsegnati:" + preparedStatement.toString());

			while (rs.next()) {
				CorsoInsegnamento corso = new CorsoInsegnamento();
				corso.setId(rs.getInt("id"));
				corso.setNome(rs.getString("nome"));
				corso.setCorsoDiLaurea(rs.getString("corso_di_laurea"));
				corso.setAnnoAccademico(rs.getString("anno_accademico"));
				corso.setAnnoDiStudio(rs.getString("anno_di_studio"));
				corso.setSemestre(rs.getString("semestre"));
				corsi.add(corso);
				connection.commit();
			}
		}

		finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			}

			finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}

		return corsi;
	}

	public static ArrayList<Utente> getListaDocentiByCorso(int id_corso) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<Utente> docenti = new ArrayList<Utente>();
		String selectSQL = "SELECT * FROM utente  WHERE id IN (SELECT id_utente  FROM insegna  WHERE id_corso = ?);";
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			preparedStatement.setInt(1, id_corso);

			ResultSet rs = preparedStatement.executeQuery();

			System.out.println("getListaDocentiByCorso:" + preparedStatement.toString());

			while (rs.next()) {
				Utente user = new Utente();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setNome(rs.getString("nome"));
				user.setCognome(rs.getString("cognome"));
				user.setEmail(rs.getString("email"));
				user.setMatricola(rs.getString("matricola"));
				user.setNazionalita(rs.getString("nazionalita"));
				user.setPassword(rs.getString("password"));
				user.setTipo(rs.getString("tipo"));
				docenti.add(user);
				connection.commit();
			}
		}

		finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			}

			finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}

		return docenti;
	}

	public static ArrayList<CorsoInsegnamento> getListaCorsiIscritti(int id_utente) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<CorsoInsegnamento> corsi = new ArrayList<CorsoInsegnamento>();
		String selectSQL = "SELECT *  FROM corso WHERE id IN ( SELECT id_corso FROM iscrive WHERE id_utente=(?));";
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			preparedStatement.setInt(1, id_utente);

			ResultSet rs = preparedStatement.executeQuery();

			System.out.println("getListaCorsiIscritti:" + preparedStatement.toString());

			while (rs.next()) {
				CorsoInsegnamento corso = new CorsoInsegnamento();
				corso.setId(rs.getInt("id"));
				corso.setNome(rs.getString("nome"));
				corso.setCorsoDiLaurea(rs.getString("corso_di_laurea"));
				corso.setAnnoAccademico(rs.getString("anno_accademico"));
				corso.setAnnoDiStudio(rs.getString("anno_di_studio"));
				corso.setSemestre(rs.getString("semestre"));
				corsi.add(corso);
				connection.commit();
			}
		}

		finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			}

			finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}

		return corsi;
	}
}
