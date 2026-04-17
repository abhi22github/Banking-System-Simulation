import java.sql.Connection;
import java.sql.DriverManager;

public class TestConnection {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/Banking";
        String user = "root";
        String pass = "Abhi2209!!";

        try {
            System.out.println("Checking for MySQL Driver...");
            // Force load the driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver found!");

            System.out.println("Connecting to Database...");
            Connection conn = DriverManager.getConnection(url, user, pass);

            if (conn != null) {
                System.out.println("SUCCESS: Connection established to 'Banking' database!");
                conn.close();
            }
        } catch (ClassNotFoundException e) {
            System.out.println("FAILURE: MySQL Connector JAR is not in the Project Library!");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("FAILURE: Connection failed. Check if MySQL is running or if password is correct.");
            e.printStackTrace();
        }
    }
}