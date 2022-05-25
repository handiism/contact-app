package repository.contact;

import entity.Contact;
import entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
// import java.util.logging.Level;
// import java.util.logging.Logger;

public class ContactRepositoryImpl implements ContactRepository {
  private Connection connection;
  private User user;

  public ContactRepositoryImpl(Connection connection) {
    this.connection = connection;
  }

  public ContactRepositoryImpl(Connection connection, User user) {
    this.connection = connection;
    this.user = user;
  }

  public User getUser() { return user; }

  public void setUser(User user) { this.user = user; }

  @Override
  public Contact add(Contact contact) {
    try {
      String query =
          "INSERT INTO contact (name, phone, user_id) VALUES (?,?,?)";
      PreparedStatement preparedStatement = connection.prepareStatement(
          query, PreparedStatement.RETURN_GENERATED_KEYS);
      preparedStatement.setString(1, contact.getName());
      preparedStatement.setString(2, contact.getPhone());
      preparedStatement.setInt(3, user.getId());
      preparedStatement.executeUpdate();
      ResultSet resultSet = preparedStatement.getGeneratedKeys();
      if (resultSet.next()) {
        return new Contact(resultSet.getInt(1), contact.getName(),
                           contact.getPhone());
      }
      return contact;
    } catch (SQLException e) {
      // Logger.getLogger(ContactRepositoryImpl.class.getName())
      //     .log(Level.SEVERE, null, e);
      return null;
    }
  }

  @Override
  public Contact read(int id) {
    try {
      String query =
          "SELECT id, name, phone FROM contact WHERE id = ? AND user_id = ?";
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setInt(1, id);
      preparedStatement.setInt(2, user.getId());
      ResultSet resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        return new Contact(resultSet.getInt(1), resultSet.getString(2),
                           resultSet.getString(3));
      }
      return null;
    } catch (SQLException ex) {
      // Logger.getLogger(ContactRepositoryImpl.class.getName())
      //     .log(Level.SEVERE, null, ex);
      return null;
    }
  }

  @Override
  public List<Contact> readAll() {
    try {
      ArrayList<Contact> contact = new ArrayList<>();
      String query = "SELECT id, name, phone FROM contact WHERE user_id = ?";
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setInt(1, user.getId());
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        Contact note = new Contact(resultSet.getInt(1), resultSet.getString(2),
                                   resultSet.getString(3));
        contact.add(note);
      }
      // jika contact.isEmpty() bener maka return null, jika salah maka return
      // contact
      return contact;
    } catch (SQLException ex) {
      // Logger.getLogger(ContactRepositoryImpl.class.getName())
      //     .log(Level.SEVERE, null, ex);
      return null;
    }
  }

  @Override
  public void update(Contact contact) {
    try {
      String query =
          "UPDATE contact SET name = ?, phone = ? WHERE id = ? AND user_id = ?";
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1, contact.getName());
      preparedStatement.setString(2, contact.getPhone());
      preparedStatement.setInt(3, contact.getId());
      preparedStatement.setInt(4, user.getId());
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      // Logger.getLogger(ContactRepositoryImpl.class.getName())
      //     .log(Level.SEVERE, null, e);
    }
  }

  @Override
  public void delete(Contact note) {
    try {
      String query = "DELETE FROM contact WHERE id = ? AND user_id = ?";
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setInt(1, note.getId());
      preparedStatement.setInt(2, user.getId());
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      // Logger.getLogger(ContactRepositoryImpl.class.getName())
      //     .log(Level.SEVERE, null, e);
    }
  }
}
