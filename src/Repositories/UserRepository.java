package Repositories;

import Model.Role;
import Model.User;
import Utils.DatabaseUtils;

import javax.naming.NamingException;
import java.sql.*;

public class UserRepository {

    public int create(User item) {
        Connection connection = null;
        PreparedStatement statement = null;
        int resultId = -1;

        try {
            connection = DatabaseUtils.getInstance().getConnection();

            Statement state = connection.createStatement();
            ResultSet resultSet = state.executeQuery(String.format("SELECT ID_Role FROM Role WHERE RoleName='%s'",
                    item.getRole()));
            resultSet.next();
            int roleId = resultSet.getInt("ID_Role");

            statement = connection.prepareStatement("INSERT INTO User (Login, Password, Salt, ID_Role) VALUES (?, ?, ?, ?);");
            statement.setString(1, item.getLogin());
            statement.setBytes(2, item.getPassword());
            statement.setBytes(3, item.getSalt());
            statement.setInt(4, roleId);
            statement.executeUpdate();

            resultSet = statement.executeQuery("SELECT LAST_INSERT_ID() AS id;");
            resultSet.next();
            resultId = resultSet.getInt("id");
            item.setUserId(resultId);
        }
        catch (NamingException ex) {  }
        catch (SQLException ex) { }
        finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
        return resultId;
    }

    public User getUserByLogin(String login) {
        Connection connection = null;
        Statement statement = null;
        User result = null;

        try {
            connection = DatabaseUtils.getInstance().getConnection();
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM User JOIN Role " +
                    "ON User.ID_Role = Role.ID_Role WHERE Login='%s'", login));
            while (resultSet.next()) {
                result = new User();
                result.setUserId(resultSet.getInt("ID_User"));
                result.setLogin(resultSet.getString("Login"));
                result.setPassword(resultSet.getBytes("Password"));
                result.setSalt(resultSet.getBytes("Salt"));
                result.setRole(Role.valueOf(resultSet.getString("RoleName")));
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

    public User read(int id) {
        Connection connection = null;
        Statement statement = null;
        User result = new User();

        try {
            connection = DatabaseUtils.getInstance().getConnection();
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM User JOIN Role " +
                    "ON User.ID_Role = Role.ID_Role WHERE ID_User=%d", id));
            while (resultSet.next()) {
                result.setLogin(resultSet.getString("Login"));
                result.setPassword(resultSet.getBytes("Password"));
                result.setSalt(resultSet.getBytes("Salt"));
                result.setUserId(resultSet.getInt("ID_User"));
                result.setRole(Role.valueOf(resultSet.getString("RoleName")));
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

    public void update(User item) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DatabaseUtils.getInstance().getConnection();

            Statement state = connection.createStatement();
            ResultSet resultSet = state.executeQuery(String.format("SELECT ID_Role FROM Role WHERE RoleName='%s'",
                    item.getRole()));
            resultSet.next();
            int roleId = resultSet.getInt("ID_Role");

            statement = connection.prepareStatement("UPDATE User SET Login = ?, Password = ?, Salt = ?, ID_Role = ? WHERE ID_User = ?");
            statement.setString(1, item.getLogin());
            statement.setBytes(2, item.getPassword());
            statement.setBytes(3, item.getSalt());
            statement.setInt(4, roleId);
            statement.setInt(5, item.getUserId());
            statement.executeUpdate();
        }
        catch (NamingException ex) {}
        catch (SQLException ex) { }
        finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
    }

    public void delete(int id) {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = DatabaseUtils.getInstance().getConnection();
            statement = connection.createStatement();
            statement.execute(String.format("DELETE FROM User WHERE ID_User = %d", id));
        }
        catch (NamingException ex) {}
        catch (SQLException ex) {}
        finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
    }
}
