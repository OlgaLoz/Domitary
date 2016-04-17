package Repositories;

import Interfaces.IRepository;
import Utils.DatabaseUtils;
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
        ResultSet resultSet = null;
        int res = 0;

        try {
            connection = DatabaseUtils.getInstance().getConnection();
            statement = connection.createStatement();

            statement.execute(String.format("INSERT INTO Room (ID_Block, RoomNumber, MaxPlacesCount, FreePlacesCount) " +
                    "VALUES ('%s','%s','%s','%s')", item.getBlockId(), item.getRoomNumber(), item.getMaxPlacesCount(), item.getFreePlacesCount()));
            resultSet = statement.executeQuery("SELECT LAST_INSERT_ID() as id");
            resultSet.next();
            res = resultSet.getInt("id");
        }
        catch (NamingException ex) { }
        catch (SQLException ex) { }
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
            connection = DatabaseUtils.getInstance().getConnection();
            statement = connection.createStatement();

            statement.executeQuery("SET CHARACTER SET UTF8");
            statement.executeQuery("SET CHARSET UTF8");
            statement.executeQuery("SET NAMES UTF8");
            ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM Room WHERE ID_Room = %d", id));
            while(resultSet.next())
            {
                room.setBlockId(resultSet.getInt("ID_Block"));
                room.setRoomNumber(resultSet.getInt("RoomNumber"));
                room.setRoomId(resultSet.getInt("ID_Room"));
                room.setMaxPlacesCount(resultSet.getInt("MaxPlacesCount"));
                room.setFreePlacesCount(resultSet.getInt("FreePlacesCount"));
            }
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
            connection = DatabaseUtils.getInstance().getConnection();
            statement = connection.createStatement();

            statement.executeUpdate(String.format("UPDATE Room SET ID_Block = '%d', RoomNumber = '%s', MaxPlacesCount = '%s', " +
                    "FreePlacesCount = '%s' WHERE ID_Room = '%d'", item.getBlockId(), item.getRoomNumber(), item.getMaxPlacesCount(),
                            item.getFreePlacesCount(), item.getRoomId()));
        }
        catch (NamingException ex) { }
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

            statement.execute(String.format("DELETE FROM Room WHERE ID_Room = '%d'", id));
        }
        catch (NamingException ex) { }
        catch (SQLException ex) { }
        finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
    }

}
