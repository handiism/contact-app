import database.MySQL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import repository.ContactRepositoryImpl;

public class Main {
  public static void main(String[] args) {
    MySQL mySQL = new MySQL();

    Connection connection = mySQL.getConnection();
    ContactRepositoryImpl contactRepositoryImpl =
        new ContactRepositoryImpl(connection);

    do {
      System.out.println("menu");
      System.out.println("1 lihat");
      System.out.println("2 tambah");
      System.out.println("3 ubah");
      System.out.println("4 hapus");
    } while (true);
  }
}
