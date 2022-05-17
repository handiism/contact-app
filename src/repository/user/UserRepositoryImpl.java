package repository.user;

import entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
// import java.util.logging.Level;
// import java.util.logging.Logger;

public class UserRepositoryImpl implements UserRepository {
  private Connection connection;

  public UserRepositoryImpl(Connection connection) {
    this.connection = connection;
  }

  @Override
  public boolean register(User user) {
    try {
      String query = "INSERT INTO users (name, phone, password) VALUES (?,?,?)";
      PreparedStatement preparedStatement = connection.prepareStatement(
          query, PreparedStatement.RETURN_GENERATED_KEYS);
      preparedStatement.setString(1, user.getName());
      preparedStatement.setString(2, user.getPhone());
      preparedStatement.setString(3, user.getPassword());
      preparedStatement.executeUpdate();
      ResultSet resultSet = preparedStatement.getGeneratedKeys();
      if (resultSet.next()) {
        return true;
      }
      return false;
    } catch (Exception e) {
      // Logger.getLogger(UserRepositoryImpl.class.getName())
      //     .log(Level.SEVERE, null, e);
      return false;
    }
  }

  @Override
  public User login(User user) {
    try {
      String query =
          "SELECT id, name, phone, password FROM users WHERE phone = ? AND password = ?";
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1, user.getPhone());
      preparedStatement.setString(2, user.getPassword());
      ResultSet resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        return new User(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4));
      }
    } catch (Exception e) {
      // Logger.getLogger(UserRepositoryImpl.class.getName())
      //     .log(Level.SEVERE, null, e);
    }
    return null;
  }
}
