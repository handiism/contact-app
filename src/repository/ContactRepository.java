package repository;

import entity.Contact;
import java.util.List;

public interface ContactRepository {
  Contact add(Contact contact);
  Contact read(int id);
  List<Contact> readAll();
  void update(Contact contact);
  void delete(Contact contact);
}
