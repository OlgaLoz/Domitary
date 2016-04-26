package Repositories;


import Model.Block;
import Utils.DatabaseUtils;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BlockRepository {

    public int create(Block block) {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet;

        try {
            connection = DatabaseUtils.getInstance().getConnection();
            statement = connection.createStatement();

            statement.execute(String.format("INSERT INTO Block (ID_Dormitory, BlockNumber) " + "VALUES ('%s','%s')",
                    block.getDormitoryId(), block.getBlockNumber()));
            resultSet = statement.executeQuery("SELECT LAST_INSERT_ID() as id");
            resultSet.next();
            block.setBlockId(resultSet.getInt("id"));
        } catch (NamingException ex) {
        } catch (SQLException ex) {
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }

        return block.getBlockId();
    }

    public Block read(int id) {
        Connection connection = null;
        Statement statement = null;
        Block block = new Block();

        try {
            connection = DatabaseUtils.getInstance().getConnection();
            statement = connection.createStatement();

            statement.executeQuery("SET CHARACTER SET UTF8");
            statement.executeQuery("SET CHARSET UTF8");
            statement.executeQuery("SET NAMES UTF8");

            ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM Block WHERE ID_Block = %d", id));
            while (resultSet.next()) {
                block.setDormitoryId(resultSet.getInt("ID_Dormitory"));
                block.setBlockNumber(resultSet.getInt("BlockNumber"));
                block.setBlockId(resultSet.getInt("ID_Block"));
            }
        } catch (NamingException ex) {
        } catch (SQLException ex) {
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }

        return block;
    }

    public void update(Block item) {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = DatabaseUtils.getInstance().getConnection();
            statement = connection.createStatement();

            statement.executeUpdate(String.format("UPDATE Block SET ID_Dormitory = '%d', BlockNumber = '%s' WHERE ID_Block = '%d'",
                    item.getDormitoryId(), item.getBlockNumber(), item.getBlockId()));
        } catch (NamingException ex) {
        } catch (SQLException ex) {
        } finally {
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

            statement.execute(String.format("DELETE FROM Block WHERE ID_Block = '%d'", id));
        } catch (NamingException ex) {
        } catch (SQLException ex) {
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
    }

    public void addAll(Block[] blocks) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet;

        try {
            connection = DatabaseUtils.getInstance().getConnection();
            statement = connection.createStatement();

            for (int i = 0; i < blocks.length; i++) {
                statement.execute(String.format("INSERT INTO Block (ID_Dormitory, BlockNumber) " +
                        "VALUES ('%s','%s')", blocks[i].getDormitoryId(), blocks[i].getBlockNumber()));
                resultSet = statement.executeQuery("SELECT LAST_INSERT_ID() as id");
                resultSet.next();
                blocks[i].setBlockId(resultSet.getInt("id"));
            }
        } catch (NamingException ex) {
        } catch (SQLException ex) {
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
    }

    public ArrayList<Block> readAll() {
        Connection connection = null;
        Statement statement = null;
        ArrayList<Block> list = new ArrayList<Block>();

        try {
            connection = DatabaseUtils.getInstance().getConnection();
            statement = connection.createStatement();

            statement.executeQuery("SET CHARACTER SET UTF8");
            statement.executeQuery("SET CHARSET UTF8");
            statement.executeQuery("SET NAMES UTF8");

            ResultSet resultSet = statement.executeQuery("SELECT * FROM Block ORDER BY BlockNumber");

            while (resultSet.next()) {
                Block block = new Block();
                block.setDormitoryId(resultSet.getInt("ID_Dormitory"));
                block.setBlockNumber(resultSet.getInt("BlockNumber"));
                block.setBlockId(resultSet.getInt("ID_Block"));
                list.add(block);
            }
        } catch (NamingException ex) {
        } catch (SQLException ex) {
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }

        return list;
    }

    public ArrayList<Block> readAllByDormitoryId(int dormitoryId) {
        Connection connection = null;
        Statement statement = null;
        ArrayList<Block> blocks = new ArrayList<Block>();
        RoomRepository rr = new RoomRepository();

        try {
            connection = DatabaseUtils.getInstance().getConnection();
            statement = connection.createStatement();

            statement.executeQuery("SET CHARACTER SET UTF8");
            statement.executeQuery("SET CHARSET UTF8");
            statement.executeQuery("SET NAMES UTF8");

            ResultSet resultSet = statement.executeQuery("SELECT BlockNumber, ID_Block FROM Block " +
                    "WHERE ID_Dormitory = " + dormitoryId + " ORDER BY BlockNumber");

            while (resultSet.next()) {
                Block block = new Block();
                block.setDormitoryId(dormitoryId);
                block.setBlockNumber(resultSet.getInt("BlockNumber"));
                block.setBlockId(resultSet.getInt("ID_Block"));
                blocks.add(block);
            }
        } catch (NamingException ex) {
        } catch (SQLException ex) {
        } finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }

        for(Block block : blocks) {
            block.setRooms(rr.readAllByBlockId(block.getBlockId()));
        }

        return blocks;
    }
}
