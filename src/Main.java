import database.MySQL;
import entity.Contact;
import entity.User;
import java.util.ArrayList;
import java.util.List;

import repository.contact.ContactRepository;
import repository.contact.ContactRepositoryImpl;
import repository.user.UserRepository;
import repository.user.UserRepositoryImpl;

public class Main {
  public static void main(String[] args) {
    MySQL mySQL = new MySQL();
    UserRepository userRepository =
        new UserRepositoryImpl(mySQL.getConnection());
    // register
    // boolean success =
    //     userRepository.register(new User("paijem", "99999", "paijem"));
    // if (success) {
    //   System.out.println("Sukses Daftar");
    // }

    // login
    User user = userRepository.login(new User("paijem", "99999", "paijem"));
    if (user == null) {
      System.out.println("Salah");
    } else {

      ContactRepository contactRepository =
          new ContactRepositoryImpl(mySQL.getConnection(), user);
      // nambah kontak berdasarkan user sik login
      // contactRepository.add(new Contact("kontak1", "1380130"));
      // contactRepository.add(new Contact("kontak2", "731013"));
      // contactRepository.add(new Contact("kontak3", "013801398"));
      List<Contact> contacs = contactRepository.readAll();
      for (Contact contact : contacs) {
        // System.out.println(contact.getId());
        // System.out.println(contact.getName());
        // System.out.println(contact.getPhone());
        // System.out.println();
      }

      // Contact contoh = contactRepository.read(5);
      // System.out.println(contoh.getId());
      // System.out.println(contoh.getName());
      // System.out.println(contoh.getPhone());
      // contactRepository.update(new Contact(1, "namebaru", "phonebaru"));
      // contactRepository.delete(new Contact(2, "namename", "phonephone"));
    }
  }
}
