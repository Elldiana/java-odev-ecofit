import java.util.*;
import java.sql.*;



public class Ecofit_Uygulama {


    public static void haftalikPlaniGoster() {
        String[] sporlar = {
                "Yürüyüş", "Koşu", "Bisiklet", "Yüzme", "Fonksiyonel Antrenman",
                "Kuvvet Antrenmanı", "HIIT"
        };

        System.out.println("Haftalık Spor Programı Önerisi:");
        for (int i = 0; i < 7; i++) {
            int rastgeleIndex = (int) (Math.random() * 7); // 0 ile 6 arasında rastgele sayı
            System.out.println(getGun(i) + ": " + sporlar[rastgeleIndex]);
        }
    }

    private static String getGun(int index) {
        String[] gunler = {"Pazartesi", "Salı", "Çarşamba", "Perşembe", "Cuma", "Cumartesi", "Pazar"};
        return gunler[index];
    }

    public static void sporOnerisiYap(int kalori) {
        String spor = "";

        if (kalori >= 0 && kalori <= 100) {
            spor = "Yürüyüş";
        } else if (kalori <= 200) {
            spor = "Koşu";
        } else if (kalori <= 300) {
            spor = "Bisiklet";
        } else if (kalori <= 400) {
            spor = "Yüzme";
        } else if (kalori <= 500) {
            spor = "Fonksiyonel Antrenman";
        } else if (kalori <= 600) {
            spor = "Kuvvet Antrenmanı";
        } else if (kalori > 600) {
            spor = "HIIT";
        }

        System.out.println("Günlük kaloriye göre önerilen spor: " + spor);
    }

    public static int kaloriHesapla(int dakika) {
        if (dakika < 0) {
            System.out.println("Geçersiz süre. Pozitif bir süre girin.");
            return 0;
        }
        int kalori = dakika * 5;
        System.out.println("Yakılan kalori: " + kalori + " kcal");
        return kalori;
    }

    public static String rastgeleSporSec() {
        String[] sporlar = {
                "Yürüyüş", "Koşu", "Bisiklet", "Yüzme", "Fonksiyonel Antrenman",
                "Kuvvet Antrenmanı", "HIIT"
        };

        int rastgeleIndex = (int) (Math.random() * 7); // 0 ile 6 arasında rastgele sayı
        return sporlar[rastgeleIndex];
    }

    public static void kaloriBilgisiGuncelle(int kullaniciId, int kalori) {
        String sql = "INSERT INTO kalori (kullanici_id, kalori) VALUES (?, ?) " +
                "ON DUPLICATE KEY UPDATE kalori = ?";
        try (Connection conn = MySqlBaglanti.baglan();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, kullaniciId);
            ps.setInt(2, kalori);
            ps.setInt(3, kalori);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Kalori bilgisi başarıyla güncellendi veya eklendi!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Kalori bilgisi güncellenirken bir hata oluştu.");
        }
    }

    public static void menu( int kullaniciId) {
        Scanner scanner = new Scanner(System.in);
        boolean cikis = false;

        while (!cikis) {
            System.out.println("\n*** Ana Menü ***");
            System.out.println("1. Kalori Hesapla");
            System.out.println("2. Spor Önerisi Yap");
            System.out.println("3. Haftalık Spor Programı Öner");
            System.out.println("4. Çıkış");
            System.out.print("Seçiminizi yapın (1-4): ");

            int secim = scanner.nextInt();
            scanner.nextLine(); // Boşluğu geç

            switch (secim) {
                case 1:
                    // Kalori hesaplama metodu
                    System.out.print("Egzersiz süresi (dakika): ");
                    int dakika = scanner.nextInt();
                    int kalori = kaloriHesapla(dakika);
                    kaloriBilgisiGuncelle(kullaniciId, kalori);
                    break;
                case 2:
                    // Spor önerisi yapma metodu
                    System.out.print("Harcadığınız kalori miktarını girin: ");
                    int harcananKalori = scanner.nextInt();
                    sporOnerisiYap(harcananKalori);
                    break;
                case 3:
                    // Haftalık spor programı önerisi metodu
                    haftalikPlaniGoster();
                    break;
                case 4:
                    // Çıkış
                    System.out.println("Çıkılıyor...");
                    cikis = true;
                    break;
                default:
                    // Geçersiz giriş
                    System.out.println("Geçersiz seçenek. Lütfen 1 ile 4 arasında bir sayı girin.");
            }
        }
    }
}




