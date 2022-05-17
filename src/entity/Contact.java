package entity;

public class Contact extends Person {
  private String phone;

  public Contact() {}

  public Contact(String name, String phone) {
    super(name);
    this.phone = phone;
  }

  public Contact(int id, String name, String phone) {
    super(id, name);
    this.phone = phone;
  }

  public String getPhone() { return phone; }
  public void setPhone(String phone) { this.phone = phone; }
}
