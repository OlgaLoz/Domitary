package Repositories;

import Interfaces.IRepository;
import main.DatabaseUtils;
import Model.Room;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RoomRepository implements IRepository<Room> {

    @Override
    public int create(Room item) {

        Connection connection = null;
        Statement statement = null;
        ResultSet r = null;
        int res = 0;

        try {
            connection = DatabaseUtils.getConnection();
            statement = connection.createStatement();

            statement.execute(String.format("INSERT INTO `Room` (`ID_Block`, `RoomNumber`, `MaxPlacesCount`, `FreePlacesCount`) " +
                    "VALUES ('%s','%s','%s','%s')", item.getBlockId(), item.getRoomNumber(), item.getMaxPlacesCount(), item.getFreePlacesCount()));
            r = statement.executeQuery("SELECT LAST_INSERT_ID() as id");
            r.next();
            res = r.getInt("id");
        }
        catch (NamingException ex) { result = ex.getMessage(); }
        catch (SQLException ex) { result = ex.getMessage(); }
        finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }

        return res;
    }

    @Override
    public Room read(int id) {
        Connection connection = null;
        Statement statement = null;
        Room room = new Room();

        try {
            connection = DatabaseUtils.getConnection();
            statement = connection.createStatement();
            // SQL ���:
            statement.executeQuery("SET CHARACTER SET UTF8");
            statement.executeQuery("SET CHARSET UTF8");
            statement.executeQuery("SET NAMES UTF8");
            ResultSet r = statement.executeQuery(String.format("SELECT * FROM `Room` WHERE %d = `ID_Room`", id));

            while(r.next())
            {
                room.setBlockId(r.getInt("ID_Block"));
                room.setRoomNumber(r.getInt("RoomNumber"));
                room.setRoomId(r.getInt("ID_Room"));
                room.setMaxPlacesCount(r.getInt("MaxPlacesCount"));
                room.setFreePlacesCount(r.getInt("FreePlacesCount"));
            }

            room.setBlockId(r.getInt("ID_Block"));
            room.setRoomNumber(r.getInt("RoomNumber"));
            room.setRoomId(r.getInt("ID_Room"));
            room.setMaxPlacesCount(r.getInt("MaxPlacesCount"));
            room.setFreePlacesCount(r.getInt("FreePlacesCount"));
        }
        catch (NamingException ex) {  }
        catch (SQLException ex) {  }
        finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }

        return room;
    }

    @Override
    public void update(Room item) {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = DatabaseUtils.getConnection();
            statement = connection.createStatement();

            statement.executeUpdate(String.format("UPDATE Room SET ID_Block = '%d', RoomNumber = '%s', MaxPlacesCount = '%s', " +
                    "FreePlacesCount = '%s' WHERE ID_Room = '%d'", item.getBlockId(), item.getRoomNumber(), item.getMaxPlacesCount(),
                            item.getFreePlacesCount(), item.getRoomId()));
        }
        catch (NamingException ex) { result = ex.getMessage(); }
        catch (SQLException ex) { result = ex.getMessage(); }
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

            statement.execute(String.format("DELETE FROM Room WHERE ID_Room = '%d'", id));
        }
        catch (NamingException ex) { result = ex.getMessage(); }
        catch (SQLException ex) { result = ex.getMessage(); }
        finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
    }

    public String result = "0";
}
