package Repositories;

import Model.Dormitory;
import Utils.DatabaseUtils;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DormitoryRepository {

    public static int create(Dormitory dormitory) {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet;

        try {
            connection = DatabaseUtils.getInstance().getConnection();
            statement = connection.createStatement();

            statement.execute(String.format("INSERT INTO Dormitory (`Number`, `Address`, `FreeBlocksCount`, `MaxBlocksCount`) " +
                    "VALUES ('%s','%s','%s','%s')", dormitory.getDormitoryNumber(),
                    dormitory.getAddress(), dormitory.getFreeBlocksCount(), dormitory.getMaxBlocksCount()));
            resultSet = statement.executeQuery("SELECT LAST_INSERT_ID() as id");
            resultSet.next();
            dormitory.setDormitoryId(resultSet.getInt("id"));
        } catch (NamingException ex) {
        } catch (SQLException ex) {
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }

        return dormitory.getDormitoryId();
    }

    public static Dormitory read(int id) {
        Connection connection = null;
        Statement statement = null;
        Dormitory dormitory = new Dormitory();

        try {
            connection = DatabaseUtils.getInstance().getConnection();
            statement = connection.createStatement();

            statement.executeQuery("SET CHARACTER SET UTF8");
            statement.executeQuery("SET CHARSET UTF8");
            statement.executeQuery("SET NAMES UTF8");

            ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM Dormitory WHERE ID_Dormitory = %d", id));
            while (resultSet.next()) {
                dormitory.setDormitoryId(resultSet.getInt("ID_Dormitory"));
                dormitory.setDormitoryNumber(resultSet.getInt("Number"));
                dormitory.setAddress(resultSet.getString("Address"));
                dormitory.setMaxBlocksCount(resultSet.getInt("MaxBlocksCount"));
                dormitory.setFreeBlocksCount(resultSet.getInt("FreeBlocksCount"));
            }
        } catch (NamingException ex) {
        } catch (SQLException ex) {
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }

        return dormitory;
    }

    public static void update(Dormitory item) {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = DatabaseUtils.getInstance().getConnection();
            statement = connection.createStatement();

            statement.executeUpdate(String.format("UPDATE `Dormitory` SET `Number` = '%d', `Address` = '%s', " +
                    "`FreeBlocksCount` = '%d', `MaxBlocksCount` = '%d' WHERE `ID_Dormitory` = '%d'",
                    item.getDormitoryNumber(), item.getAddress(), item.getFreeBlocksCount(),
                    item.getMaxBlocksCount(), item.getDormitoryId()));
        } catch (NamingException ex) { int i = 2;
        } catch (SQLException ex) { int i = 2;
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
    }

    public static void delete(int id) {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = DatabaseUtils.getInstance().getConnection();
            statement = connection.createStatement();

            statement.execute(String.format("DELETE FROM Dormitory WHERE ID_Dormitory = '%d'", id));
        } catch (NamingException ex) {
        } catch (SQLException ex) {
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
    }

    public static void update(Integer dormitoryID, Integer freeBlocksCount) {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = DatabaseUtils.getInstance().getConnection();
            statement = connection.createStatement();

            statement.executeUpdate(String.format("UPDATE `Dormitory` SET `FreeBlocksCount` = '%d' WHERE `ID_Dormitory` = '%d'",
                    freeBlocksCount, dormitoryID));
        } catch (NamingException ex) { int i = 2;
        } catch (SQLException ex) { int i = 2;
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
    }

    public static void addAll(Dormitory[] dormitories) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet;

        try {
            connection = DatabaseUtils.getInstance().getConnection();
            statement = connection.createStatement();

            for (int i = 0; i < dormitories.length; i++) {
                statement.execute(String.format("INSERT INTO Dormitory (Number, Address, FreeBlocksCount, " +
                        "MaxBlocksCount) VALUES ('%s','%s','%s','%s')", dormitories[i].getDormitoryNumber(),
                        dormitories[i].getAddress(), dormitories[i].getFreeBlocksCount(), dormitories[i].getMaxBlocksCount()));
                resultSet = statement.executeQuery("SELECT LAST_INSERT_ID() as id");
                resultSet.next();
                dormitories[i].setDormitoryId(resultSet.getInt("id"));
            }
        } catch (NamingException ex) {
        } catch (SQLException ex) {
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
    }

    public static ArrayList<Dormitory> readAll() {
        Connection connection = null;
        Statement statement = null;
        ArrayList<Dormitory> list = new ArrayList<Dormitory>();

        try {
            connection = DatabaseUtils.getInstance().getConnection();
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM Dormitory ORDER BY Number");

            while (resultSet.next()) {
                Dormitory dormitory = new Dormitory();
                dormitory.setDormitoryId(resultSet.getInt("ID_Dormitory"));
                dormitory.setDormitoryNumber(resultSet.getInt("Number"));
                dormitory.setAddress(resultSet.getString("Address"));
                dormitory.setMaxBlocksCount(resultSet.getInt("MaxBlocksCount"));
                dormitory.setFreeBlocksCount(resultSet.getInt("FreeBlocksCount"));
                list.add(dormitory);
            }
        } catch (NamingException ex) {
        } catch (SQLException ex) {
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }

        return list;
    }
}
