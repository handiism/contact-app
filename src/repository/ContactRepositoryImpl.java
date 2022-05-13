package repository;

import entity.Contact;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ContactRepositoryImpl implements ContactRepository {
  private Connection connection;

  public ContactRepositoryImpl(Connection connection) {
    this.connection = connection;
    System.out.println(this.connection);
  }

  @Override
  public Contact add(Contact contact) {
    try {
      String query = "INSERT INTO contact (name, phone) VALUES (?,?)";
      PreparedStatement preparedStatement = connection.prepareStatement(
          query, PreparedStatement.RETURN_GENERATED_KEYS);
      preparedStatement.setString(1, contact.getName());
      preparedStatement.setString(2, contact.getPhone());
      preparedStatement.executeUpdate();
      ResultSet resultSet = preparedStatement.getGeneratedKeys();
      if (resultSet.next()) {
        return new Contact(resultSet.getInt(1), contact.getName(),
                           contact.getPhone());
      }
      return contact;
    } catch (SQLException e) {
      Logger.getLogger(ContactRepositoryImpl.class.getName())
          .log(Level.SEVERE, null, e);
      return null;
    }
  }

  @Override
  public Contact read(int id) {
    try {
      String query = "SELECT id, name, phone FROM contact WHERE id = ?";
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setInt(1, id);
      ResultSet resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        return new Contact(resultSet.getInt(1), resultSet.getString(2),
                           resultSet.getString(3));
      }
      return null;
    } catch (SQLException ex) {
      Logger.getLogger(ContactRepositoryImpl.class.getName())
          .log(Level.SEVERE, null, ex);
      return null;
    }
  }

  @Override
  public List<Contact> readAll() {
    try {
      ArrayList<Contact> contact = new ArrayList<>();
      String query = "SELECT id, name, phone FROM contact";
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        Contact note = new Contact(resultSet.getInt(1), resultSet.getString(2),
                                   resultSet.getString(3));
        contact.add(note);
      }
      // jika contact.isEmpty() bener maka return null, jika salah maka return contact 
      return contact.isEmpty() ? null : contact;
    } catch (SQLException ex) {
      Logger.getLogger(ContactRepositoryImpl.class.getName())
          .log(Level.SEVERE, null, ex);
      return null;
    }
  }

  @Override
  public void update(Contact contact) {
    try {
      String query = "UPDATE contact SET name = ?, phone = ? WHERE id = ?";
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1, contact.getName());
      preparedStatement.setString(2, contact.getPhone());
      preparedStatement.setInt(3, contact.getId());
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      Logger.getLogger(ContactRepositoryImpl.class.getName())
          .log(Level.SEVERE, null, e);
    }
  }

  @Override
  public void delete(Contact note) {
    try {
      String query = "DELETE FROM contact WHERE id = ?";
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setInt(1, note.getId());
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      Logger.getLogger(ContactRepositoryImpl.class.getName())
          .log(Level.SEVERE, null, e);
    }
  }
}
