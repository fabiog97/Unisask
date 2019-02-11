package storage_layer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Connector per il database.
 *
 * @author FabioGrauso & AntonioVitiello
 */
public class DriverManagerConnectionPool {

  private static List<Connection> freeDbConnections;

  static {
    freeDbConnections = new LinkedList<Connection>();
    try {
      Class.forName("com.mysql.jdbc.Driver");
    } catch (ClassNotFoundException e) {
      System.out.println("DB driver not found:" + e.getMessage());
    }
  }

  /**
   * Inizializza le connessioni.
   * @author FabioGrauso & AntonioVitiello
   */
  private static synchronized Connection createDbConnection() throws SQLException {
    Connection newConnection = null;
    String ip = "localhost";
    String port = "3306";
    String db = "unisask";
    String username = "root";
    String password = "root";

    newConnection =
        DriverManager.getConnection(
            "jdbc:mysql://" + ip + ":" + port + "/" + db, username, password);

    System.out.println("Create a new DB connection");
    newConnection.setAutoCommit(false);
    return newConnection;
  }
  
  /**
   * Funzione che restituisce la connessione.
   * @return
   */
  public static synchronized Connection getConnection() throws SQLException {
    Connection connection;

    if (!freeDbConnections.isEmpty()) {
      connection = (Connection) freeDbConnections.get(0);
      freeDbConnections.remove(0);

      try {
        if (connection.isClosed()) {
          connection = getConnection();
        }
      } catch (SQLException e) {
        connection.close();
        connection = getConnection();
      }
    } else {
      connection = createDbConnection();
    }

    return connection;
  }

  /**
   * Rilascia la connessione.
   *
   * @param connection rappresenta la connessione rilasciata e ri-aggiunta al pool di connessioni.
   * @author FabioGrauso & AntonioVitiello
   */
  public static synchronized void releaseConnection(Connection connection) throws SQLException {
    if (connection != null) {
      freeDbConnections.add(connection);
    }
  }
}
