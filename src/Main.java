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
    // tersedia menu login dan register

    // login memakai nomer hp dan password
    // register memakai nomer hp, nama, dan password

    // jika gagal register, maka terdapat menu akan mengulang atau
    // kembali ke menu login/register

    // jikalogin gagal maka terdapat menu untuk kembali login atau kembali ke
    // menu login/register

    // jika register berhasil, maka kembali ke menu login/register jika login
    // berhasil, maka terdapat menu lihat contact, ubah kontak, cari kontak,
    // hapus kontak

    // tambah kontak memakai nomer hp dan nama
    // edit kontak memakai id konttak, nomer hp, dan nama
    // hapus memakai id kontak
    // cari kontak memakai id kontak
  }
}
