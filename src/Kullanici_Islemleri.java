import java.sql.*;
import java.util.Scanner;

public class Kullanici_Islemleri {

    public static void uyeOl() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ad: ");
        String ad = scanner.nextLine();
        System.out.print("Soyad: ");
        String soyad = scanner.nextLine();
        System.out.print("Öğrenci No: ");
        int ogrNo = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Kullanıcı Adı: ");
        String kullaniciAd = scanner.nextLine();
        System.out.print("Parola: ");
        String parola = scanner.nextLine();

        try (Connection conn = MySqlBaglanti.baglan()) {
            String sql = "INSERT INTO kayit (ad, soyad, ogr_numara, kullanici_ad, parola) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, ad);
            ps.setString(2, soyad);
            ps.setInt(3, ogrNo);
            ps.setString(4, kullaniciAd);
            ps.setString(5, parola);
            ps.executeUpdate();
            System.out.println("Kayıt başarılı!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Kayıt başarısız.");
        }
    }

    public static int girisYap() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Kullanıcı Adı: ");
        String kullaniciAdi = scanner.nextLine();
        System.out.print("Parola: ");
        String parola = scanner.nextLine();

        try (Connection conn = MySqlBaglanti.baglan()) {
            String sql = "SELECT * FROM kayit WHERE kullanici_ad = ? AND parola = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, kullaniciAdi);
            ps.setString(2, parola);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("Giriş başarılı! Hoş geldiniz, " + rs.getString("ad"));
                return rs.getInt("id");
            } else {
                System.out.println("Giriş başarısız. Bilgilerinizi kontrol edin.");
                return -1;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
