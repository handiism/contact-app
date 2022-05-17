package repository.user;

import entity.User;

public interface UserRepository {
  User login(User user);
  boolean register(User user);
}
