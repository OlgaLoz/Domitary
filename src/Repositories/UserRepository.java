package Repositories;

import Interfaces.IRepository;
import Model.Role;
import Model.User;
import main.DatabaseUtils;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserRepository implements IRepository<User> {

    @Override
    public int create(User item) {
        Connection connection = null;
        Statement statement = null;
        int result = -1;

        try {
            connection = DatabaseUtils.getConnection();
            statement = connection.createStatement();

            statement.execute(String.format("INSERT INTO dormitorydb.user (Login, Password, ID_Role) VALUES ('%s','%s', '2');",
                    item.getLogin(), item.getPassword()));
            ResultSet resultSet = statement.executeQuery("SELECT LAST_INSERT_ID();");
            resultSet.next();
            result = resultSet.getInt("LAST_INSERT_ID()");
        }
        catch (NamingException ex) {  }
        catch (SQLException ex) { }
        finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
        return result;
    }

    @Override
    public User read(int id) {
        Connection connection = null;
        Statement statement = null;
        User result = new User();

        try {
            connection = DatabaseUtils.getConnection();
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM User WHERE User.ID_User = '%d'", id));
            while (resultSet.next()) {
                result.setLogin(resultSet.getString("Login"));
                result.setPassword(resultSet.getInt("Password"));
                result.setUserId(resultSet.getInt("ID_User"));
                result.setRole(Role.values()[resultSet.getInt("ID_Role")]);
            }

        }
        catch (NamingException ex) { }
        catch (SQLException ex) {  }
        finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
        return result;
    }

    @Override
    public void update(User item) {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = DatabaseUtils.getConnection();
            statement = connection.createStatement();
            statement.executeUpdate(String.format("UPDATE dormitorydb.user SET Login = '%s', Password = '%s' WHERE ID_User = '%s';",
                item.getLogin(), item.getPassword(), item.getUserId()));
        }
        catch (NamingException ex) {}
        catch (SQLException ex) { }
        finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
    }

    @Override
    public void delete(int id) {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = DatabaseUtils.getConnection();
            statement = connection.createStatement();

            statement.execute(String.format("DELETE FROM User WHERE ID_User = '%d'", id));
        }
        catch (NamingException ex) {}
        catch (SQLException ex) {}
        finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
    }
}
