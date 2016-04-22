package Repositories;

import Model.Student;
import Utils.DatabaseUtils;
import Utils.TestDatabaseUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class StudentRepositoryTest {

    @Test
    public void testCreateReadDelete() throws Exception {

        String expectedFirstName = "Test1";
        String expectedMidName = "Test2";
        String expectedLastName = "Test3";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
        java.util.Date parsed = formatter.parse("1996-01-01");
        Date expectedDateOfBirth = new java.sql.Date(parsed.getTime());
        String expectedGroupNumber = "Test4";
        String expectedStatement = "Test5";

        Student expectedStudent = new Student();
        expectedStudent.setFirstName(expectedFirstName);
        expectedStudent.setMidName(expectedMidName);
        expectedStudent.setLastName(expectedLastName);
        expectedStudent.setDateOfBirth(expectedDateOfBirth);
        expectedStudent.setGroupNumber(expectedGroupNumber);
        expectedStudent.setStatement(expectedStatement);
        expectedStudent.setUserId(1);

        StudentRepository sr = new StudentRepository();
        int last_inserted_id = sr.create(expectedStudent);
        Student actualStudent = sr.read(last_inserted_id);

        sr.delete(last_inserted_id);

        Assert.assertEquals(expectedFirstName, actualStudent.getFirstName());
        Assert.assertEquals(expectedMidName, actualStudent.getMidName());
        Assert.assertEquals(expectedLastName, actualStudent.getLastName());
        Assert.assertEquals(expectedDateOfBirth.toString(), actualStudent.getDateOfBirth().toString());
        Assert.assertEquals(expectedGroupNumber, actualStudent.getGroupNumber());
        Assert.assertEquals(expectedStatement, actualStudent.getStatement());
    }

    @Test
    public void testUpdateReadDelete() throws Exception {

    }

    @Before
    public void createConnection() {
        DatabaseUtils.setInstance(new TestDatabaseUtils());
    }

}