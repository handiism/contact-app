package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQL {

  private static final String DRIVER = "jdbc:mysql";
  private static final String HOSTNAME = "localhost";
  private static final String DATABASE = "notesapp";
  private static final String PORT = "3306";
  private static final String USERNAME = "root";
  private static final String PASSWORD = "";
  private Connection connection;

  public MySQL() {
    String url =
        String.format("%s://%s:%s/%s", DRIVER, HOSTNAME, PORT, DATABASE);
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      connection = DriverManager.getConnection(url, USERNAME, PASSWORD);
      System.out.println(connection.getClientInfo());
      System.out.println("Success");
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  public Connection getConnection() { return connection; }
}
