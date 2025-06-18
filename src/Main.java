import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        // giriş, uyeol, çıkış
        Scanner scanner = new Scanner(System.in);
        while (true) {

            System.out.println("1 - Üye Ol");
            System.out.println("2 - Giriş Yap");
            System.out.println("0 - Çıkış");
            System.out.print("Seçiminizi yapın: ");
            int secim = scanner.nextInt();
            scanner.nextLine();  // Boşluğu geçmek için

            if (secim == 1) {
                Kullanici_Islemleri.uyeOl();
            }
            else if (secim == 2) {
                int kullaniciId = Kullanici_Islemleri.girisYap();
                if (kullaniciId != -1) {
                    System.out.println("Ana menüye yönlendiriliyorsunuz...");
                    Ecofit_Uygulama.menu(kullaniciId);
                } else {
                    System.out.println("Giriş başarısız. Tekrar deneyin.");
                }
            }

            else if (secim == 0) {
                // Çıkış
                System.out.println("Çıkılıyor...");
                break;
            } else {
                System.out.println("Geçersiz seçim, lütfen tekrar deneyin.");
            }
        }
        
    }
}