package entity;

public class Contact {
  private int id;
  private String name;
  private String phone;

  public Contact() {}

  public Contact(String name, String phone) {
    this.name = name;
    this.phone = phone;
  }

  public Contact(int id, String name, String phone) {
    this.id = id;
    this.name = name;
    this.phone = phone;
  }

  public String getPhone() { return phone; }
  public int getId() { return id; }
  public void setId(int id) { this.id = id; }
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }
  public void setPhone(String phone) { this.phone = phone; }
}
