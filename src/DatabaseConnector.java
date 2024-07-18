import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseConnector {
    private Connection connection;

    public DatabaseConnector() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/gg", "phanna", "123");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ResultSet getCustomers() {
        try {
            Statement statement = connection.createStatement();
            return statement.executeQuery("SELECT * FROM customers");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
