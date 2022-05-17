package entity;

public class Person {
  private int id;
  private String name;

  public Person() {}
  public Person(String name) { this.name = name; }
  public Person(int id, String name) {
    this.id = id;
    this.name = name;
  }

  public int getId() { return id; }
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }
  public void setId(int id) { this.id = id; }
}
