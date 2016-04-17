package Utils;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestDatabaseUtils extends DatabaseUtils {

    @Override
    public Connection getConnection() throws SQLException, NamingException {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.jdbc.Driver").newInstance());
            String url = "jdbc:mysql://localhost:3306/dormitorydb?characterEncoding=utf8&user=root&password=";
            return DriverManager.getConnection(url);
        }
        catch (SQLException ex){ }
        catch (Exception ex){ }
        return null;
    }
}
