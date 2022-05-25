import database.MySQL;
import entity.Contact;
import entity.User;
import java.util.List;
import java.util.Scanner;
import repository.contact.ContactRepository;
import repository.contact.ContactRepositoryImpl;
import repository.user.UserRepository;
import repository.user.UserRepositoryImpl;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    MySQL mySQL = new MySQL();
    UserRepository userRepository =
        new UserRepositoryImpl(mySQL.getConnection());
    do {
      System.out.print("\033[H\033[2J");
      System.out.flush();

      System.out.println("Contact App");
      System.out.println("-----------");
      System.out.println("[1] Daftar");
      System.out.println("[2] Login");
      System.out.println("[3] Keluar");
      System.out.println("--------");
      System.out.print("Pilihan: ");
      String menu = scanner.nextLine();
      if (menu.equals("1")) {
        System.out.print("\033[H\033[2J");
        System.out.flush();

        System.out.println("Daftar User");
        System.out.println("-----------");
        System.out.print("Nama : ");
        String name = scanner.nextLine();
        System.out.print("Nomor HP : ");
        String phone = scanner.nextLine();
        System.out.print("Password : ");
        String password = scanner.nextLine();
        System.out.println("-----------");
        User user = new User(name, phone, password);
        boolean success = userRepository.register(user);
        if (success) {
          System.out.println("Pendaftaran Berhasil");
        } else {
          System.out.println("Pendaftaran Gagal");
        }
        System.out.println("-----------------");
        System.out.print("Ulangi menu? (y/n) : ");
        menu = scanner.nextLine();
        if (menu.equals("y")) {
          continue;
        } else {
          break;
        }
      } else if (menu.equals("2")) {
        System.out.print("\033[H\033[2J");
        System.out.flush();

        System.out.println("Login User");
        System.out.println("----------");
        System.out.print("Nomor HP : ");
        String phone = scanner.nextLine();
        System.out.print("Password : ");
        String password = scanner.nextLine();
        User user = new User();
        user.setPhone(phone);
        user.setPassword(password);
        user = userRepository.login(user);
        if (user != null) {
          do {
            System.out.print("\033[H\033[2J");
            System.out.flush();

            ContactRepository contactRepository =
                new ContactRepositoryImpl(mySQL.getConnection(), user);

            System.out.println("Menu Kontak");
            System.out.println("-----------");
            System.out.println("[1] Tambah");
            System.out.println("[2] Lihat");
            System.out.println("[3] Edit");
            System.out.println("[4] Cari");
            System.out.println("[5] Hapus");
            System.out.println("[6] Keluar");
            System.out.println("---------");
            System.out.print("Pilihan : ");
            menu = scanner.nextLine();
            if (menu.equals("1")) {
              System.out.print("\033[H\033[2J");
              System.out.flush();

              System.out.println("Tambah Kontak");
              System.out.println("-------------");
              System.out.print("Nama : ");
              String contactName = scanner.nextLine();
              System.out.print("Nomor HP : ");
              String contactPhone = scanner.nextLine();
              Contact contact = new Contact(contactName, contactPhone);
              contact = contactRepository.add(contact);
              if (contact != null) {
                System.out.println("------------------");
                System.out.println("Kontak Ditambahkan");
              } else {
                System.out.println("-----------------------");
                System.out.println("Penambahan Kontak Gagal");
              }
              System.out.println("-------------------------------");
              System.out.print("Kembali ke menu kontak? (y/n) : ");
              menu = scanner.nextLine();
              if (menu.equals("y")) {
                continue;
              } else {
                break;
              }
            } else if (menu.equals("2")) {
              System.out.print("\033[H\033[2J");
              System.out.flush();

              System.out.println("Lihat Kontak");
              System.out.println("------------");
              System.out.format("%-4s%-20s%-13s\n", "ID", "Nama", "Nomor HP");
              List<Contact> contacts = contactRepository.readAll();
              if (contacts.isEmpty()) {
                System.out.format("%-4s%-20s%-13s\n", "-", "-", "-");
              } else {
                for (Contact c : contacts) {
                  System.out.format("%-4d%-20s%-13s\n", c.getId(), c.getName(),
                          c.getPhone());
                }
              }
              System.out.println("-------------------------------");
              System.out.print("Kembali ke menu kontak? (y/n) : ");
              menu = scanner.nextLine();
              if (menu.equals("y")) {
                continue;
              } else {
                break;
              }
            } else if (menu.equals("3")) {
              System.out.print("\033[H\033[2J");
              System.out.flush();

              System.out.println("Edit Kontak");
              System.out.println("-----------");
              System.out.print("ID : ");
              int contactId;
              try {
                contactId = Integer.parseInt(scanner.nextLine());
              } catch (Exception e) {
                contactId = -1;
              }
              Contact contact = contactRepository.read(contactId);
              if (contact != null) {
                System.out.println("------");
                System.out.print("Nama : ");
                String contactName = scanner.nextLine();
                System.out.print("Nomor HP : ");
                String contactPhone = scanner.nextLine();
                contactRepository.update(
                    new Contact(contact.getId(), contactName, contactPhone));
                System.out.println("----------------------");
                System.out.println("Kontak berhasil diedit");
              } else {
                System.out.println("----------------------");
                System.out.println("Kontak tidak ditemukan");
              }
              System.out.println("-------------------------------");
              System.out.print("Kembali ke menu kontak? (y/n) : ");
              menu = scanner.nextLine();
              if (menu.equals("y")) {
                continue;
              } else {
                break;
              }
            } else if (menu.equals("4")) {
              System.out.print("\033[H\033[2J");
              System.out.flush();

              System.out.println("Cari Kontak");
              System.out.println("-----------");
              System.out.print("ID : ");
              int id;
              try {
                id = Integer.parseInt(scanner.nextLine());
              } catch (Exception e) {
                id = -1;
              }
              Contact contact = contactRepository.read(id);
              if (contact != null) {
                System.out.println("------");
                System.out.printf("Nama : %s\n", contact.getName());
                System.out.printf("Nomor HP : %s\n", contact.getPhone());
              } else {
                System.out.println("----------------------");
                System.out.println("Kontak tidak ditemukan");
              }
              System.out.println("-------------------------------");
              System.out.print("Kembali ke menu kontak? (y/n) : ");
              menu = scanner.nextLine();
              if (menu.equals("y")) {
                continue;
              } else {
                break;
              }
            } else if (menu.equals("5")) {
              System.out.print("\033[H\033[2J");
              System.out.flush();

              System.out.println("Hapus Kontak");
              System.out.println("------------");
              System.out.print("ID : ");
              int id;
              try {
                id = Integer.parseInt(scanner.nextLine());
              } catch (Exception e) {
                id = -1;
              }
              Contact contact = contactRepository.read(id);
              if (contact != null) {
                contactRepository.delete(contact);
                System.out.println("-----------------------");
                System.out.println("Kontak berhasil dihapus");
              } else {
                System.out.println("----------------------");
                System.out.println("Kontak tidak ditemukan");
              }
              System.out.println("-------------------------------");
              System.out.print("Kembali ke menu kontak? (y/n) : ");
              menu = scanner.nextLine();
              System.out.println(menu.equals("y"));
              if (menu.equals("y")) {
                continue;
              } else {
                break;
              }
            } else if (menu.equals("6")) {
              System.out.print("\033[H\033[2J");
              System.out.flush();
              break;
            } else {
              System.out.println("----------------------");
              System.out.println("Pilihan tidak tersedia");
              System.out.println("---------------------------");
              System.out.print("Ulangi menu kontak? (y/n) : ");
              menu = scanner.nextLine();
              System.out.println(menu.equals("y"));
              if (menu.equals("y")) {
                continue;
              } else {
                break;
              }
            }
          } while (true);
        } else {
          System.out.println("-----------");
          System.out.println("Login gagal");
          System.out.println("-----------------------------");
          System.out.print("Kembali ke menu awal? (y/n) : ");
          menu = scanner.nextLine();
          if (menu.equals("y")) {
            continue;
          } else {
            break;
          }
        }
      } else if (menu.equals("3")) {
        break;
      } else {
        System.out.println("-------------------------------------------");
        System.out.print("Pilihan tidak tersedia, ulangi menu? (y/n): ");
        menu = scanner.nextLine();
        if (menu != "y") {
          break;
        } else {
          continue;
        }
      }
    } while (true);
    scanner.close();
  }
}
