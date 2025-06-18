import java.sql.Connection;
import java.sql.DriverManager;

public class MySqlBaglanti {
    public static Connection baglan() {
        try {
            String url = "jdbc:mysql://localhost:3306/ekofit";
            String kullanici = "root";
            String parola = "3696";

            Connection conn = DriverManager.getConnection(url, kullanici, parola);
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
