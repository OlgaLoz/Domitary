package Repositories;

import Interfaces.IRepository;
import Utils.DatabaseUtils;
import Model.Room;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class RoomRepository implements IRepository<Room> {

    @Override
    public int create(Room room) {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseUtils.getInstance().getConnection();
            statement = connection.createStatement();

            statement.execute(String.format("INSERT INTO Room (ID_Block, RoomNumber, MaxPlacesCount, FreePlacesCount) " +
                    "VALUES ('%s','%s','%s','%s')", room.getBlockId(), room.getRoomNumber(), room.getMaxPlacesCount(), room.getFreePlacesCount()));
            resultSet = statement.executeQuery("SELECT LAST_INSERT_ID() as id");
            resultSet.next();
            room.setRoomId(resultSet.getInt("id"));
        }
        catch (NamingException ex) { }
        catch (SQLException ex) { }
        finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }

        return room.getRoomId();
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

    public void addAll(Room[] rooms){
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        int[] roomsIndexes = new int[rooms.length];

        try {
            connection = DatabaseUtils.getInstance().getConnection();
            statement = connection.createStatement();

            for (int i = 0; i < rooms.length; i++) {
                statement.execute(String.format("INSERT INTO Room (ID_Block, RoomNumber, MaxPlacesCount, FreePlacesCount) " +
                        "VALUES ('%s','%s','%s','%s')", rooms[i].getBlockId(), rooms[i].getRoomNumber(), rooms[i].getMaxPlacesCount(), rooms[i].getFreePlacesCount()));
                resultSet = statement.executeQuery("SELECT LAST_INSERT_ID() as id");
                resultSet.next();
                rooms[i].setRoomId(resultSet.getInt("id"));
            }
        }
        catch (NamingException ex) { }
        catch (SQLException ex) { }
        finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
    }

    public ArrayList<Room> readAll(){
        Connection connection = null;
        Statement statement = null;
        ArrayList<Room> list = new ArrayList<Room>();

        try {
            connection = DatabaseUtils.getInstance().getConnection();
            statement = connection.createStatement();

            statement.executeQuery("SET CHARACTER SET UTF8");
            statement.executeQuery("SET CHARSET UTF8");
            statement.executeQuery("SET NAMES UTF8");
            ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM Room"));

            while(resultSet.next()){
                Room room = new Room();
                room.setBlockId(resultSet.getInt("ID_Block"));
                room.setRoomNumber(resultSet.getInt("RoomNumber"));
                room.setRoomId(resultSet.getInt("ID_Room"));
                room.setMaxPlacesCount(resultSet.getInt("MaxPlacesCount"));
                room.setFreePlacesCount(resultSet.getInt("FreePlacesCount"));
                list.add(room);
            }
        }
        catch (NamingException ex) {  }
        catch (SQLException ex) {  }
        finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }

        return list;
    }
}