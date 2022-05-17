package entity;

public class User extends Contact {
  private String password;

  public User() {}

  public User(String name, String password) {
    this.setName(name);
    this.password = password;
  }

  public User(int id, String name, String phone, String password) {
    super(id, name, phone);
    this.password = password;
  }

  public User(String name, String phone, String password) {
    super(name, phone);
    this.password = password;
  }

  public String getPassword() { return password; }
  public void setPassword(String password) { this.password = password; }
}
