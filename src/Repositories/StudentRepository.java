package Repositories;

import Model.Student;
import Model.StudentStatus;
import Utils.DatabaseUtils;

import javax.naming.NamingException;
import java.sql.*;

public class StudentRepository {

    public int create(Student item, int userId) {

        Connection connection = null;
        Statement statement = null;
        int resultId = -1;

        try {
            connection = DatabaseUtils.getInstance().getConnection();
            statement = connection.createStatement();

            statement.execute("SET CHARACTER SET UTF8");
            statement.execute("SET CHARSET UTF8");
            statement.execute("SET NAMES UTF8");

            ResultSet resultSet = statement.executeQuery("SELECT ID_Status FROM StudentStatus WHERE StatusName='candidate'");
            resultSet.next();
            int statusId = resultSet.getInt("ID_Status");

            statement.execute(String.format("INSERT INTO Student (FirstName, MidName, LastName, DateOfBirth, " +
                            "GroupNumber, Statement, ID_Status, ID_User) VALUES ('%s','%s','%s','%s','%s','%s','%s','%s')",
                    item.getFirstName(), item.getMidName(), item.getLastName(), item.getDateOfBirth(),
                    item.getGroupNumber(), item.getStatement(), statusId, userId));

            resultSet = statement.executeQuery("SELECT LAST_INSERT_ID() as id");
            resultSet.next();
            resultId = resultSet.getInt("id");
        }
        catch (NamingException ex) { }
        catch (SQLException ex) { }
        finally {

            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
        return resultId;
    }

    public Student read(int id) {
        Connection connection = null;
        Statement statement = null;
        Student student = null;
        try {
            connection = DatabaseUtils.getInstance().getConnection();
            statement = connection.createStatement();
            student = new Student();

            statement.execute("SET CHARACTER SET UTF8");
            statement.execute("SET CHARSET UTF8");
            statement.execute("SET NAMES UTF8");
            ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM Student WHERE ID_Student = '%s'", id));
            while (resultSet.next()) {

                student.setStudentId(resultSet.getInt("ID_Student"));
                student.setFirstName(resultSet.getString("FirstName"));
                student.setMidName(resultSet.getString("MidName"));
                student.setLastName(resultSet.getString("LastName"));
                student.setDateOfBirth(resultSet.getDate("DateOfBirth"));
                student.setGroupNumber(resultSet.getString("GroupNumber"));
                student.setStatement(resultSet.getString("Statement"));
                student.setDateOfSettlement(resultSet.getDate("DateOfSettlement"));
                student.setOrder(resultSet.getString("Order"));
                student.setContract(resultSet.getString("Contract"));
                student.setRoomId(resultSet.getInt("ID_Room"));

                int statusId = resultSet.getInt("ID_Status");
                ResultSet tempRS = statement.executeQuery(String.format("SELECT StatusName FROM StudentStatus WHERE ID_Status='%s'", statusId));
                tempRS.next();
                String statusName = tempRS.getString("StatusName");
                student.setStudentStatus(StudentStatus.valueOf(statusName));
            }
        }
        catch (NamingException ex) { }
        catch (SQLException ex) { }
        finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }
        return student;
    }

    public void update(Student item) {

        Connection connection = null;
        Statement statement = null;
        try {
            connection = DatabaseUtils.getInstance().getConnection();
            statement = connection.createStatement();

            statement.execute("SET CHARACTER SET UTF8");
            statement.execute("SET CHARSET UTF8");
            statement.execute("SET NAMES UTF8");

            String statusName = item.getStudentStatus().toString();
            ResultSet tempRS = statement.executeQuery(String.format("SELECT ID_Status FROM StudentStatus WHERE StatusName='%s'", statusName));
            tempRS.next();
            int statusId = tempRS.getInt("ID_Status");

            statement.executeUpdate(String.format("UPDATE Student SET FirstName='%s', MidName='%s', " +
                            "LastName='%s', DateOfBirth='%s', GroupNumber='%s', Statement='%s', DateOfSettlement='%s' " +
                            "Order='%s', Contract='%s', ID_Room='%s', ID_Status='%s' WHERE ID_Student='%s'",
                    item.getFirstName(), item.getMidName(), item.getLastName(), item.getDateOfBirth(),
                    item.getGroupNumber(), item.getStatement(), item.getDateOfSettlement(), item.getOrder(),
                    item.getContract(), item.getRoomId(), statusId, item.getStudentId()));
        }
        catch (NamingException ex) { }
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
            statement.execute(String.format("DELETE FROM Student WHERE ID_Student = '%s'", id));
        }
        catch (NamingException ex) { }
        catch (SQLException ex) { }
        finally {
            DatabaseUtils.closeStatement(statement);
            DatabaseUtils.closeConnection(connection);
        }

    }
}