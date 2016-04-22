package Repositories;

import Interfaces.IRepository;
import Model.Role;
import Model.User;
import Utils.DatabaseUtils;

import javax.naming.NamingException;
import java.sql.*;

public class UserRepository implements IRepository<User> {

    @Override
    public int create(User item) {
        Connection connection = null;
        PreparedStatement statement = null;
        int result = -1;

        try {
            connection = DatabaseUtils.getInstance().getConnection();
            statement = connection.prepareStatement("INSERT INTO User (Login, Password, Salt, ID_Role) VALUES (?, ?, ?, 2);");
            statement.setString(1, item.getLogin());
            statement.setBytes(2, item.getPassword());
            statement.setBytes(3, item.getSalt());
            statement.executeUpdate();

            ResultSet resultSet = statement.executeQuery("SELECT LAST_INSERT_ID();");
            resultSet.next();
            result = resultSet.getInt("LAST_INSERT_ID()");
            item.setUserId(result);
        }
        catch (NamingException ex) {  }
        catch (SQLException ex) { }
        finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
        return result;
    }

    public User getUserByLogin(String login) {
        Connection connection = null;
        Statement statement = null;
        User result = null;

        try {
            connection = DatabaseUtils.getInstance().getConnection();
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM User WHERE Login = '%s'", login));
            while (resultSet.next()) {
                result = new User();
                result.setLogin(resultSet.getString("Login"));
                result.setPassword(resultSet.getBytes("Password"));
                result.setSalt(resultSet.getBytes("Salt"));
                result.setUserId(resultSet.getInt("ID_User"));

                int roleId = resultSet.getInt("ID_Role");
                ResultSet tempRS = statement.executeQuery(String.format("SELECT RoleName FROM Role WHERE ID_Role=%d", roleId));
                tempRS.next();
                String roleName = tempRS.getString("RoleName");
                result.setRole(Role.valueOf(roleName));
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
    public User read(int id) {
        Connection connection = null;
        Statement statement = null;
        User result = new User();

        try {
            connection = DatabaseUtils.getInstance().getConnection();
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM User WHERE ID_User = '%d'", id));
            while (resultSet.next()) {
                result.setLogin(resultSet.getString("Login"));
                result.setPassword(resultSet.getBytes("Password"));
                result.setSalt(resultSet.getBytes("Salt"));
                result.setUserId(resultSet.getInt("ID_User"));

                int roleId = resultSet.getInt("ID_Role");
                ResultSet tempRS = statement.executeQuery(String.format("SELECT RoleName FROM Role WHERE ID_Role=%d", roleId));
                tempRS.next();
                String roleName = tempRS.getString("RoleName");
                result.setRole(Role.valueOf(roleName));
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
            connection = DatabaseUtils.getInstance().getConnection();
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
            connection = DatabaseUtils.getInstance().getConnection();
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
