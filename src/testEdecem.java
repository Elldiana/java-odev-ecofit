public class testEdecem {

    public static void main(String[] args) {
        if (MySqlBaglanti.baglan() != null) {
            System.out.println("Bağlantı başarılı.");
        } else {
            System.out.println("Bağlantı başarısız.");
        }
    }
}
